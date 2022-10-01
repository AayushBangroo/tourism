package com.tours.tourism.repository;

import com.tours.tourism.dto.MonthlyPlan;
import com.tours.tourism.dto.TourStats;
import com.tours.tourism.entities.Tour;
import com.tours.tourism.utils.APIFeatures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class TourCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public long count() {
        return mongoTemplate.count(new Query(), Tour.class);
    }

    public List<Tour> findAllByParams(Map<String, String> params) {
        Query query = getFilteredQuery(params);

        return mongoTemplate.find(query, Tour.class);
    }

    public List<TourStats> getTourStats(){
        MatchOperation filterTours = match(new Criteria("ratingsAverage").gte(4.5));

        GroupOperation groupById = group("difficulty")
                                    .count().as("numTours")
                                    .sum("ratingsQuantity").as("numRatings")
                                    .avg("price").as("avgPrice")
                                    .min("price").as("minPrice")
                                    .max("price").as("maxPrice")
                                    .avg("ratingsAverage").as("avgRating");

        SortOperation sortOperation = sort(Sort.by(Sort.Direction.ASC, "numTours"));

        Aggregation aggregation = Aggregation.newAggregation(filterTours, groupById, sortOperation);
        AggregationResults<TourStats> results = mongoTemplate.aggregate(aggregation, "tours", TourStats.class);

        return results.getMappedResults();
    }

    public List<MonthlyPlan> getMonthlyPlan(int year){
        UnwindOperation unwindOperation = unwind("startDates");

        //Date match
        LocalDateTime startTime = LocalDateTime.of(year, Month.JANUARY, 1,0,0);
        LocalDateTime endTime = LocalDateTime.of(year, Month.DECEMBER, 31,23,59);
        MatchOperation matchOperation = match(new Criteria("startDates").gte(startTime).lte(endTime));

        //group
        //projection to extract month
        ProjectionOperation projectionOperation = project("startDates", "name")
                .and(DateOperators.Month.month("$startDates")).as("month");
        //Group by month and push Tours
        GroupOperation groupOperation = group("month")
                .count().as("numTourStarts")
                .push("name").as("tour");
        //add field month
        AddFieldsOperation addFieldsOperation = addFields().addFieldWithValue("month", "$_id").build();
        //sort by numTours
        SortOperation sortOperation = sort(Sort.by(Sort.Direction.DESC, "numTourStarts"));
        //aggregate
        Aggregation aggregation = Aggregation.newAggregation(unwindOperation, matchOperation,
                                    projectionOperation, groupOperation, addFieldsOperation,
                                    sortOperation);
        AggregationResults<MonthlyPlan> results = mongoTemplate.aggregate(aggregation, "tours", MonthlyPlan.class);

        return results.getMappedResults();
    }

    private Query getFilteredQuery(Map<String, String> params) {

        APIFeatures apiFeatures = new APIFeatures(params);
        return apiFeatures
                .sort()
                .projections()
                .paginate()
                .filter()
                .buildQuery();
    }
}

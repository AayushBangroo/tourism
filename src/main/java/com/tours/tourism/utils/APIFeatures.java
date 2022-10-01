package com.tours.tourism.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APIFeatures {

    final Query query = new Query();
    final Map<String, String> paginationProperties = new HashMap<>();
    final List<Criteria> criteria = new ArrayList<>();
    final Map<String, String> params;

    public APIFeatures(Map<String, String> queryParams) {
        this.params = queryParams;
    }

    public APIFeatures sort() {

        params.forEach((k, v) -> {
            if (k.equals("sort")) {
                String[] sortFields = v.split(",");

                //query.with(Sort.by(Sort.Direction.ASC, sortFields));
                Arrays.stream(sortFields).forEach(field -> {
                    if (field.charAt(0) == '-') {
                        query.with(Sort.by(Sort.Direction.DESC, field.substring(1)));
                    } else {
                        query.with(Sort.by(Sort.Direction.ASC, field));
                    }
                });
            }
        });

        return this;
    }

    public APIFeatures projections() {

        params.forEach((k, v) -> {
            if (k.equals("fields")) {
                String[] projectFields = v.split(",");
                query.fields().include(projectFields);
            }
        });

        return this;
    }

    public APIFeatures paginate() {

        params.forEach((k, v) -> {
            if (k.equals("page") || k.equals("limit")) {
                paginationProperties.put(k, v);
            }
        });

        return this;
    }

    public APIFeatures filter() {

        params.forEach((k, v) -> {
            if (k.equals("limit") || k.equals("page") || k.equals("fields") || k.equals("sort")) return;

            Pattern digitPattern = Pattern.compile("[0-9]+");
            Matcher m = digitPattern.matcher(v);

            if (m.find()) {
                int value = Integer.parseInt(m.group());
                if (v.contains("<=")) {
                    criteria.add(Criteria.where(k).lte(value));
                } else if (v.contains(">=")) {
                    criteria.add(Criteria.where(k).gte(value));
                } else if (v.contains(">")) {
                    criteria.add(Criteria.where(k).gt(value));
                } else if (v.contains("<")) {
                    criteria.add(Criteria.where(k).lt(value));
                } else {
                    criteria.add(Criteria.where(k).is(value));
                }
            } else {
                criteria.add(Criteria.where(k).is(v));
            }
        });

        return this;
    }

    public Query buildQuery() {
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }
        // Pagination values
        int pageNumber = paginationProperties.get("page") != null ? Integer.parseInt(paginationProperties.get("page")) : 0;
        int limit = paginationProperties.get("limit") != null ? Integer.parseInt(paginationProperties.get("limit")) : 9;

        Pageable pageable = PageRequest.of(pageNumber, limit);
        query.with(pageable);

        return query;
    }
}
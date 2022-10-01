# Tourism
Tourism is a web application which focuses on making easy for users to book and inquire about their favourite tours. 

Stack:
- Spring Boot
- MongoDB
- Thymeleaf

## APIs
### Create Tours
- POST ```http://localhost:8080/api/v1/tours/```
- Creates a tour and saves in DB
- Example Payload:
```
{
    "name": "The Forest Hike",
    "duration": 5,
    "maxGroupSize": 25,
    "difficulty": "easy",
    "ratingsQuantity": 37,
    "price": 397,
    "summary": "Breathtaking hike through the Canadian Banff National Park",
    "description": "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\nLorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    "imageCover": "tour-1-cover.jpg",
    "images": ["tour-1-1.jpg", "tour-1-2.jpg", "tour-1-3.jpg"],
    "startDates": ["2021-04-25T10:00", "2021-07-20T10:00", "2021-10-05T10:00"]
  }
  ```
  
### Get Tours
#### List all tours
- GET ```http://localhost:8080/api/v1/tours```
- Retrieves all tours in DB
- Response:
```
{
    "data": {
        "tours": [
            {
                "id": "633829c3f3a45a7453b0701c",
                "name": "The Snow Adventurer",
                "duration": 4,
                "maxGroupSize": 10,
                "difficulty": "difficult",
                "price": 997.0,
                "ratingsAverage": 4.5,
                "ratingsQuantity": 13,
                "summary": "Exciting adventure in the snow with snowboarding and skiing",
                "description": "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum!\nDolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur, exercitation ullamco laboris nisi ut aliquip. Lorem ipsum dolor sit amet, consectetur adipisicing elit!",
                "imageCover": "tour-3-cover.jpg",
                "images": [
                    "tour-3-1.jpg",
                    "tour-3-2.jpg",
                    "tour-3-3.jpg"
                ],
                "startDates": [
                    "2022-01-05T10:00:00",
                    "2022-02-12T10:00:00",
                    "2023-01-06T10:00:00"
                ]
            },
            {
                "id": "633829c3f3a45a7453b0701d",
                "name": "The City Wanderer",
                "duration": 9,
                "maxGroupSize": 20,
                "difficulty": "easy",
                "price": 1197.0,
                "ratingsAverage": 4.6,
                "ratingsQuantity": 54,
                "summary": "Living the life of Wanderlust in the US' most beatiful cities",
                "description": "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat lorem ipsum dolor sit amet.\nConsectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur, nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat!",
                "imageCover": "tour-4-cover.jpg",
                "images": [
                    "tour-4-1.jpg",
                    "tour-4-2.jpg",
                    "tour-4-3.jpg"
                ],
                "startDates": [
                    "2021-03-11T10:00:00",
                    "2021-05-02T10:00:00",
                    "2021-06-09T10:00:00"
                ]
            },
            {
                "id": "633829c3f3a45a7453b0701e",
                "name": "The Park Camper",
                "duration": 10,
                "maxGroupSize": 15,
                "difficulty": "medium",
                "price": 1497.0,
                "ratingsAverage": 4.9,
                "ratingsQuantity": 19,
                "summary": "Breathing in Nature in America's most spectacular National Parks",
                "description": "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\nDuis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum!",
                "imageCover": "tour-5-cover.jpg",
                "images": [
                    "tour-5-1.jpg",
                    "tour-5-2.jpg",
                    "tour-5-3.jpg"
                ],
                "startDates": [
                    "2021-08-05T10:00:00",
                    "2022-03-20T10:00:00",
                    "2022-08-12T10:00:00"
                ]
            }
        ]
    },
    "status": "success"
}
```

### Get Tour by ID
- GET ```http://localhost:8080/api/v1/tours/<id>'``
- Retrieves a tour by given id
- Response:
```
{
    "data": {
        "tours": {
            "id": "633829c2f3a45a7453b0701a",
            "name": "The Forest Hiker",
            "duration": 5,
            "maxGroupSize": 25,
            "difficulty": "easy",
            "price": 397.0,
            "ratingsAverage": 4.7,
            "ratingsQuantity": 37,
            "summary": "Breathtaking hike through the Canadian Banff National Park",
            "description": "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\nLorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "imageCover": "tour-1-cover.jpg",
            "images": [
                "tour-1-1.jpg",
                "tour-1-2.jpg",
                "tour-1-3.jpg"
            ],
            "startDates": [
                "2021-04-25T10:00:00",
                "2021-07-20T10:00:00",
                "2021-10-05T10:00:00"
            ]
        }
    },
    "status": "success"
}
```

### Get Tour stats
- GET ```http://localhost:8080/api/v1/tours/tour-stats```
- Retrieves stats about tours grouped by difficulty ```easy``` ```medium``` ```hard```
- Response
```
{
    "data": {
        "tours": [
            {
                "numTours": 2,
                "numRatings": 41,
                "avgRating": 4.6,
                "avgPrice": 1997.0,
                "minPrice": 997.0,
                "maxPrice": 2997.0,
                "_id": "difficult"
            },
            {
                "numTours": 3,
                "numRatings": 70,
                "avgRating": 4.8,
                "avgPrice": 1663.6666666666667,
                "minPrice": 497.0,
                "maxPrice": 2997.0,
                "_id": "medium"
            },
            {
                "numTours": 4,
                "numRatings": 159,
                "avgRating": 4.675,
                "avgPrice": 1272.0,
                "minPrice": 397.0,
                "maxPrice": 1997.0,
                "_id": "easy"
            }
        ]
    },
    "status": "success"
}
```

### Top 5 cheap tours
- GET ```http://localhost:8080/api/v1/tours/top-5-cheap```
- Retrieves top 5 cheap tours
- Response:
```
{
    "data": {
        "tours": [
            {
                "id": "633829c4f3a45a7453b07022",
                "name": "The Northern Lights",
                "difficulty": "easy",
                "price": 1497.0,
                "ratingsAverage": 4.9,
                "summary": "Enjoy the Northern Lights in one of the best places in the world"
            },
            {
                "id": "633829c3f3a45a7453b0701e",
                "name": "The Park Camper",
                "difficulty": "medium",
                "price": 1497.0,
                "ratingsAverage": 4.9,
                "summary": "Breathing in Nature in America's most spectacular National Parks"
            },
            {
                "id": "633829c2f3a45a7453b0701b",
                "name": "The Sea Explorer",
                "difficulty": "medium",
                "price": 497.0,
                "ratingsAverage": 4.8,
                "summary": "Exploring the jaw-dropping US east coast by foot and by boat"
            },
            {
                "id": "633829c2f3a45a7453b0701a",
                "name": "The Forest Hiker",
                "difficulty": "easy",
                "price": 397.0,
                "ratingsAverage": 4.7,
                "summary": "Breathtaking hike through the Canadian Banff National Park"
            },
            {
                "id": "633829c3f3a45a7453b07021",
                "name": "The Star Gazer",
                "difficulty": "medium",
                "price": 2997.0,
                "ratingsAverage": 4.7,
                "summary": "The most remote and stunningly beautiful places for seeing the night sky"
            }
        ]
    },
    "status": "success"
}
```

### Get Monthly Plan
- GET ```http://localhost:8080/api/v1/tours/monthly-plan/<year>```
- Retrieves month wise data about tours starting in each month
- Reponse:
```
{
    "data": {
        "tours": [
            {
                "month": 1,
                "numTourStarts": 3,
                "tour": [
                    "The Snow Adventurer",
                    "The Star Gazer",
                    "The Northern Lights"
                ]
            },
            {
                "month": 3,
                "numTourStarts": 2,
                "tour": [
                    "The Park Camper",
                    "The Sports Lover"
                ]
            },
            {
                "month": 2,
                "numTourStarts": 1,
                "tour": [
                    "The Snow Adventurer"
                ]
            },
            {
                "month": 8,
                "numTourStarts": 1,
                "tour": [
                    "The Park Camper"
                ]
            },
            {
                "month": 12,
                "numTourStarts": 1,
                "tour": [
                    "The Northern Lights"
                ]
            }
        ]
    },
    "status": "success"
}
```

### Update Tour
- PUT ```http://localhost:8080/api/v1/tours/<id>```
- Updates a tour with tour id with provided details as body

### Delete Tour
- DELETE ```http://localhost:8080/api/v1/tours/<id>```
- Deletes a tour from db with given tour id

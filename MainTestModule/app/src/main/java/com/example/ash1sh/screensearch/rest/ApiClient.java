package com.example.ash1sh.screensearch.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// version 3 of the api is being currently used

// sample url analysis for the queries that will made in this application

// complete url that queries the data and record the responses according to main activity
// "https://api.themoviedb.org/3/discover/movie?api_key=e6b16025cc8301efd9259466db475f34&sort_by=popularity.desc&page=1&with_genres=18&year=2016"


// complete url for top_rated movies that is expected from main menu
// "http://api.themoviedb.org/3/movie/top_rated?api_key=e6b16025cc8301efd9259466db475f34&language=en-US&page=1"


// complete url for now playing movies that is expected from main menu
// "http://api.themoviedb.org/3/movie/now_playing?api_key=e6b16025cc8301efd9259466db475f34&page=1"


// complete url for upcoming movies that is expected from the main menu
// "http://api.themoviedb.org/3/movie/upcoming?api_key=e6b16025cc8301efd9259466db475f34&page=1"


// complete url of most popular movies that is expected from the main menu
// "http://api.themoviedb.org/3/movie/popular?api_key=e6b16025cc8301efd9259466db475f34&page=1"


// But all these url will yield response in the same format. Which is handled by the two model classes that are created.


public class ApiClient {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
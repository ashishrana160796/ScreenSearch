package com.example.ash1sh.screensearch.rest;


import com.example.ash1sh.screensearch.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

// all data types are String so that they can be concatednated with the query final string.


public interface ApiInterface {

    // interface method for top_rated movies
        @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") String page_no);


    // interface method for popular movies
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") String page_no);


    // interface method for upcoming movies
    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovies(@Query("api_key") String apiKey, @Query("page") String page_no);


    // interface method for now_playing movies
    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlayingMovies(@Query("api_key") String apiKey, @Query("page") String page_no);



    // interface method for discovering movies based on various queries that are made from main activity
    @GET("discover/movie")
    Call<MoviesResponse> getDiscoveredMovies(@Query("api_key") String apiKey,@Query("sort_by") String sort_by ,@Query("with_genres") String with_genres,@Query("years") String years,@Query("page") String page_no);



    // TODO -- MAYBE BE USED IF LISTENER IS ADDED TO EACH OF CARDVIEW ITEM
    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") String id, @Query("api_key") String apiKey);
}
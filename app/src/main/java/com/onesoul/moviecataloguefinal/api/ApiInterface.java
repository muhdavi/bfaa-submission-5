package com.onesoul.moviecataloguefinal.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("discover/movie")
    Call<String> getDiscoverMovie(@Query("api_key") String API_KEY,
                                  @Query("language") String language);

    @GET("discover/tv")
    Call<String> getDiscoverTv(@Query("api_key") String API_KEY,
                               @Query("language") String language);

    @GET("discover/movie")
    Call<String> getReleaseMovie(@Query("api_key") String API_KEY,
                                 @Query("primary_release_date.gte") String release_date,
                                 @Query("primary_release_date.lte") String today_date);

    @GET("search/movie")
    Call<String> getMovieSearch(@Query("api_key") String API_KEY,
                                @Query("language") String language,
                                @Query("query") String query);
}

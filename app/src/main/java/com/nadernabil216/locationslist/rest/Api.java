package com.nadernabil216.locationslist.rest;



import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface Api {
    @GET("popular")
    Observable<Response<PopularMoviesResponse>> getMovies(@Query("api_key") String apiKey, @Query("page") int page, @Query("language") String language);
}

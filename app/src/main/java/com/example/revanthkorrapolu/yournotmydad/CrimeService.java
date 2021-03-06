package com.example.revanthkorrapolu.yournotmydad;

import com.example.revanthkorrapolu.yournotmydad.JSONSchema.NYCCrime;
import com.example.revanthkorrapolu.yournotmydad.JSONSchema.SpotCrimeList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hemanth on 5/13/17.
 */

public interface CrimeService {

    @GET("/crimes.json")
    Call<SpotCrimeList> getSpotCrimes(@Query("lat") String lat, @Query("lon") String longw, @Query("radius") String radius, @Query("key") String key);

    @GET("/resource/qb7u-rbmr.json")
    Call<List<NYCCrime>> getNYCCrimes();



}

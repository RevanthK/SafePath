package com.example.revanthkorrapolu.yournotmydad;

import com.example.revanthkorrapolu.yournotmydad.CrimeService;
import com.example.revanthkorrapolu.yournotmydad.JSONSchema.NYCCrimeList;
import com.example.revanthkorrapolu.yournotmydad.JSONSchema.SpotCrimeList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hemanth on 5/13/17.
 */

public class RetrofitClient {
   private static Retrofit mSpotCrimeRetrofit;
    private static Retrofit mNYCRetrofit;
    private static CrimeService mSpotCrimeService;
    private static CrimeService mNYCCrimeService;
    private static final String url="https://api.spotcrime.com/";
    private static final String nycURL="https://data.cityofnewyork.us/";
    /*public static Retrofit getRetrofit(){
        if(mRetrofit==null){
            mRetrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return mRetrofit;
    }*/

    public static Call<SpotCrimeList> getSpotCrimes(int lat, int long1){

        //getRetrofit();
        if(mSpotCrimeRetrofit==null){
            mSpotCrimeRetrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(url).build();
        }
        if(mSpotCrimeService==null){
            mSpotCrimeService=mSpotCrimeRetrofit.create(CrimeService.class);
        }

        Call<SpotCrimeList> call=mSpotCrimeService.getSpotCrimes(Integer.toString(lat),Integer.toString(long1),"4","privatekeyforspotcrimepublicusers-commercialuse-877.410.1607");
        return call;
    }

    public static Call<NYCCrimeList> getNYCCrimes(){

        //getRetrofit();
        if(mNYCRetrofit==null){
            mNYCRetrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(nycURL).build();
        }
        if(mNYCCrimeService==null){
            mNYCCrimeService=mNYCRetrofit.create(CrimeService.class);
        }

        Call<NYCCrimeList> call=mNYCCrimeService.getNYCCrimes();
        return call;
    }


}

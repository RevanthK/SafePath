package com.example.revanthkorrapolu.yournotmydad;

import com.example.revanthkorrapolu.yournotmydad.JSONSchema.CrimeList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hemanth on 5/13/17.
 */

public class RetrofitClient {
    static Retrofit mRetrofit;
    static CrimeService mCrimeService;
    private static final String url="https://api.spotcrime.com/";
    public static Retrofit getRetrofit(){
        if(mRetrofit==null){
            mRetrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return mRetrofit;
    }

    public Call<CrimeList> getCrimes(int lat, int long1){
        if(mCrimeService==null){
            mCrimeService=mRetrofit.create(CrimeService.class);
        }

        Call<CrimeList> call=mCrimeService.getCrimes(Integer.toString(lat),Integer.toString(long1),"4","privatekeyforspotcrimepublicusers-commercialuse-877.410.1607");
        return call;
    }
}

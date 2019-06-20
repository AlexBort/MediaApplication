package com.example.mediaapplication.api;

import com.example.mediaapplication.model.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRequestsApi {

    @GET("api/")
    Call<ServerResponse> getResults(@Query("results") Integer results);
}

package com.example.mediaapplication.api;

import com.example.mediaapplication.model.ServerResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {


    private IRequestsApi requestsApi;

    public IRequestsApi getRequestsApi() {
        return requestsApi;
    }

    public ApiManager() {
        requestsApi = createRetrofitBuilder().create(IRequestsApi.class);
    }


    private final String BASE_URL = "https://randomuser.me/";

    private Retrofit createRetrofitBuilder() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                /*.readTimeout(5, TimeUnit.MINUTES)*/
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public void getResults(final ResponseListener listener) {
        Call<ServerResponse> call = requestsApi.getResults(20);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                listener.successResponse(response.body());
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                listener.failureResponse();
            }
        });
    }

}

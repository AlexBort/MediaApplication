package com.example.mediaapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mediaapplication.api.ApiManager;
import com.example.mediaapplication.api.ResponseListener;
import com.example.mediaapplication.model.ServerResponse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiManager apiManager = new ApiManager();
        apiManager.getResults(new ResponseListener() {
            @Override
            public void successResponse(ServerResponse response) {
                response.getResults();
            }

            @Override
            public void failureResponse() {

            }
        });


    }
}

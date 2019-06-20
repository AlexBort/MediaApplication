package com.example.mediaapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.mediaapplication.api.ApiManager;
import com.example.mediaapplication.api.ResponseListener;
import com.example.mediaapplication.model.ServerResponse;
import com.example.mediaapplication.recycler.RecyclerImpl;

public class MainActivity extends AppCompatActivity {


    private RecyclerImpl recyclerImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler);


        recyclerImpl = new RecyclerImpl(recyclerView);

//        ApiManager apiManager = new ApiManager();
//        apiManager.getResults(new ResponseListener() {
//            @Override
//            public void successResponse(ServerResponse response) {
//                response.getResults();
//            }
//
//            @Override
//            public void failureResponse() {
//
//            }
//        });


    }
}

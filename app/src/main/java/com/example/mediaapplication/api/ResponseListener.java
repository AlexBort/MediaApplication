package com.example.mediaapplication.api;

import com.example.mediaapplication.model.ServerResponse;

public interface ResponseListener {

    void successResponse(ServerResponse response);

    void failureResponse();

}

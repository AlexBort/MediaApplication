package com.example.mediaapplication.model;

import java.util.List;

public class ServerResponse {

    private List<User> results;
    private Inform info;

    public ServerResponse() {
    }

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }

    public Inform getInfo() {
        return info;
    }

    public void setInfo(Inform info) {
        this.info = info;
    }

}

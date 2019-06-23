package com.example.mediaapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mediaapplication.navigation.INavigation;
import com.example.mediaapplication.navigation.NavigationManager;

public class MainActivity extends AppCompatActivity {

    private INavigation navigationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationManager = new NavigationManager(this);
        navigationManager.openListFragment();
    }


    public INavigation getNavigationManager() {
        return navigationManager;
    }
}

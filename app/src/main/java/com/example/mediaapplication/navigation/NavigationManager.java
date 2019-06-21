package com.example.mediaapplication.navigation;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.mediaapplication.R;
import com.example.mediaapplication.base.BaseFragment;
import com.example.mediaapplication.ui.FullScrDialogFragment;
import com.example.mediaapplication.ui.GalleryFragment;

import java.util.ArrayList;

public class NavigationManager implements INavigation {

    private final FragmentStack mFragmentStack;

    public NavigationManager(AppCompatActivity activity) {
        mFragmentStack = new FragmentStack(activity, activity.getSupportFragmentManager(), R.id.container_fragment);
    }

    @Override
    public void popFragment() {
        mFragmentStack.popFragment();
    }

    @Override
    public void popDialog() {

    }

    public void openGalleryFragment(int position, ArrayList<String> listUrls) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("list", listUrls);
        bundle.putInt("position", position);
        mFragmentStack.addFragment(GalleryFragment.class, bundle);
    }


    @Override
    public void openListFragment() {
        Bundle bundle = new Bundle();
        mFragmentStack.addFragment(BaseFragment.class, bundle);
    }
}

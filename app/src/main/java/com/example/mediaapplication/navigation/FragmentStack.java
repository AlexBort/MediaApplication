package com.example.mediaapplication.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.mediaapplication.base.BaseFragment;

public class FragmentStack {

    private final FragmentManager mFragmentManager;
    private final int mContainerId;
    private final AppCompatActivity mActivity;

    public FragmentStack(AppCompatActivity activity, FragmentManager fragmentManager, int containerId) {
        mFragmentManager = fragmentManager;
        mContainerId = containerId;
        mActivity = activity;

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.disallowAddToBackStack();
    }

    public Fragment addFragment(@NonNull Class clazz, Bundle args) {
        Fragment fragment = Fragment.instantiate(mActivity, clazz.getName(), args);
        mFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(mContainerId, fragment)
                .addToBackStack(clazz.getName())
                .commit();

        return fragment;
    }

    public void popFragment() {
        Fragment fragment = activeFragment();
    if (fragment == null || (fragment instanceof BaseFragment && mFragmentManager.getBackStackEntryCount() <= 1)) {
            // Don't close all screens
            return;
        }
        mFragmentManager.popBackStack();
    }

    public Fragment addFragmentAndClearStack(@NonNull Class clazz, Bundle args) {
        // Clear stack
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        // Show new fragment
        Fragment fragment = Fragment.instantiate(mActivity, clazz.getName(), args);
        mFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(mContainerId, fragment)
                .addToBackStack(clazz.getName())
                .commit();

        return fragment;
    }

    public void backToFragmentAndClearStack(@NonNull Class clazz, Bundle args) {
        mFragmentManager.popBackStack(clazz.getName(), 0);
    }

    public Fragment activeFragment() {
        return mFragmentManager.findFragmentById(mContainerId);
    }


}

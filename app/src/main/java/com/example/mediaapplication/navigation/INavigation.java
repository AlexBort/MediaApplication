package com.example.mediaapplication.navigation;

import java.util.ArrayList;

public interface INavigation {

    void popFragment();

    void popDialog();

    void openListFragment();

    void openGalleryFragment(int position, ArrayList<String> listUrls);

}

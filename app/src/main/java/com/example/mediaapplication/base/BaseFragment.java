package com.example.mediaapplication.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mediaapplication.MainActivity;
import com.example.mediaapplication.R;
import com.example.mediaapplication.navigation.INavigation;
import com.example.mediaapplication.recycler.RecyclerImpl;
import com.example.mediaapplication.recycler.SwipeCallback;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView {

    protected T presenter;
    protected RecyclerView recyclerView;
    protected RecyclerImpl recyclerImpl;

    protected abstract T createPresenter();

    protected abstract int getLayoutId();

    @Override
    public INavigation getNavigation() {
        MainActivity mainActivity = (MainActivity) getActivity();
        return mainActivity.getNavigationManager();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        recyclerView = view.findViewById(R.id.recycler);
        if (recyclerView != null) {
            recyclerImpl = new RecyclerImpl(recyclerView);
        }

        return view;
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onUnbindView();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onBindView(this);
    }


}

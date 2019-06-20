package com.example.mediaapplication;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.example.mediaapplication.api.ApiManager;
import com.example.mediaapplication.api.ResponseListener;
import com.example.mediaapplication.model.Picture;
import com.example.mediaapplication.model.ServerResponse;
import com.example.mediaapplication.model.User;
import com.example.mediaapplication.recycler.RecyclerImpl;
import com.example.mediaapplication.recycler.RecyclerItem;
import com.example.mediaapplication.recycler_items.ImageItem;
import com.example.mediaapplication.ui.FullScrDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerImpl recyclerImpl;
    List<RecyclerItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler);


        recyclerImpl = new RecyclerImpl(recyclerView);

        ApiManager apiManager = new ApiManager();
        apiManager.getResults(new ResponseListener() {
            @Override
            public void successResponse(ServerResponse response) {
                List<User> users = response.getResults();
                for (int i = 0; i < users.size(); i++) {
                    Picture picture = users.get(i).getPicture();
                    list.add(new ImageItem(picture));
                }
                recyclerImpl.updateItems(list);
            }

            @Override
            public void failureResponse() {

            }
        });

        recyclerImpl.getRecyclerView().addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                int position = recyclerView.getChildAdapterPosition(childView);

                Bundle bundle = new Bundle();
                bundle.putSerializable("images", recyclerImpl.getRecyclerAdapter().getItems());
                bundle.putInt("position", position);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                FullScrDialogFragment newFragment = FullScrDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");


                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                // there is a logic

//


            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });


    }
}

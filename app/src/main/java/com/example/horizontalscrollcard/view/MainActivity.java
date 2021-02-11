package com.example.horizontalscrollcard.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.horizontalscrollcard.R;
import com.example.horizontalscrollcard.Utility.Util;
import com.example.horizontalscrollcard.adapter.PostsRecyclerViewAdapter;
import com.example.horizontalscrollcard.model.Post;
import com.example.horizontalscrollcard.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PostsRecyclerViewAdapter.OnItemCLickListener {
    private static final String TAG = "MainActivity";

    MainViewModel viewModel;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewPager = findViewById(R.id.viewPager);
        viewModel.getPostsApi();

        viewModel.getPosts().observe(this, posts -> setRecyclerView(posts));

        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });

        viewPager.setPageTransformer(compositePageTransformer);
    }

    private void setRecyclerView(List<Post> posts) {
        viewPager.setAdapter(new PostsRecyclerViewAdapter(posts, this));
    }

    @Override
    public void onPostClick(int position) {
        Log.d(TAG, "onPostClick: ");
        Util.showSnackBar(this, viewModel.getPosts().getValue().get(position).getTitle());
    }
}
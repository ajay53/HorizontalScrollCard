package com.example.horizontalscrollcard.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.horizontalscrollcard.model.Post;
import com.example.horizontalscrollcard.service.PostWebServiceClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";

    private final MutableLiveData<List<Post>> mPosts;

    public MainViewModel() {
        mPosts = new MutableLiveData<>();
    }

    public LiveData<List<Post>> getPosts() {
        return mPosts;
    }

    public void getPostsApi() {
        Log.d(TAG, "getPostsApi: ");

        Call<List<Post>> postsCall = PostWebServiceClient.postApi.getPosts();

        postsCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {

                if (!response.isSuccessful()) {
                    Log.e(TAG, "onResponse: Code" + response.code());
                }
                mPosts.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}

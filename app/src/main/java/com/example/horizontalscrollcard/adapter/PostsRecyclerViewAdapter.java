package com.example.horizontalscrollcard.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horizontalscrollcard.R;
import com.example.horizontalscrollcard.model.Post;

import java.util.List;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<PostsRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "PostsRecyclerAdapter";

    private final List<Post> posts;
    private final OnItemCLickListener onItemCLickListener;

    public PostsRecyclerViewAdapter(List<Post> posts, OnItemCLickListener onItemCLickListener) {
        this.posts = posts;
        this.onItemCLickListener = onItemCLickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_card, parent, false);
        return new ViewHolder(view, onItemCLickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");

        holder.tvId.setText(String.valueOf(posts.get(position).getId()));
        holder.tvTitle.setText(posts.get(position).getTitle());
        holder.tvBody.setText(posts.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnItemCLickListener onItemCLickListener;
        public TextView tvId;
        public TextView tvTitle;
        public TextView tvBody;

        public ViewHolder(@NonNull View itemView, OnItemCLickListener onItemCLickListener) {

            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);

            this.onItemCLickListener = onItemCLickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemCLickListener.onPostClick(getAdapterPosition());
        }
    }

    public interface OnItemCLickListener {
        void onPostClick(int position);
    }
}

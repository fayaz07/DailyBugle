package com.root.dailybugle.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.root.dailybugle.R;
import com.root.dailybugle.activities.NewsDetailActivity;
import com.root.dailybugle.models.Model;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    List<Model> list;
    Context context;

    public HomeAdapter(List<Model> list, Context context){
        this.list=list;
        this.context=context;
    }
    public void onrefresh(List<Model> list){
        this.list.clear();
        this.list =list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.smodel,parent,false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(view);
        return  homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeAdapter.HomeViewHolder holder, int position) {
        final Model model = list.get(position);
        String url = model.getImage();
        Picasso.with(context)
                .load(url)
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {
                        holder.progressBar.setVisibility(View.INVISIBLE);
                        holder.imageView.setImageResource(R.drawable.news);
                    }


                });
        holder.b2.setText(model.getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,NewsDetailActivity.class);
                intent.putExtra("image", model.getImage());
                intent.putExtra("desc", model.getDesc());
                intent.putExtra("title", model.getTitle());
                intent.putExtra("author", model.getAuthor());
                intent.putExtra("source", model.getSname());
                intent.putExtra("url", model.getUrl());
                intent.putExtra("publishedAt", model.getDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
        Button b2;
        CardView cardView;
        public HomeViewHolder(View itemView) {
            super(itemView);
                imageView=itemView.findViewById(R.id.home_imgview);
                progressBar=itemView.findViewById(R.id.progress);
                b2=itemView.findViewById(R.id.b2);
                cardView=itemView.findViewById(R.id.c1);
        }
    }
}

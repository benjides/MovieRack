package com.exfume.movierack.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.exfume.movierack.MainActivity;

import com.exfume.movierack.R;
import com.exfume.movierack.model.Movie;
import com.exfume.movierack.model.Result;



import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> implements Callback<Result> {

    private ArrayList<Movie> dataset;
    private MainActivity activity;

    public MovieAdapter(MainActivity activity){
        this.dataset = new ArrayList<>();
        this.activity = activity;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.element , parent, false));
    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, final int position) {
        final Movie movie = dataset.get(position);
        holder.itemTitle.setText(movie.title);
        holder.itemYear.setText(""+movie.getYear());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.themoviedb.org/movie/"+dataset.get(position).id+"?language=en-US"));
                activity.startActivity(intent);
            }
        });

        Glide.with(holder.itemPoster.getContext())
                .load(movie.getPoster())
                .crossFade()
                .placeholder(R.drawable.placeholder)
                .into(holder.itemPoster);


    }

    @Override
    public int getItemCount() {
        return this.dataset.size();
    }


    @Override
    public void onResponse(Call<Result> call, Response<Result> response) {
        Log.i("Adapter",response.message());
        this.dataset = response.body().results;
        notifyDataSetChanged();
        this.activity.displayList();
    }

    @Override
    public void onFailure(Call<Result> call, Throwable t) {
        this.activity.displayError(t.getMessage());
    }


     class MovieHolder extends RecyclerView.ViewHolder{

         ImageView itemPoster;
         TextView itemTitle;
         TextView itemYear;

         MovieHolder(View itemView){
            super(itemView);
            itemPoster = (ImageView) itemView.findViewById(R.id.itemPoster);
            itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            itemYear = (TextView) itemView.findViewById(R.id.itemYear);
        }

    }



}

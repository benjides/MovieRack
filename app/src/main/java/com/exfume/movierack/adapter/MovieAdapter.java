package com.exfume.movierack.adapter;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.exfume.movierack.MainActivity;

import com.exfume.movierack.R;
import com.exfume.movierack.model.MovieElement;
import com.exfume.movierack.model.Movie;
import com.exfume.movierack.webview_activity;


import java.util.ArrayList;


import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> implements Callback<ArrayList<MovieElement>>{

    private ArrayList<MovieElement> dataset;
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
        final Movie movie = dataset.get(position).movie;
        holder.itemTitle.setText(movie.title);
        holder.itemYear.setText(""+movie.year);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("http://www.imdb.com/title/"+dataset.get(position).movie.ids.imdb+"/"));

                Intent intent = new Intent(v.getContext(), webview_activity.class);
                intent.putExtra("URL", "http://www.imdb.com/title/"+dataset.get(position).movie.ids.imdb+"/");
                intent.putExtra("TITLE",dataset.get(position).movie.title);
                intent.putExtra("YEAR", dataset.get(position).movie.year);

                activity.startActivity(intent);
            }
        });


        Glide.with(holder.itemPoster.getContext())
                .load(movie.images.poster.medium)
                .crossFade()
                .placeholder(R.drawable.placeholder)
                .into(holder.itemPoster);


    }

    @Override
    public int getItemCount() {
        return this.dataset.size();
    }

    @Override
    public void onResponse(Response<ArrayList<MovieElement>> response, Retrofit retrofit) {
        this.dataset = response.body();
        notifyDataSetChanged();
        this.activity.displayList();
    }

    @Override
    public void onFailure(Throwable t) {
        this.activity.displayError(t.getMessage());
    }


    public class MovieHolder extends RecyclerView.ViewHolder{

        public ImageView itemPoster;
        public TextView itemTitle;
        public TextView itemYear;

        public MovieHolder(View itemView){
            super(itemView);
            itemPoster = (ImageView) itemView.findViewById(R.id.itemPoster);
            itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            itemYear = (TextView) itemView.findViewById(R.id.itemYear);
        }

    }



}

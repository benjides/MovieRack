package com.exfume.movierack.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.exfume.movierack.MainActivity;
import com.exfume.movierack.R;
import com.exfume.movierack.api.TraktRest;
import com.exfume.movierack.model.Show;
import com.exfume.movierack.model.ShowElement;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Benjides on 23/12/2015.
 */
public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowHolder> implements Callback<ArrayList<ShowElement>> {

    private ArrayList<ShowElement> dataset;
    private MainActivity activity;


    public ShowAdapter(MainActivity activity){
        this.dataset = new ArrayList<>();
        this.activity = activity;
    }

    @Override
    public ShowHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ShowHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.element , parent, false));

    }

    @Override
    public void onBindViewHolder(ShowAdapter.ShowHolder holder, int position) {

        Show show = dataset.get(position).show;
        holder.itemTitle.setText(show.title);
        holder.itemYear.setText(""+show.year);

        
        Glide.with(holder.itemPoster.getContext())
                .load(show.images.poster.medium)
                .crossFade()
                .placeholder(R.drawable.placeholder)
                .into(holder.itemPoster);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public void onResponse(Response<ArrayList<ShowElement>> response, Retrofit retrofit) {
        this.dataset = response.body();
        notifyDataSetChanged();
        this.activity.displayList();
    }

    @Override
    public void onFailure(Throwable t) {
        this.activity.displayError(t.getMessage());
    }


    public static class ShowHolder extends RecyclerView.ViewHolder{

        public ImageView itemPoster;
        public TextView itemTitle;
        public TextView itemYear;

        public ShowHolder(View itemView){
            super(itemView);
            itemPoster = (ImageView) itemView.findViewById(R.id.itemPoster);
            itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            itemYear = (TextView) itemView.findViewById(R.id.itemYear);
        }

    }

}

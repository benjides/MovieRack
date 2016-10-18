package com.exfume.movierack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exfume.movierack.adapter.MovieAdapter;
import com.exfume.movierack.api.tmdbRest;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TextView mErrorDisplay;
    private ProgressBar mProgressBar;
    private MovieAdapter adapter;
    private tmdbRest RestService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestService = new tmdbRest();

        GridLayoutManager LayoutManager = new GridLayoutManager(this,2);
        if(getResources().getConfiguration().orientation == 2){
            LayoutManager.setSpanCount(4);
        }

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mErrorDisplay = (TextView) findViewById(R.id.textView);
        mRecyclerView = (RecyclerView) findViewById(R.id.recylerView);
        mRecyclerView.setLayoutManager(LayoutManager);

        adapter = new MovieAdapter(this);
        RestService.popularMovies(adapter);
        mRecyclerView.setAdapter(adapter);

    }


    public void displayError(String errorText){
        mRecyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mErrorDisplay.setVisibility(View.VISIBLE);
        mErrorDisplay.setText(errorText);
    }

    public void displayList(){
        mRecyclerView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mErrorDisplay.setVisibility(View.GONE);
    }
}

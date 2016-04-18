package com.exfume.movierack;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


public class webview_activity extends AppCompatActivity {

    private WebView webView;
    private Toolbar toolbar;
    private String URL,YEAR,TITLE;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);
        Bundle extra = getIntent().getExtras();
        URL = extra.getString("URL");
        TITLE = extra.getString("TITLE");
        YEAR = extra.getString("YEAR");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle(TITLE);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        TextView Title = (TextView) findViewById(R.id.itemTitle);
        TextView Year = (TextView) findViewById(R.id.itemYear);
        Title.setText(TITLE);
        Year.setText(YEAR);
        webView.setWebViewClient(new Callback());
        webView.loadUrl(URL);
    }

    private class Callback extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }

    }
}

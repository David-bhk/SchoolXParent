package com.example.drawer.CalendarStuff;
import androidx.appcompat.app.AppCompatActivity;;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.example.drawer.R;
public class School_Calendar extends AppCompatActivity
{
    ProgressBar progressBar;
    SwipeRefreshLayout swipe;
    private WebView mywebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_calendar);
        mywebview = (WebView) findViewById(R.id.webView);
        progressBar = findViewById(R.id.lookCalendar);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        LoadWeb();
        progressBar.setMax(100);
        progressBar.setProgress(1);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mywebview.reload();
            }
        });
        mywebview.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(progress);
            }
        });
        mywebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                mywebview.loadUrl("https://muhindo-galien.github.io/calender/");
            }
            public void onLoadResource(WebView view, String url) { //Doesn't work
                //swipe.setRefreshing(true);
            }
            public void onPageFinished(WebView view, String url) {

                progressBar.setVisibility(View.GONE);
                swipe.setRefreshing(false);
            }
        });
    }
    public void LoadWeb() {
        mywebview = (WebView) findViewById(R.id.webView);
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.getSettings().setAppCacheEnabled(true);
        mywebview.loadUrl("https://muhindo-galien.github.io/calender/");
        swipe.setRefreshing(true);
    }
    @Override
    public void onBackPressed() {
        if (mywebview.canGoBack()) {
            mywebview.goBack();
        } else {
            finish();
        }
    }
}
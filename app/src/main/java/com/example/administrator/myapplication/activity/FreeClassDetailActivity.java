package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FreeClassDetailActivity extends AppCompatActivity {

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.progress_ll)
    LinearLayout progressLl;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        title.setText("免费公开课");
        getData();
        settingWebView();
    }


    private void getData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
    }

    private void settingWebView() {
        WebSettings settings = webView.getSettings();
        settings.setBuiltInZoomControls(true);// 显示缩放按钮(wap网页不支持)
        settings.setUseWideViewPort(true);// 支持双击缩放(wap网页不支持)
        settings.setJavaScriptEnabled(true);// 支持js功能
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
                                     @Override
                                     //只要重写此方法，就能在本应用中加载网页
                                     public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                         view.loadUrl(url);
                                         return true;
                                         //返回值时true的时候控制网页在WebView中去打开，如果为false调用系统浏览器或第三方浏览器
                                     }

                                     @Override
                                     public void onReceivedError(WebView view, int errorCode,
                                                                 String description, String failingUrl) {
                                         //收到错误信息的时候，系统执行此操作.
                                         //比如当出现404错误码时，我们可以自己写个html放在asset文件夹中，把webView隐藏掉而显示本地的网页。
                                     }

                                     @Override
                                     public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                         super.onPageStarted(view, url, favicon);
                                         //若想在网页开始加载时执行一些操作，重写该方法
                                     }

                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         super.onPageFinished(view, url);
                                         //若想在网页结束时执行一些操作，重写该方法
                                         progressLl.setVisibility(View.GONE);
                                     }
                                 }
        );
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}

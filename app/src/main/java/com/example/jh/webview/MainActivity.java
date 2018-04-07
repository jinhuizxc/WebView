package com.example.jh.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;

/**
 * 测试项目中的几个webview,加载html
 */
public class MainActivity extends AppCompatActivity {

    public static final String HOST_URL = "https://lingduren.org/ldjy-student/";
    public static final String HELP_CENTER = "questions/1/index";
    WebView webView;
    Toolbar mToolbar;

    AgentWeb mAgentWeb;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        mToolbar = findViewById(R.id.toolbar);
        ll = findViewById(R.id.ll);
        // 加载帮助中心的html5可以显示
//        addData();

        // 运用这个库也可以加载html——AgentWeb
        // https://github.com/Justson/AgentWeb
        addData1();
    }

    private void addData1() {

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(ll, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(HOST_URL + HELP_CENTER);

    }

    private void addData() {

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webView.canGoBack()){
                    webView.goBack();
                }else{
                    finish();
                }
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(true);
        // 测试3个网站均可以访问
//        webView.loadUrl(HOST_URL + HELP_CENTER);
//        webView.loadUrl("https://www.csdn.net/");
        webView.loadUrl("https://www.jianshu.com/");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }


}

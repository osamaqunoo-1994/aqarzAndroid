package sa.aqarz.NewAqarz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import sa.aqarz.R;

public class WebViewActivity extends AppCompatActivity {
    boolean isPageLoaded = false; // used because the url is the same for the response

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

//791064
        try {
            isPageLoaded = false; // used because the url is the same for the response

            String data = getIntent().getStringExtra("data");

            WebView wb = (WebView) findViewById(R.id.webView1);
            wb.getSettings().setJavaScriptEnabled(true);
//            wb.getSettings().setLoadWithOverviewMode(true);
//            wb.getSettings().setUseWideViewPort(true);
//            wb.getSettings().setBuiltInZoomControls(true);
//            wb.getSettings().setPluginState(WebSettings.PluginState.ON);
//            wb.getSettings().setPluginsEnabled(true);
//            wb.setWebViewClient(new HelloWebViewClient());
            wb.setWebViewClient(new WebViewClient() {
                                    @Override
                                    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {

                                        if (isPageLoaded) {
                                            // get the json


                                            System.out.println("urlurl" + url);


                                            return null;
                                        } else return super.shouldInterceptRequest(view, url);
                                    }

                                    public void onPageFinished(WebView view, String url) {
                                        isPageLoaded = true;
                                    }
                                }
            );
            wb.setWebViewClient(new WebViewClient());

            wb.loadUrl(data);


        } catch (Exception e) {

        }


    }
}
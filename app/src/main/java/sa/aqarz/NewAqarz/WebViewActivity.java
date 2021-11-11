package sa.aqarz.NewAqarz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import sa.aqarz.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        try {
            String data = getIntent().getStringExtra("data");

            WebView wb = (WebView) findViewById(R.id.webView1);
            wb.getSettings().setJavaScriptEnabled(true);
            wb.getSettings().setLoadWithOverviewMode(true);
            wb.getSettings().setUseWideViewPort(true);
            wb.getSettings().setBuiltInZoomControls(true);
            wb.getSettings().setPluginState(WebSettings.PluginState.ON);
//            wb.getSettings().setPluginsEnabled(true);
//            wb.setWebViewClient(new HelloWebViewClient());
            wb.loadUrl(data);


        } catch (Exception e) {

        }


    }
}
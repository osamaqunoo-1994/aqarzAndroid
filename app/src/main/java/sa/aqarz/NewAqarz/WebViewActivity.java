package sa.aqarz.NewAqarz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import okhttp3.OkHttpClient;
import sa.aqarz.R;

public class WebViewActivity extends AppCompatActivity {
    boolean isPageLoaded = false; // used because the url is the same for the response
    WebView wb;
    String id_token = "";
    String state = "";
    ProgressBar progress;
    private final OkHttpClient okHttpClient = new OkHttpClient.Builder().build(); // TODO: Make this a singleton for the app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        progress = findViewById(R.id.progress);
//791064
        try {
            isPageLoaded = false; // used because the url is the same for the response

            String data = getIntent().getStringExtra("data");

            wb = findViewById(R.id.webView1);
            wb.getSettings().setJavaScriptEnabled(true);
//            wb.getSettings().setLoadWithOverviewMode(true);
//            wb.getSettings().setUseWideViewPort(true);
//            wb.getSettings().setBuiltInZoomControls(true);
//            wb.getSettings().setPluginState(WebSettings.PluginState.ON);
//            wb.getSettings().setPluginsEnabled(true);
//            wb.setWebViewClient(new HelloWebViewClient());
//            wb.setWebViewClient(new WebViewClient() {
//                                    @Override
//                                    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//
//                                            // get the json
//
//
//                                            System.out.println("urlurl" + url);
//
//
//                                        return null;
//                                    }
//
//                                    public void onPageFinished(WebView view, String url) {
//                                        isPageLoaded = true;
//                                    }
//                                }
//            );
//            wb.setWebViewClient(new WebViewClient());
//
            wb.loadUrl(data);
            WebViewClient yourWebClient = new WebViewClient() {



                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    progress.setVisibility(View.GONE);
                    wb.loadUrl("javascript:HtmlViewer.showHTML" +
                            "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");


                }
            };


            wb.getSettings().setJavaScriptEnabled(true);
            wb.getSettings().setSupportZoom(true);
            wb.getSettings().setBuiltInZoomControls(true);
            wb.setWebViewClient(yourWebClient);
            wb.loadUrl(data);
            wb.addJavascriptInterface(new MyJavaScriptInterface(this), "HtmlViewer");


//            WebSettings settings = wb.getSettings();
//            settings.setJavaScriptEnabled(true);
//
//            wb.setWebViewClient(new InterceptingWebViewClient(this, wb));
//            wb.setWebChromeClient(new WebChromeClient());
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                WebView.setWebContentsDebuggingEnabled(true);
//            }
//
//
//            wb.loadUrl(data);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    class MyJavaScriptInterface {

        private final Context ctx;

        MyJavaScriptInterface(Context ctx) {
            this.ctx = ctx;
        }

        @JavascriptInterface
        public void showHTML(String html) {


            if (html.contains("<input type=\"hidden\" name=\"state\"")) {
//                Spanned htmlText = Html.fromHtml(html);
                Document doc = Jsoup.parse(html);
                Elements elements = doc.select("body").first().getAllElements();
//or only `<p>` elements
//Elements elements = doc.select("p");


//                System.out.println("سيملنينملبيبمبلوةي: "+elements.get(2).val());

                if (html.contains("type=\"hidden\" name=\"state\"")) {
//                    for (Element el : elements) {
//
//                    }
//
                    for (int i = 0; i < elements.size(); i++) {

                        if (elements.get(i).toString().contains("state")) {
                            System.out.println("elementxxx: " + elements.get(i));

                            System.out.println("OssmsmEled: " + elements.get(i).tagName("state").val());
                            state = elements.get(i).tagName("state").val() + "";

                        }
                        if (elements.get(i).toString().contains("id_token")) {
                            System.out.println("elementxxx: " + elements.get(i));

                            System.out.println("OssmsmEled: " + elements.get(i).tagName("id_token").val());

                            id_token = elements.get(i).tagName("id_token").val() + "";

                        }
                    }


                    System.out.println("state : " + state);
                    System.out.println("id_token : " + id_token);


                    Intent resultIntent = new Intent();
// TODO Add extras or a data URI to this intent as appropriate.
                    resultIntent.putExtra("state", state);
                    resultIntent.putExtra("id_token", id_token);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();


                }


            }

        }

    }

}

//<html><head>
//<meta charset="utf-8">
//<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
//<title>SSO</title>
//</head>
//<body onload="javascript:document.forms[0].submit()">
//<form method="post" action="https://apibeta.aqarz.sa/api/login/auth/callback">
//<input type="hidden" name="state" value="8lWXN5segKvWqCyFEVDrzkdw+kUL4xcOIsUNpaZo1TQ=">
//<input type="hidden" name="id_token" value="eyJhbGciOiJSUz

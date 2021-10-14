package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.LocaleList;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.RequestParams;
import com.orhanobut.hawk.Hawk;

import org.json.JSONObject;

import java.net.InetAddress;
import java.util.Locale;
import java.util.Set;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Modules.User;
import sa.aqarz.NewAqarz.MainAqarzActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.ForceUpdateAsync;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class SplashScreenActivity extends AppCompatActivity {
    IResult mResultCallback;
    View parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        parentLayout = findViewById(android.R.id.content);


        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        findViewById(R.id.snack_ly).startAnimation(myanim);

        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
        try {
            FirebaseMessaging.getInstance().subscribeToTopic("aqarz");
        } catch (Exception e) {

        }



        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        System.out.println("refreshedToken : "+refreshedToken);

        if (Settings.checkLogin()) {


            if (Settings.CheckIsAccountAqarzMan()) {
                try {
                    FirebaseMessaging.getInstance().subscribeToTopic("aqarz_provider");
                } catch (Exception e) {

                }
            } else {
                try {
                    FirebaseMessaging.getInstance().subscribeToTopic("aqarz_user");
                } catch (Exception e) {

                }
            }

        }


        if (Hawk.contains("lang")) {
            setLocale(SplashScreenActivity.this, Hawk.get("lang").toString());
//
//            Locale locale = new Locale(Hawk.get("lang").toString());
//            Locale.setDefault(locale);
//            Configuration config = new Configuration();
//            config.locale = locale;
//            getBaseContext().getResources().updateConfiguration(config,
//                    getBaseContext().getResources().getDisplayMetrics());
        } else {

            Hawk.put("lang", "ar");
            setLocale(SplashScreenActivity.this, Hawk.get("lang").toString());

//            Locale locale = new Locale(Hawk.get("lang").toString());
//            Locale.setDefault(locale);
//            Configuration config = new Configuration();
//            config.locale = locale;
//            getBaseContext().getResources().updateConfiguration(config,
//                    getBaseContext().getResources().getDisplayMetrics());


        }

//        Locale locale = new Locale(Hawk.get("lang").toString());
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        getBaseContext().getResources().updateConfiguration(config,
//                getBaseContext().getResources().getDisplayMetrics());

//        Locale.setDefault(locale);
//        Resources resources = getResources();
//        Configuration config = resources.getConfiguration();
//        config.setLocale(locale);
//        resources.updateConfiguration(config, resources.getDisplayMetrics());
//


        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, SplashScreenActivity.this);
        mVolleyService.getDataVolley("Settings", WebService.settings);

////
//        try {
//
//            JSONObject requestParams = new JSONObject();
//
//            requestParams.put("name", "test2");
//            requestParams.put("services", "21|25");
//            requestParams.put("latitude", "26");
//            requestParams.put("longitude", "46");
//
//            mVolleyService.postDataVolley_test("Settings3232", "http://estreeh.techno2030.com/api/v1/user/update", requestParams);
//
//        } catch (Exception e) {
//
//        }
//

        if (!isInternetAvailable()) {

        } else {
            Snackbar.make(parentLayout, getResources().getString(R.string.NoInternt), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getResources().getString(R.string.Reaty), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            VolleyService mVolleyService = new VolleyService(mResultCallback, SplashScreenActivity.this);

                            mVolleyService.getDataVolley("Settings", WebService.settings);

                        }
                    })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                    .show();
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


//                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
////              intent.putExtra("from", "splash");
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
//                finish();


            }
        }, 3000); // After 1 seconds
    }

    public void init_volley() {

        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(SplashScreenActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

                        if (requestType.equals("Settings")) {

                            Hawk.put("settings", data);


//                            init_volley();
//                            VolleyService mVolleyService = new VolleyService(mResultCallback, SplashScreenActivity.this);
//
//                            mVolleyService.getDataVolley("regions", WebService.regions);
//
//
//                            Hawk.put("regions", response.toString());

                            Intent intent = new Intent(SplashScreenActivity.this, MainAqarzActivity.class);
//              intent.putExtra("from", "splash");
                            startActivity(intent);
//                                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                            finish();
                        } else if (requestType.equals("regions")) {


                        }


                    } else {

                    }


                } catch (Exception e) {

                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());


                Snackbar.make(parentLayout, getResources().getString(R.string.NoInternt), Snackbar.LENGTH_INDEFINITE)
                        .setAction(getResources().getString(R.string.Reaty), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                VolleyService mVolleyService = new VolleyService(mResultCallback, SplashScreenActivity.this);

                                mVolleyService.getDataVolley("Settings", WebService.settings);

                            }
                        })
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                        .show();
                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(SplashScreenActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    // check version on play store and force update
    public void forceUpdate() {
        PackageManager packageManager = this.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String currentVersion = packageInfo.versionName;

        System.out.println("currentVersion" + currentVersion);
        new ForceUpdateAsync(currentVersion, SplashScreenActivity.this).execute();
    }

    public static void setLocale(Activity activity, String local) {
//        if (!BuildConfig.DEBUG)
//            Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(activity));
//        if (!BuildConfig.ENGLISH) {
        String languageToLoad = local; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;


        if (Build.VERSION.SDK_INT > 17) {


            System.out.println("!!!!!");
            config.setLocale(locale);
        } else {
            config.locale = locale;
            System.out.println("!!!!!x");

        }


        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            config.setLocales(new LocaleList(locale));
            System.out.println("!!!!!s");

        }

        activity.getBaseContext().getResources().updateConfiguration(config,
                activity.getBaseContext().getResources().getDisplayMetrics());

    }
}
package aqarz.revival.sa.aqarz.Settings;

import android.app.Activity;
import android.content.res.Configuration;

import com.orhanobut.hawk.Hawk;

import java.util.Locale;


public class Application extends android.app.Application {

    static Activity activity;


    static String statis_id = "";

    @Override
    public void onCreate() {
        super.onCreate();

//        Fabric.with(this, new Crashlytics());
//        // the following line is important
//        Fresco.initialize(this);
//
//
        Hawk.init(this).build();

        if (Hawk.contains("lang")) {

            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } else {

            Hawk.put("lang", LocaleUtils.getLanguage(this));

            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
    }


}

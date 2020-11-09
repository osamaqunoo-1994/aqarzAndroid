package sa.aqarz.Settings;

import android.app.Activity;
import android.content.res.Configuration;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sa.aqarz.Modules.CityModules;


public class Application extends android.app.Application {

    static Activity activity;

    public static List<CityModules> AllCity = new ArrayList<CityModules>();

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
        try {
            if (Hawk.contains("AllCity")) {


                if (!Hawk.get("AllCity").toString().equals("")) {

                    JSONArray jsonArray = new JSONArray(Hawk.get("AllCity").toString());
                    AllCity.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {


                        JsonParser parser = new JsonParser();
                        JsonElement mJson = parser.parse(jsonArray.getString(i));

                        Gson gson = new Gson();

                        CityModules Store_M = gson.fromJson(mJson, CityModules.class);
                        AllCity.add(Store_M);

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}

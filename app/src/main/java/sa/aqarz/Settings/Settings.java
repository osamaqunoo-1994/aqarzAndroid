package sa.aqarz.Settings;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.AqarzProfileActivity;
import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.DetailsAqarzManActivity;
import sa.aqarz.Activity.OprationNew.AqarzOrActivity;
import sa.aqarz.Fragment.mapsHome.MapsViewModel;
import sa.aqarz.Modules.AllCity_WithNib;
import sa.aqarz.Modules.AllRegionList;
import sa.aqarz.Modules.RegionModules;
import sa.aqarz.Modules.SettingsModules;
import sa.aqarz.Modules.User;
import sa.aqarz.R;


public class Settings {


    public static SettingsModules getSettings() {


        if (Hawk.contains("settings")) {
            JsonParser parser = new JsonParser();
            JsonElement mJson = parser.parse(Hawk.get("settings").toString());

            Gson gson = new Gson();
            SettingsModules userModules = gson.fromJson(mJson, SettingsModules.class);
            return userModules;
        } else {
            SettingsModules userModules = new SettingsModules();
            return userModules;

        }


    }


    public static List<AllCity_WithNib.data> all_city = new ArrayList<>();

    public static List<AllCity_WithNib.data> get_city_(Activity activity) {


        if (all_city.size() == 0) {
            String all_city_txt = loadJSONFromAsset(activity);


            JsonParser parser = new JsonParser();
            JsonElement mJson = parser.parse(all_city_txt);

            Gson gson = new Gson();
            AllCity_WithNib allCity_withNib = gson.fromJson(mJson, AllCity_WithNib.class);

            all_city = allCity_withNib.getData();

            return all_city;
        } else {
            return all_city;

        }


    }

    public static String loadJSONFromAsset(Activity activity) {
        String json = null;
        try {
            InputStream is = activity.getAssets().open("response_new.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static List<RegionModules> getRegions() {


        return Settings.getSettings().getRegine();
//        if (Hawk.contains("regions")) {
//            try {
//                JsonParser parser = new JsonParser();
//                JsonElement mJson = parser.parse(Hawk.get("regions").toString());
//                Gson gson = new Gson();
//                AllRegionList All = gson.fromJson(mJson, AllRegionList.class);
//                return All.getData();
//            } catch (Exception e) {
//                List<RegionModules> regionModules = new ArrayList<>();
//                return regionModules;
//            }
//        } else {
//            List<RegionModules> regionModules = new ArrayList<>();
//            return regionModules;
//
//        }


    }

    //
    static LatLng my_location;

    public static LatLng getLocation(Activity activity) {

        try {
            SingleShotLocationProvider.requestSingleUpdate(activity,
                    new SingleShotLocationProvider.LocationCallback() {
                        @Override
                        public void onNewLocationAvailable(SingleShotLocationProvider.GPSCoordinates location) {
                            Log.d("Location", "my location is " + location.toString());
                            my_location = new LatLng(location.latitude, location.longitude);


                        }
                    });
        } catch (Exception location) {
            my_location = new LatLng(24.768516, 46.691505);


        }


        return my_location;


    }

    public static User GetUser() {

        try {

            JsonParser parser = new JsonParser();
            JsonElement mJson = parser.parse(Hawk.get("user").toString());

            Gson gson = new Gson();
            User userModules = gson.fromJson(mJson, User.class);
            return userModules;
        } catch (Exception e) {
            Hawk.put("user", "");
            return null;

        }


    }

    public static boolean CheckIsCompleate() {
        try {

            return Settings.GetUser().getName() != null || Settings.GetUser().getEmail() != null;

        } catch (Exception e) {
            Hawk.put("user", "");

            return false;

        }


    }

    public static boolean CheckIsAccountAqarzMan() {


        try {
            if (Settings.checkLogin()) {
                return Settings.GetUser().getType().equals("provider");
            } else {
                return false;

            }
        } catch (Exception e) {
            Hawk.put("user", "");


            return false;

        }


    }

    public static boolean checkLogin() {


        try {
            if (Hawk.contains("user")) {

                return !Hawk.get("user").toString().equals("");
            } else {
                return false;
            }
        } catch (Exception e) {
            Hawk.put("user", "");

            return false;

        }


    }

    public static void Dialog_not_compleate(Activity activity) {
        new AlertDialog.Builder(activity)
                .setMessage(activity.getResources().getString(R.string.you_are_not_incompleat))
                .setCancelable(false)
                .setPositiveButton(activity.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (Settings.CheckIsAccountAqarzMan()) {

                            Intent intent = new Intent(activity, AqarzProfileActivity.class);
//              intent.putExtra("from", "splash");
                            activity.startActivity(intent);

                        } else {
                            Intent intent = new Intent(activity, MyProfileInformationActivity.class);
//              intent.putExtra("from", "splash");
                            activity.startActivity(intent);
                        }


                    }
                })
                .setNegativeButton(activity.getResources().getString(R.string.no), null)
                .show();

    }

}

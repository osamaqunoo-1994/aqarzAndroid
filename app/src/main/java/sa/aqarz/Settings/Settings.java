package sa.aqarz.Settings;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import sa.aqarz.Activity.AqarzProfileActivity;
import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.DetailsAqarzManActivity;
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

//

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

            if (Settings.GetUser().getName() == null && Settings.GetUser().getEmail() == null) {
                return false;

            } else {
                return true;

            }

        } catch (Exception e) {
            Hawk.put("user", "");

            return false;

        }


    }

    public static boolean CheckIsAccountAqarzMan() {


        try {
            if (Settings.checkLogin()) {
                if (Settings.GetUser().getType().toString().equals("provider")) {
                    return true;

                } else {
                    return false;

                }
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

                if (Hawk.get("user").toString().equals("")) {

                    return false;
                } else {

                    return true;
                }
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

package aqarz.revival.sa.aqarz.Settings;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import aqarz.revival.sa.aqarz.Activity.Auth.LoginActivity;
import aqarz.revival.sa.aqarz.Modules.SettingsModules;
import aqarz.revival.sa.aqarz.Modules.User;
import aqarz.revival.sa.aqarz.R;


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

        JsonParser parser = new JsonParser();
        JsonElement mJson = parser.parse(Hawk.get("user").toString());

        Gson gson = new Gson();
        User userModules = gson.fromJson(mJson, User.class);

        return userModules;
    }

    public static boolean CheckIsCompleate() {


        if (Settings.GetUser().getName() == null) {
            return false;

        } else {
            return true;

        }


    }

    public static boolean CheckIsAccountAqarzMan() {


        if (Settings.checkLogin()) {
            if (Settings.GetUser().getType().toString().equals("provider")) {
                return true;

            } else {
                return false;

            }
        } else {
            return false;

        }


    }

    public static boolean checkLogin() {


        if (Hawk.contains("user")) {

            if (Hawk.get("user").toString().equals("")) {

                return false;
            } else {

                return true;
            }
        } else {
            return false;
        }


    }

}

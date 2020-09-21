package aqarz.revival.sa.aqarz.Settings;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import aqarz.revival.sa.aqarz.Modules.SettingsModules;
import aqarz.revival.sa.aqarz.Modules.User;


public class Settings {


    public static SettingsModules getSettings() {

        JsonParser parser = new JsonParser();
        JsonElement mJson = parser.parse(Hawk.get("settings").toString());

        Gson gson = new Gson();
        SettingsModules userModules = gson.fromJson(mJson, SettingsModules.class);

        return userModules;
    }

//

    public static User GetUser() {

        JsonParser parser = new JsonParser();
        JsonElement mJson = parser.parse(Hawk.get("user").toString());

        Gson gson = new Gson();
        User userModules = gson.fromJson(mJson, User.class);

        return userModules;
    }


}

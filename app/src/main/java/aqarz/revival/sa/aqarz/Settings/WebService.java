package aqarz.revival.sa.aqarz.Settings;

import android.app.Activity;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.loopj.android.http.AsyncHttpClient;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import aqarz.revival.sa.aqarz.R;
import es.dmoral.toasty.Toasty;


public class WebService {


    //Hakeem_jks.jks
    //Hakeem_jks
    //123456


    public static KProgressHUD kProgressHUD;
//    public static String Domain = "http://aqarz.heliohost.org/api/";
    public static String Domain = "https://aqarz.com/api/";


    public static String login = Domain + "login";
    public static String register = Domain + "register";
    public static String mobile_verify = Domain + "mobile/verify";
    public static String forget_password = Domain + "forget/password";
    public static String update_password = Domain + "update/password";
    public static String update_my_profile = Domain + "update/my/profile";
    public static String settings = Domain + "settings";
    public static String profile_image = Domain + "profile/image";
    public static String operation_type = Domain + "operation/type";
    public static String cities = Domain + "cities/1/list";
    public static String banks = Domain + "banks";


    public static void Header_Async(AsyncHttpClient client, boolean is_token) {

        client.addHeader("Accept", "application/json");
        client.addHeader("Auth-Role", "user");
        client.addHeader("Accept-Language", Hawk.get("lang").toString());
        if (is_token) {
            if (Hawk.contains("api_token")) {
                if (!Hawk.get("api_token").toString().equals("")) {
                    client.addHeader("Authorization", Hawk.get("api_token").toString());
//                    System.out.println("Authorization " + " Bearer " + Settings.GetUser().getAccessToken());
                }
            }

        }
    }


    public static Map<String, String> setHeaderVolley() {

        Map<String, String> heder = new HashMap<String, String>();
        heder.put("Accept", "application/json");
        heder.put("Accept-Language", Hawk.get("lang").toString());
        if (Hawk.contains("api_token")) {
            if (!Hawk.get("api_token").toString().equals("")) {
                heder.put("auth", Hawk.get("api_token").toString());
                System.out.println("auth " + "  " + Hawk.get("api_token").toString());
            }
        }
        return heder;
    }

    public static Map<String, String> setHeaderVolley_without_token() {

        Map<String, String> heder = new HashMap<String, String>();
        heder.put("Accept", "application/json");
        heder.put("Accept-Language", Hawk.get("lang").toString());
        if (Hawk.contains("api_token")) {
            if (!Hawk.get("api_token").toString().equals("")) {
//                heder.put("auth", Hawk.get("api_token").toString());
                System.out.println("auth " + "  " + Hawk.get("api_token").toString());
            }
        }
        return heder;
    }


    public static void loading(Activity activity, boolean stopOrstart) {

        if (stopOrstart) {
            kProgressHUD = KProgressHUD.create(activity)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setBackgroundColor(activity.getResources().getColor(R.color.colorPrimary))
                    .setDimAmount(0.5f)
                    .show();
        } else {
            if (kProgressHUD != null) {
                kProgressHUD.dismiss();

            }
        }

    }

    public static void Make_Toast(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    public static void Make_Toast_color(Activity activity, String message, String type) {
        if (type.equals("success")) {
            Toasty.success(activity, message, Toast.LENGTH_SHORT, true).show();

        } else {
            Toasty.error(activity, message, Toast.LENGTH_SHORT, true).show();

        }

    }


}

package aqarz.revival.sa.aqarz.Settings;

import android.app.Activity;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.loopj.android.http.AsyncHttpClient;
import com.orhanobut.hawk.Hawk;

import aqarz.revival.sa.aqarz.R;
import es.dmoral.toasty.Toasty;


public class WebService {


    //Hakeem_jks.jks
    //Hakeem_jks
    //123456


    public static KProgressHUD kProgressHUD;
    public static String Domain = "http://aqarz.heliohost.org/api/";


    public static String login = Domain + "login";
    public static String register = Domain + "register";
    public static String mobile_verify = Domain + "mobile/verify";


    public static void Header_Async(AsyncHttpClient client, boolean is_token) {

        client.addHeader("Accept", "application/json");
        client.addHeader("Auth-Role", "user");
        client.addHeader("Accept-Language", Hawk.get("lang").toString());
        if (is_token) {
            if (Hawk.contains("user")) {
                if (!Hawk.get("user").toString().equals("")) {
//                    client.addHeader("Authorization", "Bearer " + Settings.GetUser().getAccessToken());
//                    System.out.println("Authorization " + " Bearer " + Settings.GetUser().getAccessToken());
                }
            }

        }
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

package sa.aqarz.Settings;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.loopj.android.http.AsyncHttpClient;
import com.orhanobut.hawk.Hawk;
import com.tapadoo.alerter.Alerter;

import java.util.HashMap;
import java.util.Map;

import sa.aqarz.Activity.MainActivity;
import sa.aqarz.R;


public class WebService {


    public static KProgressHUD kProgressHUD;
    //    public static String Domain = "http://aqarz.heliohost.org/api/";
    public static String Domain = "https://aqarz.sa/api/";
    //    public static String Domain = "https://apibeta.aqarz.sa/api/";
    static String v_code = "v3";

    public static String login = Domain + "login";
    public static String register = Domain + "register";
    public static String mobile_verify = Domain + "mobile/verify";
    public static String mobile_verify_code = Domain + "confirm/password/code";
    public static String forget_password = Domain + "forget/password";
    public static String forget_password1 = Domain + "forget/password";
    public static String update_password = Domain + "update/password";
    public static String update_my_profile = Domain + "update/my/profile";
    public static String settings = Domain + "settings";
    public static String profile_image = Domain + "profile/image";
    public static String operation_type = Domain + "operation/type";
    public static String cities = Domain + "cities";
    public static String cities_page = Domain + "cities_page";
    public static String banks = Domain + "banks";
    public static String deferredInstallment = Domain + "deferredInstallment/Request";
    public static String finance = Domain + "finance/Request";
    public static String comfort = Domain + "comfort";
    public static String deleteImg_estate = Domain + "deleteImg/estate";
    public static String addImg = Domain + "addImg/estate";
    public static String addImg_planned = Domain + "addImg/planned";
    public static String addestate = Domain + "add/estate";
    public static String update = Domain + "update";
    public static String Home = Domain + "home/estate";
    public static String Home_3 = Domain + "home";
    public static String Home_4 = Domain + "home/estate";
    public static String Home_2 = Domain + "home/estate/list";
    public static String Home_1 = Domain + "home/list";
    public static String regions = Domain + "regions";
    public static String smilier = Domain + "smilier";
    public static String fund_Request = Domain + "fund/Request";
    public static String rate_Request = Domain + "rate/Request";
    public static String rate_offer = Domain + "fund/offer";
    public static String estate_Request = Domain + "estate/Request";
    public static String single_estat = Domain + "single/";
    public static String rate = Domain + "rate/";
    public static String my_estate = Domain + "my/estate";
    public static String hide = Domain + "hide";
    public static String user_estate = Domain + "user";
    public static String send_offer_fund_Request = Domain + "send/offer/fund/Request";
    public static String send_customer_offer_status = Domain + "send/customer/offer/status";
    public static String reject_fund_offer = Domain + "reject/fund/offer";
    public static String provider_code_send = Domain + "provider/code/send";
    public static String rate_estate = Domain + "rate/estate";
    public static String verify = Domain + "verify";
    public static String verifyNew = Domain + "verifyNew";
    public static String reset_password = Domain + "reset/password";
    public static String upgrade = Domain + "upgrade";
    public static String my_request = Domain + "my/request";
    public static String my_finance = Domain + "my/finance";
    public static String my_deferredInstallment = Domain + "my/deferredInstallment";
    public static String my_rate = Domain + "my/rate";
    public static String contact_us = Domain + "contact/us";
    public static String neighborhoods = Domain + "neighborhoods";
    public static String market_demands = Domain + "market/demands";
    public static String favorite = Domain + "add/delete/favorite";
    public static String my_favorite = Domain + "my/favorite";
    public static String my_fund_request_offer = Domain + "my/fund/request/offer";
    public static String add_client = Domain + "add/client";
    public static String my_client = Domain + "my/client";
    public static String my_msg = Domain + "my/msg";
    public static String msg = Domain + "msg";
    public static String my_profile = Domain + "my/profile";
    public static String customeroffer_Request = Domain + "customer/my/offer/Request";
    public static String user = Domain + "user/";
    public static String send_msg = Domain + "send/msg";
    public static String MYOfferOrder = Domain + "my/fund/request/offer";
    public static String best_provider = Domain + "best/provider";
    public static String count_call = Domain + "count";
    public static String delete = Domain + "delete";
    public static String make_up = Domain + "make/up";
    public static String updateDeviceToken = Domain + "updateDeviceToken";
    public static String notification = Domain + "notification";
    public static String cancel = Domain + "cancel";
    public static String canczdssdsel = Domain + "cancel";
    public static String dsd = Domain + "cancel";
    public static String my_employee = Domain + "my/employee";
    public static String delete_employee = Domain + "delete/employee";
    public static String add_employee = Domain + "add/employee";
    public static String report = Domain + "report";
    public static String cities_with_neb = Domain + "cities/with/neb";
    public static String estate_price_range = Domain + "estate/price/range";
    public static String estate_area_range = Domain + "estate/area/range";
    public static String home_estate_custom_list = Domain + "home/estate/custom/list";
    public static String title_global_cities = Domain + "title/global/cities";
    public static String remove_neb_interest = Domain + "remove/neb/interest";
    public static String title_gloable = Domain + "title/global/";
    public static String my_interest = Domain + "my/interest";
    public static String add_neb_interest = Domain + "add/neb/interest";
    public static String check_employe = Domain + "check/employee";
    public static String check_location_data = Domain + "check/location/data";
//    public static String provider_code_send = Domain + "provider/code/send";


    public static void Header_Async(AsyncHttpClient client, boolean is_token) {

        client.addHeader("Accept", "application/json");
        client.addHeader("Auth-Role", "user");
        client.addHeader("Accept-Language", Hawk.get("lang").toString());
        client.addHeader("v", v_code);
        System.out.println("app_v" + Application.getversionName());
        client.addHeader("app_v", Application.getversionName());


        if (is_token) {
            if (Hawk.contains("api_token")) {
                if (!Hawk.get("api_token").toString().equals("")) {
                    client.addHeader("auth", Hawk.get("api_token").toString());
//                    System.out.println("Authorization " + " Bearer " + Settings.GetUser().getAccessToken());
                }
            }

        }
    }


    public static Map<String, String> setHeaderVolley() {

        Map<String, String> heder = new HashMap<String, String>();
        heder.put("Accept", "application/json");
        heder.put("Accept-Language", Hawk.get("lang").toString());
        heder.put("v", v_code);
        System.out.println("app_v" + Application.getversionName());
        heder.put("app_v", Application.getversionName());

        if (Hawk.contains("api_token")) {
            if (!Hawk.get("api_token").toString().equals("")) {//Hawk.get("api_token").toString()
                heder.put("auth", Hawk.get("api_token").toString());

//                heder.put("auth", "token 3a5a51601b69bd31c6d825aa4e664faad79fa6715406dbc123274736551762c684ae994f3072acf6b1b02a2bc3f72ee799576b6adf2a84b9f2af2799dff0a2b4");//
                System.out.println("auth " + "  " + Hawk.get("api_token").toString());
            }
        }
        return heder;
    }

    public static Map<String, String> setHeaderVolley_without_token() {

        Map<String, String> heder = new HashMap<String, String>();
        heder.put("Accept", "application/json");
        heder.put("Accept-Language", Hawk.get("lang").toString());
        heder.put("v", v_code);
        System.out.println("app_v" + Application.getversionName());
        heder.put("app_v", Application.getversionName());

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
            if (kProgressHUD != null) {
                kProgressHUD.dismiss();

            }
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

            Alerter.create(activity)
                    .setText(message)
                    .setBackgroundColorRes(R.color.cookie_success)
                    .enableInfiniteDuration(false)
                    .setDuration(1500)

                    .enableVibration(true)
                    .setExitAnimation(R.anim.alerter_slide_out_to_top)
//                    .addButton(activity.getString(R.string.ok), R.style.ExampleButton_ok, v -> {
//                        try {
//                            setWifiEnabled(activity,true);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        Alerter.hide(); })
//
//                    .addButton(activity.getString(R.string.no_cancel),R.style.ExampleButton_cancel, v ->
//                            Alerter.hide())
                    .show();

//            Toasty.success(activity, message, Toast.LENGTH_SHORT, true).show();

        } else {


            try {


                if (message == null) {
                    message = "هنالك خطا في الاتصال بالانترنت";
                }

                Alerter.create(activity)
                        .setText(message)
                        .setBackgroundColorRes(R.color.error)
                        .enableInfiniteDuration(false)
                        .setDuration(1000)

                        .enableVibration(true)
                        .setExitAnimation(R.anim.alerter_slide_out_to_top)
//                    .addButton(activity.getString(R.string.ok), R.style.ExampleButton_ok, v -> {
//                        try {
//                            setWifiEnabled(activity,true);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        Alerter.hide(); })
//
//                    .addButton(activity.getString(R.string.no_cancel),R.style.ExampleButton_cancel, v ->
//                            Alerter.hide())
                        .show();


            } catch (Exception e) {

            }


//            Toasty.error(activity, message, Toast.LENGTH_SHORT, true).show();

        }

    }

    public static void Make_Toast_color_info(Activity activity, String message, String type) {

        Alerter.create(activity)
                .setText(message)
                .setIcon(R.drawable.ic_danger)
                .setBackgroundColorRes(R.color.yalow)
                .enableInfiniteDuration(false)
                .setDuration(1500)

                .enableVibration(true)
                .setExitAnimation(R.anim.alerter_slide_out_to_top)

                .show();


    }

}

package sa.aqarz.Activity.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sa.aqarz.Activity.AqarzProfileActivity;
import sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat_new;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat_other;
import sa.aqarz.Adapter.RecyclerView_List_estat_profile;
import sa.aqarz.Adapter.RecyclerView_List_estat_profile_other;
import sa.aqarz.Adapter.RecyclerView_clints_new;
import sa.aqarz.Adapter.RecyclerView_my_offer_in_profile;
import sa.aqarz.Adapter.RecyclerView_other_offer_in_profile;
import sa.aqarz.Modules.Clints;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_HomeList_estat_new_my;
import sa.aqarz.NewAqarz.AqqAqarz.AddAqarzStepsActivity;
import sa.aqarz.NewAqarz.MainAqarzActivity;
import sa.aqarz.NewAqarz.WebViewActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class MyOffersActivity extends AppCompatActivity {
    RecyclerView myoffer;
    IResult mResultCallback;
    List<HomeModules_aqares> homeModules = new ArrayList<>();
    ImageView back;
    LinearLayout nodata_vis;
    LinearLayout add_offer;
    //    RecyclerView_my_offer_in_profile recyclerView_my_offer_in_profile;
    RecyclerView_HomeList_estat_other recyclerView_homeList_estat_other;
    static RecyclerView_HomeList_estat_new_my recyclerView_homeList_estat_new;
    AlertDialog alertDialog;

    int page = 1;

    EditText edt_search;
    ImageView search;
    ImageView close;
    String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offers);
        myoffer = findViewById(R.id.myoffer);
        back = findViewById(R.id.back);
        nodata_vis = findViewById(R.id.nodata_vis);
        add_offer = findViewById(R.id.add_offer);
        edt_search = findViewById(R.id.edt_search);
        search = findViewById(R.id.search);
        close = findViewById(R.id.close);


        WebService.loading(MyOffersActivity.this, true);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(MyOffersActivity.this, LinearLayoutManager.VERTICAL, false);
        myoffer.setLayoutManager(layoutManager1);


        recyclerView_homeList_estat_new = new RecyclerView_HomeList_estat_new_my(MyOffersActivity.this, homeModules);

        myoffer.setAdapter(recyclerView_homeList_estat_new);


        init_volley();

        try {
            id_user = getIntent().getStringExtra("id_user");

            if (id_user.equals("--")) {

                VolleyService mVolleyService = new VolleyService(mResultCallback, MyOffersActivity.this);
                WebService.loading(MyOffersActivity.this, true);

                mVolleyService.getDataVolley("my_estate", WebService.my_estate);
                add_offer.setVisibility(View.VISIBLE);

                myoffer.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if (!recyclerView.canScrollVertically(1)) { //1 for down

                            page = page + 1;
                            init_volley();
                            WebService.loading(MyOffersActivity.this, true);

                            VolleyService mVolleyService = new VolleyService(mResultCallback, MyOffersActivity.this);
                            mVolleyService.getDataVolley("my_estate", WebService.my_estate + "?page=" + page);

                        }
                    }
                });

            } else {

                recyclerView_homeList_estat_other = new RecyclerView_HomeList_estat_other(MyOffersActivity.this, homeModules);

                myoffer.setAdapter(recyclerView_homeList_estat_other);
                WebService.loading(MyOffersActivity.this, true);


                init_volley();
                VolleyService mVolleyService = new VolleyService(mResultCallback, MyOffersActivity.this);

                mVolleyService.getDataVolley("user_estate", WebService.user_estate + "/" + id_user + "/estate");
                add_offer.setVisibility(View.GONE);
            }

        } catch (Exception e) {

        }
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (edt_search.getText().length() > 0) {
                    close.setVisibility(View.VISIBLE);

                } else {
                    close.setVisibility(View.GONE);

                }


            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    edt_search.setText("");
                    edt_search.setVisibility(View.GONE);
                    page = 1;
                    id_user = getIntent().getStringExtra("id_user");

                    if (id_user.equals("--")) {

                        VolleyService mVolleyService = new VolleyService(mResultCallback, MyOffersActivity.this);

                        mVolleyService.getDataVolley("my_estate", WebService.my_estate);
                        add_offer.setVisibility(View.VISIBLE);
                        WebService.loading(MyOffersActivity.this, true);

                        myoffer.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);
                                if (!recyclerView.canScrollVertically(1)) { //1 for down

                                    page = page + 1;
                                    init_volley();
                                    WebService.loading(MyOffersActivity.this, true);

                                    VolleyService mVolleyService = new VolleyService(mResultCallback, MyOffersActivity.this);
                                    mVolleyService.getDataVolley("my_estate", WebService.my_estate + "?page=" + page);

                                }
                            }
                        });

                    } else {

                        recyclerView_homeList_estat_other = new RecyclerView_HomeList_estat_other(MyOffersActivity.this, homeModules);

                        myoffer.setAdapter(recyclerView_homeList_estat_other);

                        WebService.loading(MyOffersActivity.this, true);

                        init_volley();
                        VolleyService mVolleyService = new VolleyService(mResultCallback, MyOffersActivity.this);

                        mVolleyService.getDataVolley("user_estate", WebService.user_estate + "/" + id_user + "/estate");
                        add_offer.setVisibility(View.GONE);
                    }

                } catch (Exception e) {

                }
            }
        });
        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    page = 1;
                    id_user = getIntent().getStringExtra("id_user");

                    if (id_user.equals("--")) {

                        VolleyService mVolleyService = new VolleyService(mResultCallback, MyOffersActivity.this);
                        WebService.loading(MyOffersActivity.this, true);

                        mVolleyService.getDataVolley("my_estate", WebService.my_estate + "?&search=" + edt_search.getText().toString());

                        myoffer.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);
                                if (!recyclerView.canScrollVertically(1)) { //1 for down
//
//                                page = page + 1;
//                                init_volley();
//                                WebService.loading(MyOffersActivity.this, true);
//
//                                VolleyService mVolleyService = new VolleyService(mResultCallback, MyOffersActivity.this);
//                                mVolleyService.getDataVolley("my_estate", WebService.my_estate + "?page=" + page+"&search="+edt_search.getText().toString());

                                }
                            }
                        });

                    } else {

                        init_volley();
                        VolleyService mVolleyService = new VolleyService(mResultCallback, MyOffersActivity.this);
                        WebService.loading(MyOffersActivity.this, true);

                        mVolleyService.getDataVolley("user_estate", WebService.user_estate + "/" + id_user + "/estate" + "?&search=" + edt_search.getText().toString());
                        add_offer.setVisibility(View.GONE);
                    }


                    return true;
                }
                return false;
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;
                id_user = getIntent().getStringExtra("id_user");

                if (id_user.equals("--")) {

                    VolleyService mVolleyService = new VolleyService(mResultCallback, MyOffersActivity.this);

                    mVolleyService.getDataVolley("my_estate", WebService.my_estate + "?&search=" + edt_search.getText().toString());

                    myoffer.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            if (!recyclerView.canScrollVertically(1)) { //1 for down
//
//                                page = page + 1;
//                                init_volley();
//                                WebService.loading(MyOffersActivity.this, true);
//
//                                VolleyService mVolleyService = new VolleyService(mResultCallback, MyOffersActivity.this);
//                                mVolleyService.getDataVolley("my_estate", WebService.my_estate + "?page=" + page+"&search="+edt_search.getText().toString());

                            }
                        }
                    });

                } else {

                    init_volley();
                    VolleyService mVolleyService = new VolleyService(mResultCallback, MyOffersActivity.this);

                    mVolleyService.getDataVolley("user_estate", WebService.user_estate + "/" + id_user + "/estate" + "?&search=" + edt_search.getText().toString());
                    add_offer.setVisibility(View.GONE);
                }

            }
        });

        add_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Settings.GetUser().getIs_iam_complete().equals("0")) {


                    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View popupView = layoutInflater.inflate(R.layout.alert_is_iam_compleate, null);

                    TextView ok = popupView.findViewById(R.id.ok);
                    TextView cancle = popupView.findViewById(R.id.cancle);


                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            init_volley();
                            WebService.loading(MyOffersActivity.this, true);


                            VolleyService mVolleyService = new VolleyService(mResultCallback, MyOffersActivity.this);
                            mVolleyService.getDataVolley("IAM_login", WebService.IAM_login);


                            alertDialog.dismiss();
                        }
                    });
                    cancle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MyOffersActivity.this);

                    builder.setView(popupView);


                    alertDialog = builder.show();

                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                } else {
                    Intent intent = new Intent(MyOffersActivity.this, AddAqarzStepsActivity.class);
                    intent.putExtra("id", "");
                    startActivity(intent);

                }
//                Intent intent = new Intent(MyOffersActivity.this, AddAqarsActivity.class);
//                Intent intent = new Intent(MyOffersActivity.this, AddAqarzStepsActivity.class);
////              intent.putExtra("from", "splash");
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(MyOffersActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String message = response.getString("message");
                        if (requestType.equals("my_estate")) {
                            String data = response.getString("data");
                            JSONObject jsonObjectdata = new JSONObject(data);
//
                            String datax = jsonObjectdata.getString("data");

                            JSONArray jsonArray = new JSONArray(datax);


                            if (page == 1) {
                                homeModules.clear();

                            }

                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    HomeModules_aqares bankModules = gson.fromJson(mJson, HomeModules_aqares.class);
                                    homeModules.add(bankModules);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            recyclerView_homeList_estat_new.Refr();

//                            myoffer.setAdapter(new RecyclerView_my_offer_in_profile(MyOffersActivity.this, homeModules));
                            if (homeModules.size() == 0) {
                                nodata_vis.setVisibility(View.VISIBLE);
                            } else {
                                nodata_vis.setVisibility(View.GONE);

                            }

                        } else if (requestType.equals("user_estate")) {
                            String data = response.getString("data");
//                        JSONObject jsonObjectdata = new JSONObject(data);
//
//                        String datax = jsonObjectdata.getString("data");

                            JSONArray jsonArray = new JSONArray(data);


                            if (page == 1) {
                                homeModules.clear();
//                                myoffer.setAdapter(null);
                            }


                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    HomeModules_aqares bankModules = gson.fromJson(mJson, HomeModules_aqares.class);
                                    homeModules.add(bankModules);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            recyclerView_homeList_estat_other.Refr();


                            if (homeModules.size() == 0) {
                                nodata_vis.setVisibility(View.VISIBLE);
                            } else {
                                nodata_vis.setVisibility(View.GONE);

                            }

                        } else if (requestType.equals("IAM_login")) {
                            String data = response.getString("data");

                            Intent intent = new Intent(MyOffersActivity.this, WebViewActivity.class);
                            intent.putExtra("data", data);
                            startActivityForResult(intent, 115);


                        } else {


//                            AlertDialog alertDialog;
//
//
//                            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                            final View popupView = layoutInflater.inflate(R.layout.upgrade_message, null);
//
//
//                            final AlertDialog.Builder builder = new AlertDialog.Builder(DetailsAqarzManActivity.this);
//
////            alertDialog_country =
//                            builder.setView(popupView);
//
//
//                            alertDialog = builder.show();
//
//                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        }
                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(MyOffersActivity.this, message, "error");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(MyOffersActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(MyOffersActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(MyOffersActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(MyOffersActivity.this, false);

                WebService.Make_Toast_color(MyOffersActivity.this, error, "error");


            }
        };


    }


    @Override
    protected void onResume() {

        setLocale(MyOffersActivity.this, Hawk.get("lang").toString());

        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if ((requestCode == 115)) {
            if (resultCode == Activity.RESULT_OK) {

                setLocale(MyOffersActivity.this, Hawk.get("lang").toString());

                try {
                    String url_callback = data.getStringExtra("url_callback");
//                    String id_token = data.getStringExtra("id_token");

                    System.out.println("url_callback" + url_callback);
                    if (url_callback != null) {

                        if (!url_callback.equals("null")) {


                            String[] separated = url_callback.split("/");
//https://apibeta.aqarz.sa/api/login/auth/2918/callback
                            String number = separated[6]; // this will contain " they taste good"

                            System.out.println("$$$$$$$$$$$$$" + number);


                            WebService.loading(MyOffersActivity.this, true);
                            VolleyService mVolleyService = new VolleyService(mResultCallback, MyOffersActivity.this);
//                            JSONObject sendObj = new JSONObject();

                            try {
                                mVolleyService.postDataVolley_without_token("user__", WebService.user + number + "", null);


//                                sendObj.put("mobile", mobile);
////                                sendObj.put("state", state);
//
//
////                                sendObj.put("identity", identity.getText().toString());
////
////                                sendObj.put("type", type_man + "");
//////                        sendObj.put("password_confirmation", Cpassword.getText().toString());
////                                sendObj.put("country_code", "+966");
//
////                        sendObj.put("device_token", "157");
////                        sendObj.put("type", type);
////                        sendObj.put("country_code", "+972");
////                        sendObj.put("device_type", "android");
//
//                                System.out.println(sendObj.toString());
//                                mVolleyService.postDataVolley_without_token("login_auth_callback", WebService.login_auth_callback, sendObj);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }


                    }


                } catch (Exception e) {

                }
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
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
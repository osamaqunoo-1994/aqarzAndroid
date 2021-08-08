package sa.aqarz.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.zxing.Result;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import io.sentry.Sentry;
import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import sa.aqarz.Activity.OprationNew.AqarzOrActivity;
import sa.aqarz.Activity.OprationNew.FinanceActivity;
import sa.aqarz.Activity.OprationNew.RentActivity;
import sa.aqarz.Activity.OprationNew.RentShowActivity;
import sa.aqarz.Activity.profile.AllclintActivity;
import sa.aqarz.Activity.profile.MyProfile;
import sa.aqarz.Activity.profile.MyProfileActivity;
import sa.aqarz.Activity.profile.OtherProfileActivity;
import sa.aqarz.Activity.profile.ProfileDetailsActivity;
import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import sa.aqarz.Adapter.RecyclerView_bottomSheet_type;
import sa.aqarz.Adapter.RecyclerView_city_bootom_sheets;
import sa.aqarz.Adapter.RecyclerView_city_bootom_sheets_multi;
import sa.aqarz.Adapter.RecyclerView_city_side_menu;
import sa.aqarz.Dialog.BottomSheetDialogFragment_Filtter;
import sa.aqarz.Dialog.BottomSheetDialogFragment_MyEstate;
import sa.aqarz.Dialog.BottomSheetDialogFragment_Service;
import sa.aqarz.Fragment.ChatFragment;
import sa.aqarz.Fragment.MapsFragment;
import sa.aqarz.Fragment.mapsHome.MapsFragmentNew;
import sa.aqarz.Fragment.MoreFragment;
import sa.aqarz.Fragment.NotficationFragment;
import sa.aqarz.Fragment.OrdersFragment;
import sa.aqarz.Fragment.ServiceFragment;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.HomeModules;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.Modules.select_typeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Application;
import sa.aqarz.Settings.ForceUpdateAsync;
import sa.aqarz.Settings.LocaleUtils;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    private Menu menu;
    private static FragmentTransaction fragmentTransaction;
    private static FragmentManager fragmentManager;
    public static Activity activity;
    public static BottomSheetDialogFragment_MyEstate bottomSheetDialogFragment_myEstate;
    static BottomSheetDialog bottomSheerDialog;

    public static OrdersModules ordersModules;
    public static demandsModules demandsModules;
    static ImageView image_1;
    static ImageView image_s;
    static ImageView image_2;
    static ImageView image_3;
    static ImageView image_4;
    static TextView text_1;
    static TextView text_2;
    static TextView text_3;
    static TextView text_4;
    static TextView text_s;
    IResult mResultCallback;
    AlertDialog alertDialog;
    List<CityModules> cityModules_list = new ArrayList<>();
    List<CityModules> cityModules_list_selected = new ArrayList<>();
    List<CityModules> cityModules_list_filtter = new ArrayList<>();
    LinearLayout service_layout;
    LinearLayout gray_layout;

    TextView nodata;
    String te = "";
    public static LinearLayout lay_1, lay_2, lay_3, lay_4, lay_s;
    ShowcaseView showCaseView;

    FloatingActionButton myFab;
    public static FragmentManager ft;

    TextView add_aqar;
    TextView add_service_aqarez;


    LinearLayout add_aqarez_c;
    LinearLayout request_add_aqarez_c;
    LinearLayout rate_c;
    LinearLayout Rental_installment_c;
    LinearLayout finance_c;

    RecyclerView allcity;
    public static boolean first_time_open_app = false;
    public static boolean first_time_ = false;
    public static boolean searh = true;


    TextView search;
    TextView search_aqarez_man;


    private BottomSheetDialogFragment_Filtter.ItemClickListener mItemClickListener;
    RecyclerView selsct_type_all;
    RecyclerView opration;
    List<select_typeModules> oprationModules_list = new ArrayList<>();
    List<TypeModules> type_list = new ArrayList<>();


    String type = "";
    String opration_select = "";

    TextView filtter_btn;


    TextView min_area, max_area;

    String min_price = "0", max_price = "1000";
    String min_area_ = "0", max_area_ = "1000";
    String num_room = "1";

    TextView room_1;
    TextView room_2;
    TextView room_3;
    TextView room_4;
    TextView room_5;


    EditText Les_price, Maximum_price, Les_space, Maximum_space;


    NavigationView navigationView_;

    DrawerLayout drawer;
    LinearLayout search_layout;
    LinearLayout search_aqaerz;
    TextView qr_search;
    ImageView close;
    TextView cancle_fillter;
    TextView search_filtter;


    EditText aqarez_name_edt;
    EditText search_text;


    static LinearLayout filtter_city;
    static LinearLayout allfilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        activity = this;

        first_time_open_app = true;
        first_time_ = true;
        add_service_aqarez = findViewById(R.id.add_service_aqarez);
        filtter_city = findViewById(R.id.filtter_city);
        allfilter = findViewById(R.id.allfilter);
        search_text = findViewById(R.id.search_text);
        add_aqar = findViewById(R.id.add_aqar);
        add_aqarez_c = findViewById(R.id.add_aqarez_c);
        request_add_aqarez_c = findViewById(R.id.request_add_aqarez_c);
        search_aqaerz = findViewById(R.id.search_aqaerz);
        allcity = findViewById(R.id.allcity);
        rate_c = findViewById(R.id.rate_c);
        Rental_installment_c = findViewById(R.id.Rental_installment_c);
        finance_c = findViewById(R.id.finance_c);
        search = findViewById(R.id.search);
        search_aqarez_man = findViewById(R.id.search_aqarez_man);
        qr_search = findViewById(R.id.qr_search);
        close = findViewById(R.id.close);
        aqarez_name_edt = findViewById(R.id.aqarez_name_edt);
        nodata = findViewById(R.id.nodata);

        selsct_type_all = findViewById(R.id.selsct_type_all);
        opration = findViewById(R.id.opration);
        filtter_btn = findViewById(R.id.filtter_btn);
        max_area = findViewById(R.id.max_area);
        min_area = findViewById(R.id.min_area);
        Les_price = findViewById(R.id.Les_price);
        Maximum_price = findViewById(R.id.Maximum_price);
        Les_space = findViewById(R.id.Les_space);
        Maximum_space = findViewById(R.id.Maximum_space);
        navigationView_ = findViewById(R.id.navigationView_);
        drawer = findViewById(R.id.drawer);
        search_layout = findViewById(R.id.search_layout);
        search_filtter = findViewById(R.id.search_filtter);
        cancle_fillter = findViewById(R.id.cancle_fillter);

        room_1 = findViewById(R.id.room_1);
        room_2 = findViewById(R.id.room_2);
        room_3 = findViewById(R.id.room_3);
        room_4 = findViewById(R.id.room_4);
        room_5 = findViewById(R.id.room_5);


        image_1 = findViewById(R.id.image_1);
        image_2 = findViewById(R.id.image_2);
        image_3 = findViewById(R.id.image_3);
        image_4 = findViewById(R.id.image_4);
        image_s = findViewById(R.id.image_s);

        text_1 = findViewById(R.id.text_1);
        text_2 = findViewById(R.id.text_2);
        text_3 = findViewById(R.id.text_3);
        text_4 = findViewById(R.id.text_4);
        text_s = findViewById(R.id.text_s);

        lay_1 = findViewById(R.id.lay_1);
        lay_2 = findViewById(R.id.lay_2);
        lay_3 = findViewById(R.id.lay_3);
        lay_4 = findViewById(R.id.lay_4);
        lay_s = findViewById(R.id.lay_s);

        service_layout = findViewById(R.id.service_layout);
        gray_layout = findViewById(R.id.gray_layout);

        ft = ((FragmentActivity) activity).getSupportFragmentManager();

        myFab = findViewById(R.id.fab);

        myFab.setColorFilter(Color.WHITE);


        if (Hawk.contains("lang")) {
            Hawk.put("lang", "ar");

        } else {
            Hawk.put("lang", "ar");
        }
//
        Locale locale = new Locale(Hawk.get("lang").toString());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new MapsFragmentNew());
        //  fragmentTransaction.commit();
        fragmentTransaction.commitAllowingStateLoss();

        try {
            forceUpdate();

        } catch (Exception e) {

        }

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                search.setBackground(getResources().getDrawable(R.drawable.button_login));
                search_aqarez_man.setBackground(getResources().getDrawable(R.drawable.mash));


                search.setTextColor(getResources().getColor(R.color.white));
                search_aqarez_man.setTextColor(getResources().getColor(R.color.textColor));
                search_layout.setVisibility(View.VISIBLE);
                search_aqaerz.setVisibility(View.GONE);
            }
        });
        search_aqarez_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                search.setBackground(getResources().getDrawable(R.drawable.mash));
                search_aqarez_man.setBackground(getResources().getDrawable(R.drawable.button_login));


                search.setTextColor(getResources().getColor(R.color.textColor));
                search_aqarez_man.setTextColor(getResources().getColor(R.color.white));
                search_layout.setVisibility(View.GONE);
                search_aqaerz.setVisibility(View.VISIBLE);
            }
        });

        qr_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                121);

                    } else {

                        Intent intent = new Intent(MainActivity.this, QRCameraActivity.class);
                        startActivity(intent);
                    }
                }


            }
        });
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!Settings.checkLogin()) {
                    startActivity(new Intent(MainActivity.this, check_login.class));

//                    new AlertDialog.Builder(MainActivity.this)
//                            .setMessage(getResources().getString(R.string.you_are_not_login_please_login))
//                            .setCancelable(false)
//                            .setPositiveButton(getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//
//                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
////                                intent.putExtra("from", "splash");
//                                    startActivity(intent);
//
//                                }
//                            })
//                            .setNegativeButton(getResources().getString(R.string.no), null)
//                            .show();
                } else {


//


                    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View popupView = layoutInflater.inflate(R.layout.addaqarez, null);


                    LinearLayout addAqares = popupView.findViewById(R.id.addAqares);
                    LinearLayout RequstAqars = popupView.findViewById(R.id.RequstAqars);
                    ImageView close = popupView.findViewById(R.id.close);
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    addAqares.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, AddAqarsActivity.class);
                            intent.putExtra("id", "");
                            startActivity(intent);
                            alertDialog.dismiss();
                        }
                    });
                    RequstAqars.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, AqarzOrActivity.class);
                            intent.putExtra("id", "");
                            startActivity(intent);
                            alertDialog.dismiss();
                        }
                    });
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });

//            alertDialog_country =
                    builder.setView(popupView);


                    alertDialog = builder.show();
                    alertDialog.getWindow().setGravity(Gravity.BOTTOM);

                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                }


            }
        });

        lay_s.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {


            }
        });

        lay_1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);

            }
        });
        lay_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = layoutInflater.inflate(R.layout.bottom_sheets_service, null);


                LinearLayout rent_layout = popupView.findViewById(R.id.rent_layout);
                LinearLayout realState = popupView.findViewById(R.id.realState);
                LinearLayout settings = popupView.findViewById(R.id.settings);
                LinearLayout contact_us = popupView.findViewById(R.id.contact_us);
                LinearLayout Technical_support = popupView.findViewById(R.id.Technical_support);
                LinearLayout MOreAqarezMan = popupView.findViewById(R.id.MOreAqarezMan);
                LinearLayout market = popupView.findViewById(R.id.market);


                if (Settings.CheckIsAccountAqarzMan()) {
                    realState.setVisibility(View.VISIBLE);
                } else {
                    realState.setVisibility(View.GONE);

                }


                realState.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (Settings.checkLogin()) {


//                            Intent intent = new Intent(MainActivity.this, OrderListActivity.class);
                            Intent intent = new Intent(MainActivity.this, AllOrderActivity.class);
                            intent.putExtra("type", "Real");
                            startActivity(intent);
//                            overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

                        } else {
                            startActivity(new Intent(MainActivity.this, check_login.class));
                        }

                    }
                });
                market.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Settings.checkLogin()) {


//                            Intent intent = new Intent(MainActivity.this, OrderListActivity.class);
                            Intent intent = new Intent(MainActivity.this, AllOrderActivity.class);
                            intent.putExtra("type", "market");
                            startActivity(intent);
//                            overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

                        } else {
                            startActivity(new Intent(MainActivity.this, check_login.class));
                        }

//                            Intent intent = new Intent(MainActivity.this, OrderListActivity.class);

//                            overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

                    }
                });
                contact_us.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ContactUsActivity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);
//                            overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

                    }
                });
                settings.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);
//                            overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

                    }
                });
                Technical_support.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {


                            Intent sendIntent = new Intent("android.intent.action.MAIN");
                            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.setType("text/plain");
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "");
                            sendIntent.putExtra("jid", "966532576667" + "@s.whatsapp.net");
                            sendIntent.setPackage("com.whatsapp");
                            startActivity(sendIntent);
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                MOreAqarezMan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(MainActivity.this, AllclintActivity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);

                    }
                });
                rent_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (Settings.checkLogin()) {


                            if (Hawk.contains("rent_layout")) {

                                Intent intent = new Intent(MainActivity.this, RentActivity.class);
                                intent.putExtra("id", "");
                                startActivity(intent);
                            } else {
                                Hawk.put("rent_layout", "rent_layout");
                                Intent intent = new Intent(MainActivity.this, RentShowActivity.class);
                                intent.putExtra("id", "");
                                startActivity(intent);
                            }

                        } else {
                            startActivity(new Intent(MainActivity.this, check_login.class));
                        }


                    }
                });


                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

//            alertDialog_country =
                builder.setView(popupView);


                alertDialog = builder.show();

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


//                new AlertDialog.Builder(MainActivity.this)
//                        .setMessage(getResources().getString(R.string.you_are_not_login_please_login))
//                        .setCancelable(false)
//                        .setPositiveButton(getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//
//                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
////                                intent.putExtra("from", "splash");
//                                startActivity(intent);
//
//                            }
//                        })
//                        .setNegativeButton(getResources().getString(R.string.no), null)
//                        .show();
            }
        });

        lay_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Settings.checkLogin()) {
                    Intent intent = new Intent(MainActivity.this, ChateActivity.class);
//                                intent.putExtra("from", "splash");
                    startActivity(intent);


                } else {
                    startActivity(new Intent(MainActivity.this, check_login.class));

//                    new AlertDialog.Builder(MainActivity.this)
//                            .setMessage(getResources().getString(R.string.you_are_not_login_please_login))
//                            .setCancelable(false)
//                            .setPositiveButton(getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//
//                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
////                                intent.putExtra("from", "splash");
//                                    startActivity(intent);
//
//                                }
//                            })
//                            .setNegativeButton(getResources().getString(R.string.no), null)
//                            .show();
                }
            }
        });
        lay_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Settings.checkLogin()) {


                    if (Settings.CheckIsAccountAqarzMan()) {
//                    Intent intent = new Intent(getContext(), DetailsAqarzManActivity.class);
//                    Intent intent = new Intent(getContext(), AqarzProfileActivity.class);
//                        Intent intent = new Intent(MainActivity.this, MyProfileActivity.class);
                        Intent intent = new Intent(MainActivity.this, ProfileDetailsActivity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);
//                        overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                    } else {
                        Intent intent = new Intent(MainActivity.this, MyProfileInformationActivity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);
//                        overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                    }
                } else {
                    startActivity(new Intent(MainActivity.this, check_login.class));

//                    new AlertDialog.Builder(MainActivity.this)
//                            .setMessage(getResources().getString(R.string.you_are_not_login_please_login))
//                            .setCancelable(false)
//                            .setPositiveButton(getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//
//                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
////                                intent.putExtra("from", "splash");
//                                    startActivity(intent);
//
//                                }
//                            })
//                            .setNegativeButton(getResources().getString(R.string.no), null)
//                            .show();
                }

//

            }
        });


        if (Settings.checkLogin()) {

            init_volley();

            JSONObject jsonObject = new JSONObject();
            try {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                System.out.println("device_token " + refreshedToken);
                jsonObject.put("device_token", refreshedToken);
            } catch (Exception e) {

            }

            VolleyService mVolleyService = new VolleyService(mResultCallback, MainActivity.this);
            mVolleyService.postDataVolley("updateDeviceToken", WebService.updateDeviceToken, jsonObject);


            if (Settings.GetUser().getIs_employee().equals("1")) {


                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = layoutInflater.inflate(R.layout.is_emp_alert_dialog, null);
                ImageView close = popupView.findViewById(R.id.close);
                TextView yes = popupView.findViewById(R.id.yes);
                TextView no = popupView.findViewById(R.id.no);


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        init_volley();

                        JSONObject jsonObject = new JSONObject();
                        try {

                            jsonObject.put("is_emp", "yes");
                            jsonObject.put("mobile", Settings.GetUser().getMobile() + "");
                            jsonObject.put("country_code", "+966");

                        } catch (Exception e) {

                        }
                        init_volley();

                        WebService.loading(MainActivity.this, true);

                        VolleyService mVolleyService = new VolleyService(mResultCallback, MainActivity.this);
                        mVolleyService.postDataVolley("check_employe", WebService.check_employe, jsonObject);
                        alertDialog.dismiss();

                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        init_volley();

                        JSONObject jsonObject = new JSONObject();
                        try {

                            jsonObject.put("is_emp", "no");
                            jsonObject.put("mobile", Settings.GetUser().getMobile() + "");
                            jsonObject.put("country_code", "966");
                        } catch (Exception e) {

                        }
                        init_volley();
                        WebService.loading(MainActivity.this, true);
                        VolleyService mVolleyService = new VolleyService(mResultCallback, MainActivity.this);
                        mVolleyService.postDataVolley("check_employe", WebService.check_employe, jsonObject);
                        alertDialog.dismiss();

                    }
                });
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

//            alertDialog_country =
                builder.setView(popupView);


                alertDialog = builder.show();

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


            }


        }

//        text_1.setTextColor(getResources().getColor(R.color.colorPrimary));
//        text_2.setTextColor(getResources().getColor(R.color.color_un_active));
//        text_3.setTextColor(getResources().getColor(R.color.color_un_active));
//        text_4.setTextColor(getResources().getColor(R.color.color_un_active));
//        text_s.setTextColor(getResources().getColor(R.color.color_un_active));
//
//        image_1.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_ATOP);
//        image_2.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//        image_3.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//        image_4.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);

//        image_1.setImageDrawable(getResources().getDrawable(R.drawable.ic_saudi_arabia_menu_fill));
//
//        image_2.setImageDrawable(getResources().getDrawable(R.drawable.ic_search_interface_symbol_fill));
//        image_3.setImageDrawable(getResources().getDrawable(R.drawable.ic_chat__1_as));
//        image_4.setImageDrawable(getResources().getDrawable(R.drawable.ic__2222pro));


        add_aqarez_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddAqarsActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
            }
        });

        request_add_aqarez_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AqarzOrActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);
            }
        });

        rate_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Rental_installment_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Hawk.contains("rent_layout")) {

                    Intent intent = new Intent(MainActivity.this, RentActivity.class);
                    intent.putExtra("id", "");
                    startActivity(intent);
                } else {
                    Hawk.put("rent_layout", "rent_layout");
                    Intent intent = new Intent(MainActivity.this, RentShowActivity.class);
                    intent.putExtra("id", "");
                    startActivity(intent);
                }


            }
        });
        finance_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, FinanceActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);

            }
        });
        LinearLayoutManager layoutManagerm
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        selsct_type_all.setLayoutManager(layoutManagerm);


        oprationModules_list.add(new select_typeModules(1, getResources().getString(R.string.mn1)));
        oprationModules_list.add(new select_typeModules(2, getResources().getString(R.string.mn2)));
        oprationModules_list.add(new select_typeModules(3, getResources().getString(R.string.mn3)));
//        oprationModules_list.add(new select_typeModules(1, getContext().getResources().getString(R.string.Investment)));

        RecyclerView_bottomSheet_type recyclerView_bottomSheet_type = new RecyclerView_bottomSheet_type(MainActivity.this, oprationModules_list);
        recyclerView_bottomSheet_type.addItemClickListener(new RecyclerView_bottomSheet_type.ItemClickListener() {
            @Override
            public void onItemClick(List<select_typeModules> select_typeModules) {
                type = "";

                for (int i = 0; i < select_typeModules.size(); i++) {

                    if (select_typeModules.get(0).getSelected()) {
                        type = "is_pay";

                    }
                    if (select_typeModules.get(1).getSelected()) {
                        type = "is_rent";

                    }
                    if (select_typeModules.get(2).getSelected()) {
                        type = "is_pay";

                    }


                }


//                type = select_typeModules;


            }
        });
        selsct_type_all.setAdapter(recyclerView_bottomSheet_type);

        cancle_fillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                te = "";
                if (!type.equals("")) {
//                    te = te + "&estate_pay_type=" + type;
                }
                if (!Les_price.getText().toString().equals("")) {
//                    te = te + "&price_from=" + Les_price.getText().toString();
                }
                if (!opration_select.equals("")) {
//                    te = te + "&estate_type=" + opration_select;
                }
                if (!Maximum_price.getText().toString().equals("")) {
//                    te = te + "&price_to=" + Maximum_price.getText().toString();
                }
                if (!Les_space.getText().toString().equals("")) {
//                    te = te + "&area_from=" + Les_space.getText().toString();
                }
                if (!Maximum_space.getText().toString().equals("")) {
//                    te = te + "&area_to=" + Maximum_space.getText().toString();
                }


//                te = "&estate_pay_type=" + type + "&price_from=" + Les_price.getText().toString() + "&price_to=" + Maximum_price.getText().toString() + "&area_from=" + Les_space.getText().toString() + "&area_from=" + Maximum_space.getText().toString();

                MapsFragmentNew.filter = te;
                MapsFragmentNew.check_lastsenareo();

                drawer.closeDrawer(GravityCompat.START);
            }
        });
        filtter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                te = "";
                if (!type.equals("")) {
                    te = te + "&estate_pay_type=" + type;
                }
                if (!Les_price.getText().toString().equals("")) {
                    te = te + "&price_from=" + Les_price.getText().toString();
                }
                if (!opration_select.equals("")) {
                    te = te + "&estate_type=" + opration_select;
                }
                if (!Maximum_price.getText().toString().equals("")) {
                    te = te + "&price_to=" + Maximum_price.getText().toString();
                }
                if (!Les_space.getText().toString().equals("")) {
                    te = te + "&area_from=" + Les_space.getText().toString();
                }
                if (!Maximum_space.getText().toString().equals("")) {
                    te = te + "&area_to=" + Maximum_space.getText().toString();
                }


//                te = "&estate_pay_type=" + type + "&price_from=" + Les_price.getText().toString() + "&price_to=" + Maximum_price.getText().toString() + "&area_from=" + Les_space.getText().toString() + "&area_from=" + Maximum_space.getText().toString();
                drawer.closeDrawer(GravityCompat.START);

                MapsFragmentNew.filter = te;
                MapsFragmentNew.getAllEstate();


            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);

            }
        });
///------------------------------------------------------------------------------------------------------
        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        LinearLayoutManager layoutManagerw
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        opration.setLayoutManager(layoutManagerw);

        RecyclerView_All_opration_bottom_sheet recyclerView_all_opration_bottom_sheet = new RecyclerView_All_opration_bottom_sheet(MainActivity.this, type_list);
        recyclerView_all_opration_bottom_sheet.addItemClickListener(new RecyclerView_All_opration_bottom_sheet.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                opration_select = type_list.get(position).getId().toString() + "";
            }
        });
        opration.setAdapter(recyclerView_all_opration_bottom_sheet);


        filtter_acion();
    }

    public void filtter_acion() {


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        allcity.setLayoutManager(layoutManager1);


        if (Application.AllCity.size() != 0) {

            cityModules_list = Application.AllCity;

        }
        search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    filtter_city.setVisibility(View.VISIBLE);
                    allfilter.setVisibility(View.GONE);
                    init_volley();
                    WebService.loading(MainActivity.this, true);

                    String region_id_postion = "";
                    if (MapsFragmentNew.region_id_postion != null) {
                        region_id_postion = MapsFragmentNew.region_id_postion + "";
                    }
                    String city_id_postion = "";
                    if (MapsFragmentNew.city_id_postion != null) {
                        city_id_postion = MapsFragmentNew.city_id_postion + "";
                    }


                    VolleyService mVolleyService = new VolleyService(mResultCallback, MainActivity.this);
                    mVolleyService.getDataVolley("cities_with_neb", WebService.cities_with_neb + "?name=" + search_text.getText().toString());//+ "&state_id=" + region_id_postion + "&city_id=" + city_id_postion


                    return true;
                }
                return false;
            }
        });
        search_text.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {

                }

            }
        });


        room_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_1.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_2.setBackground(null);
                room_3.setBackground(null);
                room_4.setBackground(null);
                room_5.setBackground(null);


                room_1.setTextColor(getResources().getColor(R.color.white));
                room_2.setTextColor(getResources().getColor(R.color.textColor));
                room_3.setTextColor(getResources().getColor(R.color.textColor));
                room_4.setTextColor(getResources().getColor(R.color.textColor));
                room_5.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "1";
            }
        });
        room_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_2.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_1.setBackground(null);
                room_3.setBackground(null);
                room_4.setBackground(null);
                room_5.setBackground(null);


                room_2.setTextColor(getResources().getColor(R.color.white));
                room_1.setTextColor(getResources().getColor(R.color.textColor));
                room_3.setTextColor(getResources().getColor(R.color.textColor));
                room_4.setTextColor(getResources().getColor(R.color.textColor));
                room_5.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "2";

            }
        });
        room_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_3.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_2.setBackground(null);
                room_1.setBackground(null);
                room_4.setBackground(null);
                room_5.setBackground(null);


                room_3.setTextColor(getResources().getColor(R.color.white));
                room_2.setTextColor(getResources().getColor(R.color.textColor));
                room_1.setTextColor(getResources().getColor(R.color.textColor));
                room_4.setTextColor(getResources().getColor(R.color.textColor));
                room_5.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "3";

            }
        });
        room_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_4.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_2.setBackground(null);
                room_3.setBackground(null);
                room_1.setBackground(null);
                room_5.setBackground(null);


                room_4.setTextColor(getResources().getColor(R.color.white));
                room_2.setTextColor(getResources().getColor(R.color.textColor));
                room_3.setTextColor(getResources().getColor(R.color.textColor));
                room_1.setTextColor(getResources().getColor(R.color.textColor));
                room_5.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "4";

            }
        });
        room_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_5.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_2.setBackground(null);
                room_3.setBackground(null);
                room_4.setBackground(null);
                room_1.setBackground(null);


                room_5.setTextColor(getResources().getColor(R.color.white));
                room_2.setTextColor(getResources().getColor(R.color.textColor));
                room_3.setTextColor(getResources().getColor(R.color.textColor));
                room_4.setTextColor(getResources().getColor(R.color.textColor));
                room_1.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "5";

            }
        });

        search_filtter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);


                try {


                    Intent intent = new Intent(MainActivity.this, AllclintActivity.class);
                    intent.putExtra("search_text", aqarez_name_edt.getText().toString() + "");
                    startActivity(intent);


//                    MapsFragmentNew.get_all_estate_list_filttter_();

                } catch (Exception e) {

                }
            }
        });
        aqarez_name_edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    Intent intent = new Intent(MainActivity.this, AllclintActivity.class);
                    intent.putExtra("search_text", aqarez_name_edt.getText().toString() + "");
                    startActivity(intent);

                    return true;
                }
                return false;
            }
        });
    }

    //jsj
    public void filtter_acion_filter_market() {


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        allcity.setLayoutManager(layoutManager1);


        if (Application.AllCity.size() != 0) {

            cityModules_list = Application.AllCity;

        }
        search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    filtter_city.setVisibility(View.VISIBLE);
                    allfilter.setVisibility(View.GONE);
                    init_volley();
                    WebService.loading(MainActivity.this, true);

                    String region_id_postion = "";
                    if (MapsFragmentNew.region_id_postion != null) {
                        region_id_postion = MapsFragmentNew.region_id_postion + "";
                    }
                    String city_id_postion = "";
                    if (MapsFragmentNew.city_id_postion != null) {
                        city_id_postion = MapsFragmentNew.city_id_postion + "";
                    }


                    VolleyService mVolleyService = new VolleyService(mResultCallback, MainActivity.this);
                    mVolleyService.getDataVolley("cities_with_neb", WebService.cities_with_neb + "?name=" + search_text.getText().toString());//+ "&state_id=" + region_id_postion + "&city_id=" + city_id_postion


                    return true;
                }
                return false;
            }
        });
        search_text.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {

                }

            }
        });


        room_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_1.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_2.setBackground(null);
                room_3.setBackground(null);
                room_4.setBackground(null);
                room_5.setBackground(null);


                room_1.setTextColor(getResources().getColor(R.color.white));
                room_2.setTextColor(getResources().getColor(R.color.textColor));
                room_3.setTextColor(getResources().getColor(R.color.textColor));
                room_4.setTextColor(getResources().getColor(R.color.textColor));
                room_5.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "1";
            }
        });
        room_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_2.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_1.setBackground(null);
                room_3.setBackground(null);
                room_4.setBackground(null);
                room_5.setBackground(null);


                room_2.setTextColor(getResources().getColor(R.color.white));
                room_1.setTextColor(getResources().getColor(R.color.textColor));
                room_3.setTextColor(getResources().getColor(R.color.textColor));
                room_4.setTextColor(getResources().getColor(R.color.textColor));
                room_5.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "2";

            }
        });
        room_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_3.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_2.setBackground(null);
                room_1.setBackground(null);
                room_4.setBackground(null);
                room_5.setBackground(null);


                room_3.setTextColor(getResources().getColor(R.color.white));
                room_2.setTextColor(getResources().getColor(R.color.textColor));
                room_1.setTextColor(getResources().getColor(R.color.textColor));
                room_4.setTextColor(getResources().getColor(R.color.textColor));
                room_5.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "3";

            }
        });
        room_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_4.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_2.setBackground(null);
                room_3.setBackground(null);
                room_1.setBackground(null);
                room_5.setBackground(null);


                room_4.setTextColor(getResources().getColor(R.color.white));
                room_2.setTextColor(getResources().getColor(R.color.textColor));
                room_3.setTextColor(getResources().getColor(R.color.textColor));
                room_1.setTextColor(getResources().getColor(R.color.textColor));
                room_5.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "4";

            }
        });
        room_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_5.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_2.setBackground(null);
                room_3.setBackground(null);
                room_4.setBackground(null);
                room_1.setBackground(null);


                room_5.setTextColor(getResources().getColor(R.color.white));
                room_2.setTextColor(getResources().getColor(R.color.textColor));
                room_3.setTextColor(getResources().getColor(R.color.textColor));
                room_4.setTextColor(getResources().getColor(R.color.textColor));
                room_1.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "5";

            }
        });

        search_filtter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);


                try {


                    Intent intent = new Intent(MainActivity.this, AllclintActivity.class);
                    intent.putExtra("search_text", aqarez_name_edt.getText().toString() + "");
                    startActivity(intent);


//                    MapsFragmentNew.get_all_estate_list_filttter_();

                } catch (Exception e) {

                }
            }
        });
        aqarez_name_edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    Intent intent = new Intent(MainActivity.this, AllclintActivity.class);
                    intent.putExtra("search_text", aqarez_name_edt.getText().toString() + "");
                    startActivity(intent);

                    return true;
                }
                return false;
            }
        });
    }


    public static void convert_city_to_filter() {


        filtter_city.setVisibility(View.GONE);
        allfilter.setVisibility(View.VISIBLE);

        System.out.println(
                "dflkdlfkdlkfldkf"
        );


    }


    private void toggle() {
        if (service_layout.getVisibility() == View.VISIBLE) {
            service_layout.setVisibility(View.GONE);
            gray_layout.setVisibility(View.GONE);

            myFab.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
//                            service_layout.animate().alpha(0.0f);

        } else {
            service_layout.setVisibility(View.VISIBLE);
            gray_layout.setVisibility(View.VISIBLE);
            myFab.setImageDrawable(getResources().getDrawable(R.drawable.ic_clear_white_24dp));


        }
        gray_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service_layout.setVisibility(View.GONE);
                gray_layout.setVisibility(View.GONE);
            }
        });


        add_aqar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddAqarsActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
            }
        });


        add_service_aqarez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AqarzOrActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onResume() {


        if (!first_time_) {
            forceUpdate();
        } else {

        }

        super.onResume();
    }

    public void goToFragment(int fragmentIndex) {
        fragmentManager = getSupportFragmentManager();
        switch (fragmentIndex) {
            case 0:
                // set sliding nav
                //back
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                //     getSupportActionBar().setDisplayShowHomeEnabled(true);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new MapsFragment());
                //  fragmentTransaction.commit();
                fragmentTransaction.commitAllowingStateLoss();

                break;


//            case 1:
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.container, new SubscriptionsFragment());
//                //    fragmentTransaction.commit();
//                fragmentTransaction.commitAllowingStateLoss();
//                break;

            case 1:

                if (Settings.checkLogin()) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new OrdersFragment());
                    //    fragmentTransaction.commit();
                    fragmentTransaction.commitAllowingStateLoss();


                } else {
                    startActivity(new Intent(MainActivity.this, check_login.class));

//                    new AlertDialog.Builder(MainActivity.this)
//                            .setMessage(getResources().getString(R.string.you_are_not_login_please_login))
//                            .setCancelable(false)
//                            .setPositiveButton(getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//
//                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
////                                intent.putExtra("from", "splash");
//                                    startActivity(intent);
//
//                                }
//                            })
//                            .setNegativeButton(getResources().getString(R.string.no), null)
//                            .show();
                }


//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.container, new OrdersFragment());
//                //  fragmentTransaction.commit();
//                fragmentTransaction.commitAllowingStateLoss();

                break;

            case 2:
                if (Settings.checkLogin()) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new ChatFragment());
                    //    fragmentTransaction.commit();
                    fragmentTransaction.commitAllowingStateLoss();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new ChatFragment());
                    //    fragmentTransaction.commit();
                    fragmentTransaction.commitAllowingStateLoss();


                } else {
                    startActivity(new Intent(MainActivity.this, check_login.class));

//                    new AlertDialog.Builder(MainActivity.this)
//                            .setMessage(getResources().getString(R.string.you_are_not_login_please_login))
//                            .setCancelable(false)
//                            .setPositiveButton(getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//
//                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
////                                intent.putExtra("from", "splash");
//                                    startActivity(intent);
//
//                                }
//                            })
//                            .setNegativeButton(getResources().getString(R.string.no), null)
//                            .show();
                }

                break;
            case 3:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new MoreFragment());
                //    fragmentTransaction.commit();
                fragmentTransaction.commitAllowingStateLoss();

                break;
        }


    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getResources().getString(R.string.click_to_back), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public static void open_found(String uuid) {
        bottomSheetDialogFragment_myEstate = new BottomSheetDialogFragment_MyEstate(uuid + "");

        bottomSheetDialogFragment_myEstate.show(((FragmentActivity) activity).getSupportFragmentManager(), "");


    }

    public static void go_to_order() {

        if (Settings.checkLogin()) {
            text_1.setTextColor(activity.getResources().getColor(R.color.color_un_active));
            text_2.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
            text_3.setTextColor(activity.getResources().getColor(R.color.color_un_active));
            text_4.setTextColor(activity.getResources().getColor(R.color.color_un_active));
            text_s.setTextColor(activity.getResources().getColor(R.color.color_un_active));

            image_1.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_saudi_arabia_menu));
            image_2.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_order_menu_fill));
            image_3.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_service_menu));
            image_4.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_user_menu));

            fragmentManager = ft;

            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new OrdersFragment());
            //  fragmentTransaction.commit();
            fragmentTransaction.commitAllowingStateLoss();
        } else {
            activity.startActivity(new Intent(activity, check_login.class));

//            new AlertDialog.Builder(activity)
//                    .setMessage(activity.getResources().getString(R.string.you_are_not_login_please_login))
//                    .setCancelable(false)
//                    .setPositiveButton(activity.getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//
//                            Intent intent = new Intent(activity, LoginActivity.class);
////                                intent.putExtra("from", "splash");
//                            activity.startActivity(intent);
//
//                        }
//                    })
//                    .setNegativeButton(activity.getResources().getString(R.string.no), null)
//                    .show();
        }

    }

    public static void send_done() {

        System.out.println("09999999999999999");

        bottomSheetDialogFragment_myEstate.dismiss();


        bottomSheerDialog = new BottomSheetDialog(MainActivity.activity);
        LayoutInflater li = (LayoutInflater) MainActivity.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View parentView = li.inflate(R.layout.success_sandoq, null);
        Button ok = parentView.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                                    finish();
                bottomSheerDialog.dismiss();


            }
        });
        bottomSheerDialog.setContentView(parentView);


        Window window = bottomSheerDialog.getWindow();
        window.findViewById(com.google.android.material.R.id.container).setFitsSystemWindows(false);
        // dark navigation bar icons
        View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, MainActivity.activity.getResources().getDisplayMetrics());


//        ((View) decorView.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));


        bottomSheerDialog.show();
    }

    // check version on play store and force update
    public void forceUpdate() {
        first_time_ = false;
        PackageManager packageManager = this.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String currentVersion = packageInfo.versionName;

        System.out.println("currentVersion" + currentVersion);
        new ForceUpdateAsync(currentVersion, MainActivity.this).execute();
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(MainActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");
//                        String next_page_url = response.getString("next_page_url");


                        if (requestType.equals("user")) {
                            JSONObject jsonObject = new JSONObject(data);


                            Hawk.put("user", data);

                        } else if (requestType.equals("check_employe")) {


                            init_volley();
                            VolleyService mVolleyService = new VolleyService(mResultCallback, MainActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                            mVolleyService.getDataVolley("user", WebService.my_profile + "");


                        } else if (requestType.equals("cities_with_neb")) {
                            JSONObject jsonObject = new JSONObject(data);

                            String datadata = jsonObject.getString("data");

                            JSONArray jsonArray = new JSONArray(datadata);
                            cityModules_list_filtter.clear();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();

                                CityModules homeModules_aqares = gson.fromJson(mJson, CityModules.class);

                                cityModules_list_filtter.add(homeModules_aqares);
                            }


                            if (cityModules_list_filtter.size() == 0) {


                                nodata.setVisibility(View.VISIBLE);
                            } else {
                                nodata.setVisibility(View.GONE);

                            }

                            RecyclerView_city_side_menu recyclerView_city_bootom_sheets = new RecyclerView_city_side_menu(MainActivity.this, cityModules_list_filtter);
                            recyclerView_city_bootom_sheets.addItemClickListener(new RecyclerView_city_side_menu.ItemClickListener() {
                                @Override
                                public void onItemClick(int i) {
//                        cityModules_list = alldata;
                                    convert_city_to_filter();
                                    searh = false;

//                                    MapsFragmentNew.city_id_postion = cityModules_list_filtter.get(i).getId() + "";
                                    MapsFragmentNew.lat = cityModules_list_filtter.get(i).getLat() + "";
                                    MapsFragmentNew.lan = cityModules_list_filtter.get(i).getLan() + "";


                                    search_text.setText(cityModules_list_filtter.get(i).getSearch_name());//+ "-" + cityModules_list_filtter.get(i).getCity().getName()

                                }
                            });

                            allcity.setAdapter(recyclerView_city_bootom_sheets);


                        }


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(MainActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(MainActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(MainActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(MainActivity.this, false);

                WebService.Make_Toast_color(MainActivity.this, error, "error");


            }
        };


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        } else {

            Intent intent = new Intent(MainActivity.this, QRCameraActivity.class);
            startActivity(intent);

        }
    }
}
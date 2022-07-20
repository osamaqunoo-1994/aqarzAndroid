package sa.aqarz.NewAqarz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import sa.aqarz.Activity.AddAqarz.AddAqarzActivity;
import sa.aqarz.Activity.AllOrderActivity;
import sa.aqarz.Activity.Auth.ChoseTypeActivity;
import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Activity.OprationNew.AqarzOrActivity;
import sa.aqarz.Activity.OprationNew.RentActivity;
import sa.aqarz.Activity.OprationNew.RentShowActivity;
import sa.aqarz.Activity.check_login;
import sa.aqarz.Activity.profile.MyOffersActivity;
import sa.aqarz.Adapter.RecyclerView_city_side_menu;
import sa.aqarz.Fragment.MapsFragment;
import sa.aqarz.Fragment.mapsHome.MapsFragmentNew;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.ObjectFiltterOrder;
import sa.aqarz.Modules.Object_filtter;
import sa.aqarz.Modules.imagemodules;
import sa.aqarz.NewAqarz.AqqAqarz.AddAqarzStepsActivity;
import sa.aqarz.NewAqarz.BottomDialog.BottomSheetDialogFragment_forceUpdate;
import sa.aqarz.NewAqarz.Fragments.ChatFragment;
import sa.aqarz.NewAqarz.Fragments.HomeMapFragment;
import sa.aqarz.NewAqarz.Fragments.MoreFragment;
import sa.aqarz.NewAqarz.Fragments.OrderFragment;
import sa.aqarz.NewAqarz.Fragments.OrderUserFragment;
import sa.aqarz.NewAqarz.OprationOrder.DetailsOrderActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.ForceUpdateAsync;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class MainAqarzActivity extends AppCompatActivity {
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

    public static LinearLayout lay_1, lay_2, lay_3, lay_4, lay_s;
    FloatingActionButton myFab;

    public static boolean first_time_ = false;

    static String click_tab = "home";

    static LinearLayout add_aqares_and_order_and_estate;
    LinearLayout gray_layout;
    LinearLayout addAqares;
    LinearLayout RequstAqars;
    LinearLayout rent;
    LinearLayout my_order;

    ImageView close_add;
    AlertDialog alertDialog;

    public static String type_order_main = "";
    public static String type_type_order_main = "";


    private static FragmentTransaction fragmentTransaction;
    private static FragmentManager fragmentManager;
    public static Activity activity;
    public static Object_filtter object_filtter = new Object_filtter();
    public static ObjectFiltterOrder objectFiltterOrder = new ObjectFiltterOrder();
    public static ArrayList<imagemodules> items_ViewPager_all = new ArrayList<imagemodules>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_aqarz);
        first_time_ = true;
        activity = this;
        add_aqares_and_order_and_estate = findViewById(R.id.add_aqares_and_order_and_estate);
        gray_layout = findViewById(R.id.gray_layout);
        addAqares = findViewById(R.id.addAqares);
        RequstAqars = findViewById(R.id.RequstAqars);
        rent = findViewById(R.id.rent);
        my_order = findViewById(R.id.my_order);
        close_add = findViewById(R.id.close_add);


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


        Hawk.put("filtter", "no");
        Hawk.put("fillterorder", "no");


        myFab = findViewById(R.id.fab);

        myFab.setColorFilter(Color.WHITE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (myFab != null) {
                myFab.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new MapsFragmentNew());
        //  fragmentTransaction.commit();
        fragmentTransaction.commitAllowingStateLoss();


        on_click_bottom_action();
        set_contanier_fragments();


        if (Settings.checkLogin()) {

            init_volley();

            JSONObject jsonObject = new JSONObject();
            try {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                System.out.println("device_token " + refreshedToken);
                jsonObject.put("device_token", refreshedToken);
            } catch (Exception e) {

            }

            VolleyService mVolleyService = new VolleyService(mResultCallback, MainAqarzActivity.this);
            mVolleyService.postDataVolley("updateDeviceToken", WebService.updateDeviceToken, jsonObject);


        }

    }


    public static void set_contanier_fragments() {
        click_tab = "home";
        text_1.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
        text_2.setTextColor(activity.getResources().getColor(R.color.color_un_active));
        text_3.setTextColor(activity.getResources().getColor(R.color.color_un_active));
        text_4.setTextColor(activity.getResources().getColor(R.color.color_un_active));
        text_s.setTextColor(activity.getResources().getColor(R.color.color_un_active));

        image_1.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_ATOP);
        image_2.setColorFilter(ContextCompat.getColor(activity, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
        image_3.setColorFilter(ContextCompat.getColor(activity, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
        image_4.setColorFilter(ContextCompat.getColor(activity, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new HomeMapFragment());
        //  fragmentTransaction.commit();
        fragmentTransaction.commitAllowingStateLoss();

    }

    public static void set_contanier_fragments_order() {
        click_tab = "order";

        text_1.setTextColor(activity.getResources().getColor(R.color.color_un_active));
        text_2.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
        text_3.setTextColor(activity.getResources().getColor(R.color.color_un_active));
        text_4.setTextColor(activity.getResources().getColor(R.color.color_un_active));
        text_s.setTextColor(activity.getResources().getColor(R.color.color_un_active));

        image_1.setColorFilter(ContextCompat.getColor(activity, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
        image_2.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_ATOP);
        image_3.setColorFilter(ContextCompat.getColor(activity, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
        image_4.setColorFilter(ContextCompat.getColor(activity, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
        click_tab = "order";

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new OrderFragment());
        fragmentTransaction.addToBackStack(null);
        //  fragmentTransaction.commit();
        fragmentTransaction.commitAllowingStateLoss();


    }

    public void on_click_bottom_action() {

        lay_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                text_1.setTextColor(getResources().getColor(R.color.colorPrimary));
                text_2.setTextColor(getResources().getColor(R.color.color_un_active));
                text_3.setTextColor(getResources().getColor(R.color.color_un_active));
                text_4.setTextColor(getResources().getColor(R.color.color_un_active));
                text_s.setTextColor(getResources().getColor(R.color.color_un_active));

                image_1.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_ATOP);
                image_2.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
                image_3.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
                image_4.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);


                if (click_tab.equals("home")) {


                } else {
                    click_tab = "home";
                    set_contanier_fragments();
                }

            }
        });


        lay_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                startActivity(new Intent(MainAqarzActivity.this, AllOrderActivity.class));


                MainAqarzActivity.type_order_main = "real";

                if (!Settings.checkLogin()) {
                    startActivity(new Intent(MainAqarzActivity.this, LoginActivity.class));
//                    startActivity(new Intent(MainAqarzActivity.this, check_login.class));

                } else {
                    click_tab = "order";

                    click_tab = "order";


                    if (Settings.CheckIsAccountAqarzMan()) {
                        text_1.setTextColor(getResources().getColor(R.color.color_un_active));
                        text_2.setTextColor(getResources().getColor(R.color.colorPrimary));
                        text_3.setTextColor(getResources().getColor(R.color.color_un_active));
                        text_4.setTextColor(getResources().getColor(R.color.color_un_active));
                        text_s.setTextColor(getResources().getColor(R.color.color_un_active));

                        image_1.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
                        image_2.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_ATOP);
                        image_3.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
                        image_4.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);

                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container, new OrderFragment());
                        fragmentTransaction.addToBackStack(null);
                        //  fragmentTransaction.commit();
                        fragmentTransaction.commitAllowingStateLoss();
                    } else {


                        Intent intent = new Intent(MainAqarzActivity.this, ManageOrderActivity.class);
                        intent.putExtra("id", "");
                        intent.putExtra("from", "home");
                        startActivity(intent);

//                        startActivity(new Intent(MainAqarzActivity.this,ManageOrderActivity.class));

//                        fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.container, new OrderUserFragment());
//                        fragmentTransaction.addToBackStack(null);
//                        //  fragmentTransaction.commit();
//                        fragmentTransaction.commitAllowingStateLoss();


                    }


                }

            }
        });
        lay_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!Settings.checkLogin()) {
//                    startActivity(new Intent(MainAqarzActivity.this, LoginActivity.class));
//
////                    startActivity(new Intent(MainAqarzActivity.this, check_login.class));
//
//                } else {
//                    click_tab = "chat";


                Intent intent = new Intent(MainAqarzActivity.this, FillterActivity.class);
                intent.putExtra("from", "home");
                startActivity(intent);


//                    text_1.setTextColor(getResources().getColor(R.color.color_un_active));
//                    text_2.setTextColor(getResources().getColor(R.color.color_un_active));
//                    text_3.setTextColor(getResources().getColor(R.color.colorPrimary));
//                    text_4.setTextColor(getResources().getColor(R.color.color_un_active));
//                    text_s.setTextColor(getResources().getColor(R.color.color_un_active));
//
//                    image_1.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                    image_2.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                    image_3.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                    image_4.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);


                //                    fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.container, new ChatFragment());
//                    fragmentTransaction.addToBackStack(null);
//                    //  fragmentTransaction.commit();
//                    fragmentTransaction.commitAllowingStateLoss();
//                }

            }
        });
        lay_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Settings.checkLogin()) {
                    startActivity(new Intent(MainAqarzActivity.this, LoginActivity.class));

//                    startActivity(new Intent(MainAqarzActivity.this, check_login.class));

                } else {
                    click_tab = "profile";

                    text_1.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_2.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_3.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_4.setTextColor(getResources().getColor(R.color.colorPrimary));
                    text_s.setTextColor(getResources().getColor(R.color.color_un_active));

                    image_1.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    image_2.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    image_3.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    image_4.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_ATOP);

                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new MoreFragment());
                    fragmentTransaction.addToBackStack(null);
                    //  fragmentTransaction.commit();
                    fragmentTransaction.commitAllowingStateLoss();
                }

            }
        });
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Settings.checkLogin()) {
                    if (Settings.CheckIsAccountAqarzMan()) {


                        add_aqares_and_order_and_estate.setVisibility(View.VISIBLE);
                        gray_layout.setVisibility(View.VISIBLE);
                        gray_layout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gray_layout.setVisibility(View.GONE);
                                add_aqares_and_order_and_estate.setVisibility(View.GONE);

                            }
                        });
                        close_add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gray_layout.setVisibility(View.GONE);
                                add_aqares_and_order_and_estate.setVisibility(View.GONE);

                            }
                        });

                    } else {
//                        startActivity(new Intent(MainActivity.this, MyOrderUserActivity.class));
                        Intent intent = new Intent(MainAqarzActivity.this, AqarzOrActivity.class);
                        intent.putExtra("id", "");
                        startActivity(intent);
                        gray_layout.setVisibility(View.GONE);
                        add_aqares_and_order_and_estate.setVisibility(View.GONE);
                    }
                } else {
                    add_aqares_and_order_and_estate.setVisibility(View.VISIBLE);
                    gray_layout.setVisibility(View.VISIBLE);
                    gray_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gray_layout.setVisibility(View.GONE);
                            add_aqares_and_order_and_estate.setVisibility(View.GONE);

                        }
                    });
                    close_add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gray_layout.setVisibility(View.GONE);
                            add_aqares_and_order_and_estate.setVisibility(View.GONE);

                        }
                    });
                }
                addAqares.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!Settings.checkLogin()) {
                            startActivity(new Intent(MainAqarzActivity.this, LoginActivity.class));

//                            startActivity(new Intent(MainAqarzActivity.this, check_login.class));

                        } else {

                            if (Settings.GetUser().getIs_iam_complete().equals("0")) {


                                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                final View popupView = layoutInflater.inflate(R.layout.alert_is_iam_compleate, null);

                                TextView ok = popupView.findViewById(R.id.ok);
                                TextView cancle = popupView.findViewById(R.id.cancle);


                                ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        init_volley();
                                        WebService.loading(MainAqarzActivity.this, true);

                                        VolleyService mVolleyService = new VolleyService(mResultCallback, MainAqarzActivity.this);
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
                                final AlertDialog.Builder builder = new AlertDialog.Builder(MainAqarzActivity.this);

                                builder.setView(popupView);


                                alertDialog = builder.show();

                                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            } else {
                                Intent intent = new Intent(MainAqarzActivity.this, AddAqarzStepsActivity.class);
                                intent.putExtra("id", "");
                                startActivity(intent);
                                gray_layout.setVisibility(View.GONE);
                                add_aqares_and_order_and_estate.setVisibility(View.GONE);
                            }
//                            Intent intent = new Intent(MainAqarzActivity.this, AddAqarzActivity.class);

                        }
//                            alertDialog.dismiss();
                    }
                });
                my_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!Settings.checkLogin()) {
                            startActivity(new Intent(MainAqarzActivity.this, LoginActivity.class));

//                            startActivity(new Intent(MainAqarzActivity.this, check_login.class));

                        } else {

//                            Intent intent = new Intent(MainAqarzActivity.this, AddAqarzActivity.class);
//                            Intent intent = new Intent(MainAqarzActivity.this, MyOrderRequstActivity.class);
                            Intent intent = new Intent(MainAqarzActivity.this, ManageOrderActivity.class);
                            intent.putExtra("id", "");
                            intent.putExtra("from", "home");

                            startActivity(intent);
                            gray_layout.setVisibility(View.GONE);
                            add_aqares_and_order_and_estate.setVisibility(View.GONE);
                        }
//                            alertDialog.dismiss();
                    }
                });
                RequstAqars.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!Settings.checkLogin()) {
//                            startActivity(new Intent(MainAqarzActivity.this, check_login.class));
                            startActivity(new Intent(MainAqarzActivity.this, LoginActivity.class));

                        } else {

                            Intent intent = new Intent(MainAqarzActivity.this, AqarzOrActivity.class);
                            intent.putExtra("id", "");
                            startActivity(intent);
                            gray_layout.setVisibility(View.GONE);
                            add_aqares_and_order_and_estate.setVisibility(View.GONE);
                        }
//                            alertDialog.dismiss();
                    }
                });
                rent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (Settings.checkLogin()) {
//
//
                            if (Hawk.contains("rent_layout")) {

                                Intent intent = new Intent(MainAqarzActivity.this, RentActivity.class);
                                intent.putExtra("id", "");
                                startActivity(intent);
                            } else {
                                Hawk.put("rent_layout", "rent_layout");
                                Intent intent = new Intent(MainAqarzActivity.this, RentShowActivity.class);
                                intent.putExtra("id", "");
                                startActivity(intent);
                            }
                            gray_layout.setVisibility(View.GONE);
                            add_aqares_and_order_and_estate.setVisibility(View.GONE);

                        } else {
                            startActivity(new Intent(MainAqarzActivity.this, LoginActivity.class));

//                            startActivity(new Intent(MainAqarzActivity.this, check_login.class));
                        }

                    }
                });


            }
        });

    }

    public void forceUpdate() {
        try {
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
            new ForceUpdateAsync(currentVersion, MainAqarzActivity.this).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onResume() {
        setLocale(MainAqarzActivity.this, Hawk.get("lang").toString());

        if (!Settings.checkLogin()) {

            text_2.setText(getResources().getString(R.string.MyOrder));
        } else {
            if (Settings.CheckIsAccountAqarzMan()) {
                text_2.setText(getResources().getString(R.string.orders2));

            } else {
                text_2.setText(getResources().getString(R.string.MyOrder));

            }
        }


        if (Settings.checkLogin()) {
            if (Settings.GetUser().getIs_employee().equals("1")) {


                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = layoutInflater.inflate(R.layout.is_emp_alert_dialog, null);
                ImageView close = popupView.findViewById(R.id.close);
                TextView title = popupView.findViewById(R.id.title);
                TextView yes = popupView.findViewById(R.id.yes);
                TextView no = popupView.findViewById(R.id.no);
                title.setText(getResources().getString(R.string.Are_you_an_employee) + " " + Settings.GetUser().getEmp().getName() + " ? ");

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

                        WebService.loading(MainAqarzActivity.this, true);

                        VolleyService mVolleyService = new VolleyService(mResultCallback, MainAqarzActivity.this);
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
                        WebService.loading(MainAqarzActivity.this, true);
                        VolleyService mVolleyService = new VolleyService(mResultCallback, MainAqarzActivity.this);
                        mVolleyService.postDataVolley("check_employe", WebService.check_employe, jsonObject);
                        alertDialog.dismiss();

                    }
                });
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainAqarzActivity.this);

//            alertDialog_country =
                builder.setView(popupView);


                alertDialog = builder.show();

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


            }

        }


        try {
            if (Settings.checkLogin()) {
                init_volley();
                VolleyService mVolleyService = new VolleyService(mResultCallback, MainAqarzActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                mVolleyService.getDataVolley("user", WebService.my_profile + "");


            }
            forceUpdate();

        } catch (Exception e) {

        }
        super.onResume();
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(MainAqarzActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    setLocale(MainAqarzActivity.this, Hawk.get("lang").toString());

                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");
//                        String next_page_url = response.getString("next_page_url");


                        if (requestType.equals("user")) {
                            JSONObject jsonObject = new JSONObject(data);


                            Hawk.put("user", data);

                        } else if (requestType.equals("check_employe")) {


                            init_volley();
                            VolleyService mVolleyService = new VolleyService(mResultCallback, MainAqarzActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                            mVolleyService.getDataVolley("user", WebService.my_profile + "");


                        } else if (requestType.equals("IAM_login")) {

                            Intent intent = new Intent(MainAqarzActivity.this, WebViewActivity.class);
                            intent.putExtra("data", data);
                            startActivityForResult(intent, 115);


                        } else if (requestType.equals("user__")) {
                            Hawk.put("user", data);


                        }


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(MainAqarzActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(MainAqarzActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(MainAqarzActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(MainAqarzActivity.this, false);

                WebService.Make_Toast_color(MainAqarzActivity.this, error, "error");


            }
        };


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if ((requestCode == 115)) {
            if (resultCode == Activity.RESULT_OK) {

                setLocale(MainAqarzActivity.this, Hawk.get("lang").toString());

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


                            WebService.loading(MainAqarzActivity.this, true);
                            VolleyService mVolleyService = new VolleyService(mResultCallback, MainAqarzActivity.this);
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

    public static void show_dialog() {
//        if (!BuildConfig.DEBUG)
//            Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(activity));
//        if (!BuildConfig.ENGLISH) {

        BottomSheetDialogFragment_forceUpdate bottomSheetDialogFragment_forceUpdate
                = new BottomSheetDialogFragment_forceUpdate("");
        bottomSheetDialogFragment_forceUpdate.show(fragmentManager, "");


    }

}
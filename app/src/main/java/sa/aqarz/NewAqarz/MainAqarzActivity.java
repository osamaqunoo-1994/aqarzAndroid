package sa.aqarz.NewAqarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.hawk.Hawk;

import sa.aqarz.Activity.AddAqarz.AddAqarzActivity;
import sa.aqarz.Activity.AllOrderActivity;
import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Activity.OprationNew.AqarzOrActivity;
import sa.aqarz.Activity.OprationNew.RentActivity;
import sa.aqarz.Activity.OprationNew.RentShowActivity;
import sa.aqarz.Activity.check_login;
import sa.aqarz.Fragment.MapsFragment;
import sa.aqarz.Fragment.mapsHome.MapsFragmentNew;
import sa.aqarz.Modules.ObjectFiltterOrder;
import sa.aqarz.Modules.Object_filtter;
import sa.aqarz.NewAqarz.AqqAqarz.AddAqarzStepsActivity;
import sa.aqarz.NewAqarz.Fragments.ChatFragment;
import sa.aqarz.NewAqarz.Fragments.HomeMapFragment;
import sa.aqarz.NewAqarz.Fragments.MoreFragment;
import sa.aqarz.NewAqarz.Fragments.OrderFragment;
import sa.aqarz.R;
import sa.aqarz.Settings.ForceUpdateAsync;
import sa.aqarz.Settings.Settings;

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

    public static LinearLayout lay_1, lay_2, lay_3, lay_4, lay_s;
    FloatingActionButton myFab;

    public static boolean first_time_ = false;

    static String click_tab = "home";

    static LinearLayout add_aqares_and_order_and_estate;
    LinearLayout gray_layout;
    LinearLayout addAqares;
    LinearLayout RequstAqars;
    LinearLayout rent;

    ImageView close_add;


    public static String type_order_main = "";
    public static String type_type_order_main = "";


    private static FragmentTransaction fragmentTransaction;
    private static FragmentManager fragmentManager;
    public static Activity activity;
    public static Object_filtter object_filtter = new Object_filtter();
    public static ObjectFiltterOrder objectFiltterOrder = new ObjectFiltterOrder();


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

        try {
            forceUpdate();

        } catch (Exception e) {

        }
    }


    public void set_contanier_fragments() {
        click_tab = "home";
        text_1.setTextColor(getResources().getColor(R.color.colorPrimary));
        text_2.setTextColor(getResources().getColor(R.color.color_un_active));
        text_3.setTextColor(getResources().getColor(R.color.color_un_active));
        text_4.setTextColor(getResources().getColor(R.color.color_un_active));
        text_s.setTextColor(getResources().getColor(R.color.color_un_active));

        image_1.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_ATOP);
        image_2.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
        image_3.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
        image_4.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);

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
                    startActivity(new Intent(MainAqarzActivity.this, check_login.class));

                } else {
                    click_tab = "order";

                    text_1.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_2.setTextColor(getResources().getColor(R.color.colorPrimary));
                    text_3.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_4.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_s.setTextColor(getResources().getColor(R.color.color_un_active));

                    image_1.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    image_2.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    image_3.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    image_4.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    click_tab = "order";

                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new OrderFragment());
                    fragmentTransaction.addToBackStack(null);
                    //  fragmentTransaction.commit();
                    fragmentTransaction.commitAllowingStateLoss();


                }

            }
        });
        lay_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Settings.checkLogin()) {
                    startActivity(new Intent(MainAqarzActivity.this, check_login.class));

                } else {
                    click_tab = "chat";

                    text_1.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_2.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_3.setTextColor(getResources().getColor(R.color.colorPrimary));
                    text_4.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_s.setTextColor(getResources().getColor(R.color.color_un_active));

                    image_1.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    image_2.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    image_3.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    image_4.setColorFilter(ContextCompat.getColor(MainAqarzActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new ChatFragment());
                    fragmentTransaction.addToBackStack(null);
                    //  fragmentTransaction.commit();
                    fragmentTransaction.commitAllowingStateLoss();
                }

            }
        });
        lay_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Settings.checkLogin()) {
                    startActivity(new Intent(MainAqarzActivity.this, check_login.class));

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


                if (!Settings.checkLogin()) {
                    startActivity(new Intent(MainAqarzActivity.this, check_login.class));

                } else {

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

                    addAqares.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Intent intent = new Intent(MainAqarzActivity.this, AddAqarzActivity.class);
                            Intent intent = new Intent(MainAqarzActivity.this, AddAqarzStepsActivity.class);
                            intent.putExtra("id", "");
                            startActivity(intent);
                            gray_layout.setVisibility(View.GONE);
                            add_aqares_and_order_and_estate.setVisibility(View.GONE);

//                            alertDialog.dismiss();
                        }
                    });
                    RequstAqars.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainAqarzActivity.this, AqarzOrActivity.class);
                            intent.putExtra("id", "");
                            startActivity(intent);
                            gray_layout.setVisibility(View.GONE);
                            add_aqares_and_order_and_estate.setVisibility(View.GONE);

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

                            } else {
                                startActivity(new Intent(MainAqarzActivity.this, check_login.class));
                            }
                            gray_layout.setVisibility(View.GONE);
                            add_aqares_and_order_and_estate.setVisibility(View.GONE);

                        }
                    });

                }


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

}
package sa.aqarz.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.hawk.Hawk;

import java.util.Locale;
import java.util.Set;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.Auth.RegisterActivity;
import sa.aqarz.Activity.OprationNew.RequestServiceActivity;
import sa.aqarz.Dialog.BottomSheetDialogFragment_MyEstate;
import sa.aqarz.Fragment.ChatFragment;
import sa.aqarz.Fragment.MapsFragment;
import sa.aqarz.Fragment.MoreFragment;
import sa.aqarz.Fragment.NotficationFragment;
import sa.aqarz.Fragment.OrdersFragment;
import sa.aqarz.Fragment.OrdersFragment_old;
import sa.aqarz.Fragment.SubscriptionsFragment;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.R;
import sa.aqarz.Settings.ForceUpdateAsync;
import sa.aqarz.Settings.LocaleUtils;
import sa.aqarz.Settings.Settings;

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

   public static LinearLayout lay_1, lay_2, lay_3, lay_4, lay_s;
    ShowcaseView showCaseView;

    FloatingActionButton myFab;
    public static FragmentManager ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        activity = this;
//        init();

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

        ft = ((FragmentActivity) activity).getSupportFragmentManager();

        myFab = (FloatingActionButton) findViewById(R.id.fab);

        myFab.setColorFilter(Color.WHITE);


        if (Hawk.contains("lang")) {


            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } else {

            Hawk.put("lang", LocaleUtils.getLanguage(this));

            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new MapsFragment());
        //  fragmentTransaction.commit();
        fragmentTransaction.commitAllowingStateLoss();

        try{
            forceUpdate();

        }catch (Exception e){

        }


        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!Settings.checkLogin()) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage(getResources().getString(R.string.you_are_not_login_please_login))
                            .setCancelable(false)
                            .setPositiveButton(getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                                intent.putExtra("from", "splash");
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton(getResources().getString(R.string.no), null)
                            .show();
                } else {

                    if (Settings.CheckIsCompleate()) {

                        Intent intent = new Intent(MainActivity.this, RequestServiceActivity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);
                    } else {
                        Settings.Dialog_not_compleate(MainActivity.this);
                    }

                }


            }
        });

        lay_s.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {


                text_s.setTextColor(getResources().getColor(R.color.colorPrimary));
                text_1.setTextColor(getResources().getColor(R.color.color_un_active));
                text_2.setTextColor(getResources().getColor(R.color.color_un_active));
                text_3.setTextColor(getResources().getColor(R.color.color_un_active));
                text_4.setTextColor(getResources().getColor(R.color.color_un_active));


                image_s.setSelected(true);
                image_1.setSelected(false);
                image_2.setSelected(false);
                image_3.setSelected(false);
                image_4.setSelected(false);
//                image_1.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_primery_), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_2.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_3.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_4.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);


                fragmentManager = getSupportFragmentManager();

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new NotficationFragment());
                //  fragmentTransaction.commit();
                fragmentTransaction.commitAllowingStateLoss();

            }
        });

        lay_1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {


                text_1.setTextColor(getResources().getColor(R.color.colorPrimary));
                text_2.setTextColor(getResources().getColor(R.color.color_un_active));
                text_3.setTextColor(getResources().getColor(R.color.color_un_active));
                text_4.setTextColor(getResources().getColor(R.color.color_un_active));
                text_s.setTextColor(getResources().getColor(R.color.color_un_active));


                image_1.setSelected(true);
                image_2.setSelected(false);
                image_3.setSelected(false);
                image_4.setSelected(false);
                image_s.setSelected(false);

//                image_1.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_primery_), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_2.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_3.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_4.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);


                fragmentManager = getSupportFragmentManager();

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new MapsFragment());
                //  fragmentTransaction.commit();
                fragmentTransaction.commitAllowingStateLoss();

            }
        });
        lay_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Settings.checkLogin()) {
                    text_1.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_2.setTextColor(getResources().getColor(R.color.colorPrimary));
                    text_3.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_4.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_s.setTextColor(getResources().getColor(R.color.color_un_active));


                    image_1.setSelected(false);
                    image_2.setSelected(true);
                    image_3.setSelected(false);
                    image_4.setSelected(false);
//
                    image_s.setSelected(false);

//                image_1.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_2.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_primery_), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_3.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_4.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);

                    fragmentManager = getSupportFragmentManager();

                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new OrdersFragment());
                    //  fragmentTransaction.commit();
                    fragmentTransaction.commitAllowingStateLoss();
                } else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage(getResources().getString(R.string.you_are_not_login_please_login))
                            .setCancelable(false)
                            .setPositiveButton(getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                                intent.putExtra("from", "splash");
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton(getResources().getString(R.string.no), null)
                            .show();
                }


            }
        });
        lay_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Settings.checkLogin()) {

                    text_1.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_2.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_3.setTextColor(getResources().getColor(R.color.colorPrimary));
                    text_4.setTextColor(getResources().getColor(R.color.color_un_active));
                    text_s.setTextColor(getResources().getColor(R.color.color_un_active));


                    image_1.setSelected(false);
                    image_2.setSelected(false);
                    image_3.setSelected(true);
                    image_4.setSelected(false);
//
                    image_s.setSelected(false);

//                image_1.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_2.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_3.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_primery_), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_4.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);


                    fragmentManager = getSupportFragmentManager();

                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, new ChatFragment());
                    //  fragmentTransaction.commit();
                    fragmentTransaction.commitAllowingStateLoss();
                } else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage(getResources().getString(R.string.you_are_not_login_please_login))
                            .setCancelable(false)
                            .setPositiveButton(getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                                intent.putExtra("from", "splash");
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton(getResources().getString(R.string.no), null)
                            .show();
                }
            }
        });
        lay_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                text_1.setTextColor(getResources().getColor(R.color.color_un_active));
                text_2.setTextColor(getResources().getColor(R.color.color_un_active));
                text_3.setTextColor(getResources().getColor(R.color.color_un_active));
                text_4.setTextColor(getResources().getColor(R.color.colorPrimary));
                text_s.setTextColor(getResources().getColor(R.color.color_un_active));


                image_1.setSelected(false);
                image_s.setSelected(false);
                image_2.setSelected(false);
                image_3.setSelected(false);
                image_4.setSelected(true);
//                image_1.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_2.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_3.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_4.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_primery_), android.graphics.PorterDuff.Mode.SRC_ATOP);
                fragmentManager = getSupportFragmentManager();

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new MoreFragment());
                //  fragmentTransaction.commit();
                fragmentTransaction.commitAllowingStateLoss();

            }
        });


//        LinearLayout yourView = findViewById(R.id.alla);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (yourView != null) {
//                yourView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);;
//            }
//        }

//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//                if (!Hawk.contains("showCaseView")) {
//
//                    Hawk.put("showCaseView", "showCaseView");
//
//
//                }
//
//
//            }
//        }, 100); // After 1 seconds
    }

//    public void init() {
//        bottomNav = findViewById(R.id.bottom_navigation);
//
//        if (bottomNav != null) {
//
//            // Select first menu item by default and show Fragment accordingly.
//            menu = bottomNav.getMenu();
//            //    bottomNavigationView.getMenu().getItem(0).setChecked(true);
//
////            selectFragment(menu.getItem(0));
//// here to know any fragment
//            selectFragment(menu.getItem(0));
//
//
//            // Set action to perform when any menu-item is selected.
//            bottomNav.setOnNavigationItemSelectedListener(
//                    new BottomNavigationView.OnNavigationItemSelectedListener() {
//                        @Override
//                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                            item.setChecked(true);
//                            selectFragment(item);
////                            selectFragment(item);
//                            return false;
//                        }
//                    });
//
//
//        }
//    }

//    protected void selectFragment(MenuItem item) {
//        item.setChecked(true);
//        switch (item.getItemId()) {
//            case R.id.home:
//                goToFragment(0);
//          /*      if (mUserSession.hasActiveSession())
//                    if (mUserSession.getCurrentUser().getUser().getMobileVerifiedAt()== null){
//                        Log.d("Mobile",mUserSession.getCurrentUser().getUser().getMobileVerifiedAt()+"empty");
//                        showDialog();}*/
//                break;
////
////            case R.id.subscriptions:
////                // if(mUserSession.hasActiveSession())
////                goToFragment(1);
//////                else
//////                    showAlert(MainActivity.this, getString(R.string.should_login));
////                break;
//
//            case R.id.orders:
//                goToFragment(1);
//                break;
//            case R.id.messages:
//                //       if(mUserSession.hasActiveSession())
//                goToFragment(2);
//            /*    else
//                    showAlert(MainActivity.this, getString(R.string.should_login));*/
//                break;
//            case R.id.more:
//                //       if(mUserSession.hasActiveSession())
//                goToFragment(3);
//            /*    else
//                    showAlert(MainActivity.this, getString(R.string.should_login));*/
//                break;
//
//        }
//    }

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

                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage(getResources().getString(R.string.you_are_not_login_please_login))
                            .setCancelable(false)
                            .setPositiveButton(getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                                intent.putExtra("from", "splash");
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton(getResources().getString(R.string.no), null)
                            .show();
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

                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage(getResources().getString(R.string.you_are_not_login_please_login))
                            .setCancelable(false)
                            .setPositiveButton(getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                                intent.putExtra("from", "splash");
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton(getResources().getString(R.string.no), null)
                            .show();
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


            image_1.setSelected(false);
            image_2.setSelected(true);
            image_3.setSelected(false);
            image_4.setSelected(false);
//
            image_s.setSelected(false);

//                image_1.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_2.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_primery_), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_3.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);
//                image_4.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.color_un_active), android.graphics.PorterDuff.Mode.SRC_ATOP);

            fragmentManager = ft;

            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new OrdersFragment());
            //  fragmentTransaction.commit();
            fragmentTransaction.commitAllowingStateLoss();
        } else {
            new AlertDialog.Builder(activity)
                    .setMessage(activity.getResources().getString(R.string.you_are_not_login_please_login))
                    .setCancelable(false)
                    .setPositiveButton(activity.getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            Intent intent = new Intent(activity, LoginActivity.class);
//                                intent.putExtra("from", "splash");
                            activity.startActivity(intent);

                        }
                    })
                    .setNegativeButton(activity.getResources().getString(R.string.no), null)
                    .show();
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
        PackageManager packageManager = this.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String currentVersion = packageInfo.versionName;

        System.out.println("currentVersion"+currentVersion);
        new ForceUpdateAsync(currentVersion, MainActivity.this).execute();
    }
}
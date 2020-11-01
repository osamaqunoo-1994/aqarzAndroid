package aqarz.revival.sa.aqarz.Activity;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Set;

import aqarz.revival.sa.aqarz.Activity.Auth.LoginActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.RegisterActivity;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_MyEstate;
import aqarz.revival.sa.aqarz.Fragment.ChatFragment;
import aqarz.revival.sa.aqarz.Fragment.MapsFragment;
import aqarz.revival.sa.aqarz.Fragment.MoreFragment;
import aqarz.revival.sa.aqarz.Fragment.OrdersFragment;
import aqarz.revival.sa.aqarz.Fragment.SubscriptionsFragment;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    private Menu menu;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    public static Activity activity;
    public static BottomSheetDialogFragment_MyEstate bottomSheetDialogFragment_myEstate;
    static BottomSheetDialog bottomSheerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        init();

//        LinearLayout yourView = findViewById(R.id.alla);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (yourView != null) {
//                yourView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);;
//            }
//        }


    }

    public void init() {
        bottomNav = findViewById(R.id.bottom_navigation);

        if (bottomNav != null) {

            // Select first menu item by default and show Fragment accordingly.
            menu = bottomNav.getMenu();
            //    bottomNavigationView.getMenu().getItem(0).setChecked(true);

//            selectFragment(menu.getItem(0));
// here to know any fragment
            selectFragment(menu.getItem(0));


            // Set action to perform when any menu-item is selected.
            bottomNav.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                            item.setChecked(true);
                            selectFragment(item);
//                            selectFragment(item);
                            return false;
                        }
                    });


        }
    }

    protected void selectFragment(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.home:
                goToFragment(0);
          /*      if (mUserSession.hasActiveSession())
                    if (mUserSession.getCurrentUser().getUser().getMobileVerifiedAt()== null){
                        Log.d("Mobile",mUserSession.getCurrentUser().getUser().getMobileVerifiedAt()+"empty");
                        showDialog();}*/
                break;
//
//            case R.id.subscriptions:
//                // if(mUserSession.hasActiveSession())
//                goToFragment(1);
////                else
////                    showAlert(MainActivity.this, getString(R.string.should_login));
//                break;

            case R.id.orders:
                goToFragment(1);
                break;
            case R.id.messages:
                //       if(mUserSession.hasActiveSession())
                goToFragment(2);
            /*    else
                    showAlert(MainActivity.this, getString(R.string.should_login));*/
                break;
            case R.id.more:
                //       if(mUserSession.hasActiveSession())
                goToFragment(3);
            /*    else
                    showAlert(MainActivity.this, getString(R.string.should_login));*/
                break;

        }
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
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.container, new ChatFragment());
//                //    fragmentTransaction.commit();
//                fragmentTransaction.commitAllowingStateLoss();
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.container, new MessagesFragment());
//                //    fragmentTransaction.commit();
//                fragmentTransaction.commitAllowingStateLoss();

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

}
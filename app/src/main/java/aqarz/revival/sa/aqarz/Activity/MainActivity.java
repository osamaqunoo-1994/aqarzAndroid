package aqarz.revival.sa.aqarz.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Set;

import aqarz.revival.sa.aqarz.Activity.Auth.LoginActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.RegisterActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}
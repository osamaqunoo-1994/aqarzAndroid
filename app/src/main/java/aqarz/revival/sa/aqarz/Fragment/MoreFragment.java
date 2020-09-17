package aqarz.revival.sa.aqarz.Fragment;
/**
 * Created by osama on 10/8/2017.
 */


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Activity.Auth.LoginActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.NewPasswordActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.RegisterActivity;
import aqarz.revival.sa.aqarz.Activity.MainActivity;
import aqarz.revival.sa.aqarz.Activity.SplashScreenActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_map;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Type_in_map;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.LocaleUtils;
import aqarz.revival.sa.aqarz.Settings.Settings;

import static android.app.Activity.RESULT_OK;


public class MoreFragment extends Fragment {

    LinearLayout info_;
    LinearLayout changePassword;
    Button loginButton;
    Button signup;


    LinearLayout with_login;
    LinearLayout no_login;
    LinearLayout logout;
    LinearLayout langauge;

    TextView user_name;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_more, container, false);

        init(v);
        return v;
    }


    public void init(View v) {
        info_ = v.findViewById(R.id.info_);
        loginButton = v.findViewById(R.id.loginButton);
        signup = v.findViewById(R.id.signup);
        changePassword = v.findViewById(R.id.changePassword);
        with_login = v.findViewById(R.id.with_login);
        no_login = v.findViewById(R.id.no_login);
        user_name = v.findViewById(R.id.user_name);
        logout = v.findViewById(R.id.logout);
        langauge = v.findViewById(R.id.langauge);


        info_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyProfileInformationActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewPasswordActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hawk.put("user", "");
                Hawk.put("api_token", "");
//                Hawk.put("user", "");


                check_user_login();

            }
        });

        langauge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setMessage(getResources().getString(R.string.are_you_change_lang_post))
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                if (Hawk.get("lang").toString().equals("ar")) {

                                    Hawk.put("lang", "en");

                                    LocaleUtils.setLocale(getContext(), "en");
//
                                    getActivity().finishAffinity();

                                    Intent intent = new Intent(getContext(), SplashScreenActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);


                                } else {

                                    Hawk.put("lang", "ar");

                                    LocaleUtils.setLocale(getContext(), "ar");
//
                                    getActivity().finishAffinity();

                                    Intent intent = new Intent(getContext(), SplashScreenActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }


                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), null)
                        .show();

            }
        });


        check_user_login();


    }


    public static MoreFragment newInstance(String text) {

        MoreFragment f = new MoreFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }


    @Override
    public void onResume() {


        check_user_login();

        super.onResume();
    }

    public void check_user_login() {
        try {


            if (Hawk.contains("user")) {

                if (Hawk.get("user").toString().equals("")) {

                    no_login.setVisibility(View.VISIBLE);
                    with_login.setVisibility(View.GONE);
                    changePassword.setVisibility(View.GONE);

                } else {

                    no_login.setVisibility(View.GONE);
                    with_login.setVisibility(View.VISIBLE);
                    changePassword.setVisibility(View.VISIBLE);
                    user_name.setText(Settings.GetUser().getName() + "");

                }
            } else {

                no_login.setVisibility(View.VISIBLE);
                with_login.setVisibility(View.GONE);
                changePassword.setVisibility(View.GONE);

            }


        } catch (Exception e) {

        }
    }
}
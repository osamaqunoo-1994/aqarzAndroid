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
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Activity.Auth.LoginActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.NewPasswordActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.RegisterActivity;
import aqarz.revival.sa.aqarz.Activity.ContactUsActivity;
import aqarz.revival.sa.aqarz.Activity.MainActivity;
import aqarz.revival.sa.aqarz.Activity.PrivecyActivity;
import aqarz.revival.sa.aqarz.Activity.SplashScreenActivity;
import aqarz.revival.sa.aqarz.Activity.TermsActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_map;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Type_in_map;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.LocaleUtils;
import aqarz.revival.sa.aqarz.Settings.Settings;
import de.hdodenhof.circleimageview.CircleImageView;

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
    LinearLayout privecy;
    LinearLayout terms;

    TextView user_name;
    TextView language_text;

    Button contact_us;


    Button email_us;
    CircleImageView image_profile;

    ImageView facebook, twiter, instagram, linked;

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
        language_text = v.findViewById(R.id.language_text);
        privecy = v.findViewById(R.id.privecy);
        terms = v.findViewById(R.id.terms);
        contact_us = v.findViewById(R.id.contact_us);
        email_us = v.findViewById(R.id.email_us);
        facebook = v.findViewById(R.id.facebook);
        twiter = v.findViewById(R.id.twiter);
        instagram = v.findViewById(R.id.instagram);
        linked = v.findViewById(R.id.linked);
        image_profile = v.findViewById(R.id.image_profile);


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
        contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ContactUsActivity.class);
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

        privecy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PrivecyActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Settings.getSettings().getFace_book()));
                startActivity(browserIntent);
            }
        });
        twiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Settings.getSettings().getTwitter()));
                startActivity(browserIntent);
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Settings.getSettings().getInsta()));
                startActivity(browserIntent);
            }
        });
        linked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Settings.getSettings().getLinked()));
                startActivity(browserIntent);
            }
        });
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TermsActivity.class);
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
        email_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "abc@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
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


        if (Hawk.get("lang").toString().equals("ar")) {

            language_text.setText(getResources().getString(R.string.Arabic_lang));
        } else {

            language_text.setText(getResources().getString(R.string.English_lang));

        }


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

                    Picasso.get().load(Settings.GetUser().getLogo()).into(image_profile);


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
package sa.aqarz.Fragment;
/**
 * Created by osama on 10/8/2017.
 */


import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

import sa.aqarz.Activity.AqarzProfileActivity;
import sa.aqarz.Activity.Auth.ForgotPasswordActivity;
import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.Auth.NewPasswordActivity;
import sa.aqarz.Activity.Auth.NewSiginUpActivity;
import sa.aqarz.Activity.Auth.RegisterActivity;
import sa.aqarz.Activity.ContactUsActivity;
import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Activity.DetailsAqarzManActivity;
import sa.aqarz.Activity.FavoriteActivity;
import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Activity.MyOrderActivity;
import sa.aqarz.Activity.PrivecyActivity;
import sa.aqarz.Activity.SplashScreenActivity;
import sa.aqarz.Activity.TermsActivity;
import sa.aqarz.Activity.profile.AllclintActivity;
import sa.aqarz.Activity.profile.MyOffersActivity;
import sa.aqarz.Activity.profile.MyProfileActivity;
import sa.aqarz.Activity.profile.OtherProfileActivity;
import sa.aqarz.Adapter.RecyclerView_All_Opration_in_map;
import sa.aqarz.Adapter.RecyclerView_All_Type_in_map;
import sa.aqarz.Dialog.BottomSheetDialogFragmen_restPassword;
import sa.aqarz.Dialog.BottomSheetDialogFragment_QR;
import sa.aqarz.Modules.OprationModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.LocaleUtils;
import sa.aqarz.Settings.Settings;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class MoreFragment extends Fragment {

    LinearLayout info_;
    LinearLayout changePassword;
    TextView loginButton;
    TextView signup;


    LinearLayout with_login;
    LinearLayout no_login;
    LinearLayout logout;
    LinearLayout langauge;
    LinearLayout privecy;
    LinearLayout terms;
    LinearLayout all_aqarez_man;
    LinearLayout Technical_support;
    LinearLayout MyOrder;

    TextView user_name;
    TextView language_text;


    LinearLayout contact_us;

    LinearLayout Favorites;
    LinearLayout Aqarez;
    LinearLayout clints;
    LinearLayout profile;
    Button email_us;
    CircleImageView image_profile;

    ImageView facebook, twiter, instagram, linked;
    TextView not_compleate;
    ImageView qr_code;


    TextView title_info;
    TextView version;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_more, container, false);
        version = v.findViewById(R.id.version);

        try {
            PackageManager packageManager = getActivity().getPackageManager();
            PackageInfo packageInfo = null;
            try {
                packageInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            String currentVersion = packageInfo.versionName;


            version.setText(" اصدار التطبيق " + currentVersion);

        } catch (Exception e) {

        }
        init(v);
        return v;
    }


    public void init(View v) {
        info_ = v.findViewById(R.id.info_);
        loginButton = v.findViewById(R.id.loginButton);
        MyOrder = v.findViewById(R.id.MyOrder);
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
        not_compleate = v.findViewById(R.id.not_compleate);
        Favorites = v.findViewById(R.id.Favorites);
        clints = v.findViewById(R.id.clints);
        Aqarez = v.findViewById(R.id.Aqarez);
        profile = v.findViewById(R.id.profile);
        qr_code = v.findViewById(R.id.qr_code);
        all_aqarez_man = v.findViewById(R.id.all_aqarez_man);
        Technical_support = v.findViewById(R.id.Technical_support);
        title_info = v.findViewById(R.id.title_info);


        info_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("***********" + Settings.GetUser().getType());
                if (Settings.CheckIsAccountAqarzMan()) {
//                    Intent intent = new Intent(getContext(), DetailsAqarzManActivity.class);
//                    Intent intent = new Intent(getContext(), AqarzProfileActivity.class);
                    Intent intent = new Intent(getContext(), MyProfileActivity.class);
//                                intent.putExtra("from", "splash");
                    startActivity(intent);
//                    getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                } else {
                    Intent intent = new Intent(getContext(), MyProfileInformationActivity.class);
//                                intent.putExtra("from", "splash");
                    startActivity(intent);
//                    getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                }
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), NewPasswordActivity.class);
////                                intent.putExtra("from", "splash");
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);


                BottomSheetDialogFragmen_restPassword bottomSheetDialogFragmen_restPassword = new BottomSheetDialogFragmen_restPassword("");
                bottomSheetDialogFragmen_restPassword.show(getChildFragmentManager(), "");

//                Intent intent = new Intent(getContext(), ForgotPasswordActivity.class);
////                                intent.putExtra("from", "splash");
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

//                finish();
            }
        });
        contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ContactUsActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        Favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FavoriteActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), OtherProfileActivity.class);
                intent.putExtra("id", Settings.GetUser().getId() + "");
                startActivity(intent);

            }
        });
        Aqarez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyOffersActivity.class);
                intent.putExtra("id_user", "--");
                startActivity(intent);


            }
        });

        privecy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PrivecyActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        all_aqarez_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AllclintActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

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
//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        MyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyOrderActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        Technical_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url = "https://api.whatsapp.com/send?phone=" + "966532576667";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);


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
                    Toast.makeText(getContext(), "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewSiginUpActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

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
                new AlertDialog.Builder(getContext())
                        .setMessage(getResources().getString(R.string.are_you_wantlog))
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                Hawk.put("user", "");
                                Hawk.put("api_token", "");
//                Hawk.put("user", "");


                                check_user_login();


                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), null)
                        .show();


            }
        });
        qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialogFragment_QR bottomSheetDialogFragment_qr = new BottomSheetDialogFragment_QR(Settings.GetUser().getLink() + "", Settings.GetUser().getLogo() + "");
                bottomSheetDialogFragment_qr.show(getActivity().getSupportFragmentManager(), "");

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
                    logout.setVisibility(View.GONE);
                    MyOrder.setVisibility(View.GONE);
                    title_info.setVisibility(View.GONE);
                    Favorites.setVisibility(View.GONE);
                    clints.setVisibility(View.GONE);
                    Aqarez.setVisibility(View.GONE);
                    profile.setVisibility(View.GONE);

                } else {


                    no_login.setVisibility(View.GONE);
                    with_login.setVisibility(View.VISIBLE);
                    changePassword.setVisibility(View.VISIBLE);
                    MyOrder.setVisibility(View.VISIBLE);
                    title_info.setVisibility(View.VISIBLE);
                    Favorites.setVisibility(View.VISIBLE);
                    clints.setVisibility(View.VISIBLE);
                    Aqarez.setVisibility(View.VISIBLE);
                    profile.setVisibility(View.VISIBLE);

                    logout.setVisibility(View.VISIBLE);

                    if (Settings.CheckIsCompleate()) {
                        user_name.setText(Settings.GetUser().getName() + "");
                        Glide.with(getActivity()).load(Settings.GetUser().getLogo()).into(image_profile);
                        not_compleate.setVisibility(View.GONE);

                    } else {
                        user_name.setText("..........");
                        not_compleate.setVisibility(View.VISIBLE);
                    }


                }
            } else {

                no_login.setVisibility(View.VISIBLE);
                with_login.setVisibility(View.GONE);
                changePassword.setVisibility(View.GONE);
                logout.setVisibility(View.GONE);
                MyOrder.setVisibility(View.GONE);
                Favorites.setVisibility(View.GONE);
                clints.setVisibility(View.GONE);
                profile.setVisibility(View.GONE);
                Aqarez.setVisibility(View.GONE);
                title_info.setVisibility(View.GONE);

            }


        } catch (Exception e) {

        }
    }


}
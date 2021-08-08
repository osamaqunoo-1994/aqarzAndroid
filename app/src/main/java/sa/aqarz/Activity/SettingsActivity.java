package sa.aqarz.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.Auth.NewSiginUpActivity;
import sa.aqarz.Activity.Auth.RegisterActivity;
import sa.aqarz.Dialog.BottomSheetDialogFragmen_restPassword;
import sa.aqarz.R;
import sa.aqarz.Settings.LocaleUtils;
import sa.aqarz.Settings.Settings;

public class SettingsActivity extends AppCompatActivity {
    LinearLayout terms;
    LinearLayout changePassword;
    LinearLayout langauge;
    LinearLayout logout;
    LinearLayout no_login;

    TextView loginButton;
    TextView signup;

    ImageView back;
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        terms = findViewById(R.id.terms);
        changePassword = findViewById(R.id.changePassword);
        langauge = findViewById(R.id.langauge);
        logout = findViewById(R.id.logout);
        no_login = findViewById(R.id.no_login);
        loginButton = findViewById(R.id.loginButton);
        back = findViewById(R.id.back);
        signup = findViewById(R.id.signup);
        version = findViewById(R.id.version);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, TermsActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, NewSiginUpActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SettingsActivity.this)
                        .setMessage(getResources().getString(R.string.are_you_wantlog))
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                Hawk.put("user", "");
                                Hawk.put("api_token", "");
//                Hawk.put("user", "");
                                finish();

//                                check_user_login();


                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), null)
                        .show();


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
                bottomSheetDialogFragmen_restPassword.show(getSupportFragmentManager(), "");

//                Intent intent = new Intent(getContext(), ForgotPasswordActivity.class);
////                                intent.putExtra("from", "splash");
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

//                finish();
            }
        });
        langauge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SettingsActivity.this)
                        .setMessage(getResources().getString(R.string.are_you_change_lang_post))
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                if (Hawk.get("lang").toString().equals("ar")) {

                                    Hawk.put("lang", "en");

                                    LocaleUtils.setLocale(SettingsActivity.this, "en");
//
                                    finishAffinity();

                                    Intent intent = new Intent(SettingsActivity.this, SplashScreenActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);


                                } else {

                                    Hawk.put("lang", "ar");

                                    LocaleUtils.setLocale(SettingsActivity.this, "ar");
//
                                    finishAffinity();

                                    Intent intent = new Intent(SettingsActivity.this, SplashScreenActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);

                                }


                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), null)
                        .show();

            }
        });
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = null;
            try {
                packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            String currentVersion = packageInfo.versionName;


            version.setText(" اصدار التطبيق " + currentVersion);

        } catch (Exception e) {

        }

    }

    @Override
    protected void onResume() {
        if (Settings.checkLogin()) {
            changePassword.setVisibility(View.VISIBLE);
            logout.setVisibility(View.VISIBLE);
            no_login.setVisibility(View.GONE);

        } else {
            changePassword.setVisibility(View.GONE);
            logout.setVisibility(View.GONE);
            no_login.setVisibility(View.VISIBLE);
        }


        super.onResume();
    }
}
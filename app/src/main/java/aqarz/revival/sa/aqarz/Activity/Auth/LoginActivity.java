package aqarz.revival.sa.aqarz.Activity.Auth;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import aqarz.revival.sa.aqarz.Activity.MainActivity;
import aqarz.revival.sa.aqarz.Activity.SplashScreenActivity;
import aqarz.revival.sa.aqarz.R;


public class LoginActivity extends AppCompatActivity {
    EditText phone_ed;
    EditText password;

    AppCompatButton new_account;
    AppCompatButton Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();


        AppCompatImageView yourView = findViewById(R.id.immage);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (yourView != null) {
                yourView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }


    }

    public void init() {
        phone_ed = findViewById(R.id.phone_ed);
        password = findViewById(R.id.password);
        new_account = findViewById(R.id.new_account);
        Login = findViewById(R.id.Login);

        phone_ed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    phone_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));

                    Drawable img = getResources().getDrawable(R.drawable.ic_phone_color);

                    phone_ed.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);

                } else {
                    phone_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
                    Drawable img = getResources().getDrawable(R.drawable.ic_phone);

                    phone_ed.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);

                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    password.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));
                    password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_look), null, null, null);

                } else {
                    password.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
                    password.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_look_color), null, null, null);

                }
            }
        });

        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);


            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);


            }
        });
    }

}
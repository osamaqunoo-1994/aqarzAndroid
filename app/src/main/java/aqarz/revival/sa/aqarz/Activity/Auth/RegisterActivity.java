package aqarz.revival.sa.aqarz.Activity.Auth;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import aqarz.revival.sa.aqarz.R;

public class RegisterActivity extends AppCompatActivity {
    EditText name_ed;
    EditText email_ed;
    EditText phone_ed;
    EditText password;
    AppCompatButton sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();


        AppCompatImageView yourView = findViewById(R.id.immage);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (yourView != null) {
                yourView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

    }

    public void init() {
        name_ed = findViewById(R.id.name_ed);
        email_ed = findViewById(R.id.email_ed);
        phone_ed = findViewById(R.id.phone_ed);
        password = findViewById(R.id.password);
        sign_up = findViewById(R.id.sign_up);

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
        name_ed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    name_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));
                    name_ed.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_user_name), null, null, null);

                } else {
                    name_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
                    name_ed.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_user_name_color), null, null, null);

                }
            }
        });
        email_ed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    email_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background_color));
                    email_ed.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_user_name), null, null, null);

                } else {
                    email_ed.setBackgroundDrawable(getDrawable(R.drawable.edit_text_background));
                    email_ed.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_email_color), null, null, null);

                }
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, ConfirmationActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);


            }
        });

    }

}
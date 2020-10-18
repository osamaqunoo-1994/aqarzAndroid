package aqarz.revival.sa.aqarz.Activity.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import aqarz.revival.sa.aqarz.R;

public class NewSiginUpActivity extends AppCompatActivity {
    EditText phone_ed;
    TextView dont_have_account;
    CheckBox chechbox;
    AppCompatButton register;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sigin_up);
        back = findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
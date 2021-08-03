package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Set;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;

public class check_login extends AppCompatActivity {
    TextView login;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_login);
        back = findViewById(R.id.back);
        login = findViewById(R.id.login);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(check_login.this, LoginActivity.class);
                startActivity(intent);


            }
        });


    }

    @Override
    protected void onResume() {

        if (Settings.checkLogin()) {
            finish();
        }

        super.onResume();
    }
}
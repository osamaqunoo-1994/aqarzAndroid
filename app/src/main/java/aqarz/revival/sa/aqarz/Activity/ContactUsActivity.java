package aqarz.revival.sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;

public class ContactUsActivity extends AppCompatActivity {
    TextView email_info;
    TextView phone;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        email_info = findViewById(R.id.email_info);
        phone = findViewById(R.id.phone);
        back = findViewById(R.id.back);


        email_info.setText(Settings.getSettings().getEmail());
        phone.setText(Settings.getSettings().getMobile());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
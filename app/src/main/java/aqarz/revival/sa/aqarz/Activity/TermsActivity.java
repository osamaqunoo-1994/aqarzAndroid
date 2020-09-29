package aqarz.revival.sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;

public class TermsActivity extends AppCompatActivity {
    TextView text;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        text = findViewById(R.id.text);
        back = findViewById(R.id.back);


        text.setText(Settings.getSettings().getPrivacy_policy());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayout lay_ = findViewById(R.id.lay_);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (lay_ != null) {
                lay_.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

    }
}
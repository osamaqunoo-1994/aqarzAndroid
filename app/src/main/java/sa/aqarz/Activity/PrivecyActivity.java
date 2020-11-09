package sa.aqarz.Activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import sa.aqarz.R;

public class PrivecyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        LinearLayout lay_ = findViewById(R.id.lay_);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (lay_ != null) {
                lay_.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }
}
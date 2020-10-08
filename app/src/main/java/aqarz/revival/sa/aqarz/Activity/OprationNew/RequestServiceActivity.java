package aqarz.revival.sa.aqarz.Activity.OprationNew;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import aqarz.revival.sa.aqarz.R;

public class RequestServiceActivity extends AppCompatActivity {
    LinearLayout rent_layout;
    LinearLayout aqar_layout;
    LinearLayout rate_layout;
    LinearLayout fini_layout;

    TextView text_a1;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_service);
        rent_layout = findViewById(R.id.rent_layout);
        aqar_layout = findViewById(R.id.aqar_layout);
        rate_layout = findViewById(R.id.rate_layout);
        fini_layout = findViewById(R.id.fini_layout);
        back = findViewById(R.id.back);
        text_a1 = findViewById(R.id.text_a1);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(RequestServiceActivity.this, RentActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);


            }
        }); fini_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(RequestServiceActivity.this, FinanceActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);


            }
        }); rate_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(RequestServiceActivity.this, RateActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);


            }
        });aqar_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(RequestServiceActivity.this, AqarzOrActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);


            }
        });

//        rent_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
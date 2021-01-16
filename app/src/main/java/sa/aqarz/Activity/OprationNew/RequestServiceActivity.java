package sa.aqarz.Activity.OprationNew;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.orhanobut.hawk.Hawk;

import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import sa.aqarz.R;

public class RequestServiceActivity extends AppCompatActivity {
    LinearLayout rent_layout;
    LinearLayout aqar_layout;
    LinearLayout rate_layout;
    LinearLayout fini_layout;
    LinearLayout AddAqareaz;
    LinearLayout Shoppingrequest_layout;
    LinearLayout Real_Estate_orders_layout;

    TextView text_a1;
    ImageView back;


    ShowcaseView showCaseView;
    ShowcaseView showCaseView1;


    static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_service);
        rent_layout = findViewById(R.id.rent_layout);
        aqar_layout = findViewById(R.id.aqar_layout);
        rate_layout = findViewById(R.id.rate_layout);
        fini_layout = findViewById(R.id.fini_layout);
        AddAqareaz = findViewById(R.id.AddAqareaz);
        Shoppingrequest_layout = findViewById(R.id.Shoppingrequest_layout);
        Real_Estate_orders_layout = findViewById(R.id.Real_Estate_orders_layout);
        back = findViewById(R.id.back);
        text_a1 = findViewById(R.id.text_a1);
        activity = this;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Hawk.contains("rent_layout")) {

                    Intent intent = new Intent(RequestServiceActivity.this, RentActivity.class);
                    intent.putExtra("id", "");
                    startActivity(intent);
                } else {
                    Hawk.put("rent_layout", "rent_layout");
                    Intent intent = new Intent(RequestServiceActivity.this, RentShowActivity.class);
                    intent.putExtra("id", "");
                    startActivity(intent);
                }


            }
        });
        Shoppingrequest_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.go_to_order();

                finish();

            }
        });
        Real_Estate_orders_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.go_to_order();
                finish();


            }
        });
        fini_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(RequestServiceActivity.this, FinanceActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);


            }
        });
        AddAqareaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(RequestServiceActivity.this, AddAqarsActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);


            }
        });
        rate_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(RequestServiceActivity.this, RateActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);


            }
        });
        aqar_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(RequestServiceActivity.this, AqarzOrActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);


            }
        });

//        rent_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                showCaseView = new ShowcaseView.Builder(RequestServiceActivity.this)
                        .setTarget(new ViewTarget(R.id.aqar_layout, RequestServiceActivity.this))
                        .setContentTitle(getResources().getString(R.string.requestAqarezhzTitle_show))
                        .setContentText(getResources().getString(R.string.requestAqarezhzdes_show))

                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showCaseView.hide();

                                showCaseView1 = new ShowcaseView.Builder(RequestServiceActivity.this)
                                        .setTarget(new ViewTarget(R.id.rent_layout, RequestServiceActivity.this))
                                        .setContentTitle(getResources().getString(R.string.finincezhzTitle_show))
                                        .setContentText(getResources().getString(R.string.finincezhzdes_show))


                                        .hideOnTouchOutside()

                                        .setStyle(R.style.CustomShowcaseTheme2)
                                        .build();
                            }
                        })


                        .setStyle(R.style.CustomShowcaseTheme2)
                        .build();


            }
        }, 100); // After 1 seconds


    }

    public static void close() {
        activity.finish();
    }
}
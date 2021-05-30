package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import java.util.Locale;

import sa.aqarz.R;
import sa.aqarz.Settings.LocaleUtils;
import sa.aqarz.Settings.Settings;

public class AllOrderActivity extends AppCompatActivity {
    TextView premium;
    ImageView favorit_button;
    ImageView more_filtter_image;

    TextView Myoffer_number;
    TextView today_number;
    TextView AllOrder_number;

    TextView AllOrder;
    TextView today;
    TextView Myoffer;

    TextView searcha_btn;


    EditText search_text;

    TextView status_1;
    TextView status_2;
    TextView status_3;

    RecyclerView orders_rec;
    ProgressBar progress;
    LinearLayout nodata_vis;
    LinearLayout not_premium;


    static String offer_status = "customer_accepted ";
    static String type_requst = "today";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);


        premium = findViewById(R.id.premium);
        status_1 = findViewById(R.id.status_1);
        status_2 = findViewById(R.id.status_2);
        status_3 = findViewById(R.id.status_3);

        orders_rec = findViewById(R.id.orders_rec);
        nodata_vis = findViewById(R.id.nodata_vis);
        not_premium = findViewById(R.id.not_premium);
        favorit_button = findViewById(R.id.favorit_button);

        search_text = findViewById(R.id.search_text);
        more_filtter_image = findViewById(R.id.more_filtter_image);


        AllOrder = findViewById(R.id.AllOrder);
        today = findViewById(R.id.today);
        Myoffer = findViewById(R.id.Myoffer);

        Myoffer_number = findViewById(R.id.Myoffer_number);
        today_number = findViewById(R.id.today_number);
        AllOrder_number = findViewById(R.id.AllOrder_number);


        progress = findViewById(R.id.progress);

        try {

            if (Hawk.contains("lang")) {


                Locale locale = new Locale(Hawk.get("lang").toString());
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
            } else {

                Hawk.put("lang", LocaleUtils.getLanguage(AllOrderActivity.this));

                Locale locale = new Locale(Hawk.get("lang").toString());
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().equals("1")) {
            premium.setVisibility(View.VISIBLE);
//            offer.setVisibility(View.VISIBLE);
            not_premium.setVisibility(View.GONE);

        } else {

            premium.setVisibility(View.GONE);
//            offer.setVisibility(View.GONE);
            not_premium.setVisibility(View.VISIBLE);
        }


        setdata();
        action_button();

    }

    public void setdata() {
        status_1.setVisibility(View.GONE);
        status_2.setVisibility(View.GONE);
        status_3.setVisibility(View.GONE);


        AllOrder_number.setText(Settings.getSettings().getAllRequestFund() + "");
        today_number.setText(Settings.getSettings().getRequestFund() + "");
        Myoffer_number.setText(Settings.getSettings().getMyRequestFundOffer() + "");


    }

    public void action_button() {

        status_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                status_1.setBackground(getResources().getDrawable(R.drawable.background_b2select));
                status_1.setTextColor(getResources().getColor(R.color.white));


                status_2.setBackground(null);
                status_3.setBackground(null);
                status_2.setTextColor(getResources().getColor(R.color.black));
                status_3.setTextColor(getResources().getColor(R.color.black));

                offer_status = "customer_accepted";
            }
        });
        status_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                status_2.setBackground(getResources().getDrawable(R.drawable.background_b2select));
                status_2.setTextColor(getResources().getColor(R.color.white));


                status_1.setBackground(null);
                status_3.setBackground(null);
                status_1.setTextColor(getResources().getColor(R.color.black));
                status_3.setTextColor(getResources().getColor(R.color.black));

                offer_status = "sending_code";

            }
        });
        status_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                status_3.setBackground(getResources().getDrawable(R.drawable.background_b2select));
                status_3.setTextColor(getResources().getColor(R.color.white));


                status_2.setBackground(null);
                status_1.setBackground(null);
                status_2.setTextColor(getResources().getColor(R.color.black));
                status_1.setTextColor(getResources().getColor(R.color.black));

                offer_status = "new";

            }
        });
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllOrder.setBackground(null);
                today.setBackground(getResources().getDrawable(R.drawable.button_login));
                Myoffer.setBackground(null);


                status_1.setVisibility(View.GONE);
                status_2.setVisibility(View.GONE);
                status_3.setVisibility(View.GONE);


//                Myoffer_number.setBackground(getResources().getDrawable(R.drawable.mash));
//                today_number.setBackground(getResources().getDrawable(R.drawable.circle_ss));
//                AllOrder_number.setBackground(getResources().getDrawable(R.drawable.mash));


                AllOrder.setTextColor(getResources().getColor(R.color.textColor));
                today.setTextColor(getResources().getColor(R.color.white));
                Myoffer.setTextColor(getResources().getColor(R.color.textColor));

//                Myoffer_number.setTextColor(getResources().getColor(R.color.textColor));
//                today_number.setTextColor(getResources().getColor(R.color.white));
//                AllOrder_number.setTextColor(getResources().getColor(R.color.textColor));


                type_requst = "today";

            }
        });


        Myoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllOrder.setBackground(null);
                today.setBackground(null);
                Myoffer.setBackground(getResources().getDrawable(R.drawable.button_login));


                status_1.setVisibility(View.VISIBLE);
                status_2.setVisibility(View.VISIBLE);
                status_3.setVisibility(View.VISIBLE);
//                Myoffer_number.setBackground(getResources().getDrawable(R.drawable.circle_ss));
//                today_number.setBackground(getResources().getDrawable(R.drawable.mash));
//                AllOrder_number.setBackground(getResources().getDrawable(R.drawable.mash));


                AllOrder.setTextColor(getResources().getColor(R.color.textColor));
                today.setTextColor(getResources().getColor(R.color.textColor));
                Myoffer.setTextColor(getResources().getColor(R.color.white));


//                Myoffer_number.setTextColor(getResources().getColor(R.color.white));
//                today_number.setTextColor(getResources().getColor(R.color.textColor));
//                AllOrder_number.setTextColor(getResources().getColor(R.color.textColor));


                type_requst = "Myoffer";


            }
        });
        AllOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllOrder.setBackground(getResources().getDrawable(R.drawable.button_login));
                today.setBackground(null);
                Myoffer.setBackground(null);


                status_1.setVisibility(View.GONE);
                status_2.setVisibility(View.GONE);
                status_3.setVisibility(View.GONE);
//                Myoffer_number.setBackground(getResources().getDrawable(R.drawable.mash));
//                today_number.setBackground(getResources().getDrawable(R.drawable.mash));
//                AllOrder_number.setBackground(getResources().getDrawable(R.drawable.circle_ss));


                AllOrder.setTextColor(getResources().getColor(R.color.white));
                today.setTextColor(getResources().getColor(R.color.textColor));
                Myoffer.setTextColor(getResources().getColor(R.color.textColor));

//                Myoffer_number.setTextColor(getResources().getColor(R.color.textColor));
//                today_number.setTextColor(getResources().getColor(R.color.textColor));
//                AllOrder_number.setTextColor(getResources().getColor(R.color.white));


                type_requst = "AllOrder";


            }
        });
        favorit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AllOrderActivity.this, FavoriteActivity.class);
                startActivity(intent);


            }
        });
    }
}
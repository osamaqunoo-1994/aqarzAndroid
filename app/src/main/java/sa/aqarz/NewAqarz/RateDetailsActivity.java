package sa.aqarz.NewAqarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.willy.ratingbar.BaseRatingBar;
import com.willy.ratingbar.ScaleRatingBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.ChatRoomActivity;
import sa.aqarz.Activity.profile.OtherProfileActivity;
import sa.aqarz.Adapter.RecyclerView_samilar;
import sa.aqarz.Adapter.home_viewPager_Adapter;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.RateDetails;
import sa.aqarz.Modules.imagemodules;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_Custamer_review;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class RateDetailsActivity extends AppCompatActivity {
    SeekBar aa1;
    SeekBar aa2;
    SeekBar aa3;
    SeekBar aa4;
    SeekBar aa5;
    String id_or_aq = "";
    IResult mResultCallback;
    Button send_rate;
    EditText comments;
    TextView rate_txt;
    TextView totle;
    TextView rate_me;
    TextView nodata_cust;
    ScaleRatingBar rate_user;

    String rate_me_slid = "";
    RecyclerView all_review;
    ImageView back;
    LinearLayout is_rate_lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_details);
        aa1 = findViewById(R.id.aa1);
        aa2 = findViewById(R.id.aa2);
        aa3 = findViewById(R.id.aa3);
        aa4 = findViewById(R.id.aa4);
        aa5 = findViewById(R.id.aa5);
        send_rate = findViewById(R.id.send_rate);
        comments = findViewById(R.id.comments);
        rate_txt = findViewById(R.id.rate_txt);
        totle = findViewById(R.id.totle);
        rate_me = findViewById(R.id.rate_me);
        rate_user = findViewById(R.id.rate_user);
        nodata_cust = findViewById(R.id.nodata_cust);
        all_review = findViewById(R.id.all_review);
        back = findViewById(R.id.back);
        is_rate_lay = findViewById(R.id.is_rate_lay);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(RateDetailsActivity.this, LinearLayoutManager.VERTICAL, false);
        all_review.setLayoutManager(layoutManager1);

        try {
            id_or_aq = getIntent().getStringExtra("id_or_aq");

            init_volley();
            WebService.loading(RateDetailsActivity.this, true);

            VolleyService mVolleyService = new VolleyService(mResultCallback, RateDetailsActivity.this);
            mVolleyService.getDataVolley("rate", WebService.rate + id_or_aq + "/details");


            String is_rate = getIntent().getStringExtra("is_rate");

            if (is_rate.equals("0")) {
                is_rate_lay.setVisibility(View.VISIBLE);
            } else {
                is_rate_lay.setVisibility(View.GONE);

            }


        } catch (Exception e) {

        }

        rate_user.setOnRatingChangeListener(new BaseRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(BaseRatingBar ratingBar, float rating, boolean fromUser) {
                rate_me_slid = rating + "";
                rate_me.setText(rate_me_slid);
            }
        });

        send_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Settings.checkLogin()) {
                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("estate_id", id_or_aq);//form operation list api in setting
                        sendObj.put("rate", rate_me_slid + "");//form operation list api in setting
                        sendObj.put("note", comments.getText().toString());//form operation list api in setting


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    init_volley();
                    WebService.loading(RateDetailsActivity.this, true);
                    VolleyService mVolleyService = new VolleyService(mResultCallback, RateDetailsActivity.this);

                    System.out.println(sendObj.toString());
                    mVolleyService.postDataVolley("rate_estate", WebService.rate_estate, sendObj);
                } else {
                    startActivity(new Intent(RateDetailsActivity.this, LoginActivity.class));
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(RateDetailsActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");
                        String message = response.getString("message");


                        if (requestType.equals("rate_estate")) {
                            init_volley();

                            VolleyService mVolleyService = new VolleyService(mResultCallback, RateDetailsActivity.this);
                            mVolleyService.getDataVolley("rate", WebService.rate + id_or_aq + "/details");

                        }
                        if (requestType.equals("rate")) {


                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(data);
                            Gson gson = new Gson();
                            RateDetails rateDetails = gson.fromJson(mJson, RateDetails.class);


                            if (rateDetails.getRate() != null) {
                                if (!rateDetails.getRate().toString().equals("null")) {

                                    int aa1_ = (int) Double.parseDouble(rateDetails.getRate().getOne());
                                    int aa2_ = (int) Double.parseDouble(rateDetails.getRate().getTwo());
                                    int aa3_ = (int) Double.parseDouble(rateDetails.getRate().getThree());
                                    int aa4_ = (int) Double.parseDouble(rateDetails.getRate().getFour());
                                    int aa5_ = (int) Double.parseDouble(rateDetails.getRate().getFive());


                                    aa1.setProgress(aa1_);
                                    aa2.setProgress(aa2_);
                                    aa3.setProgress(aa3_);
                                    aa4.setProgress(aa4_);
                                    aa5.setProgress(aa5_);

                                    rate_txt.setText(rateDetails.getRate().getRate() + "");
                                    totle.setText(rateDetails.getRate().getTotal() + "");

                                }
                            }

                            if (rateDetails.getNotes().size() == 0) {
                                nodata_cust.setVisibility(View.VISIBLE);
                            } else {
                                nodata_cust.setVisibility(View.GONE);

                            }

                            all_review.setAdapter(new RecyclerView_Custamer_review(RateDetailsActivity.this, rateDetails.getNotes()));


//                            String data_inside = jsonObject_data.getString("data");
//                            JSONArray jsonArray = new JSONArray(data);
//
//                            homeModules_aqares_list.clear();
//                            rec_list_all.setAdapter(null);
//
//                            for (int i = 0; i < jsonArray.length(); i++) {
//
//                                try {
//
//                                    JsonParser parser = new JsonParser();
//                                    JsonElement mJson = parser.parse(jsonArray.getString(i));
//
//                                    Gson gson = new Gson();
//
//                                    HomeModules_aqares bankModules = gson.fromJson(mJson, HomeModules_aqares.class);
//                                    homeModules_aqares_list.add(bankModules);
//
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//                            LinearLayoutManager layoutManager1
//                                    = new LinearLayoutManager(DetaislAqarzActivity.this, LinearLayoutManager.HORIZONTAL, false);
//                            rec_list_all.setLayoutManager(layoutManager1);
//
//
//                            rec_list_all.setAdapter(new RecyclerView_samilar(DetaislAqarzActivity.this, homeModules_aqares_list));


                        }
                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(RateDetailsActivity.this, message, "error");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(RateDetailsActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(RateDetailsActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(RateDetailsActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(RateDetailsActivity.this, false);

                WebService.Make_Toast_color(RateDetailsActivity.this, error, "error");


            }
        };


    }

}
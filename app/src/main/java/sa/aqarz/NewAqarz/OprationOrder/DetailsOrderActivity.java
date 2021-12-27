package sa.aqarz.NewAqarz.OprationOrder;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import sa.aqarz.Activity.RealState.OfferDetailsActivity;
import sa.aqarz.Modules.MyOfferModule;
import sa.aqarz.R;
import sa.aqarz.Settings.Application;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class DetailsOrderActivity extends AppCompatActivity {
    ImageView back;
    TextView status_1_img, status_2_img, status_3_img;
    TextView status_1_txt, status_2_txt, status_3_txt;
    TextView status_1_txt_s, status_2_txt_s, status_3_txt_s;

    TextView status_1;
    LinearLayout status_2;
    TextView status_3;
    LinearLayout status_4;
    LinearLayout status_5;
    LinearLayout status_6;

    TextView cancleorder;
    TextView number_order;
    TextView name;

    IResult mResultCallback;

    public static MyOfferModule myOfferModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_order);
        back = findViewById(R.id.back);


        myOfferModule = Application.myOfferModule;


        status_1_img = findViewById(R.id.status_1_img);
        status_2_img = findViewById(R.id.status_2_img);
        status_3_img = findViewById(R.id.status_3_img);

        status_1_txt = findViewById(R.id.status_1_txt);
        status_2_txt = findViewById(R.id.status_2_txt);
        status_3_txt = findViewById(R.id.status_3_txt);

        status_1_txt_s = findViewById(R.id.status_1_txt_s);
        status_2_txt_s = findViewById(R.id.status_2_txt_s);
        status_3_txt_s = findViewById(R.id.status_3_txt_s);

        status_1 = findViewById(R.id.status_1);
        status_2 = findViewById(R.id.status_2);
        status_3 = findViewById(R.id.status_3);
        status_4 = findViewById(R.id.status_4);
        status_5 = findViewById(R.id.status_5);
        status_6 = findViewById(R.id.status_6);

        cancleorder = findViewById(R.id.cancleorder);
        number_order = findViewById(R.id.number_order);
        name = findViewById(R.id.name);

        name.setText(myOfferModule.getBeneficiaryName() + "");
//        names.setText(myOfferModule.getBeneficiaryName() + "");
        number_order.setText(myOfferModule.getId() + "");


        if (myOfferModule.getStatus() == null) {


        } else if (myOfferModule.getStatus().equals("active")) {


        } else if (myOfferModule.getStatus().equals("Waiting_provider_accepted")) {


        } else if (myOfferModule.getStatus().equals("sending_code")) {


        } else if (myOfferModule.getStatus().equals("rejected_customer ")) {
            status_1_txt_s.setVisibility(View.INVISIBLE);
            status_2_txt_s.setVisibility(View.INVISIBLE);

            status_1.setVisibility(View.GONE);
            status_2.setVisibility(View.GONE);
            status_3.setVisibility(View.GONE);
            status_4.setVisibility(View.GONE);
            status_5.setVisibility(View.GONE);
            status_6.setVisibility(View.VISIBLE);
            cancleorder.setVisibility(View.GONE);
            status_3_txt_s.setTextColor(getResources().getColor(R.color.error));
            status_3_txt_s.setText(getResources().getString(R.string.unacceptable));
            status_3_txt.setTypeface(status_3_txt.getTypeface(), Typeface.BOLD);

        } else if (myOfferModule.getStatus().equals("accepted_customer")) {

            status_1_txt_s.setVisibility(View.INVISIBLE);
            status_2_txt_s.setVisibility(View.INVISIBLE);

            status_1.setVisibility(View.GONE);
            status_2.setVisibility(View.GONE);
            status_3.setVisibility(View.GONE);
            status_4.setVisibility(View.GONE);
            status_5.setVisibility(View.VISIBLE);
            status_6.setVisibility(View.GONE);

            cancleorder.setVisibility(View.GONE);
            status_3_txt.setTypeface(status_3_txt.getTypeface(), Typeface.BOLD);


        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cancleorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                RequestParams sendObj = new RequestParams();

                try {

                    sendObj.put("uuid", myOfferModule.getId() + "");//form operation list api in setting
//                    sendObj.put("estate_id", is_selected);//form estate type list api in setting
                } catch (Exception e) {
                    e.printStackTrace();
                }
                init_volley();
                WebService.loading(DetailsOrderActivity.this, true);
                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsOrderActivity.this);

                System.out.println(sendObj.toString());
                mVolleyService.postDataasync_with_file("cancel//fund/offer", WebService.cancel + "/" + myOfferModule.getId() + "/" + WebService.rate_offer, sendObj);


            }
        });
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(DetailsOrderActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {

//                        if (requestType.equals("provider_code_send")) {
//                            String message = response.getString("message");
//                            WebService.Make_Toast_color(DetailsOrderActivity.this, message, "success");
//
//                            status_1.setVisibility(View.GONE);
//                            status_2.setVisibility(View.VISIBLE);
//                            status_3.setVisibility(View.GONE);
//                            status_ss.setVisibility(View.GONE);
//                            status_4.setVisibility(View.VISIBLE);
//
//
//                        } else {
//                            String message = response.getString("message");
//                            WebService.Make_Toast_color(DetailsOrderActivity.this, message, "success");
//
//                            status_1.setVisibility(View.GONE);
//                            status_2.setVisibility(View.GONE);
//                            status_3.setVisibility(View.GONE);
//                            status_4.setVisibility(View.GONE);
////                        cancleorder.setVisibility(View.GONE);
//                            cancleorder.setBackground(getDrawable(R.drawable.mash));
////                        cancleorder.setTextColor(getColor(R.color.textColor2));
//
//                            cancleorder.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                }
//                            });
//                        }


                    } else {
                        String message = response.getString("message");


                        WebService.Make_Toast_color(DetailsOrderActivity.this, message, "error");

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());

                try {
                    WebService.loading(DetailsOrderActivity.this, false);

//                    progress.setVisibility(View.GONE);

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(DetailsOrderActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(DetailsOrderActivity.this, false);

            }
        };


    }

}
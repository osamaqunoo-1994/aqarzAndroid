package sa.aqarz.NewAqarz.OprationOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Modules.MyOfferModule;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Application;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class DetailsStatusOrderActivity extends AppCompatActivity {


    TextView date_1, date_2, date_3, date_4, date_5;
    TextView status_1, status_2, status_3;
    TextView details_1, details_2, details_3;

    IResult mResultCallback;

    ImageView whats_up;
    TextView whats_up_number;
    TextView qustion_2;
    TextView yes;
    TextView no;

    EditText a1;
    EditText a2;
    EditText a3;
    EditText a4;
    EditText a5;
    TextView confirm;
    TextView cancle;
    TextView qustion_3;
    TextView resend;


    TextView cancleorder;


    public static MyOfferModule myOfferModule;


    LinearLayout status_x1;
    LinearLayout status_x2;
    LinearLayout status_x3;
    LinearLayout status_x4;
    LinearLayout status_x5;
    OrdersModules demandsModules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_status_order);

        status_x1 = findViewById(R.id.status_x1);
        status_x2 = findViewById(R.id.status_x2);
        status_x3 = findViewById(R.id.status_x3);
        status_x4 = findViewById(R.id.status_x4);
        status_x5 = findViewById(R.id.status_x5);

        date_1 = findViewById(R.id.date_1);
        date_2 = findViewById(R.id.date_2);
        date_3 = findViewById(R.id.date_3);
        date_4 = findViewById(R.id.date_4);
        date_5 = findViewById(R.id.date_5);
        status_1 = findViewById(R.id.status_1);
        status_2 = findViewById(R.id.status_2);
        status_3 = findViewById(R.id.status_3);
        details_1 = findViewById(R.id.details_1);
        details_2 = findViewById(R.id.details_2);
        details_3 = findViewById(R.id.details_3);
        cancleorder = findViewById(R.id.cancleorder);


        whats_up = findViewById(R.id.whats_up);
        whats_up_number = findViewById(R.id.whats_up_number);
        qustion_2 = findViewById(R.id.qustion_2);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);


        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        a5 = findViewById(R.id.a5);
        confirm = findViewById(R.id.confirm);
        cancle = findViewById(R.id.cancle);
        qustion_3 = findViewById(R.id.qustion_3);
        resend = findViewById(R.id.resend);


        myOfferModule = Application.myOfferModule;

        demandsModules = MainActivity.ordersModules;


        status_x2.setVisibility(View.GONE);
        status_x3.setVisibility(View.GONE);
        status_x4.setVisibility(View.GONE);
        status_x5.setVisibility(View.GONE);




        if (myOfferModule.getStatus().equals("new")) {//new->sending_code->accepted_customer


            status_x2.setVisibility(View.GONE);
            status_x3.setVisibility(View.GONE);
            status_x4.setVisibility(View.GONE);
            status_x5.setVisibility(View.GONE);


            if(demandsModules.getStart_at()!=null){
                date_1.setText(demandsModules.getStart_at() + "");

            }

        } else if (myOfferModule.getStatus().equals("sending_code")) {

// status 1


            if(demandsModules.getStart_at()!=null){
                date_1.setText(demandsModules.getStart_at() + "");

            }
            if(demandsModules.getReview_at()!=null){
                date_2.setText(demandsModules.getReview_at() + "");

            }


            date_1.setTextColor(getResources().getColor(R.color.gray_color));
            status_1.setTextColor(getResources().getColor(R.color.gray_color));
            details_1.setTextColor(getResources().getColor(R.color.gray_color));


            status_x2.setVisibility(View.VISIBLE);
            status_x3.setVisibility(View.GONE);
            status_x4.setVisibility(View.GONE);
            status_x5.setVisibility(View.GONE);


            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("uuid", myOfferModule.getUuid() + "");//form operation list api in setting
//                    sendObj.put("estate_id", is_selected);//form estate type list api in setting
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    init_volley();
                    WebService.loading(DetailsStatusOrderActivity.this, true);
                    VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsStatusOrderActivity.this);

                    System.out.println(sendObj.toString());
                    mVolleyService.postDataVolley("provider_code_send", WebService.provider_code_send, sendObj);


                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                request_code.setVisibility(View.GONE);
//                error_message.setVisibility(View.VISIBLE);

                    try {
                        String phone = "" + MainActivity.ordersModules.getBeneficiaryMobile() + "";
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            });

            whats_up_number.setText(MainActivity.ordersModules.getBeneficiaryMobile() + "");

            whats_up_number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mobile = whats_up_number.getText().toString();
//                    String ss = mobile.substring(2);

                    String url = "https://api.whatsapp.com/send?phone=" + mobile;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });


        } else if (myOfferModule.getStatus().equals("waiting_code")) {

// status 1

            if(demandsModules.getStart_at()!=null){
                date_1.setText(demandsModules.getStart_at() + "");

            }
            if(demandsModules.getReview_at()!=null){
                date_2.setText(demandsModules.getReview_at() + "");

            }
            if(demandsModules.getAccept_review_at()!=null){
                date_3.setText(demandsModules.getAccept_review_at() + "");

            }
            date_1.setTextColor(getResources().getColor(R.color.gray_color));
            status_1.setTextColor(getResources().getColor(R.color.gray_color));
            details_1.setTextColor(getResources().getColor(R.color.gray_color));


// status 2
            date_2.setTextColor(getResources().getColor(R.color.gray_color));
            status_2.setTextColor(getResources().getColor(R.color.gray_color));
            details_2.setTextColor(getResources().getColor(R.color.gray_color));
            whats_up_number.setTextColor(getResources().getColor(R.color.gray_color));
            qustion_2.setTextColor(getResources().getColor(R.color.gray_color));
            no.setTextColor(getResources().getColor(R.color.gray_color));
            yes.setBackground(getDrawable(R.drawable.button_login1_ccc));
            whats_up.setColorFilter(ContextCompat.getColor(DetailsStatusOrderActivity.this, R.color.gray_color), android.graphics.PorterDuff.Mode.SRC_IN);


            status_x2.setVisibility(View.VISIBLE);
            status_x3.setVisibility(View.VISIBLE);
            status_x4.setVisibility(View.GONE);
            status_x5.setVisibility(View.GONE);

            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (a1.getText().toString().equals("") |
                            a2.getText().toString().equals("") |
                            a3.getText().toString().equals("") |
                            a4.getText().toString().equals("") |
                            a5.getText().toString().equals("")

                    ) {

                    } else {
                        String code2 = a1.getText().toString() +
                                a2.getText().toString() +
                                a3.getText().toString() +
                                a4.getText().toString() + a5.getText().toString() + "";


                        JSONObject sendObj = new JSONObject();

                        try {

                            sendObj.put("uuid", myOfferModule.getId());//form operation list api in setting
                            sendObj.put("code", code2);//form estate type list api in setting
                            sendObj.put("estate_id", myOfferModule.getEstateId());//form estate type list api in setting
                            sendObj.put("status", "accepted_customer");//form estate type list api in setting
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        init_volley();
                        WebService.loading(DetailsStatusOrderActivity.this, true);
                        VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsStatusOrderActivity.this);

                        System.out.println(sendObj.toString());
//                    mVolleyService.postDataVolley("send_offer_fund_Request", WebService.send_offer_fund_Request, sendObj);
                        mVolleyService.postDataVolley("send_customer_offer_status_1", WebService.send_customer_offer_status, sendObj);


                    }

                }
            });
            cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (a1.getText().toString().equals("") |
                            a2.getText().toString().equals("") |
                            a3.getText().toString().equals("") |
                            a4.getText().toString().equals("") |
                            a5.getText().toString().equals("")

                    ) {

                    } else {
                        String code2 = a1.getText().toString() +
                                a2.getText().toString() +
                                a3.getText().toString() +
                                a4.getText().toString() + a5.getText().toString() + "";


                        JSONObject sendObj = new JSONObject();

                        try {

                            sendObj.put("uuid", myOfferModule.getId());//form operation list api in setting
                            sendObj.put("code", code2);//form estate type list api in setting
                            sendObj.put("estate_id", myOfferModule.getEstateId());//form estate type list api in setting
                            sendObj.put("status", "rejected_customer");//form estate type list api in setting
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        init_volley();
                        WebService.loading(DetailsStatusOrderActivity.this, true);
                        VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsStatusOrderActivity.this);

                        System.out.println(sendObj.toString());
//                    mVolleyService.postDataVolley("send_offer_fund_Request", WebService.send_offer_fund_Request, sendObj);
                        mVolleyService.postDataVolley("send_customer_offer_status_2", WebService.send_customer_offer_status, sendObj);


                    }

                }
            });
            resend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        } else if (myOfferModule.getStatus().equals("rejected_customer")) {


            if(demandsModules.getStart_at()!=null){
                date_1.setText(demandsModules.getStart_at() + "");

            }
            if(demandsModules.getReview_at()!=null){
                date_2.setText(demandsModules.getReview_at() + "");

            }
            if(demandsModules.getAccept_review_at()!=null){
                date_3.setText(demandsModules.getAccept_review_at() + "");

            }
            if(demandsModules.getCancel_at()!=null){
                date_4.setText(demandsModules.getCancel_at() + "");

            }

            cancleorder.setVisibility(View.GONE);

// status 1
            date_1.setTextColor(getResources().getColor(R.color.gray_color));
            status_1.setTextColor(getResources().getColor(R.color.gray_color));
            details_1.setTextColor(getResources().getColor(R.color.gray_color));


// status 2
            date_2.setTextColor(getResources().getColor(R.color.gray_color));
            status_2.setTextColor(getResources().getColor(R.color.gray_color));
            details_2.setTextColor(getResources().getColor(R.color.gray_color));
            whats_up_number.setTextColor(getResources().getColor(R.color.gray_color));
            qustion_2.setTextColor(getResources().getColor(R.color.gray_color));
            no.setTextColor(getResources().getColor(R.color.gray_color));
            yes.setBackground(getDrawable(R.drawable.button_login1_ccc));
            whats_up.setColorFilter(ContextCompat.getColor(DetailsStatusOrderActivity.this, R.color.gray_color), android.graphics.PorterDuff.Mode.SRC_IN);


// status 3
            date_3.setTextColor(getResources().getColor(R.color.gray_color));
            status_3.setTextColor(getResources().getColor(R.color.gray_color));
            details_3.setTextColor(getResources().getColor(R.color.gray_color));

            a1.setClickable(false);
            a2.setClickable(false);
            a3.setClickable(false);
            a4.setClickable(false);
            a5.setClickable(false);

            confirm.setTextColor(getResources().getColor(R.color.gray_color));
            cancle.setTextColor(getResources().getColor(R.color.gray_color));
            confirm.setBackground(getDrawable(R.drawable.border_fillter_edit));
            cancle.setBackground(getDrawable(R.drawable.border_fillter_edit));
            qustion_3.setTextColor(getResources().getColor(R.color.gray_color));
            resend.setTextColor(getResources().getColor(R.color.gray_color));


            status_x2.setVisibility(View.VISIBLE);
            status_x3.setVisibility(View.VISIBLE);
            status_x4.setVisibility(View.GONE);
            status_x5.setVisibility(View.VISIBLE);

        } else if (myOfferModule.getStatus().equals("accepted_customer")) {

            cancleorder.setVisibility(View.GONE);
            if(demandsModules.getStart_at()!=null){
                date_1.setText(demandsModules.getStart_at() + "");

            }
            if(demandsModules.getReview_at()!=null){
                date_2.setText(demandsModules.getReview_at() + "");

            }
            if(demandsModules.getAccept_review_at()!=null){
                date_3.setText(demandsModules.getAccept_review_at() + "");

            }
            if(demandsModules.getCancel_at()!=null){
                date_4.setText(demandsModules.getCancel_at() + "");

            }
            if(demandsModules.getAccepted_at()!=null){
                date_5.setText(demandsModules.getAccepted_at() + "");

            }
// status 1
            date_1.setTextColor(getResources().getColor(R.color.gray_color));
            status_1.setTextColor(getResources().getColor(R.color.gray_color));
            details_1.setTextColor(getResources().getColor(R.color.gray_color));


// status 2
            date_2.setTextColor(getResources().getColor(R.color.gray_color));
            status_2.setTextColor(getResources().getColor(R.color.gray_color));
            details_2.setTextColor(getResources().getColor(R.color.gray_color));
            whats_up_number.setTextColor(getResources().getColor(R.color.gray_color));
            qustion_2.setTextColor(getResources().getColor(R.color.gray_color));
            no.setTextColor(getResources().getColor(R.color.gray_color));
            yes.setBackground(getDrawable(R.drawable.button_login1_ccc));
            whats_up.setColorFilter(ContextCompat.getColor(DetailsStatusOrderActivity.this, R.color.gray_color), android.graphics.PorterDuff.Mode.SRC_IN);


// status 3
            date_3.setTextColor(getResources().getColor(R.color.gray_color));
            status_3.setTextColor(getResources().getColor(R.color.gray_color));
            details_3.setTextColor(getResources().getColor(R.color.gray_color));

            a1.setClickable(false);
            a2.setClickable(false);
            a3.setClickable(false);
            a4.setClickable(false);
            a5.setClickable(false);

            confirm.setTextColor(getResources().getColor(R.color.gray_color));
            cancle.setTextColor(getResources().getColor(R.color.gray_color));
            confirm.setBackground(getDrawable(R.drawable.border_fillter_edit));
            cancle.setBackground(getDrawable(R.drawable.border_fillter_edit));
            qustion_3.setTextColor(getResources().getColor(R.color.gray_color));
            resend.setTextColor(getResources().getColor(R.color.gray_color));


            status_x2.setVisibility(View.VISIBLE);
            status_x3.setVisibility(View.VISIBLE);
            status_x4.setVisibility(View.VISIBLE);
            status_x5.setVisibility(View.GONE);


        } else if (myOfferModule.getStatus().equals("close")) {



            if(demandsModules.getStart_at()!=null){
                date_1.setText(demandsModules.getStart_at() + "");

            }
            if(demandsModules.getReview_at()!=null){
                date_2.setText(demandsModules.getReview_at() + "");

            }
            if(demandsModules.getAccept_review_at()!=null){
                date_3.setText(demandsModules.getAccept_review_at() + "");

            }
            if(demandsModules.getCancel_at()!=null){
                date_4.setText(demandsModules.getCancel_at() + "");

            }
            if(demandsModules.getAccepted_at()!=null){
                date_5.setText(demandsModules.getAccepted_at() + "");

            }
            cancleorder.setVisibility(View.GONE);

// status 1
            date_1.setTextColor(getResources().getColor(R.color.gray_color));
            status_1.setTextColor(getResources().getColor(R.color.gray_color));
            details_1.setTextColor(getResources().getColor(R.color.gray_color));


// status 2
            date_2.setTextColor(getResources().getColor(R.color.gray_color));
            status_2.setTextColor(getResources().getColor(R.color.gray_color));
            details_2.setTextColor(getResources().getColor(R.color.gray_color));
            whats_up_number.setTextColor(getResources().getColor(R.color.gray_color));
            qustion_2.setTextColor(getResources().getColor(R.color.gray_color));
            no.setTextColor(getResources().getColor(R.color.gray_color));
            yes.setBackground(getDrawable(R.drawable.button_login1_ccc));
            whats_up.setColorFilter(ContextCompat.getColor(DetailsStatusOrderActivity.this, R.color.gray_color), android.graphics.PorterDuff.Mode.SRC_IN);


// status 3
            date_3.setTextColor(getResources().getColor(R.color.gray_color));
            status_3.setTextColor(getResources().getColor(R.color.gray_color));
            details_3.setTextColor(getResources().getColor(R.color.gray_color));

            a1.setClickable(false);
            a2.setClickable(false);
            a3.setClickable(false);
            a4.setClickable(false);
            a5.setClickable(false);

            confirm.setTextColor(getResources().getColor(R.color.gray_color));
            cancle.setTextColor(getResources().getColor(R.color.gray_color));
            confirm.setBackground(getDrawable(R.drawable.border_fillter_edit));
            cancle.setBackground(getDrawable(R.drawable.border_fillter_edit));
            qustion_3.setTextColor(getResources().getColor(R.color.gray_color));
            resend.setTextColor(getResources().getColor(R.color.gray_color));


            status_x2.setVisibility(View.VISIBLE);
            status_x3.setVisibility(View.VISIBLE);
            status_x4.setVisibility(View.GONE);
            status_x5.setVisibility(View.VISIBLE);
        }

        cancleorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                BottomSheetDialogFragment_delete_offer bottomSheetDialogFragmen_delete_offer = new BottomSheetDialogFragment_delete_offer("");
                bottomSheetDialogFragmen_delete_offer.addItemClickListener(new BottomSheetDialogFragment_delete_offer.ItemClickListener() {
                    @Override
                    public void onItemClick(String type, String reseon) {


                        RequestParams sendObj = new RequestParams();

                        try {

                            sendObj.put("uuid", myOfferModule.getId() + "");//form operation list api in setting
//                    sendObj.put("estate_id", is_selected);//form estate type list api in setting
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        init_volley();
                        WebService.loading(DetailsStatusOrderActivity.this, true);
                        VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsStatusOrderActivity.this);

                        System.out.println(sendObj.toString());
                        mVolleyService.postDataasync_with_file("cancelfundoffer", WebService.cancel + "/" + myOfferModule.getId() + "/" + WebService.rate_offer1, sendObj);


                    }
                });


                bottomSheetDialogFragmen_delete_offer.show(getSupportFragmentManager(), "");


//                RequestParams sendObj = new RequestParams();
//
//                try {
//
//                    sendObj.put("uuid", myOfferModule.getId() + "");//form operation list api in setting
////                    sendObj.put("estate_id", is_selected);//form estate type list api in setting
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                init_volley();
//                WebService.loading(DetailsOrderActivity.this, true);
//                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsOrderActivity.this);
//
//                System.out.println(sendObj.toString());
//                mVolleyService.postDataasync_with_file("cancel//fund/offer", WebService.cancel + "/" + myOfferModule.getId() + "/" + WebService.rate_offer, sendObj);


            }
        });
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(DetailsStatusOrderActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {

                        if (requestType.equals("provider_code_send")) {
                            String message = response.getString("message");


                            WebService.Make_Toast_color(DetailsStatusOrderActivity.this, message, "success");
// status 1
                            date_1.setTextColor(getResources().getColor(R.color.gray_color));
                            status_1.setTextColor(getResources().getColor(R.color.gray_color));
                            details_1.setTextColor(getResources().getColor(R.color.gray_color));


// status 2
                            date_2.setTextColor(getResources().getColor(R.color.gray_color));
                            status_2.setTextColor(getResources().getColor(R.color.gray_color));
                            details_2.setTextColor(getResources().getColor(R.color.gray_color));
                            whats_up_number.setTextColor(getResources().getColor(R.color.gray_color));
                            qustion_2.setTextColor(getResources().getColor(R.color.gray_color));
                            no.setTextColor(getResources().getColor(R.color.gray_color));
                            yes.setBackground(getDrawable(R.drawable.button_login1_ccc));
                            whats_up.setColorFilter(ContextCompat.getColor(DetailsStatusOrderActivity.this, R.color.gray_color), android.graphics.PorterDuff.Mode.SRC_IN);


                            status_x2.setVisibility(View.VISIBLE);
                            status_x3.setVisibility(View.VISIBLE);
                            status_x4.setVisibility(View.GONE);
                            status_x5.setVisibility(View.GONE);


                            yes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            no.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });


                        } else if (requestType.equals("send_customer_offer_status_1")) {
                            String message = response.getString("message");
                            cancleorder.setVisibility(View.GONE);

// status 1
                            date_1.setTextColor(getResources().getColor(R.color.gray_color));
                            status_1.setTextColor(getResources().getColor(R.color.gray_color));
                            details_1.setTextColor(getResources().getColor(R.color.gray_color));


// status 2
                            date_2.setTextColor(getResources().getColor(R.color.gray_color));
                            status_2.setTextColor(getResources().getColor(R.color.gray_color));
                            details_2.setTextColor(getResources().getColor(R.color.gray_color));
                            whats_up_number.setTextColor(getResources().getColor(R.color.gray_color));
                            qustion_2.setTextColor(getResources().getColor(R.color.gray_color));
                            no.setTextColor(getResources().getColor(R.color.gray_color));
                            yes.setBackground(getDrawable(R.drawable.button_login1_ccc));
                            whats_up.setColorFilter(ContextCompat.getColor(DetailsStatusOrderActivity.this, R.color.gray_color), android.graphics.PorterDuff.Mode.SRC_IN);


// status 3
                            date_3.setTextColor(getResources().getColor(R.color.gray_color));
                            status_3.setTextColor(getResources().getColor(R.color.gray_color));
                            details_3.setTextColor(getResources().getColor(R.color.gray_color));

                            a1.setClickable(false);
                            a2.setClickable(false);
                            a3.setClickable(false);
                            a4.setClickable(false);
                            a5.setClickable(false);

                            confirm.setTextColor(getResources().getColor(R.color.gray_color));
                            cancle.setTextColor(getResources().getColor(R.color.gray_color));
                            confirm.setBackground(getDrawable(R.drawable.border_fillter_edit));
                            cancle.setBackground(getDrawable(R.drawable.border_fillter_edit));
                            qustion_3.setTextColor(getResources().getColor(R.color.gray_color));
                            resend.setTextColor(getResources().getColor(R.color.gray_color));


                            status_x2.setVisibility(View.VISIBLE);
                            status_x3.setVisibility(View.VISIBLE);
                            status_x4.setVisibility(View.VISIBLE);
                            status_x5.setVisibility(View.GONE);


                        } else if (requestType.equals("send_customer_offer_status_2")) {
                            String message = response.getString("message");


                            WebService.Make_Toast_color(DetailsStatusOrderActivity.this, message, "success");
//
//
                            cancleorder.setVisibility(View.GONE);

// status 1
                            date_1.setTextColor(getResources().getColor(R.color.gray_color));
                            status_1.setTextColor(getResources().getColor(R.color.gray_color));
                            details_1.setTextColor(getResources().getColor(R.color.gray_color));


// status 2
                            date_2.setTextColor(getResources().getColor(R.color.gray_color));
                            status_2.setTextColor(getResources().getColor(R.color.gray_color));
                            details_2.setTextColor(getResources().getColor(R.color.gray_color));
                            whats_up_number.setTextColor(getResources().getColor(R.color.gray_color));
                            qustion_2.setTextColor(getResources().getColor(R.color.gray_color));
                            no.setTextColor(getResources().getColor(R.color.gray_color));
                            yes.setBackground(getDrawable(R.drawable.button_login1_ccc));
                            whats_up.setColorFilter(ContextCompat.getColor(DetailsStatusOrderActivity.this, R.color.gray_color), android.graphics.PorterDuff.Mode.SRC_IN);


// status 3
                            date_3.setTextColor(getResources().getColor(R.color.gray_color));
                            status_3.setTextColor(getResources().getColor(R.color.gray_color));
                            details_3.setTextColor(getResources().getColor(R.color.gray_color));

                            a1.setClickable(false);
                            a2.setClickable(false);
                            a3.setClickable(false);
                            a4.setClickable(false);
                            a5.setClickable(false);

                            confirm.setTextColor(getResources().getColor(R.color.gray_color));
                            cancle.setTextColor(getResources().getColor(R.color.gray_color));
                            confirm.setBackground(getDrawable(R.drawable.border_fillter_edit));
                            cancle.setBackground(getDrawable(R.drawable.border_fillter_edit));
                            qustion_3.setTextColor(getResources().getColor(R.color.gray_color));
                            resend.setTextColor(getResources().getColor(R.color.gray_color));


                            status_x2.setVisibility(View.VISIBLE);
                            status_x3.setVisibility(View.VISIBLE);
                            status_x4.setVisibility(View.GONE);
                            status_x5.setVisibility(View.VISIBLE);

                        } else if (requestType.equals("cancelfundoffer")) {
                            String message = response.getString("message");


                            WebService.Make_Toast_color(DetailsStatusOrderActivity.this, message, "success");
                            cancleorder.setVisibility(View.GONE);

// status 1
                            date_1.setTextColor(getResources().getColor(R.color.gray_color));
                            status_1.setTextColor(getResources().getColor(R.color.gray_color));
                            details_1.setTextColor(getResources().getColor(R.color.gray_color));


// status 2
                            date_2.setTextColor(getResources().getColor(R.color.gray_color));
                            status_2.setTextColor(getResources().getColor(R.color.gray_color));
                            details_2.setTextColor(getResources().getColor(R.color.gray_color));
                            whats_up_number.setTextColor(getResources().getColor(R.color.gray_color));
                            qustion_2.setTextColor(getResources().getColor(R.color.gray_color));
                            no.setTextColor(getResources().getColor(R.color.gray_color));
                            yes.setBackground(getDrawable(R.drawable.button_login1_ccc));
                            whats_up.setColorFilter(ContextCompat.getColor(DetailsStatusOrderActivity.this, R.color.gray_color), android.graphics.PorterDuff.Mode.SRC_IN);


// status 3
                            date_3.setTextColor(getResources().getColor(R.color.gray_color));
                            status_3.setTextColor(getResources().getColor(R.color.gray_color));
                            details_3.setTextColor(getResources().getColor(R.color.gray_color));

                            a1.setClickable(false);
                            a2.setClickable(false);
                            a3.setClickable(false);
                            a4.setClickable(false);
                            a5.setClickable(false);

                            confirm.setTextColor(getResources().getColor(R.color.gray_color));
                            cancle.setTextColor(getResources().getColor(R.color.gray_color));
                            confirm.setBackground(getDrawable(R.drawable.border_fillter_edit));
                            cancle.setBackground(getDrawable(R.drawable.border_fillter_edit));
                            qustion_3.setTextColor(getResources().getColor(R.color.gray_color));
                            resend.setTextColor(getResources().getColor(R.color.gray_color));


                            status_x2.setVisibility(View.VISIBLE);
                            status_x3.setVisibility(View.VISIBLE);
                            status_x4.setVisibility(View.GONE);
                            status_x5.setVisibility(View.VISIBLE);
//

                        }


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


                        WebService.Make_Toast_color(DetailsStatusOrderActivity.this, message, "error");

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
                    WebService.loading(DetailsStatusOrderActivity.this, false);

//                    progress.setVisibility(View.GONE);

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(DetailsStatusOrderActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(DetailsStatusOrderActivity.this, false);

            }
        };


    }

}
package sa.aqarz.NewAqarz.OprationOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Typeface;
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
import sa.aqarz.Activity.RealState.OfferDetailsActivity;
import sa.aqarz.NewAqarz.OprationOrder.BottomSheetDialogFragment_delete_offer;
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
    LinearLayout mobile_and_name;
    LinearLayout calling;

    TextView cancleorder;
    TextView number_order;
    TextView name;
    TextView confirm;

    IResult mResultCallback;
    EditText a1;
    EditText a2;
    EditText a3;
    EditText a4;
    EditText a5;
    public static MyOfferModule myOfferModule;
    TextView request_code;
    TextView yes;
    TextView no;
    TextView error_message;
    TextView cancle;
    TextView resend;


    LinearLayout sds_1;
    LinearLayout sds_2;
    LinearLayout sds_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_order);
        request_code = findViewById(R.id.request_code);
        back = findViewById(R.id.back);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        a5 = findViewById(R.id.a5);
        sds_1 = findViewById(R.id.sds_1);
        sds_2 = findViewById(R.id.sds_2);
        error_message = findViewById(R.id.error_message);
        sds_3 = findViewById(R.id.sds_3);
        cancle = findViewById(R.id.cancle);
        resend = findViewById(R.id.resend);


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
        mobile_and_name = findViewById(R.id.mobile_and_name);
        calling = findViewById(R.id.calling);
        confirm = findViewById(R.id.confirm);

        cancleorder = findViewById(R.id.cancleorder);
        number_order = findViewById(R.id.number_order);
        name = findViewById(R.id.name);

//        name.setText(myOfferModule.getBeneficiaryName() + "");
        name.setText(MainActivity.ordersModules.getBeneficiaryName() + "");
//        names.setText(myOfferModule.getBeneficiaryName() + "");
        number_order.setText(myOfferModule.getId() + "");
//        number_order.setText(MainActivity.ordersModules.getBeneficiaryMobile() + "");


        status_1.setVisibility(View.GONE);
        status_2.setVisibility(View.GONE);
        status_3.setVisibility(View.GONE);
        status_4.setVisibility(View.GONE);
        status_5.setVisibility(View.GONE);
        status_6.setVisibility(View.GONE);
        mobile_and_name.setVisibility(View.GONE);
        cancleorder.setVisibility(View.GONE);


//    } else if (myOfferModule.getStatus().equals("active")) {

//            if (myOfferModule.getStatus() == null) {
//    } else if (myOfferModule.getStatus().equals("Waiting_provider_accepted")) {
        sds_1.setVisibility(View.GONE);
        sds_2.setVisibility(View.GONE);
        sds_3.setVisibility(View.VISIBLE);

        if (myOfferModule.getStatus().equals("new")) {//new->sending_code->accepted_customer

            status_1.setVisibility(View.VISIBLE);
            status_2.setVisibility(View.GONE);
            status_3.setVisibility(View.GONE);
            status_4.setVisibility(View.GONE);
            status_5.setVisibility(View.GONE);
            status_6.setVisibility(View.GONE);
            mobile_and_name.setVisibility(View.VISIBLE);
            cancleorder.setVisibility(View.VISIBLE);


            sds_1.setVisibility(View.VISIBLE);
            sds_2.setVisibility(View.VISIBLE);
            sds_3.setVisibility(View.GONE);


            status_2_txt_s.setVisibility(View.INVISIBLE);
            status_3_txt_s.setVisibility(View.INVISIBLE);
            status_1_txt.setTypeface(status_3_txt.getTypeface(), Typeface.BOLD);
            status_2_txt.setTypeface(status_3_txt.getTypeface(), Typeface.NORMAL);
            status_3_txt.setTypeface(status_3_txt.getTypeface(), Typeface.NORMAL);


            status_2_txt.setTextColor(getResources().getColor(R.color.unselected));
            status_3_txt.setTextColor(getResources().getColor(R.color.unselected));

            status_2_img.setBackground(getResources().getDrawable(R.drawable.circle_status_un_selected));
            status_3_img.setBackground(getResources().getDrawable(R.drawable.circle_status_un_selected));
        } else if (myOfferModule.getStatus().equals("sending_code")) {


            status_1.setVisibility(View.GONE);
            status_2.setVisibility(View.VISIBLE);
            status_3.setVisibility(View.GONE);
            status_4.setVisibility(View.GONE);
            status_5.setVisibility(View.GONE);
            status_6.setVisibility(View.GONE);
            mobile_and_name.setVisibility(View.VISIBLE);
            cancleorder.setVisibility(View.VISIBLE);


            status_1_txt_s.setVisibility(View.INVISIBLE);
            status_3_txt_s.setVisibility(View.INVISIBLE);

            status_1_txt.setTypeface(status_3_txt.getTypeface(), Typeface.NORMAL);
            status_2_txt.setTypeface(status_3_txt.getTypeface(), Typeface.BOLD);
            status_3_txt.setTypeface(status_3_txt.getTypeface(), Typeface.NORMAL);

            status_3_txt.setTextColor(getResources().getColor(R.color.unselected));


            status_3_img.setBackground(getResources().getDrawable(R.drawable.circle_status_un_selected));

        } else if (myOfferModule.getStatus().equals("waiting_code")) {

            status_1.setVisibility(View.GONE);
            status_2.setVisibility(View.GONE);
            status_3.setVisibility(View.GONE);
            status_4.setVisibility(View.VISIBLE);
            status_5.setVisibility(View.GONE);
            status_6.setVisibility(View.GONE);
            mobile_and_name.setVisibility(View.VISIBLE);
            cancleorder.setVisibility(View.VISIBLE);


            status_1_txt_s.setVisibility(View.INVISIBLE);
            status_3_txt_s.setVisibility(View.INVISIBLE);

            status_1_txt.setTypeface(status_3_txt.getTypeface(), Typeface.NORMAL);
            status_2_txt.setTypeface(status_3_txt.getTypeface(), Typeface.BOLD);
            status_3_txt.setTypeface(status_3_txt.getTypeface(), Typeface.NORMAL);

            status_3_txt.setTextColor(getResources().getColor(R.color.unselected));


            status_3_img.setBackground(getResources().getDrawable(R.drawable.circle_status_un_selected));


        } else if (myOfferModule.getStatus().equals("rejected_customer")) {
            status_1_txt_s.setVisibility(View.INVISIBLE);
            status_2_txt_s.setVisibility(View.INVISIBLE);

            status_1.setVisibility(View.GONE);
            status_2.setVisibility(View.GONE);
            status_3.setVisibility(View.GONE);
            status_4.setVisibility(View.GONE);
            status_5.setVisibility(View.GONE);
            status_6.setVisibility(View.VISIBLE);
            mobile_and_name.setVisibility(View.VISIBLE);
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
            mobile_and_name.setVisibility(View.VISIBLE);
            status_6.setVisibility(View.GONE);

            cancleorder.setVisibility(View.GONE);
            status_3_txt.setTypeface(status_3_txt.getTypeface(), Typeface.BOLD);


        } else if (myOfferModule.getStatus().equals("close")) {
            status_1_txt_s.setVisibility(View.INVISIBLE);
            status_2_txt_s.setVisibility(View.INVISIBLE);

            status_1.setVisibility(View.GONE);
            status_2.setVisibility(View.GONE);
            status_3.setVisibility(View.GONE);
            status_4.setVisibility(View.GONE);
            status_5.setVisibility(View.VISIBLE);
            mobile_and_name.setVisibility(View.VISIBLE);
            status_6.setVisibility(View.GONE);

            cancleorder.setVisibility(View.GONE);
            status_3_txt.setTypeface(status_3_txt.getTypeface(), Typeface.BOLD);


        }

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_code.setVisibility(View.VISIBLE);
                error_message.setVisibility(View.GONE);
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        calling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String phone = "" + MainActivity.ordersModules.getBeneficiaryMobile() + "";
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
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
                    WebService.loading(DetailsOrderActivity.this, true);
                    VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsOrderActivity.this);

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
                    WebService.loading(DetailsOrderActivity.this, true);
                    VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsOrderActivity.this);

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
                        WebService.loading(DetailsOrderActivity.this, true);
                        VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsOrderActivity.this);

                        System.out.println(sendObj.toString());
                        mVolleyService.postDataasync_with_file("cancel//fund/offer", WebService.cancel + "/" + myOfferModule.getId() + "/" + WebService.rate_offer, sendObj);


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
        request_code.setOnClickListener(new View.OnClickListener() {
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
                WebService.loading(DetailsOrderActivity.this, true);
                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsOrderActivity.this);

                System.out.println(sendObj.toString());
                mVolleyService.postDataVolley("provider_code_send", WebService.provider_code_send, sendObj);


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

                        if (requestType.equals("provider_code_send")) {
                            String message = response.getString("message");


                            WebService.Make_Toast_color(DetailsOrderActivity.this, message, "success");

                            status_1.setVisibility(View.GONE);
                            status_2.setVisibility(View.GONE);
                            status_3.setVisibility(View.GONE);
                            status_4.setVisibility(View.VISIBLE);
                            status_5.setVisibility(View.GONE);
                            status_6.setVisibility(View.GONE);
                            mobile_and_name.setVisibility(View.VISIBLE);
                            cancleorder.setVisibility(View.VISIBLE);


                            status_1_txt_s.setVisibility(View.INVISIBLE);
                            status_3_txt_s.setVisibility(View.INVISIBLE);

                            status_1_txt.setTypeface(status_3_txt.getTypeface(), Typeface.NORMAL);
                            status_2_txt.setTypeface(status_3_txt.getTypeface(), Typeface.BOLD);
                            status_3_txt.setTypeface(status_3_txt.getTypeface(), Typeface.NORMAL);

                            status_3_txt.setTextColor(getResources().getColor(R.color.unselected));


                            status_3_img.setBackground(getResources().getDrawable(R.drawable.circle_status_un_selected));

                        } else if (requestType.equals("send_customer_offer_status_1")) {
                            String message = response.getString("message");


                            WebService.Make_Toast_color(DetailsOrderActivity.this, message, "success");
                            status_1_txt_s.setVisibility(View.INVISIBLE);
                            status_2_txt_s.setVisibility(View.INVISIBLE);

                            status_1.setVisibility(View.GONE);
                            status_2.setVisibility(View.GONE);
                            status_3.setVisibility(View.GONE);
                            status_4.setVisibility(View.GONE);
                            status_5.setVisibility(View.VISIBLE);
                            mobile_and_name.setVisibility(View.VISIBLE);
                            status_6.setVisibility(View.GONE);

                            cancleorder.setVisibility(View.GONE);
                            status_3_txt.setTypeface(status_3_txt.getTypeface(), Typeface.BOLD);


                        } else if (requestType.equals("send_customer_offer_status_2")) {
                            String message = response.getString("message");


                            WebService.Make_Toast_color(DetailsOrderActivity.this, message, "success");

                            status_1_txt_s.setVisibility(View.INVISIBLE);
                            status_2_txt_s.setVisibility(View.INVISIBLE);

                            status_1.setVisibility(View.GONE);
                            status_2.setVisibility(View.GONE);
                            status_3.setVisibility(View.GONE);
                            status_4.setVisibility(View.GONE);
                            status_5.setVisibility(View.GONE);
                            status_6.setVisibility(View.VISIBLE);
                            mobile_and_name.setVisibility(View.VISIBLE);
                            cancleorder.setVisibility(View.GONE);
                            status_3_txt_s.setTextColor(getResources().getColor(R.color.error));
                            status_3_txt_s.setText(getResources().getString(R.string.unacceptable));
                            status_3_txt.setTypeface(status_3_txt.getTypeface(), Typeface.BOLD);
                        } else if (requestType.equals("cancel//fund/offer")) {
                            String message = response.getString("message");


                            WebService.Make_Toast_color(DetailsOrderActivity.this, message, "success");

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
package sa.aqarz.Activity.RealState;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import sa.aqarz.Dialog.BottomSheetDialogFragment_ConfirmationCode;
import sa.aqarz.Dialog.BottomSheetDialogFragment_Congratulations;
import sa.aqarz.Modules.MyOfferModule;
import sa.aqarz.R;
import sa.aqarz.Settings.Application;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class OfferDetailsActivity2 extends AppCompatActivity {
    ImageView back;
    TextView name;
    TextView names;
    TextView number_order;
    public static MyOfferModule myOfferModule;

    static LinearLayout status_1;
    static LinearLayout status_2;
    static LinearLayout status_3;
    static LinearLayout status_4;
    static LinearLayout status_ss;
    IResult mResultCallback;

    static BottomSheetDialogFragment_ConfirmationCode bottomSheetDialogFragment_confirmationCode;
    Button con;
    Button requst_code;
    LinearLayout call;
    static TextView cancleorder;
    public static Activity activity;
    public static FragmentManager ft;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);
        back = findViewById(R.id.back);
        activity = this;
        number_order = findViewById(R.id.number_order);
        name = findViewById(R.id.name);
        status_1 = findViewById(R.id.status_1);
        status_2 = findViewById(R.id.status_2);
        status_3 = findViewById(R.id.status_3);
        status_4 = findViewById(R.id.status_4);
        status_ss = findViewById(R.id.status_ss);
        call = findViewById(R.id.call);
        cancleorder = findViewById(R.id.cancleorder);
        names = findViewById(R.id.names);
        con = findViewById(R.id.con);
        requst_code = findViewById(R.id.requst_code);
        ft = ((FragmentActivity) activity).getSupportFragmentManager();

        myOfferModule = Application.myOfferModule;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    String phone = "0" + myOfferModule.getBeneficiaryMobile() + "";
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }


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
                WebService.loading(OfferDetailsActivity2.this, true);
                VolleyService mVolleyService = new VolleyService(mResultCallback, OfferDetailsActivity2.this);

                System.out.println(sendObj.toString());
                mVolleyService.postDataasync_with_file("cancel//fund/offer", WebService.cancel + "/" + myOfferModule.getId() + "/" + WebService.rate_offer, sendObj);


            }
        });
        requst_code.setOnClickListener(new View.OnClickListener() {
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
                WebService.loading(OfferDetailsActivity2.this, true);
                VolleyService mVolleyService = new VolleyService(mResultCallback, OfferDetailsActivity2.this);

                System.out.println(sendObj.toString());
                mVolleyService.postDataVolley("provider_code_send", WebService.provider_code_send, sendObj);


            }
        });
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialogFragment_confirmationCode = new BottomSheetDialogFragment_ConfirmationCode(myOfferModule.getUuid() + "", myOfferModule.getEstateId() + "");
                bottomSheetDialogFragment_confirmationCode.show(getSupportFragmentManager(), "");

            }
        });

        name.setText(myOfferModule.getBeneficiaryName() + "");
        names.setText(myOfferModule.getBeneficiaryName() + "");
        number_order.setText(myOfferModule.getId() + "");
        System.out.println("dsdfsdsd" + myOfferModule.getStatus());


        //                if(alldata.get(position).getBeneficiaryMobile()!=null) {


        if (myOfferModule.getStatus() == null) {
            status_1.setVisibility(View.VISIBLE);
            status_2.setVisibility(View.GONE);
            status_3.setVisibility(View.GONE);
            status_4.setVisibility(View.GONE);
            status_ss.setVisibility(View.GONE);
            call.setBackground(getDrawable(R.drawable.mash));
//            call.set(getColor(R.color.textColor2));
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else if (myOfferModule.getStatus().equals("active")) {
            status_1.setVisibility(View.VISIBLE);
            status_2.setVisibility(View.GONE);
            status_3.setVisibility(View.GONE);
            status_4.setVisibility(View.GONE);
            status_ss.setVisibility(View.VISIBLE);

//            cancleorder.setBackground(getDrawable(R.drawable.button_cancle));
//            cancleorder.setTextColor(getColor(R.color.white));


        } else if (myOfferModule.getStatus().equals("Waiting_provider_accepted")) {
            status_1.setVisibility(View.GONE);
            status_2.setVisibility(View.VISIBLE);
            status_3.setVisibility(View.GONE);
            status_4.setVisibility(View.GONE);
            status_ss.setVisibility(View.VISIBLE);

//            cancleorder.setBackground(getDrawable(R.drawable.button_cancle));
//            cancleorder.setTextColor(getColor(R.color.white));


        } else if (myOfferModule.getStatus().equals("sending_code")) {
            status_1.setVisibility(View.GONE);
            status_2.setVisibility(View.VISIBLE);
            status_3.setVisibility(View.GONE);
            status_ss.setVisibility(View.GONE);
            status_4.setVisibility(View.GONE);

//            cancleorder.setBackground(getDrawable(R.drawable.button_cancle));
//            cancleorder.setTextColor(getColor(R.color.white));


        } else if (myOfferModule.getStatus().equals("rejected_customer ")) {

            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$4");


//            cancleorder.setTextColor(getColor(R.color.textColor2));

//            cancleorder.setBackground(getDrawable(R.drawable.mash));
            status_1.setVisibility(View.GONE);
            status_2.setVisibility(View.GONE);
            status_3.setVisibility(View.GONE);
            status_4.setVisibility(View.GONE);
            status_ss.setVisibility(View.GONE);
        } else if (myOfferModule.getStatus().equals("accepted_customer")) {
            status_1.setVisibility(View.GONE);
            status_2.setVisibility(View.GONE);
            status_ss.setVisibility(View.GONE);
            status_3.setVisibility(View.VISIBLE);
            cancleorder.setTextColor(getColor(R.color.textColor2));

            cancleorder.setBackground(getDrawable(R.drawable.mash));
            cancleorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            status_4.setVisibility(View.GONE);
        }

    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(OfferDetailsActivity2.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {

                        if (requestType.equals("provider_code_send")) {
                            String message = response.getString("message");
                            WebService.Make_Toast_color(OfferDetailsActivity2.this, message, "success");

                            status_1.setVisibility(View.GONE);
                            status_2.setVisibility(View.VISIBLE);
                            status_3.setVisibility(View.GONE);
                            status_ss.setVisibility(View.GONE);
                            status_4.setVisibility(View.VISIBLE);


                        } else {
                            String message = response.getString("message");
                            WebService.Make_Toast_color(OfferDetailsActivity2.this, message, "success");

                            status_1.setVisibility(View.GONE);
                            status_2.setVisibility(View.GONE);
                            status_3.setVisibility(View.GONE);
                            status_4.setVisibility(View.GONE);
//                        cancleorder.setVisibility(View.GONE);
                            cancleorder.setBackground(getDrawable(R.drawable.mash));
//                        cancleorder.setTextColor(getColor(R.color.textColor2));

                            cancleorder.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                        }


                    } else {
                        String message = response.getString("message");


                        WebService.Make_Toast_color(OfferDetailsActivity2.this, message, "error");

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
                    WebService.loading(OfferDetailsActivity2.this, false);

//                    progress.setVisibility(View.GONE);

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(OfferDetailsActivity2.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(OfferDetailsActivity2.this, false);

            }
        };


    }


    public static void finishs() {

        bottomSheetDialogFragment_confirmationCode.dismiss();
//        activity.finish();

        try {
            BottomSheetDialogFragment_Congratulations bottomSheetDialogFragmentCongratulations = new BottomSheetDialogFragment_Congratulations(myOfferModule.getId() + "");
            bottomSheetDialogFragmentCongratulations.show(ft, "");
            status_1.setVisibility(View.GONE);
            status_2.setVisibility(View.GONE);
            status_ss.setVisibility(View.GONE);
            status_3.setVisibility(View.VISIBLE);
            cancleorder.setTextColor(activity.getResources().getColor(R.color.textColor2));

            cancleorder.setBackground(activity.getResources().getDrawable(R.drawable.mash));
            cancleorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            status_4.setVisibility(View.GONE);
        } catch (Exception e) {

        }

    }

}
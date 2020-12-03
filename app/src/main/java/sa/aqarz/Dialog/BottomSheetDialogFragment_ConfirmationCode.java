package sa.aqarz.Dialog;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import de.hdodenhof.circleimageview.CircleImageView;
import sa.aqarz.Activity.Auth.ConfirmationActivity;
import sa.aqarz.Activity.Auth.NewConfirmationActivity;
import sa.aqarz.Activity.RealState.AllOfferOrderActivity;
import sa.aqarz.Adapter.RecyclerView_MyState;
import sa.aqarz.Adapter.RecyclerView_ordersx;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class BottomSheetDialogFragment_ConfirmationCode extends BottomSheetDialogFragment {
    IResult mResultCallback;


    EditText a1, a2, a3, a4;
    String uuid_r = "";
    Button confirmation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheets_confirmation_code, container, false);

        a1 = v.findViewById(R.id.a1);
        a2 = v.findViewById(R.id.a2);
        a3 = v.findViewById(R.id.a3);
        a4 = v.findViewById(R.id.a4);
        confirmation = v.findViewById(R.id.confirmation);


        a1.requestFocus();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        a1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (a1.length() != 0) {
                    a2.requestFocus();
                }


            }

        });


        a2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (a2.length() != 0) {
                    a3.requestFocus();
                } else {
                    a1.requestFocus();

                }


            }

        });

        a3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (a3.length() != 0) {
                    a4.requestFocus();
                } else {
                    a2.requestFocus();

                }


            }

        });
        a4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (a4.length() != 0) {

                } else {
                    a3.requestFocus();

                }


            }

        });


        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (a1.getText().toString().equals("") |
                        a2.getText().toString().equals("") |
                        a3.getText().toString().equals("") |
                        a4.getText().toString().equals("")
                ) {

                } else {
                    String code2 = a1.getText().toString() +
                            a2.getText().toString() +
                            a3.getText().toString() +
                            a4.getText().toString() + "";


                    RequestParams sendObj = new RequestParams();

                    try {

                        sendObj.put("uuid", uuid_r);//form operation list api in setting
                        sendObj.put("code", code2);//form estate type list api in setting
                        sendObj.put("status", "accepted_customer");//form estate type list api in setting
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    init_volley();
                    WebService.loading(getActivity(), true);
                    VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());

                    System.out.println(sendObj.toString());
                    mVolleyService.postDataasync_with_file("send_offer_fund_Request", WebService.send_offer_fund_Request, sendObj);


                }


            }
        });


        return v;


    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_ConfirmationCode(String uuid) {
        uuid_r = uuid;

    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.bottom_sheets_qr, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }


    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int id_city, String city_naem);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Window window = getDialog().getWindow();
            window.findViewById(com.google.android.material.R.id.container).setFitsSystemWindows(false);
            // dark navigation bar icons
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(getActivity(), false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {

                        if (requestType.equals("send_offer_fund_Request")) {


                            String message = response.getString("message");
                            WebService.Make_Toast_color(getActivity(), message, "success");


                        }
                    } else {
                        String message = response.getString("message");


                        WebService.Make_Toast_color(getActivity(), message, "error");

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
                    WebService.loading(getActivity(), false);



                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(getActivity(), message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(getActivity(), false);

            }
        };


    }

}

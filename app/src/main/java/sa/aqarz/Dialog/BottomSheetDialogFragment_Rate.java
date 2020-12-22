package sa.aqarz.Dialog;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
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
import com.hedgehog.ratingbar.RatingBar;
import com.willy.ratingbar.ScaleRatingBar;

import org.json.JSONObject;

import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Activity.RealState.MyOfferOrderActivity;
import sa.aqarz.Activity.RealState.OfferDetailsActivity;
import sa.aqarz.Fragment.OrdersFragment;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class BottomSheetDialogFragment_Rate extends BottomSheetDialogFragment {
    IResult mResultCallback;


    Button send;
    EditText comment;
    RatingBar rate;
    ImageView close;
    String estate_id = "";
    String ratee = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheets_rate, container, false);
        send = v.findViewById(R.id.send);
        rate = v.findViewById(R.id.rate);
        comment = v.findViewById(R.id.comment);
        close = v.findViewById(R.id.close);

        rate.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                ratee = RatingCount + "";
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                JSONObject sendObj = new JSONObject();

                try {

                    sendObj.put("estate_id", estate_id);//form operation list api in setting
                    sendObj.put("rate", ratee);//form operation list api in setting
                    sendObj.put("note", comment.getText().toString());//form operation list api in setting


                } catch (Exception e) {
                    e.printStackTrace();
                }
                init_volley();
                WebService.loading(getActivity(), true);
                VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());

                System.out.println(sendObj.toString());
                mVolleyService.postDataVolley("rate_estate", WebService.rate_estate, sendObj);


            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;


    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_Rate(String estate_idd) {
        estate_id = estate_idd;
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

                        if (requestType.equals("rate_estate")) {

//
                            String message = response.getString("message");
                            WebService.Make_Toast_color(DetailsActivity_aqarz.activity, message, "success");


                            dismiss();

                        } else if (requestType.equals("provider_code_send")) {
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

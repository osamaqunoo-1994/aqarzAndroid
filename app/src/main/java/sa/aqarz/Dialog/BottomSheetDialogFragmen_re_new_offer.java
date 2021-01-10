package sa.aqarz.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hedgehog.ratingbar.RatingBar;

import org.json.JSONObject;

import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Activity.profile.OtherProfileActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class BottomSheetDialogFragmen_re_new_offer extends BottomSheetDialogFragment {
    IResult mResultCallback;

    Button resend;

    Button sensd;
    EditText comment;
    RatingBar rate;
    ImageView close;
    String estate_id = "";
    String ratee = "";
    private ItemClickListener mItemClickListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheets_re_new_offer, container, false);

        sensd = v.findViewById(R.id.sensd);
        resend = v.findViewById(R.id.resend);
        close = v.findViewById(R.id.close);


        sensd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                init_volley();
//                VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
//

                WebService.loading(getActivity(), true);

                init_volleys();


                VolleyService volleyServicedd = new VolleyService(mResultCallback, getContext());

                volleyServicedd.postDataVolley("make_up", WebService.make_up + "/" + estate_id + "/estate", new JSONObject());


//                mVolleyService.getDataVolley("make_up", WebService.make_up + "/" + estate_id + "/estate");
//
//                mVolleyService.getDataVolley("delete", WebService.delete + "/" + estate_id + "/estate");

            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
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

    public BottomSheetDialogFragmen_re_new_offer(String estate_idd) {
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
        void onItemClick(int id_estat);
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

    public void init_volleys() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(getActivity(), false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {

                        String data = response.getString("data");
                        String message = response.getString("message");
                        dismiss();

                        if (requestType.equals("delete")) {
                            System.out.println("estate_id" + estate_id);

                            if (mItemClickListener != null) {
                                mItemClickListener.onItemClick(Integer.valueOf(estate_id + ""));
                            }

                        } else if (requestType.equals("make_up")) {

                            if (mItemClickListener != null) {
                                mItemClickListener.onItemClick(Integer.valueOf(estate_id + ""));
                            }
                            dismiss();

                        }
//                        String message = response.getString("message");


//                        WebService.Make_Toast_color((Activity) context, message, "success");


                    }
                } catch (Exception e) {

                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading((Activity) getActivity(), false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color((Activity) getActivity(), message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading((Activity) getActivity(), false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading((Activity) getActivity(), false);

                WebService.Make_Toast_color((Activity) getActivity(), error, "error");


            }
        };


    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }


}

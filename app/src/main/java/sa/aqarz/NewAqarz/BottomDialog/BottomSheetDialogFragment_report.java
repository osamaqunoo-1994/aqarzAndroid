package sa.aqarz.NewAqarz.BottomDialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.ReportAqarezActivity;
import sa.aqarz.Adapter.RecyclerView_area_range;
import sa.aqarz.Adapter.RecyclerView_city_side_menu;
import sa.aqarz.Adapter.RecyclerView_price_range;
import sa.aqarz.Modules.Allprice_range;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.Modules.select_typeModules;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_All_type_fillter_order;
import sa.aqarz.NewAqarz.MainAqarzActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class BottomSheetDialogFragment_report extends BottomSheetDialogFragment {
    ItemClickListener mItemClickListener;

    IResult mResultCallback;

    Button send;
    ImageView back;

    RadioButton st_1, st_2, st_3, st_4;
    EditText edt_des;
    String estate_id = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_report_aqarez, container, false);
//        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) v.findViewById(R.id.rangeSeekbar5);
        send = v.findViewById(R.id.send);
        back = v.findViewById(R.id.back);
        st_1 = v.findViewById(R.id.st_1);
        st_2 = v.findViewById(R.id.st_2);
        st_3 = v.findViewById(R.id.st_3);
        st_4 = v.findViewById(R.id.st_4);
        edt_des = v.findViewById(R.id.edt_des);


        try {
            estate_id = getActivity().getIntent().getStringExtra("estate_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//('error_data', 'error_image', 'other')

                if (Settings.checkLogin()) {

                    String type = "";
                    if (st_1.isChecked()) {
                        type = "error_data";
                    }
                    if (st_2.isChecked()) {
                        type = "error_image";

                    } if (st_4.isChecked()) {
                        type = "contact_error";

                    }
                    if (st_3.isChecked()) {
                        type = "other";

                    }

                    init_volley();

                    VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
                    JSONObject jsonObject = new JSONObject();

                    try {
                        jsonObject.put("report_type", type);
                        jsonObject.put("estate_id", estate_id);

                        jsonObject.put("body", edt_des.getText().toString());


                    } catch (Exception e) {

                    }

                    WebService.loading(getActivity(), true);
                    mVolleyService.postDataVolley("report", WebService.report, jsonObject);

                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));

                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;
    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_report(String id_) {
        estate_id = id_;
    }


    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(String filter);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.bottom_sheets_details_aqares, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
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
                        String message = response.getString("message");


                        WebService.Make_Toast_color(getActivity(), message, "success");


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
                WebService.loading(getActivity(), false);

                try {

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

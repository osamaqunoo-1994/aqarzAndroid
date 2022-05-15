package sa.aqarz.Dialog;

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

import org.json.JSONObject;

import sa.aqarz.Activity.Employee.DetailsEmployeeActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class BottomSheetDialogFragmen_deleteAqarz extends BottomSheetDialogFragment {
    IResult mResultCallback;

    EditText name;
    EditText phone;
    ImageView back;
    Button add_employee;


    private ItemClickListener mItemClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_details_aqarz, container, false);


        name = v.findViewById(R.id.name);
        phone = v.findViewById(R.id.phone);
        add_employee = v.findViewById(R.id.add_employee);
        back = v.findViewById(R.id.close);


        add_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("") |
                        phone.getText().toString().equals("")) {

                } else {
                    WebService.loading(getActivity(), true);
                    init_volley();

                    VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());


                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("emp_name", name.getText().toString());
                        sendObj.put("emp_mobile", phone.getText().toString());
                        sendObj.put("country_code", "966");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mVolleyService.postDataVolley("add_employee", WebService.add_employee, sendObj);


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

    public BottomSheetDialogFragmen_deleteAqarz() {
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.bottom_sheets_qr, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }


    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int id_estate);
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
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(getActivity(), false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {

                        String data = response.getString("data");
                        dismiss();


                        DetailsEmployeeActivity.openSuccess(name.getText().toString());

//                        String message = response.getString("message");

//                        WebService.Make_Toast_color((Activity) context, message, "success");


                    }
                } catch (Exception e) {

                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

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

                WebService.loading(getActivity(), false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(getActivity(), false);

                WebService.Make_Toast_color(getActivity(), error, "error");


            }
        };


    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }


}

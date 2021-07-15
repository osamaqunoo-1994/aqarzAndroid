package sa.aqarz.Activity.Employee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.RequestParams;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Dialog.BottomSheetDialogFragment_Secess;
import sa.aqarz.Modules.User;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class AddEmployeeActivity extends AppCompatActivity {
    EditText name;
    EditText phone;
    ImageView back;
    Button add_employee;
    IResult mResultCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        add_employee = findViewById(R.id.add_employee);
        back = findViewById(R.id.back);


        add_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("") |
                        phone.getText().toString().equals("")) {

                } else {
                    WebService.loading(AddEmployeeActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, AddEmployeeActivity.this);

                    JSONObject sendObj = new JSONObject();

                    try {
                        sendObj.put("emp_name", name.getText().toString());
                        sendObj.put("emp_mobile", phone.getText().toString());
                        sendObj.put("country_code", "966");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    init_volley();

                    mVolleyService.postDataVolley("add_employee", WebService.add_employee, sendObj);


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
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(AddEmployeeActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
//                        String data = response.getString("data");


                        BottomSheetDialogFragment_Secess bottomSheetDialogFragment_secess = new BottomSheetDialogFragment_Secess(name.getText().toString() + "");
                        bottomSheetDialogFragment_secess.show(getSupportFragmentManager(), "");


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(AddEmployeeActivity.this, message, "error");
                    }


                } catch (Exception e) {

                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());

                try {


                    WebService.Make_Toast_color(AddEmployeeActivity.this, error.getMessage(), "error");

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);


                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


                WebService.loading(AddEmployeeActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                try {

                    Log.d("TAG", "Volley requester " + requestType);
                    Log.d("TAG", "Volley JSON post" + "That didn't work!" + error);
                    WebService.Make_Toast_color(AddEmployeeActivity.this, error, "error");


//                    WebService.Make_Toast_color(AddEmployeeActivity.this, message, "error");

//                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


                WebService.loading(AddEmployeeActivity.this, false);

            }
        };


    }

}
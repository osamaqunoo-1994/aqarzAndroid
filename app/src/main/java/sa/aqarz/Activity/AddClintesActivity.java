package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.OprationNew.AqarzOrActivity;
import sa.aqarz.Activity.OprationNew.FinanceActivity;
import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.Modules.User;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class AddClintesActivity extends AppCompatActivity {
    TextView Aqarez;
    TextView other;
    String tenant_job_type = "aqarz";
    String fir_type = "less";

    Button add;
    TextView low;
    TextView normal;
    TextView hight;
    List<TypeModules> type_list = new ArrayList<>();


    LinearLayout other_layout;
    TextView time;
    EditText from_;
    EditText ads_number;
    EditText mobile;
    EditText name;
    ImageView back;
    RecyclerView opration_RecyclerView;

    String opration_select = "1";
    IResult mResultCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clintes);
        opration_RecyclerView = findViewById(R.id.opration_RecyclerView);

        add = findViewById(R.id.add);
        time = findViewById(R.id.time);
        from_ = findViewById(R.id.otherFrom);
        ads_number = findViewById(R.id.ads_number);
        mobile = findViewById(R.id.mobile);
        name = findViewById(R.id.name);
        back = findViewById(R.id.back);

        other = findViewById(R.id.other);
        Aqarez = findViewById(R.id.Aqarez);

        low = findViewById(R.id.low);
        normal = findViewById(R.id.normal);
        hight = findViewById(R.id.hight);
        other_layout = findViewById(R.id.other_layout);


        low = findViewById(R.id.low);
        normal = findViewById(R.id.normal);
        hight = findViewById(R.id.hight);

        Aqarez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aqarez.setBackground(getResources().getDrawable(R.drawable.button_login));

                Aqarez.setTextColor(getResources().getColor(R.color.white));


                other.setBackground(null);

                other.setTextColor(getResources().getColor(R.color.color_filter));
                tenant_job_type = "aqarz";
                other_layout.setVisibility(View.GONE);

            }
        });


        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                other.setBackground(getResources().getDrawable(R.drawable.button_login));

                other.setTextColor(getResources().getColor(R.color.white));


                Aqarez.setBackground(null);

                Aqarez.setTextColor(getResources().getColor(R.color.color_filter));
                tenant_job_type = "other";
                other_layout.setVisibility(View.VISIBLE);
            }
        });


        low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                low.setBackground(getResources().getDrawable(R.drawable.button_login));

                low.setTextColor(getResources().getColor(R.color.white));


                normal.setBackground(null);

                normal.setTextColor(getResources().getColor(R.color.color_filter));
                hight.setBackground(null);

                hight.setTextColor(getResources().getColor(R.color.color_filter));
                fir_type = "less";

            }
        });
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normal.setBackground(getResources().getDrawable(R.drawable.button_login));

                normal.setTextColor(getResources().getColor(R.color.white));


                low.setBackground(null);

                low.setTextColor(getResources().getColor(R.color.color_filter));
                hight.setBackground(null);

                hight.setTextColor(getResources().getColor(R.color.color_filter));
                fir_type = "orginal";

            }
        });
        hight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hight.setBackground(getResources().getDrawable(R.drawable.button_login));

                hight.setTextColor(getResources().getColor(R.color.white));


                low.setBackground(null);

                low.setTextColor(getResources().getColor(R.color.color_filter));

                normal.setBackground(null);

                normal.setTextColor(getResources().getColor(R.color.color_filter));
                fir_type = "hight";

            }
        });
        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener datebitth = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                String myFormat = "yyyy-MM-dd"; //In which you need put here 2020-02-15
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                time.setText(sdf.format(myCalendar.getTime()));
            }

        };
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddClintesActivity.this, datebitth, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();


        LinearLayoutManager layoutManagerw
                = new LinearLayoutManager(AddClintesActivity.this, LinearLayoutManager.HORIZONTAL, false);
        opration_RecyclerView.setLayoutManager(layoutManagerw);

        RecyclerView_All_opration_bottom_sheet recyclerView_all_opration_bottom_sheet = new RecyclerView_All_opration_bottom_sheet(AddClintesActivity.this, type_list);
        recyclerView_all_opration_bottom_sheet.addItemClickListener(new RecyclerView_All_opration_bottom_sheet.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                opration_select = type_list.get(position).getId().toString() + "";


            }
        });
        opration_RecyclerView.setAdapter(recyclerView_all_opration_bottom_sheet);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mobile.getText().toString().equals("") | name.getText().toString().equals("") | ads_number.getText().toString().equals("")) {
                    WebService.Make_Toast(AddClintesActivity.this, getResources().getString(R.string.fillallfileds));
                } else {
                    init_volley();

                    WebService.loading(AddClintesActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, AddClintesActivity.this);

                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("client_name", name.getText().toString());
                        sendObj.put("client_mobile", mobile.getText().toString());
                        sendObj.put("source_type", tenant_job_type);
                        sendObj.put("request_type", opration_select);
                        sendObj.put("ads_number", mobile.getText().toString());
                        sendObj.put("priority", fir_type);
                        sendObj.put("remember", "0");
                        sendObj.put("remember_date_time", "2020-11-9 04:45:00");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mVolleyService.postDataVolley("add_client", WebService.add_client, sendObj);


                }


            }
        });


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(AddClintesActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

                        String message = response.getString("message");

                        WebService.Make_Toast_color(AddClintesActivity.this, message, "success");

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(AddClintesActivity.this, message, "error");
                    }


                } catch (Exception e) {

                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(AddClintesActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


                WebService.loading(AddClintesActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

}
package sa.aqarz.Activity.Employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.FavoriteActivity;
import sa.aqarz.Adapter.RecyclerView_AllEmployee;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat_favorit;
import sa.aqarz.Adapter.RecyclerView_orders_demandsx_favorit;
import sa.aqarz.Adapter.RecyclerView_orders_my_requstx_favorit;
import sa.aqarz.Adapter.RecyclerView_orders_my_requstx_favorit_offet;
import sa.aqarz.Modules.FavoritModules;
import sa.aqarz.Modules.User;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.Modules.mod_favorite;
import sa.aqarz.Modules.of_favorite;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class DetailsEmployeeActivity extends AppCompatActivity {
    RecyclerView all_employee;

    IResult mResultCallback;

    ImageView back;
    ImageView search;
    EditText search_edt;

    List<User> all_employee_list = new ArrayList<>();
    LinearLayout no_data_;

    FloatingActionButton add_employee;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        all_employee = findViewById(R.id.all_employee);
        add_employee = findViewById(R.id.add_employee);
        back = findViewById(R.id.back);
        search = findViewById(R.id.search);
        search_edt = findViewById(R.id.search_edt);
        no_data_ = findViewById(R.id.no_data_);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        all_employee.setLayoutManager(new GridLayoutManager(this, 2));


        WebService.loading(DetailsEmployeeActivity.this, true);


        init_volley();

        VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsEmployeeActivity.this);

        mVolleyService.getDataVolley("my_employee", WebService.my_employee);

        swipe = findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                WebService.loading(DetailsEmployeeActivity.this, true);


                init_volley();

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsEmployeeActivity.this);

                mVolleyService.getDataVolley("my_employee", WebService.my_employee + "?emp_name=" + search_edt.getText().toString());


                swipe.setRefreshing(false);
            }
        });
        search_edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    WebService.loading(DetailsEmployeeActivity.this, true);


                    init_volley();

                    VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsEmployeeActivity.this);

                    mVolleyService.getDataVolley("my_employee", WebService.my_employee + "?emp_name=" + search_edt.getText().toString());


                    return true;
                }
                return false;
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                WebService.loading(DetailsEmployeeActivity.this, true);


                init_volley();

                VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsEmployeeActivity.this);

                mVolleyService.getDataVolley("my_employee", WebService.my_employee + "?emp_name=" + search_edt.getText().toString());


            }
        });


        add_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailsEmployeeActivity.this, AddEmployeeActivity.class);
                startActivity(intent);


            }
        });
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(DetailsEmployeeActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {

                    boolean status = response.getBoolean("status");
                    if (status) {
//                        alldate.setAdapter(null);


                        String message = response.getString("message");


                        String data = response.getString("data");
                        JSONObject jsonArray = new JSONObject(data);

                        JSONArray jsonArray1 = new JSONArray(jsonArray.getString("data"));
                        all_employee_list.clear();
                        for (int i = 0; i < jsonArray1.length(); i++) {

                            try {

                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray1.getString(i));

                                Gson gson = new Gson();

                                User ordersModulesm = gson.fromJson(mJson, User.class);
                                all_employee_list.add(ordersModulesm);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                        all_employee.setAdapter(new RecyclerView_AllEmployee(DetailsEmployeeActivity.this, all_employee_list));


                        if (all_employee_list.size() == 0) {
                            no_data_.setVisibility(View.VISIBLE);
                        } else {
                            no_data_.setVisibility(View.GONE);

                        }


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(DetailsEmployeeActivity.this, message, "error");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(DetailsEmployeeActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(DetailsEmployeeActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(DetailsEmployeeActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(DetailsEmployeeActivity.this, false);

                WebService.Make_Toast_color(DetailsEmployeeActivity.this, error, "error");


            }
        };


    }

}
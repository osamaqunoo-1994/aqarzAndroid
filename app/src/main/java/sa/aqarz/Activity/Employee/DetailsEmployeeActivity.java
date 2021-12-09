package sa.aqarz.Activity.Employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
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
import com.loopj.android.http.RequestParams;

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
import sa.aqarz.Dialog.BottomSheetDialogFragmen_add_employee;
import sa.aqarz.Dialog.BottomSheetDialogFragment_Secess;
import sa.aqarz.Dialog.BottomSheetDialogFragment_Secess__add_employee;
import sa.aqarz.Dialog.BottomSheetDialogFragment_delete_employee;
import sa.aqarz.Modules.AllEmployee;
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
    static RecyclerView all_employee;

    static IResult mResultCallback;

    ImageView back;
    ImageView search;
    EditText search_edt;

    static List<AllEmployee> all_employee_list = new ArrayList<>();
    static LinearLayout no_data_;

    LinearLayout add_employee;
    SwipeRefreshLayout swipe;
    static FragmentManager activity;


    static Activity activity_th;


    static RecyclerView_AllEmployee recyclerView_allEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        activity = getSupportFragmentManager();
        activity_th = this;
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
        recyclerView_allEmployee = new RecyclerView_AllEmployee(DetailsEmployeeActivity.this, all_employee_list);
        all_employee.setAdapter(recyclerView_allEmployee);

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

//                Intent intent = new Intent(DetailsEmployeeActivity.this, AddEmployeeActivity.class);
//                startActivity(intent);
                BottomSheetDialogFragmen_add_employee bottomSheetDialogFragmen_add_employee = new BottomSheetDialogFragmen_add_employee();
                bottomSheetDialogFragmen_add_employee.show(getSupportFragmentManager(), "");

            }
        });
    }

    public static void openSuccess(String name) {


        BottomSheetDialogFragment_Secess__add_employee bottomSheetDialogFragment_secess = new BottomSheetDialogFragment_Secess__add_employee(name + "");
        bottomSheetDialogFragment_secess.show(activity, "");


        init_volley();

        VolleyService mVolleyService = new VolleyService(mResultCallback, activity_th);

        mVolleyService.getDataVolley("my_employee", WebService.my_employee);


    }

    public static void deleteEmployee(String emp_mobile) {

        BottomSheetDialogFragment_delete_employee bottomSheetDialogFragment_delete_employee = new BottomSheetDialogFragment_delete_employee("");
        bottomSheetDialogFragment_delete_employee.addItemClickListener(new BottomSheetDialogFragment_delete_employee.ItemClickListener() {
            @Override
            public void onItemClick(String mobile) {


                WebService.loading(activity_th, true);


                init_volley();

                VolleyService mVolleyService = new VolleyService(mResultCallback, activity_th);

                try {

                    RequestParams requestParams = new RequestParams();

                    requestParams.put("emp_mobile", emp_mobile + "");
                    requestParams.put("country_code", "966");

                    mVolleyService.postDataasync_with_file("delete_employee", WebService.delete_employee, requestParams);

                } catch (Exception e) {

                }


            }
        });
        bottomSheetDialogFragment_delete_employee.show(activity, "");
    }

    public static void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(activity_th, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {

                    boolean status = response.getBoolean("status");
                    if (status) {
//                        alldate.setAdapter(null);


                        if (requestType.equals("delete_employee")) {
                            WebService.loading(activity_th, true);


                            init_volley();

                            VolleyService mVolleyService = new VolleyService(mResultCallback, activity_th);

                            mVolleyService.getDataVolley("my_employee", WebService.my_employee);


                        } else {


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

                                    AllEmployee ordersModulesm = gson.fromJson(mJson, AllEmployee.class);
                                    all_employee_list.add(ordersModulesm);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
//                        all_employee.setAdapter(new RecyclerView_AllEmployee(DetailsEmployeeActivity.this, all_employee_list));
                            recyclerView_allEmployee.Refr();

                            if (all_employee_list.size() == 0) {
                                no_data_.setVisibility(View.VISIBLE);
                            } else {
                                no_data_.setVisibility(View.GONE);

                            }
                        }

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(activity_th, message, "error");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(activity_th, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(activity_th, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(activity_th, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(activity_th, false);

                WebService.Make_Toast_color(activity_th, error, "error");


            }
        };


    }

}
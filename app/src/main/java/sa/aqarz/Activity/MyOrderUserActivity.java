package sa.aqarz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.RecyclerView_city_side_menu;
import sa.aqarz.Adapter.RecyclerView_orders_demandsx;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class MyOrderUserActivity extends AppCompatActivity {
    static IResult mResultCallback;
    int page = 1;
    static List<demandsModules> demandsModules_list = new ArrayList<>();
    static RecyclerView_orders_demandsx recyclerView_orders_demandsx;
    RecyclerView AllResultRec;
    static LinearLayout nodata_vis;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_user);

        try {


            nodata_vis = findViewById(R.id.nodata_vis);

            AllResultRec = findViewById(R.id.AllResultRec);
            back = findViewById(R.id.back);


            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(MyOrderUserActivity.this, LinearLayoutManager.VERTICAL, false);
            AllResultRec.setLayoutManager(layoutManager);


            recyclerView_orders_demandsx = new RecyclerView_orders_demandsx(MyOrderUserActivity.this, demandsModules_list);

            AllResultRec.setAdapter(recyclerView_orders_demandsx);

            AllResultRec.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (!recyclerView.canScrollVertically(1)) { //1 for down

                        page = page + 1;


                        System.out.println("liouiuyytryui");

                        WebService.loading(MyOrderUserActivity.this, true);
                        init_volley();
                        VolleyService mVolleyService = new VolleyService(mResultCallback, MyOrderUserActivity.this);
                        mVolleyService.getDataVolley("my_request", WebService.my_request + "&page=" + page);


                    }
                }
            });


            WebService.loading(MyOrderUserActivity.this, true);
            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, MyOrderUserActivity.this);
            mVolleyService.getDataVolley("my_request", WebService.my_request);//"?estate_type_id=" + opration_select + id_city_

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(MyOrderUserActivity.this, false);

//                type_requst = "" + requestType;


//{"status":true,"code":200,"message":"User Profile","data"
//allRequestFund":6165,"RequestFund":18,"myRequestFundOffer":4727


                try {


                    if (requestType.equals("my_request")) {


                        String data = response.getString("data");

                        JSONObject jsonObject_data = new JSONObject(data);


                        String data_inside = jsonObject_data.getString("data");
                        JSONArray jsonArray = new JSONArray(data_inside);
//                        orders_rec.setAdapter(null);

                        if (page == 1) {
                            demandsModules_list.clear();

                        }
                        for (int i = 0; i < jsonArray.length(); i++) {


                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(jsonArray.getString(i));

                            Gson gson = new Gson();

                            demandsModules ordersModulesm = gson.fromJson(mJson, demandsModules.class);
                            demandsModules_list.add(ordersModulesm);


                        }
                        recyclerView_orders_demandsx.Refr();

                        if (demandsModules_list.size() != 0) {
                            nodata_vis.setVisibility(View.GONE);
                        } else {
                            nodata_vis.setVisibility(View.VISIBLE);


                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());
                WebService.loading(MyOrderUserActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    if (requestType.equals("my_request")) {
//                        nodata_vis.setVisibility(View.VISIBLE);

                    } else if (requestType.equals("market_demands")) {
//                        nodata_vis.setVisibility(View.VISIBLE);

                    } else {
                        WebService.Make_Toast_color(MyOrderUserActivity.this, message, "error");

                    }


                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }


            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(MyOrderUserActivity.this, false);

            }


        };


    }

}
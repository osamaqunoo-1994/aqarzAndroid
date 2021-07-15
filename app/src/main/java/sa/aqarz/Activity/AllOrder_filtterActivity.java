package sa.aqarz.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import sa.aqarz.Adapter.RecyclerView_city_side_menu;
import sa.aqarz.Adapter.RecyclerView_ordersx;
import sa.aqarz.Dialog.BottomSheetDialogFragment_area_range;
import sa.aqarz.Dialog.BottomSheetDialogFragment_price_range;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.LocaleUtils;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class AllOrder_filtterActivity extends AppCompatActivity {

    static IResult mResultCallback;

    RecyclerView AllResultRec;
    ImageView back;
    LinearLayout nodata_vis;
    static List<OrdersModules> ordersModules = new ArrayList<>();
    RecyclerView_ordersx recyclerView_ordersx;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order_filrr);

        AllResultRec = findViewById(R.id.AllResultRec);
        back = findViewById(R.id.back);
        nodata_vis = findViewById(R.id.nodata_vis);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(AllOrder_filtterActivity.this, LinearLayoutManager.VERTICAL, false);
        AllResultRec.setLayoutManager(layoutManager1);

        page=1;
        try {


            init_volley();
            WebService.loading(AllOrder_filtterActivity.this, true);
            VolleyService mVolleyService = new VolleyService(mResultCallback, AllOrder_filtterActivity.this);

            recyclerView_ordersx = new RecyclerView_ordersx(AllOrder_filtterActivity.this, ordersModules);

            AllResultRec.setAdapter(recyclerView_ordersx);

            try {

                String type = getIntent().getStringExtra("type");

                if (type.equals("accetpt")) {
                    mVolleyService.getDataVolley("order", WebService.fund_Request + "?myOwn=1&offer_status=customer_accepted");

                } else if (type.equals("preview")) {
                    mVolleyService.getDataVolley("order", WebService.fund_Request + "?myOwn=1&offer_status=sending_code");

                } else if (type.equals("watiing")) {
                    mVolleyService.getDataVolley("order", WebService.fund_Request + "?myOwn=1&offer_status=new");

                } else if (type.equals("all")) {
                    mVolleyService.getDataVolley("order", WebService.fund_Request + "?myOwn=1");
                }

//            mVolleyService.getDataVolley("user", WebService.user + id + "");
            } catch (Exception e) {

            }


        } catch (Exception e) {

        }
        AllResultRec.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) { //1 for down

                    page = page + 1;


                    init_volley();
                    WebService.loading(AllOrder_filtterActivity.this, true);
                    VolleyService mVolleyService = new VolleyService(mResultCallback, AllOrder_filtterActivity.this);

                    try {

                        String type = getIntent().getStringExtra("type");

                        if (type.equals("accetpt")) {
                            mVolleyService.getDataVolley("order", WebService.fund_Request + "?myOwn=1&offer_status=customer_accepted" + "&page=" + page);

                        } else if (type.equals("preview")) {
                            mVolleyService.getDataVolley("order", WebService.fund_Request + "?myOwn=1&offer_status=sending_code" + "&page=" + page);

                        } else if (type.equals("watiing")) {
                            mVolleyService.getDataVolley("order", WebService.fund_Request + "?myOwn=1&offer_status=new" + "&page=" + page);

                        } else if (type.equals("all")) {
                            mVolleyService.getDataVolley("order", WebService.fund_Request + "?myOwn=1" + "&page=" + page);
                        }

//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                    } catch (Exception e) {

                    }

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
                WebService.loading(AllOrder_filtterActivity.this, false);

//                type_requst = "" + requestType;


//{"status":true,"code":200,"message":"User Profile","data"
//allRequestFund":6165,"RequestFund":18,"myRequestFundOffer":4727


                try {


                    if (requestType.equals("order")) {


                        String data = response.getString("data");

                        JSONObject jsonObject_data = new JSONObject(data);

                        String data_inside = jsonObject_data.getString("data");
                        JSONArray jsonArray = new JSONArray(data_inside);
//                        AllResultRec.setAdapter(null);

                        if (page == 0) {
                            ordersModules.clear();
                        }

                        for (int i = 0; i < jsonArray.length(); i++) {


                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(jsonArray.getString(i));

                            Gson gson = new Gson();

                            OrdersModules ordersModulesm = gson.fromJson(mJson, OrdersModules.class);
                            ordersModules.add(ordersModulesm);


                        }

                        recyclerView_ordersx.Refr();

                        if (ordersModules.size() != 0) {
                            nodata_vis.setVisibility(View.GONE);
//                            isLoading = true;
                        } else {
                            nodata_vis.setVisibility(View.VISIBLE);
//                            isLoading = false;

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
                WebService.loading(AllOrder_filtterActivity.this, false);

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
                        WebService.Make_Toast_color(AllOrder_filtterActivity.this, message, "error");

                    }


                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }


            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(AllOrder_filtterActivity.this, false);

            }


        };


    }

}
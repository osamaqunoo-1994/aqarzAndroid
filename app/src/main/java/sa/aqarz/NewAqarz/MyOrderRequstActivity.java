package sa.aqarz.NewAqarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.MyOrderActivity;
import sa.aqarz.Activity.OprationNew.AqarzOrActivity;
import sa.aqarz.Activity.OprationNew.RentActivity;
import sa.aqarz.Activity.OprationNew.RentShowActivity;
import sa.aqarz.Adapter.RecyclerView_order_finince;
import sa.aqarz.Adapter.RecyclerView_order_rate;
import sa.aqarz.Adapter.RecyclerView_orders_demandsx;
import sa.aqarz.Adapter.RecyclerView_orders_my_requstx;
import sa.aqarz.Adapter.RecyclerView_orders_my_requstx_det;
import sa.aqarz.Adapter.RecyclerView_orders_my_requstx_rate;
import sa.aqarz.Adapter.RecyclerView_orders_offer_di;
import sa.aqarz.Adapter.RecyclerView_ordersx;
import sa.aqarz.Modules.MyRateModules;
import sa.aqarz.Modules.OfferRealStateModules;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.Modules.RateModules;
import sa.aqarz.Modules.deferredInstallmentModules;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.Modules.financeModules;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_mu_souq_order;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class MyOrderRequstActivity extends AppCompatActivity {
    ImageView back;
    static IResult mResultCallback;

    RecyclerView orders_rec;
    List<demandsModules> MyRequst = new ArrayList<>();
    LinearLayout nodata_vis;
    static RecyclerView_mu_souq_order recyclerView_orders_demandsx;
    int page = 1;
    LinearLayout Request_property;
    LinearLayout rent_installment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_requst);
        back = findViewById(R.id.back);
        orders_rec = findViewById(R.id.orders_rec);
        nodata_vis = findViewById(R.id.nodata_vis);
        rent_installment = findViewById(R.id.rent_installment);
        Request_property = findViewById(R.id.Request_property);
        page = 1;

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        orders_rec.setLayoutManager(layoutManager1);


        try {
            String from = getIntent().getStringExtra("from");//more,home


        } catch (Exception e) {

        }

        rent_installment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MyOrderRequstActivity.this, AqarzOrActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);
            }
        });
        Request_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Hawk.contains("rent_layout")) {

                    Intent intent = new Intent(MyOrderRequstActivity.this, RentActivity.class);
                    intent.putExtra("id", "");
                    startActivity(intent);
                } else {
                    Hawk.put("rent_layout", "rent_layout");
                    Intent intent = new Intent(MyOrderRequstActivity.this, RentShowActivity.class);
                    intent.putExtra("id", "");
                    startActivity(intent);
                }
            }
        });


        MyRequst.clear();

        recyclerView_orders_demandsx = new RecyclerView_mu_souq_order(MyOrderRequstActivity.this, MyRequst);

        orders_rec.setAdapter(recyclerView_orders_demandsx);


        WebService.loading(MyOrderRequstActivity.this, true);
        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, MyOrderRequstActivity.this);
        mVolleyService.getDataVolley("my_request", WebService.my_request);
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
                WebService.loading(MyOrderRequstActivity.this, false);


//{"status":true,"code":200,"message":"User Profile","data"

                try {


                    boolean status = response.getBoolean("status");
                    if (status) {

                        if (requestType.equals("my_request")) {
                            System.out.println("lfkdlfkdlkf");
                            String data = response.getString("data");


                            JSONObject jsonObject = new JSONObject(data);


                            String datax = jsonObject.getString("data");


                            JSONArray jsonArray = new JSONArray(datax);
                            if (page == 1) {
                                MyRequst.clear();

                            }
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();
                                demandsModules ordersModulesm = gson.fromJson(mJson, demandsModules.class);

//                                HomeModules ordersModulesm = gson.fromJson(mJson, HomeModules.class);
                                MyRequst.add(ordersModulesm);


                            }

                            recyclerView_orders_demandsx.Refr();
//                            orders_rec.setAdapter(new RecyclerView_orders_my_requstx(MyOrderRequstActivity.this, MyRequst));

                            if (MyRequst.size() != 0) {
                                nodata_vis.setVisibility(View.GONE);
                            } else {
                                nodata_vis.setVisibility(View.VISIBLE);

                            }

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
                WebService.loading(MyOrderRequstActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }


            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(MyOrderRequstActivity.this, false);

            }


        };


    }

}
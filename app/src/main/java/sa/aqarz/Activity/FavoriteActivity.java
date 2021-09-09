package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.Auth.ChoseTypeActivity;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat_favorit;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat_new;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat_new_favorite;
import sa.aqarz.Adapter.RecyclerView_orders_demandsx;
import sa.aqarz.Adapter.RecyclerView_orders_demandsx_favorit;
import sa.aqarz.Adapter.RecyclerView_orders_demandsx_favorits_2;
import sa.aqarz.Adapter.RecyclerView_orders_my_requst;
import sa.aqarz.Adapter.RecyclerView_orders_my_requstx;
import sa.aqarz.Adapter.RecyclerView_orders_my_requstx_favorit;
import sa.aqarz.Adapter.RecyclerView_orders_my_requstx_favorit_offet;
import sa.aqarz.Adapter.RecyclerView_orders_offer_di;
import sa.aqarz.Adapter.RecyclerView_ordersx;
import sa.aqarz.Adapter.RecyclerView_ordersx_favorite;
import sa.aqarz.Modules.FavoritModules;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.OfferRealStateModules;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.Modules.fund_favorite;
import sa.aqarz.Modules.mod_favorite;
import sa.aqarz.Modules.of_favorite;
import sa.aqarz.NewAqarz.SelectFavCityActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class FavoriteActivity extends AppCompatActivity {

    LinearLayout my_order_layout;
    LinearLayout Shopping_request_layout;
    LinearLayout Real_Estate_order_layout;

    TextView my_order_text;
    TextView Shopping_request_text;
    TextView Real_Estate_order_text;

    List<FavoritModules> favoritModules_l = new ArrayList<>();

    RecyclerView alldate;
    IResult mResultCallback;
    List<fund_favorite> demandsModules_list = new ArrayList<>();
    List<mod_favorite> mod_favorites_ = new ArrayList<>();
    List<of_favorite> of_favorites = new ArrayList<>();

    ImageView back;

    LinearLayout nodata_vis;
    LinearLayout layout_ffavorit;

    TextView my_fav;
    TextView araa_fav;

    RelativeLayout fav_rel;
    LinearLayout area_lay;
    CardView add_interst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        Real_Estate_order_layout = findViewById(R.id.Real_Estate_order_layout);
        Shopping_request_layout = findViewById(R.id.Shopping_request_layout);
        my_order_layout = findViewById(R.id.my_order_layout);
        add_interst = findViewById(R.id.add_interst);


        back = findViewById(R.id.back);
        nodata_vis = findViewById(R.id.nodata_vis);
        araa_fav = findViewById(R.id.araa_fav);
        my_fav = findViewById(R.id.my_fav);
        layout_ffavorit = findViewById(R.id.layout_ffavorit);
        fav_rel = findViewById(R.id.fav_rel);
        area_lay = findViewById(R.id.area_lay);


        my_order_text = findViewById(R.id.my_order_text);
        Shopping_request_text = findViewById(R.id.Shopping_request_text);
        Real_Estate_order_text = findViewById(R.id.Real_Estate_order_text);
        alldate = findViewById(R.id.alldate);


        if (Settings.CheckIsAccountAqarzMan()) {
            Real_Estate_order_text.setVisibility(View.VISIBLE);
        } else {
            Real_Estate_order_text.setVisibility(View.GONE);

        }

        my_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_fav.setBackground(getResources().getDrawable(R.drawable.button_login1));
                araa_fav.setBackground(getResources().getDrawable(R.drawable.button_loginc));

                my_fav.setTextColor(getResources().getColor(R.color.white));
                araa_fav.setTextColor(getResources().getColor(R.color.textColor));

                layout_ffavorit.setVisibility(View.VISIBLE);
                fav_rel.setVisibility(View.VISIBLE);
                area_lay.setVisibility(View.GONE);

            }
        });
        araa_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_fav.setBackground(getResources().getDrawable(R.drawable.button_loginc));
                araa_fav.setBackground(getResources().getDrawable(R.drawable.button_login1));

                my_fav.setTextColor(getResources().getColor(R.color.textColor));
                araa_fav.setTextColor(getResources().getColor(R.color.white));

                layout_ffavorit.setVisibility(View.GONE);
                fav_rel.setVisibility(View.GONE);
                area_lay.setVisibility(View.VISIBLE);

            }
        });


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(FavoriteActivity.this, LinearLayoutManager.VERTICAL, false);
        alldate.setLayoutManager(layoutManager1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add_interst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavoriteActivity.this, SelectFavCityActivity.class));
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////

        WebService.loading(FavoriteActivity.this, true);

        alldate.setBackgroundColor(getResources().getColor(R.color.color_back_rex));
        init_volley();

        VolleyService mVolleyService = new VolleyService(mResultCallback, FavoriteActivity.this);


        mVolleyService.getDataVolley("my_favorite_offer", WebService.my_favorite + "?type=offer");


        my_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                my_order_layout.setBackground(getResources().getDrawable(R.drawable.button_login1));
                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.button_loginc));
                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.button_loginc));


                my_order_text.setTextColor(getResources().getColor(R.color.white));
                Shopping_request_text.setTextColor(getResources().getColor(R.color.black));
                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.black));

                WebService.loading(FavoriteActivity.this, true);

                alldate.setBackgroundColor(getResources().getColor(R.color.color_back_rex));
                init_volley();

                VolleyService mVolleyService = new VolleyService(mResultCallback, FavoriteActivity.this);


                mVolleyService.getDataVolley("my_favorite_offer", WebService.my_favorite + "?type=offer");

            }
        });


        Shopping_request_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                my_order_layout.setBackground(getResources().getDrawable(R.drawable.button_loginc));
                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.button_login1));
                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.button_loginc));


                my_order_text.setTextColor(getResources().getColor(R.color.black));
                Shopping_request_text.setTextColor(getResources().getColor(R.color.white));
                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.black));


                WebService.loading(FavoriteActivity.this, true);


                alldate.setBackgroundColor(getResources().getColor(R.color.color_back_rex));
                init_volley();

                VolleyService mVolleyService = new VolleyService(mResultCallback, FavoriteActivity.this);

                mVolleyService.getDataVolley("my_favorite_request", WebService.my_favorite + "?type=request");

            }
        });


        Real_Estate_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                my_order_layout.setBackground(getResources().getDrawable(R.drawable.button_loginc));
                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.button_loginc));
                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.button_login1));


                my_order_text.setTextColor(getResources().getColor(R.color.black));
                Shopping_request_text.setTextColor(getResources().getColor(R.color.black));
                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.white));


                WebService.loading(FavoriteActivity.this, true);


                alldate.setBackgroundColor(getResources().getColor(R.color.color_back_rex));
                init_volley();

                VolleyService mVolleyService = new VolleyService(mResultCallback, FavoriteActivity.this);

                mVolleyService.getDataVolley("my_favorite_fund", WebService.my_favorite + "?type=fund");


            }
        });

    }


    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(FavoriteActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {

                    boolean status = response.getBoolean("status");
                    if (status) {
//                        alldate.setAdapter(null);

                        demandsModules_list.clear();
//                        alldate.setAdapter(new RecyclerView_orders_demandsx_favorit(FavoriteActivity.this, demandsModules_list));
//                        String message = response.getString("message");
                        if (requestType.equals("my_favorite_fund")) {

                            String data = response.getString("data");
                            JSONArray jsonArray = new JSONArray(data);
//                            String first = jsonArray.get(0).toString();
//                            JSONObject jsonObjectdata = new JSONObject(first);
//
//                            String fund = jsonObjectdata.getString("fund");
//
//                            JSONArray jsonArray_fund = new JSONArray(fund);
                            demandsModules_list.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    fund_favorite fund_favorite = gson.fromJson(mJson, fund_favorite.class);

                                    if (fund_favorite.getEstate().size() != 0) {
                                        demandsModules_list.add(fund_favorite);

                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            alldate.setAdapter(new RecyclerView_ordersx_favorite(FavoriteActivity.this, demandsModules_list));


                            if (demandsModules_list.size() == 0) {
                                nodata_vis.setVisibility(View.VISIBLE);
                            } else {
                                nodata_vis.setVisibility(View.GONE);

                            }


                        } else if (requestType.equals("my_favorite_offer")) {
                            String data = response.getString("data");
                            JSONArray jsonArray = new JSONArray(data);
//                            String first = jsonArray.get(0).toString();
//                            JSONObject jsonObjectdata = new JSONObject(first);
//
//                            String fund = jsonObjectdata.getString("fund");

//                            JSONArray jsonArray_fund = new JSONArray(fund);
                            of_favorites.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    of_favorite ordersModulesm = gson.fromJson(mJson, of_favorite.class);
                                    of_favorites.add(ordersModulesm);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
//                                alldate.setAdapter(new RecyclerView_orders_demandsx(FavoriteActivity.this, demandsModules_list));
                            }

                            try {
                                RecyclerView_HomeList_estat_new_favorite recyclerView_homeList_estat_new = new RecyclerView_HomeList_estat_new_favorite(FavoriteActivity.this, of_favorites);
                                alldate.setAdapter(recyclerView_homeList_estat_new);

                            } catch (Exception e) {

                            }

//                            alldate.setAdapter(new RecyclerView_orders_my_requstx_favorit_offet(FavoriteActivity.this, of_favorites));
                            if (of_favorites.size() == 0) {
                                nodata_vis.setVisibility(View.VISIBLE);
                            } else {
                                nodata_vis.setVisibility(View.GONE);

                            }
                        } else if (requestType.equals("my_favorite_request")) {
                            String data = response.getString("data");
                            JSONArray jsonArray = new JSONArray(data);
//                            String first = jsonArray.get(0).toString();
//                            JSONObject jsonObjectdata = new JSONObject(first);
//
//                            String fund = jsonObjectdata.getString("fund");

//                            JSONArray jsonArray_fund = new JSONArray(fund);
                            mod_favorites_.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    mod_favorite ordersModulesm = gson.fromJson(mJson, mod_favorite.class);
                                    mod_favorites_.add(ordersModulesm);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
//                                alldate.setAdapter(new RecyclerView_orders_demandsx(FavoriteActivity.this, demandsModules_list));

                            }
                            RecyclerView_orders_demandsx_favorits_2 recyclerView_orders_demandsx = new RecyclerView_orders_demandsx_favorits_2(FavoriteActivity.this, mod_favorites_);

                            alldate.setAdapter(recyclerView_orders_demandsx);


//                            alldate.setAdapter(new RecyclerView_orders_my_requstx_favorit(FavoriteActivity.this, mod_favorites_));
                            if (mod_favorites_.size() == 0) {
                                nodata_vis.setVisibility(View.VISIBLE);
                            } else {
                                nodata_vis.setVisibility(View.GONE);

                            }
                        } else if (requestType.equals("my_favorite")) {

                            String data = response.getString("data");
//                        JSONObject jsonObjectdata = new JSONObject(data);
//
//                        String datax = jsonObjectdata.getString("data");

                            JSONArray jsonArray = new JSONArray(data);


                            favoritModules_l.clear();
                            alldate.setAdapter(null);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    FavoritModules bankModules = gson.fromJson(mJson, FavoritModules.class);
                                    favoritModules_l.add(bankModules);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }


                            alldate.setAdapter(new RecyclerView_HomeList_estat_favorit(FavoriteActivity.this, favoritModules_l));

                            if (favoritModules_l.size() == 0) {
                                nodata_vis.setVisibility(View.VISIBLE);
                            } else {
                                nodata_vis.setVisibility(View.GONE);

                            }
                        } else {

//

                        }
                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(FavoriteActivity.this, message, "error");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(FavoriteActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(FavoriteActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(FavoriteActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(FavoriteActivity.this, false);

                WebService.Make_Toast_color(FavoriteActivity.this, error, "error");


            }
        };


    }

}
package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

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

import sa.aqarz.Adapter.RecyclerView_All_Comfort_in_fragment;
import sa.aqarz.Adapter.RecyclerView_All_Type_order_;
import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import sa.aqarz.Adapter.RecyclerView_city_bootom_sheets_multi;
import sa.aqarz.Adapter.RecyclerView_select_city;
import sa.aqarz.Dialog.BottomSheetDialogFragment_SelectCity_fillter;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Application;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class FiltterOrderActivity extends AppCompatActivity {
    RecyclerView list_opration;
    List<TypeModules> data = new ArrayList<>();

    List<TypeModules> type_list = new ArrayList<>();

    ImageView back;
    List<CityModules> cityModules_list = new ArrayList<>();
    List<CityModules> cityModules_list_selected = new ArrayList<>();
    List<CityModules> cityModules_list_filtter = new ArrayList<>();
    EditText text_search;
    RecyclerView list_city;
    RecyclerView selected_list_city;
    RecyclerView type_of_v;

    ImageView search_btn;
    String id_city = "";
    IResult mResultCallback;
    ProgressBar progress;

    String ids_of_city = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtter_order);

        list_opration = findViewById(R.id.list_opration);
        back = findViewById(R.id.back);

        type_of_v = findViewById(R.id.type_of_v);

        list_city = findViewById(R.id.list_city);
        selected_list_city = findViewById(R.id.selected_list_city);
        progress = findViewById(R.id.progress);
        text_search = findViewById(R.id.text_search);
        search_btn = findViewById(R.id.search_btn);
        try {
//            data = Settings.getSettings().getOprationType().getOriginal().getData();


            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(0));
            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(1));
            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(2));
//            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(3));


            LinearLayoutManager layoutManager1
                    = new LinearLayoutManager(FiltterOrderActivity.this, LinearLayoutManager.HORIZONTAL, false);
            list_opration.setLayoutManager(layoutManager1);
            RecyclerView_All_Type_order_ recyclerView_all_type_order_ = new RecyclerView_All_Type_order_(FiltterOrderActivity.this, data);
            recyclerView_all_type_order_.addItemClickListener(new RecyclerView_All_Comfort_in_fragment.ItemClickListener() {
                @Override
                public void onItemClick(int position) {
//                    if (position == 0) {
//                        list_opration.setVisibility(View.VISIBLE);
//                        type_sale.setVisibility(View.VISIBLE);
//                        section_horizantal.setVisibility(View.VISIBLE);
//                    } else if (position == 1) {
//                        list_opration.setVisibility(View.VISIBLE);
//                        section_horizantal.setVisibility(View.VISIBLE);
//                        type_sale.setVisibility(View.VISIBLE);
//                    } else {
//                        list_opration.setVisibility(View.VISIBLE);
//
//                        type_sale.setVisibility(View.GONE);
//                        section_horizantal.setVisibility(View.GONE);
//
//                    }
                }
            });
            list_opration.setAdapter(recyclerView_all_type_order_);

        } catch (Exception e) {
            e.printStackTrace();
        }


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(FiltterOrderActivity.this, LinearLayoutManager.VERTICAL, false);
        list_city.setLayoutManager(layoutManager1);


        LinearLayoutManager layoutManager1c
                = new LinearLayoutManager(FiltterOrderActivity.this, LinearLayoutManager.HORIZONTAL, false);
        selected_list_city.setLayoutManager(layoutManager1c);

        if (Application.AllCity.size() != 0) {
            progress.setVisibility(View.GONE);

            cityModules_list = Application.AllCity;

            cityModules_list_selected.clear();
            for (int i = 0; i < cityModules_list.size(); i++) {

                if (cityModules_list.get(i).isSelected()) {
                    cityModules_list_selected.add(cityModules_list.get(i));
                }


            }

            selected_list_city.setAdapter(new RecyclerView_select_city(FiltterOrderActivity.this, cityModules_list_selected));


            RecyclerView_city_bootom_sheets_multi recyclerView_city_bootom_sheets = new RecyclerView_city_bootom_sheets_multi(FiltterOrderActivity.this, cityModules_list);
            recyclerView_city_bootom_sheets.addItemClickListener(new RecyclerView_city_bootom_sheets_multi.ItemClickListener() {


                @Override
                public void onItemClick(List<CityModules> alldata) {

                    cityModules_list = alldata;

                    cityModules_list_selected.clear();
                    for (int i = 0; i < cityModules_list.size(); i++) {

                        if (cityModules_list.get(i).isSelected()) {
                            cityModules_list_selected.add(cityModules_list.get(i));
                        }


                    }

                    selected_list_city.setAdapter(new RecyclerView_select_city(FiltterOrderActivity.this, cityModules_list_selected));
                }
            });

            list_city.setAdapter(recyclerView_city_bootom_sheets);

        } else {
            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, FiltterOrderActivity.this);
            mVolleyService.getDataVolley("city", WebService.cities);

        }

        type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(0));
        type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(1));
        type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(2));
        type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(3));


        LinearLayoutManager layoutManagerxmx
                = new LinearLayoutManager(FiltterOrderActivity.this, LinearLayoutManager.HORIZONTAL, false);
        type_of_v.setLayoutManager(layoutManagerxmx);


        RecyclerView_All_opration_bottom_sheet recyclerView_all_opration_bottom_sheet = new RecyclerView_All_opration_bottom_sheet(FiltterOrderActivity.this, type_list);
        recyclerView_all_opration_bottom_sheet.addItemClickListener(new RecyclerView_All_opration_bottom_sheet.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                opration_select = type_list.get(position).getId().toString() + "";
//
//                page = 1;
//                send_requst_by_type(type_requst);


            }
        });
        type_of_v.setAdapter(recyclerView_all_opration_bottom_sheet);


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
                WebService.loading(FiltterOrderActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");
                        Hawk.put("AllCity", data);


//                        JSONObject jsonObjectdata = new JSONObject(data);
//
//                        String datax = jsonObjectdata.getString("data");

                        JSONArray jsonArray = new JSONArray(data);
                        progress.setVisibility(View.GONE);
                        cityModules_list.clear();
                        cityModules_list_filtter.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {


                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(jsonArray.getString(i));

                            Gson gson = new Gson();

                            CityModules Store_M = gson.fromJson(mJson, CityModules.class);
                            cityModules_list.add(Store_M);
                            cityModules_list_filtter.add(Store_M);
                            Application.AllCity.add(Store_M);
                        }

                        RecyclerView_city_bootom_sheets_multi recyclerView_city_bootom_sheets = new RecyclerView_city_bootom_sheets_multi(FiltterOrderActivity.this, cityModules_list_filtter);
                        recyclerView_city_bootom_sheets.addItemClickListener(new RecyclerView_city_bootom_sheets_multi.ItemClickListener() {


                            @Override
                            public void onItemClick(List<CityModules> alldata) {
                                cityModules_list = alldata;

                            }
                        });

                        list_city.setAdapter(recyclerView_city_bootom_sheets);


                    } else {
                        String message = response.getString("message");


                        WebService.Make_Toast_color(FiltterOrderActivity.this, message, "error");

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());

                try {
                    progress.setVisibility(View.GONE);

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(FiltterOrderActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

}
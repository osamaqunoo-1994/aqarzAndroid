package sa.aqarz.NewAqarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sa.aqarz.Activity.MyInterestsActivity;
import sa.aqarz.Adapter.RecyclerView_city;
import sa.aqarz.Modules.AlLNebModules;
import sa.aqarz.Modules.AllCityModules;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class SelectFavCityActivity extends AppCompatActivity {

    List<AllCityModules.City> dataCities = new ArrayList<>();
    List<AllCityModules.City> dataCities_result = new ArrayList<>();

    ImageView back;
    ImageView search_btn;
    RecyclerView list_city;
    EditText search_text;
    static IResult mResultCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_fav_city);
        back = findViewById(R.id.back);
        search_text = findViewById(R.id.search_text);
        search_btn = findViewById(R.id.search_btn);
        list_city = findViewById(R.id.list_city);


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(SelectFavCityActivity.this, LinearLayoutManager.VERTICAL, false);
        list_city.setLayoutManager(layoutManager1);


//        init_volley();
//        WebService.loading(SelectFavCityActivity.this, true);
//
//        try {
//            VolleyService mVolleyService = new VolleyService(mResultCallback, SelectFavCityActivity.this);
////            mVolleyService.getDataVolley("user", WebService.user + id + "");
//            mVolleyService.getDataVolley("title_global_cities", WebService.title_global_cities + "?name=" + search_text.getText().toString());
//        } catch (Exception e) {
//
//        }
        search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    init_volley();
                    WebService.loading(SelectFavCityActivity.this, true);

                    try {
                        VolleyService mVolleyService = new VolleyService(mResultCallback, SelectFavCityActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                        mVolleyService.getDataVolley("title_global_cities", WebService.title_global_cities + "?name=" + search_text.getText().toString());
                    } catch (Exception e) {

                    }


                    return true;
                }
                return false;
            }
        });


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_volley();
                WebService.loading(SelectFavCityActivity.this, true);

                try {
                    VolleyService mVolleyService = new VolleyService(mResultCallback, SelectFavCityActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                    mVolleyService.getDataVolley("title_global_cities", WebService.title_global_cities + "?name=" + search_text.getText().toString());
                } catch (Exception e) {

                }
//                if (dataCities.size() == 0) {
//
//                } else {
//
//                    dataCities_result.clear();
//                    for (int i = 0; i < dataCities.size(); i++) {
//                        if (dataCities.get(i).getNameAr().contains(search_text.getText().toString())) {
//
//                            dataCities_result.add(dataCities.get(i));
//
//                        }
//
//                    }
//                    recyclerView_city.Refr();
//
//
//                }

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
                Log.d("TAG", "Volley JSON post" + response.toString());
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String message = response.getString("message");

                        if (requestType.equals("title_global_cities")) {

//                            String data = response.getString("data");

                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(response.toString());

                            Gson gson = new Gson();
                            AllCityModules allCityModules = gson.fromJson(mJson, AllCityModules.class);
                            dataCities = allCityModules.getData();
//                            dataCities_result = allCityModules.getData();
//

                            RecyclerView_city recyclerView_city = new RecyclerView_city(SelectFavCityActivity.this, dataCities);
                            recyclerView_city.addItemClickListener(new RecyclerView_city.ItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

//                                    list_liner_city.setVisibility(View.GONE);
//                                    selected_text_city.setText(allCityModules.getData().get(position).getNameAr() + "");
//                                    init_volley();
//                                    WebService.loading(SelectFavCityActivity.this, true);
//
//                                    try {
//                                        VolleyService mVolleyService = new VolleyService(mResultCallback, SelectFavCityActivity.this);
////            mVolleyService.getDataVolley("user", WebService.user + id + "");
//                                        mVolleyService.getDataVolley("title_gloable", WebService.title_gloable + allCityModules.getData().get(position).getCityId() + "/neb");
//
//                                    } catch (Exception e) {
//
//                                    }


                                    Intent intent = new Intent(SelectFavCityActivity.this, MyInterestsActivity.class);
                                    intent.putExtra("getCityId", allCityModules.getData().get(position).getCityId() + "");
                                    startActivity(intent);

                                }
                            });

                            list_city.setAdapter(recyclerView_city);
//                            set_locationCity(allCityModules.getData());


                        }
                    }
                    WebService.loading(SelectFavCityActivity.this, false);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(SelectFavCityActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(SelectFavCityActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(SelectFavCityActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(SelectFavCityActivity.this, false);

                WebService.Make_Toast_color(SelectFavCityActivity.this, error, "error");


            }
        };


    }

}
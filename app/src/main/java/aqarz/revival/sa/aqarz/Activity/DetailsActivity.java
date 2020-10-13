package aqarz.revival.sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Activity.OprationAqarz.RequestOrderActivity;
import aqarz.revival.sa.aqarz.Activity.OprationNew.RentActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Type_in_order;
import aqarz.revival.sa.aqarz.Adapter.home_viewPager_Adapter;
import aqarz.revival.sa.aqarz.Modules.HomeModules;
import aqarz.revival.sa.aqarz.Modules.HomeModules_aqares;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;

public class DetailsActivity extends AppCompatActivity {
    ViewPagerIndicator view_pager_indicator;
    ViewPager home_viewPager;
    IResult mResultCallback;

    private ArrayList<String> items_ViewPager = new ArrayList<String>();

    RecyclerView type_RecyclerView;
    List<TypeModules> typeModules_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        init();
    }

    public void init() {

        home_viewPager = findViewById(R.id.home_viewPager);
        view_pager_indicator = findViewById(R.id.view_pager_indicator);
        type_RecyclerView = findViewById(R.id.type_RecyclerView);


        typeModules_list = Settings.getSettings().getOprationType().getOriginal().getData();
        LinearLayoutManager layoutManagers
                = new LinearLayoutManager(DetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        type_RecyclerView.setLayoutManager(layoutManagers);

        RecyclerView_All_Type_in_order recyclerView_all_type_in_order = new RecyclerView_All_Type_in_order(DetailsActivity.this, typeModules_list);
        recyclerView_all_type_in_order.addItemClickListener(new RecyclerView_All_Type_in_order.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                set_fragment(typeModules_list.get(position).getId());
            }
        });
//        type_RecyclerView.setAdapter(recyclerView_all_type_in_order);

        items_ViewPager.add("");
        items_ViewPager.add("");
        items_ViewPager.add("");


        home_viewPager.setAdapter(new home_viewPager_Adapter(DetailsActivity.this, items_ViewPager));
        view_pager_indicator.setupWithViewPager(home_viewPager);


//        home_viewPager.setClipToPadding(false);
//        // set padding manually, the more you set the padding the more you see of prev & next page
//        home_viewPager.setPadding(0, 0, 110, 0);
//        // sets a margin b/w individual pages to ensure that there is a gap b/w them
//        home_viewPager.setPageMargin(20);


        WebService.loading(DetailsActivity.this, true);

        VolleyService mVolleyService = new VolleyService(mResultCallback, DetailsActivity.this);
        mVolleyService.getDataVolley("single_estat", WebService.single_estat + "1/estate");


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(DetailsActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

                        JsonParser parser = new JsonParser();
                        JsonElement mJson = parser.parse(data);

                        Gson gson = new Gson();

                        HomeModules_aqares homeModules_aqares = gson.fromJson(mJson, HomeModules_aqares.class);


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(DetailsActivity.this, message, "error");
                    }


                } catch (Exception e) {

                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(DetailsActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(DetailsActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(DetailsActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(DetailsActivity.this, false);

                WebService.Make_Toast_color(DetailsActivity.this, error, "error");


            }
        };


    }

}
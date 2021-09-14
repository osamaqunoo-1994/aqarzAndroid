package sa.aqarz.NewAqarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.FavoriteActivity;
import sa.aqarz.Modules.AllCityListxx;
import sa.aqarz.Modules.AllCityModules;
import sa.aqarz.Modules.AllEstate;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_Intrester;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class IntrestedActivity extends AppCompatActivity {
    CardView add_interst;

    ImageView back;

    RecyclerView all_arya_list;
    static IResult mResultCallback;


    List<AllCityModules.City> dataCities = new ArrayList<>();
    RecyclerView_Intrester recyclerView_intrester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intrested);
        back = findViewById(R.id.back);
        add_interst = findViewById(R.id.add_interst);
        all_arya_list = findViewById(R.id.all_arya_list);


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(IntrestedActivity.this, LinearLayoutManager.VERTICAL, false);
        all_arya_list.setLayoutManager(layoutManager1);


        add_interst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntrestedActivity.this, SelectFavCityActivity.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView_intrester = new RecyclerView_Intrester(IntrestedActivity.this, dataCities);
        all_arya_list.setAdapter(recyclerView_intrester);

        WebService.loading(IntrestedActivity.this, true);
        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, IntrestedActivity.this);
//        url_list = WebService.home_estate_custom_list + "?" + type_filtter_;
        mVolleyService.getAsync("my_interest", WebService.my_interest);

    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    WebService.loading(IntrestedActivity.this, false);

                    boolean status = response.getBoolean("status");
                    if (status) {
                        WebService.loading(IntrestedActivity.this, false);

                        if (requestType.equals("my_interest")) {

                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(response.toString());

                            Gson gson = new Gson();

                            dataCities.clear();


                            AllCityListxx allCityListxx = gson.fromJson(mJson, AllCityListxx.class);

                            dataCities.add(allCityListxx.getData().get(0));


                            recyclerView_intrester.Refr();

//                            set_locationEstate(allNeigbers.getData().getData());
//                            all_estate_size.setVisibility(View.VISIBLE);


                        } else {
                            String message = response.getString("message");
                            WebService.Make_Toast_color(IntrestedActivity.this, message, "success");

                        }


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(IntrestedActivity.this, message, "error");
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

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(IntrestedActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


                WebService.loading(IntrestedActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

                WebService.loading(IntrestedActivity.this, false);

            }
        }

        ;


    }

}
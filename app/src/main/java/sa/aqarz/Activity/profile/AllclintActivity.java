package sa.aqarz.Activity.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

import sa.aqarz.Activity.RealState.AllOfferOrderActivity;
import sa.aqarz.Adapter.RecyclerView_AllClints;
import sa.aqarz.Adapter.RecyclerView_MyState;
import sa.aqarz.Adapter.RecyclerView_ordersx;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.User;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class AllclintActivity extends AppCompatActivity {
    RecyclerView allclints;
    ImageView back;
    IResult mResultCallback;
    List<User> homeModules = new ArrayList<>();
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allclint);
        allclints = findViewById(R.id.allclints);

        back = findViewById(R.id.back);
        title = findViewById(R.id.title);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        allclints.setLayoutManager(layoutManager1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        try {

            String titles = getIntent().getStringExtra("title");


            if (titles != null) {
                if (titles.equals("search")) {
                    title.setText(getResources().getString(R.string.BrokersBrokers));
                }
            }

        } catch (Exception e) {

        }

        try {

            String search_text = getIntent().getStringExtra("search_text");


            if (search_text != null) {

                init_volley();
                WebService.loading(AllclintActivity.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, this);

                mVolleyService.getDataVolley("best_provider", WebService.Domain + "search/user/" + search_text);

            } else {

                init_volley();
                WebService.loading(AllclintActivity.this, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, this);

                mVolleyService.getDataVolley("best_providerxxxxxx", WebService.best_provider);

            }


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
                WebService.loading(AllclintActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {

                        if (requestType.equals("send_offer_fund_Request")) {


                            String message = response.getString("message");

                            RecyclerView_ordersx.send_done();

//                            WebService.Make_Toast_color(getActivity(), message, "success");


//                        WebService.Make_Toast_color(FinanceActivity.this, message, "success");


//                            AlertDialog alertDialog;
//
//
//                            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                            final View popupView = layoutInflater.inflate(R.layout.success_sandoq, null);
//
//
//                            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//
////            alertDialog_country =
//                            builder.setView(popupView);
//
//
//                            alertDialog = builder.show();
//
//                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


                        } else {
                            String data = response.getString("data");
//                        JSONObject jsonObjectdata = new JSONObject(data);
//
//                        String datax = jsonObjectdata.getString("data");

                            JSONArray jsonArray = new JSONArray(data);
                            homeModules.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();

                                User homeModule = gson.fromJson(mJson, User.class);
                                homeModules.add(homeModule);
                            }


                            RecyclerView_AllClints recyclerView_ = new RecyclerView_AllClints(AllclintActivity.this, homeModules);


                            allclints.setAdapter(recyclerView_);

//                        RecyclerView_city_bootom_sheets recyclerView_city_bootom_sheets = new RecyclerView_city_bootom_sheets(getContext(), cityModules_list);
//                        recyclerView_city_bootom_sheets.addItemClickListener(new RecyclerView_city_bootom_sheets.ItemClickListener() {
//
//
//                            @Override
//                            public void onItemClick(int position) {
//                                if (mItemClickListener != null) {
//                                    mItemClickListener.onItemClick(cityModules_list.get(position).getId(), cityModules_list.get(position).getName());
//                                }
//                            }
//                        });
//
//                        list_city.setAdapter(recyclerView_city_bootom_sheets);

                        }
                    } else {
                        String message = response.getString("message");


                        WebService.Make_Toast_color(AllclintActivity.this, message, "error");

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
                    WebService.loading(AllclintActivity.this, false);

//                    progress.setVisibility(View.GONE);

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(AllclintActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(AllclintActivity.this, false);

            }
        };


    }

}
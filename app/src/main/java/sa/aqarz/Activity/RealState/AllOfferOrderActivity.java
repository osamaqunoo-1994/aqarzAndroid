package sa.aqarz.Activity.RealState;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import sa.aqarz.Adapter.RecyclerView_MyState;
import sa.aqarz.Adapter.RecyclerView_ordersx;
import sa.aqarz.Dialog.BottomSheetDialogFragment_MyEstate;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class AllOfferOrderActivity extends AppCompatActivity {
    String id_offer = "";
    ProgressBar progress;
    IResult mResultCallback;

    RecyclerView all_my_state;

    Button confirm;
    Button addAqares;

    String is_selected = "";

    List<HomeModules_aqares> homeModules = new ArrayList<>();
    private BottomSheetDialogFragment_MyEstate.ItemClickListener mItemClickListener;

    static Activity activity;
    ImageView back;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_offer_order);

        back = findViewById(R.id.back);
        all_my_state = findViewById(R.id.all_my_state);
        progress = findViewById(R.id.progress);
        confirm = findViewById(R.id.confirm);
        addAqares = findViewById(R.id.addAqares);


        activity = this;

        try {

            String getUuid = getIntent().getStringExtra("getUuid");
            id_offer = getUuid;
            id = getIntent().getStringExtra("id");

        } catch (Exception e) {

        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addAqares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllOfferOrderActivity.this, AddAqarsActivity.class);
//              intent.putExtra("from", "splash");
                startActivity(intent);
            }
        });


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        all_my_state.setLayoutManager(layoutManager1);


        init_volley();

        VolleyService mVolleyService = new VolleyService(mResultCallback, this);

        mVolleyService.getDataVolley("my_estate", WebService.my_estate);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (is_selected.equals("")) {

                } else {
                    RequestParams sendObj = new RequestParams();

                    try {

                        sendObj.put("uuid", id_offer);//form operation list api in setting
                        sendObj.put("estate_id", is_selected);//form estate type list api in setting
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    init_volley();
                    WebService.loading(AllOfferOrderActivity.this, true);

                    System.out.println(sendObj.toString());
                    mVolleyService.postDataasync_with_file("send_offer_fund_Request", WebService.send_offer_fund_Request, sendObj);


                }


            }
        });


    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(String status);
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(AllOfferOrderActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {

                        if (requestType.equals("send_offer_fund_Request")) {


                            String message = response.getString("message");

//                            RecyclerView_ordersx.send_done();


                            Intent intent = new Intent(AllOfferOrderActivity.this, MyOfferOrderActivity.class);
                            intent.putExtra("getUuid", id_offer + "");
                            intent.putExtra("id", id + "");
                            startActivity(intent);
                            finish();

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
                            progress.setVisibility(View.GONE);
                            homeModules.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();

                                HomeModules_aqares homeModule = gson.fromJson(mJson, HomeModules_aqares.class);
                                homeModules.add(homeModule);
                            }


                            RecyclerView_MyState recyclerView_myState = new RecyclerView_MyState(AllOfferOrderActivity.this, homeModules);
                            recyclerView_myState.addItemClickListener(new RecyclerView_MyState.ItemClickListener() {
                                @Override
                                public void onItemClick(List<HomeModules_aqares> homeModules_aqares) {
//                                homeModules = homeModules_aqares;

                                    is_selected = "";
                                    for (int i = 0; i < homeModules_aqares.size(); i++) {
                                        if (homeModules_aqares.get(i).getIs_selected()) {
                                            if (is_selected.equals("")) {
                                                is_selected = homeModules_aqares.get(i).getId() + "";
                                            } else {
                                                is_selected = is_selected + "," + homeModules_aqares.get(i).getId() + "";

                                            }
                                        }
                                    }


                                }
                            });
                            all_my_state.setAdapter(recyclerView_myState);

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


                        WebService.Make_Toast_color(AllOfferOrderActivity.this, message, "error");

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
                    WebService.loading(AllOfferOrderActivity.this, false);

                    progress.setVisibility(View.GONE);

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(AllOfferOrderActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(AllOfferOrderActivity.this, false);

            }
        };


    }

}
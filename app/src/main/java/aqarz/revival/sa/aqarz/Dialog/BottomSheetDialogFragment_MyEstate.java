package aqarz.revival.sa.aqarz.Dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Adapter.RecyclerView_MyState;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_city_bootom_sheets;
import aqarz.revival.sa.aqarz.Modules.CityModules;
import aqarz.revival.sa.aqarz.Modules.HomeModules;
import aqarz.revival.sa.aqarz.Modules.HomeModules_aqares;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;


public class BottomSheetDialogFragment_MyEstate extends BottomSheetDialogFragment {

    String id_offer = "";
    ProgressBar progress;
    IResult mResultCallback;

    RecyclerView all_my_state;

    Button confirm;

    String is_selected = "";

    List<HomeModules_aqares> homeModules = new ArrayList<>();
    private ItemClickListener mItemClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheets_newofeer, container, false);
        all_my_state = v.findViewById(R.id.all_my_state);
        progress = v.findViewById(R.id.progress);
        confirm = v.findViewById(R.id.confirm);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        all_my_state.setLayoutManager(layoutManager1);


        init_volley();

        VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());

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

                    }

                    WebService.loading(getActivity(), true);

                    System.out.println(sendObj.toString());
                    mVolleyService.postDataasync_with_file("send_offer_fund_Request", WebService.send_offer_fund_Request, sendObj);


                }


            }
        });

        return v;
    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_MyEstate(String id_offer) {
        this.id_offer = id_offer;
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.bottom_sheets_details_aqares, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }


    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int id_city, String city_naem);
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(getActivity(), false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {

                        if (requestType.equals("send_offer_fund_Request")) {


                            String message = response.getString("message");


                            WebService.Make_Toast_color(getActivity(), message, "success");

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


                            RecyclerView_MyState recyclerView_myState = new RecyclerView_MyState(getContext(), homeModules);
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


                        WebService.Make_Toast_color(getActivity(), message, "error");

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
                    WebService.loading(getActivity(), false);

                    progress.setVisibility(View.GONE);

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(getActivity(), message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(getActivity(), false);

            }
        };


    }

}

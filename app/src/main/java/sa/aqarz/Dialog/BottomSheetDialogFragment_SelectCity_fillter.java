package sa.aqarz.Dialog;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.RecyclerView_city_bootom_sheets;
import sa.aqarz.Adapter.RecyclerView_city_bootom_sheets_multi;
import sa.aqarz.Adapter.RecyclerView_select_city;
import sa.aqarz.Fragment.OrdersFragment;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Application;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class BottomSheetDialogFragment_SelectCity_fillter extends BottomSheetDialogFragment {
    IResult mResultCallback;

    String id_city = "";

    ImageView search_btn;
    RecyclerView list_city;
    RecyclerView selected_list_city;


    List<CityModules> cityModules_list = new ArrayList<>();
    List<CityModules> cityModules_list_selected = new ArrayList<>();
    List<CityModules> cityModules_list_filtter = new ArrayList<>();
    EditText text_search;

    ProgressBar progress;
    ImageView close;
    private ItemClickListener mItemClickListener;
    TextView select;

    String ids_of_city = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheets_select_city_fillter, container, false);
        list_city = v.findViewById(R.id.list_city);
        selected_list_city = v.findViewById(R.id.selected_list_city);
        progress = v.findViewById(R.id.progress);
        close = v.findViewById(R.id.close);
        text_search = v.findViewById(R.id.text_search);
        search_btn = v.findViewById(R.id.search_btn);
        select = v.findViewById(R.id.select);


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_city.setLayoutManager(layoutManager1);


        LinearLayoutManager layoutManager1c
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
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

            selected_list_city.setAdapter(new RecyclerView_select_city(getContext(), cityModules_list_selected));


            RecyclerView_city_bootom_sheets_multi recyclerView_city_bootom_sheets = new RecyclerView_city_bootom_sheets_multi(getContext(), cityModules_list);
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

                    selected_list_city.setAdapter(new RecyclerView_select_city(getContext(), cityModules_list_selected));
                }
            });

            list_city.setAdapter(recyclerView_city_bootom_sheets);

        } else {
            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("city", WebService.cities);

        }


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cityModules_list.clear();
                for (int i = 0; i < cityModules_list.size(); i++) {

                    if (cityModules_list.get(i).getName().contains(text_search.getText().toString())) {
                        cityModules_list_filtter.add(cityModules_list.get(i));
                    }


                }
                RecyclerView_city_bootom_sheets_multi recyclerView_city_bootom_sheets = new RecyclerView_city_bootom_sheets_multi(getContext(), cityModules_list_filtter);
                recyclerView_city_bootom_sheets.addItemClickListener(new RecyclerView_city_bootom_sheets_multi.ItemClickListener() {


                    @Override
                    public void onItemClick(List<CityModules> alldata) {
                        cityModules_list = alldata;

                    }
                });

                list_city.setAdapter(recyclerView_city_bootom_sheets);
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                OrdersFragment.close_bottom();
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                OrdersFragment.close_bottom();

                ids_of_city = "";
                for (int i = 0; i < cityModules_list.size(); i++) {

                    if (cityModules_list.get(i).isSelected()) {
                        if (ids_of_city.equals("")) {
                            ids_of_city = cityModules_list.get(i).getSerial_city() + "";

                        } else {
                            ids_of_city = ids_of_city + "," + cityModules_list.get(i).getSerial_city() + "";
                        }
                    }


                }

//                if (!ids_of_city.equals("")) {

                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(ids_of_city, ids_of_city);
                }
//                }


            }
        });
        return v;
    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_SelectCity_fillter(String id_) {
        id_city = id_;
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

                        RecyclerView_city_bootom_sheets_multi recyclerView_city_bootom_sheets = new RecyclerView_city_bootom_sheets_multi(getContext(), cityModules_list_filtter);
                        recyclerView_city_bootom_sheets.addItemClickListener(new RecyclerView_city_bootom_sheets_multi.ItemClickListener() {


                            @Override
                            public void onItemClick(List<CityModules> alldata) {
                                cityModules_list = alldata;

                            }
                        });

                        list_city.setAdapter(recyclerView_city_bootom_sheets);


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

            }
        };


    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(String id_city, String city_naem);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.bottom_sheets_details_aqares, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Window window = getDialog().getWindow();
            window.findViewById(com.google.android.material.R.id.container).setFitsSystemWindows(false);
            // dark navigation bar icons
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }
}

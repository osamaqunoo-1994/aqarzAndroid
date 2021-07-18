package sa.aqarz.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

import sa.aqarz.Activity.OprationAqarz.RequestOrderActivity;
import sa.aqarz.Activity.SplashScreenActivity;
import sa.aqarz.Adapter.RecyclerView_All_Type_in_order;
import sa.aqarz.Adapter.RecyclerView_city_bootom_sheets;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Application;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class BottomSheetDialogFragment_SelectCity extends BottomSheetDialogFragment {
    IResult mResultCallback;

    String id_city = "";


    RecyclerView list_city;

    EditText search;
    List<CityModules> cityModules_list = new ArrayList<>();
    ImageView search_btn;
    int page = 1;
    ProgressBar progress;

    private ItemClickListener mItemClickListener;
    RecyclerView_city_bootom_sheets recyclerView_city_bootom_sheets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheets_select_city, container, false);
        list_city = v.findViewById(R.id.list_city);
        progress = v.findViewById(R.id.progress);
        search = v.findViewById(R.id.search);
        search_btn = v.findViewById(R.id.search_btn);


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_city.setLayoutManager(layoutManager1);

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //-----
                WebService.loading(getActivity(), true);

                page = 1;
                progress.setVisibility(View.VISIBLE);
                init_volley();

                VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());

                mVolleyService.getDataVolley_with_time("city", WebService.cities_page + "?is_all=1" + "&page=" + page + "&name=" + search.getText().toString());

                return actionId == EditorInfo.IME_ACTION_SEARCH;
            }
        });
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;
                WebService.loading(getActivity(), true);

                progress.setVisibility(View.VISIBLE);
                init_volley();

                VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());

                mVolleyService.getDataVolley_with_time("city", WebService.cities_page + "?is_all=1" + "&page=" + page + "&name=" + search.getText().toString());


            }
        });

        Application.AllCity.clear();
        cityModules_list.clear();

        recyclerView_city_bootom_sheets = new RecyclerView_city_bootom_sheets(getContext(), Application.AllCity);
        recyclerView_city_bootom_sheets.addItemClickListener(new RecyclerView_city_bootom_sheets.ItemClickListener() {


            @Override
            public void onItemClick(int position) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(Integer.valueOf(Application.AllCity.get(position).getSerial_city()), Application.AllCity.get(position).getName());
                }
            }
        });

        list_city.setAdapter(recyclerView_city_bootom_sheets);


//        if (Application.AllCity.size() != 0) {
        if (false) {
            progress.setVisibility(View.GONE);
            RecyclerView_city_bootom_sheets recyclerView_city_bootom_sheets = new RecyclerView_city_bootom_sheets(getContext(), Application.AllCity);
            recyclerView_city_bootom_sheets.addItemClickListener(new RecyclerView_city_bootom_sheets.ItemClickListener() {


                @Override
                public void onItemClick(int position) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(Integer.valueOf(Application.AllCity.get(position).getSerial_city()), Application.AllCity.get(position).getName());
                    }
                }
            });

            list_city.setAdapter(recyclerView_city_bootom_sheets);

        } else {
            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley_with_time("city", WebService.cities_page + "?is_all=1");
        }
        list_city.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) { //1 for down

                    page = page + 1;
                    progress.setVisibility(View.VISIBLE);
                    init_volley();

                    VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());

                    mVolleyService.getDataVolley_with_time("city", WebService.cities_page + "?is_all=1" + "&page=" + page + "&name=" + search.getText().toString());
//                    WebService.loading(getActivity(), true);
//                    init_volley();
//                    VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
//                    mVolleyService.getDataVolley_with_time("city+page", WebService.cities + "?page=" + page);


                }
            }
        });

        return v;
    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_SelectCity(String id_) {
        id_city = id_;
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester #######" + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(getActivity(), false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {


                        String data = response.getString("data");
                        Hawk.put("AllCity", data);
                        JSONObject jsonObjectdata = new JSONObject(data);
                        String datax = jsonObjectdata.getString("data");

                        JSONArray jsonArray = new JSONArray(datax);

                        if (page == 1) {
                            cityModules_list.clear();
                            Application.AllCity.clear();
                        }
                        progress.setVisibility(View.GONE);
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(jsonArray.getString(i));

                            Gson gson = new Gson();

                            CityModules Store_M = gson.fromJson(mJson, CityModules.class);
                            cityModules_list.add(Store_M);
                            Application.AllCity.add(Store_M);

                        }
                        recyclerView_city_bootom_sheets.Refr();
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
        void onItemClick(int id_city, String city_naem);
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

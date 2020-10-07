package aqarz.revival.sa.aqarz.Dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_select_oprat_in_fragment;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_bottomSheet_type;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_city_bootom_sheets;
import aqarz.revival.sa.aqarz.Modules.CityModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.Modules.select_typeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;


public class BottomSheetDialogFragment_Filtter extends BottomSheetDialogFragment {
    IResult mResultCallback;


    private ItemClickListener mItemClickListener;
    RecyclerView selsct_type_all;
    RecyclerView opration;
    List<select_typeModules> oprationModules_list = new ArrayList<>();
    List<TypeModules> type_list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheets_fillter, container, false);
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) v.findViewById(R.id.rangeSeekbar5);

        selsct_type_all = v.findViewById(R.id.selsct_type_all);
        opration = v.findViewById(R.id.opration);


        LinearLayoutManager layoutManagerm
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        selsct_type_all.setLayoutManager(layoutManagerm);


        oprationModules_list.add(new select_typeModules(1, getContext().getResources().getString(R.string.All)));
        oprationModules_list.add(new select_typeModules(1, getContext().getResources().getString(R.string.Pay)));
        oprationModules_list.add(new select_typeModules(1, getContext().getResources().getString(R.string.Rent)));
        oprationModules_list.add(new select_typeModules(1, getContext().getResources().getString(R.string.Investment)));

        RecyclerView_bottomSheet_type recyclerView_bottomSheet_type = new RecyclerView_bottomSheet_type(getContext(), oprationModules_list);
        recyclerView_bottomSheet_type.addItemClickListener(new RecyclerView_bottomSheet_type.ItemClickListener() {
            @Override
            public void onItemClick(List<select_typeModules> select_typeModules) {

//                oprationModules_list = select_typeModules;


            }
        });
        selsct_type_all.setAdapter(recyclerView_bottomSheet_type);

///------------------------------------------------------------------------------------------------------
        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        LinearLayoutManager layoutManagerw
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        opration.setLayoutManager(layoutManagerw);

        RecyclerView_All_opration_bottom_sheet recyclerView_all_opration_bottom_sheet = new RecyclerView_All_opration_bottom_sheet(getContext(), type_list);
        recyclerView_all_opration_bottom_sheet.addItemClickListener(new RecyclerView_All_opration_bottom_sheet.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                opration_select = type_list.get(position).getId().toString() + "";
            }
        });
        opration.setAdapter(recyclerView_all_opration_bottom_sheet);


//        init_volley();
//
//        VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
//
//        mVolleyService.getDataVolley("city", WebService.cities);

        return v;
    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_Filtter(String id_) {
//        id_city = id_;
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


                        JSONArray jsonArray = new JSONArray(data);
//                        progress.setVisibility(View.GONE);
//                        cityModules_list.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {


                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(jsonArray.getString(i));

                            Gson gson = new Gson();

                            CityModules Store_M = gson.fromJson(mJson, CityModules.class);
//                            cityModules_list.add(Store_M);
                        }

//                        RecyclerView_city_bootom_sheets recyclerView_city_bootom_sheets = new RecyclerView_city_bootom_sheets(getContext(), cityModules_list);
//                        recyclerView_city_bootom_sheets.addItemClickListener(new RecyclerView_city_bootom_sheets.ItemClickListener() {


//                            @Override
//                            public void onItemClick(int position) {
//                                if (mItemClickListener != null) {
////                                    mItemClickListener.onItemClick(cityModules_list.get(position).getId(), cityModules_list.get(position).getName());
//                                }
//                            }
//                        });

//                        list_city.setAdapter(recyclerView_city_bootom_sheets);


                    } else {

                    }


                } catch (Exception e) {

                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());

                try {
//                    progress.setVisibility(View.GONE);

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
}

package sa.aqarz.Dialog;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.BubbleThumbRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import sa.aqarz.Adapter.RecyclerView_All_select_oprat_in_fragment;
import sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import sa.aqarz.Adapter.RecyclerView_bottomSheet_type;
import sa.aqarz.Adapter.RecyclerView_city_bootom_sheets;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.Modules.select_typeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class BottomSheetDialogFragment_Filtter extends BottomSheetDialogFragment {
    IResult mResultCallback;


    private ItemClickListener mItemClickListener;
    RecyclerView selsct_type_all;
    RecyclerView opration;
    List<select_typeModules> oprationModules_list = new ArrayList<>();
    List<TypeModules> type_list = new ArrayList<>();


    String type = "null";
    String opration_select = "";

    Button filtter_btn;
    CrystalRangeSeekbar price_seekpar;
    CrystalRangeSeekbar area_sseekbar;

    TextView min_area, max_area;

    String min_price = "0", max_price = "1000";
    String min_area_ = "0", max_area_ = "1000";
    String num_room = "1";

    TextView room_1;
    TextView room_2;
    TextView room_3;
    TextView room_4;
    TextView room_5;


    EditText Les_price, Maximum_price, Les_space, Maximum_space;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheets_fillter, container, false);
//        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) v.findViewById(R.id.rangeSeekbar5);

        selsct_type_all = v.findViewById(R.id.selsct_type_all);
        opration = v.findViewById(R.id.opration);
        filtter_btn = v.findViewById(R.id.filtter_btn);
        price_seekpar = v.findViewById(R.id.price_seekpar);
        area_sseekbar = v.findViewById(R.id.area_sseekbar);
        max_area = v.findViewById(R.id.max_area);
        min_area = v.findViewById(R.id.min_area);
        Les_price = v.findViewById(R.id.Les_price);
        Maximum_price = v.findViewById(R.id.Maximum_price);
        Les_space = v.findViewById(R.id.Les_space);
        Maximum_space = v.findViewById(R.id.Maximum_space);

        room_1 = v.findViewById(R.id.room_1);
        room_2 = v.findViewById(R.id.room_2);
        room_3 = v.findViewById(R.id.room_3);
        room_4 = v.findViewById(R.id.room_4);
        room_5 = v.findViewById(R.id.room_5);


        LinearLayoutManager layoutManagerm
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        selsct_type_all.setLayoutManager(layoutManagerm);


        oprationModules_list.add(new select_typeModules(1, getContext().getResources().getString(R.string.All)));
        oprationModules_list.add(new select_typeModules(2, getContext().getResources().getString(R.string.Pay)));
        oprationModules_list.add(new select_typeModules(3, getContext().getResources().getString(R.string.Rent)));
//        oprationModules_list.add(new select_typeModules(1, getContext().getResources().getString(R.string.Investment)));

        RecyclerView_bottomSheet_type recyclerView_bottomSheet_type = new RecyclerView_bottomSheet_type(getContext(), oprationModules_list);
        recyclerView_bottomSheet_type.addItemClickListener(new RecyclerView_bottomSheet_type.ItemClickListener() {
            @Override
            public void onItemClick(List<select_typeModules> select_typeModules) {
                type = "";

                for (int i = 0; i < select_typeModules.size(); i++) {

                    if (i == 0) {

                        if (select_typeModules.get(0).getSelected()) {
                            type = "null";
                            break;
                        }

                    } else {

                        if (select_typeModules.get(1).getSelected()) {
                            type = "is_pay";

                        }
                        if (select_typeModules.get(2).getSelected()) {
                            type = "is_rent";

                        }


//                        if (select_typeModules.get(i).getSelected()) {
//                            if (type.equals("")) {
//                                type = select_typeModules.get(i).getId() + "";
//                            } else {
//                                type = type + "," + select_typeModules.get(i).getId() + "";
//                            }
//
//
//                        }


                    }


                }


//                type = select_typeModules;


            }
        });
        selsct_type_all.setAdapter(recyclerView_bottomSheet_type);
        filtter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mItemClickListener != null) {
                    String te = "&estate_pay_type=" + type + "&price_from=" + Les_price.getText().toString() + "&price_to=" + Maximum_price.getText().toString() + "&area_from=" + Les_space.getText().toString() + "&area_from=" + Maximum_space.getText().toString();

                    mItemClickListener.onItemClick(te);
                }


            }
        });
///------------------------------------------------------------------------------------------------------
        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        LinearLayoutManager layoutManagerw
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        opration.setLayoutManager(layoutManagerw);

        RecyclerView_All_opration_bottom_sheet recyclerView_all_opration_bottom_sheet = new RecyclerView_All_opration_bottom_sheet(getContext(), type_list);
        recyclerView_all_opration_bottom_sheet.addItemClickListener(new RecyclerView_All_opration_bottom_sheet.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                opration_select = type_list.get(position).getId().toString() + "";
            }
        });
        opration.setAdapter(recyclerView_all_opration_bottom_sheet);


//        init_volley();
//
//        VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
//
//        mVolleyService.getDataVolley("city", WebService.cities);
        price_seekpar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                min_price = minValue + "";
                max_price = maxValue + "";


            }
        });
        area_sseekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                min_area_ = minValue + "";
                max_area_ = maxValue + "";


                String minValue___ = minValue + "";
                int price_int = Integer.valueOf(minValue___);

                int prices = (int) price_int;


                if (price_int < 1000) {

                    minValue___ = prices + "";
                } else if (price_int > 1000 && price_int < 999999) {
                    prices = (int) price_int / 1000;

                    minValue___ = prices + getResources().getString(R.string.K);

                } else if (price_int > 999999) {
                    prices = (int) price_int / 1000000;

                    minValue___ = prices + getResources().getString(R.string.Million);

                }


                String maxValue___ = maxValue + "";
                int price_int_ = Integer.valueOf(maxValue___);

                int prices_s = (int) price_int_;


                if (price_int_ < 1000) {

                    maxValue___ = prices_s + "";
                } else if (price_int_ > 1000 && price_int_ < 999999) {
                    prices_s = (int) price_int_ / 1000;

                    maxValue___ = prices_s + getResources().getString(R.string.K);

                } else if (price_int_ > 999999) {
                    prices_s = (int) price_int_ / 1000000;

                    maxValue___ = prices_s + getResources().getString(R.string.Million);

                }


                max_area.setText(maxValue___ + "");
                min_area.setText(minValue___ + "");


            }
        });


        room_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_1.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_2.setBackground(null);
                room_3.setBackground(null);
                room_4.setBackground(null);
                room_5.setBackground(null);


                room_1.setTextColor(getResources().getColor(R.color.white));
                room_2.setTextColor(getResources().getColor(R.color.textColor));
                room_3.setTextColor(getResources().getColor(R.color.textColor));
                room_4.setTextColor(getResources().getColor(R.color.textColor));
                room_5.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "1";
            }
        });
        room_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_2.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_1.setBackground(null);
                room_3.setBackground(null);
                room_4.setBackground(null);
                room_5.setBackground(null);


                room_2.setTextColor(getResources().getColor(R.color.white));
                room_1.setTextColor(getResources().getColor(R.color.textColor));
                room_3.setTextColor(getResources().getColor(R.color.textColor));
                room_4.setTextColor(getResources().getColor(R.color.textColor));
                room_5.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "2";

            }
        });
        room_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_3.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_2.setBackground(null);
                room_1.setBackground(null);
                room_4.setBackground(null);
                room_5.setBackground(null);


                room_3.setTextColor(getResources().getColor(R.color.white));
                room_2.setTextColor(getResources().getColor(R.color.textColor));
                room_1.setTextColor(getResources().getColor(R.color.textColor));
                room_4.setTextColor(getResources().getColor(R.color.textColor));
                room_5.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "3";

            }
        });
        room_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_4.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_2.setBackground(null);
                room_3.setBackground(null);
                room_1.setBackground(null);
                room_5.setBackground(null);


                room_4.setTextColor(getResources().getColor(R.color.white));
                room_2.setTextColor(getResources().getColor(R.color.textColor));
                room_3.setTextColor(getResources().getColor(R.color.textColor));
                room_1.setTextColor(getResources().getColor(R.color.textColor));
                room_5.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "4";

            }
        });
        room_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                room_5.setBackground(getResources().getDrawable(R.drawable.button_login));
                room_2.setBackground(null);
                room_3.setBackground(null);
                room_4.setBackground(null);
                room_1.setBackground(null);


                room_5.setTextColor(getResources().getColor(R.color.white));
                room_2.setTextColor(getResources().getColor(R.color.textColor));
                room_3.setTextColor(getResources().getColor(R.color.textColor));
                room_4.setTextColor(getResources().getColor(R.color.textColor));
                room_1.setTextColor(getResources().getColor(R.color.textColor));
                num_room = "5";

            }
        });
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
        void onItemClick(String filter);
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

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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
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

import sa.aqarz.Activity.FiltterOrderActivity;
import sa.aqarz.Activity.RealState.MyOfferOrderActivity;
import sa.aqarz.Activity.RealState.OfferDetailsActivity;
import sa.aqarz.Adapter.RecyclerView_All_Comfort_in_fragment;
import sa.aqarz.Adapter.RecyclerView_All_Type_order_;
import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet_type;
import sa.aqarz.Adapter.RecyclerView_city_bootom_sheets_multi;
import sa.aqarz.Adapter.RecyclerView_select_city;
import sa.aqarz.Fragment.OrdersFragment;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Application;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class BottomSheetDialogFragment_Filtter_order extends BottomSheetDialogFragment {
    RecyclerView list_opration;
    List<TypeModules> data = new ArrayList<>();
    String Type;
    List<TypeModules> type_list = new ArrayList<>();
    private ItemClickListener mItemClickListener;

    ImageView back;
    List<CityModules> cityModules_list = new ArrayList<>();
    List<CityModules> cityModules_list_selected = new ArrayList<>();
    List<CityModules> cityModules_list_filtter = new ArrayList<>();
    EditText text_search;
    RecyclerView list_city;
    RecyclerView selected_list_city;
    RecyclerView type_of_v;

    ImageView search_btn;
    String id_city = "";
    IResult mResultCallback;
    ProgressBar progress;

    String ids_of_city = "";

    RadioButton less, big;


    TextView search_badge;


    String price_order = "";
    String opration_select = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_filtter_order, container, false);

        list_opration = v.findViewById(R.id.list_opration);
        back = v.findViewById(R.id.back);

        type_of_v = v.findViewById(R.id.type_of_v);

        list_city = v.findViewById(R.id.list_city);
        selected_list_city = v.findViewById(R.id.selected_list_city);
        progress = v.findViewById(R.id.progress);
        text_search = v.findViewById(R.id.text_search);
        search_btn = v.findViewById(R.id.search_btn);
        less = v.findViewById(R.id.less);
        big = v.findViewById(R.id.big);
        search_badge = v.findViewById(R.id.search_badge);


        if (Type.equals("fund_Request")) {
            list_opration.setVisibility(View.GONE);
        } else if (Type.equals("market_demands")) {
            list_opration.setVisibility(View.VISIBLE);

        }


        less.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    less.setChecked(true);
                    less.setChecked(false);
                    price_order = "1";
                }
            }
        });
        big.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    less.setChecked(false);
                    less.setChecked(true);
                    price_order = "0";

                }
            }
        });

        try {
//            data = Settings.getSettings().getOprationType().getOriginal().getData();


            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(0));
            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(1));
            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(2));
//            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(3));


            LinearLayoutManager layoutManager1
                    = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            list_opration.setLayoutManager(layoutManager1);
            RecyclerView_All_Type_order_ recyclerView_all_type_order_ = new RecyclerView_All_Type_order_(getContext(), data);
            recyclerView_all_type_order_.addItemClickListener(new RecyclerView_All_Comfort_in_fragment.ItemClickListener() {
                @Override
                public void onItemClick(int position) {
//                    if (position == 0) {
//                        list_opration.setVisibility(View.VISIBLE);
//                        type_sale.setVisibility(View.VISIBLE);
//                        section_horizantal.setVisibility(View.VISIBLE);
//                    } else if (position == 1) {
//                        list_opration.setVisibility(View.VISIBLE);
//                        section_horizantal.setVisibility(View.VISIBLE);
//                        type_sale.setVisibility(View.VISIBLE);
//                    } else {
//                        list_opration.setVisibility(View.VISIBLE);
//
//                        type_sale.setVisibility(View.GONE);
//                        section_horizantal.setVisibility(View.GONE);
//
//                    }
                }
            });
            list_opration.setAdapter(recyclerView_all_type_order_);

        } catch (Exception e) {
            e.printStackTrace();
        }


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


        if (Type.equals("fund_Request")) {
            type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(0));
            type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(1));
            type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(2));
            type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(3));
        } else if (Type.equals("market_demands")) {
            type_list.addAll(Settings.getSettings().getEstate_types().getOriginal().getData());
        }


        LinearLayoutManager layoutManagerxmx
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        type_of_v.setLayoutManager(layoutManagerxmx);


        RecyclerView_All_opration_bottom_sheet_type recyclerView_all_opration_bottom_sheet = new RecyclerView_All_opration_bottom_sheet_type(getContext(), type_list);
        recyclerView_all_opration_bottom_sheet.addItemClickListener(new RecyclerView_All_opration_bottom_sheet_type.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                opration_select = type_list.get(position).getId().toString() + "";
//
//                page = 1;
//                send_requst_by_type(type_requst);


            }
        });
        type_of_v.setAdapter(recyclerView_all_opration_bottom_sheet);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();


            }
        });
        search_badge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                OrdersFragment.filtterModules.setOrder_by_price(price_order + "");


                String city = "";
                for (int i = 0; i < cityModules_list_selected.size(); i++) {
                    if (city.equals("")) {
                        city = "" + cityModules_list_selected.get(i).getId();
                    } else {
                        city = city + "," + cityModules_list_selected.get(i).getId();

                    }
                }

                System.out.println(city);
                OrdersFragment.filtterModules.setSelect_city(city + "");
                OrdersFragment.filtterModules.setType_oprtion(opration_select + "");
                OrdersFragment.filtterModules.setType_type("");


                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(1, "");
                }
            }
        });

        return v;


    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_Filtter_order(String Type) {
        this.Type = Type;
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.bottom_sheets_qr, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }


    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int id_city, String city_naem);
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


                        if (requestType.equals("city")) {


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

}

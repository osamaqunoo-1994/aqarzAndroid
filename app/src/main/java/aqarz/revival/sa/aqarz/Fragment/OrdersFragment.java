package aqarz.revival.sa.aqarz.Fragment;
/**
 * Created by osama on 10/8/2017.
 */


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import aqarz.revival.sa.aqarz.Activity.Auth.LoginActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.NewPasswordActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.RegisterActivity;
import aqarz.revival.sa.aqarz.Activity.ContactUsActivity;
import aqarz.revival.sa.aqarz.Activity.DetailsAqarzManActivity;
import aqarz.revival.sa.aqarz.Activity.MainActivity;
import aqarz.revival.sa.aqarz.Activity.PrivecyActivity;
import aqarz.revival.sa.aqarz.Activity.SplashScreenActivity;
import aqarz.revival.sa.aqarz.Activity.TermsActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Comfort_in_fragment;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Type_order_;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_HomeList;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_HomeList_estat;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_bottomSheet_type;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_orders;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_orders_demands;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_orders_demandsx;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_orders_my_requst;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_orders_my_requstx;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_orders_offer_di;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_ordersx;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_SelectCity_fillter;
import aqarz.revival.sa.aqarz.Modules.HomeModules;
import aqarz.revival.sa.aqarz.Modules.HomeModules_aqares;
import aqarz.revival.sa.aqarz.Modules.OfferRealStateModules;
import aqarz.revival.sa.aqarz.Modules.OrdersModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.Modules.demandsModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.LocaleUtils;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;
import de.hdodenhof.circleimageview.CircleImageView;


public class OrdersFragment extends Fragment {
    IResult mResultCallback;

    HorizontalScrollView section_horizantal;
    RecyclerView orders_rec;
    RecyclerView list_opration;
    RecyclerView type_of_v;
    LinearLayout type_sale;
    List<OrdersModules> ordersModules = new ArrayList<>();
    List<demandsModules> MyRequst = new ArrayList<>();

    static BottomSheetDialogFragment_SelectCity_fillter bottomSheetDialogFragment_selectCity_fillter;


    List<TypeModules> type_list = new ArrayList<>();
    List<demandsModules> demandsModules_list = new ArrayList<>();
    List<OfferRealStateModules> offerRealStateModulesLis = new ArrayList<>();

    LinearLayout my_order_layout;
    LinearLayout Shopping_request_layout;
    LinearLayout Real_Estate_order_layout;
    TextView my_order_text;
    TextView Real_Estate_order_text;
    ImageView Real_Estate_order_image;
    TextView Shopping_request_text;


    List<TypeModules> data = new ArrayList<>();

    AlertDialog alertDialog;

    TextView For_sale, rent;
    ImageView nodata_vis;

    String opration_select = "";
    String Type_work_select = "";
    String type_requst = "";


    ImageView filtter_city;

    LinearLayout type_requst_xml;
    TextView order;
    TextView offer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_orders, container, false);

        init(v);
        return v;
    }


    public void init(View v) {
        orders_rec = v.findViewById(R.id.orders_rec);

        type_requst_xml = v.findViewById(R.id.type_requst_xml);
        order = v.findViewById(R.id.order);
        offer = v.findViewById(R.id.offer);
        my_order_layout = v.findViewById(R.id.my_order_layout);
        Shopping_request_layout = v.findViewById(R.id.Shopping_request_layout);
        Real_Estate_order_layout = v.findViewById(R.id.Real_Estate_order_layout);
        my_order_text = v.findViewById(R.id.my_order_text);
        Real_Estate_order_text = v.findViewById(R.id.Real_Estate_order_text);
        Real_Estate_order_image = v.findViewById(R.id.Real_Estate_order_image);
        Shopping_request_text = v.findViewById(R.id.Shopping_request_text);
        list_opration = v.findViewById(R.id.list_opration);
        For_sale = v.findViewById(R.id.For_sale);
        rent = v.findViewById(R.id.rent);
        type_of_v = v.findViewById(R.id.type_of_v);
        type_sale = v.findViewById(R.id.type_sale);
        section_horizantal = v.findViewById(R.id.section_horizantal);
        nodata_vis = v.findViewById(R.id.nodata_vis);
        filtter_city = v.findViewById(R.id.filtter_city);


        try {
//            data = Settings.getSettings().getOprationType().getOriginal().getData();


            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(0));
            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(1));
            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(2));
            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(3));


            LinearLayoutManager layoutManager1
                    = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            list_opration.setLayoutManager(layoutManager1);
            RecyclerView_All_Type_order_ recyclerView_all_type_order_ = new RecyclerView_All_Type_order_(getContext(), data);
            recyclerView_all_type_order_.addItemClickListener(new RecyclerView_All_Comfort_in_fragment.ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (position == 0) {
                        list_opration.setVisibility(View.VISIBLE);
                        type_sale.setVisibility(View.VISIBLE);
                        section_horizantal.setVisibility(View.VISIBLE);
                    } else if (position == 1) {
                        list_opration.setVisibility(View.VISIBLE);
                        section_horizantal.setVisibility(View.VISIBLE);
                        type_sale.setVisibility(View.VISIBLE);
                    } else {
                        list_opration.setVisibility(View.VISIBLE);

                        type_sale.setVisibility(View.GONE);
                        section_horizantal.setVisibility(View.GONE);

                    }
                }
            });
            list_opration.setAdapter(recyclerView_all_type_order_);

        } catch (Exception e) {

        }


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        orders_rec.setLayoutManager(layoutManager1);
//        orders_rec.setAdapter(new RecyclerView_orders(getContext(), ordersModules));
//------------------------------------------------------------------------------------------------------------
        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();


        LinearLayoutManager layoutManagerxmx
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        type_of_v.setLayoutManager(layoutManagerxmx);


        RecyclerView_All_opration_bottom_sheet recyclerView_all_opration_bottom_sheet = new RecyclerView_All_opration_bottom_sheet(getContext(), type_list);
        recyclerView_all_opration_bottom_sheet.addItemClickListener(new RecyclerView_All_opration_bottom_sheet.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                opration_select = type_list.get(position).getId().toString() + "";


                send_requst_by_type(type_requst);


            }
        });
        type_of_v.setAdapter(recyclerView_all_opration_bottom_sheet);


        //------------------------------------------------------------------------------------------------------------


        For_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                For_sale.setBackground(getResources().getDrawable(R.drawable.button_login));

                For_sale.setTextColor(getResources().getColor(R.color.white));


                rent.setBackground(null);

                rent.setTextColor(getResources().getColor(R.color.black));
                Type_work_select = "1";


            }
        });
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rent.setBackground(getResources().getDrawable(R.drawable.button_login));

                rent.setTextColor(getResources().getColor(R.color.white));


                For_sale.setBackground(null);

                For_sale.setTextColor(getResources().getColor(R.color.black));

                Type_work_select = "2";

            }
        });
        //------------------------------------------------------------------------------------------------------------


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setBackground(getResources().getDrawable(R.drawable.button_login));

                order.setTextColor(getResources().getColor(R.color.white));


                offer.setBackground(null);

                offer.setTextColor(getResources().getColor(R.color.black));
                if (Settings.CheckIsCompleate()) {
                    if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().toString().equals("1")) {
                        send_requst_by_type("fund_Request");


                    } else {
                        show_dialog();
//


                    }
                } else {
                    Settings.Dialog_not_compleate(getActivity());


                }


            }
        });
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offer.setBackground(getResources().getDrawable(R.drawable.button_login));

                offer.setTextColor(getResources().getColor(R.color.white));


                order.setBackground(null);

                order.setTextColor(getResources().getColor(R.color.black));


                send_requst_by_type("request_offer");


            }
        });
        filtter_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialogFragment_selectCity_fillter = new BottomSheetDialogFragment_SelectCity_fillter("");

                bottomSheetDialogFragment_selectCity_fillter.addItemClickListener(new BottomSheetDialogFragment_SelectCity_fillter.ItemClickListener() {
                    @Override
                    public void onItemClick(int id_city, String city_naem) {

                    }
                });


                bottomSheetDialogFragment_selectCity_fillter.show(getParentFragmentManager(), "");
            }
        });
        //------------------------------------------------------------------------------------------------------------

        if (Settings.CheckIsAccountAqarzMan()) {
            my_order_layout.setVisibility(View.VISIBLE);
            Shopping_request_layout.setVisibility(View.VISIBLE);
            Real_Estate_order_layout.setVisibility(View.VISIBLE);
        } else {
            my_order_layout.setVisibility(View.VISIBLE);
            Shopping_request_layout.setVisibility(View.GONE);
            Real_Estate_order_layout.setVisibility(View.GONE);
        }
        my_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_order_layout.setBackground(getResources().getDrawable(R.drawable.button_login));

                my_order_text.setTextColor(getResources().getColor(R.color.white));

                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.mash));

                Shopping_request_text.setTextColor(getResources().getColor(R.color.textColor));

                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));

                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.textColor));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(getContext(), R.color.textColor), android.graphics.PorterDuff.Mode.MULTIPLY);

                list_opration.setVisibility(View.VISIBLE);
                type_sale.setVisibility(View.VISIBLE);
                type_requst_xml.setVisibility(View.GONE);


                MyRequst.clear();

                orders_rec.setAdapter(new RecyclerView_orders_my_requst(getContext(), MyRequst));


                send_requst_by_type("my_request");

            }
        });
        Shopping_request_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.button_login));

                Shopping_request_text.setTextColor(getResources().getColor(R.color.white));


                my_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));

                my_order_text.setTextColor(getResources().getColor(R.color.textColor));


                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));

                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.textColor));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(getContext(), R.color.textColor), android.graphics.PorterDuff.Mode.MULTIPLY);


                list_opration.setVisibility(View.GONE);
                type_sale.setVisibility(View.VISIBLE);
                type_requst_xml.setVisibility(View.GONE);

//                WebService.loading(getActivity(), true);
                MyRequst.clear();


                orders_rec.setAdapter(new RecyclerView_orders_my_requst(getContext(), MyRequst));
                send_requst_by_type("market_demands");

            }
        });


        Real_Estate_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.mash));

                Shopping_request_text.setTextColor(getResources().getColor(R.color.textColor));


                my_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));

                my_order_text.setTextColor(getResources().getColor(R.color.textColor));


                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.background_order));

                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.white));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(getContext(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);


                list_opration.setVisibility(View.GONE);
                type_sale.setVisibility(View.GONE);

                MyRequst.clear();


                orders_rec.setAdapter(new RecyclerView_orders_my_requst(getContext(), MyRequst));


                if (Settings.CheckIsCompleate()) {
                    if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().toString().equals("1")) {
                        send_requst_by_type("fund_Request");
                        type_requst_xml.setVisibility(View.VISIBLE);

                    } else {
                        show_dialog();
//
                        type_requst_xml.setVisibility(View.GONE);

                    }
                } else {
                    Settings.Dialog_not_compleate(getActivity());
                    type_requst_xml.setVisibility(View.GONE);

                }


            }
        });
        list_opration.setVisibility(View.GONE);
        type_sale.setVisibility(View.GONE);


        my_order_layout.setBackground(getResources().getDrawable(R.drawable.button_login));

        my_order_text.setTextColor(getResources().getColor(R.color.white));

        Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.mash));

        Shopping_request_text.setTextColor(getResources().getColor(R.color.textColor));

        Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));

        Real_Estate_order_text.setTextColor(getResources().getColor(R.color.textColor));
        Real_Estate_order_image.setColorFilter(ContextCompat.getColor(getContext(), R.color.textColor), android.graphics.PorterDuff.Mode.MULTIPLY);

        list_opration.setVisibility(View.VISIBLE);
        type_sale.setVisibility(View.VISIBLE);


        MyRequst.clear();

        orders_rec.setAdapter(new RecyclerView_orders_my_requst(getContext(), MyRequst));

        send_requst_by_type("my_request");


    }


    public static OrdersFragment newInstance(String text) {

        OrdersFragment f = new OrdersFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }


    @Override
    public void onResume() {


        super.onResume();
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(getActivity(), false);

                type_requst = "" + requestType;


//{"status":true,"code":200,"message":"User Profile","data"


                try {
                    boolean status = response.getBoolean("status");
                    if (status) {

                        if (requestType.equals("my_request")) {
                            System.out.println("lfkdlfkdlkf");
                            String data = response.getString("data");


                            JSONObject jsonObject = new JSONObject(data);


                            String datax = jsonObject.getString("data");


                            JSONArray jsonArray = new JSONArray(datax);
                            orders_rec.setAdapter(null);
                            MyRequst.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();
                                demandsModules ordersModulesm = gson.fromJson(mJson, demandsModules.class);

//                                HomeModules ordersModulesm = gson.fromJson(mJson, HomeModules.class);
                                MyRequst.add(ordersModulesm);


                            }


                            orders_rec.setAdapter(new RecyclerView_orders_my_requstx(getContext(), MyRequst));

                            if (MyRequst.size() != 0) {
                                nodata_vis.setVisibility(View.GONE);
                            } else {
                                nodata_vis.setVisibility(View.VISIBLE);

                            }


                        } else if (requestType.equals("upgrade")) {

                            String message = response.getString("message");


//                            WebService.Make_Toast_color(getActivity(), message, "success");
                            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            final View popupView = layoutInflater.inflate(R.layout.upgrade_message, null);

                            ImageView close = popupView.findViewById(R.id.close);
                            Button ok = popupView.findViewById(R.id.ok);


                            close.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                }
                            });

                            ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    WebService.loading(getActivity(), true);

                                    init_volley();
                                    VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
                                    mVolleyService.getDataVolley("upgrade", WebService.upgrade);


                                    alertDialog.dismiss();
                                }
                            });


                            final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

//            alertDialog_country =
                            builder.setView(popupView);


                            alertDialog = builder.show();

                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


                        } else if (requestType.equals("market_demands")) {

                            System.out.println("lfkdlfkdlkf");
                            String data = response.getString("data");

                            JSONObject jsonObject_data = new JSONObject(data);

                            String data_inside = jsonObject_data.getString("data");
                            JSONArray jsonArray = new JSONArray(data_inside);
                            orders_rec.setAdapter(null);
                            demandsModules_list.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();

                                demandsModules ordersModulesm = gson.fromJson(mJson, demandsModules.class);
                                demandsModules_list.add(ordersModulesm);


                            }


                            orders_rec.setAdapter(new RecyclerView_orders_demandsx(getContext(), demandsModules_list));


                            if (demandsModules_list.size() != 0) {
                                nodata_vis.setVisibility(View.GONE);
                            } else {
                                nodata_vis.setVisibility(View.VISIBLE);

                            }
                        } else if (requestType.equals("request_offer")) {


                            System.out.println("lfkdlfkdlkf");
                            String data = response.getString("data");

//                            JSONObject jsonObject_data = new JSONObject(data);

//                            String data_inside = jsonObject_data.getString("data");
                            JSONArray jsonArray = new JSONArray(data);
                            orders_rec.setAdapter(null);
                            offerRealStateModulesLis.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();

                                OfferRealStateModules offerRealStateModuless = gson.fromJson(mJson, OfferRealStateModules.class);
                                offerRealStateModulesLis.add(offerRealStateModuless);


                            }

                            orders_rec.setAdapter(new RecyclerView_orders_offer_di(getContext(), offerRealStateModulesLis));

                            if (offerRealStateModulesLis.size() != 0) {
                                nodata_vis.setVisibility(View.GONE);
                            } else {
                                nodata_vis.setVisibility(View.VISIBLE);

                            }


                        } else {


                            System.out.println("lfkdlfkdlkf");
                            String data = response.getString("data");

                            JSONObject jsonObject_data = new JSONObject(data);

                            String data_inside = jsonObject_data.getString("data");
                            JSONArray jsonArray = new JSONArray(data_inside);
                            orders_rec.setAdapter(null);
                            ordersModules.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();

                                OrdersModules ordersModulesm = gson.fromJson(mJson, OrdersModules.class);
                                ordersModules.add(ordersModulesm);


                            }


                            orders_rec.setAdapter(new RecyclerView_ordersx(getContext(), ordersModules));
                            if (ordersModules.size() != 0) {
                                nodata_vis.setVisibility(View.GONE);
                            } else {
                                nodata_vis.setVisibility(View.VISIBLE);

                            }
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
                WebService.loading(getActivity(), false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    if (requestType.equals("my_request")) {
                        nodata_vis.setVisibility(View.VISIBLE);

                    } else if (requestType.equals("market_demands")) {
                        nodata_vis.setVisibility(View.VISIBLE);

                    } else {
                        WebService.Make_Toast_color(getActivity(), message, "error");

                    }


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

    public void show_dialog() {
        BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(getContext());
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View parentView = li.inflate(R.layout.upgrade_message2, null);


        Button accept = parentView.findViewById(R.id.accept);
        Button no = parentView.findViewById(R.id.no);
        ImageView close = parentView.findViewById(R.id.close);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheerDialog.cancel();

//                                    finish();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheerDialog.cancel();

//                                    finish();
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebService.loading(getActivity(), true);

                init_volley();
                VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
                mVolleyService.getDataVolley("upgrade", WebService.upgrade);
//
                bottomSheerDialog.cancel();
//
//                                    finish();
            }
        });
        bottomSheerDialog.setContentView(parentView);


        Window window = bottomSheerDialog.getWindow();
        window.findViewById(com.google.android.material.R.id.container).setFitsSystemWindows(false);
        // dark navigation bar icons
        View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getActivity().getResources().getDisplayMetrics());


//        ((View) decorView.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));


        bottomSheerDialog.show();
    }


    public void send_requst_by_type(String requst_type) {
        type_requst = requst_type;
        if (requst_type.equals("fund_Request")) {

            WebService.loading(getActivity(), true);

            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("fund_Request", WebService.fund_Request + "?estate_type_id=" + opration_select);


        } else if (requst_type.equals("market_demands")) {

            WebService.loading(getActivity(), true);

            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("market_demands", WebService.market_demands + "?estate_type_id=" + opration_select);

        } else if (requst_type.equals("my_request")) {
            WebService.loading(getActivity(), true);

            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("my_request", WebService.my_request + "?estate_type_id=" + opration_select);
        } else if (requst_type.equals("request_offer")) {
            WebService.loading(getActivity(), true);

            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("request_offer", WebService.my_fund_request_offer);
        }
    }

    public static void close_bottom() {
        bottomSheetDialogFragment_selectCity_fillter.dismiss();
    }
}
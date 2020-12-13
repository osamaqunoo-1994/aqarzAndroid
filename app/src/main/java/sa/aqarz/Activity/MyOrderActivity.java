package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.RecyclerView_All_Comfort_in_fragment;
import sa.aqarz.Adapter.RecyclerView_All_Type_order_;
import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import sa.aqarz.Adapter.RecyclerView_order_finince;
import sa.aqarz.Adapter.RecyclerView_order_rate;
import sa.aqarz.Adapter.RecyclerView_orders_demandsx;
import sa.aqarz.Adapter.RecyclerView_orders_my_requst;
import sa.aqarz.Adapter.RecyclerView_orders_my_requstx;
import sa.aqarz.Adapter.RecyclerView_orders_my_requstx_det;
import sa.aqarz.Adapter.RecyclerView_orders_my_requstx_rate;
import sa.aqarz.Adapter.RecyclerView_orders_offer_di;
import sa.aqarz.Adapter.RecyclerView_ordersx;
import sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares_orders_1;
import sa.aqarz.Dialog.BottomSheetDialogFragment_SelectCity_fillter;
import sa.aqarz.Modules.MyRateModules;
import sa.aqarz.Modules.OfferRealStateModules;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.Modules.RateModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.Modules.deferredInstallmentModules;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.Modules.financeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class MyOrderActivity extends AppCompatActivity {
ImageView back;
    static IResult mResultCallback;


    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;


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
    List<RateModules> rate_list = new ArrayList<>();
    List<financeModules> finance_list = new ArrayList<>();
    List<deferredInstallmentModules> deferredInstallmentModuleslist = new ArrayList<>();
    List<MyRateModules> myRateModules = new ArrayList<>();

    LinearLayout my_order_layout;
    LinearLayout Shopping_request_layout;
    LinearLayout Real_Estate_order_layout;
    TextView my_order_text;
    TextView Real_Estate_order_text;
    ImageView Real_Estate_order_image;
    TextView Shopping_request_text;

    ImageView convert_to_map;
    String convert_to_map_text = "list";
    List<TypeModules> data = new ArrayList<>();

    AlertDialog alertDialog;

    TextView For_sale, rent;
    LinearLayout nodata_vis;

    static String opration_select = "";
    String Type_work_select = "1";
    String type_requst = "";

    ImageView premium;
    LinearLayout not_premium;
    ImageView filtter_city;

    LinearLayout type_requst_xml;
    LinearLayout type_market_xml;
    TextView order;
    TextView offer;
    static String id_city_selected = "";

    static Activity activity;
    String type_order = "1";
    LinearLayout order_type;

    TextView rate_aq1, rent_aq1, finince_aq1, aqaerz_aq1;
    TextView governmental;
    TextView Special;
    TextView Soldier;
    TextView cumber_req;
    String tenant_job_type = "Purchase";


    String type_is_today_or_my_own = "";
    String type_is_today_or_not_market = "0";

    TextView order_today;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        activity = this;



        init();
    }
    public void init() {
        orders_rec = findViewById(R.id.orders_rec);

        type_requst_xml = findViewById(R.id.type_requst_xml);
        convert_to_map = findViewById(R.id.convert_to_map);
        type_market_xml = findViewById(R.id.type_market_xml);
        premium = findViewById(R.id.premium);
        not_premium = findViewById(R.id.not_premium);
        order = findViewById(R.id.order);
        offer = findViewById(R.id.offer);
        my_order_layout = findViewById(R.id.my_order_layout);
        Shopping_request_layout = findViewById(R.id.Shopping_request_layout);
        Real_Estate_order_layout = findViewById(R.id.Real_Estate_order_layout);
        my_order_text = findViewById(R.id.my_order_text);
        Real_Estate_order_text = findViewById(R.id.Real_Estate_order_text);
        Real_Estate_order_image = findViewById(R.id.Real_Estate_order_image);
        Shopping_request_text = findViewById(R.id.Shopping_request_text);
        list_opration = findViewById(R.id.list_opration);
        For_sale = findViewById(R.id.For_sale);
        rent = findViewById(R.id.rent);
        type_of_v = findViewById(R.id.type_of_v);
        type_sale = findViewById(R.id.type_sale);
        section_horizantal = findViewById(R.id.section_horizantal);
        nodata_vis = findViewById(R.id.nodata_vis);
        filtter_city = findViewById(R.id.filtter_city);
        rate_aq1 = findViewById(R.id.rate_aq1);
        rent_aq1 = findViewById(R.id.rent_aq1);
        finince_aq1 = findViewById(R.id.finince_aq1);
        aqaerz_aq1 = findViewById(R.id.aqaerz_aq1);
        order_type = findViewById(R.id.order_type);

        Soldier = findViewById(R.id.Soldier);
        Special = findViewById(R.id.Special);
        governmental = findViewById(R.id.governmental);
        cumber_req = findViewById(R.id.cumber_req);
        order_today = findViewById(R.id.order_today);

        try {
//            data = Settings.getSettings().getOprationType().getOriginal().getData();


            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(0));
            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(1));
            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(2));
            data.add(Settings.getSettings().getOprationType().getOriginal().getData().get(3));


            LinearLayoutManager layoutManager1
                    = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            list_opration.setLayoutManager(layoutManager1);
            RecyclerView_All_Type_order_ recyclerView_all_type_order_ = new RecyclerView_All_Type_order_(this, data);
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
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        orders_rec.setLayoutManager(layoutManager1);
//        orders_rec.setAdapter(new RecyclerView_orders(getContext(), ordersModules));
//------------------------------------------------------------------------------------------------------------
//        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();


        type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(0));
        type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(1));
        type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(2));
        type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(3));


        LinearLayoutManager layoutManagerxmx
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        type_of_v.setLayoutManager(layoutManagerxmx);


        RecyclerView_All_opration_bottom_sheet recyclerView_all_opration_bottom_sheet = new RecyclerView_All_opration_bottom_sheet(this, type_list);
        recyclerView_all_opration_bottom_sheet.addItemClickListener(new RecyclerView_All_opration_bottom_sheet.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                opration_select = type_list.get(position).getId().toString() + "";


                send_requst_by_type(type_requst);


            }
        });
        type_of_v.setAdapter(recyclerView_all_opration_bottom_sheet);


//---------------------------------------------------------------------------------
        governmental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                governmental.setBackground(getResources().getDrawable(R.drawable.circle_ss));

                governmental.setTextColor(getResources().getColor(R.color.white));


                Soldier.setBackground(null);

                Soldier.setTextColor(getResources().getColor(R.color.color_filter));

                Special.setBackground(null);

                Special.setTextColor(getResources().getColor(R.color.color_filter));

                send_requst_by_type("market_demands");

                tenant_job_type = "Purchase";
            }
        });
        Soldier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Soldier.setBackground(getResources().getDrawable(R.drawable.circle_ss));

                Soldier.setTextColor(getResources().getColor(R.color.white));


                governmental.setBackground(null);

                governmental.setTextColor(getResources().getColor(R.color.color_filter));
                Special.setBackground(null);

                Special.setTextColor(getResources().getColor(R.color.color_filter));
                tenant_job_type = "rent";
                send_requst_by_type("market_demands");

            }
        });
        init_volley();
        Special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Special.setBackground(getResources().getDrawable(R.drawable.circle_ss));

                Special.setTextColor(getResources().getColor(R.color.white));


                governmental.setBackground(null);

                governmental.setTextColor(getResources().getColor(R.color.color_filter));
                Soldier.setBackground(null);

                Soldier.setTextColor(getResources().getColor(R.color.color_filter));
                tenant_job_type = "investment";
                send_requst_by_type("market_demands");


            }
        });
        //------------------------------------------------------------------------------------------------------------


        aqaerz_aq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aqaerz_aq1.setBackground(getResources().getDrawable(R.drawable.button_login));

                aqaerz_aq1.setTextColor(getResources().getColor(R.color.white));


                finince_aq1.setBackground(null);
                finince_aq1.setTextColor(getResources().getColor(R.color.black));
                rent_aq1.setBackground(null);
                rent_aq1.setTextColor(getResources().getColor(R.color.black));
                rate_aq1.setBackground(null);
                rate_aq1.setTextColor(getResources().getColor(R.color.black));


                type_order = "1";
                type_sale.setVisibility(View.GONE);
                type_of_v.setVisibility(View.VISIBLE);

                send_requst_by_type("my_request");
            }
        });
        finince_aq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finince_aq1.setBackground(getResources().getDrawable(R.drawable.button_login));

                finince_aq1.setTextColor(getResources().getColor(R.color.white));

                type_sale.setVisibility(View.GONE);
                type_of_v.setVisibility(View.GONE);

                aqaerz_aq1.setBackground(null);
                aqaerz_aq1.setTextColor(getResources().getColor(R.color.black));
                rent_aq1.setBackground(null);
                rent_aq1.setTextColor(getResources().getColor(R.color.black));
                rate_aq1.setBackground(null);
                rate_aq1.setTextColor(getResources().getColor(R.color.black));
                type_order = "2";

                send_requst_by_type("my_request");

            }
        });
        rent_aq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rent_aq1.setBackground(getResources().getDrawable(R.drawable.button_login));

                rent_aq1.setTextColor(getResources().getColor(R.color.white));

                type_sale.setVisibility(View.GONE);
                type_of_v.setVisibility(View.GONE);

                finince_aq1.setBackground(null);
                finince_aq1.setTextColor(getResources().getColor(R.color.black));
                aqaerz_aq1.setBackground(null);
                aqaerz_aq1.setTextColor(getResources().getColor(R.color.black));
                rate_aq1.setBackground(null);
                rate_aq1.setTextColor(getResources().getColor(R.color.black));

                type_order = "3";

                send_requst_by_type("my_request");

            }
        });
        rate_aq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate_aq1.setBackground(getResources().getDrawable(R.drawable.button_login));

                rate_aq1.setTextColor(getResources().getColor(R.color.white));

                type_sale.setVisibility(View.GONE);
                type_of_v.setVisibility(View.GONE);

                finince_aq1.setBackground(null);
                finince_aq1.setTextColor(getResources().getColor(R.color.black));
                rent_aq1.setBackground(null);
                rent_aq1.setTextColor(getResources().getColor(R.color.black));
                aqaerz_aq1.setBackground(null);
                aqaerz_aq1.setTextColor(getResources().getColor(R.color.black));


                type_order = "4";

                send_requst_by_type("my_request");

            }
        });
        //------------------------------------------------------------------------------------------------------------


        For_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                For_sale.setBackground(getResources().getDrawable(R.drawable.button_login));

                For_sale.setTextColor(getResources().getColor(R.color.white));


                rent.setBackground(null);

                rent.setTextColor(getResources().getColor(R.color.black));
                Type_work_select = "1";
                send_requst_by_type(type_requst);


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


                send_requst_by_type(type_requst);

            }
        });
        //------------------------------------------------------------------------------------------------------------
        order_today.setBackground(getResources().getDrawable(R.drawable.background_fill_ccc));

        order_today.setTextColor(getResources().getColor(R.color.black));
        order_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type_is_today_or_not_market.equals("1")) {
                    type_is_today_or_not_market = "0";
                    order_today.setBackground(getResources().getDrawable(R.drawable.background_fill_ccc));

                    order_today.setTextColor(getResources().getColor(R.color.black));
                    send_requst_by_type("market_demands");

                } else {
                    order_today.setBackground(getResources().getDrawable(R.drawable.button_login));

                    order_today.setTextColor(getResources().getColor(R.color.white));
                    type_is_today_or_not_market = "1";
                    send_requst_by_type("market_demands");


                }
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (type_is_today_or_my_own.equals("today")) {


                    order.setBackground(getResources().getDrawable(R.drawable.background_fill_ccc));

                    order.setTextColor(getResources().getColor(R.color.black));


                    offer.setBackground(getResources().getDrawable(R.drawable.background_fill_ccc));

                    offer.setTextColor(getResources().getColor(R.color.black));


                    if (Settings.CheckIsCompleate()) {
                        if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().toString().equals("1")) {

                            send_requst_by_type("fund_Request");

                            type_is_today_or_my_own = "";

                        } else {
                            show_dialog();
//


                        }
                    } else {
                        Settings.Dialog_not_compleate(MyOrderActivity.this);


                    }

                } else {
                    order.setBackground(getResources().getDrawable(R.drawable.button_login));

                    order.setTextColor(getResources().getColor(R.color.white));


                    offer.setBackground(getResources().getDrawable(R.drawable.background_fill_ccc));

                    offer.setTextColor(getResources().getColor(R.color.black));
                    if (Settings.CheckIsCompleate()) {
                        if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().toString().equals("1")) {

                            send_requst_by_type("fund_Request_today");


                        } else {
                            show_dialog();
//


                        }
                    } else {
                        Settings.Dialog_not_compleate(MyOrderActivity.this);


                    }
                    type_is_today_or_my_own = "today";
                }


            }
        });
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (type_is_today_or_my_own.equals("my_own")) {
                    order.setBackground(getResources().getDrawable(R.drawable.background_fill_ccc));

                    order.setTextColor(getResources().getColor(R.color.black));


                    offer.setBackground(getResources().getDrawable(R.drawable.background_fill_ccc));

                    offer.setTextColor(getResources().getColor(R.color.black));

                    send_requst_by_type("fund_Request");
                    type_is_today_or_my_own = "my_own";
                } else {
                    offer.setBackground(getResources().getDrawable(R.drawable.button_login));

                    offer.setTextColor(getResources().getColor(R.color.white));


                    order.setBackground(null);

                    order.setTextColor(getResources().getColor(R.color.black));


                    send_requst_by_type("fund_Request_my_own");
                    type_is_today_or_my_own = "my_own";
                }


            }
        });
        filtter_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialogFragment_selectCity_fillter = new BottomSheetDialogFragment_SelectCity_fillter("");

                bottomSheetDialogFragment_selectCity_fillter.addItemClickListener(new BottomSheetDialogFragment_SelectCity_fillter.ItemClickListener() {
                    @Override
                    public void onItemClick(String id_city, String city_naem) {
                        id_city_selected = id_city + "";


                        send_requst_by_type(type_requst);

                    }
                });


                bottomSheetDialogFragment_selectCity_fillter.show(getSupportFragmentManager(), "");
            }
        });
//        //------------------------------------------------------------------------------------------------------------
//        convert_to_map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (convert_to_map_text.equals("list")) {
//                    convert_to_map_text = "map";
//                    orders_rec.setVisibility(View.GONE);
//                    mMapView.setVisibility(View.VISIBLE);
//                    googleMap.clear();
//
//                    for (int i = 0; i < demandsModules_list.size(); i++) {
//
//
////                        JsonParser parser = new JsonParser();
////                        JsonElement mJson = parser.parse(jsonArray.getString(i));
////
////                        Gson gson = new Gson();
////
//                        demandsModules ordersModulesm = demandsModules_list.get(i);
//                        String price = ordersModulesm.getPriceTo();
//                        try {
//
//                            int price_int = Integer.valueOf(price);
//
//                            int prices = (int) price_int;
//
//
//                            if (price_int < 1000) {
//
//                                price = prices + "";
//                            } else if (price_int > 1000 && price_int < 999999) {
//                                prices = (int) price_int / 1000;
//
//                                price = prices + getResources().getString(R.string.K);
//
//                            } else if (price_int > 999999) {
//                                prices = (int) price_int / 1000000;
//
//                                price = prices + getResources().getString(R.string.Million);
//
//                            }
////                            System.out.println(bankModules.getTotalPrice() + "--------------" + price);
//
////
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
////                        demandsModules_list.add(ordersModulesm);
//                        LatLng sydneya = new LatLng(Double.valueOf(ordersModulesm.getLat()), Double.valueOf(ordersModulesm.getLan()));
//                        googleMap.addMarker(new MarkerOptions()
//                                .position(sydneya)
//                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(price)))).setTag(i);//تم التغير الشكل من السكني للكلر
//
//                    }
//
//
//                } else {
//                    convert_to_map_text = "list";
//                    orders_rec.setVisibility(View.VISIBLE);
//                    mMapView.setVisibility(View.GONE);
//
//                    orders_rec.setAdapter(new RecyclerView_orders_demandsx(MyOrderActivity.this, demandsModules_list));
//
//
//                    if (demandsModules_list.size() != 0) {
//                        nodata_vis.setVisibility(View.GONE);
//                    } else {
//                        nodata_vis.setVisibility(View.VISIBLE);
//
//                    }
//
//                }
//
//
//            }
//        });
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
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(MyOrderActivity.this, R.color.textColor), android.graphics.PorterDuff.Mode.MULTIPLY);
                type_sale.setVisibility(View.GONE);

                list_opration.setVisibility(View.VISIBLE);
                type_sale.setVisibility(View.GONE);
                order_type.setVisibility(View.VISIBLE);
                type_of_v.setVisibility(View.VISIBLE);
                type_requst_xml.setVisibility(View.GONE);
                type_market_xml.setVisibility(View.GONE);
                convert_to_map.setVisibility(View.GONE);


                if (convert_to_map_text.equals("list")) {
//                    convert_to_map_text = "map";
//                    orders_rec.setVisibility(View.GONE);
//                    mMapView.setVisibility(View.VISIBLE);
                } else {
                    convert_to_map_text = "list";
                    orders_rec.setVisibility(View.VISIBLE);
                    mMapView.setVisibility(View.GONE);

                }


                aqaerz_aq1.setBackground(getResources().getDrawable(R.drawable.button_login));

                aqaerz_aq1.setTextColor(getResources().getColor(R.color.white));


                finince_aq1.setBackground(null);
                finince_aq1.setTextColor(getResources().getColor(R.color.black));
                rent_aq1.setBackground(null);
                rent_aq1.setTextColor(getResources().getColor(R.color.black));
                rate_aq1.setBackground(null);
                rate_aq1.setTextColor(getResources().getColor(R.color.black));


                type_order = "1";


                MyRequst.clear();

                orders_rec.setAdapter(new RecyclerView_orders_my_requst(MyOrderActivity.this, MyRequst));


                send_requst_by_type("my_request");

            }
        });
        list_opration.setVisibility(View.GONE);
        type_sale.setVisibility(View.GONE);
        type_market_xml.setVisibility(View.VISIBLE);
        convert_to_map.setVisibility(View.VISIBLE);
        type_requst_xml.setVisibility(View.GONE);
        type_requst_xml.setVisibility(View.GONE);
        order_type.setVisibility(View.GONE);


        Shopping_request_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.button_login));

                Shopping_request_text.setTextColor(getResources().getColor(R.color.white));


                my_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));

                my_order_text.setTextColor(getResources().getColor(R.color.textColor));


                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));

                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.textColor));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(MyOrderActivity.this, R.color.textColor), android.graphics.PorterDuff.Mode.MULTIPLY);


                list_opration.setVisibility(View.GONE);
                type_sale.setVisibility(View.GONE);
                type_market_xml.setVisibility(View.VISIBLE);
                convert_to_map.setVisibility(View.VISIBLE);
                type_requst_xml.setVisibility(View.GONE);
                type_requst_xml.setVisibility(View.GONE);
                order_type.setVisibility(View.GONE);

//                WebService.loading(getActivity(), true);
                MyRequst.clear();


                orders_rec.setAdapter(new RecyclerView_orders_my_requst(MyOrderActivity.this, MyRequst));
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
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(MyOrderActivity.this, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);


                if (convert_to_map_text.equals("list")) {
//                    convert_to_map_text = "map";
//                    orders_rec.setVisibility(View.GONE);
//                    mMapView.setVisibility(View.VISIBLE);
                } else {
                    convert_to_map_text = "list";
                    orders_rec.setVisibility(View.VISIBLE);
                    mMapView.setVisibility(View.GONE);

                }

                list_opration.setVisibility(View.GONE);
                type_sale.setVisibility(View.GONE);
                order_type.setVisibility(View.GONE);
                type_market_xml.setVisibility(View.GONE);
                convert_to_map.setVisibility(View.GONE);

                MyRequst.clear();

                order.setBackground(getResources().getDrawable(R.drawable.background_fill_ccc));

                order.setTextColor(getResources().getColor(R.color.black));


                offer.setBackground(getResources().getDrawable(R.drawable.background_fill_ccc));

                offer.setTextColor(getResources().getColor(R.color.black));

                orders_rec.setAdapter(new RecyclerView_orders_my_requst(MyOrderActivity.this, MyRequst));

                send_requst_by_type("fund_Request");
                type_requst_xml.setVisibility(View.VISIBLE);

//                if (Settings.CheckIsCompleate()) {
//
//
////                    if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().toString().equals("1")) {
////                        send_requst_by_type("fund_Request");
////                        type_requst_xml.setVisibility(View.VISIBLE);
////
////                    } else {
////                        show_dialog();
//////
////                        type_requst_xml.setVisibility(View.GONE);
////
////                    }
//                } else {
//                    Settings.Dialog_not_compleate(getActivity());
//                    type_requst_xml.setVisibility(View.GONE);
//
//                }


            }
        });


        if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().toString().equals("1")) {
            premium.setVisibility(View.VISIBLE);
            offer.setVisibility(View.VISIBLE);
            not_premium.setVisibility(View.GONE);

        } else {

            premium.setVisibility(View.GONE);
            offer.setVisibility(View.GONE);
            not_premium.setVisibility(View.VISIBLE);
        }


        list_opration.setVisibility(View.GONE);
        type_sale.setVisibility(View.GONE);


        my_order_layout.setBackground(getResources().getDrawable(R.drawable.button_login));

        my_order_text.setTextColor(getResources().getColor(R.color.white));

        Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.mash));

        Shopping_request_text.setTextColor(getResources().getColor(R.color.textColor));

        Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));

        Real_Estate_order_text.setTextColor(getResources().getColor(R.color.textColor));
        Real_Estate_order_image.setColorFilter(ContextCompat.getColor(MyOrderActivity.this, R.color.textColor), android.graphics.PorterDuff.Mode.MULTIPLY);

//        list_opration.setVisibility(View.VISIBLE);
//        type_sale.setVisibility(View.GONE);


        type_order = "1";


        MyRequst.clear();

        orders_rec.setAdapter(new RecyclerView_orders_my_requst(MyOrderActivity.this, MyRequst));
        my_order_layout.setBackground(getResources().getDrawable(R.drawable.button_login));

        my_order_text.setTextColor(getResources().getColor(R.color.white));

        Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.mash));

        Shopping_request_text.setTextColor(getResources().getColor(R.color.textColor));

        Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));

        Real_Estate_order_text.setTextColor(getResources().getColor(R.color.textColor));
        Real_Estate_order_image.setColorFilter(ContextCompat.getColor(MyOrderActivity.this, R.color.textColor), android.graphics.PorterDuff.Mode.MULTIPLY);
        type_sale.setVisibility(View.GONE);

        list_opration.setVisibility(View.VISIBLE);
        type_sale.setVisibility(View.GONE);
        order_type.setVisibility(View.VISIBLE);
        type_of_v.setVisibility(View.VISIBLE);
        type_requst_xml.setVisibility(View.GONE);
        type_market_xml.setVisibility(View.GONE);
        convert_to_map.setVisibility(View.GONE);

//        send_requst_by_type("my_request");
        aqaerz_aq1.setBackground(getResources().getDrawable(R.drawable.button_login));

        aqaerz_aq1.setTextColor(getResources().getColor(R.color.white));


        finince_aq1.setBackground(null);
        finince_aq1.setTextColor(getResources().getColor(R.color.black));
        rent_aq1.setBackground(null);
        rent_aq1.setTextColor(getResources().getColor(R.color.black));
        rate_aq1.setBackground(null);
        rate_aq1.setTextColor(getResources().getColor(R.color.black));


        type_order = "1";


        MyRequst.clear();

        orders_rec.setAdapter(new RecyclerView_orders_my_requst(MyOrderActivity.this, MyRequst));


        send_requst_by_type("my_request");




        convert_to_map.setVisibility(View.GONE);


    }
    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(MyOrderActivity.this, false);

                type_requst = "" + requestType;


//{"status":true,"code":200,"message":"User Profile","data"

                try {


                    boolean status = response.getBoolean("status");
                    if (status) {


                        try {
                            String datas = response.getString("data");
                            JSONObject jsonObjectz = new JSONObject(datas);
                            String total = jsonObjectz.getString("total");

                            cumber_req.setText(total + "");


                        } catch (Exception e) {

                        }

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


                            orders_rec.setAdapter(new RecyclerView_orders_my_requstx(MyOrderActivity.this, MyRequst));

                            if (MyRequst.size() != 0) {
                                nodata_vis.setVisibility(View.GONE);
                            } else {
                                nodata_vis.setVisibility(View.VISIBLE);

                            }


                        } else if (requestType.equals("my_deferredInstallment")) {
                            System.out.println("lfkdlfkdlkf");
                            String data = response.getString("data");


                            JSONObject jsonObject = new JSONObject(data);


                            String datax = jsonObject.getString("data");


                            JSONArray jsonArray = new JSONArray(datax);
                            orders_rec.setAdapter(null);
                            deferredInstallmentModuleslist.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();
                                deferredInstallmentModules ordersModulesm = gson.fromJson(mJson, deferredInstallmentModules.class);

//                                HomeModules ordersModulesm = gson.fromJson(mJson, HomeModules.class);
                                deferredInstallmentModuleslist.add(ordersModulesm);


                            }


                            orders_rec.setAdapter(new RecyclerView_orders_my_requstx_det(MyOrderActivity.this, deferredInstallmentModuleslist));

                            if (MyRequst.size() != 0) {
                                nodata_vis.setVisibility(View.GONE);
                            } else {
                                nodata_vis.setVisibility(View.VISIBLE);

                            }


                        } else if (requestType.equals("my_rate")) {
                            System.out.println("lfkdlfkdlkf");
                            String data = response.getString("data");


                            JSONObject jsonObject = new JSONObject(data);


                            String datax = jsonObject.getString("data");


                            JSONArray jsonArray = new JSONArray(datax);
                            orders_rec.setAdapter(null);
                            myRateModules.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();
                                MyRateModules ordersModulesm = gson.fromJson(mJson, MyRateModules.class);

//                                HomeModules ordersModulesm = gson.fromJson(mJson, HomeModules.class);
                                myRateModules.add(ordersModulesm);


                            }


                            orders_rec.setAdapter(new RecyclerView_orders_my_requstx_rate(MyOrderActivity.this, myRateModules));

                            if (MyRequst.size() != 0) {
                                nodata_vis.setVisibility(View.GONE);
                            } else {
                                nodata_vis.setVisibility(View.VISIBLE);

                            }


                        } else if (requestType.equals("my_rate")) {
                            System.out.println("lfkdlfkdlkf");
                            String data = response.getString("data");


                            JSONObject jsonObject = new JSONObject(data);


                            String datax = jsonObject.getString("data");


                            JSONArray jsonArray = new JSONArray(datax);
                            orders_rec.setAdapter(null);
                            rate_list.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();
                                RateModules ordersModulesm = gson.fromJson(mJson, RateModules.class);

//                                HomeModules ordersModulesm = gson.fromJson(mJson, HomeModules.class);
                                rate_list.add(ordersModulesm);


                            }


                            orders_rec.setAdapter(new RecyclerView_order_rate(MyOrderActivity.this, rate_list));

                            if (rate_list.size() != 0) {
                                nodata_vis.setVisibility(View.GONE);
                            } else {
                                nodata_vis.setVisibility(View.VISIBLE);

                            }


                        } else if (requestType.equals("my_finance")) {
                            System.out.println("lfkdlfkdlkf");
                            String data = response.getString("data");


                            JSONObject jsonObject = new JSONObject(data);


                            String datax = jsonObject.getString("data");


                            JSONArray jsonArray = new JSONArray(datax);
                            orders_rec.setAdapter(null);
                            finance_list.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();
                                financeModules ordersModulesm = gson.fromJson(mJson, financeModules.class);

//                                HomeModules ordersModulesm = gson.fromJson(mJson, HomeModules.class);
                                finance_list.add(ordersModulesm);


                            }


                            orders_rec.setAdapter(new RecyclerView_order_finince(MyOrderActivity.this, finance_list));

                            if (finance_list.size() != 0) {
                                nodata_vis.setVisibility(View.GONE);
                            } else {
                                nodata_vis.setVisibility(View.VISIBLE);

                            }


                        } else if (requestType.equals("upgrade")) {

                            String message = response.getString("message");


//                            WebService.Make_Toast_color(getActivity(), message, "success");
                            LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

                                    WebService.loading(MyOrderActivity.this, true);

                                    init_volley();
                                    VolleyService mVolleyService = new VolleyService(mResultCallback, MyOrderActivity.this);
                                    mVolleyService.getDataVolley("upgrade", WebService.upgrade);


                                    alertDialog.dismiss();
                                }
                            });


                            final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(MyOrderActivity.this);

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


                            if (convert_to_map_text.equals("list")) {
                                for (int i = 0; i < jsonArray.length(); i++) {


                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    demandsModules ordersModulesm = gson.fromJson(mJson, demandsModules.class);
                                    demandsModules_list.add(ordersModulesm);


                                }

                                orders_rec.setAdapter(new RecyclerView_orders_demandsx(MyOrderActivity.this, demandsModules_list));


                                if (demandsModules_list.size() != 0) {
                                    nodata_vis.setVisibility(View.GONE);
                                } else {
                                    nodata_vis.setVisibility(View.VISIBLE);

                                }
                            } else {
                                //-----------4343

                                googleMap.clear();

                                for (int i = 0; i < jsonArray.length(); i++) {


                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    demandsModules ordersModulesm = gson.fromJson(mJson, demandsModules.class);
                                    demandsModules_list.add(ordersModulesm);
                                    String price = ordersModulesm.getPriceTo();

                                    try {
                                        int price_int = Integer.valueOf(price);

                                        int prices = (int) price_int;


                                        if (price_int < 1000) {

                                            price = prices + "";
                                        } else if (price_int > 1000 && price_int < 999999) {
                                            prices = (int) price_int / 1000;

                                            price = prices + getResources().getString(R.string.K);

                                        } else if (price_int > 999999) {
                                            prices = (int) price_int / 1000000;

                                            price = prices + getResources().getString(R.string.Million);

                                        }
                                    } catch (Exception e) {

                                    }

//                                    System.out.println(bankModules.getTotalPrice() + "--------------" + price);
                                    LatLng sydneya = new LatLng(Double.valueOf(ordersModulesm.getLat()), Double.valueOf(ordersModulesm.getLan()));
                                    googleMap.addMarker(new MarkerOptions()
                                            .position(sydneya)
                                            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(price)))).setTag(i);//تم التغير الشكل من السكني للكلر

                                }


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

                            orders_rec.setAdapter(new RecyclerView_orders_offer_di(MyOrderActivity.this, offerRealStateModulesLis));

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


                            orders_rec.setAdapter(new RecyclerView_ordersx(MyOrderActivity.this, ordersModules));
                            if (ordersModules.size() != 0) {
                                nodata_vis.setVisibility(View.GONE);
                            } else {
                                nodata_vis.setVisibility(View.VISIBLE);

                            }
                        }
                    } else {

                        String message = response.getString("message");


                        WebService.Make_Toast_color(MyOrderActivity.this, message, "error");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());
                WebService.loading(MyOrderActivity.this, false);

                try {
                    ordersModules.clear();
                    orders_rec.setAdapter(new RecyclerView_ordersx(MyOrderActivity.this, ordersModules));
                    if (ordersModules.size() != 0) {
                        nodata_vis.setVisibility(View.GONE);
                    } else {
                        nodata_vis.setVisibility(View.VISIBLE);

                    }
                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    if (requestType.equals("my_request")) {
                        nodata_vis.setVisibility(View.VISIBLE);

                    } else if (requestType.equals("market_demands")) {
                        nodata_vis.setVisibility(View.VISIBLE);

                    } else {
                        WebService.Make_Toast_color(MyOrderActivity.this, message, "error");

                    }


                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }


            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(MyOrderActivity.this, false);

            }


        };


    }

    public void show_dialog() {
        BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(MyOrderActivity.this);
        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View parentView = li.inflate(R.layout.upgrade_message2, null);


        TextView accept = parentView.findViewById(R.id.accept);
        TextView no = parentView.findViewById(R.id.no);
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

                WebService.loading(MyOrderActivity.this, true);

                init_volley();
                VolleyService mVolleyService = new VolleyService(mResultCallback, MyOrderActivity.this);
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
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());


//        ((View) decorView.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));


        bottomSheerDialog.show();
    }




    public void send_requst_by_type(String requst_type) {
        type_requst = requst_type;
        WebService.loading(MyOrderActivity.this, true);
        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, MyOrderActivity.this);

        String id_city_ = "";

        if (!id_city_selected.equals("")) {
            id_city_ = "&city_id=" + id_city_selected;
        } else {
            id_city_ = "";
        }


        if (requst_type.equals("fund_Request")) {


            mVolleyService.getDataVolley("fund_Request", WebService.fund_Request + "?estate_type_id=" + opration_select + id_city_);


        } else if (requst_type.equals("fund_Request_my_own")) {


            mVolleyService.getDataVolley("fund_Request", WebService.fund_Request + "?estate_type_id=" + opration_select + id_city_ + "&myOwn=1");


        } else if (requst_type.equals("fund_Request_today")) {


            mVolleyService.getDataVolley("fund_Request", WebService.fund_Request + "?estate_type_id=" + opration_select + id_city_ + "&today=1");


        } else if (requst_type.equals("request_offer")) {


            mVolleyService.getDataVolley("request_offer", WebService.my_fund_request_offer + "?estate_type_id=" + opration_select + id_city_);


        } else if (requst_type.equals("my_request")) {


            if (type_order.equals("1")) {//aqarez
                if (Type_work_select.equals("1")) {//sell
                    mVolleyService.getDataVolley("my_request", WebService.my_request + "?estate_type_id=" + opration_select + id_city_);

                } else if (Type_work_select.equals("2")) {//rent
                    mVolleyService.getDataVolley("my_request", WebService.my_request + "?estate_type_id=" + opration_select + id_city_);
                }

            } else if (type_order.equals("2")) {//finincac
                mVolleyService.getDataVolley("my_finance", WebService.my_finance + "?estate_type=" + opration_select + id_city_);

            } else if (type_order.equals("3")) {//rent
                mVolleyService.getDataVolley("my_deferredInstallment", WebService.my_deferredInstallment + "?estate_type=" + opration_select + id_city_);

            } else if (type_order.equals("4")) {//rate
                mVolleyService.getDataVolley("my_rate", WebService.my_rate + "?estate_type=" + opration_select + id_city_);

            }

        } else if (requst_type.equals("market_demands")) {

            String today = "";
            if (type_is_today_or_not_market.equals("1")) {
                today = "&today=1";
            } else {
                today = "";
            }
//            tenant_job_type = "rent";

            if (tenant_job_type.equals("Purchase")) {//sell
                mVolleyService.getDataVolley("market_demands", WebService.market_demands + "?estate_type_id=" + opration_select + id_city_ + today);

            } else if (tenant_job_type.equals("rent")) {//rent
                mVolleyService.getDataVolley("market_demands", WebService.market_demands + "?estate_type_id=" + opration_select + id_city_ + today + "&estate_pay_type=is_rent");

            } else if (tenant_job_type.equals("investment")) {//rent
                mVolleyService.getDataVolley("market_demands", WebService.market_demands + "?estate_type_id=" + opration_select + id_city_ + today);

            }
            //


        }


    }

    public static void close_bottom() {
        bottomSheetDialogFragment_selectCity_fillter.dismiss();
    }

    public static void refrech() {
        String id_city_ = "";

        if (!id_city_selected.equals("")) {
            id_city_ = "&city_id=" + id_city_selected;
        } else {
            id_city_ = "";
        }
        VolleyService mVolleyService = new VolleyService(mResultCallback, activity);

        mVolleyService.getDataVolley("fund_Request", WebService.fund_Request + "?estate_type_id=" + opration_select + id_city_);

    }

    private Bitmap getMarkerBitmapFromView(String Price) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom, null);
        TextView markerImageView = (TextView) customMarkerView.findViewById(R.id.numb);
//        markerImageView.setImageResource(resId);
        markerImageView.setText(Price);


        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }
}
package sa.aqarz.Fragment;
/**
 * Created by osama on 10/8/2017.
 */


import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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
import java.util.Locale;
import java.util.Set;

import io.blushine.android.ui.showcase.MaterialShowcaseView;
import io.blushine.android.ui.showcase.ShowcaseListener;
import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.Auth.NewPasswordActivity;
import sa.aqarz.Activity.Auth.RegisterActivity;
import sa.aqarz.Activity.ContactUsActivity;
import sa.aqarz.Activity.DetailsAqarzManActivity;
import sa.aqarz.Activity.FavoriteActivity;
import sa.aqarz.Activity.FiltterOrderActivity;
import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Activity.PrivecyActivity;
import sa.aqarz.Activity.RealState.AllOfferOrderActivity;
import sa.aqarz.Activity.SplashScreenActivity;
import sa.aqarz.Activity.TermsActivity;
import sa.aqarz.Adapter.RecyclerView_All_Comfort_in_fragment;
import sa.aqarz.Adapter.RecyclerView_All_Type_order_;
import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet_type;
import sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import sa.aqarz.Adapter.RecyclerView_HomeList;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat;
import sa.aqarz.Adapter.RecyclerView_bottomSheet_type;
import sa.aqarz.Adapter.RecyclerView_city_bootom_sheets_multi;
import sa.aqarz.Adapter.RecyclerView_order_finince;
import sa.aqarz.Adapter.RecyclerView_order_rate;
import sa.aqarz.Adapter.RecyclerView_orders;
import sa.aqarz.Adapter.RecyclerView_orders_demands;
import sa.aqarz.Adapter.RecyclerView_orders_demandsx;
import sa.aqarz.Adapter.RecyclerView_orders_my_requst;
import sa.aqarz.Adapter.RecyclerView_orders_my_requstx;
import sa.aqarz.Adapter.RecyclerView_orders_my_requstx_det;
import sa.aqarz.Adapter.RecyclerView_orders_my_requstx_rate;
import sa.aqarz.Adapter.RecyclerView_orders_offer_di;
import sa.aqarz.Adapter.RecyclerView_ordersx;
import sa.aqarz.Adapter.RecyclerView_select_city;
import sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares;
import sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares_orders;
import sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares_orders_1;
import sa.aqarz.Dialog.BottomSheetDialogFragment_Filtter_order;
import sa.aqarz.Dialog.BottomSheetDialogFragment_SelectCity_fillter;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.FiltterModules;
import sa.aqarz.Modules.HomeModules;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.MyRateModules;
import sa.aqarz.Modules.OfferRealStateModules;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.Modules.RateModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.Modules.deferredInstallmentModules;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.Modules.financeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Application;
import sa.aqarz.Settings.LocaleUtils;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;
import de.hdodenhof.circleimageview.CircleImageView;


public class OrdersFragment extends Fragment {
    static IResult mResultCallback;
    static ProgressBar progress;
    BottomSheetDialogFragment_Filtter_order bottomSheetDialogFragment_filtter_order;
    TextView premium;
    LinearLayout Real_Estate_order_layout;
    ImageView Real_Estate_order_image;
    TextView Real_Estate_order_text;
    RecyclerView type_of_v;
    RecyclerView selected_list_city;
    RecyclerView selected_list_citys;
    RecyclerView list_opration;
    List<TypeModules> data = new ArrayList<>();
    MaterialShowcaseView materialShowcaseView;
    MaterialShowcaseView materialShowcaseView2;
    MaterialShowcaseView materialShowcaseView3;
    MaterialShowcaseView materialShowcaseView4;

    List<CityModules> cityModules_list = new ArrayList<>();
    List<CityModules> cityModules_list_filter = new ArrayList<>();
    List<CityModules> cityModules_list_filter_selected = new ArrayList<>();

    EditText text_search;
    static LinearLayout nodata_vis;
    List<TypeModules> type_list = new ArrayList<>();
    static List<demandsModules> demandsModules_list = new ArrayList<>();
    List<OfferRealStateModules> offerRealStateModulesLis = new ArrayList<>();
    List<RateModules> rate_list = new ArrayList<>();
    List<financeModules> finance_list = new ArrayList<>();
    List<deferredInstallmentModules> deferredInstallmentModuleslist = new ArrayList<>();
    List<MyRateModules> myRateModules = new ArrayList<>();

    static List<OrdersModules> ordersModules = new ArrayList<>();

    LinearLayout Shopping_request_layout;
    LinearLayout not_premium;
    LinearLayout fillter_layoiut;
    LinearLayout show_fillter;
    LinearLayout filtter_price_market_order;
    LinearLayout search_price_real_state;
    LinearLayout real;
    LinearLayout mark;
    TextView Shopping_request_text;

    TextView AllOrder;
    TextView today;
    TextView Myoffer;
    TextView search;
    public static Activity activity;

    TextView Myoffer_number;
    TextView today_number;
    TextView AllOrder_number;
    static int page = 1;

    RadioButton less, big;
    String opration_select = "";
    static String type_type = "fund_Request";
    static String type_requst = "today";
    static String price_order = "";
    static String tyype_type = "";

    ImageView favorit_button;

    public static ShowcaseView showCaseView;

    static RecyclerView orders_rec;


    public static FiltterModules filtterModules = new FiltterModules();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_orders, container, false);

        activity = getActivity();
        ints(v);
        return v;
    }


    public static OrdersFragment newInstance(String text) {

        OrdersFragment f = new OrdersFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    public void ints(View v) {
        Real_Estate_order_layout = v.findViewById(R.id.Real_Estate_order_layout);
        Real_Estate_order_text = v.findViewById(R.id.Real_Estate_order_text);
        Real_Estate_order_image = v.findViewById(R.id.Real_Estate_order_image);
        selected_list_city = v.findViewById(R.id.selected_list_city);
        fillter_layoiut = v.findViewById(R.id.fillter_layoiut);
        show_fillter = v.findViewById(R.id.show_fillter);
        less = v.findViewById(R.id.less);
        big = v.findViewById(R.id.big);
        Shopping_request_layout = v.findViewById(R.id.Shopping_request_layout);
        premium = v.findViewById(R.id.premium);
        filtter_price_market_order = v.findViewById(R.id.filtter_price_market_order);

        Shopping_request_text = v.findViewById(R.id.Shopping_request_text);
        orders_rec = v.findViewById(R.id.orders_rec);
        nodata_vis = v.findViewById(R.id.nodata_vis);
        text_search = v.findViewById(R.id.text_search);
        favorit_button = v.findViewById(R.id.favorit_button);
        mark = v.findViewById(R.id.mark);
        real = v.findViewById(R.id.real);


        AllOrder = v.findViewById(R.id.AllOrder);
        today = v.findViewById(R.id.today);
        Myoffer = v.findViewById(R.id.Myoffer);

        Myoffer_number = v.findViewById(R.id.Myoffer_number);
        today_number = v.findViewById(R.id.today_number);
        AllOrder_number = v.findViewById(R.id.AllOrder_number);
        not_premium = v.findViewById(R.id.not_premium);
        type_of_v = v.findViewById(R.id.type_of_v);
        search_price_real_state = v.findViewById(R.id.search_price_real_state);
        selected_list_citys = v.findViewById(R.id.selected_list_citys);

        list_opration = v.findViewById(R.id.list_opration);
        search = v.findViewById(R.id.search);

        progress = v.findViewById(R.id.progress);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        orders_rec.setLayoutManager(layoutManager1);
        LinearLayoutManager layoutManager1c
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        selected_list_city.setLayoutManager(layoutManager1c);
        LinearLayoutManager layoutManager1cs
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        selected_list_citys.setLayoutManager(layoutManager1cs);

        if (Application.AllCity.size() != 0) {
            progress.setVisibility(View.GONE);

            cityModules_list = Application.AllCity;


        } else {
            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("city", WebService.cities);

        }
        try {

            if (Hawk.contains("lang")) {


                Locale locale = new Locale(Hawk.get("lang").toString());
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getActivity().getBaseContext().getResources().updateConfiguration(config,
                        getActivity().getBaseContext().getResources().getDisplayMetrics());
            } else {

                Hawk.put("lang", LocaleUtils.getLanguage(getActivity()));

                Locale locale = new Locale(Hawk.get("lang").toString());
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getActivity().getBaseContext().getResources().updateConfiguration(config,
                        getActivity().getBaseContext().getResources().getDisplayMetrics());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().toString().equals("1")) {
            premium.setVisibility(View.VISIBLE);
//            offer.setVisibility(View.VISIBLE);
            not_premium.setVisibility(View.GONE);

        } else {

            premium.setVisibility(View.GONE);
//            offer.setVisibility(View.GONE);
            not_premium.setVisibility(View.VISIBLE);
        }
        text_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                selected_list_city.setVisibility(View.VISIBLE);
                cityModules_list_filter.clear();

                System.out.println("$$$" + s + "%%%" + s.length());


                if (s.length() > 3) {

                    for (int i = 0; i < cityModules_list.size(); i++) {

                        if (cityModules_list.get(i).getName().contains(s + "")) {

                            System.out.println("ddddddddddd" + cityModules_list.get(i).getName());
                            cityModules_list_filter.add(cityModules_list.get(i));


                        }


                    }
                    RecyclerView_city_bootom_sheets_multi recyclerView_city_bootom_sheets = new RecyclerView_city_bootom_sheets_multi(getContext(), cityModules_list_filter);
                    recyclerView_city_bootom_sheets.addItemClickListener2(new RecyclerView_city_bootom_sheets_multi.ItemClickListener2() {
                        @Override
                        public void onItemClick(CityModules id_) {
                            for (int i = 0; i < cityModules_list.size(); i++) {

                                if (cityModules_list.get(i).getId().toString().equals(id_.getId() + "")) {

                                    System.out.println("ddddddddddd" + cityModules_list.get(i).getName());
                                    cityModules_list.get(i).setSelected(id_.isSelected());


                                }


                            }

                            System.out.println("ddddddd####dddd");
                            Show_cityModules_list_filter_selected();

                        }
                    });

                    selected_list_city.setAdapter(recyclerView_city_bootom_sheets);

                } else {
                    cityModules_list_filter.clear();
                    RecyclerView_city_bootom_sheets_multi recyclerView_city_bootom_sheets = new RecyclerView_city_bootom_sheets_multi(getContext(), cityModules_list_filter);
                    recyclerView_city_bootom_sheets.addItemClickListener2(new RecyclerView_city_bootom_sheets_multi.ItemClickListener2() {


                        @Override
                        public void onItemClick(CityModules cityModules) {
                            cityModules_list.clear();
//                            cityModules_list = cityModules;

//                            if (cityModules_list.get(postion).isSelected()) {
//                                cityModules_list.get(postion).setSelected(false);
//
//                            } else {
//                                cityModules_list.get(postion).setSelected(true);
//
//                            }
                            Show_cityModules_list_filter_selected();

                        }
                    });

                    selected_list_city.setAdapter(recyclerView_city_bootom_sheets);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                if (!Hawk.contains("showCaseView_order")) {

                    Hawk.put("showCaseView_order", "showCaseView_order");

                    materialShowcaseView = new MaterialShowcaseView.Builder(getActivity())
                            .setTitleText(getResources().getString(R.string.title_realstate))
                            .setContentText(getResources().getString(R.string.desrealstate))
                            .setContentTextColor(getResources().getColor(R.color.white))

                            .setDismissText(getResources().getString(R.string.Nextt)) // Optional. When used can only dismiss the showcase by clicking on the dismiss button and target isn't pressable.
                            .setTarget(Real_Estate_order_layout)


//                            .setBackgroundColor(getResources().getColor(R.color.color_brimarys))
                            .setDelay(300) // Optional. But starting animations immediately in onCreate can make the choppy
                            .show();
                    materialShowcaseView.addListener(new ShowcaseListener() {
                        @Override
                        public void onShowcaseDisplayed(MaterialShowcaseView materialShowcaseView) {

                        }

                        @Override
                        public void onShowcaseDismissed(MaterialShowcaseView materialShowcaseView) {
                            materialShowcaseView2 = new MaterialShowcaseView.Builder(getActivity())
                                    .setTitleText(getResources().getString(R.string.title_market))
                                    .setContentText(getResources().getString(R.string.des_market))
                                    .setContentTextColor(getResources().getColor(R.color.white))

                                    .setDismissText(getResources().getString(R.string.Nextt)) // Optional. When used can only dismiss the showcase by clicking on the dismiss button and target isn't pressable.
                                    .setTarget(Shopping_request_layout)


//                            .setBackgroundColor(getResources().getColor(R.color.color_brimarys))
                                    .setDelay(300) // Optional. But starting animations immediately in onCreate can make the choppy
                                    .show();
                            materialShowcaseView2.addListener(new ShowcaseListener() {
                                @Override
                                public void onShowcaseDisplayed(MaterialShowcaseView materialShowcaseView) {

                                }

                                @Override
                                public void onShowcaseDismissed(MaterialShowcaseView materialShowcaseView) {
                                    materialShowcaseView3 = new MaterialShowcaseView.Builder(getActivity())
                                            .setTitleText(getResources().getString(R.string.title_All))
                                            .setContentText(getResources().getString(R.string.tdes_All))
                                            .setContentTextColor(getResources().getColor(R.color.white))

                                            .setDismissText(getResources().getString(R.string.Nextt)) // Optional. When used can only dismiss the showcase by clicking on the dismiss button and target isn't pressable.
                                            .setTarget(AllOrder)


//                            .setBackgroundColor(getResources().getColor(R.color.color_brimarys))
                                            .setDelay(300) // Optional. But starting animations immediately in onCreate can make the choppy
                                            .show();
                                    materialShowcaseView3.addListener(new ShowcaseListener() {
                                        @Override
                                        public void onShowcaseDisplayed(MaterialShowcaseView materialShowcaseView) {

                                        }

                                        @Override
                                        public void onShowcaseDismissed(MaterialShowcaseView materialShowcaseView) {
                                            materialShowcaseView4 = new MaterialShowcaseView.Builder(getActivity())
                                                    .setTitleText(getResources().getString(R.string.title_filter))
                                                    .setContentText(getResources().getString(R.string.dies_filter))
                                                    .setContentTextColor(getResources().getColor(R.color.white))

                                                    .setDismissText(getResources().getString(R.string.Nextt)) // Optional. When used can only dismiss the showcase by clicking on the dismiss button and target isn't pressable.
                                                    .setTarget(show_fillter)


//                            .setBackgroundColor(getResources().getColor(R.color.color_brimarys))
                                                    .setDelay(300) // Optional. But starting animations immediately in onCreate can make the choppy
                                                    .show();
                                        }

                                        @Override
                                        public void onShowcaseSkipped(MaterialShowcaseView materialShowcaseView) {

                                        }

                                        @Override
                                        public void onTargetPressed(MaterialShowcaseView materialShowcaseView) {

                                        }
                                    });

                                }

                                @Override
                                public void onShowcaseSkipped(MaterialShowcaseView materialShowcaseView) {

                                }

                                @Override
                                public void onTargetPressed(MaterialShowcaseView materialShowcaseView) {

                                }
                            });
                        }

                        @Override
                        public void onShowcaseSkipped(MaterialShowcaseView materialShowcaseView) {

                        }

                        @Override
                        public void onTargetPressed(MaterialShowcaseView materialShowcaseView) {

                        }
                    });


//                    showCaseView = new ShowcaseView.Builder(getActivity())
//                            .setTarget(new ViewTarget(R.id.Real_Estate_order_layout, getActivity()))
//
//                            .setContentTitle(getResources().getString(R.string.title_realstate))
//                            .setContentText(getResources().getString(R.string.desrealstate))
//
//                            .setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    showCaseView.hide();
//                                    showCaseView = new ShowcaseView.Builder(getActivity())
//                                            .setTarget(new ViewTarget(R.id.Shopping_request_layout, getActivity()))
//
//                                            .setContentTitle(getResources().getString(R.string.title_market))
//                                            .setContentText(getResources().getString(R.string.des_market))
//
//                                            .setOnClickListener(new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View v) {
//                                                    showCaseView.hide();
//                                                }
//                                            })
//
//
//                                            .setStyle(R.style.CustomShowcaseTheme2)
//                                            .build();
//                                }
//                            })
//
//
//                            .setStyle(R.style.CustomShowcaseTheme2)
//                            .build();


                }


            }
        }, 100); // After 1 seconds
        Action_Button();
    }

    public void Show_cityModules_list_filter_selected() {
        System.out.println("ooeooeoeoeeoe");
        cityModules_list_filter_selected.clear();

        for (int i = 0; i < cityModules_list.size(); i++) {


            if (cityModules_list.get(i).isSelected()) {

                cityModules_list_filter_selected.add(cityModules_list.get(i));
            }

        }
        selected_list_citys.setAdapter(new RecyclerView_select_city(getContext(), cityModules_list_filter_selected));

    }

    public void Action_Button() {


        AllOrder_number.setText(Settings.getSettings().getAllRequestFund() + "");
        today_number.setText(Settings.getSettings().getRequestFund() + "");
        Myoffer_number.setText(Settings.getSettings().getMyRequestFundOffer() + "");


        LinearLayoutManager layoutManagerxmx
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        type_of_v.setLayoutManager(layoutManagerxmx);

//        if (Type.equals("fund_Request")) {
//
//        } else if (Type.equals("market_demands")) {
//            type_list.addAll(Settings.getSettings().getEstate_types().getOriginal().getData());
//        }
        type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(0));
        type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(1));
        type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(2));
        type_list.add(Settings.getSettings().getEstate_types().getOriginal().getData().get(3));

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
                    if (position == 0) {
                        tyype_type = "sell";
                    } else if (position == 1) {

                        tyype_type = "rent";

                    } else {

                        tyype_type = "es";


                    }
                }
            });
            list_opration.setAdapter(recyclerView_all_type_order_);

        } catch (Exception e) {
            e.printStackTrace();
        }
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillter_layoiut.setVisibility(View.GONE);

                OrdersFragment.filtterModules.setOrder_by_price(price_order + "");
                String city = "";
                for (int i = 0; i < cityModules_list.size(); i++) {
                    if (city.equals("")) {
                        city = "" + cityModules_list.get(i).getId();
                    } else {
                        city = city + "," + cityModules_list.get(i).getId();

                    }
                }

                if (type_type.equals("fund_Request")) {

                    System.out.println(city);
                    OrdersFragment.filtterModules.setSelect_city(city + "");
                    OrdersFragment.filtterModules.setType_oprtion(opration_select + "");
                    OrdersFragment.filtterModules.setType_type("");
                    OrdersFragment.filtterModules.setOrder_by_price("price_order");

                } else {

                    System.out.println(city);
                    OrdersFragment.filtterModules.setSelect_city(city + "");
                    OrdersFragment.filtterModules.setType_oprtion(opration_select + "");
                    OrdersFragment.filtterModules.setType_type(tyype_type);

                }
                page = 1;


                GetData(type_requst);


            }
        });
        show_fillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillter_layoiut.setVisibility(View.VISIBLE);
            }
        });

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
        favorit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), FavoriteActivity.class);
                getActivity().startActivity(intent);


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

        Real_Estate_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.mash));


//                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.background_order));


                Shopping_request_text.setTextColor(getResources().getColor(R.color.textColor));

                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.colorPrimary));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);

                filtter_price_market_order.setVisibility(View.GONE);
                search_price_real_state.setVisibility(View.VISIBLE);
                mark.setVisibility(View.GONE);
                real.setVisibility(View.VISIBLE);
                page = 1;

                type_type = "fund_Request";
                GetData(type_requst);


                AllOrder_number.setText(Settings.getSettings().getAllRequestFund() + "");
                today_number.setText(Settings.getSettings().getRequestFund() + "");
                Myoffer_number.setText(Settings.getSettings().getMyRequestFundOffer() + "");

            }
        });


        Shopping_request_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.button_login));

                Shopping_request_text.setTextColor(getResources().getColor(R.color.colorPrimary));


//                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));

                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.textColor));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(getContext(), R.color.textColor), PorterDuff.Mode.MULTIPLY);

                filtter_price_market_order.setVisibility(View.VISIBLE);
                search_price_real_state.setVisibility(View.GONE);
                mark.setVisibility(View.VISIBLE);
                real.setVisibility(View.GONE);
                page = 1;

                type_type = "market_demands";


                GetData(type_requst);


                AllOrder_number.setText(Settings.getSettings().getAllRequest() + "");
                today_number.setText(Settings.getSettings().getMarketDemands() + "");
                Myoffer_number.setText(Settings.getSettings().getMyRequestOffer() + "");


            }
        });

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllOrder.setBackground(getResources().getDrawable(R.drawable.mash));
                today.setBackground(getResources().getDrawable(R.drawable.button_login));
                Myoffer.setBackground(getResources().getDrawable(R.drawable.mash));


                Myoffer_number.setBackground(getResources().getDrawable(R.drawable.mash));
                today_number.setBackground(getResources().getDrawable(R.drawable.circle_ss));
                AllOrder_number.setBackground(getResources().getDrawable(R.drawable.mash));


                AllOrder.setTextColor(getResources().getColor(R.color.textColor));
                today.setTextColor(getResources().getColor(R.color.white));
                Myoffer.setTextColor(getResources().getColor(R.color.textColor));

                Myoffer_number.setTextColor(getResources().getColor(R.color.textColor));
                today_number.setTextColor(getResources().getColor(R.color.white));
                AllOrder_number.setTextColor(getResources().getColor(R.color.textColor));


                page = 1;

                type_requst = "today";
                GetData(type_requst);

            }
        });


        Myoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllOrder.setBackground(getResources().getDrawable(R.drawable.mash));
                today.setBackground(getResources().getDrawable(R.drawable.mash));
                Myoffer.setBackground(getResources().getDrawable(R.drawable.button_login));


                Myoffer_number.setBackground(getResources().getDrawable(R.drawable.circle_ss));
                today_number.setBackground(getResources().getDrawable(R.drawable.mash));
                AllOrder_number.setBackground(getResources().getDrawable(R.drawable.mash));


                AllOrder.setTextColor(getResources().getColor(R.color.textColor));
                today.setTextColor(getResources().getColor(R.color.textColor));
                Myoffer.setTextColor(getResources().getColor(R.color.white));


                Myoffer_number.setTextColor(getResources().getColor(R.color.white));
                today_number.setTextColor(getResources().getColor(R.color.textColor));
                AllOrder_number.setTextColor(getResources().getColor(R.color.textColor));


                page = 1;

                type_requst = "Myoffer";
                GetData(type_requst);


            }
        });
        AllOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllOrder.setBackground(getResources().getDrawable(R.drawable.button_login));
                today.setBackground(getResources().getDrawable(R.drawable.mash));
                Myoffer.setBackground(getResources().getDrawable(R.drawable.mash));


                Myoffer_number.setBackground(getResources().getDrawable(R.drawable.mash));
                today_number.setBackground(getResources().getDrawable(R.drawable.mash));
                AllOrder_number.setBackground(getResources().getDrawable(R.drawable.circle_ss));


                AllOrder.setTextColor(getResources().getColor(R.color.white));
                today.setTextColor(getResources().getColor(R.color.textColor));
                Myoffer.setTextColor(getResources().getColor(R.color.textColor));

                Myoffer_number.setTextColor(getResources().getColor(R.color.textColor));
                today_number.setTextColor(getResources().getColor(R.color.textColor));
                AllOrder_number.setTextColor(getResources().getColor(R.color.white));


                page = 1;

                type_requst = "AllOrder";
                GetData(type_requst);


            }
        });


//        Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.mash));

        Shopping_request_text.setTextColor(getResources().getColor(R.color.textColor));


//        Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.background_order));

        Real_Estate_order_text.setTextColor(getResources().getColor(R.color.colorPrimary));
        Real_Estate_order_image.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);


        page = 1;

        type_type = "fund_Request";
        GetData(type_requst);


    }


    @Override
    public void onResume() {


        super.onResume();
    }

    public static void GetData(String type) {
        type_requst = type;
        if (page > 1) {
            progress.setVisibility(View.VISIBLE);
        } else {
            WebService.loading(activity, true);
        }

        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, activity);
        String id_city_ = "";
        if (!filtterModules.getSelect_city().equals("")) {
            id_city_ = "&city_id=" + filtterModules.getSelect_city();
        } else {
            id_city_ = "";
        }
        String opration_select = "";

        if (!filtterModules.getType_oprtion().equals("")) {
            opration_select = "&estate_type_id=" + filtterModules.getType_oprtion();
        } else {
            opration_select = "";
        }


        if (type_type.equals("fund_Request")) {


            if (type_requst.equals("today")) {
                mVolleyService.getDataVolley("fund_Request", WebService.fund_Request + "?" + "page=" + page + "&today=1" + id_city_ + opration_select);

            } else if (type_requst.equals("AllOrder")) {
                mVolleyService.getDataVolley("fund_Request", WebService.fund_Request + "?" + "page=" + page + id_city_ + opration_select);

            } else if (type_requst.equals("Myoffer")) {
                mVolleyService.getDataVolley("fund_Request", WebService.fund_Request + "?" + "page=" + page + "&myOwn=1" + id_city_ + opration_select);

            }

        } else {

            mVolleyService.getDataVolley("my_request", WebService.my_request);

            if (type_requst.equals("today")) {
                mVolleyService.getDataVolley("market_demands", WebService.market_demands + "?" + "page=" + page + "&today=1" + id_city_ + opration_select);

            } else if (type_requst.equals("AllOrder")) {
                mVolleyService.getDataVolley("market_demands", WebService.market_demands + "?" + "page=" + page + id_city_ + opration_select);

            } else if (type_requst.equals("Myoffer")) {
                mVolleyService.getDataVolley("market_demands", WebService.market_demands + "?" + "page=" + page + "&myOwn=1" + id_city_ + opration_select);

            }

        }


    }

    public static void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(activity, false);

//                type_requst = "" + requestType;


//{"status":true,"code":200,"message":"User Profile","data"

                try {


                    if (requestType.equals("city")) {

                        String data = response.getString("data");

                        Hawk.put("AllCity", data);


                    } else if (requestType.equals("market_demands")) {

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

                        orders_rec.setAdapter(new RecyclerView_orders_demandsx(activity, demandsModules_list));


                        if (demandsModules_list.size() != 0) {
                            nodata_vis.setVisibility(View.GONE);
                        } else {
                            nodata_vis.setVisibility(View.VISIBLE);


                        }


                    } else if (requestType.equals("fund_Request")) {


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

                        RecyclerView_ordersx recyclerView_ordersx = new RecyclerView_ordersx(activity, ordersModules);
                        orders_rec.setAdapter(recyclerView_ordersx);

                        if (ordersModules.size() != 0) {
                            nodata_vis.setVisibility(View.GONE);
                        } else {
                            nodata_vis.setVisibility(View.VISIBLE);

                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());
                WebService.loading(activity, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    if (requestType.equals("my_request")) {
//                        nodata_vis.setVisibility(View.VISIBLE);

                    } else if (requestType.equals("market_demands")) {
//                        nodata_vis.setVisibility(View.VISIBLE);

                    } else {
                        WebService.Make_Toast_color(activity, message, "error");

                    }


                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }


            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(activity, false);

            }


        };


    }


}
package sa.aqarz.Fragment;
/**
 * Created by osama on 10/8/2017.
 */


import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.RecyclerView_orders_demandsx;
import sa.aqarz.Adapter.RecyclerView_ordersx;
import sa.aqarz.Dialog.BottomSheetDialogFragment_Filtter_order;
import sa.aqarz.Modules.FiltterModules;
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


public class OrdersFragment_old_2 extends Fragment {
    static IResult mResultCallback;
    static ProgressBar progress;
    BottomSheetDialogFragment_Filtter_order bottomSheetDialogFragment_filtter_order;
    ImageView premium;
    LinearLayout Real_Estate_order_layout;
    ImageView Real_Estate_order_image;
    TextView Real_Estate_order_text;

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
    TextView Shopping_request_text;

    TextView AllOrder;
    TextView today;
    TextView Myoffer;
    public static Activity activity;

    TextView Myoffer_number;
    TextView today_number;
    TextView AllOrder_number;
    static int page = 1;


    static String type_type = "fund_Request";
    static String type_requst = "today";

    ImageView open_filter;

    static RecyclerView orders_rec;


    public static FiltterModules filtterModules = new FiltterModules();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_orders, container, false);

        activity = getActivity();
        ints(v);
        return v;
    }


    public static OrdersFragment_old_2 newInstance(String text) {

        OrdersFragment_old_2 f = new OrdersFragment_old_2();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    public void ints(View v) {
        Real_Estate_order_layout = v.findViewById(R.id.Real_Estate_order_layout);
        Real_Estate_order_text = v.findViewById(R.id.Real_Estate_order_text);
        Real_Estate_order_image = v.findViewById(R.id.Real_Estate_order_image);

        Shopping_request_layout = v.findViewById(R.id.Shopping_request_layout);
        premium = v.findViewById(R.id.premium);

        Shopping_request_text = v.findViewById(R.id.Shopping_request_text);
        orders_rec = v.findViewById(R.id.orders_rec);
        nodata_vis = v.findViewById(R.id.nodata_vis);


        AllOrder = v.findViewById(R.id.AllOrder);
        today = v.findViewById(R.id.today);
        Myoffer = v.findViewById(R.id.Myoffer);

        Myoffer_number = v.findViewById(R.id.Myoffer_number);
        today_number = v.findViewById(R.id.today_number);
        AllOrder_number = v.findViewById(R.id.AllOrder_number);
        not_premium = v.findViewById(R.id.not_premium);


        progress = v.findViewById(R.id.progress);
        open_filter = v.findViewById(R.id.open_filter);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        orders_rec.setLayoutManager(layoutManager1);


        if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().toString().equals("1")) {
            premium.setVisibility(View.VISIBLE);
//            offer.setVisibility(View.VISIBLE);
            not_premium.setVisibility(View.GONE);

        } else {

            premium.setVisibility(View.GONE);
//            offer.setVisibility(View.GONE);
            not_premium.setVisibility(View.VISIBLE);
        }


        Action_Button();
    }

    public void Action_Button() {


        AllOrder_number.setText(Settings.getSettings().getAllRequestFund() + "");
        today_number.setText(Settings.getSettings().getRequestFund() + "");
        Myoffer_number.setText(Settings.getSettings().getMyRequestFundOffer() + "");


        Real_Estate_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.mash));

                Shopping_request_text.setTextColor(getResources().getColor(R.color.textColor));


                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.background_order));

                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.white));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(getContext(), R.color.white), PorterDuff.Mode.MULTIPLY);


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
                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.button_login));

                Shopping_request_text.setTextColor(getResources().getColor(R.color.white));


                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.mash));

                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.textColor));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(getContext(), R.color.textColor), PorterDuff.Mode.MULTIPLY);


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

        open_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                Intent intent = new Intent(getContext(), FiltterOrderActivity.class);
////                Bundle bndlAnimation = ActivityOptions.makeCustomAnimation(getContext(), R.anim.rh, R.anim.slideinright).toBundle();
//
//
//                startActivity(intent);
//
//                getActivity().overridePendingTransition(R.anim.rh, R.anim.ex);


                bottomSheetDialogFragment_filtter_order = new BottomSheetDialogFragment_Filtter_order(type_type);
                bottomSheetDialogFragment_filtter_order.addItemClickListener(new BottomSheetDialogFragment_Filtter_order.ItemClickListener() {
                    @Override
                    public void onItemClick(int id_city, String city_naem) {
                        bottomSheetDialogFragment_filtter_order.dismiss();
                        page = 1;

                        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + filtterModules.getOrder_by_price() + filtterModules.getSelect_city() + filtterModules.getType_oprtion() + filtterModules.getType_type());


                        GetData(type_requst);


                    }
                });
                bottomSheetDialogFragment_filtter_order.show(getChildFragmentManager(), "");

            }
        });


        Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.mash));

        Shopping_request_text.setTextColor(getResources().getColor(R.color.textColor));


        Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.background_order));

        Real_Estate_order_text.setTextColor(getResources().getColor(R.color.white));
        Real_Estate_order_image.setColorFilter(ContextCompat.getColor(getContext(), R.color.white), PorterDuff.Mode.MULTIPLY);


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

                    if (requestType.equals("market_demands")) {

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
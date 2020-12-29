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
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
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
import java.util.Set;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.Auth.NewPasswordActivity;
import sa.aqarz.Activity.Auth.RegisterActivity;
import sa.aqarz.Activity.ContactUsActivity;
import sa.aqarz.Activity.DetailsAqarzManActivity;
import sa.aqarz.Activity.FiltterOrderActivity;
import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Activity.PrivecyActivity;
import sa.aqarz.Activity.SplashScreenActivity;
import sa.aqarz.Activity.TermsActivity;
import sa.aqarz.Adapter.RecyclerView_All_Comfort_in_fragment;
import sa.aqarz.Adapter.RecyclerView_All_Type_order_;
import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import sa.aqarz.Adapter.RecyclerView_HomeList;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat;
import sa.aqarz.Adapter.RecyclerView_bottomSheet_type;
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
import sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares;
import sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares_orders;
import sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares_orders_1;
import sa.aqarz.Dialog.BottomSheetDialogFragment_SelectCity_fillter;
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
import sa.aqarz.Settings.LocaleUtils;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;
import de.hdodenhof.circleimageview.CircleImageView;


public class OrdersFragment extends Fragment {
    static IResult mResultCallback;
    static ProgressBar progress;


    LinearLayout Real_Estate_order_layout;
    ImageView Real_Estate_order_image;
    TextView Real_Estate_order_text;


    LinearLayout Shopping_request_layout;
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

        Shopping_request_layout = v.findViewById(R.id.Shopping_request_layout);

        Shopping_request_text = v.findViewById(R.id.Shopping_request_text);


        AllOrder = v.findViewById(R.id.AllOrder);
        today = v.findViewById(R.id.today);
        Myoffer = v.findViewById(R.id.Myoffer);

        Myoffer_number = v.findViewById(R.id.Myoffer_number);
        today_number = v.findViewById(R.id.today_number);
        AllOrder_number = v.findViewById(R.id.AllOrder_number);


        progress = v.findViewById(R.id.progress);
        open_filter = v.findViewById(R.id.open_filter);


        Action_Button();
    }

    public void Action_Button() {

        Real_Estate_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.mash));

                Shopping_request_text.setTextColor(getResources().getColor(R.color.textColor));


                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.background_order));

                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.white));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(getContext(), R.color.white), PorterDuff.Mode.MULTIPLY);

                type_type = "fund_Request";
                GetData(type_requst);

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

                type_type = "market_demands";


                GetData(type_requst);
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

                type_requst = "AllOrder";
                GetData(type_requst);


            }
        });

        open_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), FiltterOrderActivity.class);
//                Bundle bndlAnimation = ActivityOptions.makeCustomAnimation(getContext(), R.anim.rh, R.anim.slideinright).toBundle();


                startActivity(intent);

                getActivity().overridePendingTransition(R.anim.rh, R.anim.ex);

            }
        });
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


        if (type_type.equals("fund_Request")) {

            mVolleyService.getDataVolley("fund_Request", WebService.fund_Request);


            if (type_requst.equals("today")) {

            } else if (type_requst.equals("AllOrder")) {

            } else if (type_requst.equals("Myoffer")) {

            }

        } else {

            mVolleyService.getDataVolley("my_request", WebService.my_request);

            if (type_requst.equals("today")) {

            } else if (type_requst.equals("AllOrder")) {

            } else if (type_requst.equals("Myoffer")) {

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
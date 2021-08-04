package sa.aqarz.Fragment;
/**
 * Created by osama on 10/8/2017.
 */


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
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ActionViewTarget;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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
import java.util.TreeMap;

import io.blushine.android.ui.showcase.MaterialShowcaseView;
import io.blushine.android.ui.showcase.ShowcaseListener;
import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.ChateActivity;
import sa.aqarz.Activity.DetailsActivity;
import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Activity.NotficationActvity;
import sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import sa.aqarz.Activity.OprationAqarz.RequestOrderActivity;
import sa.aqarz.Activity.OprationNew.AqarzOrActivity;
import sa.aqarz.Activity.OprationNew.RequestServiceActivity;
import sa.aqarz.Activity.OrderListActivity;
import sa.aqarz.Activity.SplashScreenActivity;
import sa.aqarz.Adapter.RecyclerView_All_Opration_in_map;
import sa.aqarz.Adapter.RecyclerView_All_Type_in_map;
import sa.aqarz.Adapter.RecyclerView_All_select_oprat_in_fragment;
import sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import sa.aqarz.Adapter.RecyclerView_All_type_in_fragment1;
import sa.aqarz.Adapter.RecyclerView_HomeList;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat;
import sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares;
import sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares_orders;
import sa.aqarz.Dialog.BottomSheetDialogFragment_Filtter;
import sa.aqarz.Modules.BankModules;
import sa.aqarz.Modules.CityLocation;
import sa.aqarz.Modules.HomeModules;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.OprationModules;
import sa.aqarz.Modules.RegionModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.Modules.select_typeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.CustomInfoWindowGoogleMap;
import sa.aqarz.Settings.CustomInfoWindowGoogleMaptyp_2;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.SingleShotLocationProvider;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class MapsFragment extends Fragment {
    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;

    TextView Orders_tab;
    TextView Offers_tab;
    MaterialShowcaseView materialShowcaseView;
    MaterialShowcaseView materialShowcaseView2;
    MaterialShowcaseView materialShowcaseView3;
    MaterialShowcaseView materialShowcaseView4;

    String final_type_requst_filter = "";
    String typeTab = "Orders_tab";

    TextView RequstAqars;
    TextView addAqares;
    RecyclerView type;
    RecyclerView selsct_type_all;

    List<RegionModules> city_location = new ArrayList<>();
    List<CityLocation> list_city = new ArrayList<>();


    List<TypeModules> typeModules_list = new ArrayList<>();
    List<select_typeModules> oprationModules_list = new ArrayList<>();
    CircleImageView image_profile;

    IResult mResultCallback;

    List<TypeModules> type_list = new ArrayList<>();


    List<HomeModules> homeModules = new ArrayList<>();
    List<HomeModules_aqares> homeModules_aqares = new ArrayList<>();
    String x_latitude = "";
    String x_longitude = "";
    RelativeLayout layout_list;
    LinearLayout layout_button;
    RelativeLayout layout_button_2;

    ImageView convert_map_to_list;
    ImageView change_list_to_map;
    ImageView search_btn;
    ImageView chate;
    ImageView notfication;

    String opration_select = "";
    String convert_type = "map";

    public static ShowcaseView showCaseView;
    ShowcaseView showCaseView1;
    ShowcaseView showCaseView2;
    RecyclerView list_aqaers;
    ImageView chmnage_map_style;


    String style = "";

    ImageView get_location;
    ImageView filtter;
    ImageView cityMap;


    ImageView nodata_vis;


    String filtter_selected = "";
    LinearLayout laout_of_change;
    EditText search_text;


    TextView RealStatr_order;
    TextView MarketOrder;
    TextView OfferOrder;


    String type_selected = "Rela";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = v.findViewById(R.id.mapViewxx);
        Orders_tab = v.findViewById(R.id.Orders_tab);
        Offers_tab = v.findViewById(R.id.Offers_tab);
        cityMap = v.findViewById(R.id.cityMap);
        notfication = v.findViewById(R.id.notfication);
        chate = v.findViewById(R.id.chate);
        RealStatr_order = v.findViewById(R.id.RealStatr_order);
        MarketOrder = v.findViewById(R.id.MarketOrder);
        OfferOrder = v.findViewById(R.id.OfferOrder);


        init(v);
        getProfile();


//        try {
//            if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                style = "MAP_TYPE_NORMAL";
                googleMap.getUiSettings().setRotateGesturesEnabled(true);


                //get latlong for corners for specified place
                LatLng one = new LatLng(30.250032, 38.374554);
                LatLng two = new LatLng(19.117340, 49.913804);
//                LatLng three = new LatLng(25.784818, 49.666975);
//                LatLng forth = new LatLng(23.031780, 39.361870);

                LatLngBounds.Builder builder = new LatLngBounds.Builder();

                //add them to builder
                builder.include(one);
                builder.include(two);
//                builder.include(three);
//                builder.include(forth);

                LatLngBounds bounds = builder.build();

                //get width and height to current display screen
                int width = getResources().getDisplayMetrics().widthPixels;
                int height = getResources().getDisplayMetrics().heightPixels;

                // 20% padding
                int padding = (int) (width * 0.05);

                //set latlong bounds
                mMap.setLatLngBoundsForCameraTarget(bounds);

                //move camera to fill the bound to screen
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));

                //set zoom to level to current so that you won't be able to zoom out viz. move outside bounds
                mMap.setMinZoomPreference(mMap.getCameraPosition().zoom);


//
//                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                    @Override
//                    public void onMapClick(LatLng latLng) {
//
//
////                        ic_marker_lo__
//                    }
//                });


                if (!MainActivity.first_time_open_app) {

                    try {
                        String data = Hawk.get("FF").toString();
                        JSONArray jsonArray = new JSONArray(data);


                        googleMap.clear();


                        //offer Market Real


                        for (int i = 0; i < jsonArray.length(); i++) {
                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(jsonArray.getString(i));

                            Gson gson = new Gson();

                            RegionModules bankModules = gson.fromJson(mJson, RegionModules.class);


                            city_location.add(bankModules);

                            LatLng sydneya = new LatLng(bankModules.getCenter().getCoordinates().get(1), bankModules.getCenter().getCoordinates().get(0));


                            googleMap.addMarker(new MarkerOptions()
                                    .position(sydneya)

                                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView2_city(bankModules.getName() + "", i + "")))).setTag("allArea/" + i);


                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    WebService.loading(getActivity(), true);

                    init_volley();
                    VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
                    mVolleyService.getDataVolley("regions", WebService.regions);//&request_type=pay

                }


//                city_location.add(new CityLocation(1, "الرياض", "24.774265", "46.738586"));
//                city_location.add(new CityLocation(2, "جدّة", "21.54472", "39.17611"));
//                city_location.add(new CityLocation(3, "الدمام", "26.39222", "49.97778"));
//                city_location.add(new CityLocation(4, "مكة", "21.42250", "39.82611"));
//                city_location.add(new CityLocation(5, "نجران", "17.49250", "44.13472"));
//                city_location.add(new CityLocation(6, "المدينة", "24.46722", "39.61111"));
//                city_location.add(new CityLocation(7, "تبوك", "28.38417", "36.58000"));
//                city_location.add(new CityLocation(8, "حائل", "27.52444", "41.70389"));
//                city_location.add(new CityLocation(9, "عرعر", "30.98333", "41.01667"));
//                city_location.add(new CityLocation(10, "جازان", "16.89472", "42.55778"));
//                city_location.add(new CityLocation(11, "الباحة", "20.01250", "41.46000"));
//                city_location.add(new CityLocation(12, "القصيم", "26.333333", "43.966667"));
//                city_location.add(new CityLocation(13, "عسير", "18.5473952", "42.0534398"));
//                city_location.add(new CityLocation(14, "الجوف", "29.97111", "40.20028"));
//                city_location.add(new CityLocation(15, "الأحساء", "25.383333", "49.583333"));
//


                cityMap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        googleMap.clear();

                        //get latlong for corners for specified place
                        LatLng one = new LatLng(30.250032, 38.374554);
                        LatLng two = new LatLng(19.117340, 49.913804);
//                LatLng three = new LatLng(25.784818, 49.666975);
//                LatLng forth = new LatLng(23.031780, 39.361870);

                        LatLngBounds.Builder builder = new LatLngBounds.Builder();

                        //add them to builder
                        builder.include(one);
                        builder.include(two);
//                builder.include(three);
//                builder.include(forth);

                        LatLngBounds bounds = builder.build();

                        //get width and height to current display screen
                        int width = getResources().getDisplayMetrics().widthPixels;
                        int height = getResources().getDisplayMetrics().heightPixels;

                        // 20% padding
                        int padding = (int) (width * 0.05);

                        //set latlong bounds
                        mMap.setLatLngBoundsForCameraTarget(bounds);

                        //move camera to fill the bound to screen
                        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));

                        //set zoom to level to current so that you won't be able to zoom out viz. move outside bounds
                        mMap.setMinZoomPreference(mMap.getCameraPosition().zoom);


//
//
                        for (int i = 0; i < city_location.size(); i++) {

                            LatLng sydneya = new LatLng(Double.valueOf(city_location.get(i).getCenter().getCoordinates().get(1)), Double.valueOf(city_location.get(i).getCenter().getCoordinates().get(0)));
                            googleMap.addMarker(new MarkerOptions()
                                    .position(sydneya)

                                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView2_city(city_location.get(i).getName() + "", i + "")))).setTag("allArea/" + i);


                        }
                    }
                });

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {


                        if (marker.getTag().toString().equals("mylocation")) {
                        } else if (marker.getTag().toString().contains("allcity")) {


                            System.out.println("^^^^^^^^^^^^^^^^^^^");

                            try {

                                String[] separated = marker.getTag().toString().split("/");

                                String number = separated[1]; // this will contain " they taste good"


//                                LatLng my_location = new LatLng(Double.valueOf(city_location.get(Integer.valueOf(number)).getCenter().getCoordinates().get(1)), Double.valueOf(city_location.get(Integer.valueOf(number)).getCenter().getCoordinates().get(0)));

                                if (type_selected.equals("Real")) {


                                    Intent intent = new Intent(getContext(), OrderListActivity.class);
                                    getActivity().startActivity(intent);


                                } else if (type_selected.equals("Market")) {

                                    Intent intent = new Intent(getContext(), OrderListActivity.class);
                                    getActivity().startActivity(intent);


                                } else if (type_selected.equals("offer")) {

                                    get_data_from_api("map_offer", filtter_selected, list_city.get(Integer.valueOf(number)).getLat() + "", list_city.get(Integer.valueOf(number)).getLan() + "");

                                }


                            } catch (Exception e) {

                            }

                        } else if (marker.getTag().toString().contains("allArea")) {

                            System.out.println("^^^^^^^^^^^^^^^^^^^");

                            try {

                                String[] separated = marker.getTag().toString().split("/");

                                String number = separated[1]; // this will contain " they taste good"


                                LatLng my_location = new LatLng(Double.valueOf(city_location.get(Integer.valueOf(number)).getCenter().getCoordinates().get(1)), Double.valueOf(city_location.get(Integer.valueOf(number)).getCenter().getCoordinates().get(0)));


                                CameraPosition cameraPosition = new CameraPosition.Builder().target(my_location).zoom(4).build();
                                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my_location, 10));
                                // Zoom in, animating the camera.
                                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                                 Zoom out to zoom level 10, animating with a duration of 2 seconds.
                                googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 3000, null);

                                get_data_from_api("citeis", filtter_selected, city_location.get(Integer.valueOf(number)).getId() + "", "");

                            } catch (Exception e) {

                            }


                        } else {
                            if (final_type_requst_filter.equals("list_order")) {

                            } else if (final_type_requst_filter.equals("list_offer")) {//aqarz

                            } else if (final_type_requst_filter.equals("map_order")) {
//

                                if (marker.getTag().toString().equals("mylocation")) {

                                } else {


                                    CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(getActivity(), homeModules.get(Integer.valueOf(marker.getTag().toString())));
                                    mMap.setInfoWindowAdapter(customInfoWindow);
                                    mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                        @Override
                                        public void onInfoWindowClick(Marker marker) {


                                            try {
                                                if (!marker.getTag().toString().equals("mylocation")) {
                                                    Intent intent = new Intent(getContext(), DetailsActivity.class);
                                                    intent.putExtra("id", homeModules.get(Integer.valueOf(marker.getTag().toString())).getId() + "");
                                                    System.out.println(homeModules.get(Integer.valueOf(marker.getTag().toString())).getId() + "");
                                                    getActivity().startActivity(intent);
                                                }
                                            } catch (Exception e) {

                                            }


                                        }
                                    });

//                                    BottomSheetDialogFragment_DetailsAqares_orders bottomSheetDialogFragment_detailsAqares_orders = new BottomSheetDialogFragment_DetailsAqares_orders(homeModules.get(Integer.valueOf(marker.getTag().toString())));
//                                    bottomSheetDialogFragment_detailsAqares_orders.show(getFragmentManager(), "");
                                }
                            } else if (final_type_requst_filter.equals("map_offer")) {//aqarz

                                if (marker.getTag().toString().equals("mylocation")) {

                                } else {
//                                    BottomSheetDialogFragment_DetailsAqares bottomSheetDialogFragment_detailsAqares = new BottomSheetDialogFragment_DetailsAqares(homeModules_aqares.get(Integer.valueOf(marker.getTag().toString())));
//                                    bottomSheetDialogFragment_detailsAqares.show(getFragmentManager(), "");
                                    CustomInfoWindowGoogleMaptyp_2 customInfoWindow = new CustomInfoWindowGoogleMaptyp_2(getActivity(), homeModules_aqares.get(Integer.valueOf(marker.getTag().toString())));
                                    mMap.setInfoWindowAdapter(customInfoWindow);
                                    mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                        @Override
                                        public void onInfoWindowClick(Marker marker) {
                                            if (!marker.getTag().toString().equals("mylocation")) {

                                                Intent intent = new Intent(getContext(), DetailsActivity_aqarz.class);
                                                intent.putExtra("id", homeModules_aqares.get(Integer.valueOf(marker.getTag().toString())).getId() + "");
                                                getActivity().startActivity(intent);

                                            }
                                        }
                                    });


                                }
                            }


                        }
                        return false;
                    }
                });

                try {
                    if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);

//                        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                        requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                        LatLng mylocation = getLocation_sau();
                        if (mylocation != null) {
//                            googleMap.addMarker(new MarkerOptions()
//                                    .position(mylocation)).setTag("mylocation");
//
//                            ;
                            CameraPosition cameraPosition = new CameraPosition.Builder().target(mylocation).zoom(4).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


//                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 40));
//                            // Zoom in, animating the camera.
//                            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                            // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//                            googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);
                        }
                    } else {


                        LatLng mylocation = getLocation();
                        if (mylocation != null) {
                            googleMap.addMarker(new MarkerOptions()
                                    .position(mylocation)).setTag("mylocation");

                            //                            CameraPosition cameraPosition = new CameraPosition.Builder().target(mylocation).zoom(4).build();
//                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


//                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 40));
//                            // Zoom in, animating the camera.
//                            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                            // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//                            googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);
                        }


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


        return v;
    }


    public static MapsFragment newInstance(String text) {

        MapsFragment f = new MapsFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }


    public void getProfile() {

        try {

            if (Settings.checkLogin()) {
                if (Settings.GetUser().getLogo() == null) {

                } else {
                    Glide.with(getActivity()).load(Settings.GetUser().getLogo()).error(R.drawable.ic_user_un).into(image_profile);

                }

                image_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), MyProfileInformationActivity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);
//                        getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                    }
                });
            } else {
                image_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), LoginActivity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);
                    }
                });
            }

        } catch (Exception e) {

        }
    }

    public void getProfiles() {

        try {

            if (Settings.checkLogin()) {
                if (Settings.GetUser().getLogo() == null) {

                } else {
                    Glide.with(getActivity()).load(Settings.GetUser().getLogo()).error(R.drawable.ic_user_un).into(image_profile);

                }

                image_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), MyProfileInformationActivity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);
//                        getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                    }
                });
            } else {
                image_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), LoginActivity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);
                    }
                });
            }

        } catch (Exception e) {

        }
    }

    public void init(View v) {
        type = v.findViewById(R.id.opration);
        selsct_type_all = v.findViewById(R.id.selsct_type_all);
        filtter = v.findViewById(R.id.filtter);
        search_text = v.findViewById(R.id.search_text);
        search_btn = v.findViewById(R.id.search_btn);

        addAqares = v.findViewById(R.id.addAqares);
        RequstAqars = v.findViewById(R.id.RequstAqars);
        image_profile = v.findViewById(R.id.image_profile);
        layout_list = v.findViewById(R.id.layout_list);
        layout_button = v.findViewById(R.id.layout_button);
        layout_button_2 = v.findViewById(R.id.layout_button_2);
        convert_map_to_list = v.findViewById(R.id.convert_map_to_list);
        change_list_to_map = v.findViewById(R.id.change_list_to_map);
        list_aqaers = v.findViewById(R.id.list_aqaers);
        chmnage_map_style = v.findViewById(R.id.chmnage_map_style);
        get_location = v.findViewById(R.id.get_location);
        nodata_vis = v.findViewById(R.id.nodata_vis);
        laout_of_change = v.findViewById(R.id.laout_of_change);

        LinearLayoutManager layoutManagexx
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_aqaers.setLayoutManager(layoutManagexx);


//---------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------


        RealStatr_order.setBackground(getActivity().getResources().getDrawable(R.drawable.button_1));
        MarketOrder.setBackground(null);
        OfferOrder.setBackground(null);


        RealStatr_order.setTextColor(getActivity().getResources().getColor(R.color.white));
        MarketOrder.setTextColor(getActivity().getResources().getColor(R.color.textColor));
        OfferOrder.setTextColor(getActivity().getResources().getColor(R.color.textColor));
        type_selected = "Real";

        RealStatr_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RealStatr_order.setBackground(getActivity().getResources().getDrawable(R.drawable.button_1));
                MarketOrder.setBackground(null);
                OfferOrder.setBackground(null);


                RealStatr_order.setTextColor(getActivity().getResources().getColor(R.color.white));
                MarketOrder.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                OfferOrder.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                type_selected = "Real";
                try {


                    googleMap.clear();
                    //get latlong for corners for specified place
                    LatLng one = new LatLng(30.250032, 38.374554);
                    LatLng two = new LatLng(19.117340, 49.913804);
//                LatLng three = new LatLng(25.784818, 49.666975);
//                LatLng forth = new LatLng(23.031780, 39.361870);

                    LatLngBounds.Builder builder = new LatLngBounds.Builder();

                    //add them to builder
                    builder.include(one);
                    builder.include(two);
//                builder.include(three);
//                builder.include(forth);

                    LatLngBounds bounds = builder.build();

                    //get width and height to current display screen
                    int width = getResources().getDisplayMetrics().widthPixels;
                    int height = getResources().getDisplayMetrics().heightPixels;

                    // 20% padding
                    int padding = (int) (width * 0.05);

                    //set latlong bounds
                    googleMap.setLatLngBoundsForCameraTarget(bounds);

                    //move camera to fill the bound to screen
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));

                    //set zoom to level to current so that you won't be able to zoom out viz. move outside bounds
                    googleMap.setMinZoomPreference(googleMap.getCameraPosition().zoom);


                    //offer Market Real


                    for (int i = 0; i < city_location.size(); i++) {


                        LatLng sydneya = new LatLng(city_location.get(i).getCenter().getCoordinates().get(1), city_location.get(i).getCenter().getCoordinates().get(0));


                        googleMap.addMarker(new MarkerOptions()
                                .position(sydneya)

                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView2_city(city_location.get(i).getName() + "", i + "")))).setTag("allArea/" + i);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

        MarketOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MarketOrder.setBackground(getActivity().getResources().getDrawable(R.drawable.button_2));
                RealStatr_order.setBackground(null);
                OfferOrder.setBackground(null);


                MarketOrder.setTextColor(getActivity().getResources().getColor(R.color.white));
                RealStatr_order.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                OfferOrder.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                type_selected = "Market";
                try {


                    googleMap.clear();

                    //get latlong for corners for specified place
                    LatLng one = new LatLng(30.250032, 38.374554);
                    LatLng two = new LatLng(19.117340, 49.913804);
//                LatLng three = new LatLng(25.784818, 49.666975);
//                LatLng forth = new LatLng(23.031780, 39.361870);

                    LatLngBounds.Builder builder = new LatLngBounds.Builder();

                    //add them to builder
                    builder.include(one);
                    builder.include(two);
//                builder.include(three);
//                builder.include(forth);

                    LatLngBounds bounds = builder.build();

                    //get width and height to current display screen
                    int width = getResources().getDisplayMetrics().widthPixels;
                    int height = getResources().getDisplayMetrics().heightPixels;

                    // 20% padding
                    int padding = (int) (width * 0.05);

                    //set latlong bounds
                    googleMap.setLatLngBoundsForCameraTarget(bounds);

                    //move camera to fill the bound to screen
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));

                    //set zoom to level to current so that you won't be able to zoom out viz. move outside bounds
                    googleMap.setMinZoomPreference(googleMap.getCameraPosition().zoom);


                    //offer Market Real


                    for (int i = 0; i < city_location.size(); i++) {


                        LatLng sydneya = new LatLng(city_location.get(i).getCenter().getCoordinates().get(1), city_location.get(i).getCenter().getCoordinates().get(0));


                        googleMap.addMarker(new MarkerOptions()
                                .position(sydneya)

                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView2_city(city_location.get(i).getName() + "", i + "")))).setTag("allArea/" + i);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        OfferOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OfferOrder.setBackground(getActivity().getResources().getDrawable(R.drawable.button_3));
                MarketOrder.setBackground(null);
                RealStatr_order.setBackground(null);


                OfferOrder.setTextColor(getActivity().getResources().getColor(R.color.white));
                MarketOrder.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                RealStatr_order.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                type_selected = "offer";
                try {


                    googleMap.clear();
                    //get latlong for corners for specified place
                    LatLng one = new LatLng(30.250032, 38.374554);
                    LatLng two = new LatLng(19.117340, 49.913804);
//                LatLng three = new LatLng(25.784818, 49.666975);
//                LatLng forth = new LatLng(23.031780, 39.361870);

                    LatLngBounds.Builder builder = new LatLngBounds.Builder();

                    //add them to builder
                    builder.include(one);
                    builder.include(two);
//                builder.include(three);
//                builder.include(forth);

                    LatLngBounds bounds = builder.build();

                    //get width and height to current display screen
                    int width = getResources().getDisplayMetrics().widthPixels;
                    int height = getResources().getDisplayMetrics().heightPixels;

                    // 20% padding
                    int padding = (int) (width * 0.05);

                    //set latlong bounds
                    googleMap.setLatLngBoundsForCameraTarget(bounds);

                    //move camera to fill the bound to screen
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));

                    //set zoom to level to current so that you won't be able to zoom out viz. move outside bounds
                    googleMap.setMinZoomPreference(googleMap.getCameraPosition().zoom);


                    //offer Market Real


                    for (int i = 0; i < city_location.size(); i++) {

                        LatLng sydneya = new LatLng(city_location.get(i).getCenter().getCoordinates().get(1), city_location.get(i).getCenter().getCoordinates().get(0));


                        googleMap.addMarker(new MarkerOptions()
                                .position(sydneya)

                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView2_city(city_location.get(i).getName() + "", i + "")))).setTag("allArea/" + i);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


//---------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------

        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        type.setLayoutManager(layoutManager1);
        RecyclerView_All_type_in_fragment1 recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_fragment1(getContext(), type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_fragment1.ItemClickListener() {
            @Override
            public void onItemClick(List<TypeModules> typeModules) {

                opration_select = "";
                for (int i = 0; i < typeModules.size(); i++) {
                    if (typeModules.get(i).isIsselected()) {
                        if (opration_select.equals("")) {
                            opration_select = typeModules.get(i).getId().toString() + "";

                        } else {
                            opration_select = opration_select + "," + typeModules.get(i).getId().toString() + "";

                        }

                    }
                }


                System.out.println("opration_select" + opration_select);

//                opration_select = type_list.get(position).getId().toString() + "";


//                get_data_from_api(final_type_requst_filter, filtter_selected);


            }
        });
        type.setAdapter(recyclerView_all_type_in_fragment);


//        type.scrollToPosition(0);

//---------------------------------------------------------------------------------------
//        oprationModules_list.add(new select_typeModules(1, getContext().getResources().getString(R.string.All)));
//        oprationModules_list.add(new select_typeModules(1, getContext().getResources().getString(R.string.Pay)));
//        oprationModules_list.add(new select_typeModules(1, getContext().getResources().getString(R.string.Rent)));
//        oprationModules_list.add(new select_typeModules(1, getContext().getResources().getString(R.string.Investment)));
//        LinearLayoutManager layoutManagerm
//                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        selsct_type_all.setLayoutManager(layoutManagerm);

//        RecyclerView_All_select_oprat_in_fragment recyclerView_all_select_oprat_in_fragment = new RecyclerView_All_select_oprat_in_fragment(getContext(), oprationModules_list);
//        recyclerView_all_select_oprat_in_fragment.addItemClickListener(new RecyclerView_All_select_oprat_in_fragment.ItemClickListener() {
//            @Override
//            public void onItemClick(List<select_typeModules> select_typeModules) {
//
//                oprationModules_list = select_typeModules;
//
//
//            }
//        });
//        selsct_type_all.setAdapter(recyclerView_all_select_oprat_in_fragment);


        ActionButton();
        action_btn();

//
//        if (Settings.CheckIsAccountAqarzMan()) {
//            laout_of_change.setVisibility(View.VISIBLE);
////            get_data_from_api("map_order", filtter_selected);
//            get_data_from_api("map_offer", filtter_selected);
//
//        } else {
//            laout_of_change.setVisibility(View.GONE);
//            get_data_from_api("map_offer", filtter_selected);
//
//        }


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                if (!Hawk.contains("showCaseView")) {

                    Hawk.put("showCaseView", "showCaseView");


                    materialShowcaseView = new MaterialShowcaseView.Builder(getActivity())
                            .setTitleText(getResources().getString(R.string.title_orfdder))
                            .setContentText(getResources().getString(R.string.deta_order))
                            .setContentTextColor(getResources().getColor(R.color.white))

                            .setDismissText(getResources().getString(R.string.Nextt)) // Optional. When used can only dismiss the showcase by clicking on the dismiss button and target isn't pressable.
                            .setTarget(MainActivity.lay_2)


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
                                    .setTitleText(getResources().getString(R.string.AddAqarezTitle_show))
                                    .setContentText(getResources().getString(R.string.AddAqarezdes_show))
                                    .setDismissText(getResources().getString(R.string.Nextt)) // Optional. When used can only dismiss the showcase by clicking on the dismiss button and target isn't pressable.
                                    .setTarget(addAqares)
                                    .setContentTextColor(getResources().getColor(R.color.white))

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
                                            .setTitleText(getResources().getString(R.string.RequestServicezTitle_show))
                                            .setContentText(getResources().getString(R.string.RequestServicezdes_show))
                                            .setDismissText(getResources().getString(R.string.Nextt)) // Optional. When used can only dismiss the showcase by clicking on the dismiss button and target isn't pressable.
                                            .setTarget(RequstAqars)
                                            .setContentTextColor(getResources().getColor(R.color.white))

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
                                                    .setTitleText(getResources().getString(R.string.filtterSearchzTitle_show))
                                                    .setContentText(getResources().getString(R.string.filtterSearchzdes_show))
                                                    .setDismissText(getResources().getString(R.string.Nextt)) // Optional. When used can only dismiss the showcase by clicking on the dismiss button and target isn't pressable.
                                                    .setTarget(filtter)
                                                    .setContentTextColor(getResources().getColor(R.color.white))

//                            .setBackgroundColor(getResources().getColor(R.color.color_brimarys))
                                                    .setDelay(300) // Optional. But starting animations immediately in onCreate can make the choppy
                                                    .show();

//
//
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
//
//
//                    materialShowcaseView4.addListener(new ShowcaseListener() {
//                        @Override
//                        public void onShowcaseDisplayed(MaterialShowcaseView materialShowcaseView) {
//
//                        }
//
//                        @Override
//                        public void onShowcaseDismissed(MaterialShowcaseView materialShowcaseView) {
//
//                        }
//
//                        @Override
//                        public void onShowcaseSkipped(MaterialShowcaseView materialShowcaseView) {
//
//                        }
//
//                        @Override
//                        public void onTargetPressed(MaterialShowcaseView materialShowcaseView) {
//
//                        }
//                    });

//                    showCaseView = new ShowcaseView.Builder(getActivity())
//                            .setTarget(new ViewTarget(R.id.lay_2, getActivity()))
//
//                            .setContentTitle(getResources().getString(R.string.title_orfdder))
//                            .setContentText(getResources().getString(R.string.deta_order))
//
//                            .setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    showCaseView.hide();
//
//                                    showCaseView = new ShowcaseView.Builder(getActivity())
//                                            .setTarget(new ViewTarget(R.id.addAqares, getActivity()))
//                                            .setContentTitle(getResources().getString(R.string.AddAqarezTitle_show))
//                                            .setContentText(getResources().getString(R.string.AddAqarezdes_show))
//
//                                            .setOnClickListener(new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View v) {
//                                                    showCaseView.hide();
//
//                                                    showCaseView1 = new ShowcaseView.Builder(getActivity())
//                                                            .setTarget(new ViewTarget(R.id.RequstAqars, getActivity()))
//                                                            .setContentTitle(getResources().getString(R.string.RequestServicezTitle_show))
//                                                            .setContentText(getResources().getString(R.string.RequestServicezdes_show))
//
//                                                            .setOnClickListener(new View.OnClickListener() {
//                                                                @Override
//                                                                public void onClick(View v) {
//                                                                    showCaseView1.hide();
//
//
//                                                                    showCaseView2 = new ShowcaseView.Builder(getActivity())
//                                                                            .setTarget(new ViewTarget(R.id.filtter, getActivity()))
//                                                                            .setContentTitle(getResources().getString(R.string.filtterSearchzTitle_show))
//                                                                            .setContentText(getResources().getString(R.string.filtterSearchzdes_show))
//
//
//                                                                            .hideOnTouchOutside()
//
//                                                                            .setStyle(R.style.CustomShowcaseTheme2)
//                                                                            .build();
//                                                                }
//                                                            })
//
//                                                            .setStyle(R.style.CustomShowcaseTheme2)
//                                                            .build();
//                                                }
//                                            })
//
//
//                                            .setStyle(R.style.CustomShowcaseTheme2)
//                                            .build();
//
//                                }
//                            })
//
//
//                            .setStyle(R.style.CustomShowcaseTheme2)
//                            .build();


                }


            }
        }, 100); // After 1 seconds
    }

    public void ActionButton() {

//----------------------------------------------------------------------Rec
        notfication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Settings.checkLogin()) {
                    new AlertDialog.Builder(getContext())
                            .setMessage(getActivity().getResources().getString(R.string.you_are_not_login_please_login))
                            .setCancelable(false)
                            .setPositiveButton(getActivity().getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(getContext(), LoginActivity.class);
//                                intent.putExtra("from", "splash");
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton(getActivity().getResources().getString(R.string.no), null)
                            .show();
                } else {

                    if (Settings.CheckIsCompleate()) {

                        Intent intent = new Intent(getContext(), NotficationActvity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);
                    } else {
                        Settings.Dialog_not_compleate(getActivity());
                    }

                }
            }
        });
        chate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Settings.checkLogin()) {
                    new AlertDialog.Builder(getContext())
                            .setMessage(getActivity().getResources().getString(R.string.you_are_not_login_please_login))
                            .setCancelable(false)
                            .setPositiveButton(getActivity().getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(getContext(), LoginActivity.class);
//                                intent.putExtra("from", "splash");
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton(getActivity().getResources().getString(R.string.no), null)
                            .show();
                } else {

                    if (Settings.CheckIsCompleate()) {

                        Intent intent = new Intent(getContext(), ChateActivity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);
                    } else {
                        Settings.Dialog_not_compleate(getActivity());
                    }

                }
            }
        });
        RequstAqars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Settings.checkLogin()) {
                    new AlertDialog.Builder(getContext())
                            .setMessage(getActivity().getResources().getString(R.string.you_are_not_login_please_login))
                            .setCancelable(false)
                            .setPositiveButton(getActivity().getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(getContext(), LoginActivity.class);
//                                intent.putExtra("from", "splash");
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton(getActivity().getResources().getString(R.string.no), null)
                            .show();
                } else {

                    if (Settings.CheckIsCompleate()) {

                        Intent intent = new Intent(getContext(), RequestServiceActivity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);
                    } else {
                        Settings.Dialog_not_compleate(getActivity());
                    }

                }


//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        addAqares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Settings.checkLogin()) {

                    new AlertDialog.Builder(getContext())
                            .setMessage(getActivity().getResources().getString(R.string.you_are_not_login_please_login))
                            .setCancelable(false)
                            .setPositiveButton(getActivity().getResources().getString(R.string.Go_to_login), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(getContext(), LoginActivity.class);
//                                intent.putExtra("from", "splash");
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton(getActivity().getResources().getString(R.string.no), null)
                            .show();
                } else {

                    if (Settings.CheckIsCompleate()) {
                        Intent intent = new Intent(getContext(), AddAqarsActivity.class);
//              intent.putExtra("from", "splash");
                        startActivity(intent);
                    } else {
                        Settings.Dialog_not_compleate(getActivity());
                    }


                }

//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);


            }

        });

        //------------------------------------------------------------------------------------------------------
        convert_map_to_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                layout_button.setVisibility(View.GONE);
//                layout_button_2.setVisibility(View.GONE);
                layout_list.setVisibility(View.VISIBLE);
                change_list_to_map.setVisibility(View.VISIBLE);
                convert_map_to_list.setVisibility(View.GONE);

                convert_type = "list";

                if (Settings.CheckIsAccountAqarzMan()) {
                    if (typeTab.equals("Orders_tab")) {
                        get_data_from_api("list_order", filtter_selected, x_latitude, x_longitude);
//                        get_data_from_api("list_offer", filtter_selected);

                    } else {
//                        get_data_from_api("list_offer", filtter_selected);
                        get_data_from_api("list_offer", filtter_selected, x_latitude, x_longitude);

                    }
                } else {
//                    get_data_from_api("list_offer", filtter_selected);
                    get_data_from_api("list_offer", filtter_selected, x_latitude, x_longitude);

                }


            }
        });
        change_list_to_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list_aqaers.setAdapter(null);

                layout_button.setVisibility(View.VISIBLE);
//                layout_button_2.setVisibility(View.VISIBLE);
                layout_list.setVisibility(View.GONE);
                change_list_to_map.setVisibility(View.GONE);
                convert_map_to_list.setVisibility(View.VISIBLE);

                if (typeTab.equals("Orders_tab")) {
//                    get_data_from_api("map_order", filtter_selected);

                } else {
//                    get_data_from_api("map_offer", filtter_selected);

                }

                convert_type = "map";

            }
        });

        chmnage_map_style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (style.equals("MAP_TYPE_NORMAL")) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    style = "MAP_TYPE_SATELLITE";

                } else {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    style = "MAP_TYPE_NORMAL";

                }


            }
        });


        get_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                    } else {


                        LatLng mylocation = getLocation();
                        if (mylocation != null) {
                            googleMap.addMarker(new MarkerOptions()
                                    .position(mylocation)).setTag("mylocation");

                            //                            CameraPosition cameraPosition = new CameraPosition.Builder().target(mylocation).zoom(11).build();
//                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

//                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 20));
//                            // Zoom in, animating the camera.
//                            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                            // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//                            googleMap.animateCamera(CameraUpdateFactory.zoomTo(17), 3000, null);
                        }


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        filtter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialogFragment_Filtter bottomSheetDialogFragment_filtter = new BottomSheetDialogFragment_Filtter("");

                bottomSheetDialogFragment_filtter.addItemClickListener(new BottomSheetDialogFragment_Filtter.ItemClickListener() {
                    @Override
                    public void onItemClick(String filtter) {


                        filtter_selected = filtter;
//                        get_data_from_api(final_type_requst_filter, filtter_selected);

                        bottomSheetDialogFragment_filtter.dismiss();


                    }
                });
                bottomSheetDialogFragment_filtter.show(getFragmentManager(), "");


            }
        });

    }

    public void action_btn() {
        Orders_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Orders_tab.setBackground(getResources().getDrawable(R.drawable.button_login));

                Orders_tab.setTextColor(getResources().getColor(R.color.white));
                Offers_tab.setBackground(null);
                Offers_tab.setTextColor(getResources().getColor(R.color.color_filter));

                if (convert_type.equals("map")) {
//                    get_data_from_api("map_order", filtter_selected);

                } else {
//                    get_data_from_api("list_order", filtter_selected);
//                    get_data_from_api("list_offer", filtter_selected);

                }

                typeTab = "Orders_tab";
            }
        });


        Offers_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Offers_tab.setBackground(getResources().getDrawable(R.drawable.button_login));
                Offers_tab.setTextColor(getResources().getColor(R.color.white));
                Orders_tab.setBackground(null);
                Orders_tab.setTextColor(getResources().getColor(R.color.color_filter));

                if (convert_type.equals("map")) {
//                    get_data_from_api("map_offer", filtter_selected);

                } else {
//                    get_data_from_api("list_offer", filtter_selected);

                }

                typeTab = "Offers_tab";


            }
        });
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                get_data_from_api(final_type_requst_filter, filtter_selected);
            }
        });

        search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //                    get_data_from_api(final_type_requst_filter, filtter_selected);
                return actionId == EditorInfo.IME_ACTION_SEARCH;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
//        if (mGoogleApiClient != null) {
//            mGoogleApiClient.connect();
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


    private BitmapDescriptor bitmapDescriptorFromVector(@DrawableRes int id) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 121:


            }
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


                        if (requestType.equals("list_order")) {

                            String data = response.getString("data");

                            JSONObject jsonObject_data = new JSONObject(data);

                            String data_inside = jsonObject_data.getString("data");
                            JSONArray jsonArray = new JSONArray(data_inside);
                            list_aqaers.setAdapter(null);
                            homeModules.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();

                                HomeModules bankModules = gson.fromJson(mJson, HomeModules.class);
                                homeModules.add(bankModules);


                            }
                            if (homeModules.size() == 0) {

                                nodata_vis.setVisibility(View.VISIBLE);
                            } else {
                                nodata_vis.setVisibility(View.GONE);

                            }
                            list_aqaers.setAdapter(new RecyclerView_HomeList(getContext(), homeModules));


                        } else if (requestType.equals("list_offer")) {//aqarz
                            String data = response.getString("data");

                            JSONObject jsonObject_data = new JSONObject(data);

                            String data_inside = jsonObject_data.getString("data");
                            JSONArray jsonArray = new JSONArray(data_inside);

                            homeModules_aqares.clear();
                            list_aqaers.setAdapter(null);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    HomeModules_aqares bankModules = gson.fromJson(mJson, HomeModules_aqares.class);
                                    homeModules_aqares.add(bankModules);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            if (homeModules_aqares.size() == 0) {

                                nodata_vis.setVisibility(View.VISIBLE);
                            } else {
                                nodata_vis.setVisibility(View.GONE);

                            }

                            list_aqaers.setAdapter(new RecyclerView_HomeList_estat(getContext(), homeModules_aqares));


                        } else if (requestType.equals("map_order")) {


                            String data = response.getString("data");

//                            JSONObject jsonObject_data = new JSONObject(data);

//                            String data_inside = jsonObject_data.getString("data");
                            JSONArray jsonArray = new JSONArray(data);
                            googleMap.clear();

                            homeModules.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();

                                HomeModules bankModules = gson.fromJson(mJson, HomeModules.class);
                                homeModules.add(bankModules);

//                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(camPos));

                                String price = bankModules.getPrice_from();
                                int price_int = Integer.valueOf(price);

                                int prices = price_int;


                                if (price_int < 1000) {

                                    price = prices + "";
                                } else if (price_int > 1000 && price_int < 999999) {
                                    prices = price_int / 1000;

                                    price = prices + getResources().getString(R.string.K);

                                } else if (price_int > 999999) {
                                    prices = price_int / 1000000;

                                    price = prices + getResources().getString(R.string.Million);

                                }
                                System.out.println(bankModules.getPrice_from() + "--------------" + price);


                                LatLng sydneya = new LatLng(Double.valueOf(bankModules.getLat()), Double.valueOf(bankModules.getLan()));
                                googleMap.addMarker(new MarkerOptions()
                                        .position(sydneya)

                                        .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView2(price)))).setTag(i);


                            }
                            LatLng mylocation = getLocation();
                            if (mylocation != null) {
                                googleMap.addMarker(new MarkerOptions()
                                        .position(mylocation)).setTag("mylocation");

                                //                                LatLng gaza = new LatLng(Double.valueOf("31.484194"), Double.valueOf("34.408283"));
//                                googleMap.addMarker(new MarkerOptions()
//                                        .position(gaza)
//                                        .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.ic_marker_location))));


//                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydneya, 13));
//                        // Zoom in, animating the camera.
//                        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);
//
////
                            }


                        } else if (requestType.equals("regions")) {


                            MainActivity.first_time_open_app = false;


                            String data = response.getString("data");
                            JSONArray jsonArray = new JSONArray(data);
//                            System.out.println("datadata" + data);

                            Hawk.put("FF", data);

                            city_location.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();

                                RegionModules bankModules = gson.fromJson(mJson, RegionModules.class);

                                city_location.add(bankModules);
                                LatLng sydneya = new LatLng(bankModules.getCenter().getCoordinates().get(1), bankModules.getCenter().getCoordinates().get(0));
                                googleMap.addMarker(new MarkerOptions()
                                        .position(sydneya)

                                        .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView2_city(bankModules.getName() + "", i + "")))).setTag("allArea/" + i);


                            }

                        } else if (requestType.equals("citeis")) {//aqarz


                            String data = response.getString("data");
                            JSONArray jsonArray = new JSONArray(data);
                            list_city.clear();
                            googleMap.clear();


                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    CityLocation cityLocation = gson.fromJson(mJson, CityLocation.class);
//                                    homeModules_aqares.add(bankModules);

                                    list_city.add(cityLocation);

                                    LatLng sydneya = new LatLng(Double.valueOf(cityLocation.getLat() + ""), Double.valueOf(cityLocation.getLan() + ""));


                                    googleMap.addMarker(new MarkerOptions()
                                            .position(sydneya)

                                            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView3_city(cityLocation.getName() + "", "")))).setTag("allcity/" + i);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }


                        } else if (requestType.equals("map_offer")) {//aqarz
                            try {


                                String data = response.getString("data");

//                                JSONObject jsonObject_data = new JSONObject(data);
//
//                                String data_inside = jsonObject_data.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                googleMap.clear();
                                homeModules_aqares.clear();

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    try {

                                        JsonParser parser = new JsonParser();
                                        JsonElement mJson = parser.parse(jsonArray.getString(i));

                                        Gson gson = new Gson();

                                        HomeModules_aqares bankModules = gson.fromJson(mJson, HomeModules_aqares.class);
                                        homeModules_aqares.add(bankModules);


//                                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(camPos));


                                        String price = bankModules.getTotalPrice();

                                        String v = price.replace(",", "");


                                        int price_int = Integer.valueOf(v);

                                        int prices = price_int;


                                        if (price_int < 1000) {

                                            price = prices + "";

                                        } else if (price_int > 1000 && price_int < 999999) {
                                            prices = price_int / 1000;

                                            price = prices + getResources().getString(R.string.K);

                                        } else if (price_int > 999999) {


                                            prices = price_int / 100000;


                                            double XXXX = (double) prices / 10;
                                            System.out.println("price_int" + XXXX);

                                            price = XXXX + getResources().getString(R.string.Million);

                                        }
                                        System.out.println(bankModules.getTotalPrice() + "--------------" + price);

                                        LatLng sydneya = new LatLng(Double.valueOf(bankModules.getLat()), Double.valueOf(bankModules.getLan()));
                                        googleMap.addMarker(new MarkerOptions()
                                                .position(sydneya)
                                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(price)))).setTag(i);//تم التغير الشكل من السكني للكلر


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                }
                                LatLng mylocation = getLocation();
                                if (mylocation != null) {
                                    googleMap.addMarker(new MarkerOptions()
                                            .position(mylocation)).setTag("mylocation");


//                                LatLng gaza = new LatLng(Double.valueOf("31.484194"), Double.valueOf("34.408283"));
//                                googleMap.addMarker(new MarkerOptions()
//                                        .position(gaza)
//                                        .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.ic_marker_location))));
//                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydneya, 13));
//                        // Zoom in, animating the camera.
//                        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);
//
////
                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }


//                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydneya, 13));
//                        // Zoom in, animating the camera.
//                        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);
//
//


                    } else {

                    }


                } catch (Exception e) {

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


                    WebService.Make_Toast_color(getActivity(), message, "error");

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

    private Bitmap getMarkerBitmapFromView(String Price) {

        View customMarkerView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom, null);
        TextView markerImageView = customMarkerView.findViewById(R.id.numb);
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

    private Bitmap getMarkerBitmapFromView2(String Price) {

        View customMarkerView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom2, null);
        TextView markerImageView = customMarkerView.findViewById(R.id.numb);
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

    private Bitmap getMarkerBitmapFromView2_city(String Price, String numbers) {

        View customMarkerView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom3, null);
        TextView markerImageView = customMarkerView.findViewById(R.id.numb);
        TextView number_ = customMarkerView.findViewById(R.id.number_);
        ImageView back_location = customMarkerView.findViewById(R.id.back_location);
//        markerImageView.setImageResource(resId);
        markerImageView.setText(Price);


        //offer Market Real
        //city_location


        if (type_selected.equals("Real")) {

            back_location.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_path_marker_home1));
            number_.setTextColor(getActivity().getResources().getColor(R.color.c1));
            number_.setText(city_location.get(Integer.valueOf(numbers)).getRequests() + "");

        } else if (type_selected.equals("Market")) {
            back_location.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_path_marker_home2));
            number_.setTextColor(getActivity().getResources().getColor(R.color.c2));
            number_.setText(city_location.get(Integer.valueOf(numbers)).getRequests() + "");

        } else if (type_selected.equals("offer")) {
            back_location.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_path_marker_home));
            number_.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
            number_.setText(city_location.get(Integer.valueOf(numbers)).getOffers() + "");

        }


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

    private Bitmap getMarkerBitmapFromView3_city(String Price, String numbers) {

        View customMarkerView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom4, null);
        TextView markerImageView = customMarkerView.findViewById(R.id.numb);
        TextView number_ = customMarkerView.findViewById(R.id.number_);
        ImageView back_location = customMarkerView.findViewById(R.id.back_location);
//        markerImageView.setImageResource(resId);
        markerImageView.setText(Price);


        //offer Market Real


        if (type_selected.equals("Real")) {

            back_location.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_path_marker_home1));
            number_.setTextColor(getActivity().getResources().getColor(R.color.c1));
//            number_.setText(list_city.get(Integer.valueOf(numbers)).getCount_fund_request() + "");

        } else if (type_selected.equals("Market")) {
            back_location.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_path_marker_home2));
            number_.setTextColor(getActivity().getResources().getColor(R.color.c2));
//            number_.setText(list_city.get(Integer.valueOf(numbers)).getCount_app_request() + "");

        } else if (type_selected.equals("offer")) {
            back_location.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_path_marker_home));
//            number_.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));

        }


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

    public LatLng getLocation_sau() {
        LatLng my_location = new LatLng(24.768516, 46.691505);
        return my_location;

    }


    public LatLng getLocation() {

        return Settings.getLocation(getActivity());


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        System.out.println("909090090909");

        try {
            if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            } else {


                LatLng mylocation = getLocation();
                if (mylocation != null) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(mylocation)).setTag("mylocation");

                    //                    CameraPosition cameraPosition = new CameraPosition.Builder().target(mylocation).zoom(4).build();
//                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 20));
//                    // Zoom in, animating the camera.
//                    googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                    // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(17), 3000, null);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void get_data_from_api(String type, String filtter, String latitude, String longitude) {
        WebService.loading(getActivity(), true);
//estate_pay_type=is_rent
// &price_from=1
// &price_to=300
// &area_from=1
// &area_from=300

        x_latitude = latitude;
        x_longitude = longitude;


        final_type_requst_filter = type;
        if (type.equals("list_order")) {
            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("list_order", WebService.Home_1 + "?lat=" + latitude + "&lan=" + longitude + "&estate_type=" + opration_select + "" + filtter + "&search=" + search_text.getText().toString());//&request_type=pay

        } else if (type.equals("list_offer")) {//aqarz
            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("list_offer", WebService.Home_2 + "?lat=" + latitude + "&lan=" + longitude + "&estate_type=" + opration_select + "" + filtter + "&search=" + search_text.getText().toString());

        } else if (type.equals("map_order")) {
            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("map_order", WebService.Home_3 + "?lat=" + latitude + "&lan=" + longitude + "&estate_type=" + opration_select + "" + filtter + "&search=" + search_text.getText().toString());//&request_type=pay

        } else if (type.equals("map_offer")) {//aqarz//"search="+search_text.getText().toString()+
            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("map_offer", WebService.Home_4 + "?lat=" + latitude + "&lan=" + longitude + "&estate_type=" + opration_select + "" + filtter + "&search=" + search_text.getText().toString());

        } else if (type.equals("citeis")) {//aqarz//"search="+search_text.getText().toString()+
            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("citeis", WebService.cities + "?state_id=" + latitude);

        }


    }

}
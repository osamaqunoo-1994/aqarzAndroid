package sa.aqarz.Fragment.mapsHome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
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
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sa.aqarz.Activity.AllOrderActivity;
import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.ContactUsActivity;
import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Activity.DetailsNewAqarezActivity;
import sa.aqarz.Activity.FavoriteActivity;
import sa.aqarz.Activity.InfoActivity;
import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Activity.NotficationActvity;
import sa.aqarz.Activity.OprationNew.AqarzOrActivity;
import sa.aqarz.Activity.OrderListActivity;
import sa.aqarz.Adapter.RecyclerView_All_type_in_fragment1;
import sa.aqarz.Adapter.RecyclerView_GenralNotfication;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat_new;
import sa.aqarz.Modules.AllEstate;
import sa.aqarz.Modules.AllNeigbers;
import sa.aqarz.Modules.CityLocation;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.Neighborhood;
import sa.aqarz.Modules.RegionModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.CustomInfoWindowGoogleMapEstatMaps;
import sa.aqarz.Settings.CustomInfoWindowGoogleMaptyp_2;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class MapsFragmentNew extends Fragment {


    MapsRepository mapsRepository;

    static MapsViewModel mapsViewModel;
    public static List<HomeModules_aqares> homeModules_aqares = new ArrayList<>();
    AlertDialog alertDialog;

    static IResult mResultCallback;

    static GoogleMap googleMap;
    static String type_selected = "Rela";
    static RecyclerView_HomeList_estat_new recyclerView_homeList_estat_new;
    static List<HomeModules_aqares> homeModules_aqares2 = new ArrayList<>();
    static SupportMapFragment mapFragment;
    static List<RegionModules> regionModules_list = new ArrayList<>();
    static List<CityLocation> city_location_list = new ArrayList<>();
    static List<Neighborhood> locationNeighborhood_list = new ArrayList<>();

    static String urlEstat = "";
    List<TypeModules> type_list = new ArrayList<>();

    static TextView RealStatr_order;
    static TextView MarketOrder;
    static TextView OfferOrder;
    static TextView all_estate_size;
    ImageView notfication;
    ImageView get_location;
    static ImageView convert_map_to_list;
    static ImageView Info;
    static ImageView change_list_to_map;
    RecyclerView TypeAqarez;
    ImageView cityMap;

    public static String region_id_postion = "";
    public static String city_id_postion = "";
    public static String city_serial_postion = "";
    ImageView favorit;

    public static String lat = "";
    public static String lat1 = "";
    public static String lan = "";
    public static String lan1 = "";
    public static boolean is_first_time = true;

    static LinearLayout list_estate;
    static LinearLayout all_list_backround;

    static LinearLayout filtter_type;
    AlertDialog ad;
    static Activity activity;
    static RelativeLayout layout_list;
    RecyclerView list_aqaers;
    CardView notfication_layout;
    static boolean onclick_marker_aqarez = false;


    static String type_filtter = "";


    private final OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        @Override
        public void onMapReady(GoogleMap googleMap1) {
            googleMap = googleMap1;
            LatLng sydney = new LatLng(24.527282, 44.007305);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            on_click_maps_marker();


            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 5));

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_maps_new, container, false);

        all_list_backround = v.findViewById(R.id.all_list_backround);
        filtter_type = v.findViewById(R.id.filtter_type);
        notfication_layout = v.findViewById(R.id.notfication_layout);
        favorit = v.findViewById(R.id.favorit);
        RealStatr_order = v.findViewById(R.id.RealStatr_order);

        activity = getActivity();
        mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();

        mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }


        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMapc) {


                googleMap = googleMapc;

                googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                    @Override
                    public void onCameraChange(CameraPosition cameraPosition) {

                        Log.i("centerLat", cameraPosition.target.latitude + "");

                        Log.i("centerLong", cameraPosition.target.longitude + "");


                        if (!region_id_postion.equals("") && !city_id_postion.equals("") && type_selected.equals("offer")) {


//                            System.out.println("lan:" + lan + " lat:" + lat);
//                            NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
//                            DecimalFormat formatter = (DecimalFormat) nf;
//                            formatter.applyPattern("00.0000000");

//                            DecimalFormat formater = new DecimalFormat("00.0000000");


                            lat = cameraPosition.target.latitude + "";
                            lan = cameraPosition.target.longitude + "";

                            lat1 = cameraPosition.target.latitude + "";
                            lan1 = cameraPosition.target.longitude + "";


                            System.out.println("lan:" + lan + " lat:" + lat);


                            if (onclick_marker_aqarez) {

                                onclick_marker_aqarez = false;

                            } else {
                                onclick_marker_aqarez = false;
                                getAllEstate();
                            }


                        }


                    }
                });
                mapsViewModel = new MapsViewModel();
                mapsRepository = new MapsRepository(getActivity(), false);

                init(view);


            }
        });


//        mapsRepository.getOpration();

    }

    static int page = 1;
    RecyclerView_All_type_in_fragment1 recyclerView_all_type_in_fragment;

    public void init(View v) {

        RealStatr_order = v.findViewById(R.id.RealStatr_order);
        MarketOrder = v.findViewById(R.id.MarketOrder);
        OfferOrder = v.findViewById(R.id.OfferOrder);
        notfication = v.findViewById(R.id.notfication);
        get_location = v.findViewById(R.id.get_location);
        TypeAqarez = v.findViewById(R.id.TypeAqarez);
        convert_map_to_list = v.findViewById(R.id.convert_map_to_list);
        Info = v.findViewById(R.id.Info);
        list_aqaers = v.findViewById(R.id.list_aqaers);
        list_estate = v.findViewById(R.id.list_estate);
        all_list_backround = v.findViewById(R.id.all_list_backround);
        change_list_to_map = v.findViewById(R.id.change_list_to_map);
        cityMap = v.findViewById(R.id.cityMap);
        all_estate_size = v.findViewById(R.id.all_estate_size);
        layout_list = v.findViewById(R.id.layout_list);
        favorit = v.findViewById(R.id.favorit);


        if (Settings.checkLogin()) {
            if (Settings.CheckIsAccountAqarzMan()) {
                RealStatr_order.setBackground(getActivity().getResources().getDrawable(R.drawable.button_1));
                MarketOrder.setBackground(null);
                OfferOrder.setBackground(null);
//        convert_map_to_list.setVisibility(View.GONE);
                RealStatr_order.setTextColor(getActivity().getResources().getColor(R.color.white));
                MarketOrder.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                OfferOrder.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                type_selected = "Real";
            } else {

                MarketOrder.setBackground(getActivity().getResources().getDrawable(R.drawable.button_2));
                RealStatr_order.setBackground(null);
                OfferOrder.setBackground(null);
//                convert_map_to_list.setVisibility(View.GONE);

                MarketOrder.setTextColor(getActivity().getResources().getColor(R.color.white));
                RealStatr_order.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                OfferOrder.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                type_selected = "Market";
            }
        }


        LinearLayoutManager layoutMana
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_aqaers.setLayoutManager(layoutMana);


        check_lastsenareo();


        RealStatr_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RealStatr_order.setBackground(getActivity().getResources().getDrawable(R.drawable.button_1));
                MarketOrder.setBackground(null);
                OfferOrder.setBackground(null);
//                convert_map_to_list.setVisibility(View.GONE);


                RealStatr_order.setTextColor(getActivity().getResources().getColor(R.color.white));
                MarketOrder.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                OfferOrder.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                type_selected = "Real";
                LatLng sydney = new LatLng(24.527282, 44.007305);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 5));
//                region_id_postion = "";
//                Hawk.put("region_id_postion", "");
//
//                city_id_postion = "";
//                Hawk.put("city_id_postion", "");
                check_lastsenareo();

//                set_locationRegions();
            }
        });
        cityMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng sydney = new LatLng(24.527282, 44.007305);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 5));

                region_id_postion = "";
                Hawk.put("region_id_postion", "");

                city_id_postion = "";
                Hawk.put("city_id_postion", "");
//                set_locationRegions();
                check_lastsenareo();

            }
        });
        MarketOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MarketOrder.setBackground(getActivity().getResources().getDrawable(R.drawable.button_2));
                RealStatr_order.setBackground(null);
                OfferOrder.setBackground(null);
//                convert_map_to_list.setVisibility(View.GONE);

                MarketOrder.setTextColor(getActivity().getResources().getColor(R.color.white));
                RealStatr_order.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                OfferOrder.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                type_selected = "Market";
                LatLng sydney = new LatLng(24.527282, 44.007305);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 5));
//                region_id_postion = "";
//                Hawk.put("region_id_postion", "");
//
//                city_id_postion = "";
//                Hawk.put("city_id_postion", "");
//                check_lastsenareo();
//                region_id_postion = "";
//                Hawk.put("region_id_postion", "");
//
//                city_id_postion = "";
//                Hawk.put("city_id_postion", "");
//                set_locationRegions();
                check_lastsenareo();
//                set_locationRegions();

            }
        });
        OfferOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OfferOrder.setBackground(getActivity().getResources().getDrawable(R.drawable.button_3));
                MarketOrder.setBackground(null);
                RealStatr_order.setBackground(null);
//                convert_map_to_list.setVisibility(View.VISIBLE);


                OfferOrder.setTextColor(getActivity().getResources().getColor(R.color.white));
                MarketOrder.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                RealStatr_order.setTextColor(getActivity().getResources().getColor(R.color.textColor));
                type_selected = "offer";
                LatLng sydney = new LatLng(24.527282, 44.007305);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 5));
//                region_id_postion = "";
//                city_id_postion = "";
//                Hawk.put("region_id_postion", "");
//                Hawk.put("city_id_postion", "");
//
//                set_locationRegions();
                check_lastsenareo();
            }
        });
        favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FavoriteActivity.class);
                getActivity().startActivity(intent);
            }
        });

        mapsViewModel.getRegions_list().observe(getActivity(), new Observer<List<RegionModules>>() {
            @Override
            public void onChanged(List<RegionModules> regionModules) {

                if (regionModules != null) {
                    regionModules_list = regionModules;

                }

                MainActivity.first_time_open_app = false;
                WebService.loading(getActivity(), false);

                set_locationRegions();

            }
        });

        list_aqaers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) { //1 for down

                    page = page + 1;


                    WebService.loading(activity, true);
                    init_volley();
                    VolleyService mVolleyService = new VolleyService(mResultCallback, activity);


                    mVolleyService.getAsync("home_estate_custom_list_more", urlEstat + "&page=" + page);


                }
            }
        });


//        list_estate_rec.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (!recyclerView.canScrollVertically(1)) { //1 for down
//
//                    page = page + 1;
//                    mapsViewModel.getEstate_list_without_loading(activity, "home/estate/list", urlEstat + "&page=" + page);
//
//
//                }
//            }
//        });
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        TypeAqarez.setLayoutManager(layoutManager1);

        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();
        recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_fragment1(getContext(), type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_fragment1.ItemClickListener() {
            @Override
            public void onItemClick(List<TypeModules> typeModules) {
                type_filtter = "";

                for (int i = 0; i < typeModules.size(); i++) {
                    if (typeModules.get(i).isIsselected()) {

                        if (type_filtter.equals("")) {
                            type_filtter = "" + typeModules.get(i).getId();
                        } else {
                            type_filtter = type_filtter + "," + typeModules.get(i).getId();

                        }

                    }
                }


                if (layout_list.getVisibility() == View.VISIBLE) {

                    page = 1;

                    recyclerView_homeList_estat_new = new RecyclerView_HomeList_estat_new(activity, homeModules_aqares);
                    list_aqaers.setAdapter(recyclerView_homeList_estat_new);

                    WebService.loading(activity, true);
                    init_volley();
                    VolleyService mVolleyService = new VolleyService(mResultCallback, activity);

                    mVolleyService.getAsync("home_estate_custom_list_more", WebService.home_estate_custom_list + "?estate_type=" + type_filtter);


                } else {
                    check_lastsenareo();

                }


            }
        });
        TypeAqarez.setAdapter(recyclerView_all_type_in_fragment);


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
        get_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


//                        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                        final View popupView = layoutInflater.inflate(R.layout.alert_permission, null);
//
//
//                        TextView cancle = popupView.findViewById(R.id.cancle);
//                        TextView ok = popupView.findViewById(R.id.ok);
//                        cancle.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                alertDialog.dismiss();
//                            }
//                        });
//                        ok.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//
//                                alertDialog.dismiss();
//
//                            }
//                        });
//
//                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//
////            alertDialog_country =
//                        builder.setView(popupView);
//
//
//                        alertDialog = builder.show();
//
//                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);

                    } else {


                        LatLng mylocation = getLocation();
                        if (mylocation != null) {
                            googleMap.addMarker(new MarkerOptions()
                                    .position(mylocation)).setTag("mylocation");

                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 6));


                        }


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        convert_map_to_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                change_layout();
//                get_all_estate_list_filttter();


                if (type_selected.equals("Real")) {


                    Intent intent = new Intent(activity, AllOrderActivity.class);
                    intent.putExtra("type", type_selected);


                    intent.putExtra("id_city", city_serial_postion);
                    intent.putExtra("id_nib", "");

                    activity.startActivity(intent);
                } else if (type_selected.equals("Market")) {
                    Intent intent = new Intent(activity, AllOrderActivity.class);

                    intent.putExtra("type", type_selected);
                    intent.putExtra("id_city", city_serial_postion);
                    intent.putExtra("id_nib", "");


                    activity.startActivity(intent);
                } else {
                    all_estate_size.setVisibility(View.GONE);
                    convert_map_to_list.setVisibility(View.GONE);
                    layout_list.setVisibility(View.VISIBLE);
                    change_list_to_map.setVisibility(View.VISIBLE);


                    if (homeModules_aqares.size() == 0) {
                        page = 1;

                        recyclerView_homeList_estat_new = new RecyclerView_HomeList_estat_new(activity, homeModules_aqares);
                        list_aqaers.setAdapter(recyclerView_homeList_estat_new);

                        WebService.loading(activity, true);
                        init_volley();
                        VolleyService mVolleyService = new VolleyService(mResultCallback, activity);

                        mVolleyService.getAsync("home_estate_custom_list_more", WebService.home_estate_custom_list);


                    } else {
                        recyclerView_homeList_estat_new = new RecyclerView_HomeList_estat_new(activity, homeModules_aqares);
                        list_aqaers.setAdapter(recyclerView_homeList_estat_new);

                    }

                }

            }
        });
        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), InfoActivity.class);
                getActivity().startActivity(intent);
            }
        });
        change_list_to_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Un_change_layout();
                layout_list.setVisibility(View.GONE);
                convert_map_to_list.setVisibility(View.VISIBLE);
                change_list_to_map.setVisibility(View.GONE);


            }
        });


    }

    public static void check_lastsenareo() {


        all_estate_size.setVisibility(View.GONE);


        if (Settings.checkLogin()) {

            if (regionModules_list.size() == 0) {

                mapsViewModel.getRegoins_without(activity);


            } else {


                if (Hawk.contains("region_id_postion")) {

                    if (Hawk.get("region_id_postion").toString().equals("")) {

//                if (MainActivity.first_time_open_app) {
                        if (regionModules_list.size() == 0) {
                            mapsViewModel.getRegoins(activity);

                        } else {
                            set_locationRegions();

                        }
                    } else {
                        System.out.println("@@!5");

                        region_id_postion = Hawk.get("region_id_postion").toString();

                        if (Hawk.contains("city_id_postion")) {


                            if (Hawk.get("city_id_postion").toString().equals("")) {


                                try {
                                    lat = "" + regionModules_list.get(Integer.valueOf(region_id_postion)).getCenter().getCoordinates().get(1);
                                    lan = "" + regionModules_list.get(Integer.valueOf(region_id_postion)).getCenter().getCoordinates().get(0);

                                } catch (Exception e) {
                                    lat = 24.768516 + "";
                                    lat = 46.691505 + "";
                                }
                                try {


                                    //                            LatLng my_location = new LatLng(Double.valueOf(regionModules_list.get(Integer.valueOf(region_id_postion)).getCenter().getCoordinates().get(1)), Double.valueOf(regionModules_list.get(Integer.valueOf(region_id_postion)).getCenter().getCoordinates().get(0)));
//                            CameraPosition cameraPosition = new CameraPosition.Builder().target(my_location).zoom(4).build();
//                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my_location, 6));
                                    // Zoom in, animating the camera.
//                            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                                 Zoom out to zoom level 10, animating with a duration of 2 seconds.
//                            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 3000, null);

                                    mapsViewModel.getCity_list().observe((LifecycleOwner) activity, new Observer<List<CityLocation>>() {
                                        @Override
                                        public void onChanged(List<CityLocation> cityLocations) {


                                            System.out.println("*&*&*&*&^%$#@#$%^");


                                            if (cityLocations != null) {
                                                set_locationCity(cityLocations);

                                            }
                                            WebService.loading(activity, false);


                                        }
                                    });
                                    mapsViewModel.getCity(activity, regionModules_list.get(Integer.valueOf(region_id_postion)).getId() + "");


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                city_id_postion = Hawk.get("city_id_postion").toString();


                                if (city_location_list.size() == 0) {

                                    mapsViewModel.getCity2(activity, regionModules_list.get(Integer.valueOf(region_id_postion)).getId() + "");

                                } else {


                                    System.out.println("^%&%&%&%&^%");


                                    try {
                                        lat = "" + city_location_list.get(Integer.valueOf(city_id_postion)).getLat();
                                        lan = "" + city_location_list.get(Integer.valueOf(city_id_postion)).getLan();

                                    } catch (Exception e) {
                                        lat = 24.768516 + "";
                                        lat = 46.691505 + "";
                                    }
                                    try {

//                        Hawk.put("city_id_postion", city_id_postion + "");


//                            lat = "" + city_location_list.get(Integer.valueOf(city_id_postion)).getLat();
//                            lan = "" + city_location_list.get(Integer.valueOf(city_id_postion)).getLan();
                                        if (type_selected.equals("Real")) {


//                                Intent intent = new Intent(activity, OrderListActivity.class);
//                                activity.startActivity(intent);


                                            WebService.loading(activity, true);
                                            init_volley();
                                            VolleyService mVolleyService = new VolleyService(mResultCallback, activity);

//                                        mVolleyService.getDataVolley("neighborhoods", WebService.neighborhoods + "/" + city_id_postion + "/list");
//                                        mVolleyService.getDataVolley("neighborhoods", WebService.neighborhoods + "/" + city_id_postion + "/list?is_all=0");
                                            mVolleyService.getAsync("neighborhoods", WebService.neighborhoods + "/" + city_location_list.get(Integer.valueOf(city_id_postion)).getSerial_city() + "/list?is_all=0");


                                        } else if (type_selected.equals("Market")) {


//                                Intent intent = new Intent(activity, OrderListActivity.class);
//                                activity.startActivity(intent);
                                            WebService.loading(activity, true);
                                            init_volley();
                                            VolleyService mVolleyService = new VolleyService(mResultCallback, activity);

//                                        mVolleyService.getDataVolley("neighborhoods", WebService.neighborhoods + "/" + city_id_postion + "/list");
//                                        mVolleyService.getDataVolley("neighborhoods", WebService.neighborhoods + "/" + city_id_postion + "/list?is_all=0");
                                            mVolleyService.getAsync("neighborhoods", WebService.neighborhoods + "/" + city_location_list.get(Integer.valueOf(city_id_postion)).getSerial_city() + "/list?is_all=2");


//                                        is_first_time = false;
//                                        LatLng sydney = new LatLng(Double.valueOf(lat + ""), Double.valueOf(lan + ""));
//                                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 8));
//
//                                        getAllEstate();
                                        } else if (type_selected.equals("offer")) {

                                            is_first_time = false;
                                            LatLng sydney = new LatLng(Double.valueOf(lat1 + ""), Double.valueOf(lan1 + ""));
                                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 8));

                                            getAllEstate();


                                        }


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }


                            }


                        } else {


                            region_id_postion = Hawk.get("region_id_postion").toString();

                            try {
                                lat = "" + city_location_list.get(Integer.valueOf(city_id_postion)).getLat();
                                lan = "" + city_location_list.get(Integer.valueOf(city_id_postion)).getLan();

                            } catch (Exception e) {
                                lat = 24.768516 + "";
                                lat = 46.691505 + "";
                            }
                            try {
                                System.out.println("#@$@#@@#@@@");
//                        lat = "" + regionModules_list.get(Integer.valueOf(region_id_postion)).getCenter().getCoordinates().get(1);
//                        lan = "" + regionModules_list.get(Integer.valueOf(region_id_postion)).getCenter().getCoordinates().get(0);
//                        LatLng my_location = new LatLng(Double.valueOf(regionModules_list.get(Integer.valueOf(region_id_postion)).getCenter().getCoordinates().get(1)), Double.valueOf(regionModules_list.get(Integer.valueOf(region_id_postion)).getCenter().getCoordinates().get(0)));
//                        CameraPosition cameraPosition = new CameraPosition.Builder().target(my_location).zoom(4).build();
//                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my_location, 6));
//                        // Zoom in, animating the camera.
//                        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
////                                 Zoom out to zoom level 10, animating with a duration of 2 seconds.
//                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 3000, null);

                                mapsViewModel.getCity_list().observe((LifecycleOwner) activity, new Observer<List<CityLocation>>() {
                                    @Override
                                    public void onChanged(List<CityLocation> cityLocations) {


                                        System.out.println("*&*&*&*&^%$#@#$%^");


                                        if (cityLocations != null) {
                                            set_locationCity(cityLocations);

                                        }
                                        WebService.loading(activity, false);


                                    }
                                });
                                mapsViewModel.getCity(activity, regionModules_list.get(Integer.valueOf(region_id_postion)).getId() + "");


                            } catch (Exception e) {

                            }
                        }


                    }


                } else {
//            if (MainActivity.first_time_open_app) {
                    if (regionModules_list.size() == 0) {

                        mapsViewModel.getRegoins(activity);

                    } else {
                        set_locationRegions();
                    }
                }
            }
        } else {

            all_list_backround.setVisibility(View.GONE);
            if (regionModules_list.size() == 0) {
                mapsViewModel.getRegoins_without(activity);


            } else {

                type_selected = "offer";

                set_locationRegions();

            }
        }

    }


    @Override
    public void onResume() {

        if (Settings.checkLogin()) {

            if (Settings.CheckIsAccountAqarzMan()) {
                favorit.setVisibility(View.VISIBLE);
                notfication_layout.setVisibility(View.VISIBLE);
                all_list_backround.setVisibility(View.VISIBLE);
                RealStatr_order.setVisibility(View.VISIBLE);
            } else {
                all_list_backround.setVisibility(View.VISIBLE);
                favorit.setVisibility(View.VISIBLE);
                notfication_layout.setVisibility(View.VISIBLE);
                RealStatr_order.setVisibility(View.GONE);

            }

        } else {
            all_list_backround.setVisibility(View.GONE);
            favorit.setVisibility(View.GONE);
            notfication_layout.setVisibility(View.GONE);


        }
        super.onResume();
    }

    public static void on_click_maps_marker() {


        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {


                if (marker.getTag().toString().contains("allcity")) {
                    marker.hideInfoWindow();
                    googleMap.setInfoWindowAdapter(null);

                    try {

                        String[] separated = marker.getTag().toString().split("/");

                        String number = separated[1]; // this will contain " they taste good"
//                        LatLng my_location = new LatLng(Double.valueOf(regionModules_list.get(Integer.valueOf(number)).getCenter().getCoordinates().get(1)), Double.valueOf(regionModules_list.get(Integer.valueOf(number)).getCenter().getCoordinates().get(0)));


                        city_id_postion = number;

                        city_serial_postion = city_location_list.get(Integer.valueOf(city_id_postion)).getSerial_city() + "";
                        Hawk.put("city_id_postion", city_id_postion + "");


                        lat = "" + city_location_list.get(Integer.valueOf(city_id_postion)).getLat();
                        lan = "" + city_location_list.get(Integer.valueOf(city_id_postion)).getLan();


                        if (type_selected.equals("Real")) {


//                            Intent intent = new Intent(activity, OrderListActivity.class);
//                            activity.startActivity(intent);
                            WebService.loading(activity, true);
                            init_volley();
                            VolleyService mVolleyService = new VolleyService(mResultCallback, activity);

                            mVolleyService.getAsync("neighborhoods", WebService.neighborhoods + "/" + city_location_list.get(Integer.valueOf(city_id_postion)).getSerial_city() + "/list?is_all=0");


                        } else if (type_selected.equals("Market")) {

                            WebService.loading(activity, true);
                            init_volley();
                            VolleyService mVolleyService = new VolleyService(mResultCallback, activity);

                            mVolleyService.getAsync("neighborhoods", WebService.neighborhoods + "/" + city_location_list.get(Integer.valueOf(city_id_postion)).getSerial_city() + "/list?is_all=2");


//                            Intent intent = new Intent(activity, OrderListActivity.class);
//                            activity.startActivity(intent);


                        } else if (type_selected.equals("offer")) {


                            getAllEstate();


                        }


                    } catch (Exception e) {

                    }

                } else if (marker.getTag().toString().contains("allArea")) {
                    System.out.println("^^^^^^^^^^^^^^^^^^^");
                    marker.hideInfoWindow();
                    googleMap.setInfoWindowAdapter(null);

                    try {


                        String[] separated = marker.getTag().toString().split("/");

                        String number = separated[1]; // this will contain " they taste good"


                        region_id_postion = number;

                        Hawk.put("region_id_postion", region_id_postion + "");


                        lat = "" + regionModules_list.get(Integer.valueOf(region_id_postion)).getCenter().getCoordinates().get(1);
                        lan = "" + regionModules_list.get(Integer.valueOf(region_id_postion)).getCenter().getCoordinates().get(0);


                        LatLng my_location = new LatLng(Double.valueOf(regionModules_list.get(Integer.valueOf(number)).getCenter().getCoordinates().get(1)), Double.valueOf(regionModules_list.get(Integer.valueOf(number)).getCenter().getCoordinates().get(0)));


                        CameraPosition cameraPosition = new CameraPosition.Builder().target(my_location).zoom(4).build();
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my_location, 6));
                        // Zoom in, animating the camera.
                        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                                 Zoom out to zoom level 10, animating with a duration of 2 seconds.
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 3000, null);

                        mapsViewModel.getCity_list().observe((LifecycleOwner) activity, new Observer<List<CityLocation>>() {
                            @Override
                            public void onChanged(List<CityLocation> cityLocations) {


                                System.out.println("*&*&*&*&^%$#@#$%^");


                                if (cityLocations != null) {
                                    set_locationCity(cityLocations);

                                }
                                WebService.loading(activity, false);


                            }
                        });
                        mapsViewModel.getCity(activity, regionModules_list.get(Integer.valueOf(number)).getId() + "");


                    } catch (Exception e) {

                    }


                } else if (marker.getTag().toString().contains("allEstate")) {


                    System.out.println("TETETRTR " + marker.getTag().toString());

                    String[] separated = marker.getTag().toString().split("/");

                    String number = separated[1]; // this will contain " they taste good"
//                    if (lastOpenned != null) {
//                        // Close the info window
//                        lastOpenned.hideInfoWindow();
//
//                        // Is the marker the same marker that was already open
//                        if (lastOpenned.equals(marker)) {
//                            // Nullify the lastOpenned object
//                            lastOpenned = null;
//                            // Return so that the info window isn't openned again
//                            return true;
//                        }
//                    }

                    try {
                        onclick_marker_aqarez = true;
                        CustomInfoWindowGoogleMapEstatMaps customInfoWindow = new CustomInfoWindowGoogleMapEstatMaps(activity, homeModules_aqares.get(Integer.valueOf(number)));
                        googleMap.setInfoWindowAdapter(customInfoWindow);
                        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (!marker.getTag().toString().equals("mylocation")) {

                                    Intent intent = new Intent(activity, DetailsActivity_aqarz.class);
//                                    Intent intent = new Intent(activity, DetailsNewAqarezActivity.class);
                                    intent.putExtra("id_aqarz", homeModules_aqares.get(Integer.valueOf(Integer.valueOf(number))).getId() + "");
                                    System.out.println("id_aqarz" + homeModules_aqares.get(Integer.valueOf(Integer.valueOf(number))).getId());

                                    activity.startActivity(intent);

                                }
                            }
                        });
//                        lastOpenned = marker;

                        // Event was handled by our code do not launch default behaviour.
//                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else if (marker.getTag().toString().contains("Neighb")) {
                    marker.hideInfoWindow();

                    String[] separated = marker.getTag().toString().split("/");

                    String number = separated[1]; // this will contain " they taste good"


                    Intent intent = new Intent(activity, AllOrderActivity.class);


//                    region_id_postion = "";
//                Hawk.put("region_id_postion", "");
//
//                city_id_postion = "";
//                Hawk.put("city_id_postion", "");


                    intent.putExtra("id_city", city_serial_postion);
//                    System.out.println("id_nib" + homeModules_aqares.get(Integer.valueOf(Integer.valueOf(number))).getId());
                    intent.putExtra("type", type_selected);
                    intent.putExtra("id_nib", locationNeighborhood_list.get(Integer.valueOf(number + "")).getNeighborhoodSerial() + "");

                    activity.startActivity(intent);

                    try {
//                        CustomInfoWindowGoogleMapEstatMaps customInfoWindow = new CustomInfoWindowGoogleMapEstatMaps(activity, homeModules_aqares.get(Integer.valueOf(number)));
//                        googleMap.setInfoWindowAdapter(customInfoWindow);
//                        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//                            @Override
//                            public void onInfoWindowClick(Marker marker) {
//                                if (!marker.getTag().toString().equals("mylocation")) {
//
//                                    Intent intent = new Intent(activity, DetailsActivity_aqarz.class);
//                                    intent.putExtra("id_aqarz", homeModules_aqares.get(Integer.valueOf(Integer.valueOf(number))).getId() + "");
//                                    System.out.println("id_aqarz" + homeModules_aqares.get(Integer.valueOf(Integer.valueOf(number))).getId());
//
//
//                                    activity.startActivity(intent);
//
//                                }
//                            }
//                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
                return false;
            }
        });
    }

    public static void set_locationCity(List<CityLocation> locationCity) {

        if (googleMap != null) {
            googleMap.clear();
            city_location_list.clear();
            city_location_list = locationCity;
            for (int i = 0; i < locationCity.size(); i++) {

                LatLng sydneya = new LatLng(Double.valueOf(locationCity.get(i).getLat() + ""), Double.valueOf(locationCity.get(i).getLan() + ""));

                if (googleMap != null) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(sydneya)
                            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewCity(locationCity.get(i).getName() + "", i + "")))).setTag("allcity/" + i);

                }

            }
        } else {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.clear();
                    on_click_maps_marker();

                    for (int i = 0; i < locationCity.size(); i++) {

                        LatLng sydneya = new LatLng(Double.valueOf(locationCity.get(i).getLat() + ""), Double.valueOf(locationCity.get(i).getLan() + ""));

                        if (googleMap != null) {
                            googleMap.addMarker(new MarkerOptions()
                                    .position(sydneya)
                                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewCity(locationCity.get(i).getName() + "", i + "")))).setTag("allcity/" + i);

                        }

                    }
                }
            });
        }
    }

    public static void set_locationNeighborhood(List<Neighborhood> locationNeighborhood) {

        if (googleMap != null) {
            googleMap.clear();
            locationNeighborhood_list.clear();
            locationNeighborhood_list = locationNeighborhood;
            for (int i = 0; i < locationNeighborhood.size(); i++) {

                LatLng sydneya = new LatLng(Double.valueOf(locationNeighborhood.get(i).getLat() + ""), Double.valueOf(locationNeighborhood.get(i).getLan() + ""));

                if (googleMap != null) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(sydneya)
                            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewNibores(locationNeighborhood.get(i).getName() + "(" + locationNeighborhood.get(i).getRequest_fund_counter() + ")", i + "")))).setTag("Neighb/" + i);

                }

            }
        } else {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.clear();
                    on_click_maps_marker();

                    for (int i = 0; i < locationNeighborhood.size(); i++) {

                        LatLng sydneya = new LatLng(Double.valueOf(locationNeighborhood.get(i).getLat() + ""), Double.valueOf(locationNeighborhood.get(i).getLan() + ""));

                        if (googleMap != null) {
                            googleMap.addMarker(new MarkerOptions()
                                    .position(sydneya)
                                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewNibores(locationNeighborhood.get(i).getName() + "(" + locationNeighborhood.get(i).getRequest_fund_counter() + ")", i + "")))).setTag("Neighb/" + i);
//                                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewNibores(locationNeighborhood.get(i).getName() + "", i + "")))).setTag("Neighb/" + i);

                        }

                    }
                }
            });
        }
    }

    public static void set_locationNeighborhoodMarket(List<Neighborhood> locationNeighborhood) {

        if (googleMap != null) {
            googleMap.clear();
            locationNeighborhood_list.clear();
            locationNeighborhood_list = locationNeighborhood;
            for (int i = 0; i < locationNeighborhood.size(); i++) {

                LatLng sydneya = new LatLng(Double.valueOf(locationNeighborhood.get(i).getLat() + ""), Double.valueOf(locationNeighborhood.get(i).getLan() + ""));

                if (googleMap != null) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(sydneya)
                            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewNibores(locationNeighborhood.get(i).getName() + "(" + locationNeighborhood.get(i).getRequest_app_counter() + ")", i + "")))).setTag("Neighb/" + i);

                }

            }
        } else {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.clear();
                    on_click_maps_marker();

                    for (int i = 0; i < locationNeighborhood.size(); i++) {

                        LatLng sydneya = new LatLng(Double.valueOf(locationNeighborhood.get(i).getLat() + ""), Double.valueOf(locationNeighborhood.get(i).getLan() + ""));

                        if (googleMap != null) {
                            googleMap.addMarker(new MarkerOptions()
                                    .position(sydneya)
                                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewNibores(locationNeighborhood.get(i).getName() + "(" + locationNeighborhood.get(i).getRequest_app_counter() + ")", i + "")))).setTag("Neighb/" + i);
//                                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewNibores(locationNeighborhood.get(i).getName() + "", i + "")))).setTag("Neighb/" + i);

                        }

                    }
                }
            });
        }
    }

    public static void set_locationRegions() {


        if (googleMap != null) {
            googleMap.clear();
            for (int i = 0; i < regionModules_list.size(); i++) {

                LatLng sydneya = new LatLng(regionModules_list.get(i).getCenter().getCoordinates().get(1), regionModules_list.get(i).getCenter().getCoordinates().get(0));

                if (googleMap != null) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(sydneya)

                            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewRegions(regionModules_list.get(i).getName() + "", i + "")))).setTag("allArea/" + i);

                }

            }
        } else {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.clear();
                    on_click_maps_marker();
                    for (int i = 0; i < regionModules_list.size(); i++) {

                        LatLng sydneya = new LatLng(regionModules_list.get(i).getCenter().getCoordinates().get(1), regionModules_list.get(i).getCenter().getCoordinates().get(0));

                        if (googleMap != null) {
                            googleMap.addMarker(new MarkerOptions()
                                    .position(sydneya)
                                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewRegions(regionModules_list.get(i).getName() + "", i + "")))).setTag("allArea/" + i);

                        }

                    }

                }
            });
        }


    }

    public static String get_price_and_return_price(String pricsS) {
        String price = pricsS;
        String v = price.replace(",", "");

        int price_int = Integer.valueOf(v);

        int prices = price_int;


        if (price_int < 1000) {

            price = prices + "";
        } else if (price_int > 1000 && price_int < 999999) {
            prices = price_int / 1000;

            price = prices + activity.getResources().getString(R.string.K);

        } else if (price_int > 999999) {
            prices = price_int / 1000000;

            price = prices + activity.getResources().getString(R.string.Million);

        }
        return price;
    }

    public static void set_locationEstate(List<HomeModules_aqares> homeModules_aqaresz) {

        homeModules_aqares = homeModules_aqaresz;
        if (googleMap != null) {
            googleMap.clear();
            for (int i = 0; i < homeModules_aqares.size(); i++) {

                LatLng sydneya = new LatLng(Double.valueOf(homeModules_aqares.get(i).getLat() + ""), Double.valueOf(homeModules_aqares.get(i).getLan() + ""));

                if (googleMap != null) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(sydneya)

                            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewEstate(get_price_and_return_price(homeModules_aqares.get(i).getTotalPrice() + ""), i + "")))).setTag("allEstate/" + i);

                }

            }
        } else {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.clear();
                    on_click_maps_marker();
                    for (int i = 0; i < homeModules_aqares.size(); i++) {

                        LatLng sydneya = new LatLng(Double.valueOf(homeModules_aqares.get(i).getLat() + ""), Double.valueOf(homeModules_aqares.get(i).getLan() + ""));

                        if (googleMap != null) {
                            googleMap.addMarker(new MarkerOptions()
                                    .position(sydneya)

                                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewEstate("", i + "")))).setTag("allEstate/" + i);

                        }
                    }

                }
            });
        }


    }


    private static Bitmap getMarkerBitmapFromViewRegions(String Price, String numbers) {

        View customMarkerView = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom3, null);
        TextView markerImageView = customMarkerView.findViewById(R.id.numb);
        TextView number_ = customMarkerView.findViewById(R.id.number_);
        ImageView back_location = customMarkerView.findViewById(R.id.back_location);
//        markerImageView.setImageResource(resId);
        markerImageView.setText(Price);


        //offer Market Real
        //city_location

//
        if (type_selected.equals("Real")) {

            back_location.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_path_marker_home1));
            number_.setTextColor(activity.getResources().getColor(R.color.c1));
            number_.setText(regionModules_list.get(Integer.valueOf(numbers)).getRequests() + "");

        } else if (type_selected.equals("Market")) {
            back_location.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_path_marker_home2));
            number_.setTextColor(activity.getResources().getColor(R.color.c2));
            number_.setText(regionModules_list.get(Integer.valueOf(numbers)).getApp_request() + "");

        } else if (type_selected.equals("offer")) {
            back_location.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_path_marker_home));
            number_.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
            number_.setText(regionModules_list.get(Integer.valueOf(numbers)).getEstate() + "");

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

    private static Bitmap getMarkerBitmapFromViewEstate(String Price, String numbers) {
        View customMarkerView = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom, null);
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

    private static Bitmap getMarkerBitmapFromViewNibores(String Price, String numbers) {
        View customMarkerView = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom_nu, null);
        TextView markerImageView = customMarkerView.findViewById(R.id.numb);
        ImageView back_ground = customMarkerView.findViewById(R.id.back_ground);
//        markerImageView.setImageResource(resId);
        markerImageView.setText(Price);


        if (type_selected.equals("Real")) {

            back_ground.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_marker_location1));
//            number_.setTextColor(activity.getResources().getColor(R.color.c1));
//            number_.setText(city_location_list.get(Integer.valueOf(numbers)).getCount_fund_request() + "");

        } else if (type_selected.equals("Market")) {
            back_ground.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_marker_location2));
//            number_.setTextColor(activity.getResources().getColor(R.color.c2));
//            number_.setText(regionModules_list.get(Integer.valueOf(numbers)).getRequests() + "");
//            number_.setText(city_location_list.get(Integer.valueOf(numbers)).getCount_app_request() + "");

        } else if (type_selected.equals("offer")) {
            back_ground.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_marker_location));
//            number_.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
//            number_.setText(city_location_list.get(Integer.valueOf(numbers)).getCount_app_estate() + "");


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

    private static Bitmap getMarkerBitmapFromViewCity(String Price, String numbers) {

        View customMarkerView = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom3, null);
        TextView markerImageView = customMarkerView.findViewById(R.id.numb);
        TextView number_ = customMarkerView.findViewById(R.id.number_);
        ImageView back_location = customMarkerView.findViewById(R.id.back_location);
//        markerImageView.setImageResource(resId);
        markerImageView.setText(Price);


        //offer Market Real
        //city_location

//
        if (type_selected.equals("Real")) {

            back_location.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_path_marker_home1));
            number_.setTextColor(activity.getResources().getColor(R.color.c1));
            number_.setText(city_location_list.get(Integer.valueOf(numbers)).getCount_fund_request() + "");

        } else if (type_selected.equals("Market")) {
            back_location.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_path_marker_home2));
            number_.setTextColor(activity.getResources().getColor(R.color.c2));
//            number_.setText(regionModules_list.get(Integer.valueOf(numbers)).getRequests() + "");
            number_.setText(city_location_list.get(Integer.valueOf(numbers)).getCount_app_request() + "");

        } else if (type_selected.equals("offer")) {
            back_location.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_path_marker_home));
            number_.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
            number_.setText(city_location_list.get(Integer.valueOf(numbers)).getCount_app_estate() + "");


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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        try {
            if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            } else {
                LatLng mylocation = getLocation();
                if (mylocation != null) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(mylocation)).setTag("mylocation");
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public LatLng getLocation() {

        return Settings.getLocation(getActivity());

    }

    public static String filter = "";


    public static void getAllEstate() {
        layout_list.setVisibility(View.GONE);

        System.out.println("9090909");
        String getId_region = "";
        String getSerial_city = "";


//        try {
//            if (!region_id_postion.equals("")) {
//                if (regionModules_list != null) {
//                    if (regionModules_list.size() != 0) {
//                        getId_region = "?state_id=" + regionModules_list.get(Integer.valueOf(region_id_postion + "")).getId() + "";
//
//                    }
//
//                }
//
//            }
//            if (!city_id_postion.equals("")) {
//
//                if (city_location_list != null) {
//                    if (city_location_list.size() != 0) {
//                        getSerial_city = "&city_id=" + city_location_list.get(Integer.valueOf(city_id_postion + "")).getSerial_city() + "";
//
//                    }
//
//                }
//
//            }
//
//        } catch (Exception e) {
//
//        }

        String lat_lan = "&lan=" + lan + "&lat=" + lat;
        String distance = "50";


        float zoom = googleMap.getCameraPosition().zoom;
        if (zoom > 8) {
            distance = "50";
        } else if (zoom < 8 & zoom > 6) {
            distance = "150";

        } else if (zoom < 5) {
            distance = "300";

        }


        WebService.loading(activity, true);
        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, activity);


        mVolleyService.getAsync("home_estate_custom_list", WebService.home_estate_custom_list + "?" + filter + lat_lan + "&distance=" + distance + getId_region + getSerial_city);
        urlEstat = WebService.home_estate_custom_list + "?" + filter + lat_lan + "&distance=" + distance + getId_region + getSerial_city;

    }

    public static void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(activity, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {

                        if (requestType.equals("neighborhoods")) {


                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(response.toString());

                            Gson gson = new Gson();

                            AllNeigbers allNeigbers = gson.fromJson(mJson, AllNeigbers.class);


                            if (type_selected.equals("Real")) {

                                set_locationNeighborhood(allNeigbers.getData());

                            } else if (type_selected.equals("Market")) {
                                set_locationNeighborhoodMarket(allNeigbers.getData());


                            } else if (type_selected.equals("offer")) {

                            }


                        } else if (requestType.equals("home_estate_custom_list")) {

                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(response.toString());

                            Gson gson = new Gson();

                            AllEstate allNeigbers = gson.fromJson(mJson, AllEstate.class);

                            homeModules_aqares = allNeigbers.getData().getData();


                            if (is_first_time) {

                            }

                            set_locationEstate(allNeigbers.getData().getData());
                            all_estate_size.setVisibility(View.VISIBLE);

                            all_estate_size.setText(allNeigbers.getData().getTo() + " " + activity.getResources().getString(R.string.From_t) + " " + allNeigbers.getData().getTotal() + " " + activity.getResources().getString(R.string.advertisementsx));


                        } else if (requestType.equals("home_estate_custom_list_more")) {

                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(response.toString());

                            Gson gson = new Gson();

                            if (page == 1) {
                                homeModules_aqares.clear();
                            }

                            AllEstate allNeigbers = gson.fromJson(mJson, AllEstate.class);

                            homeModules_aqares.addAll(allNeigbers.getData().getData());


                            recyclerView_homeList_estat_new.Refr();

//                            set_locationEstate(allNeigbers.getData().getData());
//                            all_estate_size.setVisibility(View.VISIBLE);


                        } else {
                            String message = response.getString("message");
                            WebService.Make_Toast_color(activity, message, "success");

                        }


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(activity, message, "error");
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

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(activity, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


                WebService.loading(activity, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        }

        ;


    }

}
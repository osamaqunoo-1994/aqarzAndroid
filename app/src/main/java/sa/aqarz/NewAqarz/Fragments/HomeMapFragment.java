package sa.aqarz.NewAqarz.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.AllOrderActivity;
import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Activity.NotficationActvity;
import sa.aqarz.Activity.SelectLocationActivity;
import sa.aqarz.Activity.SplashScreenActivity;
import sa.aqarz.Adapter.RecyclerView_All_type_in_fragment1;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat_new;
import sa.aqarz.Adapter.RecyclerView_city_side_menu;
import sa.aqarz.Fragment.mapsHome.MapsFragmentNew;
import sa.aqarz.Fragment.mapsHome.MapsRepository;
import sa.aqarz.Fragment.mapsHome.MapsViewModel;
import sa.aqarz.Modules.AllEstate;
import sa.aqarz.Modules.CityLocation;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.RegionModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_All_type_in_home_fragment;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_HomeList_estat_map;
import sa.aqarz.NewAqarz.Adapter.ViewPager_Adapter_estate_home_map1;
import sa.aqarz.NewAqarz.BottomDialog.BottomSheetDialogFragment_SelectType;
import sa.aqarz.NewAqarz.FillterActivity;
import sa.aqarz.NewAqarz.ListAqarzActivity;
import sa.aqarz.NewAqarz.MainAqarzActivity;
import sa.aqarz.NewAqarz.ManageOrderActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.CustomInfoWindowGoogleMapEstatMaps;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class HomeMapFragment extends Fragment {
    RecyclerView all_type_aqarz;
    static ViewPager allEstate_view_pager;
    List<TypeModules> type_list = new ArrayList<>();
    ImageView convert_to_region;
    ImageView my_location;
    ImageView convert_map_style;
    ImageView convert_to_list;
    ImageView fillter;
    static ProgressBar loading_city;
    ImageView notfication;
    ImageView notfication_dote;
    static SupportMapFragment mapFragment;
    static GoogleMap googleMap;
    String style = "";
    MapsRepository mapsRepository;
    static MapsViewModel mapsViewModel;
    static List<RegionModules> regionModules_list = new ArrayList<>();
    static Activity activity;

    static IResult mResultCallback;
    public static List<HomeModules_aqares> homeModules_aqares = new ArrayList<>();

    static String lat = "26.196634";
    static String lan = "43.813666";
    static String type_filtter = "";
    static String region_id_postion = "";
    static ProgressBar loading;
    static RecyclerView_HomeList_estat_map recyclerView_homeList_estat_new;
    static boolean onclick_marker_aqarez = false;

    static Marker marker_selected;
    static int last_postion_marker = 0;
    static PopupWindow popUp;
    LinearLayoutManager layoutManager;
    public static List<Marker> marker_list = new ArrayList<Marker>();
    ImageView search_nib;
    static EditText search_text;
    public static boolean is_first = true;
    static List<CityModules> cityModules_list_filtter = new ArrayList<>();
    ImageView fill_filtter;
    static RecyclerView all_city;
    static ImageView clear_city;

    LinearLayout select_type_lay;
    LinearLayout add_rent;
    TextView select_type_txt;

    BottomSheetDialogFragment_SelectType bottomSheetDialogFragment_selectType;
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
        public void onMapReady(GoogleMap googleMapx) {
            googleMap = googleMapx;
            async_map();
//            LatLng sydney = new LatLng(-34, 151);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        }

    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_map, container, false);
        is_first = true;
        setup_type_(view);


        activity = getActivity();
        mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }


        return view;
    }

    public void setup_type_(View v) {
        all_type_aqarz = v.findViewById(R.id.all_type_aqarz);
        convert_to_region = v.findViewById(R.id.convert_to_region);
        my_location = v.findViewById(R.id.my_location);
        convert_map_style = v.findViewById(R.id.convert_map_style);
        convert_to_list = v.findViewById(R.id.convert_to_list);
        fillter = v.findViewById(R.id.fillter);
        loading = v.findViewById(R.id.loading);
        allEstate_view_pager = v.findViewById(R.id.allEstate_view_pager);
        notfication = v.findViewById(R.id.notfication);
        notfication_dote = v.findViewById(R.id.notfication_dote);
        search_nib = v.findViewById(R.id.search_nib);
        search_text = v.findViewById(R.id.search_text);
        fill_filtter = v.findViewById(R.id.fill_filtter);
        clear_city = v.findViewById(R.id.clear_city);
        select_type_lay = v.findViewById(R.id.select_type_lay);
        select_type_txt = v.findViewById(R.id.select_type_txt);
        add_rent = v.findViewById(R.id.add_rent);

        //LastPostionLat
        //LastPostionLan


        if (Hawk.contains("filtter")) {
            if (Hawk.get("filtter").toString().equals("yes")) {
                fill_filtter.setVisibility(View.VISIBLE);
            } else {
                fill_filtter.setVisibility(View.GONE);

            }

        }
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        all_type_aqarz.setLayoutManager(layoutManager1);


        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();


        MainAqarzActivity.object_filtter.setType_list(type_list);

        RecyclerView_All_type_in_home_fragment recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_home_fragment(getContext(), type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_home_fragment.ItemClickListener() {
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


                MainAqarzActivity.object_filtter.setType_aqarz(type_filtter);
                MainAqarzActivity.object_filtter.setType_list(typeModules);

                if (Hawk.contains("LastPostionLat")) {

                    if (!Hawk.get("LastPostionLat").toString().equals("")) {

                        get_Estate_from_api();

                    }

                } else {

                }

//                if (layout_list.getVisibility() == View.VISIBLE) {
//
//                    page = 1;
//
////                    recyclerView_homeList_estat_new = new RecyclerView_HomeList_estat_new(activity, homeModules_aqares_list);
////                    list_aqaers.setAdapter(recyclerView_homeList_estat_new);
//
//
//                    WebService.loading(activity, true);
//                    init_volley();
//                    VolleyService mVolleyService = new VolleyService(mResultCallback, activity);
//
//                    mVolleyService.getAsync("home_estate_custom_list_more_1", url_list + "&estate_type=" + type_filtter);
//
//
//                } else {
//                    check_lastsenareo();
//
//                }


            }
        });
        all_type_aqarz.setAdapter(recyclerView_all_type_in_fragment);


        regionModules_list = Settings.getRegions();

        convert_to_region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Hawk.put("LastPostionLat", "");
                Hawk.put("LastPostionLan", "");


                homeModules_aqares.clear();
//                recyclerView_homeList_estat_new = new RecyclerView_HomeList_estat_map(getContext(), homeModules_aqares);
//                allEstate.setAdapter(recyclerView_homeList_estat_new);
//                allEstate_view_pager
                ViewPager_Adapter_estate_home_map1 viewPager_adapter_estate_home_map = new ViewPager_Adapter_estate_home_map1(activity, homeModules_aqares);
                allEstate_view_pager.setAdapter(viewPager_adapter_estate_home_map);
                allEstate_view_pager.setVisibility(View.GONE);

                lat = 26.196634 + "";
                lan = 43.813666 + "";

                LatLng sydney = new LatLng(Double.valueOf(lat + ""), Double.valueOf(lan + ""));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 5));

                set_locationRegions();


            }
        });
        my_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                    } else {
                        LatLng mylocation = getLocation();
                        if (mylocation != null) {
                            googleMap.setMyLocationEnabled(true);
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 6));
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            style = "MAP_TYPE_NORMAL";

            convert_map_style.setOnClickListener(new View.OnClickListener() {
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
        } catch (Exception e) {
            e.printStackTrace();

        }


        add_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManageOrderActivity.class);
                intent.putExtra("type_filtter", type_filtter);
                intent.putExtra("from", "is_rent");
                startActivity(intent);


            }
        });
        convert_to_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListAqarzActivity.class);
                intent.putExtra("type_filtter", type_filtter);
                startActivity(intent);


            }
        });
        fillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FillterActivity.class);
                intent.putExtra("from", "home");
                startActivity(intent);

            }
        });

        notfication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), NotficationActvity.class));

            }
        });

        select_type_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialogFragment_selectType = new BottomSheetDialogFragment_SelectType("");
                bottomSheetDialogFragment_selectType.addItemClickListener(new BottomSheetDialogFragment_SelectType.ItemClickListener() {
                    @Override
                    public void onItemClick(String filter) {

                        if (filter.equals("rent")) {
                            MainAqarzActivity.object_filtter.setEstate_pay_type("is_rent");

                            select_type_txt.setText(getResources().getString(R.string.rent));
                            bottomSheetDialogFragment_selectType.dismiss();

                        } else if (filter.equals("Purchase")) {
                            MainAqarzActivity.object_filtter.setEstate_pay_type("is_pay");

                            select_type_txt.setText(getResources().getString(R.string.Purchase));
                            bottomSheetDialogFragment_selectType.dismiss();

                        } else if (filter.equals("investment")) {
                            MainAqarzActivity.object_filtter.setEstate_pay_type("is_pay");

                            select_type_txt.setText(getResources().getString(R.string.investment));
                            bottomSheetDialogFragment_selectType.dismiss();

                        } else if (filter.equals("canclee")) {
                            MainAqarzActivity.object_filtter.setEstate_pay_type("");

                            select_type_txt.setText("");
                            bottomSheetDialogFragment_selectType.dismiss();
                        }

                        get_Estate_from_api();


                    }
                });
                bottomSheetDialogFragment_selectType.show(getChildFragmentManager(), "");


            }
        });

        search_nib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final View mView = LayoutInflater.from(getContext()).inflate(R.layout.drop_down_layout_city_and_nib, null, false);
                popUp = new PopupWindow(mView, LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, false);

                all_city = mView.findViewById(R.id.all_city);
                loading_city = mView.findViewById(R.id.loading_city);

                LinearLayoutManager layoutManager1
                        = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                all_city.setLayoutManager(layoutManager1);


                init_volley();

//                String region_id_postion = "";
//                if (MapsFragmentNew.region_id_postion != null) {
//                    region_id_postion = MapsFragmentNew.region_id_postion + "";
//                }
//                String city_id_postion = "";
//                if (MapsFragmentNew.city_id_postion != null) {
//                    city_id_postion = MapsFragmentNew.city_id_postion + "";
//                }


                VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
                mVolleyService.getDataVolley("cities_with_neb", WebService.cities_with_neb + "?name=" + search_text.getText().toString());//+ "&state_id=" + region_id_postion + "&city_id=" + city_id_postion


                popUp.setTouchable(true);
                popUp.setFocusable(true);
                popUp.setOutsideTouchable(true);

                //Solution
                popUp.showAsDropDown(search_nib);


            }
        });
        search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    final View mView = LayoutInflater.from(getContext()).inflate(R.layout.drop_down_layout_city_and_nib, null, false);
                    popUp = new PopupWindow(mView, LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, false);

                    all_city = mView.findViewById(R.id.all_city);
                    loading_city = mView.findViewById(R.id.loading_city);

                    LinearLayoutManager layoutManager1
                            = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    all_city.setLayoutManager(layoutManager1);


                    init_volley();

//                String region_id_postion = "";
//                if (MapsFragmentNew.region_id_postion != null) {
//                    region_id_postion = MapsFragmentNew.region_id_postion + "";
//                }
//                String city_id_postion = "";
//                if (MapsFragmentNew.city_id_postion != null) {
//                    city_id_postion = MapsFragmentNew.city_id_postion + "";
//                }


                    VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
                    mVolleyService.getDataVolley("cities_with_neb", WebService.cities_with_neb + "?name=" + search_text.getText().toString());//+ "&state_id=" + region_id_postion + "&city_id=" + city_id_postion


                    popUp.setTouchable(true);
                    popUp.setFocusable(true);
                    popUp.setOutsideTouchable(true);

                    //Solution
                    popUp.showAsDropDown(search_nib);


                    return true;
                }
                return false;
            }
        });

    }

    public void set_map_marker() {

        if (Hawk.contains("region_id_postion")) {

            try {
                region_id_postion = Hawk.get("region_id_postion").toString();
                MainAqarzActivity.object_filtter.setId_state(regionModules_list.get(Integer.valueOf(region_id_postion)).getId() + "");

            } catch (Exception e) {

            }

        }


        if (Hawk.contains("LastPostionLat")) {


            if (Hawk.get("LastPostionLat").toString().equals("")) {
                lat = 26.196634 + "";
                lan = 43.813666 + "";

                LatLng my_location = new LatLng(Double.valueOf(lat), Double.valueOf(lan));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(my_location).zoom(4).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my_location, 8));
                // Zoom in, animating the camera.
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                                 Zoom out to zoom level 10, animating with a duration of 2 seconds.
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(5), 3000, null);

                set_locationRegions();
            } else {

                lat = Hawk.get("LastPostionLat").toString();
                lan = Hawk.get("LastPostionLan").toString();

                LatLng my_location = new LatLng(Double.valueOf(lat), Double.valueOf(lan));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(my_location).zoom(4).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my_location, 8));
                // Zoom in, animating the camera.
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                                 Zoom out to zoom level 10, animating with a duration of 2 seconds.
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(9), 3000, null);

                get_Estate_from_api();
            }

        } else {

            lat = 26.196634 + "";
            lan = 43.813666 + "";


            LatLng my_location = new LatLng(Double.valueOf(lat), Double.valueOf(lan));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(my_location).zoom(4).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my_location, 8));
            // Zoom in, animating the camera.
            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                                 Zoom out to zoom level 10, animating with a duration of 2 seconds.
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(5), 3000, null);


            set_locationRegions();

        }
    }


    public void async_map() {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMapc) {
                googleMap = googleMapc;
                set_map_marker();
                on_click_maps_marker();
                try {
                    if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                        requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                    } else {
                        googleMap.setMyLocationEnabled(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                    @Override
                    public void onCameraChange(CameraPosition cameraPosition) {

//                        lat = cameraPosition.target.latitude + "";
//                        lan = cameraPosition.target.longitude + "";


                        if (is_first) {

                        } else {
                            if (Hawk.contains("LastPostionLat")) {

                                if (!Hawk.get("LastPostionLat").toString().equals("")) {
                                    double distance = distance(Double.valueOf(lat), Double.valueOf(lan), cameraPosition.target.latitude, cameraPosition.target.longitude);

                                    System.out.println("distance" + distance);
                                    if (distance < 20) {


                                    } else {

                                        if (onclick_marker_aqarez) {

                                            onclick_marker_aqarez = false;

                                        } else {
                                            onclick_marker_aqarez = false;

                                            lat = "" + cameraPosition.target.latitude;
                                            lan = "" + cameraPosition.target.longitude;

                                            Hawk.put("LastPostionLat", lat);
                                            Hawk.put("LastPostionLan", lan);


                                            get_Estate_from_api();
                                        }


                                    }
                                }

                            } else {

                            }

                        }
                    }

                });
                mapsViewModel = new

                        MapsViewModel();

                mapsRepository = new

                        MapsRepository(getActivity(), false);


            }
        });


    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public LatLng getLocation() {

        return Settings.getLocation(getActivity());

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
//                    on_click_maps_marker();
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
        back_location.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_path_marker_home));
        number_.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
        number_.setText(regionModules_list.get(Integer.valueOf(numbers)).getEstate() + "");


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

    public static void on_click_maps_marker() {


        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {


                if (marker.getTag().toString().contains("allArea")) {
                    System.out.println("^^^^^^^^^^^^^^^^^^^");
                    marker.hideInfoWindow();
                    googleMap.setInfoWindowAdapter(null);

                    try {
                        String[] separated = marker.getTag().toString().split("/");
                        String number = separated[1]; // this will contain " they taste good"
                        region_id_postion = number;

                        MainAqarzActivity.object_filtter.setId_state(regionModules_list.get(Integer.valueOf(region_id_postion)).getId() + "");


                        Hawk.put("region_id_postion", region_id_postion + "");

                        lat = "" + regionModules_list.get(Integer.valueOf(region_id_postion)).getCenter().getCoordinates().get(1);
                        lan = "" + regionModules_list.get(Integer.valueOf(region_id_postion)).getCenter().getCoordinates().get(0);


                        Hawk.put("LastPostionLat", lat);
                        Hawk.put("LastPostionLan", lan);

                        LatLng my_location = new LatLng(Double.valueOf(regionModules_list.get(Integer.valueOf(number)).getCenter().getCoordinates().get(1)), Double.valueOf(regionModules_list.get(Integer.valueOf(number)).getCenter().getCoordinates().get(0)));
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(my_location).zoom(4).build();
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my_location, 8));
                        // Zoom in, animating the camera.
                        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                                 Zoom out to zoom level 10, animating with a duration of 2 seconds.
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(9), 3000, null);

                        googleMap.clear();

                        get_Estate_from_api();

                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                } else if (marker.getTag().toString().contains("allEstate")) {


                    String[] separated = marker.getTag().toString().split("/");

                    String number = separated[1]; // this will contain " they taste good"
                    onclick_marker_aqarez = true;

                    last_postion_marker = Integer.valueOf(number);
                    try {
                        if (marker_selected != null) {
                            marker_selected.setIcon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewEstate_galf(get_price_and_return_price(homeModules_aqares.get(Integer.valueOf(number)).getTotalPrice() + ""), "")));
                        }

                        marker_selected = marker;
                        marker.setIcon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewEstate_green(get_price_and_return_price(homeModules_aqares.get(Integer.valueOf(number)).getTotalPrice() + ""), "")));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {

                        if (homeModules_aqares.size() > Integer.valueOf(number)) {
                            allEstate_view_pager.setCurrentItem(Integer.valueOf(number));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

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

//                    try {
//                        onclick_marker_aqarez = true;
//                        CustomInfoWindowGoogleMapEstatMaps customInfoWindow = new CustomInfoWindowGoogleMapEstatMaps(activity, homeModules_aqares.get(Integer.valueOf(number)));
//                        googleMap.setInfoWindowAdapter(customInfoWindow);
//                        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//                            @Override
//                            public void onInfoWindowClick(Marker marker) {
//                                if (!marker.getTag().toString().equals("mylocation")) {
//
//                                    Intent intent = new Intent(activity, DetailsActivity_aqarz.class);
////                                    Intent intent = new Intent(activity, DetailsNewAqarezActivity.class);
//                                    intent.putExtra("id_aqarz", homeModules_aqares.get(Integer.valueOf(Integer.valueOf(number))).getId() + "");
//                                    System.out.println("id_aqarz" + homeModules_aqares.get(Integer.valueOf(Integer.valueOf(number))).getId());
//
//                                    activity.startActivity(intent);
//
//                                }
//                            }
//                        });
////                        lastOpenned = marker;
//
//                        // Event was handled by our code do not launch default behaviour.
////                        return true;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }


                }
                return false;
            }
        });
    }

    public static void get_Estate_from_api() {
        loading.setVisibility(View.VISIBLE);
        String lat_lan = "&lan=" + lan + "&lat=" + lat;
        String distance = "50";


//        float zoom = googleMap.getCameraPosition().zoom;
//        if (zoom > 8) {
//            distance = "50";
//        } else if (zoom < 8 & zoom > 6) {
//            distance = "150";
//
//        } else if (zoom < 5) {
//            distance = "200";
//
//        }


//estate_pay_type

//state_id
//
//bedroom_number

        String estate_pay_type = "";

        if (!MainAqarzActivity.object_filtter.getEstate_pay_type().equals("")) {
            estate_pay_type = "&estate_pay_type=" + MainAqarzActivity.object_filtter.getEstate_pay_type();
        }

        String price_to = "";

        if (!MainAqarzActivity.object_filtter.getMax_price().equals("")) {
            price_to = "&price_to=" + MainAqarzActivity.object_filtter.getMax_price();
        }


        String price_from = "";

        if (!MainAqarzActivity.object_filtter.getLess_price().equals("")) {
            price_from = "&price_from=" + MainAqarzActivity.object_filtter.getLess_price();
        }


        String area_from = "";

        if (!MainAqarzActivity.object_filtter.getLess_space().equals("")) {
            area_from = "&area_from=" + MainAqarzActivity.object_filtter.getLess_space();
        }
        String area_to = "";

        if (!MainAqarzActivity.object_filtter.getMax_space().equals("")) {
            area_to = "&area_to=" + MainAqarzActivity.object_filtter.getMax_space();
        }


        String room = "";

        if (MainAqarzActivity.object_filtter.getNumber_room() != 0) {
            room = "&room=" + MainAqarzActivity.object_filtter.getNumber_room();
        }
        String lounges_number = "";

        if (MainAqarzActivity.object_filtter.getNumber_Lounges() != 0) {
            lounges_number = "&lounges_number=" + MainAqarzActivity.object_filtter.getNumber_Lounges();
        }
        String bathrooms_number = "";

        if (MainAqarzActivity.object_filtter.getNumber_Bathrooms() != 0) {
            bathrooms_number = "&bathrooms_number=" + MainAqarzActivity.object_filtter.getNumber_Bathrooms();
        }
        String dining_rooms_number = "";

        if (MainAqarzActivity.object_filtter.getNumber_Dining_rooms() != 0) {
            dining_rooms_number = "&dining_rooms_number=" + MainAqarzActivity.object_filtter.getNumber_Dining_rooms();
        }

        String boards_number = "";

        if (MainAqarzActivity.object_filtter.getNumber_Boards_plus() != 0) {
            boards_number = "&boards_number=" + MainAqarzActivity.object_filtter.getNumber_Boards_plus();
        }

        String elevators_number = "";

        if (MainAqarzActivity.object_filtter.getNumber_lifts() != 0) {
            elevators_number = "&elevators_number=" + MainAqarzActivity.object_filtter.getNumber_lifts();
        }
        String kitchen_number = "";

        if (MainAqarzActivity.object_filtter.getNumber_Kitchens_plus() != 0) {
            kitchen_number = "&kitchen_number=" + MainAqarzActivity.object_filtter.getNumber_Kitchens_plus();
        }
        String estate_age = "";

        if (!MainAqarzActivity.object_filtter.getDate().equals("")) {
            estate_age = "&estate_age=" + MainAqarzActivity.object_filtter.getDate();
        }


        String type_filtter_ = "";
        if (!MainAqarzActivity.object_filtter.getType_aqarz().equals("")) {
            type_filtter_ = "&estate_type=" + MainAqarzActivity.object_filtter.getType_aqarz();
        }


        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, activity);

        mVolleyService.getAsync("home_estate_custom_list", WebService.home_estate_custom_list + "?" + lat_lan + "&distance=" + distance + type_filtter_ + elevators_number + kitchen_number + estate_age + boards_number + dining_rooms_number + bathrooms_number + lounges_number + room + area_from + area_to + price_to + price_from + estate_pay_type);
//        urlEstat = WebService.home_estate_custom_list + "?" + filter + lat_lan + "&distance=" + distance + getId_region + getSerial_city;


    }

    public static void init_volley() {

        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(activity, false);
                loading.setVisibility(View.GONE);

//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

                        if (requestType.equals("Settings")) {


                        } else if (requestType.equals("home_estate_custom_list")) {

                            is_first = false;

                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(response.toString());

                            Gson gson = new Gson();

                            AllEstate allNeigbers = gson.fromJson(mJson, AllEstate.class);

                            homeModules_aqares = allNeigbers.getData().getData();


                            set_locationEstate(allNeigbers.getData().getData());
//                            all_estate_size.setVisibility(View.VISIBLE);
//                            all_estate_size.setText(allNeigbers.getData().getTo() + " " + activity.getResources().getString(R.string.From_t) + " " + allNeigbers.getData().getTotal() + " " + activity.getResources().getString(R.string.advertisementsx));

//                            allEstate_view_pager.setVisibility(View.VISIBLE);
                            ViewPager_Adapter_estate_home_map1 viewPager_adapter_estate_home_map = new ViewPager_Adapter_estate_home_map1(activity, homeModules_aqares);
                            allEstate_view_pager.setAdapter(viewPager_adapter_estate_home_map);

                            if (homeModules_aqares.size() == 0) {
                                allEstate_view_pager.setVisibility(View.GONE);
                            } else {
                                allEstate_view_pager.setVisibility(View.VISIBLE);

                            }
                            allEstate_view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                }

                                @Override
                                public void onPageSelected(int position) {

                                    try {
                                        if (marker_selected != null) {
                                            marker_selected.setIcon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewEstate_galf(get_price_and_return_price(homeModules_aqares.get(last_postion_marker).getTotalPrice() + ""), "")));

                                        }
                                        onclick_marker_aqarez = true;


                                        marker_selected = marker_list.get(position);
                                        last_postion_marker = position;
                                        marker_selected.setIcon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewEstate_green(get_price_and_return_price(homeModules_aqares.get(Integer.valueOf(position)).getTotalPrice() + ""), "")));


                                        LatLng my_location = new LatLng(Double.valueOf(homeModules_aqares.get(Integer.valueOf(position)).getLat()), Double.valueOf(homeModules_aqares.get(Integer.valueOf(position)).getLan()));
                                        CameraPosition cameraPosition = new CameraPosition.Builder().target(my_location).zoom(4).build();
                                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my_location, 10));
                                        // Zoom in, animating the camera.
//                                        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                                 Zoom out to zoom level 10, animating with a duration of 2 seconds.
//                                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(9), 3000, null);
//                                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12.0f));


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {

                                }
                            });

//                            allEstate_view_pager
                        } else if (requestType.equals("cities_with_neb")) {
                            JSONObject jsonObject = new JSONObject(data);

                            String datadata = jsonObject.getString("data");

                            JSONArray jsonArray = new JSONArray(datadata);
                            cityModules_list_filtter.clear();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();

                                CityModules homeModules_aqares = gson.fromJson(mJson, CityModules.class);

                                cityModules_list_filtter.add(homeModules_aqares);
                            }


//                            if (cityModules_list_filtter.size() == 0) {
//
//
//                                nodata.setVisibility(View.VISIBLE);
//                            } else {
//                                nodata.setVisibility(View.GONE);
//
//                            }

                            RecyclerView_city_side_menu recyclerView_city_bootom_sheets = new RecyclerView_city_side_menu(activity, cityModules_list_filtter);
                            recyclerView_city_bootom_sheets.addItemClickListener(new RecyclerView_city_side_menu.ItemClickListener() {
                                @Override
                                public void onItemClick(int i) {
//                        cityModules_list = alldata;
//                                    convert_city_to_filter();
//                                    searh = false;

//                                    MapsFragmentNew.city_id_postion = cityModules_list_filtter.get(i).getId() + "";
//                                    MapsFragmentNew.lat = cityModules_list_filtter.get(i).getLat() + "";
//                                    MapsFragmentNew.lan = cityModules_list_filtter.get(i).getLan() + "";

                                    lat = "" + cityModules_list_filtter.get(i).getLat();
                                    lan = "" + cityModules_list_filtter.get(i).getLan();

                                    clear_city.setVisibility(View.VISIBLE);
                                    Hawk.put("LastPostionLat", lat);
                                    Hawk.put("LastPostionLan", lan);

                                    LatLng my_location = new LatLng(Double.valueOf(lat), Double.valueOf(lan));
                                    CameraPosition cameraPosition = new CameraPosition.Builder().target(my_location).zoom(4).build();
                                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my_location, 8));
                                    // Zoom in, animating the camera.
                                    googleMap.animateCamera(CameraUpdateFactory.zoomIn());
//                                 Zoom out to zoom level 10, animating with a duration of 2 seconds.
                                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(9), 3000, null);
                                    popUp.dismiss();

                                    get_Estate_from_api();
                                    search_text.setText(cityModules_list_filtter.get(i).getSearch_name());//+ "-" + cityModules_list_filtter.get(i).getCity().getName()

                                }
                            });
                            clear_city.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    search_text.setText("");//+ "-" + cityModules_list_filtter.get(i).getCity().getName()

                                    clear_city.setVisibility(View.GONE);

                                }
                            });
                            try {
                                loading_city.setVisibility(View.GONE);


                                if (all_city != null) {
                                    all_city.setAdapter(recyclerView_city_bootom_sheets);

                                }
                            } catch (Exception e) {
                                e.printStackTrace();

                            }


                        }


                    } else {

                    }


                } catch (Exception e) {
                    e.printStackTrace();

                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());
                loading.setVisibility(View.GONE);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(activity, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {
                    e.printStackTrace();

                }


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                loading.setVisibility(View.GONE);

            }
        };


    }

    static MarkerOptions marker_add;
    static Marker amarker;

    public static void set_locationEstate(List<HomeModules_aqares> homeModules_aqaresz) {
        marker_list.clear();


        homeModules_aqares = homeModules_aqaresz;
        if (googleMap != null) {
            googleMap.clear();
            for (int i = 0; i < homeModules_aqares.size(); i++) {
                if (i == 0) {

                    LatLng sydneya = new LatLng(Double.valueOf(homeModules_aqares.get(i).getLat() + ""), Double.valueOf(homeModules_aqares.get(i).getLan() + ""));

                    if (googleMap != null) {
                        marker_add = new MarkerOptions()
                                .position(sydneya)
                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewEstate_green(get_price_and_return_price(homeModules_aqares.get(i).getTotalPrice() + ""), i + "")));


                        amarker = googleMap.addMarker(marker_add);

                        amarker.setTag("allEstate/" + i);

                        marker_selected = amarker;
                    }
                } else {
                    LatLng sydneya = new LatLng(Double.valueOf(homeModules_aqares.get(i).getLat() + ""), Double.valueOf(homeModules_aqares.get(i).getLan() + ""));

                    if (googleMap != null) {

                        marker_add = new MarkerOptions()
                                .position(sydneya)

                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewEstate(get_price_and_return_price(homeModules_aqares.get(i).getTotalPrice() + ""), i + "")));
                        amarker = googleMap.addMarker(marker_add);

                        amarker.setTag("allEstate/" + i);


                    }


                }
                marker_list.add(amarker);

            }
        } else {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.clear();
                    on_click_maps_marker();
                    for (int i = 0; i < homeModules_aqares.size(); i++) {
                        if (i == 0) {

                            LatLng sydneya = new LatLng(Double.valueOf(homeModules_aqares.get(i).getLat() + ""), Double.valueOf(homeModules_aqares.get(i).getLan() + ""));

                            if (googleMap != null) {
                                marker_add = new MarkerOptions()
                                        .position(sydneya)

                                        .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewEstate_green(get_price_and_return_price(homeModules_aqares.get(i).getTotalPrice() + ""), i + "")));
                                amarker = googleMap.addMarker(marker_add);

                                amarker.setTag("allEstate/" + i);

                                marker_selected = amarker;

                            }
                        } else {
                            LatLng sydneya = new LatLng(Double.valueOf(homeModules_aqares.get(i).getLat() + ""), Double.valueOf(homeModules_aqares.get(i).getLan() + ""));

                            if (googleMap != null) {

                                marker_add = new MarkerOptions()
                                        .position(sydneya)

                                        .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewEstate(get_price_and_return_price(homeModules_aqares.get(i).getTotalPrice() + ""), i + "")));
                                amarker = googleMap.addMarker(marker_add);

                                amarker.setTag("allEstate/" + i);


                            }


                        }
                        marker_list.add(amarker);

                    }
                }
            });
        }

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

    private static Bitmap getMarkerBitmapFromViewEstate_galf(String Price, String numbers) {
        View customMarkerView = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom_galf, null);
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

    private static Bitmap getMarkerBitmapFromViewEstate_green(String Price, String numbers) {
        View customMarkerView = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom2, null);
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

    public static String get_price_and_return_price(String pricsS) {
        String price = pricsS;
        String v = price.replace(",", "");

        int price_int = Integer.valueOf(v);

        int prices = price_int;


        if (price_int < 10000) {
            prices = price_int / 1000;

            price = prices + activity.getResources().getString(R.string.K2);
        } else if (price_int > 10000 && price_int < 999999) {
            prices = price_int / 1000;

            price = prices + activity.getResources().getString(R.string.K);

        } else if (price_int > 999999) {
            prices = price_int / 1000000;

            price = prices + activity.getResources().getString(R.string.Million);

        }
        return price;
    }

    @Override
    public void onResume() {

        if (Hawk.contains("filtter")) {
            if (Hawk.get("filtter").toString().equals("yes")) {
                fill_filtter.setVisibility(View.VISIBLE);
            } else {
                fill_filtter.setVisibility(View.GONE);

            }

        }


        if (MainAqarzActivity.object_filtter.getType_list() != null) {
            if (MainAqarzActivity.object_filtter.getType_list().size() != 0) {
                type_list = MainAqarzActivity.object_filtter.getType_list();
            } else {
                type_list = Settings.getSettings().getEstate_types().getOriginal().getData();
            }


        } else {
            type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        }

        MainAqarzActivity.object_filtter.setType_list(type_list);

        RecyclerView_All_type_in_home_fragment recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_home_fragment(getContext(), type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_home_fragment.ItemClickListener() {
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


                MainAqarzActivity.object_filtter.setType_aqarz(type_filtter);
                MainAqarzActivity.object_filtter.setType_list(typeModules);

                if (Hawk.contains("LastPostionLat")) {

                    if (!Hawk.get("LastPostionLat").toString().equals("")) {

                        get_Estate_from_api();

                    }

                } else {

                }


            }
        });
        all_type_aqarz.setAdapter(recyclerView_all_type_in_fragment);

        if (is_first) {

        } else {
            get_Estate_from_api();

        }


        if (Settings.checkLogin()) {
            notfication.setVisibility(View.VISIBLE);
            notfication_dote.setVisibility(View.VISIBLE);
            notfication_dote.setVisibility(View.VISIBLE);
            add_rent.setVisibility(View.VISIBLE);

            if (Settings.CheckIsAccountAqarzMan()) {

            } else {


            }

        } else {
            notfication.setVisibility(View.INVISIBLE);
            notfication_dote.setVisibility(View.INVISIBLE);
            add_rent.setVisibility(View.GONE);


        }
        super.onResume();
    }

}
package aqarz.revival.sa.aqarz.Fragment;
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
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import aqarz.revival.sa.aqarz.Activity.Auth.LoginActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import aqarz.revival.sa.aqarz.Activity.MainActivity;
import aqarz.revival.sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import aqarz.revival.sa.aqarz.Activity.OprationAqarz.RequestOrderActivity;
import aqarz.revival.sa.aqarz.Activity.OprationNew.RequestServiceActivity;
import aqarz.revival.sa.aqarz.Activity.SplashScreenActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_map;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Type_in_map;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_select_oprat_in_fragment;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_HomeList;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_HomeList_estat;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares_orders;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_Filtter;
import aqarz.revival.sa.aqarz.Modules.BankModules;
import aqarz.revival.sa.aqarz.Modules.HomeModules;
import aqarz.revival.sa.aqarz.Modules.HomeModules_aqares;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.Modules.select_typeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.GpsTracker;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class MapsFragment extends Fragment {
    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;

    TextView Orders_tab;
    TextView Offers_tab;


    String final_type_requst_filter = "";
    String typeTab = "Orders_tab";

    TextView RequstAqars;
    TextView addAqares;
    RecyclerView type;
    RecyclerView selsct_type_all;

    List<TypeModules> typeModules_list = new ArrayList<>();
    List<select_typeModules> oprationModules_list = new ArrayList<>();
    CircleImageView image_profile;

    IResult mResultCallback;

    List<TypeModules> type_list = new ArrayList<>();


    List<HomeModules> homeModules = new ArrayList<>();
    List<HomeModules_aqares> homeModules_aqares = new ArrayList<>();


    RelativeLayout layout_list;
    LinearLayout layout_button;
    RelativeLayout layout_button_2;

    ImageView convert_map_to_list;
    ImageView change_list_to_map;

    String opration_select = "0";
    String convert_type = "map";


    RecyclerView list_aqaers;
    ImageView chmnage_map_style;


    String style = "";

    ImageView get_location;
    GpsTracker gpsTracker;
    ImageView filtter;


    ImageView nodata_vis;


    String filtter_selected = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = (MapView) v.findViewById(R.id.mapViewxx);
        Orders_tab = v.findViewById(R.id.Orders_tab);
        Offers_tab = v.findViewById(R.id.Offers_tab);

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

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (final_type_requst_filter.equals("list_order")) {

                        } else if (final_type_requst_filter.equals("list_offer")) {//aqarz

                        } else if (final_type_requst_filter.equals("map_order")) {

                            BottomSheetDialogFragment_DetailsAqares_orders bottomSheetDialogFragment_detailsAqares_orders = new BottomSheetDialogFragment_DetailsAqares_orders(homeModules.get(0));

                            bottomSheetDialogFragment_detailsAqares_orders.show(getFragmentManager(), "");


                        } else if (final_type_requst_filter.equals("map_offer")) {//aqarz


                            BottomSheetDialogFragment_DetailsAqares bottomSheetDialogFragment_detailsAqares = new BottomSheetDialogFragment_DetailsAqares(homeModules_aqares.get(0));

                            bottomSheetDialogFragment_detailsAqares.show(getFragmentManager(), "");


                        }


                        return false;
                    }
                });

                try {
                    if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);

                        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);

                    } else {


                        LatLng mylocation = getLocation();
                        if (mylocation != null) {
                            googleMap.addMarker(new MarkerOptions()
                                    .position(mylocation))

                            ;
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 20));
                            // Zoom in, animating the camera.
                            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                            // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                            googleMap.animateCamera(CameraUpdateFactory.zoomTo(17), 3000, null);
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
                    Picasso.get().load(Settings.GetUser().getLogo()).error(R.drawable.ic_user_un).into(image_profile);

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

        LinearLayoutManager layoutManagexx
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_aqaers.setLayoutManager(layoutManagexx);


//---------------------------------------------------------------------------------------------

        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        type.setLayoutManager(layoutManager1);
        RecyclerView_All_type_in_fragment recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_fragment(getContext(), type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_fragment.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                opration_select = type_list.get(position).getId().toString() + "";
            }
        });
        type.setAdapter(recyclerView_all_type_in_fragment);

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

        get_data_from_api("map_order", filtter_selected);


    }

    public void ActionButton() {

//----------------------------------------------------------------------Rec

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

                    Intent intent = new Intent(getContext(), RequestServiceActivity.class);
//                                intent.putExtra("from", "splash");
                    startActivity(intent);
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
                    Intent intent = new Intent(getContext(), AddAqarsActivity.class);
//              intent.putExtra("from", "splash");
                    startActivity(intent);
                }

//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);


            }
        });

        //------------------------------------------------------------------------------------------------------
        convert_map_to_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                layout_button.setVisibility(View.GONE);
                layout_button_2.setVisibility(View.GONE);
                layout_list.setVisibility(View.VISIBLE);

                convert_type = "list";
                if (typeTab.equals("Orders_tab")) {
                    get_data_from_api("list_order", filtter_selected);

                } else {
                    get_data_from_api("list_offer", filtter_selected);

                }


            }
        });
        change_list_to_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list_aqaers.setAdapter(null);

                layout_button.setVisibility(View.VISIBLE);
                layout_button_2.setVisibility(View.VISIBLE);
                layout_list.setVisibility(View.GONE);

                if (typeTab.equals("Orders_tab")) {
                    get_data_from_api("map_order", filtter_selected);

                } else {
                    get_data_from_api("map_offer", filtter_selected);

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
                        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                    } else {


                        LatLng mylocation = getLocation();
                        if (mylocation != null) {
                            googleMap.addMarker(new MarkerOptions()
                                    .position(mylocation))

                            ;
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 20));
                            // Zoom in, animating the camera.
                            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                            // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                            googleMap.animateCamera(CameraUpdateFactory.zoomTo(17), 3000, null);
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
                        get_data_from_api(final_type_requst_filter, filtter_selected);

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
                Offers_tab.setTextColor(getResources().getColor(R.color.textColor));

                if (convert_type.equals("map")) {
                    get_data_from_api("map_order", filtter_selected);

                } else {
                    get_data_from_api("list_order", filtter_selected);

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
                Orders_tab.setTextColor(getResources().getColor(R.color.textColor));

                if (convert_type.equals("map")) {
                    get_data_from_api("map_offer", filtter_selected);

                } else {
                    get_data_from_api("list_offer", filtter_selected);

                }

                typeTab = "Offers_tab";


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
                            System.out.println("lfkdlfkdlkf");
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
                            System.out.println("homeModules_aqareshomeModules_aqares+" + homeModules_aqares.size());
                            list_aqaers.setAdapter(new RecyclerView_HomeList_estat(getContext(), homeModules_aqares));


                        } else if (requestType.equals("map_order")) {


                            System.out.println("lfkdlfkdlkf");
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

                                LatLng sydneya = new LatLng(Double.valueOf(bankModules.getLat()), Double.valueOf(bankModules.getLan()));
                                googleMap.addMarker(new MarkerOptions()
                                        .position(sydneya)
                                        .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(bankModules.getPrice_from()))));


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


                        } else if (requestType.equals("map_offer")) {//aqarz
                            try {


                                System.out.println("lfkdlfkdlkf");
                                String data = response.getString("data");

//                                JSONObject jsonObject_data = new JSONObject(data);
//
//                                String data_inside = jsonObject_data.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                googleMap.clear();
                                homeModules_aqares.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {


                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    HomeModules_aqares bankModules = gson.fromJson(mJson, HomeModules_aqares.class);
                                    homeModules_aqares.add(bankModules);


//                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(camPos));

                                    LatLng sydneya = new LatLng(Double.valueOf(bankModules.getLat()), Double.valueOf(bankModules.getLan()));
                                    googleMap.addMarker(new MarkerOptions()
                                            .position(sydneya)
                                            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(bankModules.getTotalPrice()))));


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

    public LatLng getLocation() {
        gpsTracker = new GpsTracker(getContext());
        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            System.out.println("latitude:" + latitude);
            System.out.println("longitude:" + longitude);

            LatLng my_location = new LatLng(latitude, longitude);

            return my_location;

        } else {
            gpsTracker.showSettingsAlert();

            return null;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        try {
            if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            } else {


                LatLng mylocation = getLocation();
                if (mylocation != null) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(mylocation))

                    ;
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 20));
                    // Zoom in, animating the camera.
                    googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                    // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(17), 3000, null);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void get_data_from_api(String type, String filtter) {
        WebService.loading(getActivity(), true);
//estate_pay_type=is_rent
// &price_from=1
// &price_to=300
// &area_from=1
// &area_from=300
        final_type_requst_filter = type;
        if (type.equals("list_order")) {
            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("list_order", WebService.Home_1 + "?lat=10&lan=20&estate_type=" + opration_select + "" + filtter);//&request_type=pay

        } else if (type.equals("list_offer")) {//aqarz
            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("list_offer", WebService.Home_2 + "?lat=10&lan=20" + filtter);

        } else if (type.equals("map_order")) {
            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("map_order", WebService.Home_3 + "?lat=10&lan=20&estate_type=" + opration_select + "" + filtter);//&request_type=pay

        } else if (type.equals("map_offer")) {//aqarz
            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
            mVolleyService.getDataVolley("map_offer", WebService.Home_4 + "?lat=110&lan=211" + filtter);

        }


    }
}
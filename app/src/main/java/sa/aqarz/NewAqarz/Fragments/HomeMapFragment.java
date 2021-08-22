package sa.aqarz.NewAqarz.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.RecyclerView_All_type_in_fragment1;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_All_type_in_home_fragment;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.VolleyService;

public class HomeMapFragment extends Fragment {
    RecyclerView all_type_aqarz;
    List<TypeModules> type_list = new ArrayList<>();

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
        public void onMapReady(GoogleMap googleMap) {
            LatLng sydney = new LatLng(-34, 151);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        }

    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_map, container, false);

        setup_type_(view);
        return view;
    }

    public void setup_type_(View v) {
        all_type_aqarz = v.findViewById(R.id.all_type_aqarz);


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        all_type_aqarz.setLayoutManager(layoutManager1);


        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();
        RecyclerView_All_type_in_home_fragment recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_home_fragment(getContext(), type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_home_fragment.ItemClickListener() {
            @Override
            public void onItemClick(List<TypeModules> typeModules) {
//                type_filtter = "";
//
//                for (int i = 0; i < typeModules.size(); i++) {
//                    if (typeModules.get(i).isIsselected()) {
//
//                        if (type_filtter.equals("")) {
//                            type_filtter = "" + typeModules.get(i).getId();
//                        } else {
//                            type_filtter = type_filtter + "," + typeModules.get(i).getId();
//
//                        }
//                    }
//                }


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
}
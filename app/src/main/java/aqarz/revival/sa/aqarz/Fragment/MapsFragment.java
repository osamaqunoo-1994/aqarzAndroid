package aqarz.revival.sa.aqarz.Fragment;
/**
 * Created by osama on 10/8/2017.
 */


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Activity.MainActivity;
import aqarz.revival.sa.aqarz.Activity.OprationAqarz.RequestOrderActivity;
import aqarz.revival.sa.aqarz.Activity.SplashScreenActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_map;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Type_in_map;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;

import static android.app.Activity.RESULT_OK;


public class MapsFragment extends Fragment {
    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;

    TextView RequstAqars;
    RecyclerView type;
    RecyclerView opration;
    List<TypeModules> typeModules_list = new ArrayList<>();
    List<OprationModules> oprationModules_list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) v.findViewById(R.id.mapViewxx);
        type = v.findViewById(R.id.type);
        opration = v.findViewById(R.id.opration);
        RequstAqars = v.findViewById(R.id.RequstAqars);

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

                googleMap.getUiSettings().setRotateGesturesEnabled(true);


            }
        });
//----------------------------------------------------------------------Rec


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        type.setLayoutManager(layoutManager1);

        LinearLayoutManager layoutManagers
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        opration.setLayoutManager(layoutManagers);


        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        type.setAdapter(new RecyclerView_All_Type_in_map(getContext(), typeModules_list));


        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());

        opration.setAdapter(new RecyclerView_All_Opration_in_map(getContext(), oprationModules_list));


        RequstAqars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), RequestOrderActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);


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


    private BitmapDescriptor bitmapDescriptorFromVector(Context context, @DrawableRes int id) {
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

}
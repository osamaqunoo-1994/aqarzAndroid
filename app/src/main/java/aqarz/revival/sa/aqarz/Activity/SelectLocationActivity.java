package aqarz.revival.sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import aqarz.revival.sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_DetailsAqares_orders;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_Filtter;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.WebService;

public class SelectLocationActivity extends AppCompatActivity {
    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;
    Geocoder geocoder;
    List<Address> addresses;

    String lat = "";
    String lang = "";

    EditText text_search;
    PlaceAutocompleteFragment placeAutoComplete;
    private ItemClickListener mItemClickListener;

    Button select;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);

        mMapView = (MapView) findViewById(R.id.mapViewxx);
        text_search = findViewById(R.id.text_search);
        select = findViewById(R.id.select);
        back = findViewById(R.id.back);


        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(SelectLocationActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }


        placeAutoComplete = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);
        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Log.d("Maps", "Place selected: " + place.getName());
                LatLng sydney = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
                // Zoom in, animating the camera.
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);


            }

            @Override
            public void onError(Status status) {
                Log.d("Maps", "An error occurred: " + status);
            }
        });


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lat.equals("")) {
//                    if (mItemClickListener != null) {
////                        mItemClickListener.onItemClick(lat, lang, text_search.getText().toString());
////                    }
                    Intent resultIntent = new Intent();
// TODO Add extras or a data URI to this intent as appropriate.
                    resultIntent.putExtra("lat", lang);
                    resultIntent.putExtra("lang", lang);
                    resultIntent.putExtra("address", text_search.getText().toString());
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();


                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                googleMap.getUiSettings().setRotateGesturesEnabled(true);


                if (ActivityCompat.checkSelfPermission(SelectLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SelectLocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMap.setMyLocationEnabled(true);
                View locationButton = ((View) mMapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
// position on right bottom
                rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
                rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                rlp.setMargins(0, 180, 180, 0);


                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        googleMap.clear();
                        googleMap.addMarker(new MarkerOptions()
                                .position(latLng)).setTag("mylocation");

                        lat = latLng.latitude + "";
                        lang = latLng.longitude + "";


                        geocoder = new Geocoder(SelectLocationActivity.this, Locale.getDefault());

                        try {

                            WebService.loading(SelectLocationActivity.this, true);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        WebService.loading(SelectLocationActivity.this, false);


                                        addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//                                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//                                        String city = addresses.get(0).getLocality();
                                        String state = addresses.get(0).getAdminArea();
                                        String country = addresses.get(0).getCountryName();
//                                        String postalCode = addresses.get(0).getPostalCode();
//                                        String knownName = addresses.get(0).getFeatureName(); // Onl


                                        text_search.setText(country + " - " + state);
                                    } catch (Exception e) {

                                    }

                                }
                            }, 500); // After 1 seconds


                            //    state country


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });

            }
        });

    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(String lat_, String lang_, String address);
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

}
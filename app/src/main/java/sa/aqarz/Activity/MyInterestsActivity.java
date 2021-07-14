package sa.aqarz.Activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import sa.aqarz.Activity.profile.MyOffersActivity;
import sa.aqarz.Activity.profile.ProfileDetailsActivity;
import sa.aqarz.Adapter.RecyclerVie_member_service;
import sa.aqarz.Adapter.RecyclerVie_member_service_m;
import sa.aqarz.Adapter.RecyclerView_Course;
import sa.aqarz.Adapter.RecyclerView_experince;
import sa.aqarz.DataBase.FeedReaderContract;
import sa.aqarz.DataBase.FeedReaderDbHelper;
import sa.aqarz.Dialog.BottomSheetDialogFragment_QR;
import sa.aqarz.Modules.AlLNebModules;
import sa.aqarz.Modules.AllCityList;
import sa.aqarz.Modules.AllCityModules;
import sa.aqarz.Modules.CityLocation;
import sa.aqarz.Modules.MyItem;
import sa.aqarz.Modules.User;
import sa.aqarz.R;
import sa.aqarz.Settings.CustomInfoWindowGoogleMapEstatMaps;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class MyInterestsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static GoogleMap mMap;
    static IResult mResultCallback;
    static SupportMapFragment mapFragment;
    private FusedLocationProviderClient fusedLocationClient;
    private List<AlLNebModules.neb> all_neb = new ArrayList<>();
    private ClusterManager<MyItem> clusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_interests);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(37.35, -122.2);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        WebService.loading(MyInterestsActivity.this, true);

        clusterManager = new ClusterManager<MyItem>(MyInterestsActivity.this, mMap);


        mMap.setOnCameraIdleListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);


        init_volley();

        try {
            VolleyService mVolleyService = new VolleyService(mResultCallback, MyInterestsActivity.this);

//            mVolleyService.getDataVolley("user", WebService.user + id + "");
            mVolleyService.getDataVolley("title_global_cities", WebService.title_global_cities + "");

        } catch (Exception e) {

        }
//        on_click_maps_marker();


        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

                AlLNebModules.neb neb = all_neb.get(Integer.valueOf(polygon.getTag().toString()));


                if (neb.getInMyInterset().equals("1")) {
                    polygon.setFillColor(Color.TRANSPARENT);
                    all_neb.get(Integer.valueOf(polygon.getTag().toString())).setInMyInterset("0");
                } else {
                    polygon.setFillColor(color);
                    all_neb.get(Integer.valueOf(polygon.getTag().toString())).setInMyInterset("1");

                }


            }
        });


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //
            //                                          int[] grantResults)
            ActivityCompat.requestPermissions(MyInterestsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 12);

            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
//                            mMap.clear();

//                            lat = location.getLatitude();
//                            lan = location.getLongitude();
                            System.out.println("location " + location.getLatitude() + "^%^" + location.getLongitude());
                            LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
//                            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


                        }
                    }
                });


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(MyInterestsActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String message = response.getString("message");

                        if (requestType.equals("title_global_cities")) {

//                            String data = response.getString("data");

                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(response.toString());

                            Gson gson = new Gson();
                            AllCityModules allCityModules = gson.fromJson(mJson, AllCityModules.class);

                            mMap.clear();
                            for (int i = 0; i < allCityModules.getData().size(); i++) {
//                                double offset = i / 60d;
//                                lat = lat + offset;
//                                lng = lng + offset;
                                MyItem offsetItem = new MyItem(Double.valueOf(allCityModules.getData().get(i).getCenter().getCoordinates().get(1)), Double.valueOf(allCityModules.getData().get(i).getCenter().getCoordinates().get(0)), allCityModules.getData().get(i).getNameAr() + "", "" + i);
                                clusterManager.addItem(offsetItem);
                            }

                            LatLng latLng = new LatLng(Double.valueOf(allCityModules.getData().get(0).getCenter().getCoordinates().get(1)), Double.valueOf(allCityModules.getData().get(0).getCenter().getCoordinates().get(0)));

                            CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(4.0f).build();
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                            mMap.moveCamera(cameraUpdate);


                            clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
                                @Override
                                public boolean onClusterItemClick(MyItem myItem) {


                                    init_volley();
                                    WebService.loading(MyInterestsActivity.this, true);

                                    try {
                                        VolleyService mVolleyService = new VolleyService(mResultCallback, MyInterestsActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                                        mVolleyService.getDataVolley("title_gloable", WebService.title_gloable + myItem.getSnippet() + "/neb");

                                    } catch (Exception e) {

                                    }
                                    return false;
                                }
                            });

//                            FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(MyInterestsActivity.this);
//
//                            SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//
//                            dbHelper.onUpgrade(db, 1, 1);
//
//
//                            for (int i = 0; i < allCityModules.getData().size(); i++) {
//                                ContentValues values = new ContentValues();
//                                values.put(FeedReaderContract.FeedEntry.COLUMN_name_ar, allCityModules.getData().get(i).getNameAr() + "");
//                                values.put(FeedReaderContract.FeedEntry.COLUMN_city_id, allCityModules.getData().get(i).getCityId() + "");
//                                values.put(FeedReaderContract.FeedEntry.COLUMN_lat, allCityModules.getData().get(i).getCenter().getCoordinates().get(1) + "");
//                                values.put(FeedReaderContract.FeedEntry.COLUMN_long, allCityModules.getData().get(i).getCenter().getCoordinates().get(0) + "");
//
//                                long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
//
//                                System.out.println("newRowId" + newRowId);
//
//                            }


//                            set_locationCity(allCityModules.getData());
                        } else if (requestType.equals("title_gloable")) {


                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(response.toString());

                            Gson gson = new Gson();
                            AlLNebModules alLNebModules = gson.fromJson(mJson, AlLNebModules.class);
                            all_neb = alLNebModules.getData();
                            mMap.clear();
                            for (int i = 0; i < alLNebModules.getData().size(); i++) {


                                if (i == 0) {

                                    LatLng latLng = new LatLng(Double.valueOf(alLNebModules.getData().get(i).getBoundaries().getCoordinates().get(0).get(0).get(1).toString()), Double.valueOf(alLNebModules.getData().get(i).getBoundaries().getCoordinates().get(0).get(0).get(0).toString()));

                                    CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(12.0f).build();
                                    CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                                    mMap.moveCamera(cameraUpdate);

                                }


                                PolygonOptions rectOptions = new PolygonOptions();
                                rectOptions.clickable(true);
                                for (int l = 0; l < alLNebModules.getData().get(i).getBoundaries().getCoordinates().get(0).size(); l++) {

                                    LatLng latLng = new LatLng(Double.valueOf(alLNebModules.getData().get(i).getBoundaries().getCoordinates().get(0).get(l).get(1).toString()), Double.valueOf(alLNebModules.getData().get(i).getBoundaries().getCoordinates().get(0).get(l).get(0).toString()));

                                    System.out.println("%^%^%^" + Double.valueOf(alLNebModules.getData().get(i).getBoundaries().getCoordinates().get(0).get(l).get(1).toString()) + "dffkgflgjflg" + Double.valueOf(alLNebModules.getData().get(i).getBoundaries().getCoordinates().get(0).get(l).get(0).toString()));


                                    rectOptions.add(latLng);

                                }

//                                rectOptions.add(new LatLng(37.35, -122.0),
//                                        new LatLng(37.45, -122.0),
//                                        new LatLng(37.45, -122.2),
//                                        new LatLng(37.35, -122.2),
//                                        new LatLng(37.35, -122.0));
// Get back the mutable Polygon


                                double[] centroid = centroid(alLNebModules.getData().get(i).getBoundaries().getCoordinates());
                                LatLng sydneya = new LatLng(centroid[0], centroid[1]);

                                if (mMap != null) {
                                    mMap.addMarker(new MarkerOptions()
                                            .position(sydneya)
                                            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewText(alLNebModules.getData().get(i).getNameAr() + "")))).setTag("allneb/" + i);

                                }

//                                Random rnd = new Random();
//                                int color = Color.argb(100, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));


                                Polygon polygon = mMap.addPolygon(rectOptions);
                                polygon.setTag(i + "");

//                                polygon.setFillColor(color);
                                polygon.setStrokeColor(Color.parseColor("#FE9457"));
                                polygon.setStrokeWidth(5);
                            }


                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(MyInterestsActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(MyInterestsActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(MyInterestsActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(MyInterestsActivity.this, false);

                WebService.Make_Toast_color(MyInterestsActivity.this, error, "error");


            }
        };


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

    public static double[] centroid(List<List<List<Double>>> points) {
        double[] centroid = {0.0, 0.0};

        for (int i = 0; i < points.get(0).size(); i++) {
            centroid[0] += Double.valueOf(points.get(0).get(i).get(1).toString());
            centroid[1] += Double.valueOf(points.get(0).get(i).get(0).toString());
        }

        int totalPoints = points.get(0).size();
        centroid[0] = centroid[0] / totalPoints;
        centroid[1] = centroid[1] / totalPoints;

        return centroid;
    }

    public void set_locationCity(List<AllCityModules.City> locationCity) {


        if (mMap != null) {
            mMap.clear();
            for (int i = 0; i < 100; i++) {

                LatLng sydneya = new LatLng(Double.valueOf(locationCity.get(i).getCenter().getCoordinates().get(1) + ""), Double.valueOf(locationCity.get(i).getCenter().getCoordinates().get(0) + ""));

                if (mMap != null) {
                    mMap.addMarker(new MarkerOptions()
                            .position(sydneya)
                            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromViewCity(locationCity.get(i).getNameAr() + "", i + "")))).setTag("allcity/" + i);

                }

            }
        }

    }

    private Bitmap getMarkerBitmapFromViewCity(String name, String numbers) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom_all, null);
        TextView markerImageView = customMarkerView.findViewById(R.id.numb);
        TextView number_ = customMarkerView.findViewById(R.id.number_);
        ImageView back_location = customMarkerView.findViewById(R.id.back_location);
//        markerImageView.setImageResource(resId);
        markerImageView.setText(name);


        //offer Market Real
        //city_location
        number_.setText(numbers + "");

//
//        if (type_selected.equals("Real")) {
//
//            back_location.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_path_marker_home1));
//            number_.setTextColor(activity.getResources().getColor(R.color.c1));
//
//        } else if (type_selected.equals("Market")) {
//            back_location.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_path_marker_home2));
//            number_.setTextColor(activity.getResources().getColor(R.color.c2));
////            number_.setText(regionModules_list.get(Integer.valueOf(numbers)).getRequests() + "");
//            number_.setText(city_location_list.get(Integer.valueOf(numbers)).getCount_app_request() + "");
//
//        } else if (type_selected.equals("offer")) {
//            back_location.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_path_marker_home));
//            number_.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
//            number_.setText(city_location_list.get(Integer.valueOf(numbers)).getCount_app_estate() + "");
//
//
//        }


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

    private Bitmap getMarkerBitmapFromViewText(String name) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.marker_map_custom_text, null);
        TextView text = customMarkerView.findViewById(R.id.text);


        text.setText(name);


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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //
            //                                          int[] grantResults)
//            ActivityCompat.requestPermissions(MyInterestsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 12);

            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
//                            mMap.clear();

//                            lat = location.getLatitude();
//                            lan = location.getLongitude();
                            System.out.println("location " + location.getLatitude() + "^%^" + location.getLongitude());
                            LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
//                            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


                        }
                    }
                });
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void on_click_maps_marker() {
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                if (marker.getTag().toString().contains("allcity")) {

                    try {

                        String[] separated = marker.getTag().toString().split("/");

                        String number = separated[1]; // this will contain " they taste good"
//                        LatLng my_location = new LatLng(Double.valueOf(regionModules_list.get(Integer.valueOf(number)).getCenter().getCoordinates().get(1)), Double.valueOf(regionModules_list.get(Integer.valueOf(number)).getCenter().getCoordinates().get(0)));

//                        mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );

                        init_volley();
                        WebService.loading(MyInterestsActivity.this, true);

                        try {
                            VolleyService mVolleyService = new VolleyService(mResultCallback, MyInterestsActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                            mVolleyService.getDataVolley("title_gloable", WebService.title_gloable + number + "/neb");

                        } catch (Exception e) {

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else if (marker.getTag().toString().contains("allneb")) {
                    String[] separated = marker.getTag().toString().split("/");

                    String number = separated[1];


                    return true;


                }
                return false;
            }
        });
    }

    public class CustomClusterRenderer extends DefaultClusterRenderer<MyItem> {

        public CustomClusterRenderer(Context context, GoogleMap map, ClusterManager<MyItem> clusterManager) {
            super(context, map, clusterManager);
        }

        @Override
        protected int getColor(int clusterSize) {
            return Color.BLUE;// Return any color you want here. You can base it on clusterSize.
        }
    }
}
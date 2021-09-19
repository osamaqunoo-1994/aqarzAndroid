package sa.aqarz.NewAqarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sa.aqarz.Activity.MyInterestsActivity;
import sa.aqarz.Adapter.RecyclerView_city;
import sa.aqarz.Adapter.RecyclerView_select_neb;
import sa.aqarz.Modules.AlLNebModules;
import sa.aqarz.Modules.AllCityModules;
import sa.aqarz.NewAqarz.AqqAqarz.AddRentalInstallmentActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class AddIntrestedMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    static IResult mResultCallback;
    private FusedLocationProviderClient fusedLocationClient;
    private List<AlLNebModules.neb> all_neb = new ArrayList<>();
    private final List<AlLNebModules.neb> all_nebSelected = new ArrayList<>();
    RecyclerView all_city;
    LinearLayout open_city_list;
    LinearLayout list_liner_city;

    TextView selected_text_city;

    Button search_ok_btn;

    EditText serch_edt;

    List<AllCityModules.City> dataCities = new ArrayList<>();
    List<Polygon> polygons = new ArrayList<>();

    RecyclerView allneb;

    RecyclerView_select_neb recyclerView_select_neb;

    Button send_to_server;
    ImageView back;
    ImageView search_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_intrested_map);

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map_);
//
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        back = findViewById(R.id.back);
        open_city_list = findViewById(R.id.open_city_list);
        list_liner_city = findViewById(R.id.list_liner_city);
        selected_text_city = findViewById(R.id.selected_text_city);
        search_ok_btn = findViewById(R.id.search_ok_btn);
        serch_edt = findViewById(R.id.serch_edt);
        allneb = findViewById(R.id.allneb);
        send_to_server = findViewById(R.id.send_to_server);
        search_btn = findViewById(R.id.search_btn);
        all_city = findViewById(R.id.all_city);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (back != null) {
                back.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }


    }

    public void on_click() {

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(AddIntrestedMapActivity.this, LinearLayoutManager.VERTICAL, false);
        all_city.setLayoutManager(layoutManager1);

        allneb.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView_select_neb = new RecyclerView_select_neb(AddIntrestedMapActivity.this, all_nebSelected);
        recyclerView_select_neb.addItemClickListener(new RecyclerView_select_neb.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

//                polygons.get(position).setFillColor(Color.TRANSPARENT);
//                all_neb.get(Integer.valueOf(polygons.get(position).getTag().toString())).setInMyInterset("0");


                for (int i = 0; i < all_neb.size(); i++) {

                    if (all_neb.get(i).getDistrictId().equals(all_nebSelected.get(position).getDistrictId())) {

                        AlLNebModules.neb neb = all_neb.get(i);
                        Polygon polygon = polygons.get(i);


                        System.out.println("neb.getInMyInterset()" + neb.getInMyInterset());

                        if (neb.getInMyInterset().equals("1")) {
                            polygon.setFillColor(Color.TRANSPARENT);
                            all_neb.get(Integer.valueOf(polygon.getTag().toString())).setInMyInterset("0");


                        }


                        all_nebSelected.remove(position);
                        recyclerView_select_neb.Refr();

                        break;
                    }


                }


            }
        });
        allneb.setAdapter(recyclerView_select_neb);
        try {
            String idcit = getIntent().getStringExtra("id_city");

            LatLng sydney = new LatLng(26.196634, 43.813666);
//        mMap.addMarker(new MarkerOptions()
//                .position(sydney)
//                .title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            if (idcit.equals("")) {


            } else {
                String name_city = getIntent().getStringExtra("name_city");

                selected_text_city.setText(name_city + "");
                init_volley();
                WebService.loading(AddIntrestedMapActivity.this, true);

                try {
                    VolleyService mVolleyService = new VolleyService(mResultCallback, AddIntrestedMapActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                    mVolleyService.getDataVolley("title_gloable", WebService.title_gloable + idcit + "/neb");

                } catch (Exception e) {

                }
            }


        } catch (Exception e) {

        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        open_city_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (list_liner_city.getVisibility() == View.VISIBLE) {
                    list_liner_city.setVisibility(View.GONE);
                } else {
                    list_liner_city.setVisibility(View.VISIBLE);
                }


            }
        });
        send_to_server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String all_ids = "";

                if (all_nebSelected.size() != 0) {

                    for (int i = 0; i < all_nebSelected.size(); i++) {

                        if (all_ids.equals("")) {
                            all_ids = all_nebSelected.get(i).getDistrictId() + "";
                        } else {
                            all_ids = all_ids + "," + all_nebSelected.get(i).getDistrictId() + "";

                        }


                    }

                    init_volley();
                    WebService.loading(AddIntrestedMapActivity.this, true);

                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("neb_ids", all_ids + "");

                        VolleyService mVolleyService = new VolleyService(mResultCallback, AddIntrestedMapActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                        mVolleyService.postDataVolley("add_neb_interest", WebService.add_neb_interest, jsonObject);
                    } catch (Exception e) {

                    }

                }


            }
        });
        search_ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!serch_edt.getText().toString().equals("")) {
                    init_volley();
                    WebService.loading(AddIntrestedMapActivity.this, true);

                    try {
                        VolleyService mVolleyService = new VolleyService(mResultCallback, AddIntrestedMapActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                        mVolleyService.getDataVolley("title_global_cities", WebService.title_global_cities + "?name=" + serch_edt.getText().toString());
                    } catch (Exception e) {

                    }
                } else {
                    list_liner_city.setVisibility(View.GONE);


                }

            }
        });
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!serch_edt.getText().toString().equals("")) {
                    init_volley();
                    WebService.loading(AddIntrestedMapActivity.this, true);

                    try {
                        VolleyService mVolleyService = new VolleyService(mResultCallback, AddIntrestedMapActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                        mVolleyService.getDataVolley("title_global_cities", WebService.title_global_cities + "?name=" + serch_edt.getText().toString());
                    } catch (Exception e) {

                    }
                } else {
                    list_liner_city.setVisibility(View.GONE);


                }

            }
        });
        serch_edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    init_volley();
                    WebService.loading(AddIntrestedMapActivity.this, true);

                    try {
                        VolleyService mVolleyService = new VolleyService(mResultCallback, AddIntrestedMapActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                        mVolleyService.getDataVolley("title_global_cities", WebService.title_global_cities + "?name=" + serch_edt.getText().toString());
                    } catch (Exception e) {

                    }


                    return true;
                }
                return false;
            }
        });
        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

                AlLNebModules.neb neb = all_neb.get(Integer.valueOf(polygon.getTag().toString()));


                if (neb.getInMyInterset().equals("1")) {
                    polygon.setFillColor(Color.TRANSPARENT);
                    all_neb.get(Integer.valueOf(polygon.getTag().toString())).setInMyInterset("0");


                    for (int i = 0; i < all_nebSelected.size(); i++) {
                        if (all_nebSelected.get(i).getDistrictId().equals(neb.getDistrictId())) {
                            all_nebSelected.remove(i);
                            recyclerView_select_neb.Refr();

                        }
                    }

//                    init_volley();
////                    WebService.loading(MyInterestsActivity.this, true);
//
//                    try {
//
//                        RequestParams requestParams = new RequestParams();
//                        requestParams.put("neb_ids", all_neb.get(Integer.valueOf(polygon.getTag().toString())).getDistrictId() + "");
//                        VolleyService mVolleyService = new VolleyService(mResultCallback, MyInterestsActivity.this);
////            mVolleyService.getDataVolley("user", WebService.user + id + "");
//                        mVolleyService.postDataasync_with_file("remove_neb_interest", WebService.remove_neb_interest, requestParams);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                } else {
                    polygon.setFillColor(color);
                    all_neb.get(Integer.valueOf(polygon.getTag().toString())).setInMyInterset("1");

                    all_nebSelected.add(neb);
                    recyclerView_select_neb.Refr();


                }

                if (all_nebSelected.size() == 0) {
                    send_to_server.setVisibility(View.GONE);
                } else {
                    send_to_server.setVisibility(View.VISIBLE);

                }
            }
        });

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
                        WebService.loading(AddIntrestedMapActivity.this, true);

                        try {
                            VolleyService mVolleyService = new VolleyService(mResultCallback, AddIntrestedMapActivity.this);
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


                    Random rnd = new Random();
                    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

                    AlLNebModules.neb neb = all_neb.get(Integer.valueOf(number));
                    Polygon polygon = polygons.get(Integer.valueOf(number));

                    if (neb.getInMyInterset().equals("1")) {
                        polygon.setFillColor(Color.TRANSPARENT);
                        all_neb.get(Integer.valueOf(polygon.getTag().toString())).setInMyInterset("0");


                        for (int i = 0; i < all_nebSelected.size(); i++) {
                            if (all_nebSelected.get(i).getDistrictId().equals(neb.getDistrictId())) {
                                all_nebSelected.remove(i);
                                recyclerView_select_neb.Refr();

                            }
                        }


                    } else {
                        polygon.setFillColor(color);
                        all_neb.get(Integer.valueOf(polygon.getTag().toString())).setInMyInterset("1");

                        all_nebSelected.add(neb);
                        recyclerView_select_neb.Refr();


                    }
                    if (all_nebSelected.size() == 0) {
                        send_to_server.setVisibility(View.GONE);
                    } else {
                        send_to_server.setVisibility(View.VISIBLE);

                    }

                    return true;


                }
                return false;
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions()
//                .position(sydney)
//                .title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        on_click();

    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String message = response.getString("message");

                        if (requestType.equals("remove_neb_interest")) {


                            System.out.println(")))))");


                        } else if (requestType.equals("add_neb_interest")) {


                            try {

                                BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(AddIntrestedMapActivity.this);
                                View parentView = getLayoutInflater().inflate(R.layout.success_message, null);
                                Button close = parentView.findViewById(R.id.close);
                                TextView text = parentView.findViewById(R.id.text);
                                text.setText(getResources().getString(R.string.neb_Selected_msg));
                                close.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        bottomSheerDialog.dismiss();
                                        finish();
                                    }
                                });
                                bottomSheerDialog.setContentView(parentView);

                                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
                                bottomSheerDialog.show();
                            } catch (Exception e) {

                            }


                        } else if (requestType.equals("title_global_cities")) {

//                            String data = response.getString("data");

                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(response.toString());

                            Gson gson = new Gson();
                            AllCityModules allCityModules = gson.fromJson(mJson, AllCityModules.class);
                            dataCities = allCityModules.getData();

                            if (dataCities.size() != 0) {
                                all_city.setVisibility(View.VISIBLE);
                            } else {
                                all_city.setVisibility(View.GONE);

                            }

                            mMap.clear();
//                            for (int i = 0; i < allCityModules.getData().size(); i++) {
////                                double offset = i / 60d;
////                                lat = lat + offset;
////                                lng = lng + offset;
//                                MyItem offsetItem = new MyItem(Double.valueOf(allCityModules.getData().get(i).getCenter().getCoordinates().get(1)), Double.valueOf(allCityModules.getData().get(i).getCenter().getCoordinates().get(0)), allCityModules.getData().get(i).getNameAr() + "", "" + i);
//                                clusterManager.addItem(offsetItem);
//                            }
//
//                            LatLng latLng = new LatLng(Double.valueOf(allCityModules.getData().get(0).getCenter().getCoordinates().get(1)), Double.valueOf(allCityModules.getData().get(0).getCenter().getCoordinates().get(0)));
//
//                            CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(4.0f).build();
//                            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
//                            mMap.moveCamera(cameraUpdate);
//
//
//                            clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
//                                @Override
//                                public boolean onClusterItemClick(MyItem myItem) {
//
//
//                                    init_volley();
//                                    WebService.loading(MyInterestsActivity.this, true);
//
//                                    try {
//                                        VolleyService mVolleyService = new VolleyService(mResultCallback, MyInterestsActivity.this);
////            mVolleyService.getDataVolley("user", WebService.user + id + "");
//                                        mVolleyService.getDataVolley("title_gloable", WebService.title_gloable + myItem.getSnippet() + "/neb");
//
//                                    } catch (Exception e) {
//
//                                    }
//                                    return false;
//                                }
//                            });

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


                            RecyclerView_city recyclerView_city = new RecyclerView_city(AddIntrestedMapActivity.this, allCityModules.getData());
                            recyclerView_city.addItemClickListener(new RecyclerView_city.ItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                    all_city.setVisibility(View.GONE);
                                    selected_text_city.setText(allCityModules.getData().get(position).getNameAr() + "");
                                    init_volley();
                                    WebService.loading(AddIntrestedMapActivity.this, true);

                                    try {
                                        VolleyService mVolleyService = new VolleyService(mResultCallback, AddIntrestedMapActivity.this);
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
                                        mVolleyService.getDataVolley("title_gloable", WebService.title_gloable + allCityModules.getData().get(position).getCityId() + "/neb");

                                    } catch (Exception e) {

                                    }


                                }
                            });

                            all_city.setAdapter(recyclerView_city);


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

                                Random rnd = new Random();
                                int color = Color.argb(100, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));


                                Polygon polygon = mMap.addPolygon(rectOptions);
                                polygon.setTag(i + "");

                                if (alLNebModules.getData().get(i).getInMyInterset().equals("1")) {
                                    polygon.setFillColor(color);
                                    all_nebSelected.add(alLNebModules.getData().get(i));
                                    recyclerView_select_neb.Refr();
                                }


                                if (all_nebSelected.size() == 0) {
                                    send_to_server.setVisibility(View.GONE);
                                } else {
                                    send_to_server.setVisibility(View.VISIBLE);

                                }


                                polygon.setStrokeColor(Color.parseColor("#FE9457"));
                                polygon.setStrokeWidth(8);

                                polygons.add(polygon);
                            }


                        }
                    }
                    WebService.loading(AddIntrestedMapActivity.this, false);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(AddIntrestedMapActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(AddIntrestedMapActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(AddIntrestedMapActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(AddIntrestedMapActivity.this, false);

                WebService.Make_Toast_color(AddIntrestedMapActivity.this, error, "error");


            }
        };


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
}
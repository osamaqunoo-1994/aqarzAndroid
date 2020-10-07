package aqarz.revival.sa.aqarz.Activity.OprationAqarz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;
import com.orhanobut.hawk.Hawk;
import com.rtchagas.pingplacepicker.PingPlacePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Activity.Auth.LoginActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import aqarz.revival.sa.aqarz.Activity.SplashScreenActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Comfort_in_fragment;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Type_in_order;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_date_select;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_selectImage;
import aqarz.revival.sa.aqarz.Fragment.TypeOrders.type1Fragment;
import aqarz.revival.sa.aqarz.Modules.ComfortModules;
import aqarz.revival.sa.aqarz.Modules.SelectImageModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;
import cz.msebera.android.httpclient.Header;
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

public class AddAqarsActivity extends AppCompatActivity {

    RecyclerView opration_RecyclerView;

    ImageView Lounges_plus, Lounges_minus;
    TextView Lounges_number;

    ImageView room_plus, room_minus;
    TextView room_text;


    ImageView Bathrooms_plus, Bathrooms_minus;
    TextView Bathrooms_text;


    ImageView Boards_plus, Boards_minus;
    TextView Boards_text;


    ImageView Kitchens_plus, Kitchens_minus;
    TextView Kitchens_text;

    ImageView Dining_rooms_plus, Dining_rooms_minus;
    TextView Dining_text;


    TextView For_sale, rent, investment;

    ImageView Instrument_file_text;
    TextView Add_charts_text;
    TextView deluxe, average, normal;
    TextView north, south, east, west;
    TextView unmarried, married;

    ImageView select_image;
    ImageView select_image_s;

    RecyclerView comfort_RecyclerView;
    List<TypeModules> type_list = new ArrayList<>();
    List<ComfortModules> comfort_list = new ArrayList<>();
    List<SelectImageModules> selectIamgeList = new ArrayList<>();
    IResult mResultCallback;

    Button btn_send;
    RecyclerView images_RecyclerView;

    AlertDialog alertDialog;

    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;

    EditText description;
    PlacesClient placesClient;
    ImageView back;

    LinearLayout Instrument_file;
    LinearLayout Add_charts;

    LinearLayout specificationsqares;
    LinearLayout means_comfort;
    LinearLayout date_eqjar;

    RecyclerView type_date;

    List<String> date_list = new ArrayList<>();


    EditText Instrument_number, piece_number, No_planned, Total_area, age_of_the_property, Role_number, Street_view, total_price, price_one_meter, Communication_Officer, contact_number;
    //---------------------------------------------
    String opration_select = "0";
    String Type_work_select = "1";
    String image_planed = "";
    String is_rent = "0";
    String rent_type = "yearly";


    String lat = "";
    String lng = "";


    String finishing_type = "deluxe";
    String interface_ = "north";
    String social_status = "unmarried";


    File instrument_file = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aqars);
        mMapView = (MapView) findViewById(R.id.mapViewxx);

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately

        init();
        select_image_s = findViewById(R.id.select_image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (select_image_s != null) {
                select_image_s.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    int number_Lounges = 0;
    int number_room = 0;
    int number_Bathrooms = 0;
    int number_Boards_plus = 0;
    int number_Kitchens_plus = 0;
    int number_Dining_rooms = 0;


    public void init() {
        back = findViewById(R.id.back);
        images_RecyclerView = findViewById(R.id.images_RecyclerView);
        opration_RecyclerView = findViewById(R.id.opration_RecyclerView);
        select_image = findViewById(R.id.select_image);

        Lounges_plus = findViewById(R.id.Lounges_plus);
        Lounges_minus = findViewById(R.id.Lounges_minus);
        Lounges_number = findViewById(R.id.Lounges_number);

        room_plus = findViewById(R.id.room_plus);
        room_minus = findViewById(R.id.room_minus);
        room_text = findViewById(R.id.room_text);

        Bathrooms_plus = findViewById(R.id.Bathrooms_plus);
        Bathrooms_minus = findViewById(R.id.Bathrooms_minus);
        Bathrooms_text = findViewById(R.id.Bathrooms_text);

        Boards_plus = findViewById(R.id.Boards_plus);
        Boards_minus = findViewById(R.id.Boards_minus);
        Boards_text = findViewById(R.id.Boards_text);

        Kitchens_plus = findViewById(R.id.Kitchens_plus);
        Kitchens_minus = findViewById(R.id.Kitchens_minus);
        Kitchens_text = findViewById(R.id.Kitchens_text);

        Dining_rooms_plus = findViewById(R.id.Dining_rooms_plus);
        Dining_rooms_minus = findViewById(R.id.Dining_rooms_minus);
        Dining_text = findViewById(R.id.Dining_text);

        deluxe = findViewById(R.id.deluxe);
        average = findViewById(R.id.average);
        normal = findViewById(R.id.normal);

        north = findViewById(R.id.north);
        south = findViewById(R.id.south);
        east = findViewById(R.id.east);
        west = findViewById(R.id.west);

        unmarried = findViewById(R.id.unmarried);
        married = findViewById(R.id.married);

        For_sale = findViewById(R.id.For_sale);
        rent = findViewById(R.id.rent);
        investment = findViewById(R.id.investment);
        Instrument_file = findViewById(R.id.Instrument_file);
        specificationsqares = findViewById(R.id.specificationsqares);
        means_comfort = findViewById(R.id.means_comfort);
        Add_charts = findViewById(R.id.Add_charts);
        type_date = findViewById(R.id.type_date);
        Instrument_file_text = findViewById(R.id.Instrument_file_text);


        date_eqjar = findViewById(R.id.date_eqjar);
        comfort_RecyclerView = findViewById(R.id.comfort_RecyclerView);
        btn_send = findViewById(R.id.btn_send);
        description = findViewById(R.id.description);


        Instrument_number = findViewById(R.id.Instrument_number);
        piece_number = findViewById(R.id.piece_number);
        No_planned = findViewById(R.id.No_planned);
        Total_area = findViewById(R.id.Total_area);
        age_of_the_property = findViewById(R.id.age_of_the_property);
        Role_number = findViewById(R.id.Role_number);
        Street_view = findViewById(R.id.Street_view);
        total_price = findViewById(R.id.total_price);
        price_one_meter = findViewById(R.id.price_one_meter);
        Communication_Officer = findViewById(R.id.Communication_Officer);
        contact_number = findViewById(R.id.contact_number);
        Add_charts_text = findViewById(R.id.Add_charts_text);

        //---------------------------------------------------------------------------------------
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //---------------------------------------------------------------------------------------


        comfort_RecyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(AddAqarsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        opration_RecyclerView.setLayoutManager(layoutManager1);

        RecyclerView_All_type_in_fragment recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_fragment(AddAqarsActivity.this, type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_fragment.ItemClickListener() {
            @Override
            public void onItemClick(int position) {


                opration_select = type_list.get(position).getId().toString() + "";


                if (opration_select.toString().equals("1")) {//فيلا

                    specificationsqares.setVisibility(View.VISIBLE);
                    means_comfort.setVisibility(View.VISIBLE);


                } else if (opration_select.toString().equals("2")) {//ارض


                    specificationsqares.setVisibility(View.GONE);
                    means_comfort.setVisibility(View.GONE);


                } else if (opration_select.toString().equals("3")) {//شقه
                    specificationsqares.setVisibility(View.VISIBLE);
                    means_comfort.setVisibility(View.VISIBLE);


                }


            }
        });
        opration_RecyclerView.setAdapter(recyclerView_all_type_in_fragment);


        LinearLayoutManager layoutManagem
                = new LinearLayoutManager(AddAqarsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        images_RecyclerView.setLayoutManager(layoutManagem);


        LinearLayoutManager layoutManags
                = new LinearLayoutManager(AddAqarsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        type_date.setLayoutManager(layoutManags);
        date_list.add(getResources().getString(R.string.yearly));
        date_list.add(getResources().getString(R.string.months_6));
        date_list.add(getResources().getString(R.string.months_3));
        date_list.add(getResources().getString(R.string.Monthly));
        date_list.add(getResources().getString(R.string.daily));
        RecyclerView_date_select recyclerView_date_select = new RecyclerView_date_select(AddAqarsActivity.this, date_list);

        recyclerView_date_select.addItemClickListener(new RecyclerView_date_select.ItemClickListener() {
            @Override
            public void onItemClick(int position) {


                switch (position) {
                    case 0:
                        rent_type = "yearly";

                        break;
                    case 1:
                        rent_type = "monthly";

                        break;
                    case 2:
                        rent_type = "monthly";

                        break;
                    case 3:
                        rent_type = "monthly";

                        break;
                    case 4:
                        rent_type = "daily";

                        break;


                }


            }
        });

        type_date.setAdapter(recyclerView_date_select);
//-------------------------------------------------------------------------------------------------


        try {
            MapsInitializer.initialize(AddAqarsActivity.this.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

//                googleMap.getUiSettings().setRotateGesturesEnabled(true);


                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (ContextCompat.checkSelfPermission(AddAqarsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                                // No explanation needed, we can request the permission.

                                ActivityCompat.requestPermissions(AddAqarsActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        11);

                            } else {

                                PingPlacePicker.IntentBuilder builder = new PingPlacePicker.IntentBuilder();
                                builder.setAndroidApiKey("AIzaSyDWtJ0cgqHXouF5I5YdPjLzztWQzXM4zQc")
                                        .setMapsApiKey("AIzaSyDWtJ0cgqHXouF5I5YdPjLzztWQzXM4zQc");

                                // If you want to set a initial location rather then the current device location.
                                // NOTE: enable_nearby_search MUST be true.
                                // builder.setLatLng(new LatLng(37.4219999, -122.0862462))

                                try {
                                    Intent placeIntent = builder.build(AddAqarsActivity.this);
                                    startActivityForResult(placeIntent, 11);
                                } catch (Exception ex) {
                                    // Google Play services is not available...
                                }
                            }
                        } else {


                        }
                    }
                });

            }
        });
        Places.initialize(AddAqarsActivity.this, "AIzaSyA6E2L_Feqp6HMD85eQ1RP06WnykHJj7Mc");
        placesClient = Places.createClient(AddAqarsActivity.this);


//-------------------------------------------------------------------------------------------------

        Lounges_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_Lounges = Integer.valueOf(Lounges_number.getText().toString());


                number_Lounges++;


                Lounges_number.setText(number_Lounges + "");

                if (number_Lounges > 0) {
                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));

                }


            }
        });
        Lounges_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_Lounges = Integer.valueOf(Lounges_number.getText().toString());


                if (number_Lounges == 0) {

                } else {
                    number_Lounges--;

                }

                if (number_Lounges > 0) {
                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));

                }
                Lounges_number.setText(number_Lounges + "");


            }
        });


//-------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------


        room_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_room = Integer.valueOf(room_text.getText().toString());


                number_room++;


                if (number_room > 0) {
                    room_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    room_text.setBackgroundColor(getResources().getColor(R.color.white));

                }
                room_text.setText(number_room + "");


            }
        });
        room_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_room = Integer.valueOf(room_text.getText().toString());


                if (number_room == 0) {

                } else {
                    number_room--;

                }

                if (number_room > 0) {
                    room_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    room_text.setBackgroundColor(getResources().getColor(R.color.white));

                }
                room_text.setText(number_room + "");


            }
        });


//-------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------


        Bathrooms_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_Bathrooms = Integer.valueOf(Bathrooms_text.getText().toString());


                number_Bathrooms++;


                if (number_Bathrooms > 0) {
                    Bathrooms_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    Bathrooms_text.setBackgroundColor(getResources().getColor(R.color.white));

                }
                Bathrooms_text.setText(number_Bathrooms + "");


            }
        });
        Bathrooms_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_Bathrooms = Integer.valueOf(Bathrooms_text.getText().toString());

                if (number_Bathrooms == 0) {

                } else {
                    number_Bathrooms--;

                }

                if (number_Bathrooms > 0) {
                    Bathrooms_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    Bathrooms_text.setBackgroundColor(getResources().getColor(R.color.white));

                }
                Bathrooms_text.setText(number_Bathrooms + "");


            }
        });


//-------------------------------------------------------------------------------------------------


//-------------------------------------------------------------------------------------------------


        Boards_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_Boards_plus = Integer.valueOf(Boards_text.getText().toString());


                number_Boards_plus++;


                if (number_Boards_plus > 0) {
                    Boards_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    Boards_text.setBackgroundColor(getResources().getColor(R.color.white));

                }
                Boards_text.setText(number_Boards_plus + "");


            }
        });
        Boards_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_Boards_plus = Integer.valueOf(Boards_text.getText().toString());


                if (number_Boards_plus == 0) {

                } else {
                    number_Boards_plus--;

                }

                if (number_Boards_plus > 0) {
                    Boards_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    Boards_text.setBackgroundColor(getResources().getColor(R.color.white));

                }
                Boards_text.setText(number_Boards_plus + "");


            }
        });


//-------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------


        Kitchens_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_Kitchens_plus = Integer.valueOf(Kitchens_text.getText().toString());


                number_Kitchens_plus++;


                if (number_Kitchens_plus > 0) {
                    Kitchens_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    Kitchens_text.setBackgroundColor(getResources().getColor(R.color.white));

                }
                Kitchens_text.setText(number_Kitchens_plus + "");


            }
        });
        Kitchens_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_Kitchens_plus = Integer.valueOf(Kitchens_text.getText().toString());


                if (number_Kitchens_plus == 0) {

                } else {
                    number_Kitchens_plus--;

                }

                if (number_Kitchens_plus > 0) {
                    Kitchens_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    Kitchens_text.setBackgroundColor(getResources().getColor(R.color.white));

                }
                Kitchens_text.setText(number_Kitchens_plus + "");


            }
        });


//-------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------


        Dining_rooms_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_Dining_rooms = Integer.valueOf(Dining_text.getText().toString());


                number_Dining_rooms++;


                if (number_Dining_rooms > 0) {
                    Dining_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    Dining_text.setBackgroundColor(getResources().getColor(R.color.white));

                }
                Dining_text.setText(number_Dining_rooms + "");


            }
        });
        Dining_rooms_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_Dining_rooms = Integer.valueOf(Dining_text.getText().toString());


                if (number_Dining_rooms == 0) {

                } else {
                    number_Dining_rooms--;

                }

                if (number_Dining_rooms > 0) {
                    Dining_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    Dining_text.setBackgroundColor(getResources().getColor(R.color.white));

                }
                Dining_text.setText(number_Dining_rooms + "");


            }
        });


//-------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------


        unmarried.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unmarried.setBackground(getResources().getDrawable(R.drawable.button_login));

                unmarried.setTextColor(getResources().getColor(R.color.white));


                married.setBackground(getResources().getDrawable(R.drawable.search_background));

                married.setTextColor(getResources().getColor(R.color.textColor));
                social_status = "unmarried";

            }
        });
        married.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                married.setBackground(getResources().getDrawable(R.drawable.button_login));

                married.setTextColor(getResources().getColor(R.color.white));

                unmarried.setBackground(getResources().getDrawable(R.drawable.search_background));


                unmarried.setTextColor(getResources().getColor(R.color.textColor));
                social_status = "married";

            }
        });


//-------------------------------------------------------------------------------------------------
        deluxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deluxe.setBackground(getResources().getDrawable(R.drawable.button_login));

                deluxe.setTextColor(getResources().getColor(R.color.white));

                average.setBackground(getResources().getDrawable(R.drawable.search_background));


                average.setTextColor(getResources().getColor(R.color.textColor));

                normal.setBackground(getResources().getDrawable(R.drawable.search_background));


                normal.setTextColor(getResources().getColor(R.color.textColor));

                finishing_type = "deluxe";
            }
        });
        average.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                average.setBackground(getResources().getDrawable(R.drawable.button_login));

                average.setTextColor(getResources().getColor(R.color.white));


                deluxe.setBackground(getResources().getDrawable(R.drawable.search_background));

                deluxe.setTextColor(getResources().getColor(R.color.textColor));


                normal.setBackground(getResources().getDrawable(R.drawable.search_background));

                normal.setTextColor(getResources().getColor(R.color.textColor));

                finishing_type = "average";
            }
        });
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normal.setBackground(getResources().getDrawable(R.drawable.button_login));
                normal.setTextColor(getResources().getColor(R.color.white));
                average.setBackground(getResources().getDrawable(R.drawable.search_background));
                average.setTextColor(getResources().getColor(R.color.textColor));
                deluxe.setBackground(getResources().getDrawable(R.drawable.search_background));

                deluxe.setTextColor(getResources().getColor(R.color.textColor));

                finishing_type = "normal";
            }
        });


//-------------------------------------------------------------------------------------------------
        north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                north.setBackground(getResources().getDrawable(R.drawable.button_login));

                north.setTextColor(getResources().getColor(R.color.white));


                west.setBackground(getResources().getDrawable(R.drawable.search_background));

                west.setTextColor(getResources().getColor(R.color.textColor));


                south.setBackground(getResources().getDrawable(R.drawable.search_background));

                south.setTextColor(getResources().getColor(R.color.textColor));


                east.setBackground(getResources().getDrawable(R.drawable.search_background));

                east.setTextColor(getResources().getColor(R.color.textColor));

                interface_ = "north";
            }
        });
        south.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                south.setBackground(getResources().getDrawable(R.drawable.button_login));

                south.setTextColor(getResources().getColor(R.color.white));


                north.setBackground(getResources().getDrawable(R.drawable.search_background));

                north.setTextColor(getResources().getColor(R.color.textColor));


                east.setBackground(getResources().getDrawable(R.drawable.search_background));

                east.setTextColor(getResources().getColor(R.color.textColor));


                west.setBackground(getResources().getDrawable(R.drawable.search_background));

                west.setTextColor(getResources().getColor(R.color.textColor));

                interface_ = "south";
            }
        });
        east.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                east.setBackground(getResources().getDrawable(R.drawable.button_login));

                east.setTextColor(getResources().getColor(R.color.white));


                north.setBackground(getResources().getDrawable(R.drawable.search_background));

                north.setTextColor(getResources().getColor(R.color.textColor));


                south.setBackground(getResources().getDrawable(R.drawable.search_background));

                south.setTextColor(getResources().getColor(R.color.textColor));


                west.setBackground(getResources().getDrawable(R.drawable.search_background));

                west.setTextColor(getResources().getColor(R.color.textColor));

                interface_ = "east";
            }
        });
        west.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                west.setBackground(getResources().getDrawable(R.drawable.button_login));

                west.setTextColor(getResources().getColor(R.color.white));

                north.setBackground(getResources().getDrawable(R.drawable.search_background));

                north.setTextColor(getResources().getColor(R.color.textColor));

                south.setBackground(getResources().getDrawable(R.drawable.search_background));

                south.setTextColor(getResources().getColor(R.color.textColor));

                east.setBackground(getResources().getDrawable(R.drawable.search_background));

                east.setTextColor(getResources().getColor(R.color.textColor));

                interface_ = "west";

            }
        });


//-------------------------------------------------------------------------------------------------
        For_sale.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                For_sale.setBackground(getResources().getDrawable(R.drawable.button_login));

                For_sale.setTextColor(getResources().getColor(R.color.white));


                rent.setBackground(getResources().getDrawable(R.drawable.search_background));

                rent.setTextColor(getResources().getColor(R.color.textColor));


                investment.setBackground(getResources().getDrawable(R.drawable.search_background));

                investment.setTextColor(getResources().getColor(R.color.textColor));


                rent.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
                investment.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
                For_sale.getCompoundDrawables()[0].setTint(Color.WHITE);

                Type_work_select = "1";

                date_eqjar.setVisibility(View.GONE);
                is_rent = "0";
            }
        });
        rent.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                rent.setBackground(getResources().getDrawable(R.drawable.button_login));

                rent.setTextColor(getResources().getColor(R.color.white));


                For_sale.setBackground(getResources().getDrawable(R.drawable.search_background));

                For_sale.setTextColor(getResources().getColor(R.color.textColor));


                investment.setBackground(getResources().getDrawable(R.drawable.search_background));

                investment.setTextColor(getResources().getColor(R.color.textColor));


                For_sale.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
                investment.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
                rent.getCompoundDrawables()[0].setTint(Color.WHITE);

                Type_work_select = "2";

                date_eqjar.setVisibility(View.VISIBLE);
                is_rent = "1";

            }
        });
        investment.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                investment.setBackground(getResources().getDrawable(R.drawable.button_login));

                investment.setTextColor(getResources().getColor(R.color.white));


                For_sale.setBackground(getResources().getDrawable(R.drawable.search_background));

                For_sale.setTextColor(getResources().getColor(R.color.textColor));


                rent.setBackground(getResources().getDrawable(R.drawable.search_background));

                rent.setTextColor(getResources().getColor(R.color.textColor));


                For_sale.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
                rent.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
                investment.getCompoundDrawables()[0].setTint(Color.WHITE);

                Type_work_select = "3";
                date_eqjar.setVisibility(View.GONE);
                is_rent = "0";

            }
        });


        //-----------------------------------------------------------------------------------------
        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, AddAqarsActivity.this);

        mVolleyService.getDataVolley("comfort", WebService.comfort);

        WebService.loading(AddAqarsActivity.this, false);

        //-----------------------------------------------------------------------------------------


        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(AddAqarsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(AddAqarsActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                11);

                    } else {

                        ImagePicker.with(AddAqarsActivity.this)
                                .setFolderMode(true)
                                .setFolderTitle("Album")

                                .setDirectoryName("Image Picker")
                                .setMultipleMode(false)
                                .setShowNumberIndicator(true)
                                .setMaxSize(1)
                                .setLimitMessage("You can select one image")
                                .setRequestCode(1213)
                                .start();
                    }
                } else {

                    ImagePicker.with(AddAqarsActivity.this)
                            .setFolderMode(true)
                            .setFolderTitle("Album")
                            .setDirectoryName("Image Picker")
                            .setMultipleMode(false)
                            .setShowNumberIndicator(true)
                            .setMaxSize(1)
                            .setLimitMessage("You can select one image")
                            .setRequestCode(1213)
                            .start();

                }


            }
        });

        Instrument_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(AddAqarsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(AddAqarsActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                12);

                    } else {


                        ImagePicker.with(AddAqarsActivity.this)
                                .setFolderMode(true)
                                .setFolderTitle("Album")

                                .setDirectoryName("Image Picker")
                                .setMultipleMode(false)
                                .setShowNumberIndicator(true)
                                .setMaxSize(1)
                                .setLimitMessage("You can select one image")

                                .setRequestCode(1217)
                                .start();
                    }
                } else {

                    ImagePicker.with(AddAqarsActivity.this)
                            .setFolderMode(true)
                            .setFolderTitle("Album")

                            .setDirectoryName("Image Picker")
                            .setMultipleMode(false)
                            .setShowNumberIndicator(true)
                            .setMaxSize(1)
                            .setLimitMessage("You can select one image")

                            .setRequestCode(1217)
                            .start();

                }


            }
        });


        Add_charts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(AddAqarsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(AddAqarsActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                13);

                    } else {


                        ImagePicker.with(AddAqarsActivity.this)
                                .setFolderMode(true)
                                .setFolderTitle("Album")

                                .setDirectoryName("Image Picker")
                                .setMultipleMode(false)
                                .setShowNumberIndicator(true)
                                .setMaxSize(1)
                                .setLimitMessage("You can select one image")

                                .setRequestCode(20)
                                .start();
                    }
                } else {

                    ImagePicker.with(AddAqarsActivity.this)
                            .setFolderMode(true)
                            .setFolderTitle("Album")

                            .setDirectoryName("Image Picker")
                            .setMultipleMode(false)
                            .setShowNumberIndicator(true)
                            .setMaxSize(1)
                            .setLimitMessage("You can select one image")

                            .setRequestCode(20)
                            .start();

                }


            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Instrument_number.getText().toString().equals("") |
                        piece_number.getText().toString().equals("") |
                        No_planned.getText().toString().equals("") |
                        Total_area.getText().toString().equals("") |
                        age_of_the_property.getText().toString().equals("") |
                        Role_number.getText().toString().equals("") |
                        Street_view.getText().toString().equals("") |
                        total_price.getText().toString().equals("") |
                        price_one_meter.getText().toString().equals("") |
                        Communication_Officer.getText().toString().equals("") |
                        lat.toString().equals("") |
                        instrument_file == null |
                        image_planed == null |
                        selectIamgeList.size() == 0 |
                        contact_number.getText().toString().equals("")

                ) {


                    WebService.Make_Toast_color(AddAqarsActivity.this, getResources().getString(R.string.fillallfileds) + "", "error");


                } else {


                    RequestParams sendObj = new RequestParams();

                    try {

                        sendObj.put("operation_type_id", opration_select);
                        sendObj.put("estate_type_id", Type_work_select);
                        sendObj.put("instrument_number", Instrument_number.getText().toString());
                        if (instrument_file != null) {

                            sendObj.put("instrument_file", instrument_file);


                        }
                        sendObj.put("pace_number", piece_number.getText().toString());
                        sendObj.put("planned_number", No_planned.getText().toString());
                        sendObj.put("total_area", Total_area.getText().toString());
                        sendObj.put("estate_age", age_of_the_property.getText().toString());
                        sendObj.put("floor_number", Role_number.getText().toString());
                        sendObj.put("street_view", Street_view.getText().toString());
                        sendObj.put("total_price", total_price.getText().toString());
                        sendObj.put("meter_price", price_one_meter.getText().toString());
                        sendObj.put("owner_name", Communication_Officer.getText().toString());
                        sendObj.put("owner_mobile", contact_number.getText().toString());
                        sendObj.put("lounges_number", number_Lounges + "");
                        sendObj.put("rooms_number", number_room + "");
                        sendObj.put("bathrooms_number", number_Bathrooms + "");
                        sendObj.put("boards_number", number_Boards_plus + "");
                        sendObj.put("kitchen_number", number_Kitchens_plus + "");
                        sendObj.put("dining_rooms_number", number_Dining_rooms + "");
                        sendObj.put("finishing_type", finishing_type);
                        sendObj.put("interface", interface_);
                        sendObj.put("social_status", social_status);
                        sendObj.put("lat", lat);
                        sendObj.put("lan", lng);
                        sendObj.put("is_rent", is_rent);//1 = yes or 0 = no
                        if (is_rent.equals("1")) {
                            sendObj.put("rent_type", rent_type);//'daily','monthly','yearly'

                        }


                        String attachment_planned = "";
                        for (int i = 0; i < selectIamgeList.size(); i++) {

                            if (attachment_planned.equals("")) {
                                attachment_planned = selectIamgeList.get(i).getId() + "";
                            } else {
                                attachment_planned = "," + selectIamgeList.get(i).getId() + "";

                            }

                        }
                        sendObj.put("attachment_estate", attachment_planned + "");


                        String comfort_list_ = "";

                        for (int i = 0; i < comfort_list.size(); i++) {

                            if (comfort_list.get(i).get_is_selected()) {
                                if (comfort_list_.equals("")) {
                                    comfort_list_ = comfort_list.get(i).getId() + "";
                                } else {
                                    comfort_list_ = comfort_list.get(i).getId() + "," + comfort_list.get(i).getId() + "";

                                }

                            }


                        }

                        sendObj.put("estate_comforts", comfort_list_ + "");
                        System.out.println("comfort_list_" + comfort_list_);

                        sendObj.put("attachment_planned", image_planed);
                        sendObj.put("note", description.getText().toString());


                        System.out.println(sendObj.toString());


                        AddAqersAsyncTask(sendObj);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }


                if (opration_select.toString().equals("1")) {//فيلا


                } else if (opration_select.toString().equals("2")) {//ارض


                } else if (opration_select.toString().equals("3")) {//شقه


                }

            }
        });

    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {


                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(AddAqarsActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                if (requestType.equals("SendOrder")) {


                } else {


                    try {
                        boolean status = response.getBoolean("status");
                        if (status) {
                            String data = response.getString("data");

                            JSONArray jsonArray = new JSONArray(data);

                            comfort_list.clear();

                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();

                                ComfortModules Store_M = gson.fromJson(mJson, ComfortModules.class);
                                comfort_list.add(Store_M);
                            }

                            RecyclerView_All_Comfort_in_fragment recyclerView_all_comfort_in_fragment = new RecyclerView_All_Comfort_in_fragment(AddAqarsActivity.this, comfort_list);

                            recyclerView_all_comfort_in_fragment.addItemClickListener(new RecyclerView_All_Comfort_in_fragment.ItemClickListener() {
                                @Override
                                public void onItemClick(int position) {


                                    if (comfort_list.get(position).get_is_selected()) {

                                        comfort_list.get(position).setIs_selected(false);

                                    } else {

                                        comfort_list.get(position).setIs_selected(true);

                                    }


                                    System.out.println("%%%%%%%%%%%%%5" + comfort_list.get(position).get_is_selected());


                                }
                            });


                            comfort_RecyclerView.setAdapter(recyclerView_all_comfort_in_fragment);
                        } else {
                            String message = response.getString("message");

                            WebService.Make_Toast_color(AddAqarsActivity.this, message, "error");
                        }


                    } catch (Exception e) {

                    }

                }
            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.toString());

                try {

                    NetworkResponse response = error.networkResponse;
                    String json = new String(response.data);


                } catch (Exception e) {

                }


                WebService.loading(AddAqarsActivity.this, false);
                WebService.Make_Toast_color(AddAqarsActivity.this, error.getMessage(), "error");


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 11)) {
            Place place = PingPlacePicker.getPlace(data);
            if (place != null) {
                Toast.makeText(AddAqarsActivity.this, "You selected the place: " + place.getName(), Toast.LENGTH_SHORT).show();


                LatLng sydney = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                googleMap.addMarker(new MarkerOptions()
                        .position(sydney)
                        .title("Marker"));

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
                // Zoom in, animating the camera.
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);


                lat = "" + place.getLatLng().latitude;
                lng = "" + place.getLatLng().longitude;


            }
        }

        if (requestCode == 00 && resultCode == Activity.RESULT_OK) {

            if (data != null) {
                String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
                Bitmap selectedImage = BitmapFactory.decodeFile(filePath);

            }


//            image_profile.setImageBitmap(selectedImage);
//
//            //            file_path = filePath;
//            image_file_file = new File(filePath);
//
//


//            try {
//
//                RequestParams requestParams = new RequestParams();
//
//                requestParams.put("logo", image_file_file);
//
//
//                Upload_image(requestParams);
//            } catch (Exception e) {
//
//            }


        }

        if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 1213)) {


            if (data != null) {

                ArrayList<Image> images = ImagePicker.getImages(data);
//            Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, images.get(0).getId()+"");


                String filePath = images.get(0).getPath().toString();
                Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);


                File file_image_profile = new File(filePath);

                try {

                    RequestParams requestParams = new RequestParams();

                    requestParams.put("photo", file_image_profile);


                    Upload_image(requestParams, selectedImagea);
                } catch (Exception e) {

                }
//            sendChatMsg_file(first_image_file);


            }
            if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 1217)) {


                if (data != null) {

                    ArrayList<Image> images = ImagePicker.getImages(data);
//            Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, images.get(0).getId()+"");


                    String filePath = images.get(0).getPath().toString();
                    Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);
                    Instrument_file_text.setImageBitmap(selectedImagea);

                    File file_image_profile = new File(filePath);

                    try {
//                        Instrument_file_text.setText(getResources().getString(R.string.upload_file_success));
                        instrument_file = file_image_profile;

//                RequestParams requestParams = new RequestParams();
//
//                requestParams.put("photo", file_image_profile);
//
//
//                Upload_image(requestParams, selectedImagea);
                    } catch (Exception e) {

                    }
//            sendChatMsg_file(first_image_file);


                }


            }

            if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 20)) {


                if (data != null) {
                    ArrayList<Image> images = ImagePicker.getImages(data);
//            Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, images.get(0).getId()+"");


                    String filePath = images.get(0).getPath().toString();
                    Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);


                    File file_image_profile = new File(filePath);

                    try {
                        Add_charts_text.setText(getResources().getString(R.string.upload_file_success));

//                RequestParams requestParams = new RequestParams();
//
//                requestParams.put("photo", file_image_profile);
//
//
//                Upload_image(requestParams, selectedImagea);
                    } catch (Exception e) {

                    }
                    try {

                        RequestParams requestParams = new RequestParams();

                        requestParams.put("photo", file_image_profile);

                        Upload_image_planed(requestParams, selectedImagea);
                    } catch (Exception e) {

                    }
//            sendChatMsg_file(first_image_file);


                }

            }
        }


    }


    public void Upload_image(RequestParams requestParams, Bitmap selectedImage) {


        AsyncHttpClient client = new AsyncHttpClient();
        String BASE_URL = WebService.addImg;
        WebService.loading(AddAqarsActivity.this, true);


        final int DEFAULT_TIMEOUT = 20 * 1000;

        client.setTimeout(DEFAULT_TIMEOUT);
        WebService.Header_Async(client, true);

        client.post(BASE_URL, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                WebService.loading(AddAqarsActivity.this, false);
//                Addproduct_next_btn.setClickable(true);

                System.out.println("dfsdfd" + responseBody.toString());

                try {

                    String status = responseBody.getString("status");
//
//                    String message = responseBody.getString("message");
//                    String code = responseBody.getString("code");
                    String message = responseBody.getString("message");

                    if (status.equals("true")) {

                        String data = responseBody.getString("data");


                        selectIamgeList.add(new SelectImageModules(Integer.valueOf(data), selectedImage));


                        images_RecyclerView.setAdapter(new RecyclerView_selectImage(AddAqarsActivity.this, selectIamgeList));


                        WebService.Make_Toast_color(AddAqarsActivity.this, message, "success");

                    } else {


                        WebService.Make_Toast(AddAqarsActivity.this, message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
//                    noorder.setVisibility(View.VISIBLE);
//
//                    WebService.loading(MainActivity.this, false);

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                System.out.println("responseString" + responseString);
                WebService.loading(AddAqarsActivity.this, false);


                WebService.loading(AddAqarsActivity.this, false);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                try {
                    System.out.println("responseString" + errorResponse.toString());

                    WebService.loading(AddAqarsActivity.this, false);

                } catch (Exception e) {

                }

            }

            @Override
            public void onUserException(Throwable error) {
                // super.onUserException(error);
//                Log.d("ttt", "onFailure: jhjj" + error.getMessage());


            }


            @Override
            public void onProgress(long bytesWritten, long totalSize) {


                try {
//                    int po = (int) (totalSize / 500);
//
//                    int cc = (int) bytesWritten / po;
//                    int progressPercentage = (int) (250 * bytesWritten / totalSize);
//
//
//                    System.out.println(totalSize + "###" + po + "%%" + cc);
//                    kProgressHUDx.setProgress(progressPercentage);
//

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }

    public void Upload_image_planed(RequestParams requestParams, Bitmap selectedImage) {


        AsyncHttpClient client = new AsyncHttpClient();
        String BASE_URL = WebService.addImg_planned;
        WebService.loading(AddAqarsActivity.this, true);


        final int DEFAULT_TIMEOUT = 20 * 1000;

        client.setTimeout(DEFAULT_TIMEOUT);
        WebService.Header_Async(client, true);

        client.post(BASE_URL, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                WebService.loading(AddAqarsActivity.this, false);
//                Addproduct_next_btn.setClickable(true);

                System.out.println("dfsdfd" + responseBody.toString());

                try {

                    String status = responseBody.getString("status");
//
//                    String message = responseBody.getString("message");
//                    String code = responseBody.getString("code");
                    String message = responseBody.getString("message");

                    if (status.equals("true")) {

                        String data = responseBody.getString("data");


                        WebService.Make_Toast_color(AddAqarsActivity.this, message, "success");

                    } else {


                        WebService.Make_Toast(AddAqarsActivity.this, message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
//                    noorder.setVisibility(View.VISIBLE);
//
//                    WebService.loading(MainActivity.this, false);

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                System.out.println("responseString" + responseString);
                WebService.loading(AddAqarsActivity.this, false);


                WebService.loading(AddAqarsActivity.this, false);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                try {
                    System.out.println("responseString" + errorResponse.toString());

                    WebService.loading(AddAqarsActivity.this, false);

                } catch (Exception e) {

                }

            }

            @Override
            public void onUserException(Throwable error) {
                // super.onUserException(error);
//                Log.d("ttt", "onFailure: jhjj" + error.getMessage());


            }


            @Override
            public void onProgress(long bytesWritten, long totalSize) {


                try {
//                    int po = (int) (totalSize / 500);
//
//                    int cc = (int) bytesWritten / po;
//                    int progressPercentage = (int) (250 * bytesWritten / totalSize);
//
//
//                    System.out.println(totalSize + "###" + po + "%%" + cc);
//                    kProgressHUDx.setProgress(progressPercentage);
//

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }

    public void AddAqersAsyncTask(RequestParams requestParams) {


        AsyncHttpClient client = new AsyncHttpClient();
        String BASE_URL = WebService.addestate;
        WebService.loading(AddAqarsActivity.this, true);


        final int DEFAULT_TIMEOUT = 20 * 1000;

        client.setTimeout(DEFAULT_TIMEOUT);
        WebService.Header_Async(client, true);

        client.post(BASE_URL, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                WebService.loading(AddAqarsActivity.this, false);
//                Addproduct_next_btn.setClickable(true);

                System.out.println("dfsdfd" + responseBody.toString());

                try {
                    WebService.loading(AddAqarsActivity.this, false);

                    String status = responseBody.getString("status");
//
//                    String message = responseBody.getString("message");
//                    String code = responseBody.getString("code");
                    String message = responseBody.getString("message");

                    if (status.equals("true")) {

                        WebService.loading(AddAqarsActivity.this, false);

//                        WebService.Make_Toast_color(AddAqarsActivity.this, message, "success");


                        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        final View popupView = layoutInflater.inflate(R.layout.card_add_aqares_success, null);


                        final AlertDialog.Builder builder = new AlertDialog.Builder(AddAqarsActivity.this);

//            alertDialog_country =
                        builder.setView(popupView);


                        alertDialog = builder.show();

                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    } else {


                        WebService.Make_Toast(AddAqarsActivity.this, message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
//                    noorder.setVisibility(View.VISIBLE);
//
//                    WebService.loading(MainActivity.this, false);

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                System.out.println("responseString" + responseString);
                WebService.loading(AddAqarsActivity.this, false);


                WebService.loading(AddAqarsActivity.this, false);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                try {
                    System.out.println("responseString" + errorResponse.toString());

                    WebService.loading(AddAqarsActivity.this, false);

                } catch (Exception e) {

                }

            }

            @Override
            public void onUserException(Throwable error) {
                // super.onUserException(error);
//                Log.d("ttt", "onFailure: jhjj" + error.getMessage());


            }


            @Override
            public void onProgress(long bytesWritten, long totalSize) {


                try {
//                    int po = (int) (totalSize / 500);
//
//                    int cc = (int) bytesWritten / po;
//                    int progressPercentage = (int) (250 * bytesWritten / totalSize);
//
//
//                    System.out.println(totalSize + "###" + po + "%%" + cc);
//                    kProgressHUDx.setProgress(progressPercentage);
//

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }

}
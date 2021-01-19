package sa.aqarz.Activity.OprationAqarz;

import androidx.annotation.NonNull;
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
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;
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
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.OprationNew.FinanceActivity;
import sa.aqarz.Activity.SelectLocationActivity;
import sa.aqarz.Activity.SplashScreenActivity;
import sa.aqarz.Adapter.RecyclerView_All_Comfort_in_fragment;
import sa.aqarz.Adapter.RecyclerView_All_Type_in_order;
import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import sa.aqarz.Adapter.RecyclerView_date_select;
import sa.aqarz.Adapter.RecyclerView_selectImage;
import sa.aqarz.Dialog.BottomSheetDialogFragment_SelectCity;
import sa.aqarz.Dialog.BottomSheetDialogFragment_SelectNeighborhoods;
import sa.aqarz.Fragment.TypeOrders.type1Fragment;
import sa.aqarz.Modules.ComfortModules;
import sa.aqarz.Modules.SelectImageModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.GpsTracker;
import sa.aqarz.Settings.NumberTextWatcher;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;
import cz.msebera.android.httpclient.Header;
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

public class AddAqarsActivity extends AppCompatActivity {

    RecyclerView opration_RecyclerView;
    String attachment_planned = "";

    ImageView Lounges_plus, Lounges_minus;
    TextView Lounges_number;

    ImageView room_plus, room_minus;
    TextView room_text;
    TextView more_comfort;
    boolean is_place = false;
    ArrayList<Image> images = new ArrayList<>();
    ImageView Bathrooms_plus, Bathrooms_minus;
    TextView Bathrooms_text;


    ImageView Boards_plus, Boards_minus;
    TextView Boards_text;


    ImageView Kitchens_plus, Kitchens_minus;
    TextView Kitchens_text;

    ImageView Dining_rooms_plus, Dining_rooms_minus;
    TextView Dining_text;


    TextView For_sale, rent, investment;

    ImageView Instrument_file;
    TextView Add_charts_text;
    TextView deluxe, average, normal;
    TextView north, south, east, west;
    String interface_north = "";
    String interface_south = "";
    String interface_east = "";
    String interface_west = "";


    TextView unmarried, married;

    ImageView select_image;
    ImageView select_image_s;

    RecyclerView comfort_RecyclerView;
    List<TypeModules> type_list = new ArrayList<>();
    List<ComfortModules> comfort_list = new ArrayList<>();
    public static List<SelectImageModules> selectIamgeList = new ArrayList<>();
    IResult mResultCallback;

    Button btn_send;
    RecyclerView images_RecyclerView;

    AlertDialog alertDialog;
    String id_of_all_image = "";
    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;

    EditText description;
    PlacesClient placesClient;
    ImageView back;

    TextView city_l;
    LinearLayout specificationsqares;
    LinearLayout means_comfort;
    LinearLayout date_eqjar;

    RecyclerView type_date;

    List<String> date_list = new ArrayList<>();
    LinearLayout all_gender;

    EditText Instrument_number, piece_number, No_planned, Total_area, age_of_the_property, Role_number, Street_view, total_price, price_one_meter, Communication_Officer, contact_number;
    //---------------------------------------------
    String opration_select = "1";
    String Type_work_select = "1";
    String image_planed = "";
    String is_rent = "0";
    String rent_type = "yearly";


    String lat = "0.0";
    String lng = "0.0";


    String finishing_type = "deluxe";
    String social_status = "unmarried";


    File instrument_filexx = null;

    ImageView Add_charts_image;

    Switch switch_more_detials;
    LinearLayout all_more_detila;
    TextView nibors;
    LinearLayout Lounges_lay;
    LinearLayout room_lay;
    LinearLayout Bathrooms_lay;
    LinearLayout Dining_rooms_lay;
    LinearLayout Boards_lay;
    LinearLayout Kitchens_lay;


    TextView ada_1_yes;
    TextView ada_1_no;
    TextView property_dd_yes;
    TextView property_dd_no;
    TextView property_dd_yes1;
    TextView property_dd_no1;
    TextView real_yes;
    TextView real_no;


    GpsTracker gpsTracker;


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

    String city_id = "";
    String nib_id = "";
    String Address = "";

    BottomSheetDialogFragment_SelectCity bottomSheetDialogFragment_selectCity;
    BottomSheetDialogFragment_SelectNeighborhoods bottomSheetDialogFragment_selectNeighborhoods;

    public void init() {

        ada_1_yes = findViewById(R.id.ada_1_yes);
        ada_1_no = findViewById(R.id.ada_1_no);
        property_dd_yes = findViewById(R.id.property_dd_yes);
        property_dd_no = findViewById(R.id.property_dd_no);
        property_dd_yes1 = findViewById(R.id.property_dd_yes1);
        property_dd_no1 = findViewById(R.id.property_dd_no1);
        more_comfort = findViewById(R.id.more_comfort);
        real_yes = findViewById(R.id.real_yes);
        real_no = findViewById(R.id.real_no);


        back = findViewById(R.id.back);
        images_RecyclerView = findViewById(R.id.images_RecyclerView);
        opration_RecyclerView = findViewById(R.id.opration_RecyclerView);
        select_image = findViewById(R.id.select_image);
        all_gender = findViewById(R.id.all_gender);
        nibors = findViewById(R.id.nibors);

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

        type_date = findViewById(R.id.type_date);
        city_l = findViewById(R.id.city_l);


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
        Add_charts_image = findViewById(R.id.Add_charts_image);
        switch_more_detials = findViewById(R.id.switch_more_detials);
        all_more_detila = findViewById(R.id.all_more_detila);

        Lounges_lay = findViewById(R.id.Lounges_lay);
        room_lay = findViewById(R.id.room_lay);
        Bathrooms_lay = findViewById(R.id.Bathrooms_lay);
        Dining_rooms_lay = findViewById(R.id.Dining_rooms_lay);
        Boards_lay = findViewById(R.id.Boards_lay);
        Kitchens_lay = findViewById(R.id.Kitchens_lay);


        total_price.addTextChangedListener(new NumberTextWatcher(total_price));
        Total_area.addTextChangedListener(new NumberTextWatcher(Total_area));
        piece_number.addTextChangedListener(new NumberTextWatcher(piece_number));
        price_one_meter.addTextChangedListener(new NumberTextWatcher(piece_number));


        //---------------------------------------------------------------------------------------
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //---------------------------------------------------------------------------------------
        try {
            Communication_Officer.setText(Settings.GetUser().getName() + "");
            contact_number.setText("0" + Settings.GetUser().getMobile() + "");
        } catch (Exception e) {

        }

//        comfort_RecyclerView.setLayoutManager(new GridLayoutManager(this, 3));


        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(AddAqarsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        opration_RecyclerView.setLayoutManager(layoutManager1);
//
//        RecyclerView_All_type_in_fragment recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_fragment(AddAqarsActivity.this, type_list);
//        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_fragment.ItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//
//
//
//
//            }
//        });
//        opration_RecyclerView.setAdapter(recyclerView_all_type_in_fragment);

        city_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                bottomSheetDialogFragment_selectCity = new BottomSheetDialogFragment_SelectCity("");
                bottomSheetDialogFragment_selectCity.addItemClickListener(new BottomSheetDialogFragment_SelectCity.ItemClickListener() {
                    @Override
                    public void onItemClick(int id_city, String city_naem) {
                        city_id = id_city + "";
                        city_l.setText(city_naem);
                        bottomSheetDialogFragment_selectCity.dismiss();
                    }
                });

                bottomSheetDialogFragment_selectCity.show(getSupportFragmentManager(), "");

            }
        });
        nibors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (city_l.getText().toString().equals("")) {

                } else {

                    bottomSheetDialogFragment_selectNeighborhoods = new BottomSheetDialogFragment_SelectNeighborhoods(city_id);
                    bottomSheetDialogFragment_selectNeighborhoods.addItemClickListener(new BottomSheetDialogFragment_SelectNeighborhoods.ItemClickListener() {
                        @Override
                        public void onItemClick(int id_city, String city_naem) {
                            nib_id = id_city + "";
                            nibors.setText(city_naem);
                            bottomSheetDialogFragment_selectNeighborhoods.dismiss();

                        }
                    });

                    bottomSheetDialogFragment_selectNeighborhoods.show(getSupportFragmentManager(), "");

                }


            }
        });

        real_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                real_yes.setBackground(getResources().getDrawable(R.drawable.button_login));

                real_yes.setTextColor(getResources().getColor(R.color.white));

                real_no.setBackground(null);

                real_no.setTextColor(getResources().getColor(R.color.textColor));

            }
        });
        real_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                real_no.setBackground(getResources().getDrawable(R.drawable.button_login));

                real_no.setTextColor(getResources().getColor(R.color.white));


                real_yes.setBackground(null);

                real_yes.setTextColor(getResources().getColor(R.color.textColor));


            }

        });
//------------------------------------------------------------------------------------
        ada_1_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ada_1_yes.setBackground(getResources().getDrawable(R.drawable.button_login));

                ada_1_yes.setTextColor(getResources().getColor(R.color.white));

                ada_1_no.setBackground(null);

                ada_1_no.setTextColor(getResources().getColor(R.color.textColor));

            }
        });
        ada_1_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ada_1_no.setBackground(getResources().getDrawable(R.drawable.button_login));

                ada_1_no.setTextColor(getResources().getColor(R.color.white));


                ada_1_yes.setBackground(null);

                ada_1_yes.setTextColor(getResources().getColor(R.color.textColor));


            }

        });
        property_dd_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                property_dd_yes.setBackground(getResources().getDrawable(R.drawable.button_login));

                property_dd_yes.setTextColor(getResources().getColor(R.color.white));


                property_dd_no.setBackground(null);

                property_dd_no.setTextColor(getResources().getColor(R.color.textColor));

            }
        });
        property_dd_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                property_dd_no.setBackground(getResources().getDrawable(R.drawable.button_login));

                property_dd_no.setTextColor(getResources().getColor(R.color.white));


                property_dd_yes.setBackground(null);

                property_dd_yes.setTextColor(getResources().getColor(R.color.textColor));

            }
        });
        property_dd_yes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                property_dd_yes1.setBackground(getResources().getDrawable(R.drawable.button_login));

                property_dd_yes1.setTextColor(getResources().getColor(R.color.white));


                property_dd_no1.setBackground(null);

                property_dd_no1.setTextColor(getResources().getColor(R.color.textColor));


            }
        });
        property_dd_no1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                property_dd_no1.setBackground(getResources().getDrawable(R.drawable.button_login));

                property_dd_no1.setTextColor(getResources().getColor(R.color.white));


                property_dd_yes1.setBackground(null);

                property_dd_yes1.setTextColor(getResources().getColor(R.color.textColor));

            }
        });


        ///------------------------------------------------------------------------------------------------------
        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        LinearLayoutManager layoutManagerw
                = new LinearLayoutManager(AddAqarsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        opration_RecyclerView.setLayoutManager(layoutManagerw);

        RecyclerView_All_opration_bottom_sheet recyclerView_all_opration_bottom_sheet = new RecyclerView_All_opration_bottom_sheet(AddAqarsActivity.this, type_list);
        recyclerView_all_opration_bottom_sheet.addItemClickListener(new RecyclerView_All_opration_bottom_sheet.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

                opration_select = type_list.get(position).getId().toString() + "";


                if (opration_select.toString().equals("1")) {//شقة


                    specificationsqares.setVisibility(View.VISIBLE);
                    means_comfort.setVisibility(View.VISIBLE);

                } else if (opration_select.toString().equals("2")) {//فيلا


                    specificationsqares.setVisibility(View.VISIBLE);
                    means_comfort.setVisibility(View.VISIBLE);
                } else if (opration_select.toString().equals("3")) {//ارض


                    specificationsqares.setVisibility(View.GONE);
                    means_comfort.setVisibility(View.GONE);
                } else if (opration_select.toString().equals("7")) {//مزرعه


                    specificationsqares.setVisibility(View.GONE);
                    means_comfort.setVisibility(View.GONE);

                } else if (opration_select.toString().equals("4")) {//دبلكس
                    specificationsqares.setVisibility(View.VISIBLE);
                    means_comfort.setVisibility(View.VISIBLE);


                } else if (opration_select.toString().equals("6")) {//مكتب

                    specificationsqares.setVisibility(View.VISIBLE);
                    means_comfort.setVisibility(View.VISIBLE);


                }
            }
        });
        opration_RecyclerView.setAdapter(recyclerView_all_opration_bottom_sheet);
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
                                requstcode = 11;

                                ActivityCompat.requestPermissions(AddAqarsActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        11);

                            } else {

//                                PingPlacePicker.IntentBuilder builder = new PingPlacePicker.IntentBuilder();
//                                builder.setAndroidApiKey("AIzaSyDWtJ0cgqHXouF5I5YdPjLzztWQzXM4zQc")
//                                        .setMapsApiKey("AIzaSyDWtJ0cgqHXouF5I5YdPjLzztWQzXM4zQc");
//
//                                // If you want to set a initial location rather then the current device location.
//                                // NOTE: enable_nearby_search MUST be true.
//                                // builder.setLatLng(new LatLng(37.4219999, -122.0862462))
//
//                                try {
//                                    Intent placeIntent = builder.build(AddAqarsActivity.this);
//                                    startActivityForResult(placeIntent, 11);
//                                } catch (Exception ex) {
//                                    // Google Play services is not available...
//                                }

                                requstcode = 11;
                                is_place = true;
                                Intent intent = new Intent(AddAqarsActivity.this, SelectLocationActivity.class);
                                startActivityForResult(intent, 11);


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
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Lounges_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    Lounges_number.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
                    Lounges_lay.setBackground(null);
                    Lounges_number.setTextColor(getResources().getColor(R.color.black));

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

//                if (number_Lounges > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
//
//                }
                if (number_Lounges > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Lounges_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    Lounges_number.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
                    Lounges_lay.setBackground(null);
                    Lounges_number.setTextColor(getResources().getColor(R.color.black));

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


//                if (number_room > 0) {
//                    room_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    room_text.setBackgroundColor(getResources().getColor(R.color.white));
//
//                }
                if (number_room > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    room_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    room_text.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
                    room_lay.setBackground(null);
                    room_text.setTextColor(getResources().getColor(R.color.black));

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

//                if (number_room > 0) {
//                    room_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    room_text.setBackgroundColor(getResources().getColor(R.color.white));
//
//                }
                if (number_room > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    room_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    room_text.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
                    room_lay.setBackground(null);
                    room_text.setTextColor(getResources().getColor(R.color.black));

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

//
//                if (number_Bathrooms > 0) {
//                    Bathrooms_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    Bathrooms_text.setBackgroundColor(getResources().getColor(R.color.white));
//
//                }

                if (number_Bathrooms > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Bathrooms_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    Bathrooms_text.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
                    Bathrooms_lay.setBackground(null);
                    Bathrooms_text.setTextColor(getResources().getColor(R.color.black));

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
//
//                if (number_Bathrooms > 0) {
//                    Bathrooms_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    Bathrooms_text.setBackgroundColor(getResources().getColor(R.color.white));
//
//                }

                if (number_Bathrooms > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Bathrooms_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    Bathrooms_text.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
                    Bathrooms_lay.setBackground(null);
                    Bathrooms_text.setTextColor(getResources().getColor(R.color.black));

                }
                Bathrooms_text.setText(number_Bathrooms + "");


            }
        });


//-------------------------------------------------------------------------------------------------
        switch_more_detials.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    all_more_detila.setVisibility(View.VISIBLE);
                } else {
                    all_more_detila.setVisibility(View.GONE);
                }


            }
        });

//-------------------------------------------------------------------------------------------------


        Boards_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_Boards_plus = Integer.valueOf(Boards_text.getText().toString());


                number_Boards_plus++;

//
//                if (number_Boards_plus > 0) {
//                    Boards_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    Boards_text.setBackgroundColor(getResources().getColor(R.color.white));
//
//                }

                if (number_Boards_plus > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Boards_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    Boards_text.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
                    Boards_lay.setBackground(null);
                    Boards_text.setTextColor(getResources().getColor(R.color.black));

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

//                if (number_Boards_plus > 0) {
//                    Boards_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    Boards_text.setBackgroundColor(getResources().getColor(R.color.white));
//
//                }
                if (number_Boards_plus > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Boards_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    Boards_text.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
                    Boards_lay.setBackground(null);
                    Boards_text.setTextColor(getResources().getColor(R.color.black));

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


//                if (number_Kitchens_plus > 0) {
//                    Kitchens_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    Kitchens_text.setBackgroundColor(getResources().getColor(R.color.white));
//
//                }

                if (number_Kitchens_plus > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Kitchens_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    Kitchens_text.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
                    Kitchens_lay.setBackground(null);
                    Kitchens_text.setTextColor(getResources().getColor(R.color.black));

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

//                if (number_Kitchens_plus > 0) {
//                    Kitchens_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    Kitchens_text.setBackgroundColor(getResources().getColor(R.color.white));
//
//                }
                if (number_Kitchens_plus > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Kitchens_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    Kitchens_text.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
                    Kitchens_lay.setBackground(null);
                    Kitchens_text.setTextColor(getResources().getColor(R.color.black));

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

//
//                if (number_Dining_rooms > 0) {
//                    Dining_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    Dining_text.setBackgroundColor(getResources().getColor(R.color.white));
//
//                }
                if (number_Dining_rooms > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Dining_rooms_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    Dining_text.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
                    Dining_rooms_lay.setBackground(null);
                    Dining_text.setTextColor(getResources().getColor(R.color.black));

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

//                if (number_Dining_rooms > 0) {
//                    Dining_text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    Dining_text.setBackgroundColor(getResources().getColor(R.color.white));
//
//                }
                if (number_Dining_rooms > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Dining_rooms_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    Dining_text.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
                    Dining_rooms_lay.setBackground(null);
                    Dining_text.setTextColor(getResources().getColor(R.color.black));

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


                married.setBackground(getResources().getDrawable(R.drawable.mash));

                married.setTextColor(getResources().getColor(R.color.textColor));
                social_status = "unmarried";

            }
        });
        married.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                married.setBackground(getResources().getDrawable(R.drawable.button_login));

                married.setTextColor(getResources().getColor(R.color.white));

                unmarried.setBackground(getResources().getDrawable(R.drawable.mash));


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

                average.setBackground(getResources().getDrawable(R.drawable.mash));


                average.setTextColor(getResources().getColor(R.color.textColor));

                normal.setBackground(getResources().getDrawable(R.drawable.mash));


                normal.setTextColor(getResources().getColor(R.color.textColor));

                finishing_type = "deluxe";
            }
        });
        average.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                average.setBackground(getResources().getDrawable(R.drawable.button_login));

                average.setTextColor(getResources().getColor(R.color.white));


                deluxe.setBackground(getResources().getDrawable(R.drawable.mash));

                deluxe.setTextColor(getResources().getColor(R.color.textColor));


                normal.setBackground(getResources().getDrawable(R.drawable.mash));

                normal.setTextColor(getResources().getColor(R.color.textColor));

                finishing_type = "average";
            }
        });
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normal.setBackground(getResources().getDrawable(R.drawable.button_login));
                normal.setTextColor(getResources().getColor(R.color.white));
                average.setBackground(getResources().getDrawable(R.drawable.mash));
                average.setTextColor(getResources().getColor(R.color.textColor));
                deluxe.setBackground(getResources().getDrawable(R.drawable.mash));

                deluxe.setTextColor(getResources().getColor(R.color.textColor));

                finishing_type = "normal";
            }
        });


//-------------------------------------------------------------------------------------------------
        north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (interface_north.equals("")) {
                    north.setBackground(getResources().getDrawable(R.drawable.button_login));

                    north.setTextColor(getResources().getColor(R.color.white));
                    interface_north = "north";

                } else {
                    north.setBackground(getResources().getDrawable(R.drawable.mash));
//
                    north.setTextColor(getResources().getColor(R.color.textColor));
                    interface_north = "";

//
                }


//                west.setBackground(getResources().getDrawable(R.drawable.mash));
//
//                west.setTextColor(getResources().getColor(R.color.textColor));
//
//
//                south.setBackground(getResources().getDrawable(R.drawable.mash));
//
//                south.setTextColor(getResources().getColor(R.color.textColor));
//
//
//                east.setBackground(getResources().getDrawable(R.drawable.mash));
//
//                east.setTextColor(getResources().getColor(R.color.textColor));


            }
        });
        south.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (interface_south.equals("")) {
                    south.setBackground(getResources().getDrawable(R.drawable.button_login));

                    south.setTextColor(getResources().getColor(R.color.white));
                    interface_south = "south";

                } else {
                    south.setBackground(getResources().getDrawable(R.drawable.mash));
//
                    south.setTextColor(getResources().getColor(R.color.textColor));
                    interface_south = "";

//
                }


//                north.setBackground(getResources().getDrawable(R.drawable.mash));
//
//                north.setTextColor(getResources().getColor(R.color.textColor));
//
//
//                east.setBackground(getResources().getDrawable(R.drawable.mash));
//
//                east.setTextColor(getResources().getColor(R.color.textColor));
//
//
//                west.setBackground(getResources().getDrawable(R.drawable.mash));
//
//                west.setTextColor(getResources().getColor(R.color.textColor));

            }
        });
        east.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (interface_east.equals("")) {
                    east.setBackground(getResources().getDrawable(R.drawable.button_login));

                    east.setTextColor(getResources().getColor(R.color.white));
                    interface_east = "east";

                } else {
                    east.setBackground(getResources().getDrawable(R.drawable.mash));
//
                    east.setTextColor(getResources().getColor(R.color.textColor));
                    interface_east = "";

//
                }


//                north.setBackground(getResources().getDrawable(R.drawable.mash));
//
//                north.setTextColor(getResources().getColor(R.color.textColor));
//
//
//                south.setBackground(getResources().getDrawable(R.drawable.mash));
//
//                south.setTextColor(getResources().getColor(R.color.textColor));
//
//
//                west.setBackground(getResources().getDrawable(R.drawable.mash));
//
//                west.setTextColor(getResources().getColor(R.color.textColor));


            }
        });
        west.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (interface_west.equals("")) {
                    west.setBackground(getResources().getDrawable(R.drawable.button_login));

                    west.setTextColor(getResources().getColor(R.color.white));
                    interface_west = "west";

                } else {
                    west.setBackground(getResources().getDrawable(R.drawable.mash));
//
                    west.setTextColor(getResources().getColor(R.color.textColor));
                    interface_west = "";

//
                }


//                north.setBackground(getResources().getDrawable(R.drawable.mash));
//
//                north.setTextColor(getResources().getColor(R.color.textColor));
//
//                south.setBackground(getResources().getDrawable(R.drawable.mash));
//
//                south.setTextColor(getResources().getColor(R.color.textColor));
//
//                east.setBackground(getResources().getDrawable(R.drawable.mash));
//
//                east.setTextColor(getResources().getColor(R.color.textColor));


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


                rent.setBackground(getResources().getDrawable(R.drawable.mash));

                rent.setTextColor(getResources().getColor(R.color.textColor));


                investment.setBackground(getResources().getDrawable(R.drawable.mash));

                investment.setTextColor(getResources().getColor(R.color.textColor));


//                rent.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
//                investment.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
//                For_sale.getCompoundDrawables()[0].setTint(Color.WHITE);

                Type_work_select = "1";

                date_eqjar.setVisibility(View.GONE);
                is_rent = "0";
                all_gender.setVisibility(View.GONE);

            }
        });
        rent.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                all_gender.setVisibility(View.VISIBLE);


                rent.setBackground(getResources().getDrawable(R.drawable.button_login));

                rent.setTextColor(getResources().getColor(R.color.white));


                For_sale.setBackground(getResources().getDrawable(R.drawable.mash));

                For_sale.setTextColor(getResources().getColor(R.color.textColor));


                investment.setBackground(getResources().getDrawable(R.drawable.mash));

                investment.setTextColor(getResources().getColor(R.color.textColor));


//                For_sale.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
//                investment.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
//                rent.getCompoundDrawables()[0].setTint(Color.WHITE);

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

                all_gender.setVisibility(View.GONE);

                For_sale.setBackground(getResources().getDrawable(R.drawable.search_background));

                For_sale.setTextColor(getResources().getColor(R.color.textColor));


                rent.setBackground(getResources().getDrawable(R.drawable.search_background));

                rent.setTextColor(getResources().getColor(R.color.textColor));


//                For_sale.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
//                rent.getCompoundDrawables()[0].setTint(getResources().getColor(R.color.black));
//                investment.getCompoundDrawables()[0].setTint(Color.WHITE);

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

                System.out.println("ireiruieruie");


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(AddAqarsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(AddAqarsActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                11);

                    } else {
                        select_image_from_local(1213, 1213);

                    }
                } else {
                    select_image_from_local(1213, 1213);

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


                        select_image_from_local(1217, 1217);

                    }
                } else {

                    select_image_from_local(1217, 1217);


                }


            }
        });


        Add_charts_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(AddAqarsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(AddAqarsActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                13);

                    } else {

                        select_image_from_local(20, 20);
                    }
                } else {

                    select_image_from_local(20, 20);

                }


            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (total_price.getText().toString().equals("") |

                        Total_area.getText().toString().equals("") |
                        city_l.getText().toString().equals("") |
                        nibors.getText().toString().equals("") |

                        contact_number.getText().toString().equals("") |
                        Communication_Officer.getText().toString().equals("")


//
//                        Instrument_number.getText().toString().equals("") |
//                        piece_number.getText().toString().equals("") |
//                        No_planned.getText().toString().equals("") |
//                        age_of_the_property.getText().toString().equals("") |
//                        Role_number.getText().toString().equals("") |
//                        Street_view.getText().toString().equals("") |
//                        price_one_meter.getText().toString().equals("") |
//                        lat.toString().equals("") |
//                        selectIamgeList.size() == 0 |

                ) {


                    WebService.Make_Toast_color(AddAqarsActivity.this, getResources().getString(R.string.fillallfileds) + "", "error");


                } else if (lat.equals("0.0")) {


                    new AlertDialog.Builder(AddAqarsActivity.this)
                            .setMessage(getResources().getString(R.string.message_location))
                            .setCancelable(false)
                            .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {


                                    lat = getLocation().latitude + "";
                                    lng = getLocation().longitude + "";
                                    add_aqares_information();

                                }
                            })
                            .setNegativeButton(getResources().getString(R.string.no), null)
                            .show();


                } else {
                    add_aqares_information();

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


                            List<ComfortModules> comfort_listxx = new ArrayList<>();


                            if (comfort_list.size() > 4) {
                                more_comfort.setVisibility(View.VISIBLE);


                                for (int i = 0; i < 4; i++) {

                                    comfort_listxx.add(comfort_list.get(i));

                                }


                            } else {
                                more_comfort.setVisibility(View.GONE);
                                comfort_listxx = comfort_list;
                            }

                            FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
                            flowLayoutManager.setAutoMeasureEnabled(true);
//                            flowLayoutManager.maxItemsPerLine(1);
                            comfort_RecyclerView.setLayoutManager(flowLayoutManager);

//                            comfort_RecyclerView.setLayoutManager();
//                            comfort_RecyclerView.setLayoutManager(new FlowLayoutManager().singleItemPerLine());
//                            comfort_RecyclerView.setLayoutManager(new FlowLayoutManager().maxItemsPerLine(3));

                            RecyclerView_All_Comfort_in_fragment recyclerView_all_comfort_in_fragment = new RecyclerView_All_Comfort_in_fragment(AddAqarsActivity.this, comfort_listxx);

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


                            more_comfort.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    more_comfort.setVisibility(View.GONE);
                                    recyclerView_all_comfort_in_fragment.add_more_date(comfort_list);
                                }
                            });
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

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%55");

        if (requstcode == 1213) {


//                    String filePath = resultUri.getPath().toString();
//                    Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);
//
//                    File file_image_profile = new File(filePath);
//                    try {
//
//                        RequestParams requestParams = new RequestParams();
//
//                        requestParams.put("photo", file_image_profile);
//
//
//                        Upload_image(requestParams, selectedImagea);
//                    } catch (Exception e) {
//
//                    }
//

            if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, requstcode)) {
                images = ImagePicker.getImages(data);

//                RequestParams requestParams = new RequestParams();

//                Upload_image(requestParams, images);

                System.out.println("imagesimages" + images.size());
                for (int i = 0; i < images.size(); i++) {
                    Bitmap selectedImagea = BitmapFactory.decodeFile(images.get(i).getPath());
                    selectIamgeList.add(new SelectImageModules("1", selectedImagea));
                }
                images_RecyclerView.setAdapter(new RecyclerView_selectImage(AddAqarsActivity.this, selectIamgeList));


            }


//                    ArrayList<Uri> image_uris = data.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
//
////                    ArrayList<String> selectionResult=data.getStringArrayListExtra("result");
//
////                    List<Image> images = ImagePicker.getImages(data);
//
//
//                    RequestParams requestParams = new RequestParams();
//                    for (int i = 0; i < image_uris.size(); i++) {
//
//                        try {
//                            File file_image_profile = new File(image_uris.get(i).getPath());
//
//                            System.out.println();
//                            requestParams.put("photo[" + i + "]", file_image_profile);
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                    Upload_image(requestParams, image_uris);


//                        // Do stuff with image's path or id. For example:
//                        for (Image in images) {
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                                Glide.with(context)
//                                        .load(image.uri)
//                                        .into(imageView)
//                            } else {
//                                Glide.with(context)
//                                        .load(image.path)
//                                        .into(imageView)
//                            }
//                        }


        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            WebService.Make_Toast_color(AddAqarsActivity.this, "تم ارجاع الصور", "success");

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                System.out.println("dkfmdlfdlfkdf");

                if (requstcode == 1213) {


//                    String filePath = resultUri.getPath().toString();
//                    Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);
//
//                    File file_image_profile = new File(filePath);
//                    try {
//
//                        RequestParams requestParams = new RequestParams();
//
//                        requestParams.put("photo", file_image_profile);
//
//
//                        Upload_image(requestParams, selectedImagea);
//                    } catch (Exception e) {
//
//                    }
//
//
//                    if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, requstcode)) {
//                        ArrayList<Image> images=ImagePicker.getImages(data);
//
//                        RequestParams requestParams = new RequestParams();
//                    for (int i = 0; i < images.size(); i++) {
//
//                        try {
//                            File file_image_profile = new File(images.get(i).getPath());
//
//                            System.out.println();
//                            requestParams.put("photo[" + i + "]", file_image_profile);
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                        Upload_image(requestParams, images);
//
//
//
//                    }


//                    ArrayList<Uri> image_uris = data.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
//
////                    ArrayList<String> selectionResult=data.getStringArrayListExtra("result");
//
////                    List<Image> images = ImagePicker.getImages(data);
//
//
//                    RequestParams requestParams = new RequestParams();
//                    for (int i = 0; i < image_uris.size(); i++) {
//
//                        try {
//                            File file_image_profile = new File(image_uris.get(i).getPath());
//
//                            System.out.println();
//                            requestParams.put("photo[" + i + "]", file_image_profile);
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                    Upload_image(requestParams, image_uris);


//                        // Do stuff with image's path or id. For example:
//                        for (Image in images) {
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                                Glide.with(context)
//                                        .load(image.uri)
//                                        .into(imageView)
//                            } else {
//                                Glide.with(context)
//                                        .load(image.path)
//                                        .into(imageView)
//                            }
//                        }


                } else if (requstcode == 1217) {
                    String filePath = resultUri.getPath().toString();

                    Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);

                    Instrument_file.setImageBitmap(selectedImagea);

                    instrument_filexx = new File(filePath);
                } else if (requstcode == 20) {
                    String filePath = resultUri.getPath().toString();

                    Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);


                    Add_charts_image.setImageBitmap(selectedImagea);

                    File file_image_profile = new File(filePath);


                    try {

                        RequestParams requestParams = new RequestParams();

                        requestParams.put("photo", file_image_profile);

                        Upload_image_planed(requestParams, selectedImagea);
                    } catch (Exception e) {

                    }

                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }


        if ((requstcode == 11 & is_place)) {
            if (resultCode == Activity.RESULT_OK) {

                try {

                    // TODO Extract the data returned from the child Activity.
                    String lat_ = data.getStringExtra("lat");
                    String lang_ = data.getStringExtra("lang");
                    String address_ = data.getStringExtra("address");

                    lat = "" + lat_;
                    lng = "" + lang_;
                    Toast.makeText(AddAqarsActivity.this, "You selected the place: " + address_, Toast.LENGTH_SHORT).show();
//
                    LatLng sydney = new LatLng(Double.valueOf(lat), Double.valueOf(lng));
                    googleMap.addMarker(new MarkerOptions()
                            .position(sydney)
                            .title("Marker"));

                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
                    // Zoom in, animating the camera.
                    googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                    // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);
                    Address = address_;
                    is_place = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            if (data != null) {
//                Place place = PingPlacePicker.getPlace(data);
//                if (place != null) {
//                    Toast.makeText(AddAqarsActivity.this, "You selected the place: " + place.getName(), Toast.LENGTH_SHORT).show();
//
//
//
//
//
//
//                    Address = place.getAddress() + "";
//                    System.out.println("ADDDRESS::" + place.getAddress());
//
//
//                }
//            }


        }


//
//        if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 1213)) {
//
//
//            if (data != null) {
//                ArrayList<Image> images = ImagePicker.getImages(data);
//                String filePath = images.get(0).getPath().toString();
//                Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);
//
//                File file_image_profile = new File(filePath);
//                try {
//
//                    RequestParams requestParams = new RequestParams();
//
//                    requestParams.put("photo", file_image_profile);
//
//
//                    Upload_image(requestParams, selectedImagea);
//                } catch (Exception e) {
//
//                }
////                image_id.setImageBitmap(selectedImagea);
////                get_id_image_file = new File(filePath);
////                try {
////                    RequestParams requestParams = new RequestParams();
////                    requestParams.put("photo", file_image_profile);
////
////                } catch (Exception e) {
////                }
//            }
//        }
//
//        if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 1217)) {
//
//
//            if (data != null) {
//                ArrayList<Image> images = ImagePicker.getImages(data);
//                String filePath = images.get(0).getPath().toString();
//                Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);
//
//
//                Instrument_file.setImageBitmap(selectedImagea);
//
//                instrument_filexx = new File(filePath);
//
////                image_id.setImageBitmap(selectedImagea);
////                get_id_image_file = new File(filePath);
////                try {
////                    RequestParams requestParams = new RequestParams();
////                    requestParams.put("photo", file_image_profile);
////
////                } catch (Exception e) {
////                }
//            }
//        }
//
//
//        if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 20)) {
//
//
//            if (data != null) {
//                ArrayList<Image> images = ImagePicker.getImages(data);
//                String filePath = images.get(0).getPath().toString();
//                Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);
//
//
//                Add_charts_image.setImageBitmap(selectedImagea);
//
//                File file_image_profile = new File(filePath);
//
//
//                try {
//
//                    RequestParams requestParams = new RequestParams();
//
//                    requestParams.put("photo", file_image_profile);
//
//                    Upload_image_planed(requestParams, selectedImagea);
//                } catch (Exception e) {
//
//                }
//
//
//            }
//        }


    }


    public void Upload_image(RequestParams requestParams, ArrayList<Image> image_uris) {


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

                        String data = responseBody.getString("data");//"status":true,"code":200,"message":"Estate","data":[95,96,97]}

                        id_of_all_image = data;
                        for (int i = 0; i < image_uris.size(); i++) {
                            Bitmap selectedImagea = BitmapFactory.decodeFile(image_uris.get(i).getPath());
                            selectIamgeList.add(new SelectImageModules(data, selectedImagea));
                        }
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

                        image_planed = data + "";
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

//
                        try {

                            BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(AddAqarsActivity.this);
                            View parentView = getLayoutInflater().inflate(R.layout.success_message, null);
                            Button close = parentView.findViewById(R.id.close);
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

//                        WebService.Make_Toast_color(FinanceActivity.this, message, "success");


//                        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                        final View popupView = layoutInflater.inflate(R.layout.card_add_aqares_success, null);
//
//
//                        final AlertDialog.Builder builder = new AlertDialog.Builder(AddAqarsActivity.this);
//
////            alertDialog_country =
//                        builder.setView(popupView);
//
//
//                        alertDialog = builder.show();

//                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
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

    int requstcode = 0;

    public void select_image_from_local(int permission, int st_code) {
        requstcode = st_code;


        if (permission == 1213) {
            System.out.println("requstcode" + requstcode);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(AddAqarsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(AddAqarsActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            permission);

                } else {

//                    CropImage.activity()
//                            .setGuidelines(CropImageView.Guidelines.ON)
//                            .start(this);


//                    Intent intent= new Intent(this, Gallery.class);
//                    // Set the title
//                    intent.putExtra("title","Select media");
//                    // Mode 1 for both images and videos selection, 2 for images only and 3 for videos!
//                    intent.putExtra("mode",1);
//                    intent.putExtra("maxSelection",3); // Optional
//                    startActivityForResult(intent,permission);


                    ImagePicker.with(this)
                            .setFolderMode(true)
                            .setFolderTitle("Album")

                            .setDirectoryName("Image Picker")
                            .setMultipleMode(true)
                            .setShowNumberIndicator(true)
                            .setMaxSize(5)
                            .setLimitMessage("You can select up to 10 images")

                            .setRequestCode(st_code)
                            .start();


                }
            } else {

//                CropImage.activity()
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .start(this);

                ImagePicker.with(this)
                        .setFolderMode(true)
                        .setFolderTitle("Album")

                        .setDirectoryName("Image Picker")
                        .setMultipleMode(true)
                        .setShowNumberIndicator(true)
                        .setMaxSize(5)
                        .setLimitMessage("You can select up to 10 images")

                        .setRequestCode(st_code)
                        .start();
//                Intent intent= new Intent(this, Gallery.class);
//                // Set the title
//                intent.putExtra("title","Select media");
//                // Mode 1 for both images and videos selection, 2 for images only and 3 for videos!
//                intent.putExtra("mode",1);
//                intent.putExtra("maxSelection",3); // Optional
//                startActivityForResult(intent,permission);

//                ImagePicker.with(AddAqarsActivity.this)
//                        .setFolderMode(true)
//                        .setFolderTitle("Album")
//
//                        .setDirectoryName("Image Picker")
//                        .setMultipleMode(false)
//                        .setShowNumberIndicator(true)
//                        .setMaxSize(5)
//                        .
//                        .setLimitMessage("You can select one image")
//
//                        .setRequestCode(st_code)
//                        .start();

            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(AddAqarsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(AddAqarsActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            permission);

                } else {

                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(this);


//                ImagePicker.with(AddAqarsActivity.this)
//                        .setFolderMode(true)
//                        .setFolderTitle("Album")
//
//                        .setDirectoryName("Image Picker")
//                        .setMultipleMode(false)
//                        .setShowNumberIndicator(true)
//                        .setMaxSize(1)
//                        .setLimitMessage("You can select one image")
//
//                        .setRequestCode(st_code)
//                        .start();
                }
            } else {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);


//            ImagePicker.with(AddAqarsActivity.this)
//                    .setFolderMode(true)
//                    .setFolderTitle("Album")
//
//                    .setDirectoryName("Image Picker")
//                    .setMultipleMode(false)
//                    .setShowNumberIndicator(true)
//                    .setMaxSize(1)
//                    .setLimitMessage("You can select one image")
//
//                    .setRequestCode(st_code)
//                    .start();

            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (ContextCompat.checkSelfPermission(AddAqarsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        } else {
            requstcode = requestCode;

            ImagePicker.with(AddAqarsActivity.this)
                    .setFolderMode(true)
                    .setFolderTitle("Album")

                    .setDirectoryName("Image Picker")
                    .setMultipleMode(false)
                    .setShowNumberIndicator(true)
                    .setMaxSize(5)
                    .setLimitMessage("You can select one image")

                    .setRequestCode(requestCode)
                    .start();


        }


    }


    public void add_aqares_information() {

        RequestParams sendObj = new RequestParams();

        try {


            System.out.println("TESTADDAQAREZ-operation_type_id-" + opration_select + "-Type_work_select-" + Type_work_select);

            sendObj.put("operation_type_id", Type_work_select);
            sendObj.put("estate_type_id", opration_select);

            if (!Instrument_number.getText().toString().equals("")) {
                sendObj.put("instrument_number", Instrument_number.getText().toString());

            }
            if (instrument_filexx != null) {

                sendObj.put("instrument_file", instrument_filexx);


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


            String interface_ = "";


            interface_ = interface_south;


            if (interface_.equals("")) {
                interface_ = interface_east;

            } else {
                interface_ = "," + interface_east;

            }

            if (interface_.equals("")) {
                interface_ = interface_north;

            } else {
                interface_ = "," + interface_north;

            }

            if (interface_.equals("")) {
                interface_ = interface_west;

            } else {
                interface_ = "," + interface_west;

            }


            sendObj.put("interface", interface_);
            sendObj.put("social_status", social_status);
            sendObj.put("city_id", city_id + "");
            sendObj.put("neighborhood_id", nib_id + "");
            sendObj.put("address", Address + "");
            sendObj.put("lat", lat);
            sendObj.put("lan", lng);
            sendObj.put("is_rent", is_rent);//1 = yes or 0 = no
            if (is_rent.equals("1")) {
                sendObj.put("rent_type", rent_type);//'daily','monthly','yearly'
            }

            attachment_planned = "";
            for (int i = 0; i < selectIamgeList.size(); i++) {

                if (attachment_planned.equals("")) {
                    attachment_planned = selectIamgeList.get(i).getId() + "";
                } else {
                    attachment_planned = attachment_planned + "," + selectIamgeList.get(i).getId() + "";

                }

            }
//            sendObj.put("attachment_estate", attachment_planned + "");
//            sendObj.put("attachment_estate", id_of_all_image + "");

            if (images != null) {
                for (int i = 0; i < images.size(); i++) {

                    try {
                        File file_image_profile = new File(images.get(i).getPath());

                        System.out.println();
                        sendObj.put("photo[" + i + "]", file_image_profile);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                }
            }


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
            sendObj.put("is_resident", "1");
            sendObj.put("is_checked", "1");
            sendObj.put("is_insured", "1");


            System.out.println(sendObj.toString());


            AddAqersAsyncTask(sendObj);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public LatLng getLocation() {

        try {
            gpsTracker = new GpsTracker(AddAqarsActivity.this);
            if (gpsTracker.canGetLocation()) {
                double latitude = gpsTracker.getLatitude();
                double longitude = gpsTracker.getLongitude();
                System.out.println("latitude:" + latitude);
                System.out.println("longitude:" + longitude);

                LatLng my_location = new LatLng(latitude, longitude);
//                LatLng my_location = new LatLng(24.768516, 46.691505);

                return my_location;

            } else {
                gpsTracker.showSettingsAlert();

                //24.768516, 46.691505

                LatLng my_location = new LatLng(24.768516, 46.691505);


                return my_location;

            }
        } catch (Exception e) {

            LatLng my_location = new LatLng(24.768516, 46.691505);


            return my_location;
        }

    }

}
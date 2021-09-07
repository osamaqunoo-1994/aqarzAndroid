package sa.aqarz.NewAqarz.AqqAqarz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import sa.aqarz.Activity.AddAqarz.AddAqarzActivity;
import sa.aqarz.Activity.SelectLocationActivity;
import sa.aqarz.Activity.SelectNewLocationActivity;
import sa.aqarz.Adapter.RecyclerView_All_Comfort_in_fragment;
import sa.aqarz.Adapter.RecyclerView_selectImage;
import sa.aqarz.Modules.AddAqarezObject;
import sa.aqarz.Modules.ComfortModules;
import sa.aqarz.Modules.SelectImageModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class AddAqarzStepsActivity extends AppCompatActivity {

    LinearLayout lay_1;
    LinearLayout lay_2;
    LinearLayout lay_3;
    LinearLayout lay_4;


    ///------------------------------------------------------------------------------------------------------

    RecyclerView opration_RecyclerView;
    List<TypeModules> opration_list = new ArrayList<>();


    TextView sell;
    TextView rent;
    TextView investment;

    String type_opration_selected = "";
    String type_aqarz_selected = "";

    ArrayList<Image> images = new ArrayList<>();
    public static List<SelectImageModules> selectIamgeList = new ArrayList<>();

    public static AddAqarezObject addAqarezObject;
    TextView title;
    LinearLayout button_step_1;
    ///------------------------------------------------------------------------------------------------------
    LinearLayout addImage;
    LinearLayout select_video;
    RecyclerView images_RecyclerView;

    ImageView select_video_image;
    LinearLayout button_step_2;
    ///------------------------------------------------------------------------------------------------------
    TextView residential;
    TextView commercial;
    TextView Artificial;

    EditText area_text;
    EditText land_number_text;
    EditText number_units_text;
    EditText turn_number_text;
    EditText unit_number_text;

    ImageView Boards_plus, Boards_minus;
    TextView Boards_text;
    ImageView Kitchens_plus, Kitchens_minus;
    TextView Kitchens_text;

    ImageView Dining_rooms_plus, Dining_rooms_minus;
    TextView Dining_text;

    ImageView Lounges_plus, Lounges_minus;
    TextView Lounges_number;

    ImageView room_plus, room_minus;
    TextView room_text;

    ImageView Bathrooms_plus, Bathrooms_minus;
    TextView Bathrooms_text;

    LinearLayout Lounges_lay;
    LinearLayout room_lay;
    LinearLayout Bathrooms_lay;
    LinearLayout Dining_rooms_lay;
    LinearLayout Boards_lay;
    LinearLayout Kitchens_lay;


    int number_Lounges = 0;
    int number_room = 0;
    int number_Bathrooms = 0;
    int number_Boards_plus = 0;
    int number_Kitchens_plus = 0;
    int number_Dining_rooms = 0;


    EditText lengths_add_text;
    EditText streetwidthadd_text;
    EditText Date_of_construction_text;
    EditText sale_price_text;
    EditText Warranties_duration_txt;

    LinearLayout button_step_3;
    ///------------------------------------------------------------------------------------------------------
    RecyclerView comfort_RecyclerView;
    List<ComfortModules> comfortModules = new ArrayList<>();
    IResult mResultCallback;


    LinearLayout lifts_lay;
    ImageView lifts_plus, lifts_mins;
    TextView lifts_txt;


    LinearLayout parking_lay;
    ImageView parking_plus, parking_mins;
    TextView parking_txt;


    int number_lifts = 0;
    int number_parking = 0;
    ///------------------------------------------------------------------------------------------------------
    TextView is_disputes_yes;
    TextView is_disputes_no;
    TextView is_mortgage_yes;
    TextView is_mortgage_no;
    TextView is_obligations_yes;
    TextView is_obligations_no;
    TextView is_saudi_building_code_yes;
    TextView is_saudi_building_code_no;

    ///------------------------------------------------------------------------------------------------------

    TextView individual_txt;
    TextView facility_txt;

    TextView owner_txt;
    TextView authorized_txt;


    EditText owner_edt;
    EditText mobile_edt;

    LinearLayout button_step_4;


    TextView north;
    TextView south;
    TextView east;
    TextView west;

    boolean north_selected = false;
    boolean south_selected = false;
    boolean east_selected = false;
    boolean west_selected = false;
    LinearLayout all_details;

    ImageView back;
    LinearLayout step_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aqarz_steps);

        addAqarezObject = new AddAqarezObject();

        init();


        step_1();
        select_image_and_video();
        step_3();
        action_plus_mins();
        comfort_();
        owner_info();
        yes_or_no_qustion();
        approval();
        select_view();


    }

    public void init() {

        sell = findViewById(R.id.sell);
        rent = findViewById(R.id.rent);
        investment = findViewById(R.id.investment);
        opration_RecyclerView = findViewById(R.id.opration_RecyclerView);
        title = findViewById(R.id.title);
        button_step_1 = findViewById(R.id.button_step_1);
        addImage = findViewById(R.id.addImage);
        images_RecyclerView = findViewById(R.id.images_RecyclerView);
        select_video = findViewById(R.id.select_video);
        select_video_image = findViewById(R.id.select_video_image);
        lay_1 = findViewById(R.id.lay_1);
        lay_2 = findViewById(R.id.lay_2);
        lay_3 = findViewById(R.id.lay_3);
        lay_4 = findViewById(R.id.lay_4);
        button_step_2 = findViewById(R.id.button_step_2);
        residential = findViewById(R.id.residential);
        commercial = findViewById(R.id.commercial);
        Artificial = findViewById(R.id.Artificial);
        area_text = findViewById(R.id.area_text);
        land_number_text = findViewById(R.id.land_number_text);
        number_units_text = findViewById(R.id.number_units_text);
        turn_number_text = findViewById(R.id.turn_number_text);
        unit_number_text = findViewById(R.id.unit_number_text);
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

        Lounges_lay = findViewById(R.id.Lounges_lay);
        room_lay = findViewById(R.id.room_lay);
        Bathrooms_lay = findViewById(R.id.Bathrooms_lay);
        Dining_rooms_lay = findViewById(R.id.Dining_rooms_lay);
        Boards_lay = findViewById(R.id.Boards_lay);
        Kitchens_lay = findViewById(R.id.Kitchens_lay);
        lengths_add_text = findViewById(R.id.lengths_add_text);
        streetwidthadd_text = findViewById(R.id.streetwidthadd_text);
        Date_of_construction_text = findViewById(R.id.Date_of_construction_text);
        sale_price_text = findViewById(R.id.sale_price_text);
        Warranties_duration_txt = findViewById(R.id.Warranties_duration_txt);
        button_step_3 = findViewById(R.id.button_step_3);
        comfort_RecyclerView = findViewById(R.id.comfort_RecyclerView);
        lifts_lay = findViewById(R.id.lifts_lay);
        lifts_plus = findViewById(R.id.lifts_plus);
        lifts_mins = findViewById(R.id.lifts_mins);
        lifts_txt = findViewById(R.id.lifts_txt);


        parking_lay = findViewById(R.id.parking_lay);
        parking_plus = findViewById(R.id.parking_plus);
        parking_mins = findViewById(R.id.parking_mins);
        parking_txt = findViewById(R.id.parking_txt);
        is_disputes_yes = findViewById(R.id.is_disputes_yes);
        is_disputes_no = findViewById(R.id.is_disputes_no);
        is_mortgage_yes = findViewById(R.id.is_mortgage_yes);
        is_mortgage_no = findViewById(R.id.is_mortgage_no);
        is_obligations_yes = findViewById(R.id.is_obligations_yes);
        is_obligations_no = findViewById(R.id.is_obligations_no);
        is_saudi_building_code_yes = findViewById(R.id.is_saudi_building_code_yes);
        is_saudi_building_code_no = findViewById(R.id.is_saudi_building_code_no);
        individual_txt = findViewById(R.id.individual_txt);
        facility_txt = findViewById(R.id.facility_txt);

        owner_txt = findViewById(R.id.owner_txt);
        authorized_txt = findViewById(R.id.authorized_txt);
        mobile_edt = findViewById(R.id.mobile_edt);
        owner_edt = findViewById(R.id.owner_edt);
        button_step_4 = findViewById(R.id.button_step_4);
        north = findViewById(R.id.north);
        south = findViewById(R.id.south);
        east = findViewById(R.id.east);
        west = findViewById(R.id.west);
        back = findViewById(R.id.back);
        all_details = findViewById(R.id.all_details);
        step_3 = findViewById(R.id.step_3);


        title.setText(getResources().getString(R.string.AqarTypeselect));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (lay_1.getVisibility() == View.VISIBLE) {


                } else if (lay_2.getVisibility() == View.VISIBLE) {
                    lay_1.setVisibility(View.VISIBLE);
                    lay_2.setVisibility(View.GONE);
                    lay_3.setVisibility(View.GONE);
                    lay_4.setVisibility(View.GONE);
                    title.setText(getResources().getString(R.string.AqarTypeselect));

                } else if (lay_3.getVisibility() == View.VISIBLE) {
                    lay_1.setVisibility(View.GONE);
                    lay_2.setVisibility(View.VISIBLE);
                    lay_3.setVisibility(View.GONE);
                    lay_4.setVisibility(View.GONE);
                    title.setText(getResources().getString(R.string.title2));

                } else if (lay_4.getVisibility() == View.VISIBLE) {
                    lay_1.setVisibility(View.GONE);
                    lay_2.setVisibility(View.GONE);
                    lay_3.setVisibility(View.VISIBLE);
                    lay_4.setVisibility(View.GONE);
                    title.setText(getResources().getString(R.string.title6));

                }


            }
        });
    }

    @Override
    public void onBackPressed() {

        if (lay_1.getVisibility() == View.VISIBLE) {

            super.onBackPressed();

        } else if (lay_2.getVisibility() == View.VISIBLE) {
            lay_1.setVisibility(View.VISIBLE);
            lay_2.setVisibility(View.GONE);
            lay_3.setVisibility(View.GONE);
            lay_4.setVisibility(View.GONE);
            title.setText(getResources().getString(R.string.AqarTypeselect));

        } else if (lay_3.getVisibility() == View.VISIBLE) {
            lay_1.setVisibility(View.GONE);
            lay_2.setVisibility(View.VISIBLE);
            lay_3.setVisibility(View.GONE);
            lay_4.setVisibility(View.GONE);
            title.setText(getResources().getString(R.string.title2));

        } else if (lay_4.getVisibility() == View.VISIBLE) {
            lay_1.setVisibility(View.GONE);
            lay_2.setVisibility(View.GONE);
            lay_3.setVisibility(View.VISIBLE);
            lay_4.setVisibility(View.GONE);
            title.setText(getResources().getString(R.string.title6));

        }
    }

    public void step_1() {
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type_opration_selected = "1";
                addAqarezObject.setOperation_type_id(type_opration_selected);

                selected_opration();
            }
        });
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type_opration_selected = "2";
                addAqarezObject.setOperation_type_id(type_opration_selected);

                selected_opration();

            }
        });
        investment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type_opration_selected = "3";
                addAqarezObject.setOperation_type_id(type_opration_selected);

                selected_opration();

            }
        });
        ///------------------------------------------------------------------------------------------------------
        opration_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        LinearLayoutManager layoutManagerw
                = new LinearLayoutManager(AddAqarzStepsActivity.this, LinearLayoutManager.VERTICAL, false);
        opration_RecyclerView.setLayoutManager(layoutManagerw);

        RecyclerView_All_opration recyclerView_all_opration = new RecyclerView_All_opration(AddAqarzStepsActivity.this, opration_list);
        recyclerView_all_opration.addItemClickListener(new RecyclerView_All_opration.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                System.out.println("setEstate_type_id" + position + "*&*&*" + opration_list.get(position).getId());
                type_aqarz_selected = opration_list.get(position).getId() + "";
                change_color_button_step_1();
                addAqarezObject.setEstate_type_id(opration_list.get(position).getId() + "");

                String opration_select = addAqarezObject.getEstate_type_id();

                if (opration_select.equals("1")) {//شقة


                    all_details.setVisibility(View.VISIBLE);
                    Date_of_construction_text.setVisibility(View.VISIBLE);
                    number_units_text.setVisibility(View.VISIBLE);
                    turn_number_text.setVisibility(View.VISIBLE);
                    unit_number_text.setVisibility(View.VISIBLE);
                    step_3.setVisibility(View.VISIBLE);

                } else if (opration_select.equals("2")) {//فيلا


                    all_details.setVisibility(View.VISIBLE);
                    Date_of_construction_text.setVisibility(View.VISIBLE);
                    number_units_text.setVisibility(View.VISIBLE);
                    turn_number_text.setVisibility(View.VISIBLE);
                    unit_number_text.setVisibility(View.VISIBLE);
                    step_3.setVisibility(View.VISIBLE);
                } else if (opration_select.equals("3")) {//ارض


                    all_details.setVisibility(View.GONE);
                    Date_of_construction_text.setVisibility(View.GONE);
                    number_units_text.setVisibility(View.GONE);
                    turn_number_text.setVisibility(View.GONE);
                    unit_number_text.setVisibility(View.GONE);
                    step_3.setVisibility(View.GONE);
                } else if (opration_select.equals("7")) {//مزرعه


                    all_details.setVisibility(View.GONE);
                    Date_of_construction_text.setVisibility(View.GONE);
                    number_units_text.setVisibility(View.GONE);
                    turn_number_text.setVisibility(View.GONE);
                    unit_number_text.setVisibility(View.GONE);
                    step_3.setVisibility(View.GONE);

                } else if (opration_select.equals("4")) {//دبلكس
                    all_details.setVisibility(View.VISIBLE);
                    Date_of_construction_text.setVisibility(View.VISIBLE);
                    number_units_text.setVisibility(View.VISIBLE);
                    turn_number_text.setVisibility(View.VISIBLE);
                    unit_number_text.setVisibility(View.VISIBLE);
                    step_3.setVisibility(View.VISIBLE);


                } else if (opration_select.equals("6")) {//مكتب

                    all_details.setVisibility(View.VISIBLE);
                    Date_of_construction_text.setVisibility(View.VISIBLE);
                    number_units_text.setVisibility(View.VISIBLE);
                    turn_number_text.setVisibility(View.VISIBLE);
                    unit_number_text.setVisibility(View.VISIBLE);
                    step_3.setVisibility(View.VISIBLE);


                }

            }
        });
        opration_RecyclerView.setAdapter(recyclerView_all_opration);

        ///------------------------------------------------------------------------------------------------------
        button_step_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!type_opration_selected.equals("") & !type_aqarz_selected.equals("")) {
                    lay_1.setVisibility(View.GONE);
                    lay_2.setVisibility(View.VISIBLE);
                    title.setText(getResources().getString(R.string.title2));

                } else {


                }

            }
        });

    }

    public void selected_opration() {
        change_color_button_step_1();
        if (type_opration_selected.equals("1")) {

            sell.setTextColor(getResources().getColor(R.color.white));
            rent.setTextColor(getResources().getColor(R.color.textColor));
            investment.setTextColor(getResources().getColor(R.color.textColor));

            sell.setBackground(getResources().getDrawable(R.drawable.back_search_home_selected));
            rent.setBackground(getResources().getDrawable(R.drawable.back_search_homecc));
            investment.setBackground(getResources().getDrawable(R.drawable.back_search_homecc));

        } else if (type_opration_selected.equals("2")) {
            rent.setTextColor(getResources().getColor(R.color.white));
            sell.setTextColor(getResources().getColor(R.color.textColor));
            investment.setTextColor(getResources().getColor(R.color.textColor));

            rent.setBackground(getResources().getDrawable(R.drawable.back_search_home_selected));
            sell.setBackground(getResources().getDrawable(R.drawable.back_search_homecc));
            investment.setBackground(getResources().getDrawable(R.drawable.back_search_homecc));
        } else {

            investment.setTextColor(getResources().getColor(R.color.white));
            rent.setTextColor(getResources().getColor(R.color.textColor));
            sell.setTextColor(getResources().getColor(R.color.textColor));


            investment.setBackground(getResources().getDrawable(R.drawable.back_search_home_selected));
            rent.setBackground(getResources().getDrawable(R.drawable.back_search_homecc));
            sell.setBackground(getResources().getDrawable(R.drawable.back_search_homecc));
        }
    }

    public void change_color_button_step_1() {
        if (!type_opration_selected.equals("") & !type_aqarz_selected.equals("")) {
            button_step_1.setBackground(getResources().getDrawable(R.drawable.back_search_home_selected));
        } else {
            button_step_1.setBackground(getResources().getDrawable(R.drawable.back_search_homeccc));

        }
    }

    public void change_color_button_step_2() {
        if (selectIamgeList.size() != 0) {
            button_step_2.setBackground(getResources().getDrawable(R.drawable.back_search_home_selected));
        } else {
            button_step_2.setBackground(getResources().getDrawable(R.drawable.back_search_homeccc));

        }
    }


    public void step_3() {

        addAqarezObject.setEstate_use_type("residential");

        residential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAqarezObject.setEstate_use_type("residential");


                residential.setBackground(getResources().getDrawable(R.drawable.back_search_home_selected));
                commercial.setBackground(getResources().getDrawable(R.drawable.back_search_homecc));
                Artificial.setBackground(getResources().getDrawable(R.drawable.back_search_homecc));

            }
        });
        commercial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAqarezObject.setEstate_use_type("commercial");

                residential.setBackground(getResources().getDrawable(R.drawable.back_search_homecc));
                commercial.setBackground(getResources().getDrawable(R.drawable.back_search_home_selected));
                Artificial.setBackground(getResources().getDrawable(R.drawable.back_search_homecc));

            }
        });
        Artificial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAqarezObject.setEstate_use_type("artificial");

                residential.setBackground(getResources().getDrawable(R.drawable.back_search_homecc));
                commercial.setBackground(getResources().getDrawable(R.drawable.back_search_homecc));
                Artificial.setBackground(getResources().getDrawable(R.drawable.back_search_home_selected));

            }
        });
        button_step_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                lay_1.setVisibility(View.GONE);
                lay_2.setVisibility(View.GONE);
                lay_3.setVisibility(View.GONE);
                lay_4.setVisibility(View.VISIBLE);
                title.setText(getResources().getString(R.string.title7));


            }
        });
    }


    public void select_image_and_video() {

        LinearLayoutManager layoutManagem
                = new LinearLayoutManager(AddAqarzStepsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        images_RecyclerView.setLayoutManager(layoutManagem);


        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(AddAqarzStepsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(AddAqarzStepsActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                111);

                    } else {
                        select_image_from_local(111, 111);

                    }
                } else {
                    select_image_from_local(111, 111);

                }


            }
        });
        select_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(AddAqarzStepsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(AddAqarzStepsActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                1451);

                    } else {
                        Intent intent = new Intent();
                        intent.setType("video/*");
                        intent.setAction(Intent.ACTION_PICK);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "video/*");

                        startActivityForResult(Intent.createChooser(intent, "Select Video"), 1451);

                    }
                } else {
//                    Pico.openMultipleFiles(AddnewsActivity.this, Pico.TYPE_VIDEO);
                    Intent intent = new Intent();
                    intent.setType("video/*");
                    intent.setAction(Intent.ACTION_PICK);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "video/*");

                    startActivityForResult(Intent.createChooser(intent, "Select Video"), 1451);

                }

            }
        });
        button_step_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectIamgeList.size() != 0) {

                    Intent intent = new Intent(AddAqarzStepsActivity.this, SelectLocationActivity.class);
                    intent.putExtra("lat", addAqarezObject.getLat() + "");
                    intent.putExtra("lan", addAqarezObject.getLan() + "");
                    intent.putExtra("address", "");
                    startActivityForResult(intent, 115);


                } else {


                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if ((requestCode == 115)) {
            if (resultCode == Activity.RESULT_OK) {

                try {

                    // TODO Extract the data returned from the child Activity.
                    String lat_ = data.getStringExtra("lat");
                    String lang_ = data.getStringExtra("lang");
                    String address_ = data.getStringExtra("address");

                    addAqarezObject.setLat("" + lat_);
                    addAqarezObject.setLan("" + lang_);
                    Toast.makeText(AddAqarzStepsActivity.this, "You selected the place: " + address_, Toast.LENGTH_SHORT).show();

                    addAqarezObject.setAddress(address_ + "");


                    lay_1.setVisibility(View.GONE);
                    lay_2.setVisibility(View.GONE);
                    lay_3.setVisibility(View.VISIBLE);
                    title.setText(getResources().getString(R.string.title6));


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (requestCode == 111) {


            if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 111)) {
                images = ImagePicker.getImages(data);

                for (int i = 0; i < images.size(); i++) {
                    Bitmap selectedImagea = BitmapFactory.decodeFile(images.get(i).getPath());
                    selectIamgeList.add(new SelectImageModules("1", selectedImagea));
                }
                addAqarezObject.setSelectIamgeList(selectIamgeList);
                images_RecyclerView.setAdapter(new RecyclerView_selectImage(AddAqarzStepsActivity.this, selectIamgeList));

                change_color_button_step_2();

            }


        }
        if (requestCode == 1451) {
            try {
                Uri selectedImageUri = data.getData();

                String selectedImagePath = getPath(selectedImageUri);


                if (selectedImagePath != null) {

                    addAqarezObject.setVideo(getFile(getApplicationContext(), selectedImageUri));

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {

                        select_video_image.setImageBitmap(createThumbnail(AddAqarzStepsActivity.this, addAqarezObject.getVideo().getPath() + ""));

                    } else {
                        select_video_image.setImageBitmap(ThumbnailUtils.createVideoThumbnail(addAqarezObject.getVideo().getPath(), MediaStore.Video.Thumbnails.FULL_SCREEN_KIND));
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void select_image_from_local(int permission, int st_code) {


        if (permission == 111) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(AddAqarzStepsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(AddAqarzStepsActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            permission);

                } else {


                    ImagePicker.with(this)
                            .setFolderMode(true)
                            .setFolderTitle("Album")

                            .setDirectoryName("Image Picker")
                            .setMultipleMode(true)
                            .setShowNumberIndicator(true)
                            .setMaxSize(5)
                            .setLimitMessage("You can select up to 10 images")
                            .setRequestCode(111)
                            .start();


                }
            } else {


                ImagePicker.with(this)
                        .setFolderMode(true)
                        .setFolderTitle("Album")

                        .setDirectoryName("Image Picker")
                        .setMultipleMode(true)
                        .setShowNumberIndicator(true)
                        .setMaxSize(5)
                        .setLimitMessage("You can select up to 10 images")

                        .setRequestCode(111)
                        .start();


            }

        }

    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }


    public static File getFile(Context context, Uri uri) throws IOException {
        File destinationFilename = new File(context.getFilesDir().getPath() + File.separatorChar + queryName(context, uri));
        try (InputStream ins = context.getContentResolver().openInputStream(uri)) {
            createFileFromStream(ins, destinationFilename);
        } catch (Exception ex) {
            Log.e("Save File", ex.getMessage());
            ex.printStackTrace();
        }
        return destinationFilename;
    }

    private static String queryName(Context context, Uri uri) {
        Cursor returnCursor =
                context.getContentResolver().query(uri, null, null, null, null);
        assert returnCursor != null;
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = returnCursor.getString(nameIndex);
        returnCursor.close();
        return name;
    }

    public static void createFileFromStream(InputStream ins, File destination) {
        try (OutputStream os = new FileOutputStream(destination)) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = ins.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
        } catch (Exception ex) {
            Log.e("Save File", ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static Bitmap createThumbnail(Activity activity, String path) {
        MediaMetadataRetriever mediaMetadataRetriever = null;
        Bitmap bitmap = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(activity, Uri.parse(path));
            bitmap = mediaMetadataRetriever.getFrameAtTime(1000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 111) {
            if (ContextCompat.checkSelfPermission(AddAqarzStepsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            } else {


                ImagePicker.with(this)
                        .setFolderMode(true)
                        .setFolderTitle("Album")

                        .setDirectoryName("Image Picker")
                        .setMultipleMode(true)
                        .setShowNumberIndicator(true)
                        .setMaxSize(5)
                        .setLimitMessage("You can select up to 10 images")
                        .setRequestCode(111)
                        .start();


            }
        }
        if (requestCode == 1451) {
            if (ContextCompat.checkSelfPermission(AddAqarzStepsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


            } else {
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "video/*");

                startActivityForResult(Intent.createChooser(intent, "Select Video"), 1451);

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void action_plus_mins() {
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

                    Lounges_lay.setBackground(getResources().getDrawable(R.drawable.mash));
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
                    Lounges_lay.setBackground(getResources().getDrawable(R.drawable.mash));

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
                    room_lay.setBackground(getResources().getDrawable(R.drawable.mash));

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
                    room_lay.setBackground(getResources().getDrawable(R.drawable.mash));

                    room_text.setTextColor(getResources().getColor(R.color.black));

                }
                room_text.setText(number_room + "");


            }
        });


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
                    Bathrooms_lay.setBackground(getResources().getDrawable(R.drawable.mash));

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
                    Bathrooms_lay.setBackground(getResources().getDrawable(R.drawable.mash));

                    Bathrooms_text.setTextColor(getResources().getColor(R.color.black));

                }
                Bathrooms_text.setText(number_Bathrooms + "");


            }
        });

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
                    Boards_lay.setBackground(getResources().getDrawable(R.drawable.mash));

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
                    Boards_lay.setBackground(getResources().getDrawable(R.drawable.mash));
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
                    Kitchens_lay.setBackground(getResources().getDrawable(R.drawable.mash));

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
                    Kitchens_lay.setBackground(getResources().getDrawable(R.drawable.mash));
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
                    Dining_rooms_lay.setBackground(getResources().getDrawable(R.drawable.mash));

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
                    Dining_rooms_lay.setBackground(getResources().getDrawable(R.drawable.mash));
                    Dining_text.setTextColor(getResources().getColor(R.color.black));

                }
                Dining_text.setText(number_Dining_rooms + "");


            }
        });

    }

    public void comfort_() {


        //-----------------------------------------------------------------------------------------
        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, AddAqarzStepsActivity.this);

        mVolleyService.getDataVolley("comfort", WebService.comfort);

        WebService.loading(AddAqarzStepsActivity.this, false);


        lifts_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_lifts = Integer.valueOf(lifts_txt.getText().toString());


                number_lifts++;


                lifts_txt.setText(number_lifts + "");

                if (number_lifts > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    lifts_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    lifts_txt.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));

                    lifts_lay.setBackground(getResources().getDrawable(R.drawable.mash));
                    lifts_txt.setTextColor(getResources().getColor(R.color.black));

                }


            }
        });
        lifts_mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_lifts = Integer.valueOf(lifts_txt.getText().toString());


                if (number_lifts == 0) {

                } else {
                    number_lifts--;

                }

//                if (number_Lounges > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
//
//                }
                if (number_lifts > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    lifts_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    lifts_txt.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
                    lifts_lay.setBackground(getResources().getDrawable(R.drawable.mash));

                    lifts_txt.setTextColor(getResources().getColor(R.color.black));

                }
                lifts_txt.setText(number_lifts + "");


            }
        });


        parking_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_parking = Integer.valueOf(parking_txt.getText().toString());


                number_parking++;


                parking_txt.setText(number_parking + "");

                if (number_parking > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    parking_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    parking_txt.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));

                    parking_lay.setBackground(getResources().getDrawable(R.drawable.mash));
                    parking_txt.setTextColor(getResources().getColor(R.color.black));

                }


            }
        });
        parking_mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number_parking = Integer.valueOf(parking_txt.getText().toString());


                if (number_parking == 0) {

                } else {
                    number_parking--;

                }

//                if (number_Lounges > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
//
//                }
                if (number_parking > 0) {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    parking_lay.setBackground(getResources().getDrawable(R.drawable.button_login));
                    parking_txt.setTextColor(getResources().getColor(R.color.white));
                } else {
//                    Lounges_number.setBackgroundColor(getResources().getColor(R.color.white));
                    parking_lay.setBackground(getResources().getDrawable(R.drawable.back_search_homecc));

                    parking_txt.setTextColor(getResources().getColor(R.color.black));

                }
                parking_txt.setText(number_parking + "");


            }
        });


    }

    public void owner_info() {


        individual_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addAqarezObject.setAdvertiser_side("individual");
                individual_txt.setBackground(getResources().getDrawable(R.drawable.button_login));
                individual_txt.setTextColor(getResources().getColor(R.color.white));
                facility_txt.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                facility_txt.setTextColor(getResources().getColor(R.color.black));


            }
        });
        facility_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAqarezObject.setAdvertiser_side("facility");
                facility_txt.setBackground(getResources().getDrawable(R.drawable.button_login));
                facility_txt.setTextColor(getResources().getColor(R.color.white));
                individual_txt.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                individual_txt.setTextColor(getResources().getColor(R.color.black));


            }
        });
        owner_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addAqarezObject.setAdvertiser_character("onwer");
                owner_txt.setBackground(getResources().getDrawable(R.drawable.button_login));
                owner_txt.setTextColor(getResources().getColor(R.color.white));
                authorized_txt.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                authorized_txt.setTextColor(getResources().getColor(R.color.black));


            }
        });
        authorized_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAqarezObject.setAdvertiser_character("authorized");
                authorized_txt.setBackground(getResources().getDrawable(R.drawable.button_login));
                authorized_txt.setTextColor(getResources().getColor(R.color.white));
                owner_txt.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                owner_txt.setTextColor(getResources().getColor(R.color.black));


            }
        });


    }

    public void yes_or_no_qustion() {

        is_disputes_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addAqarezObject.setIs_disputes("1");
                is_disputes_yes.setBackground(getResources().getDrawable(R.drawable.button_login));
                is_disputes_yes.setTextColor(getResources().getColor(R.color.white));
                is_disputes_no.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                is_disputes_no.setTextColor(getResources().getColor(R.color.black));


            }
        });
        is_disputes_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAqarezObject.setIs_disputes("0");
                is_disputes_no.setBackground(getResources().getDrawable(R.drawable.button_login));
                is_disputes_no.setTextColor(getResources().getColor(R.color.white));
                is_disputes_yes.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                is_disputes_yes.setTextColor(getResources().getColor(R.color.black));


            }
        });
        is_mortgage_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addAqarezObject.setIs_mortgage("1");
                is_mortgage_yes.setBackground(getResources().getDrawable(R.drawable.button_login));
                is_mortgage_yes.setTextColor(getResources().getColor(R.color.white));
                is_mortgage_no.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                is_mortgage_no.setTextColor(getResources().getColor(R.color.black));


            }
        });
        is_mortgage_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAqarezObject.setIs_mortgage("0");
                is_mortgage_no.setBackground(getResources().getDrawable(R.drawable.button_login));
                is_mortgage_no.setTextColor(getResources().getColor(R.color.white));
                is_mortgage_yes.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                is_mortgage_yes.setTextColor(getResources().getColor(R.color.black));


            }
        });
        is_obligations_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addAqarezObject.setIs_obligations("1");
                is_obligations_yes.setBackground(getResources().getDrawable(R.drawable.button_login));
                is_obligations_yes.setTextColor(getResources().getColor(R.color.white));
                is_obligations_no.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                is_obligations_no.setTextColor(getResources().getColor(R.color.black));


            }
        });
        is_obligations_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAqarezObject.setIs_obligations("0");
                is_obligations_no.setBackground(getResources().getDrawable(R.drawable.button_login));
                is_obligations_no.setTextColor(getResources().getColor(R.color.white));
                is_obligations_yes.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                is_obligations_yes.setTextColor(getResources().getColor(R.color.black));


            }
        });
        is_saudi_building_code_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addAqarezObject.setIs_saudi_building_code("1");
                is_saudi_building_code_yes.setBackground(getResources().getDrawable(R.drawable.button_login));
                is_saudi_building_code_yes.setTextColor(getResources().getColor(R.color.white));
                is_saudi_building_code_no.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                is_saudi_building_code_no.setTextColor(getResources().getColor(R.color.black));


            }
        });
        is_saudi_building_code_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAqarezObject.setIs_saudi_building_code("0");
                is_saudi_building_code_no.setBackground(getResources().getDrawable(R.drawable.button_login));
                is_saudi_building_code_no.setTextColor(getResources().getColor(R.color.white));
                is_saudi_building_code_yes.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                is_saudi_building_code_yes.setTextColor(getResources().getColor(R.color.black));


            }
        });
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {


                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(AddAqarzStepsActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                if (requestType.equals("SendOrder")) {


                } else {


                    try {
                        boolean status = response.getBoolean("status");
                        if (status) {
                            String data = response.getString("data");

                            JSONArray jsonArray = new JSONArray(data);

                            comfortModules.clear();

                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();

                                ComfortModules Store_M = gson.fromJson(mJson, ComfortModules.class);
                                comfortModules.add(Store_M);
                            }


                            comfort_RecyclerView.setLayoutManager(new GridLayoutManager(AddAqarzStepsActivity.this, 3));

                            RecyclerView_All_Comfort_in_fragment recyclerView_all_comfort_in_fragment = new RecyclerView_All_Comfort_in_fragment(AddAqarzStepsActivity.this, comfortModules);

                            recyclerView_all_comfort_in_fragment.addItemClickListener(new RecyclerView_All_Comfort_in_fragment.ItemClickListener() {
                                @Override
                                public void onItemClick(int position) {


                                    comfortModules.get(position).setIs_selected(!comfortModules.get(position).get_is_selected());
                                    addAqarezObject.setComfortModules(comfortModules);

                                }
                            });


                            comfort_RecyclerView.setAdapter(recyclerView_all_comfort_in_fragment);


                        } else {
                            String message = response.getString("message");

                            WebService.Make_Toast_color(AddAqarzStepsActivity.this, message, "error");
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


                WebService.loading(AddAqarzStepsActivity.this, false);
                WebService.Make_Toast_color(AddAqarzStepsActivity.this, error.getMessage(), "error");


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

    public void approval() {

        button_step_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (
                        addAqarezObject.getLan().equals("") |
                                area_text.getText().toString().equals("") |
                                lengths_add_text.getText().toString().equals("") |
                                streetwidthadd_text.getText().toString().equals("") |
                                Date_of_construction_text.getText().toString().equals("") |
                                sale_price_text.getText().toString().equals("") |
                                Warranties_duration_txt.getText().toString().equals("") |
                                mobile_edt.getText().toString().equals("") |
                                owner_edt.getText().toString().equals("") |
                                addAqarezObject.getAdvertiser_side().equals("") |
                                addAqarezObject.getAdvertiser_character().equals("")
                ) {
                    WebService.Make_Toast_color_info(AddAqarzStepsActivity.this, "عليك تعبئة الحقول المطلوبه اعلى", "error");


                } else {
                    add_aqares_information();
                }


            }
        });


    }

    public void add_aqares_information() {

        RequestParams sendObj = new RequestParams();

        try {

            sendObj.put("estate_type_id", addAqarezObject.getEstate_type_id());
//            sendObj.put("instrument_number", Type_work_select);
//            sendObj.put("instrument_file", Type_work_select);

            sendObj.put("pace_number", land_number_text.getText().toString());
//            sendObj.put("planned_number", land_number_text.getText().toString());


            sendObj.put("total_area", area_text.getText().toString());
            sendObj.put("floor_number", turn_number_text.getText().toString());
            sendObj.put("owner_name", owner_edt.getText().toString());
            sendObj.put("owner_mobile", mobile_edt.getText().toString());
            sendObj.put("lounges_number", number_Lounges + "");
            sendObj.put("bedroom_number", number_room + "");
            sendObj.put("bathrooms_number", number_Bathrooms + "");
            sendObj.put("boards_number", number_Boards_plus + "");
            sendObj.put("kitchen_number", number_Kitchens_plus + "");
            sendObj.put("dining_rooms_number", number_Dining_rooms + "");
            sendObj.put("estate_dimensions", lengths_add_text.getText().toString() + "");


            String interface_ = "";


            if (south_selected) {
                if (interface_.equals("")) {
                    interface_ = "south";

                } else {
                    interface_ = "," + "south";

                }
            }

            if (north_selected) {
                if (interface_.equals("")) {
                    interface_ = "north";

                } else {
                    interface_ = "," + "north";

                }
            }

            if (east_selected) {
                if (interface_.equals("")) {
                    interface_ = "east";

                } else {
                    interface_ = "," + "east";

                }
            }

            if (west_selected) {
                if (interface_.equals("")) {
                    interface_ = "west";

                } else {
                    interface_ = "," + "west";

                }
            }

            sendObj.put("interface", interface_);

            sendObj.put("street_view", streetwidthadd_text.getText().toString());
            sendObj.put("estate_age", Date_of_construction_text.getText().toString());
            sendObj.put("total_price", sale_price_text.getText().toString());
            sendObj.put("is_disputes", addAqarezObject.getIs_disputes());


//            sendObj.put("finishing_type", finishing_type);//'deluxe','average','normal'
//            sendObj.put("social_status", finishing_type);//'unmarried','married'
//            sendObj.put("attachment_planned", 44545);


            String comfort_list_ = "";

            for (int i = 0; i < comfortModules.size(); i++) {

                if (comfortModules.get(i).get_is_selected()) {
                    if (comfort_list_.equals("")) {
                        comfort_list_ = comfortModules.get(i).getId() + "";
                    } else {
                        comfort_list_ = comfort_list_ + "," + comfortModules.get(i).getId() + "";

                    }

                }


            }

            sendObj.put("estate_comforts", comfort_list_ + "");


//            sendObj.put("attachment_estate", attachment_planned + "");
//            sendObj.put("note", attachment_planned + "");
//            sendObj.put("is_rent", attachment_planned + "");
//            sendObj.put("rent_type", attachment_planned + "");
//            sendObj.put("is_resident", addAqarezObject.isr + "");
//            sendObj.put("is_checked", addAqarezObject.isr + "");
//            sendObj.put("is_insured", addAqarezObject.isr + "");
//            sendObj.put("exclusive_contract_file", addAqarezObject.isr + "");

            sendObj.put("state_id", addAqarezObject.getState_id());
            sendObj.put("city_id", addAqarezObject.getCity_id());
            sendObj.put("neighborhood_id", addAqarezObject.getNeighborhood_id());
//            sendObj.put("street_name", street.getText().toString());
            sendObj.put("lat", addAqarezObject.getLat());
            sendObj.put("lan", addAqarezObject.getLan());


//            sendObj.put("in_fund_offer", street.getText().toString());
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


//            sendObj.put("attachment_planned[0]", Type_work_select);
            sendObj.put("is_mortgage", addAqarezObject.getIs_mortgage());
            sendObj.put("is_obligations", addAqarezObject.getIs_obligations());
            sendObj.put("is_saudi_building_code", addAqarezObject.getIs_saudi_building_code());
//            sendObj.put("touching_information","");

            sendObj.put("elevators_number", lifts_txt.getText().toString());
            sendObj.put("parking_spaces_numbers", parking_txt.getText().toString());
            sendObj.put("advertiser_side", addAqarezObject.getAdvertiser_side());
            sendObj.put("advertiser_character", addAqarezObject.getAdvertiser_character());
            sendObj.put("operation_type_id", addAqarezObject.getOperation_type_id());
            sendObj.put("estate_use_type", addAqarezObject.getEstate_use_type());


            if (addAqarezObject.getVideo() != null) {
                sendObj.put("video", addAqarezObject.getVideo());

            }


            System.out.println(sendObj.toString());


            AddAqersAsyncTask(sendObj);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void AddAqersAsyncTask(RequestParams requestParams) {


        AsyncHttpClient client = new AsyncHttpClient();
        String BASE_URL = WebService.addestate;
        WebService.loading(AddAqarzStepsActivity.this, true);


        final int DEFAULT_TIMEOUT = 50 * 1000;

        client.setTimeout(DEFAULT_TIMEOUT);
        WebService.Header_Async(client, true);

        client.post(BASE_URL, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {

                System.out.println("ADDAQARZ : " + responseBody.toString());
                WebService.loading(AddAqarzStepsActivity.this, false);


                try {
                    WebService.loading(AddAqarzStepsActivity.this, false);

                    String status = responseBody.getString("status");
//
                    String message = responseBody.getString("message");

                    if (status.equals("true")) {

                        WebService.loading(AddAqarzStepsActivity.this, false);


//
                        try {

                            BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(AddAqarzStepsActivity.this);
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

                    } else {


                        WebService.Make_Toast(AddAqarzStepsActivity.this, message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
//

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                System.out.println("responseString" + responseString);
                WebService.loading(AddAqarzStepsActivity.this, false);


                WebService.loading(AddAqarzStepsActivity.this, false);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                try {
                    System.out.println("responseString" + errorResponse.toString());

                    WebService.loading(AddAqarzStepsActivity.this, false);

                } catch (Exception e) {

                }

            }

            @Override
            public void onUserException(Throwable error) {


            }


            @Override
            public void onProgress(long bytesWritten, long totalSize) {


            }
        });

    }

    public void select_view() {


        north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (north_selected) {
                    north_selected = false;
                    north.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                    north.setTextColor(getResources().getColor(R.color.black));
                } else {
                    north_selected = true;
                    north.setBackground(getResources().getDrawable(R.drawable.button_login));
                    north.setTextColor(getResources().getColor(R.color.white));


                }


            }
        });
        south.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (south_selected) {
                    south_selected = false;
                    south.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                    south.setTextColor(getResources().getColor(R.color.black));

                } else {
                    south_selected = true;
                    south.setBackground(getResources().getDrawable(R.drawable.button_login));
                    south.setTextColor(getResources().getColor(R.color.white));


                }
            }
        });
        east.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (east_selected) {
                    east_selected = false;
                    east.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                    east.setTextColor(getResources().getColor(R.color.black));

                } else {
                    east_selected = true;
                    east.setBackground(getResources().getDrawable(R.drawable.button_login));
                    east.setTextColor(getResources().getColor(R.color.white));


                }
            }
        });
        west.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (west_selected) {
                    west_selected = false;
                    west.setBackground(getResources().getDrawable(R.drawable.back_edittext_add_aqarz));
                    west.setTextColor(getResources().getColor(R.color.black));

                } else {
                    west_selected = true;
                    west.setBackground(getResources().getDrawable(R.drawable.button_login));
                    west.setTextColor(getResources().getColor(R.color.white));


                }
            }
        });


    }

}
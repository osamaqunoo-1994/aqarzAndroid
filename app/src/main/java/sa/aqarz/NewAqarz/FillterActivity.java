package sa.aqarz.NewAqarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.AddAqarz.AddAqarzActivity;
import sa.aqarz.Activity.FiltterOrderActivity;
import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Activity.QRCameraActivity;
import sa.aqarz.Activity.profile.AllclintActivity;
import sa.aqarz.Adapter.RecyclerView_All_Comfort_in_fragment;
import sa.aqarz.Modules.ComfortModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_All_type_fillter;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_All_type_in_home_fragment;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_comfort_fillter;
import sa.aqarz.NewAqarz.Fragments.HomeMapFragment;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class FillterActivity extends AppCompatActivity {
    TextView purchase;
    TextView rent;
    TextView investment;

    List<TypeModules> type_list = new ArrayList<>();

    String type = "";
    String type_filtter = "";


    RecyclerView all_type_aqarz;
    RecyclerView comfort;


    EditText Les_price;
    EditText Maximum_price;
    EditText Les_space;
    EditText Maximum_space;

    EditText Date_of_construction_text;
    ImageView back;

    LinearLayout Lounges_lay;
    LinearLayout room_lay;
    LinearLayout Bathrooms_lay;
    LinearLayout Dining_rooms_lay;
    LinearLayout Boards_lay;
    LinearLayout Kitchens_lay;


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

    int number_Lounges = 0;
    int number_room = 0;
    int number_Bathrooms = 0;
    int number_Boards_plus = 0;
    int number_Kitchens_plus = 0;
    int number_Dining_rooms = 0;


    TextView north;
    TextView south;
    TextView east;
    TextView west;

    boolean north_selected = false;
    boolean south_selected = false;
    boolean east_selected = false;
    boolean west_selected = false;


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

    TextView aqarez_lay;
    TextView aqarezman_lay;

    LinearLayout aqarezman_lay_line;
    LinearLayout aqarez_lay_line;


    LinearLayout lay1;
    LinearLayout lay2;


    EditText name_aqarz;

    Button search_aqarz_man;
    RelativeLayout search_qr;

    Button search_filtter;
    String from = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillter);


        search_filtter = findViewById(R.id.search_filtter);
        back = findViewById(R.id.back);
        search_qr = findViewById(R.id.search_qr);
        search_aqarz_man = findViewById(R.id.search_aqarz_man);
        name_aqarz = findViewById(R.id.name_aqarz);


        lay1 = findViewById(R.id.lay1);
        lay2 = findViewById(R.id.lay2);

        aqarez_lay = findViewById(R.id.aqarez_lay);
        aqarezman_lay = findViewById(R.id.aqarezman_lay);
        aqarezman_lay_line = findViewById(R.id.aqarezman_lay_line);
        aqarez_lay_line = findViewById(R.id.aqarez_lay_line);

        purchase = findViewById(R.id.purchase);
        rent = findViewById(R.id.rent);
        investment = findViewById(R.id.investment);
        all_type_aqarz = findViewById(R.id.all_type_aqarz);
        Les_price = findViewById(R.id.Les_price);
        Maximum_price = findViewById(R.id.Maximum_price);
        Les_space = findViewById(R.id.Les_space);
        Maximum_space = findViewById(R.id.Maximum_space);

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


        north = findViewById(R.id.north);
        south = findViewById(R.id.south);
        east = findViewById(R.id.east);
        west = findViewById(R.id.west);

        comfort = findViewById(R.id.comfort);
        Date_of_construction_text = findViewById(R.id.Date_of_construction_text);
        lifts_lay = findViewById(R.id.lifts_lay);
        lifts_plus = findViewById(R.id.lifts_plus);
        lifts_mins = findViewById(R.id.lifts_mins);
        lifts_txt = findViewById(R.id.lifts_txt);


        parking_lay = findViewById(R.id.parking_lay);
        parking_plus = findViewById(R.id.parking_plus);
        parking_mins = findViewById(R.id.parking_mins);
        parking_txt = findViewById(R.id.parking_txt);


        try {
            from = getIntent().getStringExtra("from");

        } catch (Exception e) {

        }


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(FillterActivity.this, LinearLayoutManager.HORIZONTAL, false);
        all_type_aqarz.setLayoutManager(layoutManager1);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(FillterActivity.this, LinearLayoutManager.HORIZONTAL, false);
        comfort.setLayoutManager(layoutManager);


        change_lay();
        onclick();
        action_plus_mins();
        select_view();

        //-----------------------------------------------------------------------------------------
        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, FillterActivity.this);

        mVolleyService.getDataVolley("comfort", WebService.comfort);

        WebService.loading(FillterActivity.this, false);


    }

    public void change_lay() {
        aqarez_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aqarez_lay.setTextColor(getResources().getColor(R.color.colorPrimary));
                aqarezman_lay.setTextColor(getResources().getColor(R.color.te_unselected));


                aqarez_lay_line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                aqarezman_lay_line.setBackgroundColor(getResources().getColor(R.color.te_unselected));


            }
        });
        aqarezman_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aqarezman_lay.setTextColor(getResources().getColor(R.color.colorPrimary));
                aqarez_lay.setTextColor(getResources().getColor(R.color.te_unselected));

                aqarez_lay_line.setBackgroundColor(getResources().getColor(R.color.te_unselected));
                aqarezman_lay_line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            }
        });
    }

    public void onclick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (type.equals("purchase")) {
                    type = "";

                    purchase.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    rent.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    investment.setBackground(getResources().getDrawable(R.drawable.border_fillter));

                    purchase.setTextColor(getResources().getColor(R.color.textColor));
                    rent.setTextColor(getResources().getColor(R.color.textColor));
                    investment.setTextColor(getResources().getColor(R.color.textColor));


                } else {
                    type = "purchase";

                    purchase.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                    rent.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    investment.setBackground(getResources().getDrawable(R.drawable.border_fillter));

                    purchase.setTextColor(getResources().getColor(R.color.white));
                    rent.setTextColor(getResources().getColor(R.color.textColor));
                    investment.setTextColor(getResources().getColor(R.color.textColor));

                }


            }
        });

        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (type.equals("rent")) {
                    type = "";

                    purchase.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    rent.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    investment.setBackground(getResources().getDrawable(R.drawable.border_fillter));

                    purchase.setTextColor(getResources().getColor(R.color.textColor));
                    rent.setTextColor(getResources().getColor(R.color.textColor));
                    investment.setTextColor(getResources().getColor(R.color.textColor));

                } else {
                    type = "rent";

                    purchase.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    rent.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                    investment.setBackground(getResources().getDrawable(R.drawable.border_fillter));


                    purchase.setTextColor(getResources().getColor(R.color.textColor));
                    rent.setTextColor(getResources().getColor(R.color.white));
                    investment.setTextColor(getResources().getColor(R.color.textColor));

                }

            }
        });

        investment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (type.equals("investment")) {
                    type = "";

                    purchase.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    rent.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    investment.setBackground(getResources().getDrawable(R.drawable.border_fillter));

                    purchase.setTextColor(getResources().getColor(R.color.textColor));
                    rent.setTextColor(getResources().getColor(R.color.textColor));
                    investment.setTextColor(getResources().getColor(R.color.textColor));

                } else {
                    type = "investment";

                    purchase.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    rent.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    investment.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));

                    purchase.setTextColor(getResources().getColor(R.color.textColor));
                    rent.setTextColor(getResources().getColor(R.color.textColor));
                    investment.setTextColor(getResources().getColor(R.color.white));


                }

            }
        });

        if (MainAqarzActivity.object_filtter.getType_list() != null) {
            if (MainAqarzActivity.object_filtter.getType_list().size() != 0) {
                type_list = MainAqarzActivity.object_filtter.getType_list();
            } else {
                type_list = Settings.getSettings().getEstate_types().getOriginal().getData();
            }


        } else {
            type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        }
        RecyclerView_All_type_fillter recyclerView_all_type_in_fragment = new RecyclerView_All_type_fillter(FillterActivity.this, type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_fillter.ItemClickListener() {
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


            }
        });
        all_type_aqarz.setAdapter(recyclerView_all_type_in_fragment);


        search_aqarz_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    Intent intent = new Intent(FillterActivity.this, AllclintActivity.class);
                    intent.putExtra("search_text", name_aqarz.getText().toString() + "");
                    startActivity(intent);

                } catch (Exception e) {

                }
            }
        });
        search_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(FillterActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(FillterActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                121);

                    } else {

                        Intent intent = new Intent(FillterActivity.this, QRCameraActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });
        search_filtter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MainAqarzActivity.object_filtter.setDate(Date_of_construction_text.getText().toString());
                MainAqarzActivity.object_filtter.setType_aqarz_view(type);
                MainAqarzActivity.object_filtter.setType_aqarz(type_filtter);
                MainAqarzActivity.object_filtter.setComfortModules(comfortModules);
                MainAqarzActivity.object_filtter.setLess_price(Les_price.getText().toString());
                MainAqarzActivity.object_filtter.setMax_price(Maximum_price.getText().toString());
                MainAqarzActivity.object_filtter.setLess_space(Les_space.getText().toString());
                MainAqarzActivity.object_filtter.setMax_space(Maximum_space.getText().toString());

                MainAqarzActivity.object_filtter.setEast_selected(east_selected);
                MainAqarzActivity.object_filtter.setWest_selected(west_selected);
                MainAqarzActivity.object_filtter.setNorth_selected(north_selected);
                MainAqarzActivity.object_filtter.setSouth_selected(south_selected);


                MainAqarzActivity.object_filtter.setNumber_Lounges(number_Lounges);
                MainAqarzActivity.object_filtter.setNumber_room(number_room);
                MainAqarzActivity.object_filtter.setNumber_Bathrooms(number_Bathrooms);
                MainAqarzActivity.object_filtter.setNumber_Boards_plus(number_Boards_plus);
                MainAqarzActivity.object_filtter.setNumber_Kitchens_plus(number_Kitchens_plus);
                MainAqarzActivity.object_filtter.setNumber_Dining_rooms(number_Dining_rooms);


                MainAqarzActivity.object_filtter.setNumber_parking(number_parking);
                MainAqarzActivity.object_filtter.setNumber_lifts(number_lifts);

                if (from.equals("home")) {
                    HomeMapFragment.get_Estate_from_api();
                } else {
                    ListAqarzActivity.get_data();
                }
                finish();


            }
        });

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
                    parking_lay.setBackground(getResources().getDrawable(R.drawable.mash));

                    parking_txt.setTextColor(getResources().getColor(R.color.black));

                }
                parking_txt.setText(number_parking + "");


            }
        });


    }

    public void select_view() {


        north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (north_selected) {
                    north_selected = false;
                    north.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    north.setTextColor(getResources().getColor(R.color.black));
                } else {
                    north_selected = true;
                    north.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                    north.setTextColor(getResources().getColor(R.color.white));


                }


            }
        });
        south.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (south_selected) {
                    south_selected = false;
                    south.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    south.setTextColor(getResources().getColor(R.color.black));

                } else {
                    south_selected = true;
                    south.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                    south.setTextColor(getResources().getColor(R.color.white));


                }
            }
        });
        east.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (east_selected) {
                    east_selected = false;
                    east.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    east.setTextColor(getResources().getColor(R.color.black));

                } else {
                    east_selected = true;
                    east.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                    east.setTextColor(getResources().getColor(R.color.white));


                }
            }
        });
        west.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (west_selected) {
                    west_selected = false;
                    west.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    west.setTextColor(getResources().getColor(R.color.black));

                } else {
                    west_selected = true;
                    west.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                    west.setTextColor(getResources().getColor(R.color.white));


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
                WebService.loading(FillterActivity.this, false);
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


//                            comfort.setLayoutManager(new GridLayoutManager(FillterActivity.this, 3));

                            RecyclerView_comfort_fillter recyclerView_all_comfort_in_fragment = new RecyclerView_comfort_fillter(FillterActivity.this, comfortModules);

                            recyclerView_all_comfort_in_fragment.addItemClickListener(new RecyclerView_comfort_fillter.ItemClickListener() {
                                @Override
                                public void onItemClick(int position) {


//                                    comfortModules.get(position).setIs_selected(!comfortModules.get(position).get_is_selected());
//                                    addAqarezObject.setComfortModules(comfortModules);

                                }
                            });

                            comfort.setAdapter(recyclerView_all_comfort_in_fragment);


                        } else {
                            String message = response.getString("message");

                            WebService.Make_Toast_color(FillterActivity.this, message, "error");
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


                WebService.loading(FillterActivity.this, false);
                WebService.Make_Toast_color(FillterActivity.this, error.getMessage(), "error");


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

}
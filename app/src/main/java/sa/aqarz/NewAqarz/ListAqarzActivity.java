package sa.aqarz.NewAqarz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.RecyclerView_HomeList_estat_new;
import sa.aqarz.Modules.AllEstate;
import sa.aqarz.Modules.AllNeigbers;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_All_type_in_home_List;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_All_type_in_home_fragment;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class ListAqarzActivity extends AppCompatActivity {
    RecyclerView all_type_aqarz;
    RecyclerView Allaqarz;
    List<TypeModules> type_list = new ArrayList<>();
    static RecyclerView_HomeList_estat_new recyclerView_homeList_estat_new;
    public static List<HomeModules_aqares> homeModules_aqares_list = new ArrayList<>();
    static IResult mResultCallback;

    static int page = 1;
    ImageView back;
    static String type_filtter = "";
    String type_filter_order = "";
    static String url_list = "";
    static ProgressBar loading;
    TextView order_by;
    ImageView filtter;

    public static Activity activity;

    static TextView nodata;
    ImageView fill_filtter;


    static String order_by_price = "0";
    static String order_by_area = "0";
    static String order_by_date = "0";
    static String order_by_rate = "0";
    static String order_by_bathrooms_number = "0";
    static String order_by_rooms_number = "0";

    ImageView clear_fi_by;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_aqarz);
        back = findViewById(R.id.back);
        order_by = findViewById(R.id.order_by);
        filtter = findViewById(R.id.filtter);
        nodata = findViewById(R.id.nodata);
        fill_filtter = findViewById(R.id.fill_filtter);
        clear_fi_by = findViewById(R.id.clear_fi_by);


        activity = this;
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


        order_by_price = "";
        order_by_area = "0";
        order_by_date = "0";
        order_by_rate = "0";
        order_by_bathrooms_number = "0";
        order_by_rooms_number = "0";


        init();
    }

    public void init() {

        try {

            type_filtter = getIntent().getStringExtra("type_filtter");
        } catch (Exception e) {

        }


        all_type_aqarz = findViewById(R.id.all_type_aqarz);
        loading = findViewById(R.id.loading);
        Allaqarz = findViewById(R.id.Allaqarz);
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(ListAqarzActivity.this, LinearLayoutManager.HORIZONTAL, false);
        all_type_aqarz.setLayoutManager(layoutManager1);
        LinearLayoutManager layoutManagerss
                = new LinearLayoutManager(ListAqarzActivity.this, LinearLayoutManager.VERTICAL, false);
        Allaqarz.setLayoutManager(layoutManagerss);


        if (MainAqarzActivity.object_filtter.getType_list() != null) {
            if (MainAqarzActivity.object_filtter.getType_list().size() != 0) {
                type_list = MainAqarzActivity.object_filtter.getType_list();
            } else {
                type_list = Settings.getSettings().getEstate_types().getOriginal().getData();
            }


        } else {
            type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        }
        RecyclerView_All_type_in_home_List recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_home_List(ListAqarzActivity.this, type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_home_List.ItemClickListener() {
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
//

                MainAqarzActivity.object_filtter.setType_aqarz(type_filtter);
                MainAqarzActivity.object_filtter.setType_list(typeModules);

                get_data();


            }
        });
        all_type_aqarz.setAdapter(recyclerView_all_type_in_fragment);


        page = 1;

        recyclerView_homeList_estat_new = new RecyclerView_HomeList_estat_new(ListAqarzActivity.this, homeModules_aqares_list);
        Allaqarz.setAdapter(recyclerView_homeList_estat_new);
        Allaqarz.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) { //1 for down

                    page = page + 1;

                    loading.setVisibility(View.VISIBLE);

//                    WebService.loading(activity, true);
                    init_volley();
                    VolleyService mVolleyService = new VolleyService(mResultCallback, ListAqarzActivity.this);


                    mVolleyService.getAsync("home_estate_custom_list_more_1", url_list + "&page=" + page);


                }
            }
        });
        filtter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListAqarzActivity.this, FillterActivity.class);
                intent.putExtra("from", "list");

                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        order_by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View mView = LayoutInflater.from(ListAqarzActivity.this).inflate(R.layout.drop_down_layout, null, false);
                final PopupWindow popUp = new PopupWindow(mView, LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, false);

                TextView s1 = mView.findViewById(R.id.s1);
                TextView s2 = mView.findViewById(R.id.s2);
                TextView s3 = mView.findViewById(R.id.s3);
                TextView s4 = mView.findViewById(R.id.s4);
                TextView s5 = mView.findViewById(R.id.s5);
                TextView s6 = mView.findViewById(R.id.s6);

                if (type_filter_order.equals("s1")) {
                    s1.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                    s2.setBackground(null);
                    s3.setBackground(null);
                    s4.setBackground(null);
                    s5.setBackground(null);
                    s6.setBackground(null);

                } else if (type_filter_order.equals("s2")) {
                    s2.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                    s1.setBackground(null);
                    s3.setBackground(null);
                    s4.setBackground(null);
                    s5.setBackground(null);
                    s6.setBackground(null);
                } else if (type_filter_order.equals("s3")) {
                    s3.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                    s2.setBackground(null);
                    s1.setBackground(null);
                    s4.setBackground(null);
                    s5.setBackground(null);
                    s6.setBackground(null);
                } else if (type_filter_order.equals("s4")) {
                    s4.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                    s2.setBackground(null);
                    s3.setBackground(null);
                    s1.setBackground(null);
                    s5.setBackground(null);
                    s6.setBackground(null);
                } else if (type_filter_order.equals("s5")) {
                    s5.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                    s2.setBackground(null);
                    s3.setBackground(null);
                    s4.setBackground(null);
                    s1.setBackground(null);
                    s6.setBackground(null);

                } else if (type_filter_order.equals("s6")) {
                    s6.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                    s2.setBackground(null);
                    s3.setBackground(null);
                    s4.setBackground(null);
                    s5.setBackground(null);
                    s1.setBackground(null);
                }
                s1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        type_filter_order = "s1";
                        s1.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                        s2.setBackground(null);
                        s3.setBackground(null);
                        s4.setBackground(null);
                        s5.setBackground(null);
                        s6.setBackground(null);
                        popUp.dismiss();
                        order_by_price = "price_desc";
                        order_by_area = "0";
                        order_by_date = "0";
                        order_by_rate = "0";
                        order_by_bathrooms_number = "0";
                        order_by_rooms_number = "0";
                        get_data();
                        clear_fi_by.setVisibility(View.VISIBLE);
                        order_by.setText(getResources().getString(R.string.awaw1));

                    }
                });
                s2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        type_filter_order = "s2";

                        s2.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                        s1.setBackground(null);
                        s3.setBackground(null);
                        s4.setBackground(null);
                        s5.setBackground(null);
                        s6.setBackground(null);
                        popUp.dismiss();
                        order_by_price = "price_asc";
                        order_by_area = "1";
                        order_by_date = "0";
                        order_by_rate = "0";
                        order_by_bathrooms_number = "0";
                        order_by_rooms_number = "0";
                        get_data();
                        clear_fi_by.setVisibility(View.VISIBLE);
                        order_by.setText(getResources().getString(R.string.awaw2));

                    }
                });
                s3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        type_filter_order = "s3";

                        s3.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                        s2.setBackground(null);
                        s1.setBackground(null);
                        s4.setBackground(null);
                        s5.setBackground(null);
                        s6.setBackground(null);
                        popUp.dismiss();
                        order_by_price = "date";
                        order_by_area = "0";
                        order_by_date = "1";
                        order_by_rate = "0";
                        order_by_bathrooms_number = "0";
                        order_by_rooms_number = "0";
                        get_data();
                        clear_fi_by.setVisibility(View.VISIBLE);
                        order_by.setText(getResources().getString(R.string.awaw3));

                    }
                });
                s4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        type_filter_order = "s4";

                        s4.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                        s2.setBackground(null);
                        s3.setBackground(null);
                        s1.setBackground(null);
                        s5.setBackground(null);
                        s6.setBackground(null);
                        popUp.dismiss();
                        order_by_price = "rate";
                        order_by_area = "0";
                        order_by_date = "0";
                        order_by_rate = "1";
                        order_by_bathrooms_number = "0";
                        order_by_rooms_number = "0";
                        get_data();
                        clear_fi_by.setVisibility(View.VISIBLE);
                        order_by.setText(getResources().getString(R.string.awaw4));

                    }
                });
                s5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        type_filter_order = "s5";

                        s5.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                        s2.setBackground(null);
                        s3.setBackground(null);
                        s4.setBackground(null);
                        s1.setBackground(null);
                        s6.setBackground(null);
                        popUp.dismiss();
                        order_by_price = "0";
                        order_by_area = "0";
                        order_by_date = "0";
                        order_by_rate = "0";
                        order_by_bathrooms_number = "1";
                        order_by_rooms_number = "0";
                        get_data();
                        clear_fi_by.setVisibility(View.VISIBLE);
                        order_by.setText(getResources().getString(R.string.BathrommNumber));

                    }
                });
                s6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        type_filter_order = "s6";

                        s6.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                        s2.setBackground(null);
                        s3.setBackground(null);
                        s4.setBackground(null);
                        s5.setBackground(null);
                        s1.setBackground(null);
                        popUp.dismiss();
                        order_by_price = "0";
                        order_by_area = "0";
                        order_by_date = "0";
                        order_by_rate = "0";
                        order_by_bathrooms_number = "0";
                        order_by_rooms_number = "1";
                        get_data();
                        clear_fi_by.setVisibility(View.VISIBLE);
                        order_by.setText(getResources().getString(R.string.rommNumber));

                    }
                });


                popUp.setTouchable(true);
                popUp.setFocusable(true);
                popUp.setOutsideTouchable(true);

                //Solution
                popUp.showAsDropDown(order_by);
            }
        });

        clear_fi_by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_fi_by.setVisibility(View.GONE);
                order_by_price = "0";
                order_by_area = "0";
                order_by_date = "0";
                order_by_rate = "0";
                order_by_bathrooms_number = "0";
                order_by_rooms_number = "0";
                order_by.setText("");

                type_filter_order = "";

                get_data();

            }
        });
        get_data();
    }


    public static void get_data() {


        page = 1;

        String estate_pay_type = "";

        if (!MainAqarzActivity.object_filtter.getEstate_pay_type().equals("")) {

            if (MainAqarzActivity.object_filtter.getEstate_pay_type().equals("is_pay")) {
                estate_pay_type = "&operation_type_id=" + 1;

            } else if (MainAqarzActivity.object_filtter.getEstate_pay_type().equals("is_rent")) {
                estate_pay_type = "&operation_type_id=" + 2;

            } else {
                estate_pay_type = "&operation_type_id=" + 3;

            }
        }
        String state_id = "";

        if (!MainAqarzActivity.object_filtter.getId_state().equals("")) {
            state_id = "&state_id=" + MainAqarzActivity.object_filtter.getId_state();
        }

        String price_to = "";

        if (!MainAqarzActivity.object_filtter.getMax_price().equals("")) {
            price_to = "&price_to=" + MainAqarzActivity.object_filtter.getMax_price();
        }


        String price_from = "";

        if (!MainAqarzActivity.object_filtter.getLess_price().equals("")) {
            price_from = "&price_from=" + MainAqarzActivity.object_filtter.getLess_price();
        }
        String is_rent_installment = "";

        if (!MainAqarzActivity.object_filtter.getIs_rent_installment().equals("0")) {
            is_rent_installment = "&is_rent_installment=" + MainAqarzActivity.object_filtter.getIs_rent_installment();
        }

        String area_from = "";

        if (!MainAqarzActivity.object_filtter.getLess_space().equals("")) {
            area_from = "&area_from=" + MainAqarzActivity.object_filtter.getLess_space();
        }
        String area_to = "";

        if (!MainAqarzActivity.object_filtter.getMax_space().equals("")) {
            area_to = "&area_to=" + MainAqarzActivity.object_filtter.getMax_space();
        }


        String room = "";

        if (MainAqarzActivity.object_filtter.getNumber_room() != 0) {
            room = "&room=" + MainAqarzActivity.object_filtter.getNumber_room();
        }
        String lounges_number = "";

        if (MainAqarzActivity.object_filtter.getNumber_Lounges() != 0) {
            lounges_number = "&lounges_number=" + MainAqarzActivity.object_filtter.getNumber_Lounges();
        }
        String bathrooms_number = "";

        if (MainAqarzActivity.object_filtter.getNumber_Bathrooms() != 0) {
            bathrooms_number = "&bathrooms_number=" + MainAqarzActivity.object_filtter.getNumber_Bathrooms();
        }
        String dining_rooms_number = "";

        if (MainAqarzActivity.object_filtter.getNumber_Dining_rooms() != 0) {
            dining_rooms_number = "&dining_rooms_number=" + MainAqarzActivity.object_filtter.getNumber_Dining_rooms();
        }

        String boards_number = "";

        if (MainAqarzActivity.object_filtter.getNumber_Boards_plus() != 0) {
            boards_number = "&boards_number=" + MainAqarzActivity.object_filtter.getNumber_Boards_plus();
        }

        String elevators_number = "";

        if (MainAqarzActivity.object_filtter.getNumber_lifts() != 0) {
            elevators_number = "&elevators_number=" + MainAqarzActivity.object_filtter.getNumber_lifts();
        }
        String kitchen_number = "";

        if (MainAqarzActivity.object_filtter.getNumber_Kitchens_plus() != 0) {
            kitchen_number = "&kitchen_number=" + MainAqarzActivity.object_filtter.getNumber_Kitchens_plus();
        }
        String estate_age = "";

        if (!MainAqarzActivity.object_filtter.getDate().equals("")) {
            estate_age = "&estate_age=" + MainAqarzActivity.object_filtter.getDate();
        }


//        String type_filtter_ = "";
//        if (!type_filtter.equals("")) {
//            type_filtter_ = "&estate_type=" + type_filtter;
//        }
        String type_filtter_ = "";
        if (!MainAqarzActivity.object_filtter.getType_aqarz().equals("")) {
            type_filtter_ = "&estate_type=" + MainAqarzActivity.object_filtter.getType_aqarz();
        }

//
        String fil_by = "&" + "order_by=" + order_by_price;
//                + "&order_by_area=" + order_by_area
//                + "&order_by_date=" + order_by_date
//                + "&order_by_rate=" + order_by_rate
//                + "&order_by_bathrooms_number=" + order_by_bathrooms_number
//                + "&order_by_rooms_number=" + order_by_rooms_number;


        WebService.loading(activity, true);
        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, activity);

        url_list = WebService.home_estate_custom_list + "?" + type_filtter_ + elevators_number + fil_by + state_id + kitchen_number + is_rent_installment + estate_age + boards_number + dining_rooms_number + bathrooms_number + lounges_number + room + area_from + area_to + price_to + price_from + estate_pay_type;

//        url_list = WebService.home_estate_custom_list + "?" + type_filtter_;
        mVolleyService.getAsync("home_estate_custom_list_more_1", url_list);

    }

    @Override
    protected void onResume() {

        if (Hawk.contains("filtter")) {
            if (Hawk.get("filtter").toString().equals("yes")) {
                fill_filtter.setVisibility(View.VISIBLE);
            } else {
                fill_filtter.setVisibility(View.GONE);

            }

        }
        super.onResume();
    }

    public static void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    loading.setVisibility(View.GONE);

                    boolean status = response.getBoolean("status");
                    if (status) {
                        WebService.loading(activity, false);

                        if (requestType.equals("home_estate_custom_list_more_1")) {

                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(response.toString());

                            Gson gson = new Gson();

                            if (page == 1) {
                                homeModules_aqares_list.clear();
                            }

                            AllEstate allNeigbers = gson.fromJson(mJson, AllEstate.class);

                            homeModules_aqares_list.addAll(allNeigbers.getData().getData());

                            recyclerView_homeList_estat_new.Refr();

                            if (homeModules_aqares_list.size() == 0) {
                                nodata.setVisibility(View.VISIBLE);
                            } else {
                                nodata.setVisibility(View.GONE);

                            }
//                            set_locationEstate(allNeigbers.getData().getData());
//                            all_estate_size.setVisibility(View.VISIBLE);


                        } else {
                            String message = response.getString("message");
                            WebService.Make_Toast_color(activity, message, "success");

                        }


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(activity, message, "error");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());

                try {
                    loading.setVisibility(View.GONE);

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(activity, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


                WebService.loading(activity, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                loading.setVisibility(View.GONE);

            }
        }

        ;


    }

}
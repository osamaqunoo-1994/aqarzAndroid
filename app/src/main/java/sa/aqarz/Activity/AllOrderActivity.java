package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import java.util.Locale;

import sa.aqarz.Activity.OprationNew.AqarzOrActivity;
import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import sa.aqarz.Adapter.RecyclerView_city_side_menu;
import sa.aqarz.Adapter.RecyclerView_orders_demandsx;
import sa.aqarz.Adapter.RecyclerView_ordersx;
import sa.aqarz.Dialog.BottomSheetDialogFragment_area_range;
import sa.aqarz.Dialog.BottomSheetDialogFragment_price_range;
import sa.aqarz.Fragment.mapsHome.MapsFragmentNew;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.R;
import sa.aqarz.Settings.LocaleUtils;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class AllOrderActivity extends AppCompatActivity {

    static IResult mResultCallback;


    TextView premium;
    ImageView favorit_button;
    ImageView more_filtter_image;

    static TextView Myoffer_number;
    static TextView today_number;
    static TextView AllOrder_number;

    TextView AllOrder;
    TextView today;
    TextView Myoffer;

    TextView searcha_btn;
    static RecyclerView AllResultRec;

    static EditText search_text;

    TextView status_1;
    TextView status_2;
    TextView status_3;
    DrawerLayout drawer;

    ImageView close;
    ImageView open_filtter;
    TextView select_space;
    TextView select_price;
    TextView filtter_btn;
    TextView clear_fillter;
    static RecyclerView all_type;
    static RecyclerView allcity;
    static EditText search_citytext;

    ProgressBar progress;
    static LinearLayout nodata_vis;
    LinearLayout not_premium;
    static LinearLayout allfilter;
    LinearLayoutManager layoutManager;
    static LinearLayout filtter_city;
    static String offer_status = "";
    static String type_requst = "today";
    static String area_estate_id = "";
    static String estate_type_id = "";
    static String city_id = "";
    static String price_id = "";
    public static boolean searh = true;
    public static boolean isLoading = false;

    static Activity activity;
    static List<OrdersModules> ordersModules = new ArrayList<>();
    static List<TypeModules> type_list = new ArrayList<>();
    static List<CityModules> cityModules_list_filtter = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);
        activity = this;

        AllResultRec = findViewById(R.id.AllResultRec);
        filtter_city = findViewById(R.id.filtter_city);
        premium = findViewById(R.id.premium);
        select_space = findViewById(R.id.select_space);
        select_price = findViewById(R.id.select_price);
        all_type = findViewById(R.id.all_type);
        search_citytext = findViewById(R.id.search_citytext);
        close = findViewById(R.id.close);

        status_1 = findViewById(R.id.status_1);
        status_2 = findViewById(R.id.status_2);
        status_3 = findViewById(R.id.status_3);
        allcity = findViewById(R.id.allcity);

        nodata_vis = findViewById(R.id.nodata_vis);
        allfilter = findViewById(R.id.allfilter);
        not_premium = findViewById(R.id.not_premium);
        favorit_button = findViewById(R.id.favorit_button);
        clear_fillter = findViewById(R.id.clear_fillter);

        search_text = findViewById(R.id.search_text);
        more_filtter_image = findViewById(R.id.more_filtter_image);
        filtter_btn = findViewById(R.id.filtter_btn);
        open_filtter = findViewById(R.id.open_filtter);

        drawer = findViewById(R.id.drawer);

        AllOrder = findViewById(R.id.AllOrder);
        today = findViewById(R.id.today);
        Myoffer = findViewById(R.id.Myoffer);

        Myoffer_number = findViewById(R.id.Myoffer_number);
        today_number = findViewById(R.id.today_number);
        AllOrder_number = findViewById(R.id.AllOrder_number);
        searcha_btn = findViewById(R.id.searcha_btn);


        progress = findViewById(R.id.progress);


        layoutManager
                = new LinearLayoutManager(AllOrderActivity.this, LinearLayoutManager.VERTICAL, false);
        AllResultRec.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManager1a
                = new LinearLayoutManager(AllOrderActivity.this, LinearLayoutManager.VERTICAL, false);
        allcity.setLayoutManager(layoutManager1a);


        AllResultRec.addOnScrollListener(recyclerViewOnScrollListener);

        try {

            if (Hawk.contains("lang")) {


                Locale locale = new Locale(Hawk.get("lang").toString());
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
            } else {

                Hawk.put("lang", LocaleUtils.getLanguage(AllOrderActivity.this));

                Locale locale = new Locale(Hawk.get("lang").toString());
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().equals("1")) {
            premium.setVisibility(View.VISIBLE);
//            offer.setVisibility(View.VISIBLE);
            not_premium.setVisibility(View.GONE);

        } else {

            premium.setVisibility(View.GONE);
//            offer.setVisibility(View.GONE);
            not_premium.setVisibility(View.VISIBLE);
        }
        set_city_fillter();
        setdata();
        action_button();
        setTypeFilter();
    }

    private final RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();


            System.out.println("visibleItemCount" + visibleItemCount);
            System.out.println("firstVisibleItemPosition" + firstVisibleItemPosition);
            System.out.println("totalItemCount" + totalItemCount);

            if (isLoading) {

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= 10) {
                    System.out.println("*&*&*&*&*&*&*");

                }
            }
        }
    };

    public void setdata() {
        status_1.setVisibility(View.GONE);
        status_2.setVisibility(View.GONE);
        status_3.setVisibility(View.GONE);


        AllOrder_number.setText(Settings.getSettings().getAllRequestFund() + "");
        today_number.setText(Settings.getSettings().getRequestFund() + "");
        Myoffer_number.setText(Settings.getSettings().getMyRequestFundOffer() + "");

        setFiltter();

    }

    public void action_button() {

        status_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (offer_status.equals("customer_accepted")) {
                    status_1.setBackground(null);
                    status_2.setBackground(null);
                    status_3.setBackground(null);
                    status_1.setTextColor(getResources().getColor(R.color.black));
                    status_2.setTextColor(getResources().getColor(R.color.black));
                    status_3.setTextColor(getResources().getColor(R.color.black));

                    offer_status = "";
                    setFiltter();
                } else {
                    status_1.setBackground(getResources().getDrawable(R.drawable.background_b2select));
                    status_1.setTextColor(getResources().getColor(R.color.white));


                    status_2.setBackground(null);
                    status_3.setBackground(null);
                    status_2.setTextColor(getResources().getColor(R.color.black));
                    status_3.setTextColor(getResources().getColor(R.color.black));

                    offer_status = "customer_accepted";
                    setFiltter();
                }


            }
        });

        status_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (offer_status.equals("sending_code")) {
                    status_1.setBackground(null);
                    status_2.setBackground(null);
                    status_3.setBackground(null);
                    status_1.setTextColor(getResources().getColor(R.color.black));
                    status_2.setTextColor(getResources().getColor(R.color.black));
                    status_3.setTextColor(getResources().getColor(R.color.black));

                    offer_status = "";
                    setFiltter();
                } else {
                    status_2.setBackground(getResources().getDrawable(R.drawable.background_b2select));
                    status_2.setTextColor(getResources().getColor(R.color.white));


                    status_1.setBackground(null);
                    status_3.setBackground(null);
                    status_1.setTextColor(getResources().getColor(R.color.black));
                    status_3.setTextColor(getResources().getColor(R.color.black));

                    offer_status = "sending_code";
                    setFiltter();
                }


            }
        });
        status_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (offer_status.equals("new")) {
                    status_1.setBackground(null);
                    status_2.setBackground(null);
                    status_3.setBackground(null);
                    status_1.setTextColor(getResources().getColor(R.color.black));
                    status_2.setTextColor(getResources().getColor(R.color.black));
                    status_3.setTextColor(getResources().getColor(R.color.black));

                    offer_status = "";
                    setFiltter();
                } else {
                    status_3.setBackground(getResources().getDrawable(R.drawable.background_b2select));
                    status_3.setTextColor(getResources().getColor(R.color.white));


                    status_2.setBackground(null);
                    status_1.setBackground(null);
                    status_2.setTextColor(getResources().getColor(R.color.black));
                    status_1.setTextColor(getResources().getColor(R.color.black));

                    offer_status = "new";
                    setFiltter();
                }


            }
        });
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllOrder.setBackground(null);
                today.setBackground(getResources().getDrawable(R.drawable.button_login));
                Myoffer.setBackground(null);


                status_1.setVisibility(View.GONE);
                status_2.setVisibility(View.GONE);
                status_3.setVisibility(View.GONE);


//                Myoffer_number.setBackground(getResources().getDrawable(R.drawable.mash));
//                today_number.setBackground(getResources().getDrawable(R.drawable.circle_ss));
//                AllOrder_number.setBackground(getResources().getDrawable(R.drawable.mash));


                AllOrder.setTextColor(getResources().getColor(R.color.textColor));
                today.setTextColor(getResources().getColor(R.color.white));
                Myoffer.setTextColor(getResources().getColor(R.color.textColor));

//                Myoffer_number.setTextColor(getResources().getColor(R.color.textColor));
//                today_number.setTextColor(getResources().getColor(R.color.white));
//                AllOrder_number.setTextColor(getResources().getColor(R.color.textColor));


                type_requst = "today";
                setFiltter();

            }
        });
        searcha_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFiltter();
            }
        });

        filtter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFiltter();
            }
        });
        open_filtter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer.openDrawer(GravityCompat.START);

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer.closeDrawer(GravityCompat.START);

            }
        });

        Myoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllOrder.setBackground(null);
                today.setBackground(null);
                Myoffer.setBackground(getResources().getDrawable(R.drawable.button_login));


                status_1.setVisibility(View.VISIBLE);
                status_2.setVisibility(View.VISIBLE);
                status_3.setVisibility(View.VISIBLE);
//                Myoffer_number.setBackground(getResources().getDrawable(R.drawable.circle_ss));
//                today_number.setBackground(getResources().getDrawable(R.drawable.mash));
//                AllOrder_number.setBackground(getResources().getDrawable(R.drawable.mash));


                AllOrder.setTextColor(getResources().getColor(R.color.textColor));
                today.setTextColor(getResources().getColor(R.color.textColor));
                Myoffer.setTextColor(getResources().getColor(R.color.white));


//                Myoffer_number.setTextColor(getResources().getColor(R.color.white));
//                today_number.setTextColor(getResources().getColor(R.color.textColor));
//                AllOrder_number.setTextColor(getResources().getColor(R.color.textColor));


                type_requst = "Myoffer";

                setFiltter();

            }
        });
        AllOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllOrder.setBackground(getResources().getDrawable(R.drawable.button_login));
                today.setBackground(null);
                Myoffer.setBackground(null);


                status_1.setVisibility(View.GONE);
                status_2.setVisibility(View.GONE);
                status_3.setVisibility(View.GONE);
//                Myoffer_number.setBackground(getResources().getDrawable(R.drawable.mash));
//                today_number.setBackground(getResources().getDrawable(R.drawable.mash));
//                AllOrder_number.setBackground(getResources().getDrawable(R.drawable.circle_ss));


                AllOrder.setTextColor(getResources().getColor(R.color.white));
                today.setTextColor(getResources().getColor(R.color.textColor));
                Myoffer.setTextColor(getResources().getColor(R.color.textColor));

//                Myoffer_number.setTextColor(getResources().getColor(R.color.textColor));
//                today_number.setTextColor(getResources().getColor(R.color.textColor));
//                AllOrder_number.setTextColor(getResources().getColor(R.color.white));


                type_requst = "AllOrder";

                setFiltter();
            }
        });
        favorit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AllOrderActivity.this, FavoriteActivity.class);
                startActivity(intent);


            }
        });
        select_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment_price_range bottomSheetDialogFragment_price_range = new BottomSheetDialogFragment_price_range("");
                bottomSheetDialogFragment_price_range.addItemClickListener(new BottomSheetDialogFragment_price_range.ItemClickListener() {
                    @Override
                    public void onItemClick(int id_price, String text_price) {
                        select_price.setText(text_price + "");
                        price_id = id_price + "";
                        bottomSheetDialogFragment_price_range.dismiss();
                    }
                });
                bottomSheetDialogFragment_price_range.show(getSupportFragmentManager(), "");
            }
        });
        select_space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment_area_range bottomSheetDialogFragment_price_range = new BottomSheetDialogFragment_area_range("");
                bottomSheetDialogFragment_price_range.addItemClickListener(new BottomSheetDialogFragment_area_range.ItemClickListener() {
                    @Override
                    public void onItemClick(int id_price, String text_price) {
                        select_space.setText(text_price + "");
                        area_estate_id = id_price + "";
                        bottomSheetDialogFragment_price_range.dismiss();
                    }
                });
                bottomSheetDialogFragment_price_range.show(getSupportFragmentManager(), "");
            }
        });
        clear_fillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_space.setText("");
                area_estate_id = "";

                select_price.setText("");
                price_id = "";

                estate_type_id = "";

                search_text.setText("");
                city_id = "";

                drawer.closeDrawer(GravityCompat.START);


                setFiltter();

            }
        });
    }

    public static void set_city_fillter() {
        search_citytext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    System.out.println("%$$$$$$$$$$$$$$$$4");
                    filtter_city.setVisibility(View.VISIBLE);
                    allfilter.setVisibility(View.GONE);
//                    if (searh) {
//
////                        searh = true;
//                    }
//                    searh = true;

//
//                    for (int i = 0; i < cityModules_list.size(); i++) {
//
//
//                        if (cityModules_list.get(i).getName().contains(search_text.getText().toString())) {
//                            cityModules_list_filtter.add(cityModules_list.get(i));
//                        }
//
//
//                    }

                    init_volley();
                    WebService.loading(activity, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, activity);
                    mVolleyService.getDataVolley("cities_with_neb", WebService.cities_with_neb + "?name=" + search_citytext.getText().toString());


                    return true;
                }
                return false;
            }
        });
        search_citytext.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    System.out.println("kjdgnkdgjndkjgdkf");
                }

            }
        });

    }

    public static void setTypeFilter() {
        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        LinearLayoutManager layoutManagerw
                = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        all_type.setLayoutManager(layoutManagerw);

        RecyclerView_All_opration_bottom_sheet recyclerView_all_opration_bottom_sheet = new RecyclerView_All_opration_bottom_sheet(activity, type_list);
        recyclerView_all_opration_bottom_sheet.addItemClickListener(new RecyclerView_All_opration_bottom_sheet.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                estate_type_id = type_list.get(position).getId().toString() + "";

//            if (opration_select.toString().equals("1")) {//فيلا
//
//                seaction_roomes.setVisibility(View.VISIBLE);
//
//
//            } else if (opration_select.toString().equals("2")) {//ارض
//
//
//                seaction_roomes.setVisibility(View.GONE);
//
//
//            } else if (opration_select.toString().equals("3")) {//شقه
//                seaction_roomes.setVisibility(View.VISIBLE);
//
//            } else if (opration_select.toString().equals("7")) {//مزرعه
//                seaction_roomes.setVisibility(View.GONE);
//
//
//            } else if (opration_select.toString().equals("4")) {//دبلكس
//                seaction_roomes.setVisibility(View.VISIBLE);
//
//            } else if (opration_select.toString().equals("6")) {//مكتب
//
//
//                seaction_roomes.setVisibility(View.VISIBLE);
//
//
//            }
            }
        });
        all_type.setAdapter(recyclerView_all_opration_bottom_sheet);
    }

    public static void setFiltter() {

        String offer_status_text = "";
        String type_requst_text = "";
        String search_text_s = "";
        String area_estate_id_text = "";
        String price_id_text = "";
        String estate_type_id_text = "";
        String city_id_text = "";


        if (type_requst.equals("today")) {
            offer_status_text = "";
            type_requst_text = "&today=1";
        } else if (type_requst.equals("Myoffer")) {

            type_requst_text = "&myOwn=1";

            if (offer_status.equals("customer_accepted")) {
                offer_status_text = "&offer_status=customer_accepted";

            } else if (offer_status.equals("sending_code")) {
                offer_status_text = "&offer_status=sending_code";

            } else if (offer_status.equals("new")) {
                offer_status_text = "&offer_status=new";
            }


        } else if (type_requst.equals("AllOrder")) {
            offer_status_text = "";
            type_requst_text = "";

        }

        if (!search_text.getText().toString().equals("")) {
            search_text_s = "&search=" + search_text.getText();
        }
        if (!price_id.equals("")) {
            price_id_text = "&price_id=" + price_id;
        }

        if (!area_estate_id.equals("")) {
            area_estate_id_text = "&area_estate_id=" + area_estate_id;
        }

        if (!estate_type_id.equals("")) {
            estate_type_id_text = "&estate_type_id=" + estate_type_id;
        }
        if (city_id != null) {
            if (!city_id.equals("")) {
                city_id_text = "&city_id=" + city_id;
            }
        }

        String url = WebService.fund_Request + "?" + type_requst_text + offer_status_text + search_text_s + price_id_text + area_estate_id_text + estate_type_id_text + city_id_text;//WebService.fund_Request + "?" + "page=" + page + "&today=1" + id_city_ + opration_select + search_te

        WebService.loading(activity, true);

        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, activity);
        mVolleyService.getDataVolley("fund_Request", url);

    }


    public static void convert_city_to_filter() {


        filtter_city.setVisibility(View.GONE);
        allfilter.setVisibility(View.VISIBLE);

        System.out.println(
                "dflkdlfkdlkfldkf"
        );


    }

    public static void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(activity, false);

//                type_requst = "" + requestType;


//{"status":true,"code":200,"message":"User Profile","data"
//allRequestFund":6165,"RequestFund":18,"myRequestFundOffer":4727


                try {

                    String allRequestFund = response.getString("allRequestFund");
                    AllOrder_number.setText(allRequestFund + "");

                    String RequestFund = response.getString("RequestFund");
                    today_number.setText(RequestFund + "");

                    String myRequestFundOffer = response.getString("myRequestFundOffer");
                    Myoffer_number.setText(myRequestFundOffer + "");


                    if (requestType.equals("fund_Request")) {


                        String data = response.getString("data");

                        JSONObject jsonObject_data = new JSONObject(data);

                        String data_inside = jsonObject_data.getString("data");
                        JSONArray jsonArray = new JSONArray(data_inside);
                        AllResultRec.setAdapter(null);
                        ordersModules.clear();

                        for (int i = 0; i < jsonArray.length(); i++) {


                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(jsonArray.getString(i));

                            Gson gson = new Gson();

                            OrdersModules ordersModulesm = gson.fromJson(mJson, OrdersModules.class);
                            ordersModules.add(ordersModulesm);


                        }

                        RecyclerView_ordersx recyclerView_ordersx = new RecyclerView_ordersx(activity, ordersModules);
                        AllResultRec.setAdapter(recyclerView_ordersx);

                        if (ordersModules.size() != 0) {
                            nodata_vis.setVisibility(View.GONE);
                            isLoading = true;
                        } else {
                            nodata_vis.setVisibility(View.VISIBLE);
                            isLoading = false;

                        }

                    } else if (requestType.equals("cities_with_neb")) {
                        String data = response.getString("data");
                        JSONObject jsonObject = new JSONObject(data);

                        String datadata = jsonObject.getString("data");

                        JSONArray jsonArray = new JSONArray(datadata);
                        cityModules_list_filtter.clear();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(jsonArray.getString(i));

                            Gson gson = new Gson();

                            CityModules homeModules_aqares = gson.fromJson(mJson, CityModules.class);

                            cityModules_list_filtter.add(homeModules_aqares);
                        }


                        RecyclerView_city_side_menu recyclerView_city_bootom_sheets = new RecyclerView_city_side_menu(activity, cityModules_list_filtter);
                        recyclerView_city_bootom_sheets.addItemClickListener(new RecyclerView_city_side_menu.ItemClickListener() {
                            @Override
                            public void onItemClick(int i) {
//                        cityModules_list = alldata;
                                convert_city_to_filter();
                                searh = false;
                                city_id = cityModules_list_filtter.get(i).getSerial_city() + "";
//                                    MapsFragmentNew.city_id_postion = cityModules_list_filtter.get(i).getId() + "";
//                                MapsFragmentNew.lat = cityModules_list_filtter.get(i).getLat() + "";
//                                MapsFragmentNew.lan = cityModules_list_filtter.get(i).getLan() + "";


                                search_citytext.setText(cityModules_list_filtter.get(i).getName() + "-" + cityModules_list_filtter.get(i).getCity().getName());

                            }
                        });

                        allcity.setAdapter(recyclerView_city_bootom_sheets);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());
                WebService.loading(activity, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    if (requestType.equals("my_request")) {
//                        nodata_vis.setVisibility(View.VISIBLE);

                    } else if (requestType.equals("market_demands")) {
//                        nodata_vis.setVisibility(View.VISIBLE);

                    } else {
                        WebService.Make_Toast_color(activity, message, "error");

                    }


                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }


            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(activity, false);

            }


        };


    }

}
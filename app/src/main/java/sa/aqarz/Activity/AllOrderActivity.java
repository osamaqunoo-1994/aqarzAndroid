package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
    static List<demandsModules> demandsModules_list = new ArrayList<>();

    ImageView back;

    LinearLayout Real_Estate_order_layout;
    LinearLayout market_order_layout;

    TextView market_order_text;
    TextView Real_Estate_order_text;


    ImageView Real_Estate_order_image;


    static String type = "Real";


    LinearLayout price_market;
    LinearLayout price_fund;
    LinearLayout area_market;
    LinearLayout area_real;


    static EditText Les_price;
    static EditText Maximum_price;
    static EditText Les_space;
    static EditText Maximum_space;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);
        activity = this;

        price_market = findViewById(R.id.price_market);
        price_fund = findViewById(R.id.price_fund);
        area_market = findViewById(R.id.area_market);
        area_real = findViewById(R.id.area_real);

        Les_price = findViewById(R.id.Les_price);
        Maximum_price = findViewById(R.id.Maximum_price);
        Les_space = findViewById(R.id.Les_space);
        Maximum_space = findViewById(R.id.Maximum_space);

        Real_Estate_order_layout = findViewById(R.id.Real_Estate_order_layout);
        market_order_layout = findViewById(R.id.market_order_layout);
        market_order_text = findViewById(R.id.market_order_text);
        Real_Estate_order_text = findViewById(R.id.Real_Estate_order_text);
        Real_Estate_order_image = findViewById(R.id.Real_Estate_order_image);


        AllResultRec = findViewById(R.id.AllResultRec);
        filtter_city = findViewById(R.id.filtter_city);
        premium = findViewById(R.id.premium);
        select_space = findViewById(R.id.select_space);
        select_price = findViewById(R.id.select_price);
        all_type = findViewById(R.id.all_type);
        search_citytext = findViewById(R.id.search_citytext);
        close = findViewById(R.id.close);
        back = findViewById(R.id.back);

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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        layoutManager
                = new LinearLayoutManager(AllOrderActivity.this, LinearLayoutManager.VERTICAL, false);
        AllResultRec.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManager1a
                = new LinearLayoutManager(AllOrderActivity.this, LinearLayoutManager.VERTICAL, false);
        allcity.setLayoutManager(layoutManager1a);


        AllResultRec.addOnScrollListener(recyclerViewOnScrollListener);

//00
        if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().equals("1")) {
            premium.setVisibility(View.VISIBLE);
//            offer.setVisibility(View.VISIBLE);
            not_premium.setVisibility(View.GONE);

        } else {

            premium.setVisibility(View.GONE);
//            offer.setVisibility(View.GONE);
            not_premium.setVisibility(View.VISIBLE);
        }
        change_type_bettwen_market_and_real();
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


    }

    public void change_type_bettwen_market_and_real() {

        Real_Estate_order_layout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.button_login));
                market_order_layout.setBackground(getResources().getDrawable(R.drawable.circle_w));
                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.white));
                market_order_text.setTextColor(getResources().getColor(R.color.colorPrimary));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(AllOrderActivity.this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
                type = "Real";
                setFiltter();
                price_market.setVisibility(View.GONE);
                price_fund.setVisibility(View.VISIBLE);
                area_market.setVisibility(View.GONE);
                area_real.setVisibility(View.VISIBLE);
            }
        });
        market_order_layout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.circle_w));
                market_order_layout.setBackground(getResources().getDrawable(R.drawable.button_login));

                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.colorPrimary));
                market_order_text.setTextColor(getResources().getColor(R.color.white));


                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(AllOrderActivity.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);

                type = "market";
                setFiltter();
                price_market.setVisibility(View.VISIBLE);
                price_fund.setVisibility(View.GONE);
                area_market.setVisibility(View.VISIBLE);
                area_real.setVisibility(View.GONE);


            }
        });


        try {
            String types = getIntent().getStringExtra("type");


            if (types.equals("main")) {
                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.button_login));
                market_order_layout.setBackground(getResources().getDrawable(R.drawable.circle_w));
                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.white));
                market_order_text.setTextColor(getResources().getColor(R.color.colorPrimary));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(AllOrderActivity.this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
                type = "Real";
                setFiltter();
                AllOrder_number.setText(Settings.getSettings().getAllRequestFund() + "");
                today_number.setText(Settings.getSettings().getRequestFund() + "");
                Myoffer_number.setText(Settings.getSettings().getMyRequestFundOffer() + "");

                price_market.setVisibility(View.GONE);
                price_fund.setVisibility(View.VISIBLE);
                area_market.setVisibility(View.GONE);
                area_real.setVisibility(View.VISIBLE);


            } else if (types.equals("Real")) {
                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.button_login));
                market_order_layout.setBackground(getResources().getDrawable(R.drawable.circle_w));
                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.white));
                market_order_text.setTextColor(getResources().getColor(R.color.colorPrimary));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(AllOrderActivity.this, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
                type = "Real";
                setFiltter();
                AllOrder_number.setText(Settings.getSettings().getAllRequestFund() + "");
                today_number.setText(Settings.getSettings().getRequestFund() + "");
                Myoffer_number.setText(Settings.getSettings().getMyRequestFundOffer() + "");

                price_market.setVisibility(View.GONE);
                price_fund.setVisibility(View.VISIBLE);
                area_market.setVisibility(View.GONE);
                area_real.setVisibility(View.VISIBLE);


            } else {//Market
                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.circle_w));
                market_order_layout.setBackground(getResources().getDrawable(R.drawable.button_login));

                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.colorPrimary));
                market_order_text.setTextColor(getResources().getColor(R.color.white));


                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(AllOrderActivity.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);

                type = "market";
                setFiltter();
                AllOrder_number.setText(Settings.getSettings().getAllRequest() + "");
                today_number.setText(Settings.getSettings().getMarketDemands() + "");
                Myoffer_number.setText(Settings.getSettings().getMyRequestOffer() + "");

                price_market.setVisibility(View.VISIBLE);
                price_fund.setVisibility(View.GONE);
                area_market.setVisibility(View.VISIBLE);
                area_real.setVisibility(View.GONE);

            }
        } catch (Exception e) {
            setFiltter();

        }
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
                drawer.closeDrawer(GravityCompat.START);

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

        String url = "";
        if (type.equals("Real")) {

            url = WebService.fund_Request + "?" + type_requst_text + offer_status_text + search_text_s + price_id_text + area_estate_id_text + estate_type_id_text + city_id_text;//WebService.fund_Request + "?" + "page=" + page + "&today=1" + id_city_ + opration_select + search_te
            WebService.loading(activity, true);

            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, activity);
            mVolleyService.getDataVolley("fund_Request", url);
        } else {
            String te = "";
            if (!Les_price.getText().toString().equals("")) {
                te = te + "&price_from=" + Les_price.getText().toString();
            }

            if (!Maximum_price.getText().toString().equals("")) {
                te = te + "&price_to=" + Maximum_price.getText().toString();
            }
            if (!Les_space.getText().toString().equals("")) {
                te = te + "&area_from=" + Les_space.getText().toString();
            }
            if (!Maximum_space.getText().toString().equals("")) {
                te = te + "&area_to=" + Maximum_space.getText().toString();
            }


            url = WebService.market_demands + "?" + type_requst_text + offer_status_text + search_text_s + te + estate_type_id_text + city_id_text;//WebService.fund_Request + "?" + "page=" + page + "&today=1" + id_city_ + opration_select + search_te
            WebService.loading(activity, true);

            init_volley();
            VolleyService mVolleyService = new VolleyService(mResultCallback, activity);
            mVolleyService.getDataVolley("Market_Request", url);
        }


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


                    if (requestType.equals("fund_Request")) {
                        String allRequestFund = response.getString("allRequestFund");
                        AllOrder_number.setText(allRequestFund + "");

                        String RequestFund = response.getString("RequestFund");
                        today_number.setText(RequestFund + "");

                        String myRequestFundOffer = response.getString("myRequestFundOffer");
                        Myoffer_number.setText(myRequestFundOffer + "");


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

                    } else if (requestType.equals("Market_Request")) {
                        String allRequestFund = response.getString("allRequestd");
                        AllOrder_number.setText(allRequestFund + "");

                        String RequestFund = response.getString("Request");
                        today_number.setText(RequestFund + "");

                        String myRequestFundOffer = response.getString("myRequestOffer");
                        Myoffer_number.setText(myRequestFundOffer + "");


                        String data = response.getString("data");

                        JSONObject jsonObject_data = new JSONObject(data);


                        String data_inside = jsonObject_data.getString("data");
                        JSONArray jsonArray = new JSONArray(data_inside);
//                        orders_rec.setAdapter(null);
                        demandsModules_list.clear();


                        for (int i = 0; i < jsonArray.length(); i++) {


                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(jsonArray.getString(i));

                            Gson gson = new Gson();

                            demandsModules ordersModulesm = gson.fromJson(mJson, demandsModules.class);
                            demandsModules_list.add(ordersModulesm);


                        }

                        AllResultRec.setAdapter(new RecyclerView_orders_demandsx(activity, demandsModules_list));


                        if (demandsModules_list.size() != 0) {
                            nodata_vis.setVisibility(View.GONE);
                        } else {
                            nodata_vis.setVisibility(View.VISIBLE);


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


                                search_citytext.setText(cityModules_list_filtter.get(i).getName());

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
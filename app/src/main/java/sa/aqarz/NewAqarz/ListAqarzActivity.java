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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_aqarz);
        back = findViewById(R.id.back);
        order_by = findViewById(R.id.order_by);
        filtter = findViewById(R.id.filtter);


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
                    }
                });


                popUp.setTouchable(true);
                popUp.setFocusable(true);
                popUp.setOutsideTouchable(true);

                //Solution
                popUp.showAsDropDown(order_by);
            }
        });
        get_data();
    }


    public static void get_data() {


        page = 1;
        String type_filtter_ = "";
        if (!type_filtter.equals("")) {
            type_filtter_ = "&estate_type=" + type_filtter;
        }


        WebService.loading(activity, true);
        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, activity);
        url_list = WebService.home_estate_custom_list + "?" + type_filtter_;
        mVolleyService.getAsync("home_estate_custom_list_more_1", url_list);

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

                WebService.loading(activity, false);

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
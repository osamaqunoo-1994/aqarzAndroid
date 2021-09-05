package sa.aqarz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.RecyclerView_GenralNotfication;
import sa.aqarz.Adapter.RecyclerView_chat_main;
import sa.aqarz.Modules.MsgModules;
import sa.aqarz.Modules.NotficationModules;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class NotficationActvity extends AppCompatActivity {
    RecyclerView notfication_list;

    List<NotficationModules> notficationModules = new ArrayList<>();

    IResult mResultCallback;
    LinearLayout nodata;

    TextView message;
    TextView notfication;
    TextView title;
    ImageView back;


    TextView all;
    TextView requested;
    TextView Administrativenotices;
    TextView news_;
    TextView req;
    TextView admin;
    TextView news;
    int page = 1;

    String url = "";
    RecyclerView_GenralNotfication recyclerView_genralNotfication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfication_actvity);
        notfication_list = findViewById(R.id.notfication_list);
        nodata = findViewById(R.id.nodata);
        message = findViewById(R.id.message);
        notfication = findViewById(R.id.notfication);
        title = findViewById(R.id.title);
        back = findViewById(R.id.back);
        all = findViewById(R.id.all);
        requested = findViewById(R.id.requested);
        news = findViewById(R.id.news);
        Administrativenotices = findViewById(R.id.Administrativenotices);
        news_ = findViewById(R.id.news_);
        req = findViewById(R.id.req);
        admin = findViewById(R.id.admin);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (back != null) {
//                back.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            }
//        }
        title.setText(getResources().getString(R.string.Notfication_bottm));
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(NotficationActvity.this, LinearLayoutManager.VERTICAL, false);
        notfication_list.setLayoutManager(layoutManager1);


        recyclerView_genralNotfication = new RecyclerView_GenralNotfication(NotficationActvity.this, notficationModules);


        notfication_list.setAdapter(recyclerView_genralNotfication);


        WebService.loading(NotficationActvity.this, true);

        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, NotficationActvity.this);
//        nodata.setVisibility(View.VISIBLE);

        url = WebService.notification;
        mVolleyService.getDataVolley("notification", WebService.notification);


        notfication_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) { //1 for down

                    page = page + 1;


                    WebService.loading(NotficationActvity.this, true);
                    init_volley();
                    VolleyService mVolleyService = new VolleyService(mResultCallback, NotficationActvity.this);
                    mVolleyService.getDataVolley("notification", url + "?page=" + page);

                }
            }
        });


        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.button_login));
                all.setTextColor(getResources().getColor(R.color.white));


                Administrativenotices.setBackground(null);
                Administrativenotices.setTextColor(getResources().getColor(R.color.black));


                requested.setBackground(null);
                requested.setTextColor(getResources().getColor(R.color.black));


                news_.setBackground(null);
                news_.setTextColor(getResources().getColor(R.color.black));
                WebService.loading(NotficationActvity.this, true);

                page = 1;
                init_volley();
                VolleyService mVolleyService = new VolleyService(mResultCallback, NotficationActvity.this);
//        nodata.setVisibility(View.VISIBLE);
                mVolleyService.getDataVolley("notification", WebService.notification);
                url = WebService.notification;
//                mVolleyService.getDataVolley("my_msg", WebService.my_msg);
            }
        });
        Administrativenotices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                all.setBackground(getResources().getDrawable(R.drawable.shadow));
                all.setTextColor(getResources().getColor(R.color.black));


                Administrativenotices.setBackground(getResources().getDrawable(R.drawable.button_login));
                Administrativenotices.setTextColor(getResources().getColor(R.color.white));


                requested.setBackground(null);
                requested.setTextColor(getResources().getColor(R.color.black));


                news_.setBackground(null);
                news_.setTextColor(getResources().getColor(R.color.black));
                WebService.loading(NotficationActvity.this, true);
                page = 1;

                init_volley();
                VolleyService mVolleyService = new VolleyService(mResultCallback, NotficationActvity.this);
//        nodata.setVisibility(View.VISIBLE);
                mVolleyService.getDataVolley("notification", WebService.notification + "?type=" + "chat,employee");
                url = WebService.notification + "?type=" + "chat,employee";
//                mVolleyService.getDataVolley("my_msg", WebService.my_msg);
            }
        });
        requested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                all.setBackground(getResources().getDrawable(R.drawable.shadow));
                all.setTextColor(getResources().getColor(R.color.black));


                requested.setBackground(getResources().getDrawable(R.drawable.button_login));
                requested.setTextColor(getResources().getColor(R.color.white));


                Administrativenotices.setBackground(null);
                Administrativenotices.setTextColor(getResources().getColor(R.color.black));


                news_.setBackground(null);
                news_.setTextColor(getResources().getColor(R.color.black));
                WebService.loading(NotficationActvity.this, true);
                page = 1;

                init_volley();
                VolleyService mVolleyService = new VolleyService(mResultCallback, NotficationActvity.this);
//        nodata.setVisibility(View.VISIBLE);
                mVolleyService.getDataVolley("notification", WebService.notification + "?type=" + "request,fund_request,fund_offer,offer");
                url = WebService.notification + "?type=" + "request,fund_request,fund_offer,offer";

//                mVolleyService.getDataVolley("my_msg", WebService.my_msg);
            }
        });
        news_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                all.setBackground(getResources().getDrawable(R.drawable.shadow));
                all.setTextColor(getResources().getColor(R.color.black));


                news_.setBackground(getResources().getDrawable(R.drawable.button_login));
                news_.setTextColor(getResources().getColor(R.color.white));


                requested.setBackground(null);
                requested.setTextColor(getResources().getColor(R.color.black));


                Administrativenotices.setBackground(null);
                Administrativenotices.setTextColor(getResources().getColor(R.color.black));
                WebService.loading(NotficationActvity.this, true);
                page = 1;

                init_volley();
                VolleyService mVolleyService = new VolleyService(mResultCallback, NotficationActvity.this);
//        nodata.setVisibility(View.VISIBLE);
                mVolleyService.getDataVolley("notification", WebService.notification + "?type=" + "rate_offer,rate_estate");
                url = WebService.notification + "?type=" + "rate_offer,rate_estate";

//                mVolleyService.getDataVolley("my_msg", WebService.my_msg);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setBackground(getResources().getDrawable(R.drawable.button_login));

                message.setTextColor(getResources().getColor(R.color.white));


                notfication.setBackground(getResources().getDrawable(R.drawable.mash));

                notfication.setTextColor(getResources().getColor(R.color.black));


//                mVolleyService.getDataVolley("my_msg", WebService.my_msg);
            }
        });
        notfication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notfication.setBackground(getResources().getDrawable(R.drawable.button_login));
                notfication.setTextColor(getResources().getColor(R.color.white));
                message.setBackground(getResources().getDrawable(R.drawable.mash));
                message.setTextColor(getResources().getColor(R.color.black));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(NotficationActvity.this, false);


//{"status":true,"code":200,"message":"User Profile","data"


                try {
                    boolean status = response.getBoolean("status");
                    if (status) {

                        if (requestType.equals("notification")) {
                            System.out.println("lfkdlfkdlkf");
                            String data = response.getString("data");
                            String follow_request = response.getString("follow_request");
                            String mange_request = response.getString("mange_request");
                            String news_report = response.getString("news_report");

                            req.setText(follow_request + "");
                            admin.setText(news_report + "");
                            news.setText(mange_request + "");
                            JSONObject jsonObject = new JSONObject(data);


                            String datax = jsonObject.getString("data");


                            JSONArray jsonArray = new JSONArray(datax);

                            if (page == 1) {
                                notficationModules.clear();

                            }

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();
                                NotficationModules msgModules = gson.fromJson(mJson, NotficationModules.class);

//                                HomeModules ordersModulesm = gson.fromJson(mJson, HomeModules.class);
                                notficationModules.add(msgModules);


                            }
                            if (page == 1) {
                                if (notficationModules.size() != 0) {
                                    nodata.setVisibility(View.GONE);
                                } else {
                                    nodata.setVisibility(View.VISIBLE);

                                }
                            }

                            recyclerView_genralNotfication.Refr();


                        }

                    } else {

                        String message = response.getString("message");


                        WebService.Make_Toast_color(NotficationActvity.this, message, "error");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());
                WebService.loading(NotficationActvity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(NotficationActvity.this, message, "error");


                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }


            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(NotficationActvity.this, false);

            }


        };


    }

}
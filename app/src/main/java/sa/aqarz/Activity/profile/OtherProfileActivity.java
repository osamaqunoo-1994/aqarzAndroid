package sa.aqarz.Activity.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager;

import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import sa.aqarz.Activity.AqarzProfileActivity_other;
import sa.aqarz.Activity.Auth.EditProfileActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.ChatRoomActivity;
import sa.aqarz.Activity.SettingsActivity;
import sa.aqarz.Adapter.RecyclerVie_member_service;
import sa.aqarz.Adapter.RecyclerVie_member_service_m;
import sa.aqarz.Adapter.RecyclerView_Course;
import sa.aqarz.Adapter.RecyclerView_experince;
import sa.aqarz.Adapter.RecyclerView_member;
import sa.aqarz.Dialog.BottomSheetDialogFragment_QR;
import sa.aqarz.Modules.SettingsModules;
import sa.aqarz.Modules.User;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class OtherProfileActivity extends AppCompatActivity {
    TextView name;
    LinearLayout is_real_state;
    TextView link;
    TextView phone;
    TextView bio;
    TextView view;

    TextView Clints;
    TextView MyOffer;
    TextView request_nu;

    public static User userModules;

    CircleImageView profile;
    LinearLayout qr_code;
    ImageView cirtificad;


    RecyclerView member_list;
    RecyclerView memssr_list;
    RecyclerView Courses;
    RecyclerView service;

    String id;
    IResult mResultCallback;
    LinearLayout myoffer_layout;
    LinearLayout aqarez;
    LinearLayout editProfile;
    LinearLayout call;
    LinearLayout sms;
    RatingBar rate;
    RecyclerView list_service;
    ImageView back;

    LinearLayout memberships;
    LinearLayout service_;
    LinearLayout experience;
    LinearLayout Coursesxx;

    ImageView memberships_i;
    ImageView service_i;
    ImageView experience_i;
    ImageView Coursesxx_i;

    LinearLayout whats_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile3);
        cirtificad = findViewById(R.id.cirtificad);
        Clints = findViewById(R.id.Clints);
        sms = findViewById(R.id.sms);
        request_nu = findViewById(R.id.request_nu);
        MyOffer = findViewById(R.id.MyOffer);
        qr_code = findViewById(R.id.qr_code);
        profile = findViewById(R.id.profile);
        name = findViewById(R.id.name);
        is_real_state = findViewById(R.id.is_real_state);
        call = findViewById(R.id.call);
        link = findViewById(R.id.link);
        myoffer_layout = findViewById(R.id.myoffer_layout);
        bio = findViewById(R.id.bio);

        link = findViewById(R.id.link);
        aqarez = findViewById(R.id.aqarez);
        back = findViewById(R.id.back);
        myoffer_layout = findViewById(R.id.myoffer_layout);
        whats_up = findViewById(R.id.whats_up);

        member_list = findViewById(R.id.member_list);
        editProfile = findViewById(R.id.editProfile);

        memssr_list = findViewById(R.id.service_list);

        bio = findViewById(R.id.bio);
        list_service = findViewById(R.id.list_service);
        Courses = findViewById(R.id.Courses);
        phone = findViewById(R.id.phone);
        view = findViewById(R.id.view);

        memberships_i = findViewById(R.id.memberships_i);
        service_i = findViewById(R.id.service_i);
        experience_i = findViewById(R.id.experience_i);
        Coursesxx_i = findViewById(R.id.Coursesxx_i);
        memberships = findViewById(R.id.memberships);
        service_ = findViewById(R.id.service);
        experience = findViewById(R.id.experience);
        Coursesxx = findViewById(R.id.Coursesxx);
        member_list.setLayoutManager(new GridLayoutManager(this, 3));
        memssr_list.setLayoutManager(new GridLayoutManager(this, 3));
        Courses.setLayoutManager(new GridLayoutManager(this, 3));
        list_service.setLayoutManager(new GridLayoutManager(this, 3));


//        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
//        flowLayoutManager.setAutoMeasureEnabled(true);
////                            flowLayoutManager.maxItemsPerLine(1);
//        member_list.setLayoutManager(flowLayoutManager);

//        FlowLayoutManager flowLayoutManagerss = new FlowLayoutManager();
//        flowLayoutManagerss.setAutoMeasureEnabled(true);
////                            flowLayoutManager.maxItemsPerLine(1);
//        memssr_list.setLayoutManager(flowLayoutManagerss);
//
//
//        FlowLayoutManager flowLayoutManagerssa = new FlowLayoutManager();
//        flowLayoutManagerss.setAutoMeasureEnabled(true);
////                            flowLayoutManager.maxItemsPerLine(1);
//        Courses.setLayoutManager(flowLayoutManagerssa);
//
//        FlowLayoutManager flowLayoutservice = new FlowLayoutManager();
//        flowLayoutManagerss.setAutoMeasureEnabled(true);
////                            flowLayoutManager.maxItemsPerLine(1);
//        list_service.setLayoutManager(flowLayoutservice);


        WebService.loading(OtherProfileActivity.this, true);


        init_volley();

//        try {
//            id = getIntent().getStringExtra("id");
//            VolleyService mVolleyService = new VolleyService(mResultCallback, OtherProfileActivity.this);
//
//            mVolleyService.getDataVolley("user", WebService.user + id + "");
//
//        } catch (Exception e) {
//
//        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        try {
            String id = getIntent().getStringExtra("id");
            if (id != null | !id.equals("null")) {

                VolleyService mVolleyService = new VolleyService(mResultCallback, OtherProfileActivity.this);

                mVolleyService.getDataVolleyWithoutToken("user", WebService.user + id + "");
            } else {
                Intent intent = getIntent();
                String action = intent.getAction();
                Uri data = intent.getData();

                System.out.println("action" + action);
                System.out.println("data" + data);


                String[] separated = data.toString().split("/");

                String number = separated[3]; // this will contain " they taste good"

                System.out.println("$$$$$$$$$$$$$" + number);

                VolleyService mVolleyService = new VolleyService(mResultCallback, OtherProfileActivity.this);

                mVolleyService.getDataVolleyWithoutToken("user", WebService.user + number + "");
            }


        } catch (Exception e) {
            e.printStackTrace();
            Intent intent = getIntent();
            String action = intent.getAction();
            Uri data = intent.getData();

            System.out.println("action" + action);
            System.out.println("data" + data);


            String[] separated = data.toString().split("/");

            String number = separated[3]; // this will contain " they taste good"

            System.out.println("$$$$$$$$$$$$$" + number);

            VolleyService mVolleyService = new VolleyService(mResultCallback, OtherProfileActivity.this);

            mVolleyService.getDataVolleyWithoutToken("user", WebService.user + number + "");
        }
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Settings.CheckIsAccountAqarzMan()) {
                    Intent intent = new Intent(OtherProfileActivity.this, EditProfileActivity.class);
//              intent.putExtra("from", "splash");
                    startActivity(intent);
//                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                } else {
                    Intent intent = new Intent(OtherProfileActivity.this, MyProfileInformationActivity.class);
//              intent.putExtra("from", "splash");
                    startActivity(intent);
//                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                }

            }
        });

        memberships.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (member_list.getVisibility() == View.VISIBLE) {
                    member_list.setVisibility(View.GONE);
                    memberships_i.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down_x));
                } else {
                    member_list.setVisibility(View.VISIBLE);
                    memberships_i.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_up_x));

                }

            }
        });

        service_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (memssr_list.getVisibility() == View.VISIBLE) {
                    memssr_list.setVisibility(View.GONE);
                    service_i.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down_x));

                } else {
                    memssr_list.setVisibility(View.VISIBLE);
                    service_i.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_up_x));

                }
            }
        });

        experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_service.getVisibility() == View.VISIBLE) {
                    list_service.setVisibility(View.GONE);
                    experience_i.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down_x));

                } else {
                    list_service.setVisibility(View.VISIBLE);
                    experience_i.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_up_x));

                }
            }
        });

        Coursesxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Courses.getVisibility() == View.VISIBLE) {
                    Courses.setVisibility(View.GONE);
                    Coursesxx_i.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down_x));

                } else {
                    Courses.setVisibility(View.VISIBLE);
                    Coursesxx_i.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_up_x));

                }
            }
        });


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(OtherProfileActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String message = response.getString("message");

                        if (requestType.equals("user")) {

                            try {


                                if (requestType.equals("count_call")) {

                                } else {

                                    String data = response.getString("data");

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(data);

                                    Gson gson = new Gson();
                                    userModules = gson.fromJson(mJson, User.class);


//                                    myoffer_layout.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            Intent intent = new Intent(OtherProfileActivity.this, MyOffersActivity.class);
////              intent.putExtra("from", "splash");
//                                            intent.putExtra("id_user", userModules.getId() + "");
//
//                                            startActivity(intent);
////                                            overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
//                                        }
//                                    });

                                    RecyclerVie_member_service service_l = new RecyclerVie_member_service(OtherProfileActivity.this, userModules.getMember_name());


                                    member_list.setAdapter(service_l);

                                    RecyclerVie_member_service_m member_l = new RecyclerVie_member_service_m(OtherProfileActivity.this, userModules.getService_name());


                                    memssr_list.setAdapter(member_l);


                                    Courses.setAdapter(new RecyclerView_Course(OtherProfileActivity.this, userModules.getCourse_name()));


                                    list_service.setAdapter(new RecyclerView_experince(OtherProfileActivity.this, userModules.getExperience_name()));


                                    Glide.with(OtherProfileActivity.this).load(userModules.getLogo() + "").error(getResources().getDrawable(R.drawable.ic_user_un)).diskCacheStrategy(DiskCacheStrategy.NONE)
                                            .skipMemoryCache(true).into(profile);


                                    if (userModules.getBio() != null) {
                                        bio.setText(userModules.getBio() + "");
                                    }

                                    phone.setText(userModules.getCount_call() + " " + getResources().getString(R.string.call));
                                    view.setText(userModules.getCount_visit() + " " + getResources().getString(R.string.view_co));

//                                    call.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            try {
//                                                String phone = "0" + userModules.getMobile();
//                                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
//                                                startActivity(intent);
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                            }
//
//                                        }
//                                    });

                                    aqarez.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(OtherProfileActivity.this, MyOffersActivity.class);
//              intent.putExtra("from", "splash");
                                            intent.putExtra("id_user", "" + userModules.getId());

                                            startActivity(intent);
//                                            overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                                        }
                                    });
                                    qr_code.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            BottomSheetDialogFragment_QR bottomSheetDialogFragment_qr = new BottomSheetDialogFragment_QR(userModules.getLink() + "", userModules.getLogo() + "");
                                            bottomSheetDialogFragment_qr.show(getSupportFragmentManager(), "");

                                        }
                                    });
                                    sms.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(OtherProfileActivity.this, ChatRoomActivity.class);
                                            intent.putExtra("user_id", userModules.getId() + "");
                                            intent.putExtra("parent_id", "-1");
                                            intent.putExtra("nameUser", userModules.getName() + "");
                                            intent.putExtra("imageUser", userModules.getLogo() + "");
                                            startActivity(intent);
                                        }
                                    });
//                                    location.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//
//
//                                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                                                    Uri.parse("http://maps.google.com/maps?saddr=" + userModules.getLat() + "," + userModules.getLan() + "&daddr=" + Settings.GetUser().getLat() + "," + Settings.GetUser().getLan()));
//                                            startActivity(intent);
//                                        }
//                                    });

//                                    call.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//
//                                            try {
//                                                String phone = "0" + userModules.getMobile();
//                                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
//                                                startActivity(intent);
//
//
//                                                VolleyService mVolleyService = new VolleyService(mResultCallback, OtherProfileActivity.this);
//
//                                                mVolleyService.getDataVolley("count_call", WebService.count_call + "/" + id + "/call");
//
//
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                            }
//
//                                        }
//                                    });
//                                    mobile.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//
//                                            try {
//                                                String phone = "0" + userModules.getMobile();
//                                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
//                                                startActivity(intent);
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                            }
//
//                                        }
//                                    });
                                    call.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            try {
                                                String phone = "9660" + userModules.getMobile();
                                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                                                startActivity(intent);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                                    whats_up.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            try {
                                                String phone = "9660" + userModules.getMobile();

                                                String url = "https://api.whatsapp.com/send?phone="+phone;
                                                Intent i = new Intent(Intent.ACTION_VIEW);
                                                i.setData(Uri.parse(url));
                                                startActivity(i);


                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                                    try {
                                        if (!userModules.getName().equals("null")) {
                                            name.setText(userModules.getName());

                                        } else {
                                            name.setText("-------");

                                        }

                                        if (userModules.getIs_certified() != null) {
                                            if (userModules.getIs_certified().equals("1")) {

                                                cirtificad.setVisibility(View.VISIBLE);

                                            } else {
                                                cirtificad.setVisibility(View.GONE);
                                            }
                                        } else {
                                            cirtificad.setVisibility(View.GONE);

                                        }

                                        if (userModules.getIs_pay() != null) {
                                            if (userModules.getIs_pay().equals("1")) {

                                                is_real_state.setVisibility(View.GONE);

                                            } else {
                                                is_real_state.setVisibility(View.GONE);
                                            }
                                        } else {
                                            is_real_state.setVisibility(View.GONE);

                                        }


                                        Clints.setText(userModules.getCount_emp() + "");
                                        request_nu.setText(userModules.getCount_estate() + "");
                                        MyOffer.setText(userModules.getCount_fund_offer() + "");
//                                        visit_nu.setText(userModules.getCount_visit() + "");

                                        if (userModules.getLogo() != null && !userModules.getLogo().equals("null")) {
                                            Glide.with(OtherProfileActivity.this).load(userModules.getLogo()).into(profile);

                                        }//591694624
//                                        if (userModules.getUser_name() != null && !userModules.getUser_name().equals("null")) {
//                                            link.setText("@" + userModules.getUser_name());
//
//                                        } else {
//                                            link.setText("");
//
//                                        }

                                        try {
                                            rate.setRating(Float.valueOf(userModules.getRate() + ""));

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    try {

                                    } catch (Exception e) {

                                    }
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

//                            try {
//                                service_list = Settings.getSettings().getService_types();
//                                member_list = Settings.getSettings().getMember_types();
//
//                            } catch (Exception e) {

//                            }


//                            VolleyService mVolleyService = new VolleyService(mResultCallback, OtherProfileActivity.this);
//                            mVolleyService.getDataVolley("my_estate", WebService.my_estate);


                        }

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(OtherProfileActivity.this, message, "error");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(OtherProfileActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(OtherProfileActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(OtherProfileActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(OtherProfileActivity.this, false);

                WebService.Make_Toast_color(OtherProfileActivity.this, error, "error");


            }
        };


    }

}
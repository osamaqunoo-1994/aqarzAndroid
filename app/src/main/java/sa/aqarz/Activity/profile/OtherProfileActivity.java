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
import sa.aqarz.Activity.Auth.EditProfileActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.ChatRoomActivity;
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
    TextView mobile;
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
    LinearLayout editProfile;
    LinearLayout call;
    LinearLayout sms;
    RatingBar rate;
    RecyclerView list_service;

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
        mobile = findViewById(R.id.mobile);
        myoffer_layout = findViewById(R.id.myoffer_layout);
        bio = findViewById(R.id.bio);

        link = findViewById(R.id.link);
        mobile = findViewById(R.id.mobile);
        myoffer_layout = findViewById(R.id.myoffer_layout);

        member_list = findViewById(R.id.member_list);
        editProfile = findViewById(R.id.editProfile);

        memssr_list = findViewById(R.id.service_list);

        bio = findViewById(R.id.bio);
        list_service = findViewById(R.id.list_service);
        Courses = findViewById(R.id.Courses);
        phone = findViewById(R.id.phone);
        view = findViewById(R.id.view);


        member_list.setLayoutManager(new GridLayoutManager(this, 2));


        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        flowLayoutManager.setAutoMeasureEnabled(true);
//                            flowLayoutManager.maxItemsPerLine(1);
        member_list.setLayoutManager(flowLayoutManager);

        FlowLayoutManager flowLayoutManagerss = new FlowLayoutManager();
        flowLayoutManagerss.setAutoMeasureEnabled(true);
//                            flowLayoutManager.maxItemsPerLine(1);
        memssr_list.setLayoutManager(flowLayoutManagerss);


        FlowLayoutManager flowLayoutManagerssa = new FlowLayoutManager();
        flowLayoutManagerss.setAutoMeasureEnabled(true);
//                            flowLayoutManager.maxItemsPerLine(1);
        Courses.setLayoutManager(flowLayoutManagerssa);

        FlowLayoutManager flowLayoutservice = new FlowLayoutManager();
        flowLayoutManagerss.setAutoMeasureEnabled(true);
//                            flowLayoutManager.maxItemsPerLine(1);
        list_service.setLayoutManager(flowLayoutservice);


        WebService.loading(OtherProfileActivity.this, true);


        init_volley();

        try {
            id = getIntent().getStringExtra("id");
            VolleyService mVolleyService = new VolleyService(mResultCallback, OtherProfileActivity.this);

            mVolleyService.getDataVolley("user", WebService.user + id + "");

        } catch (Exception e) {

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


                                    System.out.println("userModulesuserModules" + userModules.getService_name().size());

                                    myoffer_layout.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(OtherProfileActivity.this, MyOffersActivity.class);
//              intent.putExtra("from", "splash");
                                            intent.putExtra("id_user", userModules.getId() + "");

                                            startActivity(intent);
//                                            overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                                        }
                                    });

                                    RecyclerVie_member_service service_l = new RecyclerVie_member_service(OtherProfileActivity.this, userModules.getMember_name());


                                    member_list.setAdapter(service_l);

                                    RecyclerVie_member_service_m member_l = new RecyclerVie_member_service_m(OtherProfileActivity.this, userModules.getService_name());


                                    memssr_list.setAdapter(member_l);


                                    Courses.setAdapter(new RecyclerView_Course(OtherProfileActivity.this, userModules.getCourse_name()));


                                    list_service.setAdapter(new RecyclerView_experince(OtherProfileActivity.this, userModules.getExperience_name()));


                                    try {
                                        rate.setRating(Float.valueOf(userModules.getRate() + ""));

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
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
                                                String phone = "0" + userModules.getMobile();
                                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                                                startActivity(intent);
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
                                        if (!userModules.getUser_name().equals("null")) {
                                            link.setText("@" + userModules.getUser_name());

                                        } else {
                                            link.setText("");

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

                                                is_real_state.setVisibility(View.VISIBLE);

                                            } else {
                                                is_real_state.setVisibility(View.GONE);
                                            }
                                        } else {
                                            is_real_state.setVisibility(View.GONE);

                                        }

                                        if (userModules.getMobile() != null) {
                                            mobile.setText("0" + userModules.getMobile() + "");

                                        }
                                        Clints.setText(userModules.getCount_emp() + "");
                                        request_nu.setText(userModules.getCount_request() + "");
                                        MyOffer.setText(userModules.getCount_offer() + "");
//                                        visit_nu.setText(userModules.getCount_visit() + "");

                                        if (!userModules.getLogo().equals("null")) {
                                            Picasso.get().load(userModules.getLogo()).into(profile);

                                        }//591694624


                                    } catch (Exception e) {

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
package sa.aqarz.Activity.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import sa.aqarz.Activity.ChatRoomActivity;
import sa.aqarz.Dialog.BottomSheetDialogFragment_QR;
import sa.aqarz.Modules.User;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class OtherProfileActivity_old extends AppCompatActivity {
    TextView offer_text;
    TextView clints_text;
    TextView memberships_text;
    TextView service_text;
    IResult mResultCallback;

    TextView name;
    LinearLayout is_real_state;
    TextView link;
    TextView mobile;
    TextView go_to_map;
    TextView requstService;
    ImageView back;
   public static User userModules;

    CircleImageView profile;


    LinearLayout qr_code;
    ImageView cirtificad;


    LinearLayout link_url;


    TextView clints_nu;
    TextView request_nu;
    TextView offer_nu;
    TextView visit_nu;
    LinearLayout myoffer_layout;
    LinearLayout member;
    LinearLayout my_service;
    LinearLayout my_clints;
    LinearLayout chat;
    LinearLayout call;

    ImageView mobile_icon;
    LinearLayout location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__other_profile_old);
        link_url = findViewById(R.id.link_url);
        cirtificad = findViewById(R.id.cirtificad);
        qr_code = findViewById(R.id.qr_code);
        profile = findViewById(R.id.profile);
        back = findViewById(R.id.back);
        name = findViewById(R.id.name);
        is_real_state = findViewById(R.id.is_real_state);
        link = findViewById(R.id.link);
        mobile = findViewById(R.id.mobile);
        go_to_map = findViewById(R.id.go_to_map);
        requstService = findViewById(R.id.requstService);
        chat = findViewById(R.id.chat);
        call = findViewById(R.id.call);

        visit_nu = findViewById(R.id.visit_nu);
        offer_nu = findViewById(R.id.offer_nu);
        request_nu = findViewById(R.id.request_nu);
        clints_nu = findViewById(R.id.clints_nu);
        myoffer_layout = findViewById(R.id.myoffer_layout);
        member = findViewById(R.id.member);
        my_service = findViewById(R.id.my_service);
        my_clints = findViewById(R.id.my_clints);

        location = findViewById(R.id.location);

        offer_text = findViewById(R.id.offer_text);
        clints_text = findViewById(R.id.clints_text);
        memberships_text = findViewById(R.id.memberships_text);
        mobile_icon = findViewById(R.id.mobile_icon);
        service_text = findViewById(R.id.service_text);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        WebService.loading(OtherProfileActivity_old.this, true);


        init_volley();

        try {
            String id = getIntent().getStringExtra("id");
            VolleyService mVolleyService = new VolleyService(mResultCallback, OtherProfileActivity_old.this);

            mVolleyService.getDataVolley("user", WebService.user + id + "");

        } catch (Exception e) {

        }
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=" + Settings.GetUser().getLat() + "," + Settings.GetUser().getLan() + "&daddr=" + Settings.GetUser().getLat() + "," + Settings.GetUser().getLan()));
                startActivity(intent);
            }
        });

        requstService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                if (Settings.CheckIsAccountAqarzMan()) {
                Intent intent = new Intent(OtherProfileActivity_old.this, AddServiceActivity.class);
//              intent.putExtra("from", "splash");
                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
//                } else {
//                    Intent intent = new Intent(OtherProfileActivity.this, MyProfileInformationActivity.class);
////              intent.putExtra("from", "splash");
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
//                }

            }
        });

        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtherProfileActivity_old.this, MyMemberActivity.class);
//              intent.putExtra("from", "splash");
                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
            }
        });

        my_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtherProfileActivity_old.this, MyServiceActivity.class);
//              intent.putExtra("from", "splash");
                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
            }
        });
        my_clints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtherProfileActivity_old.this, MyClintsActivity.class);
//              intent.putExtra("from", "splash");
                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
            }
        });


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(OtherProfileActivity_old.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String message = response.getString("message");

                        if (requestType.equals("user")) {

                            try {
                                String data = response.getString("data");

                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(data);

                                Gson gson = new Gson();
                                 userModules = gson.fromJson(mJson, User.class);


                                System.out.println("userModulesuserModules"+userModules.getService_name().size());

                                myoffer_layout.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(OtherProfileActivity_old.this, MyOffersActivity.class);
//              intent.putExtra("from", "splash");
                                        intent.putExtra("id_user", userModules.getId() + "");

                                        startActivity(intent);
//                                        overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                                    }
                                });

                                mobile_icon.setOnClickListener(new View.OnClickListener() {
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

                                chat.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(OtherProfileActivity_old.this, ChatRoomActivity.class);
                                        intent.putExtra("user_id", userModules.getId() + "");
                                        intent.putExtra("parent_id", "-1");
                                        intent.putExtra("nameUser", userModules.getName() + "");
                                        intent.putExtra("imageUser", userModules.getLogo() + "");
                                        startActivity(intent);
                                    }
                                });
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
                                });  mobile.setOnClickListener(new View.OnClickListener() {
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
                                mobile_icon.setOnClickListener(new View.OnClickListener() {
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
                                        name.setText("----------");

                                    }
                                    if (!userModules.getLink().equals("null")) {
                                        link.setText(userModules.getLink());

                                    } else {
                                        link.setText("----------------");

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
                                    clints_nu.setText(userModules.getCount_agent() + "");
                                    request_nu.setText(userModules.getCount_request() + "");
                                    offer_nu.setText(userModules.getCount_offer() + "");
                                    visit_nu.setText(userModules.getCount_visit() + "");

                                    if (!userModules.getLogo().equals("null")) {
                                        Glide.with(OtherProfileActivity_old.this).load(userModules.getLogo()).into(profile);

                                    }//591694624

                                    qr_code.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            BottomSheetDialogFragment_QR bottomSheetDialogFragment_qr = new BottomSheetDialogFragment_QR(userModules.getLink() + "", userModules.getLogo() + "");
                                            bottomSheetDialogFragment_qr.show(getSupportFragmentManager(), "");

                                        }
                                    });
                                } catch (Exception e) {

                                }
                                try {

                                } catch (Exception e) {

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

                        WebService.Make_Toast_color(OtherProfileActivity_old.this, message, "error");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(OtherProfileActivity_old.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(OtherProfileActivity_old.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(OtherProfileActivity_old.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(OtherProfileActivity_old.this, false);

                WebService.Make_Toast_color(OtherProfileActivity_old.this, error, "error");


            }
        };


    }

}


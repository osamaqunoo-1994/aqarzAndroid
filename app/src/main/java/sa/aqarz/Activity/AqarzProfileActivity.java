package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;
import sa.aqarz.Activity.Auth.EditProfileActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat;
import sa.aqarz.Adapter.RecyclerView_HomeList_estat_favorit;
import sa.aqarz.Adapter.RecyclerView_clints_new;
import sa.aqarz.Adapter.RecyclerView_member_new;
import sa.aqarz.Adapter.RecyclerView_orders_demandsx;
import sa.aqarz.Adapter.RecyclerView_orders_my_requst;
import sa.aqarz.Adapter.RecyclerView_service_new;
import sa.aqarz.Dialog.BottomSheetDialogFragment_QR;
import sa.aqarz.Modules.Clints;
import sa.aqarz.Modules.FavoritModules;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.SettingsModules;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class AqarzProfileActivity extends AppCompatActivity {
    TextView offer_text;
    TextView clints_text;
    TextView memberships_text;
    TextView service_text;
    IResult mResultCallback;
    List<HomeModules_aqares> homeModules = new ArrayList<>();
    List<Clints> Clintss = new ArrayList<>();
    List<SettingsModules.service_types> service_list = new ArrayList<>();
    List<SettingsModules.service_types> member_list = new ArrayList<>();


    LinearLayout offer_line;
    LinearLayout clints_line;
    LinearLayout memberships_line;
    LinearLayout service_line;


    TextView name;
    LinearLayout is_real_state;
    TextView link;
    TextView mobile;
    TextView go_to_map;
    TextView editProfile;
    ImageView back;

    RecyclerView alldate;


    CircleImageView profile;

    FloatingActionButton add_clint;


    ImageView qr_code;
    ImageView cirtificad;


    LinearLayout link_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aqarz_profile);


        link_url = findViewById(R.id.link_url);
        cirtificad = findViewById(R.id.cirtificad);
        qr_code = findViewById(R.id.qr_code);
        add_clint = findViewById(R.id.add_clint);
        profile = findViewById(R.id.profile);
        back = findViewById(R.id.back);
        alldate = findViewById(R.id.alldate);
        name = findViewById(R.id.name);
        is_real_state = findViewById(R.id.is_real_state);
        link = findViewById(R.id.link);
        mobile = findViewById(R.id.mobile);
        go_to_map = findViewById(R.id.go_to_map);
        editProfile = findViewById(R.id.editProfile);


        offer_text = findViewById(R.id.offer_text);
        clints_text = findViewById(R.id.clints_text);
        memberships_text = findViewById(R.id.memberships_text);
        service_text = findViewById(R.id.service_text);

        offer_line = findViewById(R.id.offer_line);
        clints_line = findViewById(R.id.clints_line);
        memberships_line = findViewById(R.id.memberships_line);
        service_line = findViewById(R.id.service_line);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (back != null) {
                back.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Settings.CheckIsAccountAqarzMan()) {
                    Intent intent = new Intent(AqarzProfileActivity.this, EditProfileActivity.class);
//              intent.putExtra("from", "splash");
                    startActivity(intent);
//                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                } else {
                    Intent intent = new Intent(AqarzProfileActivity.this, MyProfileInformationActivity.class);
//              intent.putExtra("from", "splash");
                    startActivity(intent);
//                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                }

            }
        });

        add_clint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AqarzProfileActivity.this, AddClintesActivity.class);
//              intent.putExtra("from", "splash");
                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);


            }
        });
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(AqarzProfileActivity.this, LinearLayoutManager.VERTICAL, false);
        alldate.setLayoutManager(layoutManager1);


        try {


            if (Settings.CheckIsAccountAqarzMan()) {
                link_url.setVisibility(View.VISIBLE);
                clints_line.setVisibility(View.VISIBLE);
                clints_text.setVisibility(View.VISIBLE);
                memberships_line.setVisibility(View.VISIBLE);
                memberships_text.setVisibility(View.VISIBLE);
                service_line.setVisibility(View.VISIBLE);
                service_text.setVisibility(View.VISIBLE);
            } else {
                link_url.setVisibility(View.GONE);
                clints_line.setVisibility(View.GONE);
                clints_text.setVisibility(View.GONE);
                memberships_line.setVisibility(View.GONE);
                memberships_text.setVisibility(View.GONE);
                service_line.setVisibility(View.GONE);
                service_text.setVisibility(View.GONE);
            }


            if (!Settings.GetUser().getName().equals("null")) {
                name.setText(Settings.GetUser().getName());

            } else {
                name.setText("----------");

            }
            if (!Settings.GetUser().getLink().equals("null")) {
                link.setText(Settings.GetUser().getLink());

            } else {
                link.setText("----------------");

            }

            if (Settings.GetUser().getIs_certified() != null) {
                if (Settings.GetUser().getIs_certified().equals("1")) {

                    cirtificad.setVisibility(View.VISIBLE);

                } else {
                    cirtificad.setVisibility(View.GONE);
                }
            } else {
                cirtificad.setVisibility(View.GONE);

            }

            if (Settings.GetUser().getIs_pay() != null) {
                if (Settings.GetUser().getIs_pay().equals("1")) {

                    is_real_state.setVisibility(View.VISIBLE);

                } else {
                    is_real_state.setVisibility(View.GONE);
                }
            } else {
                is_real_state.setVisibility(View.GONE);

            }


//                if (!Settings.GetUser().getEmail().toString().equals("null")) {
////                email.setText(Settings.GetUser().getEmail());
//
//                } else {
////                email.setText("---------");
////                email.setText("---------");
////                email.setText("---------");
////                email.setText("---------");
////                email.setText("---------");
////                email.setText("---------");
////                email.setText("---------");
////                email.setText("---------");
////                email.setText("---------");
////                email.setText("---------");
////                email.setText("---------");
////                email.setText("---------");
//
//                }
            if (!Settings.GetUser().getLan().equals("null")) {
                go_to_map.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("http://maps.google.com/maps?saddr=" + Settings.GetUser().getLat() + "," + Settings.GetUser().getLan() + "&daddr=" + Settings.GetUser().getLat() + "," + Settings.GetUser().getLan()));
                        startActivity(intent);
                    }
                });
            } else {
                go_to_map.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(AqarzProfileActivity.this, getResources().getString(R.string.no_address_found), Toast.LENGTH_LONG).show();
                    }
                });
            }
            if (Settings.GetUser().getMobile() != null) {
                mobile.setText("0" + Settings.GetUser().getMobile() + "");

            }


            if (!Settings.GetUser().getLogo().equals("null")) {
                Glide.with(AqarzProfileActivity.this).load(Settings.GetUser().getLogo()).into(profile);

            }

        } catch (Exception e) {

        }


        try {
            service_list = Settings.getSettings().getService_types();
            member_list = Settings.getSettings().getMember_types();

        } catch (Exception e) {

        }

        WebService.loading(AqarzProfileActivity.this, true);


        alldate.setBackgroundColor(getResources().getColor(R.color.color_back_rex));
        init_volley();

        VolleyService mVolleyService = new VolleyService(mResultCallback, AqarzProfileActivity.this);

        mVolleyService.getDataVolley("my_estate", WebService.my_estate);


        offer_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offer_text.setTextColor(getResources().getColor(R.color.colorPrimary));
                clints_text.setTextColor(getResources().getColor(R.color.textColor2));
                memberships_text.setTextColor(getResources().getColor(R.color.textColor2));
                service_text.setTextColor(getResources().getColor(R.color.textColor2));


                offer_line.setVisibility(View.VISIBLE);
                clints_line.setVisibility(View.GONE);
                memberships_line.setVisibility(View.GONE);
                service_line.setVisibility(View.GONE);


                WebService.loading(AqarzProfileActivity.this, true);


                alldate.setBackgroundColor(getResources().getColor(R.color.color_back_rex));
                VolleyService mVolleyService = new VolleyService(mResultCallback, AqarzProfileActivity.this);

                mVolleyService.getDataVolley("my_estate", WebService.my_estate);

            }
        });

        clints_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offer_text.setTextColor(getResources().getColor(R.color.textColor2));
                clints_text.setTextColor(getResources().getColor(R.color.colorPrimary));
                memberships_text.setTextColor(getResources().getColor(R.color.textColor2));
                service_text.setTextColor(getResources().getColor(R.color.textColor2));

                offer_line.setVisibility(View.GONE);
                clints_line.setVisibility(View.VISIBLE);
                memberships_line.setVisibility(View.GONE);
                service_line.setVisibility(View.GONE);

                WebService.loading(AqarzProfileActivity.this, true);


                alldate.setBackgroundColor(getResources().getColor(R.color.color_back_rex));
                VolleyService mVolleyService = new VolleyService(mResultCallback, AqarzProfileActivity.this);
                mVolleyService.getDataVolley("my_client", WebService.my_client);


            }
        });

        memberships_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offer_text.setTextColor(getResources().getColor(R.color.textColor2));
                clints_text.setTextColor(getResources().getColor(R.color.textColor2));
                memberships_text.setTextColor(getResources().getColor(R.color.colorPrimary));
                service_text.setTextColor(getResources().getColor(R.color.textColor2));

                offer_line.setVisibility(View.GONE);
                clints_line.setVisibility(View.GONE);
                memberships_line.setVisibility(View.VISIBLE);
                service_line.setVisibility(View.GONE);


                alldate.setAdapter(new RecyclerView_member_new(AqarzProfileActivity.this, member_list));

            }
        });

        service_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offer_text.setTextColor(getResources().getColor(R.color.textColor2));
                clints_text.setTextColor(getResources().getColor(R.color.textColor2));
                memberships_text.setTextColor(getResources().getColor(R.color.textColor2));
                service_text.setTextColor(getResources().getColor(R.color.colorPrimary));

                offer_line.setVisibility(View.GONE);
                clints_line.setVisibility(View.GONE);
                memberships_line.setVisibility(View.GONE);
                service_line.setVisibility(View.VISIBLE);

                alldate.setAdapter(new RecyclerView_service_new(AqarzProfileActivity.this, service_list));

            }
        });
        qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                BottomSheetDialogFragment_QR bottomSheetDialogFragment_qr = new BottomSheetDialogFragment_QR();
//                bottomSheetDialogFragment_qr.show(getSupportFragmentManager(), "");

            }
        });

    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(AqarzProfileActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String message = response.getString("message");
                        if (requestType.equals("my_estate")) {
                            String data = response.getString("data");
//                        JSONObject jsonObjectdata = new JSONObject(data);
//
//                        String datax = jsonObjectdata.getString("data");

                            JSONArray jsonArray = new JSONArray(data);


                            homeModules.clear();
                            alldate.setAdapter(null);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    HomeModules_aqares bankModules = gson.fromJson(mJson, HomeModules_aqares.class);
                                    homeModules.add(bankModules);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }


                            alldate.setAdapter(new RecyclerView_HomeList_estat(AqarzProfileActivity.this, homeModules));


                        } else {
                            String data = response.getString("data");
                            JSONArray jsonArray = new JSONArray(data);
                            Clintss.clear();
                            alldate.setAdapter(null);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    Clints bankModules = gson.fromJson(mJson, Clints.class);
                                    Clintss.add(bankModules);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
//

                            alldate.setAdapter(new RecyclerView_clints_new(AqarzProfileActivity.this, Clintss));

//                            AlertDialog alertDialog;
//
//
//                            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                            final View popupView = layoutInflater.inflate(R.layout.upgrade_message, null);
//
//
//                            final AlertDialog.Builder builder = new AlertDialog.Builder(DetailsAqarzManActivity.this);
//
////            alertDialog_country =
//                            builder.setView(popupView);
//
//
//                            alertDialog = builder.show();
//
//                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        }
                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(AqarzProfileActivity.this, message, "error");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(AqarzProfileActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(AqarzProfileActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(AqarzProfileActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(AqarzProfileActivity.this, false);

                WebService.Make_Toast_color(AqarzProfileActivity.this, error, "error");


            }
        };


    }

}
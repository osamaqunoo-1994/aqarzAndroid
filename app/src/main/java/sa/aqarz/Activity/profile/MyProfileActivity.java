package sa.aqarz.Activity.profile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;
import sa.aqarz.Activity.AqarzProfileActivity;
import sa.aqarz.Activity.Auth.EditProfileActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import sa.aqarz.Activity.SelectLocationActivity;
import sa.aqarz.Activity.SplashScreenActivity;
import sa.aqarz.Dialog.BottomSheetDialogFragment_QR;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.api.IResult;

public class MyProfileActivity extends AppCompatActivity {
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
    TextView editProfile;
    ImageView back;

    Button logout;
    CircleImageView profile;


    ImageView qr_code;
    ImageView cirtificad;

    private static GoogleMap googleMap;
    public GoogleApiClient mGoogleApiClient;
    MapView mMapView;


    LinearLayout link_url;


    TextView clints_nu;
    TextView request_nu;
    TextView offer_nu;
    TextView visit_nu;
    LinearLayout myoffer_layout;
    LinearLayout member;
    LinearLayout my_service;
    LinearLayout my_clints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
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
        editProfile = findViewById(R.id.editProfile);
        logout = findViewById(R.id.logout);
        visit_nu = findViewById(R.id.visit_nu);
        offer_nu = findViewById(R.id.offer_nu);
        request_nu = findViewById(R.id.request_nu);
        clints_nu = findViewById(R.id.clints_nu);
        myoffer_layout = findViewById(R.id.myoffer_layout);
        member = findViewById(R.id.member);
        my_service = findViewById(R.id.my_service);
        my_clints = findViewById(R.id.my_clints);


        offer_text = findViewById(R.id.offer_text);
        clints_text = findViewById(R.id.clints_text);
        memberships_text = findViewById(R.id.memberships_text);
        service_text = findViewById(R.id.service_text);


        mMapView = (MapView) findViewById(R.id.mapViewxx);

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately


        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

//                googleMap.getUiSettings().setRotateGesturesEnabled(true);

                if (!Settings.GetUser().getLat().toString().equals("null")) {

                    LatLng sydney = new LatLng(Double.valueOf(Settings.GetUser().getLat()), Double.valueOf(Settings.GetUser().getLan()));

                    googleMap.addMarker(new MarkerOptions()
                            .position(sydney)
                            .title("Marker"));

                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
                    // Zoom in, animating the camera.
                    googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                    // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);

                }

            }
        });
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
                    Intent intent = new Intent(MyProfileActivity.this, EditProfileActivity.class);
//              intent.putExtra("from", "splash");
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                } else {
                    Intent intent = new Intent(MyProfileActivity.this, MyProfileInformationActivity.class);
//              intent.putExtra("from", "splash");
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                }

            }
        });
        myoffer_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, MyOffersActivity.class);
              intent.putExtra("id_user", "--");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
            }
        });
        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, MyMemberActivity.class);
//              intent.putExtra("from", "splash");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
            }
        });

        my_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, MyServiceActivity.class);
//              intent.putExtra("from", "splash");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
            }
        });
        my_clints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, MyClintsActivity.class);
//              intent.putExtra("from", "splash");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
            }
        });

        try {
            if (!Settings.GetUser().getName().toString().equals("null")) {
                name.setText(Settings.GetUser().getName());

            } else {
                name.setText("----------");

            }
            if (!Settings.GetUser().getLink().toString().equals("null")) {
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

            if (Settings.GetUser().getMobile() != null) {
                mobile.setText("0" + Settings.GetUser().getMobile() + "");

            }
            clints_nu.setText(Settings.GetUser().getCount_agent() + "");
            request_nu.setText(Settings.GetUser().getCount_request() + "");
            offer_nu.setText(Settings.GetUser().getCount_offer() + "");
            visit_nu.setText(Settings.GetUser().getCount_visit() + "");

            if (!Settings.GetUser().getLogo().toString().equals("null")) {
                Picasso.get().load(Settings.GetUser().getLogo()).into(profile);

            }//591694624

            qr_code.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    BottomSheetDialogFragment_QR bottomSheetDialogFragment_qr = new BottomSheetDialogFragment_QR(Settings.GetUser().getLink() + "", Settings.GetUser().getLogo() + "");
                    bottomSheetDialogFragment_qr.show(getSupportFragmentManager(), "");

                }
            });
        } catch (Exception e) {

        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new AlertDialog.Builder(MyProfileActivity.this)
                        .setMessage(getResources().getString(R.string.are_you_wantlog))
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                Hawk.put("user", "");
                                Hawk.put("api_token", "");
//                Hawk.put("user", "");

                                finish();
//                                check_user_login();


                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), null)
                        .show();

            }
        });


    }
}
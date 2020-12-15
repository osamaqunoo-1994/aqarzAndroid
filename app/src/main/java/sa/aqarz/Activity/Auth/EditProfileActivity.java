package sa.aqarz.Activity.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;
import com.orhanobut.hawk.Hawk;
import com.rtchagas.pingplacepicker.PingPlacePicker;
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import sa.aqarz.Activity.OprationNew.RateActivity;
import sa.aqarz.Activity.SelectLocationActivity;
import sa.aqarz.Adapter.RecyclerView_member;
import sa.aqarz.Adapter.RecyclerView_service_types;
import sa.aqarz.Dialog.BottomSheetDialogFragment_SelectCity;
import sa.aqarz.Modules.SettingsModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;
import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

public class EditProfileActivity extends AppCompatActivity {
    EditText name_ed;
    TextView email_ed;
    TextView phone_ed;
    TextView address;
    EditText name_office;
    EditText link;
    Button done;
    BottomSheetDialogFragment_SelectCity bottomSheetDialogFragment_selectCity;

    IResult mResultCallback;

    ImageView back;
    ImageView edit_image;

    CircleImageView image_profile;
    File image_file_file = null;

    RecyclerView service_types_list;
    RecyclerView member_list;

    String service_types_te = "";
    String memmber_te = "";
    String city_id = "";
    String lat = "";
    String lng = "";
    String Address_t = "";
    List<SettingsModules.service_types> service_types_listss = new ArrayList<>();
    List<SettingsModules.service_types> Member_typesl = new ArrayList<>();


    TextView yes, no;
    String type_yes_no = "0";
    String type_experiencex = "";


    CheckBox more_than_10, form_5_to_10, less_than_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        init_volley();
        init();


        LinearLayout yourView = findViewById(R.id.lay);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (yourView != null) {
                yourView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

    }

    public void init() {
        less_than_5 = findViewById(R.id.less_than_5);
        form_5_to_10 = findViewById(R.id.form_5_to_10);
        more_than_10 = findViewById(R.id.more_than_10);
        link = findViewById(R.id.link);
        name_ed = findViewById(R.id.name_ed);
        email_ed = findViewById(R.id.www);
        phone_ed = findViewById(R.id.phone_ed);
        done = findViewById(R.id.done);
        back = findViewById(R.id.back);
        edit_image = findViewById(R.id.edit_image);
        image_profile = findViewById(R.id.image_profile);
        service_types_list = findViewById(R.id.service_types_list);
        member_list = findViewById(R.id.member_list);
        address = findViewById(R.id.address);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        name_office = findViewById(R.id.name_office);

        Places.initialize(EditProfileActivity.this, "AIzaSyA6E2L_Feqp6HMD85eQ1RP06WnykHJj7Mc");
        PlacesClient placesClient = Places.createClient(EditProfileActivity.this);


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(EditProfileActivity.this, LinearLayoutManager.VERTICAL, false);
        service_types_list.setLayoutManager(layoutManager1);


        service_types_listss = Settings.getSettings().getService_types();


        for (int i = 0; i < service_types_listss.size(); i++) {
            for (int j = 0; j < Settings.GetUser().getService_name().size(); j++) {

                if (service_types_listss.get(i).getId() == Settings.GetUser().getService_name().get(j).getId()) {
                    System.out.println("oooooooooooooo");
                    service_types_listss.get(i).setChecked(true);
                }
            }

        }

        service_types_te = "";
        for (int i = 0; i < service_types_listss.size(); i++) {
            if (service_types_listss.get(i).isChecked()) {
                if (service_types_te.equals("")) {
                    service_types_te = service_types_listss.get(i).getId() + "";

                } else {
                    service_types_te = service_types_te + "," + service_types_listss.get(i).getId() + "";

                }

            }
        }


        RecyclerView_service_types recyclerView_service_types = new RecyclerView_service_types(EditProfileActivity.this, service_types_listss);
        recyclerView_service_types.addItemClickListener(new RecyclerView_service_types.ItemClickListener() {
            @Override
            public void onItemClick(List<SettingsModules.service_types> service_types) {
                service_types_te = "";
                for (int i = 0; i < service_types.size(); i++) {
                    if (service_types.get(i).isChecked()) {
                        if (service_types_te.equals("")) {
                            service_types_te = service_types.get(i).getId() + "";

                        } else {
                            service_types_te = service_types_te + "," + service_types.get(i).getId() + "";

                        }

                    }
                }

                System.out.println("service_types_te" + service_types_te);
            }
        });

        service_types_list.setAdapter(recyclerView_service_types);


        city_id = Settings.GetUser().getCity_id() + "";
        member_list.setLayoutManager(new GridLayoutManager(this, 2));


        try {
            lat = Settings.GetUser().getLat() + "";
            lng = Settings.GetUser().getLan() + "";
            Address_t = Settings.GetUser().getAddress();

            address.setText(Address_t + "");
            link.setText(Settings.GetUser().getUser_name() + "");

        } catch (Exception e) {

        }

        Member_typesl = Settings.getSettings().getMember_types();


        less_than_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    less_than_5.setChecked(true);
                    form_5_to_10.setChecked(false);
                    more_than_10.setChecked(false);
                    type_experiencex = "less_than_5";
                }
            }
        });


        form_5_to_10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    less_than_5.setChecked(false);
                    form_5_to_10.setChecked(true);
                    more_than_10.setChecked(false);
                    type_experiencex = "form_5_to_10";

                }
            }
        });


        more_than_10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    less_than_5.setChecked(false);
                    form_5_to_10.setChecked(false);
                    more_than_10.setChecked(true);
                    type_experiencex = "more_than_10";

                }
            }
        });


        try {

            System.out.println("YTYTY" + Settings.GetUser().getMember_name().get(0).getName());
            for (int i = 0; i < Member_typesl.size(); i++) {
                for (int j = 0; j < Settings.GetUser().getMember_name().size(); j++) {


                    try {
                        if (Member_typesl.get(i).getId() == Settings.GetUser().getMember_name().get(j).getId()) {
                            System.out.println("XXXXX" + Member_typesl.get(i).getName());
                            Member_typesl.get(i).setChecked(true);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        memmber_te = "";
        for (int i = 0; i < Member_typesl.size(); i++) {
            if (Member_typesl.get(i).isChecked()) {
                if (memmber_te.equals("")) {
                    memmber_te = Member_typesl.get(i).getId() + "";

                } else {
                    memmber_te = memmber_te + "," + Member_typesl.get(i).getId() + "";

                }

            }
        }


        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        flowLayoutManager.setAutoMeasureEnabled(true);
//                            flowLayoutManager.maxItemsPerLine(1);
        member_list.setLayoutManager(flowLayoutManager);


        RecyclerView_member recyclerView_member = new RecyclerView_member(EditProfileActivity.this, Member_typesl);
        recyclerView_member.addItemClickListener(new RecyclerView_member.ItemClickListener() {
            @Override
            public void onItemClick(List<SettingsModules.service_types> service_types) {
                memmber_te = "";
                for (int i = 0; i < service_types.size(); i++) {
                    if (service_types.get(i).isChecked()) {
                        if (memmber_te.equals("")) {
                            memmber_te = service_types.get(i).getId() + "";

                        } else {
                            memmber_te = memmber_te + "," + service_types.get(i).getId() + "";

                        }

                    }
                }
                System.out.println("memmber_te" + memmber_te);

            }
        });

        member_list.setAdapter(recyclerView_member);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                Intent intent = new Intent(EditProfileActivity.this, SelectLocationActivity.class);
                startActivityForResult(intent, 11);

//
//                bottomSheetDialogFragment_selectCity = new BottomSheetDialogFragment_SelectCity("");
//                bottomSheetDialogFragment_selectCity.addItemClickListener(new BottomSheetDialogFragment_SelectCity.ItemClickListener() {
//                    @Override
//                    public void onItemClick(int id_city, String city_naem) {
//                        city_id = id_city + "";
//                        address.setText(city_naem);
//                        bottomSheetDialogFragment_selectCity.dismiss();
//
//                    }
//                });
//
//                bottomSheetDialogFragment_selectCity.show(getSupportFragmentManager(), "");

//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    if (ContextCompat.checkSelfPermission(EditProfileActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//
//                        // No explanation needed, we can request the permission.
//
//                        ActivityCompat.requestPermissions(EditProfileActivity.this,
//                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                                11);
//
//                    } else {
//
//                        PingPlacePicker.IntentBuilder builder = new PingPlacePicker.IntentBuilder();
//                        builder.setAndroidApiKey("AIzaSyDWtJ0cgqHXouF5I5YdPjLzztWQzXM4zQc")
//                                .setMapsApiKey("AIzaSyDWtJ0cgqHXouF5I5YdPjLzztWQzXM4zQc");
//
//                        // If you want to set a initial location rather then the current device location.
//                        // NOTE: enable_nearby_search MUST be true.
//                        // builder.setLatLng(new LatLng(37.4219999, -122.0862462))
//
//                        try {
//                            Intent placeIntent = builder.build(EditProfileActivity.this);
//                            startActivityForResult(placeIntent, 11);
//                        } catch (Exception ex) {
//                            // Google Play services is not available...
//                        }
//                    }
//                } else {
//
//
//                }
            }
        });

        try {

            phone_ed.setText(Settings.GetUser().getMobile());

            if (Settings.GetUser().getOnwer_name() != null) {
                name_ed.setText(Settings.GetUser().getOnwer_name() + "");

            }
            if (Settings.GetUser().getName() != null) {
                name_office.setText(Settings.GetUser().getName() + "");

            }
            if (Settings.GetUser().getUser_name() != null) {
                link.setText(Settings.GetUser().getUser_name() + "");

            }
            if (Settings.GetUser().getEmail() != null) {
                email_ed.setText(Settings.GetUser().getEmail().toString() + "");

            }
            if (Settings.GetUser().getCity_name() != null) {
                address.setText(Settings.GetUser().getAddress() + "");

            }


            Glide.with(EditProfileActivity.this).load(Settings.GetUser().getLogo() + "").error(getResources().getDrawable(R.drawable.ic_user_un)).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(image_profile);

        } catch (Exception e) {

        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(EditProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(EditProfileActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                11);

                    } else {

                        ImagePicker.with(EditProfileActivity.this)
                                .setFolderMode(true)
                                .setFolderTitle("Album")

                                .setDirectoryName("Image Picker")
                                .setMultipleMode(false)
                                .setShowNumberIndicator(true)
                                .setMaxSize(1)
                                .setLimitMessage("You can select up to 1 images")

                                .setRequestCode(1213)
                                .start();
                    }
                } else {
                    ImagePicker.with(EditProfileActivity.this)
                            .setFolderMode(true)
                            .setFolderTitle("Album")

                            .setDirectoryName("Image Picker")
                            .setMultipleMode(false)
                            .setShowNumberIndicator(true)
                            .setMaxSize(1)
                            .setLimitMessage("You can select up to 1 images")

                            .setRequestCode(1213)
                            .start();

                }


            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (name_ed.getText().toString().equals("")) {
                    WebService.Make_Toast_color(EditProfileActivity.this, getResources().getString(R.string.hint_name__address) + "", "error");
                } else if (name_office.getText().toString().equals("")) {
                    WebService.Make_Toast_color(EditProfileActivity.this, getResources().getString(R.string.reqname_office) + "", "error");
                } else if (phone_ed.getText().toString().equals("")) {
                    WebService.Make_Toast_color(EditProfileActivity.this, getResources().getString(R.string.hint_mob__address) + "", "error");

                } else if (email_ed.getText().toString().equals("")) {
                    WebService.Make_Toast_color(EditProfileActivity.this, getResources().getString(R.string.hint_email_address) + "", "error");

                } else if (address.getText().toString().equals("")) {
                    WebService.Make_Toast_color(EditProfileActivity.this, getResources().getString(R.string.hint_address__address) + "", "error");

                } else if (service_types_te.toString().equals("")) {
                    WebService.Make_Toast_color(EditProfileActivity.this, getResources().getString(R.string.select_service) + "", "error");

                } else if (memmber_te.toString().equals("")) {
                    WebService.Make_Toast_color(EditProfileActivity.this, getResources().getString(R.string.selectmember) + "", "error");

                } else if (type_experiencex.toString().equals("")) {
                    WebService.Make_Toast_color(EditProfileActivity.this, getResources().getString(R.string.select_experiencex) + "", "error");

                } else if (link.toString().equals("")) {
                    WebService.Make_Toast_color(EditProfileActivity.this, getResources().getString(R.string.user_name_req) + "", "error");

                } else {
                    WebService.loading(EditProfileActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, EditProfileActivity.this);


                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("onwer_name", name_ed.getText().toString());
//                        sendObj.put("city_id", city_id);
                        sendObj.put("neighborhood_id", 2);
                        sendObj.put("email", email_ed.getText().toString() + "");
//                        sendObj.put("services_name", "s_nam1,s_name2");
//                        sendObj.put("members_name", "mebmer1,mebmer_nam2");

                        sendObj.put("services_id", service_types_te);
                        sendObj.put("name", name_office.getText().toString());
                        sendObj.put("members_id", memmber_te);
                        sendObj.put("lan", lng);
                        sendObj.put("lat", lat);
                        sendObj.put("address", Address_t);
                        sendObj.put("user_name", link.getText().toString());
                        sendObj.put("experience", type_experiencex);
                        sendObj.put("office_staff", type_yes_no);


                        System.out.println(sendObj.toString());
                        mVolleyService.postDataVolley("Update", WebService.update_my_profile, sendObj);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes.setBackground(getResources().getDrawable(R.drawable.button_login));

                yes.setTextColor(getResources().getColor(R.color.white));

                no.setBackground(null);
                type_yes_no = "1";
                no.setTextColor(getResources().getColor(R.color.textColor));
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no.setBackground(getResources().getDrawable(R.drawable.button_login));

                no.setTextColor(getResources().getColor(R.color.white));
                type_yes_no = "0";


                yes.setBackground(null);

                yes.setTextColor(getResources().getColor(R.color.textColor));

            }
        });


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(EditProfileActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

                        Hawk.put("user", data);


                        String message = response.getString("message");


                        WebService.Make_Toast_color(EditProfileActivity.this, getResources().getString(R.string.edit_text), "success");

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(EditProfileActivity.this, message, "error");
                    }


                } catch (Exception e) {

                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.toString());

                try {

                    NetworkResponse response = error.networkResponse;
                    String json = new String(response.data);


                    WebService.loading(EditProfileActivity.this, false);
                    WebService.Make_Toast_color(EditProfileActivity.this, error.getMessage(), "error");

                } catch (Exception e) {

                }


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == Activity.RESULT_OK) {
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            image_profile.setImageBitmap(selectedImage);

            //            file_path = filePath;
            image_file_file = new File(filePath);


            try {

                RequestParams requestParams = new RequestParams();

                requestParams.put("logo", image_file_file);


                Upload_image(requestParams);
            } catch (Exception e) {

            }


        }

        if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 1213)) {


            ArrayList<Image> images = ImagePicker.getImages(data);
//            Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, images.get(0).getId()+"");


            String filePath = images.get(0).getPath().toString();
            Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);

            image_profile.setImageBitmap(selectedImagea);

            File file_image_profile = new File(filePath);

            try {

                RequestParams requestParams = new RequestParams();

                requestParams.put("logo", file_image_profile);


                Upload_image(requestParams);
            } catch (Exception e) {

            }
//            sendChatMsg_file(first_image_file);


        }
        if (requestCode == 100) {
//            if (resultCode == RESULT_OK) {
//                Place selectedPlace = PlacePicker.getPlace(data, this);
//
//                Latitude = selectedPlace.getLatLng().latitude + "";
//                Longitude = selectedPlace.getLatLng().longitude + "";
//
//
//                System.out.println("Latitude" + Latitude + "Longitude" + Longitude);
//
//                // Do something with the place
//            }
        }
        if ((requestCode == 11) & data != null) {


            if (resultCode == Activity.RESULT_OK) {
                // TODO Extract the data returned from the child Activity.
                String lat_ = data.getStringExtra("lat");
                String lang_ = data.getStringExtra("lang");
                String address_ = data.getStringExtra("address");

                lat = "" + lat_;
                lng = "" + lang_;
                Toast.makeText(EditProfileActivity.this, "You selected the place: " + lat + address_, Toast.LENGTH_SHORT).show();
//


                Address_t = address_;
                address.setText(Address_t);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        if (requestCode == 11) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(EditProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                    // No explanation needed, we can request the permission.

//                    ActivityCompat.requestPermissions(MyProfileInformationActivity.this,
//                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                            11);

                } else {
                    ImagePicker.with(EditProfileActivity.this)
                            .setFolderMode(true)
                            .setFolderTitle("Album")

                            .setDirectoryName("Image Picker")
                            .setMultipleMode(false)
                            .setShowNumberIndicator(true)
                            .setMaxSize(1)
                            .setLimitMessage("You can select up to 1 images")

                            .setRequestCode(1213)
                            .start();
                }
            } else {
                ImagePicker.with(EditProfileActivity.this)
                        .setFolderMode(true)
                        .setFolderTitle("Album")

                        .setDirectoryName("Image Picker")
                        .setMultipleMode(false)
                        .setShowNumberIndicator(true)
                        .setMaxSize(1)
                        .setLimitMessage("You can select up to 1 images")

                        .setRequestCode(1213)
                        .start();
            }
        }


    }

    public void Upload_image(RequestParams requestParams) {


        AsyncHttpClient client = new AsyncHttpClient();
        String BASE_URL = WebService.profile_image;
        WebService.loading(EditProfileActivity.this, true);


        final int DEFAULT_TIMEOUT = 20 * 1000;

        client.setTimeout(DEFAULT_TIMEOUT);
        WebService.Header_Async(client, true);

        client.post(BASE_URL, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                WebService.loading(EditProfileActivity.this, false);
//                Addproduct_next_btn.setClickable(true);

                System.out.println("dfsdfd" + responseBody.toString());

                try {

                    String status = responseBody.getString("status");
//
//                    String message = responseBody.getString("message");
//                    String code = responseBody.getString("code");
                    String message = responseBody.getString("message");

                    if (status.equals("true")) {

                        String data = responseBody.getString("data");

                        Hawk.put("user", data);


                        WebService.Make_Toast_color(EditProfileActivity.this, message, "success");

                    } else {


                        WebService.Make_Toast(EditProfileActivity.this, message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
//                    noorder.setVisibility(View.VISIBLE);
//
//                    WebService.loading(MainActivity.this, false);

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                System.out.println("responseString" + responseString);
                WebService.loading(EditProfileActivity.this, false);


                WebService.loading(EditProfileActivity.this, false);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                try {
                    System.out.println("responseString" + errorResponse.toString());

                    WebService.loading(EditProfileActivity.this, false);

                } catch (Exception e) {

                }

            }

            @Override
            public void onUserException(Throwable error) {
                // super.onUserException(error);
//                Log.d("ttt", "onFailure: jhjj" + error.getMessage());


            }


            @Override
            public void onProgress(long bytesWritten, long totalSize) {


                try {
//                    int po = (int) (totalSize / 500);
//
//                    int cc = (int) bytesWritten / po;
//                    int progressPercentage = (int) (250 * bytesWritten / totalSize);
//
//
//                    System.out.println(totalSize + "###" + po + "%%" + cc);
//                    kProgressHUDx.setProgress(progressPercentage);
//

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }

}
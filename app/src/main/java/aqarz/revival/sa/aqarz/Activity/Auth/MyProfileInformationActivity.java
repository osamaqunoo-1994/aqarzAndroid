package aqarz.revival.sa.aqarz.Activity.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Set;

import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;
import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

public class MyProfileInformationActivity extends AppCompatActivity {
    EditText name_ed;
    TextView email_ed;
    TextView phone_ed;

    Button done;


    IResult mResultCallback;

    ImageView back;
    ImageView edit_image;

    CircleImageView image_profile;


    File image_file_file = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_information);

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
        name_ed = findViewById(R.id.name_ed);
        email_ed = findViewById(R.id.email_ed);
        phone_ed = findViewById(R.id.phone_ed);
        done = findViewById(R.id.done);
        back = findViewById(R.id.back);
        edit_image = findViewById(R.id.edit_image);
        image_profile = findViewById(R.id.image_profile);


        try {
            name_ed.setText(Settings.GetUser().getName());
            phone_ed.setText(Settings.GetUser().getMobile());
            email_ed.setText(Settings.GetUser().getEmail());

            Glide.with(MyProfileInformationActivity.this).load(Settings.GetUser().getLogo() + "").error(getResources().getDrawable(R.drawable.ic_user_un)).diskCacheStrategy(DiskCacheStrategy.NONE)
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
                    if (ContextCompat.checkSelfPermission(MyProfileInformationActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(MyProfileInformationActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                11);

                    } else {

                        Intent intent = new Intent(MyProfileInformationActivity.this, ImageSelectActivity.class);
                        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                        startActivityForResult(intent, 1213);
                    }
                } else {
                    Intent intent = new Intent(MyProfileInformationActivity.this, ImageSelectActivity.class);
                    intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                    startActivityForResult(intent, 1213);

                }


            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (name_ed.getText().toString().equals("") |
                        email_ed.getText().toString().equals("") |
                        phone_ed.getText().toString().equals("")) {
                    WebService.Make_Toast_color(MyProfileInformationActivity.this, getResources().getString(R.string.fillallfileds) + "", "error");
                } else {
                    WebService.loading(MyProfileInformationActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, MyProfileInformationActivity.this);


                    JSONObject sendObj = new JSONObject();

                    try {

                        sendObj.put("name", name_ed.getText().toString());
//                        sendObj.put("email", email_ed.getText().toString());
//                        sendObj.put("mobile", phone_ed.getText().toString());


                        System.out.println(sendObj.toString());
                        mVolleyService.postDataVolley("Update", WebService.update_my_profile, sendObj);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        });


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(MyProfileInformationActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

                        Hawk.put("user", data);


                        String message = response.getString("message");

                        WebService.Make_Toast_color(MyProfileInformationActivity.this, message, "success");

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(MyProfileInformationActivity.this, message, "error");
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


                } catch (Exception e) {

                }


                WebService.loading(MyProfileInformationActivity.this, false);
                WebService.Make_Toast_color(MyProfileInformationActivity.this, error.getMessage(), "error");


            }
        };


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1213 && resultCode == Activity.RESULT_OK) {
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        if (requestCode == 11) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(MyProfileInformationActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                    // No explanation needed, we can request the permission.

//                    ActivityCompat.requestPermissions(MyProfileInformationActivity.this,
//                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                            11);

                } else {

                    Intent intent = new Intent(MyProfileInformationActivity.this, ImageSelectActivity.class);
                    intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                    startActivityForResult(intent, 1213);
                }
            } else {
                Intent intent = new Intent(MyProfileInformationActivity.this, ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                startActivityForResult(intent, 1213);

            }
        }


    }

    public void Upload_image(RequestParams requestParams) {


        AsyncHttpClient client = new AsyncHttpClient();
        String BASE_URL = WebService.profile_image;
        WebService.loading(MyProfileInformationActivity.this, true);


        final int DEFAULT_TIMEOUT = 20 * 1000;

        client.setTimeout(DEFAULT_TIMEOUT);
        WebService.Header_Async(client, true);

        client.post(BASE_URL, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                WebService.loading(MyProfileInformationActivity.this, false);
//                Addproduct_next_btn.setClickable(true);

                try {

                    String status = responseBody.getString("status");
//
//                    String message = responseBody.getString("message");
//                    String code = responseBody.getString("code");
                    String message = responseBody.getString("message");

                    if (status.equals("true")) {

                        String data = responseBody.getString("data");

                        Hawk.put("user", data);


                        WebService.Make_Toast_color(MyProfileInformationActivity.this, message, "success");

                    } else {


                        WebService.Make_Toast(MyProfileInformationActivity.this, message);
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
                WebService.loading(MyProfileInformationActivity.this, false);


                WebService.loading(MyProfileInformationActivity.this, false);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                try {

                    WebService.loading(MyProfileInformationActivity.this, false);

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
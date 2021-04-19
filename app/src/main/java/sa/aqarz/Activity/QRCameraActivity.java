package sa.aqarz.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import sa.aqarz.Activity.profile.OtherProfileActivity;
import sa.aqarz.R;


public class QRCameraActivity extends AppCompatActivity {
    private CodeScanner mCodeScanner;


    String qr_code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_camera);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }

        ImageView image = findViewById(R.id.back);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (image != null) {
                image.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(QRCameraActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(QRCameraActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        121);

            } else {
                CodeScannerView scannerView = findViewById(R.id.scanner_view);
                mCodeScanner = new CodeScanner(this, scannerView);
                mCodeScanner.setDecodeCallback(new DecodeCallback() {
                    @Override
                    public void onDecoded(@NonNull final Result result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                System.out.println("result###" + result.getText() + "");
                                Toast.makeText(QRCameraActivity.this, result.getText(), Toast.LENGTH_SHORT).show();


                                Intent intent = new Intent(QRCameraActivity.this, OtherProfileActivity.class);
                                intent.putExtra("id", result.getText() + "");
                                startActivity(intent);

//                        qr_code = result.getText();
//                        Intent returnIntent = new Intent();
//                        returnIntent.putExtra("result", result.getText() + "");
//                        setResult(Activity.RESULT_OK, returnIntent);
//                        finish();
                            }
                        });
                    }
                });

                scannerView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mCodeScanner.startPreview();
                    }
                });
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (ContextCompat.checkSelfPermission(QRCameraActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        } else {


            CodeScannerView scannerView = findViewById(R.id.scanner_view);
            mCodeScanner = new CodeScanner(this, scannerView);
            mCodeScanner.setDecodeCallback(new DecodeCallback() {
                @Override
                public void onDecoded(@NonNull final Result result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("result###" + result.getText() + "");
                            Toast.makeText(QRCameraActivity.this, result.getText(), Toast.LENGTH_SHORT).show();


                            Intent intent = new Intent(QRCameraActivity.this, OtherProfileActivity.class);
                            intent.putExtra("id", result.getText() + "");
                            startActivity(intent);
                        }
                    });
                }
            });
            scannerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCodeScanner.startPreview();
                }
            });
//
//


        }
    }
}
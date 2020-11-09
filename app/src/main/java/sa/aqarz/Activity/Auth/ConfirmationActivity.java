package sa.aqarz.Activity.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class ConfirmationActivity extends AppCompatActivity {
    IResult mResultCallback;
    TextView mobile;
    String mobilex = "";
    String codex = "";


    EditText a1, a2, a3, a4, a5, a6;

    AppCompatButton confirm;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        mobile = findViewById(R.id.mobile);
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        a5 = findViewById(R.id.a5);
        a6 = findViewById(R.id.a6);
        confirm = findViewById(R.id.confirm);
        back = findViewById(R.id.back);


        AppCompatImageView yourView = findViewById(R.id.immage);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (yourView != null) {
                yourView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        init_volley();


        try {

            mobilex = getIntent().getStringExtra("mobile");
            codex = getIntent().getStringExtra("code");

            mobile.setText(mobilex + "");
        } catch (Exception e) {

        }
        a1.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        a1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (a1.length() != 0) {
                    a2.requestFocus();
                }


            }

        });


        a2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (a2.length() != 0) {
                    a3.requestFocus();
                } else {
                    a1.requestFocus();

                }


            }

        });

        a3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (a3.length() != 0) {
                    a4.requestFocus();
                } else {
                    a2.requestFocus();

                }


            }

        });
        a4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (a4.length() != 0) {
                    a5.requestFocus();
                } else {
                    a3.requestFocus();

                }


            }

        });
        a5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (a5.length() != 0) {
                    a6.requestFocus();
                } else {
                    a4.requestFocus();

                }


            }

        });
        a6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (a6.length() != 0) {

                } else {
                    a5.requestFocus();

                }


            }

        });


        final PinEntryEditText pinEntry = (PinEntryEditText) findViewById(R.id.txt_pin_entry);
        if (pinEntry != null) {
            pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    if (str.toString().length() == 4) {


                        WebService.loading(ConfirmationActivity.this, true);

                        VolleyService mVolleyService = new VolleyService(mResultCallback, ConfirmationActivity.this);


                        JSONObject sendObj = new JSONObject();

                        try {


//                            sendObj.put("password_confirmation", password.getText().toString());

                            sendObj.put("code", str.toString());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mVolleyService.postDataVolley("mobile verify", WebService.mobile_verify, sendObj);


                    } else {

//                        pinEntry.setText(null);
                    }
                }
            });
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (a1.getText().toString().equals("") |
                        a2.getText().toString().equals("") |
                        a3.getText().toString().equals("") |
                        a4.getText().toString().equals("") |
                        a5.getText().toString().equals("") |
                        a6.getText().toString().equals("")
                ) {

                } else {
                    String code2 = a1.getText().toString() +
                            a2.getText().toString() +
                            a3.getText().toString() +
                            a4.getText().toString() +
                            a5.getText().toString() +
                            a6.getText().toString() + "";


                    System.out.println(code2 + "++++++" + codex);
                    if (code2.equals(codex)) {

                        Intent intent = new Intent(ConfirmationActivity.this, NewConfirmationActivity.class);
                        intent.putExtra("mobile", mobilex);
                        intent.putExtra("code", codex);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                        finish();
                    } else {
                        WebService.Make_Toast_color(ConfirmationActivity.this, getResources().getString(R.string.The_code_is_wrong), "error");

                    }
                }


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
                WebService.loading(ConfirmationActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

//                        Hawk.put("user", data);
                        String message = response.getString("message");

                        WebService.Make_Toast_color(ConfirmationActivity.this, message, "success");


                        Intent intent = new Intent(ConfirmationActivity.this, ConfirmationActivity.class);
//                                intent.putExtra("from", "splash");
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                        finish();

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(ConfirmationActivity.this, message, "error");
                    }


                } catch (Exception e) {

                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());
                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(ConfirmationActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }
                WebService.loading(ConfirmationActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

}
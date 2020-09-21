package aqarz.revival.sa.aqarz.Activity.OprationAqarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.NewPasswordActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_map;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_order;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Type_in_order;
import aqarz.revival.sa.aqarz.Fragment.MapsFragment;
import aqarz.revival.sa.aqarz.Fragment.TypeOrders.type1Fragment;
import aqarz.revival.sa.aqarz.Fragment.TypeOrders.type2Fragment;
import aqarz.revival.sa.aqarz.Fragment.TypeOrders.type3Fragment;
import aqarz.revival.sa.aqarz.Fragment.TypeOrders.type4Fragment;
import aqarz.revival.sa.aqarz.Fragment.TypeOrders.type5Fragment;
import aqarz.revival.sa.aqarz.Fragment.TypeOrders.type6Fragment;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;

public class RequestOrderActivity extends AppCompatActivity {
    RecyclerView opration_RecyclerView;
    RecyclerView opration_2__RecyclerView;
    RecyclerView type_RecyclerView;

    LinearLayout view_;

    IResult mResultCallback;

    List<OprationModules> oprationModules_list = new ArrayList<>();
    List<TypeModules> typeModules_list = new ArrayList<>();


    private static FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_order);
        init();

        LinearLayout yourView = findViewById(R.id.layo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (yourView != null) {
                yourView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }


    }


    public void init() {
        type_RecyclerView = findViewById(R.id.type_RecyclerView);
        view_ = findViewById(R.id.view_);


//


        LinearLayoutManager layoutManagers
                = new LinearLayoutManager(RequestOrderActivity.this, LinearLayoutManager.HORIZONTAL, false);
        type_RecyclerView.setLayoutManager(layoutManagers);


        fragmentManager = getSupportFragmentManager();


        fragmentTransaction = fragmentManager.beginTransaction();


        fragmentTransaction.replace(R.id.container, new type1Fragment());
        //  fragmentTransaction.commit();
        fragmentTransaction.commitAllowingStateLoss();

        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, RequestOrderActivity.this);
        mVolleyService.postDataVolley_without_token("operation_type", WebService.operation_type, null);
        WebService.loading(RequestOrderActivity.this, true);

    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {

                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(RequestOrderActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");


                        JSONArray jsonArray = new JSONArray(data);

                        typeModules_list.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {


                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(jsonArray.getString(i));

                            Gson gson = new Gson();

                            TypeModules Store_M = gson.fromJson(mJson, TypeModules.class);
                            typeModules_list.add(Store_M);
                        }


                        type_RecyclerView.setAdapter(new RecyclerView_All_Type_in_order(RequestOrderActivity.this, typeModules_list));


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(RequestOrderActivity.this, message, "error");
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


                WebService.loading(RequestOrderActivity.this, false);
                WebService.Make_Toast_color(RequestOrderActivity.this, error.getMessage(), "error");


            }
        };


    }

    public static void set_fragment(int id) {
        switch (id) {

            case 1:


                break;

            case 2:
                fragmentTransaction.replace(R.id.container, new type2Fragment());
                //  fragmentTransaction.commit();
                fragmentTransaction.commitAllowingStateLoss();

                break;

            case 3:
                fragmentTransaction.replace(R.id.container, new type3Fragment());
                //  fragmentTransaction.commit();
                fragmentTransaction.commitAllowingStateLoss();

                break;

            case 4:
                fragmentTransaction.replace(R.id.container, new type4Fragment());
                //  fragmentTransaction.commit();
                fragmentTransaction.commitAllowingStateLoss();

                break;

            case 5:
                fragmentTransaction.replace(R.id.container, new type5Fragment());
                //  fragmentTransaction.commit();
                fragmentTransaction.commitAllowingStateLoss();

                break;

            case 6:
                fragmentTransaction.replace(R.id.container, new type6Fragment());
                //  fragmentTransaction.commit();
                fragmentTransaction.commitAllowingStateLoss();

                break;


        }


    }

}
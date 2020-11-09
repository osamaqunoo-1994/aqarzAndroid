package sa.aqarz.Activity.OprationAqarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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

import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.Auth.NewPasswordActivity;
import sa.aqarz.Adapter.RecyclerView_All_Opration_in_map;
import sa.aqarz.Adapter.RecyclerView_All_Opration_in_order;
import sa.aqarz.Adapter.RecyclerView_All_Type_in_order;
import sa.aqarz.Fragment.MapsFragment;
import sa.aqarz.Fragment.TypeOrders.type1Fragment;
import sa.aqarz.Fragment.TypeOrders.type2Fragment;
import sa.aqarz.Fragment.TypeOrders.type3Fragment;
import sa.aqarz.Fragment.TypeOrders.type4Fragment;
import sa.aqarz.Fragment.TypeOrders.type5Fragment;
import sa.aqarz.Fragment.TypeOrders.type6Fragment;
import sa.aqarz.Modules.OprationModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class RequestOrderActivity extends AppCompatActivity {
    RecyclerView opration_RecyclerView;
    RecyclerView opration_2__RecyclerView;
    RecyclerView type_RecyclerView;

    LinearLayout view_;
    static Activity activity;
    IResult mResultCallback;
    static Bundle bundle = new Bundle();

    List<OprationModules> oprationModules_list = new ArrayList<>();
    List<TypeModules> typeModules_list = new ArrayList<>();

    ImageView back;
    private static FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_order);
        activity = this;

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
        back = findViewById(R.id.back);

        //---------------------------------------------------------------------------------------
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //---------------------------------------------------------------------------------------
        LinearLayoutManager layoutManagers
                = new LinearLayoutManager(RequestOrderActivity.this, LinearLayoutManager.HORIZONTAL, false);
        type_RecyclerView.setLayoutManager(layoutManagers);

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
                        RecyclerView_All_Type_in_order recyclerView_all_type_in_order = new RecyclerView_All_Type_in_order(RequestOrderActivity.this, typeModules_list);
                        recyclerView_all_type_in_order.addItemClickListener(new RecyclerView_All_Type_in_order.ItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                set_fragment(typeModules_list.get(position).getId());
                            }
                        });
                        type_RecyclerView.setAdapter(recyclerView_all_type_in_order);

                        bundle.putString("Id_eastate", typeModules_list.get(0).getId() + "");

                        type1Fragment type1Fragment = new type1Fragment();
                        type1Fragment.setArguments(bundle);

                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


                        transaction.replace(R.id.container, type1Fragment);
                        transaction.addToBackStack(null);

// Commit the transaction
                        transaction.commit();
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

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

    public void set_fragment(int id) {
        switch (id) {

            case 1:

                bundle.putString("Id_eastate", id + "");

                type1Fragment type1Fragment = new type1Fragment();
                type1Fragment.setArguments(bundle);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


                transaction.replace(R.id.container, type1Fragment);
                transaction.addToBackStack(null);

// Commit the transaction
                transaction.commit();

                break;

            case 2:

                bundle.putString("Id_eastate", id + "");

                type5Fragment type5Fragment = new type5Fragment();
                type5Fragment.setArguments(bundle);

                FragmentTransaction transaction5 = getSupportFragmentManager().beginTransaction();


                transaction5.replace(R.id.container, type5Fragment);
                transaction5.addToBackStack(null);


                transaction5.commit();
                break;

            case 3:
                bundle.putString("Id_eastate", id + "");

                type2Fragment type2Fragment = new type2Fragment();
                type2Fragment.setArguments(bundle);

                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();


                transaction2.replace(R.id.container, type2Fragment);
                transaction2.addToBackStack(null);

// Commit the transaction
                transaction2.commit();

                break;

            case 4:


                type6Fragment type6Fragment = new type6Fragment();
                type6Fragment.setArguments(bundle);

                FragmentTransaction transaction6 = getSupportFragmentManager().beginTransaction();


                transaction6.replace(R.id.container, type6Fragment);
                transaction6.addToBackStack(null);


                transaction6.commit();
                break;

            case 5:


                bundle.putString("Id_eastate", id + "");

                type3Fragment type3Fragment = new type3Fragment();
                type3Fragment.setArguments(bundle);

                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();


                transaction3.replace(R.id.container, type3Fragment);
                transaction3.addToBackStack(null);


                transaction3.commit();

                break;


            case 6:
                bundle.putString("Id_eastate", id + "");

                bundle.putString("Id_eastate", id + "");

                type4Fragment type4Fragment = new type4Fragment();
                type4Fragment.setArguments(bundle);

                FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();


                transaction4.replace(R.id.container, type4Fragment);
                transaction4.addToBackStack(null);


                transaction4.commit();
                break;


        }


    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
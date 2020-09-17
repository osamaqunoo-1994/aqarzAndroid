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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_map;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_order;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Type_in_order;
import aqarz.revival.sa.aqarz.Fragment.MapsFragment;
import aqarz.revival.sa.aqarz.Fragment.TypeOrders.type1Fragment;
import aqarz.revival.sa.aqarz.Fragment.TypeOrders.type2Fragment;
import aqarz.revival.sa.aqarz.Fragment.TypeOrders.type3Fragment;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;

public class RequestOrderActivity extends AppCompatActivity {
    RecyclerView opration_RecyclerView;
    RecyclerView opration_2__RecyclerView;
    RecyclerView type_RecyclerView;

    LinearLayout view_;


    List<OprationModules> oprationModules_list = new ArrayList<>();
    List<TypeModules> typeModules_list = new ArrayList<>();


    private FragmentTransaction fragmentTransaction;
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
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());


        LinearLayoutManager layoutManagers
                = new LinearLayoutManager(RequestOrderActivity.this, LinearLayoutManager.HORIZONTAL, false);
        type_RecyclerView.setLayoutManager(layoutManagers);
        type_RecyclerView.setAdapter(new RecyclerView_All_Type_in_order(RequestOrderActivity.this, typeModules_list));


        fragmentManager = getSupportFragmentManager();


        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new type3Fragment());
        //  fragmentTransaction.commit();
        fragmentTransaction.commitAllowingStateLoss();
    }







}
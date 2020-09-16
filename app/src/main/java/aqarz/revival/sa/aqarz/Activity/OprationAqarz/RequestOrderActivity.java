package aqarz.revival.sa.aqarz.Activity.OprationAqarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_map;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_order;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Type_in_order;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;

public class RequestOrderActivity extends AppCompatActivity {
    RecyclerView opration_RecyclerView;
    RecyclerView opration_2__RecyclerView;
    RecyclerView type_RecyclerView;


    List<OprationModules> oprationModules_list = new ArrayList<>();
    List<TypeModules> typeModules_list = new ArrayList<>();


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
        opration_RecyclerView = findViewById(R.id.opration_RecyclerView);
        type_RecyclerView = findViewById(R.id.type_RecyclerView);
        opration_2__RecyclerView = findViewById(R.id.opration_2__RecyclerView);


        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());


        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());
        typeModules_list.add(new TypeModules());


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(RequestOrderActivity.this, LinearLayoutManager.HORIZONTAL, false);
        opration_RecyclerView.setLayoutManager(layoutManager1);
        opration_RecyclerView.setAdapter(new RecyclerView_All_Opration_in_order(RequestOrderActivity.this, oprationModules_list));


        LinearLayoutManager layoutManagerm
                = new LinearLayoutManager(RequestOrderActivity.this, LinearLayoutManager.HORIZONTAL, false);
        opration_2__RecyclerView.setLayoutManager(layoutManagerm);
        opration_2__RecyclerView.setAdapter(new RecyclerView_All_Opration_in_order(RequestOrderActivity.this, oprationModules_list));


        LinearLayoutManager layoutManagers
                = new LinearLayoutManager(RequestOrderActivity.this, LinearLayoutManager.HORIZONTAL, false);
        type_RecyclerView.setLayoutManager(layoutManagers);
        type_RecyclerView.setAdapter(new RecyclerView_All_Type_in_order(RequestOrderActivity.this, typeModules_list));


    }
}
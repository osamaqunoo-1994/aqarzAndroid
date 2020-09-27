package aqarz.revival.sa.aqarz.Fragment.TypeOrders;
/**
 * Created by osama on 10/8/2017.
 */


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_order;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;


public class type3Fragment extends Fragment {

    String opration_select = "";

    List<TypeModules> type_list = new ArrayList<>();

    RecyclerView opration_RecyclerView;

    List<OprationModules> oprationModules_list = new ArrayList<>();

    String Id_eastate = "";


    EditText description;
    ImageView map_select;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.requst_order_type_3, container, false);
        Id_eastate = getArguments().getString("Id_eastate");

        init(v);
        return v;
    }


    public void init(View v) {
        opration_RecyclerView = v.findViewById(R.id.opration_RecyclerView);
        description = v.findViewById(R.id.description);
        map_select = v.findViewById(R.id.map_select);


        //---------------------------------------------------------------------------------------------

        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        System.out.println("type_list" + type_list.size());


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        opration_RecyclerView.setLayoutManager(layoutManager1);
        RecyclerView_All_type_in_fragment recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_fragment(getContext(), type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_fragment.ItemClickListener() {
            @Override
            public void onItemClick(int position) {


                opration_select = type_list.get(position).getId().toString() + "";


            }
        });
        opration_RecyclerView.setAdapter(recyclerView_all_type_in_fragment);

//---------------------------------------------------------------------------------------
        map_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                11);

                    } else {


                    }
                } else {


                }

            }
        });
    }


    public static type3Fragment newInstance(String text) {

        type3Fragment f = new type3Fragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }


    @Override
    public void onResume() {
        super.onResume();
    }


}
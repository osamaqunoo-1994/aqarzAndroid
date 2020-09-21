package aqarz.revival.sa.aqarz.Fragment.TypeOrders;
/**
 * Created by osama on 10/8/2017.
 */


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Activity.OprationAqarz.RequestOrderActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_order;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.R;


public class type2Fragment extends Fragment {

    RecyclerView opration_2__RecyclerView;


    List<OprationModules> oprationModules_list = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.requst_order_type_2, container, false);

        init(v);
        return v;
    }


    public void init(View v) {


        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());


        opration_2__RecyclerView = v.findViewById(R.id.opration_2__RecyclerView);
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());


        LinearLayoutManager layoutManagerm
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        opration_2__RecyclerView.setLayoutManager(layoutManagerm);
//        opration_2__RecyclerView.setAdapter(new RecyclerView_All_Opration_in_order(getContext(), oprationModules_list));


    }


    public static type2Fragment newInstance(String text) {

        type2Fragment f = new type2Fragment();
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
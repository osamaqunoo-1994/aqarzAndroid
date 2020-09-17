package aqarz.revival.sa.aqarz.Fragment.TypeOrders;
/**
 * Created by osama on 10/8/2017.
 */


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Activity.Auth.LoginActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.NewPasswordActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.RegisterActivity;
import aqarz.revival.sa.aqarz.Activity.OprationAqarz.RequestOrderActivity;
import aqarz.revival.sa.aqarz.Activity.SplashScreenActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_order;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.LocaleUtils;
import aqarz.revival.sa.aqarz.Settings.Settings;


public class type1Fragment extends Fragment {

    RecyclerView opration_RecyclerView;



    List<OprationModules> oprationModules_list = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.requst_order_type_1, container, false);

        init(v);
        return v;
    }


    public void init(View v) {




        opration_RecyclerView = v.findViewById(R.id.opration_RecyclerView);
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());
        oprationModules_list.add(new OprationModules());


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        opration_RecyclerView.setLayoutManager(layoutManager1);
        opration_RecyclerView.setAdapter(new RecyclerView_All_Opration_in_order(getContext(), oprationModules_list));

//


    }


    public static type1Fragment newInstance(String text) {

        type1Fragment f = new type1Fragment();
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
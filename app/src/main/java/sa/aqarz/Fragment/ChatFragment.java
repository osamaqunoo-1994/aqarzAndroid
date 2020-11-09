package sa.aqarz.Fragment;
/**
 * Created by osama on 10/8/2017.
 */


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.Auth.NewPasswordActivity;
import sa.aqarz.Activity.Auth.RegisterActivity;
import sa.aqarz.Activity.ContactUsActivity;
import sa.aqarz.Activity.PrivecyActivity;
import sa.aqarz.Activity.SplashScreenActivity;
import sa.aqarz.Activity.TermsActivity;
import sa.aqarz.Adapter.RecyclerView_chat_main;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.R;
import sa.aqarz.Settings.LocaleUtils;
import sa.aqarz.Settings.Settings;
import de.hdodenhof.circleimageview.CircleImageView;


public class ChatFragment extends Fragment {
RecyclerView chate;

List<OrdersModules> ordersModules=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        chate=v.findViewById(R.id.chate);


        ordersModules.add(new OrdersModules());
        ordersModules.add(new OrdersModules());
        ordersModules.add(new OrdersModules());
        ordersModules.add(new OrdersModules());
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        chate.setLayoutManager(layoutManager1);



        chate.setAdapter(new RecyclerView_chat_main(getContext(),ordersModules));


        init(v);
        return v;
    }


    public void init(View v) {


    }


    public static ChatFragment newInstance(String text) {

        ChatFragment f = new ChatFragment();
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
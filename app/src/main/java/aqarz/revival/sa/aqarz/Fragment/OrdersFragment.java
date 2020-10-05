package aqarz.revival.sa.aqarz.Fragment;
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
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import aqarz.revival.sa.aqarz.Activity.Auth.LoginActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.NewPasswordActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.RegisterActivity;
import aqarz.revival.sa.aqarz.Activity.ContactUsActivity;
import aqarz.revival.sa.aqarz.Activity.PrivecyActivity;
import aqarz.revival.sa.aqarz.Activity.SplashScreenActivity;
import aqarz.revival.sa.aqarz.Activity.TermsActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Comfort_in_fragment;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Type_order_;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_orders;
import aqarz.revival.sa.aqarz.Modules.OrdersModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.LocaleUtils;
import aqarz.revival.sa.aqarz.Settings.Settings;
import de.hdodenhof.circleimageview.CircleImageView;


public class OrdersFragment extends Fragment {

    HorizontalScrollView section_horizantal;
    RecyclerView orders_rec;
    RecyclerView list_opration;
    RecyclerView type_of_v;
    LinearLayout type_sale;
    List<OrdersModules> ordersModules = new ArrayList<>();

    List<TypeModules> type_list = new ArrayList<>();

    LinearLayout my_order_layout;
    LinearLayout Shopping_request_layout;
    LinearLayout Real_Estate_order_layout;
    TextView my_order_text;
    TextView Real_Estate_order_text;
    ImageView Real_Estate_order_image;
    TextView Shopping_request_text;


    List<TypeModules> data = new ArrayList<>();


    TextView For_sale, rent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_orders, container, false);

        init(v);
        return v;
    }


    public void init(View v) {
        orders_rec = v.findViewById(R.id.orders_rec);

        my_order_layout = v.findViewById(R.id.my_order_layout);
        Shopping_request_layout = v.findViewById(R.id.Shopping_request_layout);
        Real_Estate_order_layout = v.findViewById(R.id.Real_Estate_order_layout);
        my_order_text = v.findViewById(R.id.my_order_text);
        Real_Estate_order_text = v.findViewById(R.id.Real_Estate_order_text);
        Real_Estate_order_image = v.findViewById(R.id.Real_Estate_order_image);
        Shopping_request_text = v.findViewById(R.id.Shopping_request_text);
        list_opration = v.findViewById(R.id.list_opration);
        For_sale = v.findViewById(R.id.For_sale);
        rent = v.findViewById(R.id.rent);
        type_of_v = v.findViewById(R.id.type_of_v);
        type_sale = v.findViewById(R.id.type_sale);
        section_horizantal = v.findViewById(R.id.section_horizantal);


        ordersModules.add(new OrdersModules());
        ordersModules.add(new OrdersModules());


        try {
            data = Settings.getSettings().getOprationType().getOriginal().getData();

            LinearLayoutManager layoutManager1
                    = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            list_opration.setLayoutManager(layoutManager1);
            RecyclerView_All_Type_order_ recyclerView_all_type_order_ = new RecyclerView_All_Type_order_(getContext(), data);
            recyclerView_all_type_order_.addItemClickListener(new RecyclerView_All_Comfort_in_fragment.ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (position == 0) {
                        list_opration.setVisibility(View.VISIBLE);
                        type_sale.setVisibility(View.VISIBLE);
                        section_horizantal.setVisibility(View.VISIBLE);
                    } else if (position == 1) {
                        list_opration.setVisibility(View.VISIBLE);
                        section_horizantal.setVisibility(View.VISIBLE);
                        type_sale.setVisibility(View.VISIBLE);
                    } else {
                        list_opration.setVisibility(View.VISIBLE);

                        type_sale.setVisibility(View.GONE);
                        section_horizantal.setVisibility(View.GONE);

                    }
                }
            });
            list_opration.setAdapter(recyclerView_all_type_order_);

        } catch (Exception e) {

        }


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        orders_rec.setLayoutManager(layoutManager1);
        orders_rec.setAdapter(new RecyclerView_orders(getContext(), ordersModules));
//------------------------------------------------------------------------------------------------------------
        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();


        LinearLayoutManager layoutManagerxmx
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        type_of_v.setLayoutManager(layoutManagerxmx);

        RecyclerView_All_type_in_fragment recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_fragment(getContext(), type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_fragment.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

//                opration_select = type_list.get(position).getId().toString() + "";

            }
        });
        type_of_v.setAdapter(recyclerView_all_type_in_fragment);
//------------------------------------------------------------------------------------------------------------

        For_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                For_sale.setBackground(getResources().getDrawable(R.drawable.button_login));

                For_sale.setTextColor(getResources().getColor(R.color.white));


                rent.setBackground(null);

                rent.setTextColor(getResources().getColor(R.color.black));

            }
        });
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rent.setBackground(getResources().getDrawable(R.drawable.button_login));

                rent.setTextColor(getResources().getColor(R.color.white));


                For_sale.setBackground(null);

                For_sale.setTextColor(getResources().getColor(R.color.black));


            }
        });

        //------------------------------------------------------------------------------------------------------------

        my_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_order_layout.setBackground(getResources().getDrawable(R.drawable.button_login));

                my_order_text.setTextColor(getResources().getColor(R.color.white));

                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.search_background));

                Shopping_request_text.setTextColor(getResources().getColor(R.color.textColor));

                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.search_background));

                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.color_tr));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_tr), android.graphics.PorterDuff.Mode.MULTIPLY);

                list_opration.setVisibility(View.VISIBLE);
                type_sale.setVisibility(View.VISIBLE);
            }
        });
        Shopping_request_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.button_login));

                Shopping_request_text.setTextColor(getResources().getColor(R.color.white));


                my_order_layout.setBackground(getResources().getDrawable(R.drawable.search_background));

                my_order_text.setTextColor(getResources().getColor(R.color.textColor));


                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.search_background));

                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.color_tr));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_tr), android.graphics.PorterDuff.Mode.MULTIPLY);


                list_opration.setVisibility(View.GONE);
                type_sale.setVisibility(View.VISIBLE);


            }
        });


        Real_Estate_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shopping_request_layout.setBackground(getResources().getDrawable(R.drawable.search_background));

                Shopping_request_text.setTextColor(getResources().getColor(R.color.textColor));


                my_order_layout.setBackground(getResources().getDrawable(R.drawable.search_background));

                my_order_text.setTextColor(getResources().getColor(R.color.textColor));


                Real_Estate_order_layout.setBackground(getResources().getDrawable(R.drawable.background_order));

                Real_Estate_order_text.setTextColor(getResources().getColor(R.color.white));
                Real_Estate_order_image.setColorFilter(ContextCompat.getColor(getContext(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);


                list_opration.setVisibility(View.GONE);
                type_sale.setVisibility(View.GONE);


            }
        });
        list_opration.setVisibility(View.GONE);
        type_sale.setVisibility(View.GONE);

    }


    public static OrdersFragment newInstance(String text) {

        OrdersFragment f = new OrdersFragment();
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
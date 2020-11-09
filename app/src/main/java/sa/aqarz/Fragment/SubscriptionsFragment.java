package sa.aqarz.Fragment;
/**
 * Created by osama on 10/8/2017.
 */


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.RecyclerView_orders;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.R;


public class SubscriptionsFragment extends Fragment {

LinearLayout f1,f2,f3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_subsecription, container, false);
        f1=v.findViewById(R.id.f1);
        f2=v.findViewById(R.id.f2);
        f3=v.findViewById(R.id.f3);


        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                f1.setBackground(getResources().getDrawable(R.drawable.edit_text_background_order_color));
                f2.setBackground(getResources().getDrawable(R.drawable.edit_text_background_order));
                f3.setBackground(getResources().getDrawable(R.drawable.edit_text_background_order));

            }
        });
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1.setBackground(getResources().getDrawable(R.drawable.edit_text_background_order));
                f2.setBackground(getResources().getDrawable(R.drawable.edit_text_background_order_color));
                f3.setBackground(getResources().getDrawable(R.drawable.edit_text_background_order));

            }
        });
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1.setBackground(getResources().getDrawable(R.drawable.edit_text_background_order));
                f2.setBackground(getResources().getDrawable(R.drawable.edit_text_background_order));
                f3.setBackground(getResources().getDrawable(R.drawable.edit_text_background_order_color));

            }
        });




        return v;
    }





    public static SubscriptionsFragment newInstance(String text) {

        SubscriptionsFragment f = new SubscriptionsFragment();
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
package aqarz.revival.sa.aqarz.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Type_in_order;
import aqarz.revival.sa.aqarz.Adapter.home_viewPager_Adapter;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;

public class DetailsActivity_aqarz extends AppCompatActivity {
    ViewPagerIndicator view_pager_indicator;
    ViewPager home_viewPager;

    private ArrayList<String> items_ViewPager = new ArrayList<String>();

    RecyclerView type_RecyclerView;
    List<TypeModules> typeModules_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        init();
    }

    public void init() {

        home_viewPager = findViewById(R.id.home_viewPager);
        view_pager_indicator = findViewById(R.id.view_pager_indicator);
        type_RecyclerView = findViewById(R.id.type_RecyclerView);


        typeModules_list = Settings.getSettings().getOprationType().getOriginal().getData();
        LinearLayoutManager layoutManagers
                = new LinearLayoutManager(DetailsActivity_aqarz.this, LinearLayoutManager.HORIZONTAL, false);
        type_RecyclerView.setLayoutManager(layoutManagers);

        RecyclerView_All_Type_in_order recyclerView_all_type_in_order = new RecyclerView_All_Type_in_order(DetailsActivity_aqarz.this, typeModules_list);
        recyclerView_all_type_in_order.addItemClickListener(new RecyclerView_All_Type_in_order.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                set_fragment(typeModules_list.get(position).getId());
            }
        });
        type_RecyclerView.setAdapter(recyclerView_all_type_in_order);

        items_ViewPager.add("");
        items_ViewPager.add("");
        items_ViewPager.add("");


        home_viewPager.setAdapter(new home_viewPager_Adapter(DetailsActivity_aqarz.this, items_ViewPager));
        view_pager_indicator.setupWithViewPager(home_viewPager);


//        home_viewPager.setClipToPadding(false);
//        // set padding manually, the more you set the padding the more you see of prev & next page
//        home_viewPager.setPadding(0, 0, 110, 0);
//        // sets a margin b/w individual pages to ensure that there is a gap b/w them
//        home_viewPager.setPageMargin(20);

    }
}
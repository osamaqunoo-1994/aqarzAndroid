package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.detials_viewPager_Adapter;
import sa.aqarz.Fragment.mapsHome.MapsFragmentNew;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.R;

public class DetailsNewAqarezActivity extends AppCompatActivity {
    ViewPager home_viewPager;
    static List<HomeModules_aqares> homeModules_aqares = new ArrayList<>();
public static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_new_aqarez);
        activity=this;

        homeModules_aqares= MapsFragmentNew.homeModules_aqares;




        home_viewPager = findViewById(R.id.pager);
        home_viewPager.setAdapter(new detials_viewPager_Adapter(DetailsNewAqarezActivity.this, homeModules_aqares));

        try {

            String postion=getIntent().getStringExtra("postion");


            int i=Integer.valueOf(postion);


            home_viewPager.setCurrentItem(i);



        }catch (Exception e){

        }

    }
}
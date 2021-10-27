package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;

import sa.aqarz.Adapter.fullscreen_viewPager_Adapter;
import sa.aqarz.Adapter.home_viewPager_Adapter;
import sa.aqarz.NewAqarz.MainAqarzActivity;
import sa.aqarz.R;

public class FullScreenImageActivity extends AppCompatActivity {
    ImageView back;
    ViewPagerIndicator view_pager_indicator;
    ViewPager home_viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        home_viewPager = findViewById(R.id.home_viewPager);
        view_pager_indicator = findViewById(R.id.view_pager_indicator);


        System.out.println("TTTTTT" + DetailsActivity_aqarz.items_ViewPager.size());

        home_viewPager.setAdapter(new fullscreen_viewPager_Adapter(FullScreenImageActivity.this, MainAqarzActivity.items_ViewPager_all));
        try {
            String position = getIntent().getStringExtra("position");
            home_viewPager.setCurrentItem(Integer.valueOf(position));
        } catch (Exception e) {

        }

        view_pager_indicator.setupWithViewPager(home_viewPager);

    }
}
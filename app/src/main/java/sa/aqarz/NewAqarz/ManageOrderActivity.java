package sa.aqarz.NewAqarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.booking.rtlviewpager.RtlViewPager;
import com.google.android.material.tabs.TabLayout;

import sa.aqarz.NewAqarz.Fragments.FirstFragment;
import sa.aqarz.NewAqarz.Fragments.OrderFirstFragment;
import sa.aqarz.NewAqarz.Fragments.OrderSecandFragment;
import sa.aqarz.NewAqarz.Fragments.SecandFragment;
import sa.aqarz.R;

public class ManageOrderActivity extends AppCompatActivity {
    TabLayout tabLayout;
    RtlViewPager viewPager;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_order);

        tabLayout = findViewById(R.id.my_tabs);
        back = findViewById(R.id.back);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (back != null) {
                back.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }


        viewPager = findViewById(R.id.pager);
        ViewPagerAdapter viewPagerz = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerz);
        tabLayout.setupWithViewPager(viewPager);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new OrderFirstFragment();
            } else if (position == 1) {
                fragment = new OrderSecandFragment();
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = getResources().getString(R.string.title_tab_order_1);
            } else if (position == 1) {
                title = getResources().getString(R.string.title_tab_order_2);
            }
            return title;
        }
    }
}
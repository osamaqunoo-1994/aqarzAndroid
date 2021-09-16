package sa.aqarz.NewAqarz.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.booking.rtlviewpager.RtlViewPager;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.hawk.Hawk;

import sa.aqarz.Fragment.mapsHome.MapsFragmentNew;
import sa.aqarz.NewAqarz.MainAqarzActivity;
import sa.aqarz.NewAqarz.ManageOrderActivity;
import sa.aqarz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ViewPager vpPager;


    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextView Real_Estate_orders;
    TextView Shoppingrequest;
    LinearLayout Shoppingrequest_line;
    LinearLayout Real_Estate_orders_line;
    private static FragmentTransaction fragmentTransaction;
    private static FragmentManager fragmentManager;
    TabLayout tabLayout;
    RtlViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        Real_Estate_orders = view.findViewById(R.id.Real_Estate_orders);
        Shoppingrequest = view.findViewById(R.id.Shoppingrequest);
        Shoppingrequest_line = view.findViewById(R.id.Shoppingrequest_line);
        Real_Estate_orders_line = view.findViewById(R.id.Real_Estate_orders_line);

//
//        tabLayout = view.findViewById(R.id.my_tabs);
//
//        viewPager = view.findViewById(R.id.pager);
//        ViewPagerAdapter viewPagerz = new ViewPagerAdapter(getChildFragmentManager());
//        viewPager.setAdapter(viewPagerz);
//        viewPager.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
//            @Override
//            public void onViewAttachedToWindow(View v) {
//
//            }
//
//            @Override
//            public void onViewDetachedFromWindow(View v) {
//
//            }
//        });
//        tabLayout.setupWithViewPager(viewPager);


//        vpPager = (ViewPager) view.findViewById(R.id.vpPager);
//        adapterViewPager = new MyPagerAdapter(getActivity().getSupportFragmentManager());
//        vpPager.setAdapter(adapterViewPager);


//        if (Hawk.get("lang").toString().equals("ar")) {
//            vpPager.setCurrentItem(1);
//        }

        if (!MainAqarzActivity.type_order_main.equals("")) {
            if (MainAqarzActivity.type_order_main.equals("real")) {
                MainAqarzActivity.type_order_main = "real";
                MainAqarzActivity.objectFiltterOrder.setTypelayout("Real");
                Real_Estate_orders.setTextColor(getResources().getColor(R.color.colorPrimary));
                Shoppingrequest.setTextColor(getResources().getColor(R.color.te_unselected));


                Real_Estate_orders_line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                Shoppingrequest_line.setBackgroundColor(getResources().getColor(R.color.te_unselected));

                fragmentManager = getActivity().getSupportFragmentManager();

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new FirstFragment());
                //  fragmentTransaction.commit();
                fragmentTransaction.commitAllowingStateLoss();
            } else {
                MainAqarzActivity.type_order_main = "Shopping";
                MainAqarzActivity.objectFiltterOrder.setTypelayout("Market");

                Shoppingrequest.setTextColor(getResources().getColor(R.color.colorPrimary));
                Real_Estate_orders.setTextColor(getResources().getColor(R.color.te_unselected));

                Real_Estate_orders_line.setBackgroundColor(getResources().getColor(R.color.te_unselected));
                Shoppingrequest_line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                fragmentManager = getActivity().getSupportFragmentManager();

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new SecandFragment());
                //  fragmentTransaction.commit();
                fragmentTransaction.commitAllowingStateLoss();
            }
        } else {
            MainAqarzActivity.type_order_main = "real";
            MainAqarzActivity.objectFiltterOrder.setTypelayout("Real");

            Real_Estate_orders.setTextColor(getResources().getColor(R.color.colorPrimary));
            Shoppingrequest.setTextColor(getResources().getColor(R.color.te_unselected));


            Real_Estate_orders_line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            Shoppingrequest_line.setBackgroundColor(getResources().getColor(R.color.te_unselected));

            fragmentManager = getActivity().getSupportFragmentManager();

            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, new FirstFragment());
            //  fragmentTransaction.commit();
            fragmentTransaction.commitAllowingStateLoss();

        }


        Real_Estate_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                vpPager.setCurrentItem(1);
                MainAqarzActivity.type_order_main = "real";
                MainAqarzActivity.objectFiltterOrder.setTypelayout("Real");

                Real_Estate_orders.setTextColor(getResources().getColor(R.color.colorPrimary));
                Shoppingrequest.setTextColor(getResources().getColor(R.color.te_unselected));


                Real_Estate_orders_line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                Shoppingrequest_line.setBackgroundColor(getResources().getColor(R.color.te_unselected));

                fragmentManager = getActivity().getSupportFragmentManager();

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new FirstFragment());
                //  fragmentTransaction.commit();
                fragmentTransaction.commitAllowingStateLoss();

            }
        });
        Shoppingrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                vpPager.setCurrentItem(0);
                MainAqarzActivity.type_order_main = "Shopping";
                MainAqarzActivity.objectFiltterOrder.setTypelayout("Market");

                Shoppingrequest.setTextColor(getResources().getColor(R.color.colorPrimary));
                Real_Estate_orders.setTextColor(getResources().getColor(R.color.te_unselected));

                Real_Estate_orders_line.setBackgroundColor(getResources().getColor(R.color.te_unselected));
                Shoppingrequest_line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                fragmentManager = getActivity().getSupportFragmentManager();

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new SecandFragment());
                //  fragmentTransaction.commit();
                fragmentTransaction.commitAllowingStateLoss();


            }
        });
//         Attach the page change listener inside the activity
//        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            // This method will be invoked when a new page becomes selected.
//            @Override
//            public void onPageSelected(int position) {
//
//                if (position == 1) {
//
//                    Real_Estate_orders.setTextColor(getResources().getColor(R.color.colorPrimary));
//                    Shoppingrequest.setTextColor(getResources().getColor(R.color.te_unselected));
//
//
//                    Real_Estate_orders_line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                    Shoppingrequest_line.setBackgroundColor(getResources().getColor(R.color.te_unselected));
//
//                } else {
//
//                    Shoppingrequest.setTextColor(getResources().getColor(R.color.colorPrimary));
//                    Real_Estate_orders.setTextColor(getResources().getColor(R.color.te_unselected));
//
//                    Real_Estate_orders_line.setBackgroundColor(getResources().getColor(R.color.te_unselected));
//                    Shoppingrequest_line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//
//                }
//            }
//
//            // This method will be invoked when the current page is scrolled
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                // Code goes here
//            }
//
//            // Called when the scroll state changes:
//            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                // Code goes here
//            }
//        });
        return view;


    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new FirstFragment();
            } else if (position == 1) {
                fragment = new SecandFragment();
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0) {
                title = getActivity().getResources().getString(R.string.Real_Estate_orders);
            } else if (position == 1) {
                title = getActivity().getResources().getString(R.string.Shoppingrequest);
            }

            return title;
        }
    }


}
package sa.aqarz.NewAqarz.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import sa.aqarz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    boolean isVisibleToUser;


    LinearLayout all_order_lay;
    LinearLayout order_day_lay;
    LinearLayout MyOffer_lay;


    TextView all_order;
    TextView all_order_num;
    TextView order_day;
    TextView order_day_num;
    TextView MyOffer;
    TextView MyOffer_num;

    TextView status_1;
    TextView status_2;
    TextView status_3;


    String type_order = "";


    LinearLayout all_status_offer;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);


        all_order_lay = view.findViewById(R.id.all_order_lay);
        order_day_lay = view.findViewById(R.id.order_day_lay);
        MyOffer_lay = view.findViewById(R.id.MyOffer_lay);


        all_order = view.findViewById(R.id.all_order);
        all_order_num = view.findViewById(R.id.all_order_num);
        order_day = view.findViewById(R.id.order_day);
        order_day_num = view.findViewById(R.id.order_day_num);
        MyOffer = view.findViewById(R.id.MyOffer);
        MyOffer_num = view.findViewById(R.id.MyOffer_num);

        status_1 = view.findViewById(R.id.status_1);
        status_2 = view.findViewById(R.id.status_2);
        status_3 = view.findViewById(R.id.status_3);

        all_status_offer = view.findViewById(R.id.all_status_offer);


//        if (isVisibleToUser) {
//            System.out.println("*************************************");
//        }
        onclick();

        return view;

    }

    public void setUserVisibleHint(boolean isVisibleToUsers) {
        super.setUserVisibleHint(isVisibleToUsers);
        isVisibleToUser = isVisibleToUsers;
    }

    public void onclick() {
        all_order.setTextColor(getResources().getColor(R.color.white));
        all_order_num.setTextColor(getResources().getColor(R.color.white));
        order_day.setTextColor(getResources().getColor(R.color.te_unselected));
        order_day_num.setTextColor(getResources().getColor(R.color.te_unselected));
        MyOffer.setTextColor(getResources().getColor(R.color.te_unselected));
        MyOffer_num.setTextColor(getResources().getColor(R.color.te_unselected));


        all_order_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
        order_day_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter));
        MyOffer_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter));


        all_order_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all_order.setTextColor(getResources().getColor(R.color.white));
                all_order_num.setTextColor(getResources().getColor(R.color.white));
                order_day.setTextColor(getResources().getColor(R.color.te_unselected));
                order_day_num.setTextColor(getResources().getColor(R.color.te_unselected));
                MyOffer.setTextColor(getResources().getColor(R.color.te_unselected));
                MyOffer_num.setTextColor(getResources().getColor(R.color.te_unselected));


                all_order_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                order_day_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                MyOffer_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter));

            }
        });
        order_day_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all_order.setTextColor(getResources().getColor(R.color.te_unselected));
                all_order_num.setTextColor(getResources().getColor(R.color.te_unselected));
                order_day.setTextColor(getResources().getColor(R.color.white));
                order_day_num.setTextColor(getResources().getColor(R.color.white));
                MyOffer.setTextColor(getResources().getColor(R.color.te_unselected));
                MyOffer_num.setTextColor(getResources().getColor(R.color.te_unselected));


                all_order_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                order_day_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                MyOffer_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter));

            }
        });
        MyOffer_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all_order.setTextColor(getResources().getColor(R.color.te_unselected));
                all_order_num.setTextColor(getResources().getColor(R.color.te_unselected));
                order_day.setTextColor(getResources().getColor(R.color.te_unselected));
                order_day_num.setTextColor(getResources().getColor(R.color.te_unselected));
                MyOffer.setTextColor(getResources().getColor(R.color.white));
                MyOffer_num.setTextColor(getResources().getColor(R.color.white));


                all_order_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                order_day_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                MyOffer_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));

            }
        });


        status_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type_order.equals("status_1")) {

                    status_1.setTextColor(getResources().getColor(R.color.te_unselected));
                    status_2.setTextColor(getResources().getColor(R.color.te_unselected));
                    status_3.setTextColor(getResources().getColor(R.color.te_unselected));


                    status_1.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    status_2.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    status_3.setBackground(getResources().getDrawable(R.drawable.border_fillter));


                } else {
                    status_1.setTextColor(getResources().getColor(R.color.white));
                    status_2.setTextColor(getResources().getColor(R.color.te_unselected));
                    status_3.setTextColor(getResources().getColor(R.color.te_unselected));


                    status_1.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                    status_2.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    status_3.setBackground(getResources().getDrawable(R.drawable.border_fillter));

                }

            }
        });
        status_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type_order.equals("status_2")) {

                    status_1.setTextColor(getResources().getColor(R.color.te_unselected));
                    status_2.setTextColor(getResources().getColor(R.color.te_unselected));
                    status_3.setTextColor(getResources().getColor(R.color.te_unselected));


                    status_1.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    status_2.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    status_3.setBackground(getResources().getDrawable(R.drawable.border_fillter));


                } else {
                    status_1.setTextColor(getResources().getColor(R.color.te_unselected));
                    status_2.setTextColor(getResources().getColor(R.color.white));
                    status_3.setTextColor(getResources().getColor(R.color.te_unselected));


                    status_1.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    status_2.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
                    status_3.setBackground(getResources().getDrawable(R.drawable.border_fillter));

                }

            }
        });
        status_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type_order.equals("status_3")) {

                    status_1.setTextColor(getResources().getColor(R.color.te_unselected));
                    status_2.setTextColor(getResources().getColor(R.color.te_unselected));
                    status_3.setTextColor(getResources().getColor(R.color.te_unselected));


                    status_1.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    status_2.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    status_3.setBackground(getResources().getDrawable(R.drawable.border_fillter));


                } else {
                    status_1.setTextColor(getResources().getColor(R.color.te_unselected));
                    status_2.setTextColor(getResources().getColor(R.color.te_unselected));
                    status_3.setTextColor(getResources().getColor(R.color.white));


                    status_1.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    status_2.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    status_3.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));

                }

            }
        });

    }


}
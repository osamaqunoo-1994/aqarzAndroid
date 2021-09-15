package sa.aqarz.NewAqarz.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.orhanobut.hawk.Hawk;

import sa.aqarz.Activity.OprationNew.RentActivity;
import sa.aqarz.Activity.OprationNew.RentShowActivity;
import sa.aqarz.NewAqarz.MyOrderRequstActivity;
import sa.aqarz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderSecandFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderSecandFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderSecandFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderSecandFragment newInstance(String param1, String param2) {
        OrderSecandFragment fragment = new OrderSecandFragment();
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

    LinearLayout rent_installment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_secand, container, false);
        rent_installment = view.findViewById(R.id.rent_installment);


        rent_installment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Hawk.contains("rent_layout")) {

                    Intent intent = new Intent(getActivity(), RentActivity.class);
                    intent.putExtra("id", "");
                    startActivity(intent);
                } else {
                    Hawk.put("rent_layout", "rent_layout");
                    Intent intent = new Intent(getActivity(), RentShowActivity.class);
                    intent.putExtra("id", "");
                    startActivity(intent);
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
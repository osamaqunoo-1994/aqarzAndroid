package sa.aqarz.NewAqarz.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.OprationNew.RentActivity;
import sa.aqarz.Activity.OprationNew.RentShowActivity;
import sa.aqarz.Modules.DeferredInstallment;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_Rent;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_mu_souq_order_mange_order;
import sa.aqarz.NewAqarz.AqqAqarz.AddRentalInstallmentActivity;
import sa.aqarz.NewAqarz.MyOrderRequstActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

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
    static IResult mResultCallback;
    RecyclerView orders_rec;
    List<DeferredInstallment> MyRequst = new ArrayList<>();
    LinearLayout nodata_vis;
    static RecyclerView_Rent recyclerView_orders_demandsx;
    int page = 1;
    LinearLayout Request_property;
    LinearLayout add_;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_secand, container, false);
        rent_installment = view.findViewById(R.id.rent_installment);
        orders_rec = view.findViewById(R.id.orders_rec);
        nodata_vis = view.findViewById(R.id.nodata_vis);
        Request_property = view.findViewById(R.id.Request_property);
        add_ = view.findViewById(R.id.add_);
        page = 1;
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        orders_rec.setLayoutManager(layoutManager1);


        MyRequst.clear();

        recyclerView_orders_demandsx = new RecyclerView_Rent(getContext(), MyRequst);

        orders_rec.setAdapter(recyclerView_orders_demandsx);


        WebService.loading(getActivity(), true);
        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
        mVolleyService.getDataVolley("my_deferredInstallment", WebService.my_deferredInstallment);
        // Inflate the layout for this fragment

        rent_installment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddRentalInstallmentActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);
//                if (Hawk.contains("rent_layout")) {
//
////                    Intent intent = new Intent(getActivity(), RentActivity.class);
//
//                } else {
//                    Hawk.put("rent_layout", "rent_layout");
//                    Intent intent = new Intent(getActivity(), RentShowActivity.class);
//                    intent.putExtra("id", "");
//                    startActivity(intent);
//                }
            }
        });
        add_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddRentalInstallmentActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);
//                if (Hawk.contains("rent_layout")) {
//
////                    Intent intent = new Intent(getActivity(), RentActivity.class);
//
//                } else {
//                    Hawk.put("rent_layout", "rent_layout");
//                    Intent intent = new Intent(getActivity(), RentShowActivity.class);
//                    intent.putExtra("id", "");
//                    startActivity(intent);
//                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(getActivity(), false);


//{"status":true,"code":200,"message":"User Profile","data"

                try {


                    boolean status = response.getBoolean("status");
                    if (status) {

                        if (requestType.equals("my_deferredInstallment")) {
                            System.out.println("lfkdlfkdlkf");
                            String data = response.getString("data");


                            JSONObject jsonObject = new JSONObject(data);


                            String datax = jsonObject.getString("data");


                            JSONArray jsonArray = new JSONArray(datax);
                            if (page == 1) {
                                MyRequst.clear();

                            }
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();
                                DeferredInstallment ordersModulesm = gson.fromJson(mJson, DeferredInstallment.class);

//                                HomeModules ordersModulesm = gson.fromJson(mJson, HomeModules.class);
                                MyRequst.add(ordersModulesm);


                            }

                            recyclerView_orders_demandsx.Refr();
//                            orders_rec.setAdapter(new RecyclerView_orders_my_requstx(MyOrderRequstActivity.this, MyRequst));

                            if (MyRequst.size() != 0) {
                                nodata_vis.setVisibility(View.GONE);
                            } else {
                                nodata_vis.setVisibility(View.VISIBLE);

                            }

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());
                WebService.loading(getActivity(), false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }


            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(getActivity(), false);

            }


        };


    }

}
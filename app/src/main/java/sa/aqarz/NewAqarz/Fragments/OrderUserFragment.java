package sa.aqarz.NewAqarz.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.RecyclerView_orders_demandsx;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.NewAqarz.MyOrderRequstActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderUserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderUserFragment() {
        // Required empty public constructor
    }

    static List<demandsModules> MyRequst = new ArrayList<>();
    static RecyclerView_orders_demandsx recyclerView_orders_demandsx;

    static RecyclerView allOrder;
    static IResult mResultCallback;
    static int page = 1;
    static Activity activity;
    static LinearLayout nodata_vis;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderUserFragment newInstance(String param1, String param2) {
        OrderUserFragment fragment = new OrderUserFragment();
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
        View view = inflater.inflate(R.layout.fragment_order_user, container, false);
        allOrder = view.findViewById(R.id.allOrder);
        nodata_vis = view.findViewById(R.id.nodata_vis);
        activity = getActivity();
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        allOrder.setLayoutManager(layoutManager1);


        MyRequst.clear();

        recyclerView_orders_demandsx = new RecyclerView_orders_demandsx(activity, MyRequst);

        allOrder.setAdapter(recyclerView_orders_demandsx);
        allOrder.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) { //1 for down

                    page = page + 1;


                    WebService.loading(activity, true);
                    init_volley();
                    VolleyService mVolleyService = new VolleyService(mResultCallback, activity);
                    mVolleyService.getDataVolley("my_request", WebService.my_request + "&page=" + page);

                }
            }
        });

        WebService.loading(activity, true);
        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, activity);
        mVolleyService.getDataVolley("my_request", WebService.my_request);

        return view;
    }

    public static void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
                WebService.loading(activity, false);

//                type_requst = "" + requestType;


//{"status":true,"code":200,"message":"User Profile","data"
//allRequestFund":6165,"RequestFund":18,"myRequestFundOffer":4727


                try {
                    boolean status = response.getBoolean("status");
                    if (status) {

                        if (requestType.equals("my_request")) {
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
                                demandsModules ordersModulesm = gson.fromJson(mJson, demandsModules.class);

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
                WebService.loading(activity, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    if (requestType.equals("my_request")) {
//                        nodata_vis.setVisibility(View.VISIBLE);

                    } else if (requestType.equals("market_demands")) {
//                        nodata_vis.setVisibility(View.VISIBLE);

                    } else {
                        WebService.Make_Toast_color(activity, message, "error");

                    }


                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


            }


            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(activity, false);

            }


        };


    }

}
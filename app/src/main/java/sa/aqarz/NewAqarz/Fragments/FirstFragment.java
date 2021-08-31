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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.AllOrderActivity;
import sa.aqarz.Adapter.RecyclerView_city_side_menu;
import sa.aqarz.Adapter.RecyclerView_ordersx;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.NewAqarz.BottomDialog.BottomSheetDialogFragment_Filtter;
import sa.aqarz.NewAqarz.MainAqarzActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

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

    static IResult mResultCallback;

    LinearLayout all_order_lay;
    LinearLayout order_day_lay;
    LinearLayout MyOffer_lay;
    static String type_requst = "AllOrder";
    static String offer_status = "";


    TextView all_order;
    static TextView all_order_num;
    TextView order_day;
    static TextView order_day_num;
    TextView MyOffer;
    static TextView MyOffer_num;
    EditText search_text;
    TextView status_1;
    TextView status_2;
    TextView status_3;
    static LinearLayout nodata_vis;

    String type_order = "";
    static int page = 1;
    static String url = "";
    static RecyclerView AllResultRec;
    public static boolean isLoading = false;

    LinearLayout all_status_offer;
    static List<OrdersModules> ordersModules = new ArrayList<>();
    static RecyclerView_ordersx recyclerView_ordersx;
    ImageView search_btn;
    ProgressBar progress;
    public static Activity activity;
    ImageView fillter;
    ImageView fill_filtter;
    BottomSheetDialogFragment_Filtter bottomSheetDialogFragment_filtter;

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
        activity = getActivity();

        if (!MainAqarzActivity.type_type_order_main.equals("")) {
            type_requst = MainAqarzActivity.type_type_order_main;
        }
        nodata_vis = view.findViewById(R.id.nodata_vis);
        all_order_lay = view.findViewById(R.id.all_order_lay);
        order_day_lay = view.findViewById(R.id.order_day_lay);
        MyOffer_lay = view.findViewById(R.id.MyOffer_lay);
        progress = view.findViewById(R.id.progress);
        search_text = view.findViewById(R.id.search_text);
        search_btn = view.findViewById(R.id.search_btn);
        fillter = view.findViewById(R.id.fillter);
        fill_filtter = view.findViewById(R.id.fill_filtter);


        all_order = view.findViewById(R.id.all_order);
        all_order_num = view.findViewById(R.id.all_order_num);
        order_day = view.findViewById(R.id.order_day);
        order_day_num = view.findViewById(R.id.order_day_num);
        MyOffer = view.findViewById(R.id.MyOffer);
        MyOffer_num = view.findViewById(R.id.MyOffer_num);

        status_1 = view.findViewById(R.id.status_1);
        status_2 = view.findViewById(R.id.status_2);
        status_3 = view.findViewById(R.id.status_3);
        AllResultRec = view.findViewById(R.id.AllResultRec);

        all_status_offer = view.findViewById(R.id.all_status_offer);


//        if (isVisibleToUser) {
//            System.out.println("*************************************");
//        }
        setiup();
        onclick();
        get_data();


        return view;

    }

    public void setiup() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        AllResultRec.setLayoutManager(layoutManager);

        ordersModules.clear();
        recyclerView_ordersx = new RecyclerView_ordersx(getContext(), ordersModules);
        AllResultRec.setAdapter(recyclerView_ordersx);
        AllResultRec.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) { //1 for down

                    page = page + 1;


                    WebService.loading(activity, true);
                    init_volley();
                    VolleyService mVolleyService = new VolleyService(mResultCallback, activity);
                    mVolleyService.getDataVolley("fund_Request", url + "&page=" + page);

                }
            }
        });
        fillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainAqarzActivity.objectFiltterOrder.setTypelayout("Real");


                bottomSheetDialogFragment_filtter = new BottomSheetDialogFragment_Filtter("");
                bottomSheetDialogFragment_filtter.addItemClickListener(new BottomSheetDialogFragment_Filtter.ItemClickListener() {
                    @Override
                    public void onItemClick(String filter) {
                        bottomSheetDialogFragment_filtter.dismiss();

                        get_data();
                    }
                });
                bottomSheetDialogFragment_filtter.show(getChildFragmentManager(), "");
            }
        });

    }

    public void get_data() {


        String offer_status_text = "";
        String type_requst_text = "";
        String search_text_s = "";
        String area_estate_id_text = "";
        String price_id_text = "";
        String estate_type_id_text = "";
        String city_id_text = "";
        String neighborhood_id_text = "";
        String state_id_text = "";


        if (!search_text.getText().toString().equals("")) {
            search_text_s = "&search=" + search_text.getText();
        }
        if (!MainAqarzActivity.objectFiltterOrder.getId_price().equals("")) {
            price_id_text = "&price_id=" + MainAqarzActivity.objectFiltterOrder.getId_price();
        }

        if (!MainAqarzActivity.objectFiltterOrder.getId_space().equals("")) {
            area_estate_id_text = "&area_estate_id=" + MainAqarzActivity.objectFiltterOrder.getId_space();
        }

        if (!MainAqarzActivity.objectFiltterOrder.getType_filtter().equals("")) {
            estate_type_id_text = "&estate_type_id=" + MainAqarzActivity.objectFiltterOrder.getType_filtter();
        }
        if (!MainAqarzActivity.objectFiltterOrder.getId_nib().equals("")) {
            neighborhood_id_text = "&neighborhood_id=" + MainAqarzActivity.objectFiltterOrder.getId_nib();
        }
        if (MainAqarzActivity.objectFiltterOrder.getId_city() != null) {
            if (!MainAqarzActivity.objectFiltterOrder.getId_city().equals("")) {
                city_id_text = "&city_id=" + MainAqarzActivity.objectFiltterOrder.getId_city();
            }
        }
        if (MainAqarzActivity.objectFiltterOrder.getId_state() != null) {
            if (!MainAqarzActivity.objectFiltterOrder.getId_state().equals("")) {
                state_id_text = "&state_id=" + MainAqarzActivity.objectFiltterOrder.getId_state();
            }
        }


        page = 1;
        if (type_requst.equals("today")) {
            offer_status_text = "";
            type_requst_text = "&today=1";
        } else if (type_requst.equals("Myoffer")) {

            type_requst_text = "&myOwn=1";

            if (offer_status.equals("customer_accepted")) {
                offer_status_text = "&offer_status=customer_accepted";

            } else if (offer_status.equals("sending_code")) {
                offer_status_text = "&offer_status=sending_code";

            } else if (offer_status.equals("new")) {
                offer_status_text = "&offer_status=new";
            }


        } else if (type_requst.equals("AllOrder")) {
            offer_status_text = "";
            type_requst_text = "";

        }
        if (!search_text.getText().toString().equals("")) {
            search_text_s = "&search=" + search_text.getText();
        }
        url = WebService.fund_Request + "?" + type_requst_text + state_id_text + neighborhood_id_text + offer_status_text + search_text_s + price_id_text + area_estate_id_text + estate_type_id_text + city_id_text;//WebService.fund_Request + "?" + "page=" + page + "&today=1" + id_city_ + opration_select + search_te

        WebService.loading(getActivity(), true);

        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
        mVolleyService.getDataVolley("fund_Request", url);
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


        if (type_requst.equals("AllOrder")) {
            all_order.setTextColor(getResources().getColor(R.color.white));
            all_order_num.setTextColor(getResources().getColor(R.color.white));
            order_day.setTextColor(getResources().getColor(R.color.te_unselected));
            order_day_num.setTextColor(getResources().getColor(R.color.te_unselected));
            MyOffer.setTextColor(getResources().getColor(R.color.te_unselected));
            MyOffer_num.setTextColor(getResources().getColor(R.color.te_unselected));


            all_order_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
            order_day_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter));
            MyOffer_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter));


            all_status_offer.setVisibility(View.GONE);
            type_requst = "AllOrder";
            MainAqarzActivity.type_type_order_main = type_requst;
        } else if (type_requst.equals("today")) {
            all_order.setTextColor(getResources().getColor(R.color.te_unselected));
            all_order_num.setTextColor(getResources().getColor(R.color.te_unselected));
            order_day.setTextColor(getResources().getColor(R.color.white));
            order_day_num.setTextColor(getResources().getColor(R.color.white));
            MyOffer.setTextColor(getResources().getColor(R.color.te_unselected));
            MyOffer_num.setTextColor(getResources().getColor(R.color.te_unselected));


            all_order_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter));
            order_day_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));
            MyOffer_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter));


            all_status_offer.setVisibility(View.GONE);
            type_requst = "today";
            MainAqarzActivity.type_type_order_main = type_requst;
        } else if (type_requst.equals("Myoffer")) {
            all_order.setTextColor(getResources().getColor(R.color.te_unselected));
            all_order_num.setTextColor(getResources().getColor(R.color.te_unselected));
            order_day.setTextColor(getResources().getColor(R.color.te_unselected));
            order_day_num.setTextColor(getResources().getColor(R.color.te_unselected));
            MyOffer.setTextColor(getResources().getColor(R.color.white));
            MyOffer_num.setTextColor(getResources().getColor(R.color.white));


            all_order_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter));
            order_day_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter));
            MyOffer_lay.setBackground(getResources().getDrawable(R.drawable.border_fillter_fill));


            all_status_offer.setVisibility(View.VISIBLE);
            type_requst = "Myoffer";
            MainAqarzActivity.type_type_order_main = type_requst;
        }


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


                all_status_offer.setVisibility(View.GONE);
                type_requst = "AllOrder";
                MainAqarzActivity.type_type_order_main = type_requst;

                get_data();
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


                all_status_offer.setVisibility(View.GONE);
                type_requst = "today";
                MainAqarzActivity.type_type_order_main = type_requst;

                get_data();

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


                all_status_offer.setVisibility(View.VISIBLE);
                type_requst = "Myoffer";
                MainAqarzActivity.type_type_order_main = type_requst;

                get_data();

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

                    offer_status = "";

                } else {
                    status_1.setTextColor(getResources().getColor(R.color.white));
                    status_2.setTextColor(getResources().getColor(R.color.te_unselected));
                    status_3.setTextColor(getResources().getColor(R.color.te_unselected));


                    status_1.setBackground(getResources().getDrawable(R.drawable.border_fillter_1));
                    status_2.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    status_3.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    offer_status = "customer_accepted";

                }
                get_data();

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

                    offer_status = "";

                } else {
                    status_1.setTextColor(getResources().getColor(R.color.te_unselected));
                    status_2.setTextColor(getResources().getColor(R.color.white));
                    status_3.setTextColor(getResources().getColor(R.color.te_unselected));


                    status_1.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    status_2.setBackground(getResources().getDrawable(R.drawable.border_fillter_2));
                    status_3.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    offer_status = "sending_code";

                }
                get_data();

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

                    offer_status = "";

                } else {
                    status_1.setTextColor(getResources().getColor(R.color.te_unselected));
                    status_2.setTextColor(getResources().getColor(R.color.te_unselected));
                    status_3.setTextColor(getResources().getColor(R.color.white));


                    status_1.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    status_2.setBackground(getResources().getDrawable(R.drawable.border_fillter));
                    status_3.setBackground(getResources().getDrawable(R.drawable.border_fillter_3));
                    offer_status = "new";

                }
                get_data();

            }
        });
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                get_data();
            }
        });
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


                    if (requestType.equals("fund_Request")) {
                        String allRequestFund = response.getString("allRequestFund");
                        all_order_num.setText("(" + allRequestFund + ")");

                        String RequestFund = response.getString("RequestFund");
                        order_day_num.setText("(" + RequestFund + ")");

                        String myRequestFundOffer = response.getString("myRequestFundOffer");
                        MyOffer_num.setText("(" + myRequestFundOffer + ")");


                        String data = response.getString("data");

                        JSONObject jsonObject_data = new JSONObject(data);

                        String data_inside = jsonObject_data.getString("data");
                        JSONArray jsonArray = new JSONArray(data_inside);

                        if (page == 1) {
                            ordersModules.clear();

                        }

                        for (int i = 0; i < jsonArray.length(); i++) {


                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(jsonArray.getString(i));

                            Gson gson = new Gson();

                            OrdersModules ordersModulesm = gson.fromJson(mJson, OrdersModules.class);
                            ordersModules.add(ordersModulesm);


                        }
                        recyclerView_ordersx.Refr();
                        if (ordersModules.size() != 0) {
                            nodata_vis.setVisibility(View.GONE);
                            isLoading = true;
                        } else {
                            nodata_vis.setVisibility(View.VISIBLE);
                            isLoading = false;

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

    @Override
    public void onResume() {


        if (Hawk.contains("fillterorder")) {
            if (Hawk.get("fillterorder").toString().equals("yes")) {
                fill_filtter.setVisibility(View.VISIBLE);
            } else {
                fill_filtter.setVisibility(View.GONE);

            }

        }


        super.onResume();
    }
}
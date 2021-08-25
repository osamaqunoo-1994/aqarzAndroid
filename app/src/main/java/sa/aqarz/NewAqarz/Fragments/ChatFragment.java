package sa.aqarz.NewAqarz.Fragments;
/**
 * Created by osama on 10/8/2017.
 */


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.RecyclerView_chat_main;
import sa.aqarz.Modules.MsgModules;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class ChatFragment extends Fragment {
    RecyclerView chate;

    List<MsgModules> ordersModules = new ArrayList<>();

    IResult mResultCallback;
    LinearLayout nodata;

    TextView message;
    TextView notfication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        chate = v.findViewById(R.id.chate);
        nodata = v.findViewById(R.id.nodata);
        message = v.findViewById(R.id.message);
        notfication = v.findViewById(R.id.notfication);


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        chate.setLayoutManager(layoutManager1);


        init(v);
        return v;
    }


    public void init(View v) {
        WebService.loading(getActivity(), true);

        init_volley();
        VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setBackground(getResources().getDrawable(R.drawable.button_login));

                message.setTextColor(getResources().getColor(R.color.white));


                notfication.setBackground(getResources().getDrawable(R.drawable.mash));

                notfication.setTextColor(getResources().getColor(R.color.black));


                mVolleyService.getDataVolley("my_msg", WebService.my_msg);
            }
        });
        notfication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notfication.setBackground(getResources().getDrawable(R.drawable.button_login));
                notfication.setTextColor(getResources().getColor(R.color.white));
                message.setBackground(getResources().getDrawable(R.drawable.mash));
                message.setTextColor(getResources().getColor(R.color.black));
            }
        });


        mVolleyService.getDataVolley("my_msg", WebService.my_msg);

    }


    public static ChatFragment newInstance(String text) {

        ChatFragment f = new ChatFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }


    @Override
    public void onResume() {


        super.onResume();
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

                        if (requestType.equals("my_msg")) {
                            System.out.println("lfkdlfkdlkf");
                            String data = response.getString("data");


//                            JSONObject jsonObject = new JSONObject(data);


//                            String datax = jsonObject.getString("data");


                            JSONArray jsonArray = new JSONArray(data);
                            chate.setAdapter(null);
                            ordersModules.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();
                                MsgModules msgModules = gson.fromJson(mJson, MsgModules.class);

//                                HomeModules ordersModulesm = gson.fromJson(mJson, HomeModules.class);
                                ordersModules.add(msgModules);


                            }

                            chate.setAdapter(new RecyclerView_chat_main(getContext(), ordersModules));


                            if (ordersModules.size() != 0) {
                                nodata.setVisibility(View.GONE);
                            } else {
                                nodata.setVisibility(View.VISIBLE);

                            }


                        }

                    } else {

                        String message = response.getString("message");


                        WebService.Make_Toast_color(getActivity(), message, "error");

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


                    WebService.Make_Toast_color(getActivity(), message, "error");


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

    public void gatemm_face() {


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

                        if (requestType.equals("my_msg")) {
                            System.out.println("lfkdlfkdlkf");
                            String data = response.getString("data");


//                            JSONObject jsonObject = new JSONObject(data);


//                            String datax = jsonObject.getString("data");


                            JSONArray jsonArray = new JSONArray(data);
                            chate.setAdapter(null);
                            ordersModules.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JsonParser parser = new JsonParser();
                                JsonElement mJson = parser.parse(jsonArray.getString(i));

                                Gson gson = new Gson();
                                MsgModules msgModules = gson.fromJson(mJson, MsgModules.class);

//                                HomeModules ordersModulesm = gson.fromJson(mJson, HomeModules.class);
                                ordersModules.add(msgModules);


                            }

                            chate.setAdapter(new RecyclerView_chat_main(getContext(), ordersModules));


                            if (ordersModules.size() != 0) {
                                nodata.setVisibility(View.GONE);
                            } else {
                                nodata.setVisibility(View.VISIBLE);

                            }


                        }

                    } else {

                        String message = response.getString("message");


                        WebService.Make_Toast_color(getActivity(), message, "error");

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


                    WebService.Make_Toast_color(getActivity(), message, "error");


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
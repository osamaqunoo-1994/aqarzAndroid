package aqarz.revival.sa.aqarz.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import aqarz.revival.sa.aqarz.Settings.WebService;

public class VolleyService {

    IResult mResultCallback = null;
    Context mContext;

    public VolleyService(IResult resultCallback, Context context) {
        mResultCallback = resultCallback;
        mContext = context;
    }


    public void postDataVolley(final String requestType, String url, JSONObject sendObj) {
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObj = new JsonObjectRequest(url, sendObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (mResultCallback != null)
                        mResultCallback.notifySuccess(requestType, response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (mResultCallback != null)
                        mResultCallback.notifyError(requestType, error);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {


                    return WebService.setHeaderVolley();
                }

            };


            ;

            queue.add(jsonObj);

        } catch (Exception e) {

        }
    }

    public void getDataVolley(final String requestType, String url) {
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (mResultCallback != null)
                        mResultCallback.notifySuccess(requestType, response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (mResultCallback != null)
                        mResultCallback.notifyError(requestType, error);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {


                    return WebService.setHeaderVolley();
                }

            };

            queue.add(jsonObj);

        } catch (Exception e) {

        }
    }

//    public void init_volley() {
//
//
//        IResult mResultCallback = new IResult() {
//            @Override
//            public void notifySuccess(String requestType, JSONObject response) {
//                Log.d("TAG", "Volley requester " + requestType);
//                Log.d("TAG", "Volley JSON post" + response);
//            }
//
//            @Override
//            public void notifyError(String requestType, VolleyError error) {
//                Log.d("TAG", "Volley requester " + requestType);
//                Log.d("TAG", "Volley JSON post" + "That didn't work!");
//            }
//        };
//
//        VolleyService mVolleyService = new VolleyService(mResultCallback,this);
//
//
//
//        mVolleyService.getDataVolley("GETCALL","http://192.168.1.150/datatest/get/data");
//        JSONObject sendObj = null;
//
//        try {
//            sendObj = new JSONObject("{'Test':'Test'}");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        mVolleyService.postDataVolley("POSTCALL", "http://192.168.1.150/datatest/post/data", sendObj);
//
//
//
//    }

}
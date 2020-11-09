package sa.aqarz.api;

import android.app.Activity;
import android.content.Context;
import android.util.JsonToken;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Settings.WebService;
import cz.msebera.android.httpclient.Header;

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

            System.out.println("urlurl " + url);

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

    public void postDataasync_with_file(final String requestType, String url, RequestParams requestParams) {
        try {

            AsyncHttpClient client = new AsyncHttpClient();
            final int DEFAULT_TIMEOUT = 20 * 1000;
            client.setTimeout(DEFAULT_TIMEOUT);
            WebService.Header_Async(client, true);
            client.post(url, requestParams, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                    System.out.println("responseBody" + responseBody.toString());


                    if (mResultCallback != null) {
                        mResultCallback.notifySuccess(requestType, responseBody);
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    System.out.println("responseBody" + responseString.toString());

                    if (mResultCallback != null)
                        mResultCallback.notify_Async_Error(requestType, responseString);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {

                        System.out.println("responseBody" + errorResponse.toString());


                        mResultCallback.notify_Async_Error(requestType, errorResponse.getString("message").toString());

                    } catch (Exception e) {

                    }

                }

                @Override
                public void onUserException(Throwable error) {
                    System.out.println("responseBody" + error.toString());

                }

                @Override
                public void onProgress(long bytesWritten, long totalSize) {


                }
            });
        } catch (Exception e) {

        }
    }

    public void postDataVolley_without_token(final String requestType, String url, JSONObject sendObj) {
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


                    return WebService.setHeaderVolley_without_token();
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


            System.out.println("urlurl " + url);


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

    public void getDataVolleyWithoutToken(final String requestType, String url) {
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


                    return WebService.setHeaderVolley_without_token();
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

package sa.aqarz.Fragment.mapsHome;


import android.app.Activity;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import sa.aqarz.Modules.AllCityList;
import sa.aqarz.Modules.AllEstate;
import sa.aqarz.Modules.AllRegionList;
import sa.aqarz.Modules.RegionModules;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class MapsRepository {

    IResult mResultCallback;

    VolleyService mVolleyService;

    Activity activity;

    public MapsRepository(Activity activity1) {
        activity = activity1;
        WebService.loading(activity, true);


    }


    public void getOpration() {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^");

//        final MutableLiveData<VideoModules> data = new MutableLiveData<>();
//        MapsViewModel.mutableLiveData.setValue(null);
//        MapsViewModel.mutableLiveData.setValue(null);


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + response.toString());


//                WebService.loading(activity, false);
                try {


                    JsonParser parser = new JsonParser();
                    JsonElement mJson = parser.parse(response.toString());

                    Gson gson = new Gson();

                    AllRegionList All = gson.fromJson(mJson, AllRegionList.class);

                    MapsViewModel.mutableLiveData.setValue(All.getData());
//                    WebService.loading(activity, false);

                    String data = response.getString("data");
                    Hawk.put("FF", data);

                    WebService.loading(activity, false);

                } catch (Exception e) {

                }

            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());
                WebService.loading(activity, false);

                try {
                    MapsViewModel.mutableLiveData.setValue(null);
                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(activity, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }
            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(activity, false);
                try {
                    MapsViewModel.mutableLiveData.setValue(null);


                } catch (Exception e) {

                }

            }
        };
        mVolleyService = new VolleyService(mResultCallback, activity);
        mVolleyService.getDataVolley("regions", WebService.regions);//&request_type=pay

    }

    public void getCity(String state_id) {

        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + response.toString());
                //                WebService.loading(activity, false);
                try {
                    WebService.loading(activity, false);


                    JsonParser parser = new JsonParser();
                    JsonElement mJson = parser.parse(response.toString());

                    Gson gson = new Gson();

                    AllCityList All = gson.fromJson(mJson, AllCityList.class);

//                    MapsViewModel.mutableLiveData_city.setValue(All.getData());


                    MapsFragmentNew.set_locationCity(All.getData());

//                    WebService.loading(activity, false);
//
//                    String data = response.getString("data");
//                    Hawk.put("FF", data);

                    System.out.println("oioioioioi");

                    WebService.loading(activity, false);

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
                    MapsViewModel.mutableLiveData_city.setValue(null);
                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(activity, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }
            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(activity, false);
                try {
                    MapsViewModel.mutableLiveData_city.setValue(null);


                } catch (Exception e) {

                }

            }
        };
        mVolleyService = new VolleyService(mResultCallback, activity);
        mVolleyService.getDataVolley("cities", WebService.cities + "?state_id=" + state_id);//&request_type=pay

    }

    public void getEstatMaps(final String requestType, String url) {

        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + response.toString());
                //                WebService.loading(activity, false);
                try {
                    WebService.loading(activity, false);


                    JsonParser parser = new JsonParser();
                    JsonElement mJson = parser.parse(response.toString());

                    Gson gson = new Gson();

                    AllEstate All = gson.fromJson(mJson, AllEstate.class);

//                    MapsViewModel.mutableLiveData_city.setValue(All.getData());


                    MapsFragmentNew.set_locationEstate(All.getData());

//                    WebService.loading(activity, false);
//
//                    String data = response.getString("data");
//                    Hawk.put("FF", data);

                    System.out.println("oioioioioi");

                    WebService.loading(activity, false);

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
                    MapsViewModel.mutableLiveData_city.setValue(null);
                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(activity, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }
            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(activity, false);
                try {
                    MapsViewModel.mutableLiveData_city.setValue(null);


                } catch (Exception e) {

                }

            }
        };
        mVolleyService = new VolleyService(mResultCallback, activity);
        mVolleyService.getDataVolley(requestType, url);//&request_type=pay

    }


}

package sa.aqarz.FCM;

/**
 * Created by wisleem on 8/11/16.
 */

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

//import mtc.ps.openheart.settings.OHAPP;
//import mtc.ps.openheart.settings.OHSharedPreferences;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public  void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);






//        final HashMap<String, String> Update_Device_Token_Params = new HashMap<>();
//        Update_Device_Token_Params.put(K.LOGIN_USERNAME, K.APP_USERNAME);
//        Update_Device_Token_Params.put(K.LOGIN_PASSWORD, K.APP_PASSWORD);
//
//
//        Update_Device_Token_Params.put("DvcID", WebService.getUserSetting().getDeviceInfo().getId() + "");
//        Update_Device_Token_Params.put("Token", refreshedToken);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                new Update_Device_Token().execute(Update_Device_Token_Params);
//            }
//        });
////        OHAPP.getInstance().getSharedPreferences().writeString(OHSharedPreferences.FCM_TOKEN, refreshedToken);
////            WebService.getInstance(this).NewFCMKey(refreshedToken);
    }

//    class Update_Device_Token extends AsyncTask<HashMap<String, String>, Void, HashMap<String, Object>> {
//
////        ProgressDialog progressDialog;
//
//        @Override
//        protected void onPreExecute() {
////            progressDialog = new ProgressDialog(getActivity());
////            progressDialog.setMessage(getActivity().getString(R.string.sending_rate));
////            progressDialog.show();
//        }
//
//        @Override
//        protected HashMap<String, Object> doInBackground(HashMap<String, String>... params) {
//            return WebService.MakePostRequest(MyFirebaseInstanceIDService.this, Config.Update_Device_Token, params[0]);
//        }
//
//        @Override
//        protected void onPostExecute(HashMap<String, Object> result) {
//
//            if (this != null) {
//                if (result != null) {
//                    if (result.containsKey(WebService.RESPONSE_CODE))
//                        switch ((int) result.get(WebService.RESPONSE_CODE)) {
//                            case 0:
////                                Toast.makeText(thisContext, thisContext.getString(R.string.network_error), Toast.LENGTH_SHORT).show();
//                            case 200:
//                            case 201:
//                                if (result.containsKey(WebService.STATUS_DATA)) {
//                                    if ((boolean) result.get(WebService.STATUS_DATA)) {
//
//                                        String dataAPI = result.get("result").toString();
//
//                                        try {
//                                            JSONObject jsonObject = new JSONObject(dataAPI);
//                                            switch (jsonObject.getInt(K.R_E_ID)) {
//                                                case 0:
//                                                    JSONArray dataArray = jsonObject.getJSONArray(K.R_DATA);
//                                                    final JSONObject data = dataArray.getJSONObject(0);
//                                                    Log.e("data", data.toString());
//
//                                                    break;
//                                                default:
//                                            }
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                }
//                                break;
//
//                            default:
////                                Toast.makeText(thisActivity, getString(R.string.network_error), Toast.LENGTH_SHORT).show();
//                                break;
//                        }
//                } else {
//                    Log.e("problem with sending", "Connection Problem");
////                    Toast.makeText(thisActivity, getString(R.string.network_error), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }


}
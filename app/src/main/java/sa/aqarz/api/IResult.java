package sa.aqarz.api;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface IResult {
    public void notifySuccess(String requestType, JSONObject response);
    public void notifyError(String requestType, VolleyError error);
    public void notify_Async_Error(String requestType, String error);
}

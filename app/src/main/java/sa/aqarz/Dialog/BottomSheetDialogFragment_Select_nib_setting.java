package sa.aqarz.Dialog;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.RecyclerView_all_city_from_file;
import sa.aqarz.Adapter.RecyclerView_all_nib_from_file;
import sa.aqarz.Modules.AllCity_WithNib;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;


public class BottomSheetDialogFragment_Select_nib_setting extends BottomSheetDialogFragment {
    IResult mResultCallback;

    String id_city = "";


    RecyclerView list_city;

    List<AllCity_WithNib.neighborhoods> neighborhoods_ = new ArrayList<>();
//    List<AllCity_WithNib.data> regionModules = new ArrayList<>();

    ImageView search_btn;

    ProgressBar progress;
    EditText search;
    private ItemClickListener mItemClickListener;
    LinearLayout allSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheets_select_city, container, false);
        list_city = v.findViewById(R.id.list_city);
        progress = v.findViewById(R.id.progress);
        search = v.findViewById(R.id.search);
        search_btn = v.findViewById(R.id.search_btn);
        allSearch = v.findViewById(R.id.allSearch);
        allSearch.setVisibility(View.GONE);


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list_city.setLayoutManager(layoutManager1);


//        JSONArray jsonArray = new JSONArray(data);
        progress.setVisibility(View.GONE);
//        regionModules.clear();


//        JsonParser parser = new JsonParser();
//        JsonElement mJson = parser.parse(jsonArray.getString(i));
//
//        Gson gson = new Gson();
//
//        RegionModules Store_M = gson.fromJson(mJson, RegionModules.class);


//        regionModules.addAll(Settings.get_city_(getActivity()));


        RecyclerView_all_nib_from_file recyclerView_city_bootom_sheets = new RecyclerView_all_nib_from_file(getContext(), neighborhoods_);
        recyclerView_city_bootom_sheets.addItemClickListener(new RecyclerView_all_nib_from_file.ItemClickListener() {


            @Override
            public void onItemClick(int position) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(neighborhoods_.get(position).getCityId(), neighborhoods_.get(position).getName());
                }
            }
        });

        list_city.setAdapter(recyclerView_city_bootom_sheets);


//        init_volley();
//
//        VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());
//
//        mVolleyService.getDataVolley("regions", WebService.regions );

        return v;
    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_Select_nib_setting(List<AllCity_WithNib.neighborhoods> neighborhoods) {
        neighborhoods_ = neighborhoods;
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
                        String data = response.getString("data");
//                        JSONObject jsonObjectdata = new JSONObject(data);

//                        String datax = jsonObjectdata.getString("data");


                    } else {

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());

                try {
                    progress.setVisibility(View.GONE);

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

            }
        };


    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int id_city, String city_naem);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.bottom_sheets_details_aqares, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Window window = getDialog().getWindow();
            window.findViewById(com.google.android.material.R.id.container).setFitsSystemWindows(false);
            // dark navigation bar icons
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }
}

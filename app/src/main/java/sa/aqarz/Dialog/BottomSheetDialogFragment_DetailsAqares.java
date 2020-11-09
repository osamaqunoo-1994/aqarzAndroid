package sa.aqarz.Dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.DetailsActivity;
import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Adapter.RecyclerView_city_bootom_sheets;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.HomeModules;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class BottomSheetDialogFragment_DetailsAqares extends BottomSheetDialogFragment {

    HomeModules_aqares Homemodules_object;
    ImageView image;
    ImageView image_icon;
    TextView price;
    TextView type;
    TextView dublex;
    TextView opration;
    TextView address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_details_aqarz, container, false);


        address = v.findViewById(R.id.address);
        image = v.findViewById(R.id.image);
        image_icon = v.findViewById(R.id.image_icon);
        price = v.findViewById(R.id.price);
        dublex = v.findViewById(R.id.dublex);
        opration = v.findViewById(R.id.opration);
        type = v.findViewById(R.id.type);
        Picasso.get().load(Homemodules_object.getFirst_image() + "").into(image);
        Picasso.get().load(Homemodules_object.getEstate_type().getIcon() + "").into(image_icon);
        price.setText(Homemodules_object.getTotalPrice());
        type.setText(Homemodules_object.getEstate_type_name());
        opration.setText(Homemodules_object.getOperationTypeName());

        if (Homemodules_object.getAddress() == null) {
            if (Homemodules_object.getCity_name() != null) {
                address.setText(Homemodules_object.getCity_name() + " - " + Homemodules_object.getNeighborhood_name());

            }

        } else {
            address.setText(Homemodules_object.getAddress());

        }


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailsActivity_aqarz.class);
                intent.putExtra("id_aqarz", Homemodules_object.getId() + "");
                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
//                finish();
            }
        });
        return v;
    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_DetailsAqares(HomeModules_aqares Homemodules) {
        Homemodules_object = Homemodules;
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.bottom_sheets_details_aqares, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }


    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int id_city, String city_naem);
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

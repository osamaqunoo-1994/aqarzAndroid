package aqarz.revival.sa.aqarz.Dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import aqarz.revival.sa.aqarz.Activity.DetailsActivity;
import aqarz.revival.sa.aqarz.Activity.MainActivity;
import aqarz.revival.sa.aqarz.Activity.SplashScreenActivity;
import aqarz.revival.sa.aqarz.Modules.HomeModules;
import aqarz.revival.sa.aqarz.R;


public class BottomSheetDialogFragment_DetailsAqares_orders extends BottomSheetDialogFragment {

    HomeModules Homemodules_object;

ImageView image_icon;
    TextView price;
    TextView type;
    TextView dublex;
    TextView opration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_details_aqarz_order, container, false);
        price = v.findViewById(R.id.price);
        dublex = v.findViewById(R.id.dublex);
        opration = v.findViewById(R.id.opration);
        image_icon = v.findViewById(R.id.image_icon);
        type = v.findViewById(R.id.type);
        Picasso.get().load(Homemodules_object.getEstate_type().getIcon() + "").into(image_icon);

        price.setText(Homemodules_object.getPrice_from() + " - " + Homemodules_object.getPrice_to());
        type.setText(Homemodules_object.getEstate_type_name());
        opration.setText(Homemodules_object.getOperation_type_name());


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
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

    public BottomSheetDialogFragment_DetailsAqares_orders(HomeModules Homemodules) {
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

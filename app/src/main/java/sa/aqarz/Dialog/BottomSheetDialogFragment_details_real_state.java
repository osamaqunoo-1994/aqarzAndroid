package sa.aqarz.Dialog;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.R;


public class BottomSheetDialogFragment_details_real_state extends BottomSheetDialogFragment {


    ImageView close;
    TextView p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11;
    OrdersModules ordersModuless;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheets_details_real, container, false);

        close = v.findViewById(R.id.close);
        p1 = v.findViewById(R.id.p1);
        p2 = v.findViewById(R.id.p2);
        p3 = v.findViewById(R.id.p3);
        p4 = v.findViewById(R.id.p4);
        p5 = v.findViewById(R.id.p5);
        p6 = v.findViewById(R.id.p6);
        p7 = v.findViewById(R.id.p7);
        p8 = v.findViewById(R.id.p8);

        p9 = v.findViewById(R.id.p9);
        p10 = v.findViewById(R.id.p10);
        p11 = v.findViewById(R.id.p11);

        try {
            p1.setText(ordersModuless.getId() + "");
            p2.setText(ordersModuless.getRoomsNumberId() + "");
            p3.setText(ordersModuless.getEstateTypeName() + "");
            p4.setText(ordersModuless.getStatus() + "");
            p5.setText(ordersModuless.getAreaEstateRange() + "");
            p6.setText(ordersModuless.getId() + "");
            p7.setText(ordersModuless.getStreetViewRange() + "");
            p8.setText(ordersModuless.getEstatePriceRange() + "");
            p9.setText(ordersModuless.getCityName()+"--"+ordersModuless.getNeighborhoodId() + "");
            p10.setText(ordersModuless.getCityName() + "");
            p11.setText(ordersModuless.getNeighborhoodId() + "");

        } catch (Exception e) {

        }


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return v;
    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_details_real_state(OrdersModules ordersModules) {
        ordersModuless = ordersModules;

    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.bottom_sheets_qr, null);
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

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
    TextView p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, uuid;
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
        uuid = v.findViewById(R.id.uuid);
        p8 = v.findViewById(R.id.p8);

        p9 = v.findViewById(R.id.p9);
        p10 = v.findViewById(R.id.p10);
        p11 = v.findViewById(R.id.p11);

        try {

            if (ordersModuless.getId() != null && !ordersModuless.getId().toString().equals("null")) {
                p1.setText(ordersModuless.getId() + "");

            }
            if (ordersModuless.getRoomsNumberId() != null && !ordersModuless.getRoomsNumberId().toString().equals("null")) {
                p2.setText(ordersModuless.getRoomsNumberId() + "");

            }
            if (ordersModuless.getEstateTypeName() != null && !ordersModuless.getEstateTypeName().equals("null")) {
                p3.setText(ordersModuless.getEstateTypeName() + "");

            }
            if (ordersModuless.getStatus() != null && !ordersModuless.getStatus().equals("null")) {
                p4.setText(ordersModuless.getStatus() + "");

            }
            if (ordersModuless.getAreaEstateRange() != null && !ordersModuless.getAreaEstateRange().toString().equals("null")) {
                p5.setText(ordersModuless.getAreaEstateRange() + "");

            }
            if (ordersModuless.getId() != null && !ordersModuless.getId().toString().equals("null")) {
                p6.setText(ordersModuless.getId() + "");

            }
            if (ordersModuless.getStreetViewRange() != null || !ordersModuless.getStreetViewRange().equals("null")) {
                p7.setText(ordersModuless.getStreetViewRange() + "");

            }
            if (ordersModuless.getEstatePriceRange() != null || !ordersModuless.getEstatePriceRange().equals("null")) {
                p8.setText(ordersModuless.getEstatePriceRange() + "");

            }
            if (ordersModuless.getCityName() != null || !ordersModuless.getCityName().equals("null")) {
                p9.setText(ordersModuless.getCityName() + "," + ordersModuless.getNeighborhoodId() + "");

            }
            if (ordersModuless.getCityName() != null || !ordersModuless.getCityName().equals("null")) {
                p10.setText(ordersModuless.getCityName() + "");

            }
            if (ordersModuless.getNeighborhoodId() != null || !ordersModuless.getNeighborhoodId().toString().equals("null")) {
                p11.setText(ordersModuless.getNeighborhoodId() + "");

            }
            if (ordersModuless.getUuid() != null || !ordersModuless.getUuid().equals("null")) {
                uuid.setText(ordersModuless.getUuid() + "");

            }

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

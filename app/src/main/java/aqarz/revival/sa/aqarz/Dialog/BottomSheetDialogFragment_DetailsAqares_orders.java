package aqarz.revival.sa.aqarz.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import aqarz.revival.sa.aqarz.Activity.DetailsActivity;
import aqarz.revival.sa.aqarz.Activity.MainActivity;
import aqarz.revival.sa.aqarz.Activity.SplashScreenActivity;
import aqarz.revival.sa.aqarz.Modules.HomeModules;
import aqarz.revival.sa.aqarz.R;


public class BottomSheetDialogFragment_DetailsAqares_orders extends BottomSheetDialogFragment {

    HomeModules Homemodules_object;


    TextView price;
    TextView address;
    TextView view_type;
    TextView space;
    TextView name_estate;

    TextView read_more;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheets_details_aqares_order, container, false);

        read_more = view.findViewById(R.id.read_more);
        price = view.findViewById(R.id.price);
        name_estate = view.findViewById(R.id.opration);
        address = view.findViewById(R.id.address);
        space = view.findViewById(R.id.space);
        view_type = view.findViewById(R.id.type);

        view_type.setText(Homemodules_object.getEstate_type_name());
        space.setText(Homemodules_object.getArea_from() + " - " + Homemodules_object.getArea_to());

        price.setText(Homemodules_object.getPrice_from() + " - " + Homemodules_object.getPrice_to());
        name_estate.setText(Homemodules_object.getRequest_type());

//        price.setText(Homemodules_object.getEstatePriceRange());
//        view_type.setText(Homemodules_object.getDirEstate());
//        space.setText(alldata.get(position).getStreetViewRange());
//        name_estate.setText(alldata.get(position).getEstateTypeName());
//        address.setText(alldata.get(position).getCityName() + " , " + alldata.get(position).getNeighborhoodName());


        read_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
              intent.putExtra("id_aqarz",Homemodules_object.getId()+"" );
                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
//                finish();
            }
        });
        return view;
    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_DetailsAqares_orders(HomeModules Homemodules) {
        Homemodules_object = Homemodules;
    }


    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int id_city, String city_naem);
    }
}

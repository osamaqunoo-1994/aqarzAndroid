package aqarz.revival.sa.aqarz.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import aqarz.revival.sa.aqarz.Modules.HomeModules;
import aqarz.revival.sa.aqarz.R;


public class BottomSheetDialogFragment_DetailsAqares_orders extends BottomSheetDialogFragment {

    HomeModules Homemodules_object;


    TextView price;
    TextView address;
    TextView view_type;
    TextView space;
    TextView name_estate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheets_details_aqares_order, container, false);


        price = view.findViewById(R.id.price);
        name_estate = view.findViewById(R.id.name_estate);
        address = view.findViewById(R.id.address);
        space = view.findViewById(R.id.space);
        view_type = view.findViewById(R.id.view_type);


//        price.setText(Homemodules_object.getEstatePriceRange());
//        view_type.setText(Homemodules_object.getDirEstate());
//        space.setText(alldata.get(position).getStreetViewRange());
//        name_estate.setText(alldata.get(position).getEstateTypeName());
//        address.setText(alldata.get(position).getCityName() + " , " + alldata.get(position).getNeighborhoodName());





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

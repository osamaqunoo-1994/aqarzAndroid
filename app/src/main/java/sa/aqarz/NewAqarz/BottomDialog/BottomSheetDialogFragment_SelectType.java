package sa.aqarz.NewAqarz.BottomDialog;

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
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Adapter.RecyclerView_area_range;
import sa.aqarz.Adapter.RecyclerView_city_side_menu;
import sa.aqarz.Adapter.RecyclerView_price_range;
import sa.aqarz.Modules.Allprice_range;
import sa.aqarz.Modules.CityModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.Modules.select_typeModules;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_All_type_fillter_order;
import sa.aqarz.NewAqarz.MainAqarzActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class BottomSheetDialogFragment_SelectType extends BottomSheetDialogFragment {
    IResult mResultCallback;


    private ItemClickListener mItemClickListener;

    TextView rent;
    TextView Purchase;
    TextView investment;
    TextView canclee;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheets_select_type, container, false);
//        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) v.findViewById(R.id.rangeSeekbar5);

        rent=v.findViewById(R.id.rent);
        Purchase=v.findViewById(R.id.Purchase);
        investment=v.findViewById(R.id.investment);
        canclee=v.findViewById(R.id.canclee);


        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener!=null){
                    mItemClickListener.onItemClick("rent");
                }
            }
        });

        Purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener!=null){
                    mItemClickListener.onItemClick("Purchase");
                }
            }
        });

        investment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener!=null){
                    mItemClickListener.onItemClick("investment");
                }
            }
        });

        canclee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener!=null){
                    mItemClickListener.onItemClick("canclee");
                }
            }
        });


//        oprationModules_list.add(new select_typeModules(1, getContext().getResources().getString(R.string.All)));
//        oprationModules_list.add(new select_typeModules(2, getContext().getResources().getString(R.string.Pay)));
//        oprationModules_list.add(new select_typeModules(3, getContext().getResources().getString(R.string.Rent)));
////        oprationModules_list.add(new select_typeModules(1, getContext().getResources().getString(R.string.Investment)));
//
//        RecyclerView_bottomSheet_type recyclerView_bottomSheet_type = new RecyclerView_bottomSheet_type(getContext(), oprationModules_list);
//        recyclerView_bottomSheet_type.addItemClickListener(new RecyclerView_bottomSheet_type.ItemClickListener() {
//            @Override
//            public void onItemClick(List<select_typeModules> select_typeModules) {
//                type = "";
//
//                for (int i = 0; i < select_typeModules.size(); i++) {
//
//                    if (i == 0) {
//
//                        if (select_typeModules.get(0).getSelected()) {
//                            type = "null";
//                            break;
//                        }
//
//                    } else {
//
//                        if (select_typeModules.get(1).getSelected()) {
//                            type = "is_pay";
//
//                        }
//                        if (select_typeModules.get(2).getSelected()) {
//                            type = "is_rent";
//
//                        }
//
//
////                        if (select_typeModules.get(i).getSelected()) {
////                            if (type.equals("")) {
////                                type = select_typeModules.get(i).getId() + "";
////                            } else {
////                                type = type + "," + select_typeModules.get(i).getId() + "";
////                            }
////
////
////                        }
//
//
//                    }
//
//
//                }
//
//
////                type = select_typeModules;
//
//
//            }
//        });
//        selsct_type_all.setAdapter(recyclerView_bottomSheet_type);


        return v;
    }


//    categories_bottomSheetDialogFragment = new Categories_BottomSheetDialogFragment("");
//                categories_bottomSheetDialogFragment.show(getSupportFragmentManager(), "");

    public BottomSheetDialogFragment_SelectType(String id_) {
//        id_city = id_;
    }


    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(String filter);
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

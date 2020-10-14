package aqarz.revival.sa.aqarz.Settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import aqarz.revival.sa.aqarz.Activity.DetailsActivity;
import aqarz.revival.sa.aqarz.Activity.DetailsActivity_aqarz;
import aqarz.revival.sa.aqarz.Modules.HomeModules;
import aqarz.revival.sa.aqarz.R;

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;
    HomeModules homeModules;

    public CustomInfoWindowGoogleMap(Context ctx, HomeModules homeModules) {
        context = ctx;
        this.homeModules = homeModules;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        // Getting view from the layout file
        View view = ((Activity) context).getLayoutInflater()
                .inflate(R.layout.card_marker_map_click, null);
        TextView opration = view.findViewById(R.id.opration);
        TextView location = view.findViewById(R.id.location);
        TextView araea = view.findViewById(R.id.araea);
        TextView price = view.findViewById(R.id.price);
        TextView more = view.findViewById(R.id.more);


        price.setText(homeModules.getPrice_from() + " - " + homeModules.getPrice_to() + " " + context.getResources().getString(R.string.SAR));
        araea.setText(homeModules.getArea_from() + " - " + homeModules.getArea_to() + " M ");
        opration.setText(homeModules.getEstate_type_name());
        location.setText("---");


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                                intent.putExtra("id", homeModules.getId()+"");
                context.startActivity(intent);
            }
        });
        return view;

    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity) context).getLayoutInflater()
                .inflate(R.layout.card_marker_map_click, null);

//        TextView name_tv = view.findViewById(R.id.name);
//        TextView details_tv = view.findViewById(R.id.details);
//        ImageView img = view.findViewById(R.id.pic);
//
//        TextView hotel_tv = view.findViewById(R.id.hotels);
//        TextView food_tv = view.findViewById(R.id.food);
//        TextView transport_tv = view.findViewById(R.id.transport);
//
//        name_tv.setText(marker.getTitle());
//        details_tv.setText(marker.getSnippet());
//
//        InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();
//
//        int imageId = context.getResources().getIdentifier(infoWindowData.getImage().toLowerCase(),
//                "drawable", context.getPackageName());
//        img.setImageResource(imageId);
//
//        hotel_tv.setText(infoWindowData.getHotel());
//        food_tv.setText(infoWindowData.getFood());
//        transport_tv.setText(infoWindowData.getTransport());

        return null;
    }
}


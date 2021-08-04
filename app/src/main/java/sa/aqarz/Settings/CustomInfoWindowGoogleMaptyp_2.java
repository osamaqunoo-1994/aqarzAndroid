package sa.aqarz.Settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.R;

public class CustomInfoWindowGoogleMaptyp_2 implements GoogleMap.InfoWindowAdapter {

    private final Context context;
    HomeModules_aqares homeModules_aqares;

    public CustomInfoWindowGoogleMaptyp_2(Context ctx, HomeModules_aqares homeModules_aqares) {
        context = ctx;
        this.homeModules_aqares = homeModules_aqares;
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
        ImageView image = view.findViewById(R.id.image);


        price.setText(homeModules_aqares.getTotalPrice() + " " + context.getResources().getString(R.string.SAR));
        araea.setText(homeModules_aqares.getTotalArea() + " M ");
        opration.setText(homeModules_aqares.getEstate_type_name());
        location.setText("---");

        try {

            System.out.println("$%$%$%" + homeModules_aqares.getFirst_image());
            Glide.with(context).load(homeModules_aqares.getFirst_image() + "").into(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity_aqarz.class);
                intent.putExtra("id", homeModules_aqares.getId() + "");
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


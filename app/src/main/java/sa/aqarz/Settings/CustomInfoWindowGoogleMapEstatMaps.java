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
import com.hedgehog.ratingbar.RatingBar;
import com.squareup.picasso.Picasso;
import com.willy.ratingbar.ScaleRatingBar;

import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.R;

public class CustomInfoWindowGoogleMapEstatMaps implements GoogleMap.InfoWindowAdapter {

    private final Context context;
    HomeModules_aqares homeModules_aqares;

    public CustomInfoWindowGoogleMapEstatMaps(Context ctx, HomeModules_aqares homeModules_aqares) {
        context = ctx;
        this.homeModules_aqares = homeModules_aqares;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        // Getting view from the layout file
        View view = ((Activity) context).getLayoutInflater()
                .inflate(R.layout.item_map_click_marker, null);

        TextView opration = view.findViewById(R.id.opration);
        TextView location = view.findViewById(R.id.location);
        TextView araea = view.findViewById(R.id.araea);
        TextView price = view.findViewById(R.id.price);
        TextView date = view.findViewById(R.id.date);
        TextView space = view.findViewById(R.id.space);
        ImageView image_ = view.findViewById(R.id.image_);
        ImageView close = view.findViewById(R.id.close);
        RatingBar rate = view.findViewById(R.id.rate);
        ScaleRatingBar simpleRatingBar = view.findViewById(R.id.simpleRatingBar);


        price.setText(homeModules_aqares.getTotalPrice());
        araea.setText(homeModules_aqares.getTotalArea() + " M ");
        opration.setText(homeModules_aqares.getEstate_type_name());
        date.setText(homeModules_aqares.getCreatedAt());
        space.setText(homeModules_aqares.getTotalArea() + "");
//        location.setText("---");

        if (homeModules_aqares.getCity_name() != null) {
            location.setText("" + homeModules_aqares.getCity_name() + "-" + homeModules_aqares.getNeighborhood_name());
        }

        try {

            System.out.println("$%$%$%" + homeModules_aqares.getFirst_image());


            Glide.with(context).load(homeModules_aqares.getFirst_image() + "").into(image_);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

//            rate.setStar(Float.valueOf(homeModules_aqares.getRate() + ""));
            simpleRatingBar.setRating(Float.valueOf(homeModules_aqares.getRate() + ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity_aqarz.class);

                System.out.println("id_aqarz" + homeModules_aqares.getId());
                intent.putExtra("id_aqarz", homeModules_aqares.getId() + "");
                context.startActivity(intent);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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


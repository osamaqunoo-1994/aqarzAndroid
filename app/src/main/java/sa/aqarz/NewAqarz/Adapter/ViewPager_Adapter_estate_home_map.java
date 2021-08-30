package sa.aqarz.NewAqarz.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.hedgehog.ratingbar.RatingBar;
import com.willy.ratingbar.ScaleRatingBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import sa.aqarz.Activity.ChatRoomActivity;
import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Activity.DetailsNewAqarezActivity;
import sa.aqarz.Activity.ReportAqarezActivity;
import sa.aqarz.Activity.check_login;
import sa.aqarz.Activity.profile.OtherProfileActivity;
import sa.aqarz.Adapter.RecyclerVie_Images;
import sa.aqarz.Adapter.RecyclerView_All_Comfort_in_details;
import sa.aqarz.Adapter.RecyclerView_coments;
import sa.aqarz.Adapter.RecyclerView_samilar;
import sa.aqarz.Dialog.BottomSheetDialogFragment_Rate;
import sa.aqarz.Modules.ComfortModules;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.imagemodules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class ViewPager_Adapter_estate_home_map extends PagerAdapter {


    public static List<HomeModules_aqares> alldata = new ArrayList<HomeModules_aqares>();
    private final LayoutInflater inflater;
    private final Context context;


    IResult mResultCallback;
    int oi = 0;
    public static Activity activity;

    //
    ImageView image;
    TextView price;
    TextView type;
    TextView dublex;
    TextView opration;
    TextView address;
    TextView date;
    TextView space;
    TextView num_id;
    ImageView image_icon;
    ImageView add_favorite;
    ImageView share;
    ImageView hide;
    TextView bathroom;
    TextView room;
    ScaleRatingBar rate;
    public static final ArrayList<imagemodules> items_ViewPager = new ArrayList<imagemodules>();
//
//    RecyclerView type_RecyclerView;
//    List<TypeModules> typeModules_list = new ArrayList<>();


    public ViewPager_Adapter_estate_home_map(Context context, List<HomeModules_aqares> IMAGES) {
        this.context = context;
        alldata = IMAGES;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return alldata.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View vieww = inflater.inflate(R.layout.item_list_estat_home_map, view, false);

        image = vieww.findViewById(R.id.image);
        price = vieww.findViewById(R.id.price);
        type = vieww.findViewById(R.id.type);
        dublex = vieww.findViewById(R.id.dublex);
        opration = vieww.findViewById(R.id.opration);
        address = vieww.findViewById(R.id.address);
        date = vieww.findViewById(R.id.date);
        space = vieww.findViewById(R.id.space);
        num_id = vieww.findViewById(R.id.num_id);
        image_icon = vieww.findViewById(R.id.image_icon);
        add_favorite = vieww.findViewById(R.id.add_favorite);
        share = vieww.findViewById(R.id.share);
        hide = vieww.findViewById(R.id.hide);
        rate = vieww.findViewById(R.id.rate);
        bathroom = view.findViewById(R.id.bathroom);
        room = view.findViewById(R.id.room);

        price.setText(alldata.get(position).getTotalPrice());
        type.setText(alldata.get(position).getEstate_type_name());
        opration.setText(alldata.get(position).getOperationTypeName());
        space.setText(alldata.get(position).getTotalArea() + "");
        date.setText(alldata.get(position).getCreatedAt() + "");
        num_id.setText("#" + alldata.get(position).getId() + "");

        address.setText(alldata.get(position).getFull_address() + "");

//        if (alldata.get(position).getCity_name() != null) {
//            address.setText(alldata.get(position).getCity_name() + " - " + alldata.get(position).getNeighborhood_name());
//
//        } else {
//            address.setText(alldata.get(position).getAddress());
//
//        }

//        if (alldata.get(position).getAddress() == null) {
//
//        } else {
//
//        }

        try {
            rate.setRating(Float.valueOf(alldata.get(position).getRate() + ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        bathroom.setText(alldata.get(position).getBathroomsNumber() + "");
//        room.setText(alldata.get(position).getRoomsNumber() + "");


//        if (alldata.get(position).getEstate_type() != null) {
//            Glide.with(context).load(alldata.get(position).getEstate_type().getIcon() + "").into(image_icon);
//
//        }
        Glide.with(context).load(alldata.get(position).getFirst_image() + "").into(image);


//        if (alldata.get(position).getIn_fav().equals("1")) {
//            add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_heart));
//
//        } else {
//            add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_like));
//
//        }
//        add_favorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (alldata.get(position).getIn_fav().equals("1")) {
//                    add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_like));
//                    alldata.get(position).setIn_fav("0");
//                } else {
//                    add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_heart));
//                    alldata.get(position).setIn_fav("1");
//
//                }
//                init_volley();
//                WebService.loading((Activity) context, true);
//
//                VolleyService mVolleyService = new VolleyService(mResultCallback, context);
//                try {
//
//                    JSONObject jsonObject = new JSONObject();
//                    jsonObject.put("type_id", "" + alldata.get(position).getId());
//                    jsonObject.put("type", "" + "offer");//'request','offer','fund'
//                    mVolleyService.postDataVolley("favorite", WebService.favorite, jsonObject);
//
//
//                } catch (Exception e) {
//
//                }
//
//            }
//        });


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Postion_opend = position;
//                Refr();

//                RequestOrderActivity.set_fragment(position);

                Intent intent = new Intent(context, DetailsActivity_aqarz.class);
                intent.putExtra("id_aqarz", alldata.get(position).getId() + "");
                context.startActivity(intent);


//                if (mItemClickListener != null) {
//                    mItemClickListener.onItemClick(position);
//                }
//

            }
        });
//        share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
////                Postion_opend = position;
////                Refr();
//
////                RequestOrderActivity.set_fragment(position);
//
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://aqarz.sa/estate/" + alldata.get(position).getId() + "/show");
//                sendIntent.setType("text/plain");
//
//                Intent shareIntent = Intent.createChooser(sendIntent, null);
//                context.startActivity(shareIntent);
//
//            }
//        });

//        hide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
////                Postion_opend = position;
////                Refr();
//
////                RequestOrderActivity.set_fragment(position);
//
//                init_volley();
//                WebService.loading((Activity) context, true);
//
//                VolleyService mVolleyService = new VolleyService(mResultCallback, context);
//                mVolleyService.getDataVolley("hide", WebService.hide + "/" + alldata.get(position).getId() + "/estate");
//
//            }
//        });


        view.addView(vieww, 0);

        return vieww;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading((Activity) context, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");
//                        String message = response.getString("message");
                        String message = response.getString("message");
                        if (requestType.equals("hide")) {
                            WebService.Make_Toast_color((Activity) context, message, "success");

                        }


//                        WebService.Make_Toast_color((Activity) context, message, "success");


                    }
                } catch (Exception e) {

                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading((Activity) context, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color((Activity) context, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading((Activity) context, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading((Activity) context, false);

                WebService.Make_Toast_color((Activity) context, error, "error");


            }
        };


    }

}
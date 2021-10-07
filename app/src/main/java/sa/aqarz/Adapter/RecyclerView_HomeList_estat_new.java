package sa.aqarz.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;
import com.willy.ratingbar.ScaleRatingBar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.NewAqarz.DetaislAqarzActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_HomeList_estat_new extends RecyclerView.Adapter<RecyclerView_HomeList_estat_new.MyViewHolder> {
    public static List<HomeModules_aqares> alldata = new ArrayList<HomeModules_aqares>();
    static int Postion_opend = 0;

    IResult mResultCallback;

    static AlertDialog alertDialog;
    private ItemClickListener mItemClickListener;


    /**
     * View holder class
     */
    Context context;

    public void Refr() {


        this.notifyDataSetChanged();
    }

    public void RefreshData() {
//        alldata.addAll(homeModules_aqares);

        this.notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView price;
        TextView type;
        TextView dublex;
        TextView opration;
        TextView address;
        TextView date;
        TextView space;
        TextView num_id;
        TextView bathroom;
        TextView id;
        TextView room;
        ImageView image_icon;
        ImageView add_favorite;
        ImageView share;
        ImageView hide;
        ImageView noimage;
        ScaleRatingBar rate;
        ProgressBar pr_1;

        TextView is_rent_installment;

        public MyViewHolder(View view) {
            super(view);
            //  title_cared_product_rec = (TextView) view.findViewById(R.id.title_cared_product_rec);

//
//            image_aqars = view.findViewById(R.id.image_aqars);
//            type = view.findViewById(R.id.type);
//            type_2 = view.findViewById(R.id.type_2);
//            price = view.findViewById(R.id.price);
//            address = view.findViewById(R.id.address);
//            opration = view.findViewById(R.id.opration);
//
//
//            number_room = view.findViewById(R.id.number_room);
//            number_bathroom = view.findViewById(R.id.number_bathroom);
//            max_space = view.findViewById(R.id.max_space);
//            viesw = view.findViewById(R.id.viesw);


            add_favorite = view.findViewById(R.id.add_favorite);
            pr_1 = view.findViewById(R.id.pr_1);
            noimage = view.findViewById(R.id.noimage);
            image_icon = view.findViewById(R.id.image_icon);
            image = view.findViewById(R.id.image);
            price = view.findViewById(R.id.price);
            dublex = view.findViewById(R.id.dublex);
            opration = view.findViewById(R.id.opration);
            type = view.findViewById(R.id.type);
            address = view.findViewById(R.id.address);
            date = view.findViewById(R.id.date);
            space = view.findViewById(R.id.space);
            rate = view.findViewById(R.id.rate);
            num_id = view.findViewById(R.id.num_id);
            bathroom = view.findViewById(R.id.bathroom);
            room = view.findViewById(R.id.room);
            is_rent_installment = view.findViewById(R.id.is_rent_installment);
            id = view.findViewById(R.id.id);
//            ratingbar = view.findViewById(R.id.ratingbar);
//            ratingbar = view.findViewById(R.id.ratingbar);
////            simpleRatingBar = view.findViewById(R.id.simpleRatingBar);
            share = view.findViewById(R.id.share);
            hide = view.findViewById(R.id.hide);
        }
    }

    public RecyclerView_HomeList_estat_new(Context context, List<HomeModules_aqares> alldata) {
        RecyclerView_HomeList_estat_new.alldata = alldata;
        this.context = context;
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


//        holder.setIsRecyclable(false);


        Glide.with(context).load(alldata.get(position).getFirst_image() + "").into(holder.image);
        holder.price.setText(alldata.get(position).getTotalPrice());
        holder.type.setText(alldata.get(position).getEstate_type_name());
        holder.opration.setText(alldata.get(position).getOperationTypeName());
        holder.space.setText(alldata.get(position).getTotalArea() + "");
        holder.date.setText(alldata.get(position).getCreatedAt() + "");
        holder.num_id.setText("#" + alldata.get(position).getId() + "");
        holder.id.setText("#" + alldata.get(position).getId() + "");
        try {

            if (alldata.get(position).getBathroomsNumber() != null) {
                if (!alldata.get(position).getBathroomsNumber().toString().equals("null")) {
                    holder.bathroom.setText(alldata.get(position).getBathroomsNumber() + "");

                }
            }
            if (alldata.get(position).getRoomsNumber() != null) {
                if (!alldata.get(position).getRoomsNumber().toString().equals("null")) {
                    holder.room.setText(alldata.get(position).getRoomsNumber() + "");

                }
            }
        } catch (Exception e) {

        }

        if (alldata.get(position).getFull_address() != null) {
            if (!alldata.get(position).getFull_address().equals("null")) {
                holder.address.setText(alldata.get(position).getFull_address() + "");

            }
        }

//        if (alldata.get(position).getCity_name() != null) {
//            holder.address.setText(alldata.get(position).getCity_name() + " - " + alldata.get(position).getNeighborhood_name());
//
//        } else {
//            holder.address.setText(alldata.get(position).getAddress());
//        }

//        if (alldata.get(position).getAddress() == null) {
//
//        } else {
//
//        }

        try {
            holder.rate.setRating(Float.valueOf(alldata.get(position).getRate() + ""));
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("alldata.get(position).getFirst_image()" + alldata.get(position).getFirst_image());
        if (alldata.get(position).getFirst_image() != null) {

            if (alldata.get(position).getFirst_image().equals("null")) {
                System.out.println("))))))))s)))))))");

//                holder.image_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_logo_move));
                holder.noimage.setVisibility(View.VISIBLE);
                holder.pr_1.setVisibility(View.GONE);
            } else {
                holder.noimage.setVisibility(View.GONE);
                holder.pr_1.setVisibility(View.GONE);
                Glide.with(context).load(alldata.get(position).getFirst_image() + "").error(context.getResources().getDrawable(R.drawable.logo_login)).into(holder.image_icon);
            }


        } else {
            System.out.println("))))))x)))))))))");
            holder.noimage.setVisibility(View.VISIBLE);
            holder.pr_1.setVisibility(View.GONE);
//            holder.image_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_logo_move));
        }


//        if (alldata.get(position).getIn_fav().equals("1")) {
//            holder.add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_heart));
//
//        } else {
//            holder.add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_like));
//
//        }

        if (alldata.get(position).getIs_rent_installment() != null) {
            if (!alldata.get(position).getIs_rent_installment().equals("null")) {

                if (alldata.get(position).getIs_rent_installment().equals("1")) {

                    holder.is_rent_installment.setVisibility(View.VISIBLE);

                } else {
                    holder.is_rent_installment.setVisibility(View.GONE);

                }
            } else {
                holder.is_rent_installment.setVisibility(View.GONE);

            }
        } else {
            holder.is_rent_installment.setVisibility(View.GONE);

        }


        holder.add_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (alldata.get(position).getIn_fav().equals("1")) {
                    holder.add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_like));
                    alldata.get(position).setIn_fav("0");
                } else {
                    holder.add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_heart));
                    alldata.get(position).setIn_fav("1");


                }
                init_volley();
                WebService.loading((Activity) context, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, context);
                try {

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("type_id", "" + alldata.get(position).getId());
                    jsonObject.put("type", "" + "offer");//'request','offer','fund'
                    mVolleyService.postDataVolley("favorite", WebService.favorite, jsonObject);


                } catch (Exception e) {

                }

            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Postion_opend = position;
//                Refr();

//                RequestOrderActivity.set_fragment(position);

                Intent intent = new Intent(context, DetaislAqarzActivity.class);
                intent.putExtra("id_aqarz", alldata.get(position).getId() + "");
                context.startActivity(intent);


                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(position);
                }
//

            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Postion_opend = position;
//                Refr();

//                RequestOrderActivity.set_fragment(position);

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://aqarz.sa/estate/" + alldata.get(position).getId() + "/show");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                context.startActivity(shareIntent);

            }
        });

        holder.hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Postion_opend = position;
//                Refr();

//                RequestOrderActivity.set_fragment(position);

                init_volley();
                WebService.loading((Activity) context, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, context);
                mVolleyService.getDataVolley("hide", WebService.hide + "/" + alldata.get(position).getId() + "/estate");
                alldata.remove(position);
                Refr();
            }
        });

    }

    static PopupWindow popupWindow;


    @Override
    public int getItemCount() {
        return alldata.size();
    }

    public static int getIcCount() {
        return alldata.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_estat_home, parent, false);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_estat_home_map2, parent, false);


        // Fresco.initialize(context);


        return new MyViewHolder(v);
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int position);
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
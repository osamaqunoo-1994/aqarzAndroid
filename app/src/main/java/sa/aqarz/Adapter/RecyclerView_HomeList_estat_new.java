package sa.aqarz.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.hedgehog.ratingbar.RatingBar;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Modules.HomeModules_aqares;
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
        ImageView image_icon;
        ImageView add_favorite;

        RatingBar rate;


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
//            ratingbar = view.findViewById(R.id.ratingbar);
//            ratingbar = view.findViewById(R.id.ratingbar);
////            simpleRatingBar = view.findViewById(R.id.simpleRatingBar);

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


        Picasso.get().load(alldata.get(position).getFirst_image() + "").into(holder.image);
        holder.price.setText(alldata.get(position).getTotalPrice());
        holder.type.setText(alldata.get(position).getEstate_type_name());
        holder.opration.setText(alldata.get(position).getOperationTypeName());
        holder.space.setText(alldata.get(position).getTotalArea() + "");
        holder.date.setText(alldata.get(position).getCreatedAt() + "");


        if (alldata.get(position).getAddress() == null) {
            if (alldata.get(position).getCity_name() != null) {
                holder.address.setText(alldata.get(position).getCity_name() + " - " + alldata.get(position).getNeighborhood_name());

            }

        } else {
            holder.address.setText(alldata.get(position).getAddress());

        }

        try {
            holder.rate.setStar(Float.valueOf(alldata.get(position).getRate() + ""));
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (alldata.get(position).getEstate_type() != null) {
            Picasso.get().load(alldata.get(position).getEstate_type().getIcon() + "").into(holder.image_icon);

        }


        if (alldata.get(position).getIn_fav().equals("1")) {
            holder.add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_heart));

        } else {
            holder.add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_like));

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

                Intent intent = new Intent(context, DetailsActivity_aqarz.class);
                intent.putExtra("id_aqarz", alldata.get(position).getId() + "");
                context.startActivity(intent);


                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(position);
                }
//

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


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_estat_home, parent, false);


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
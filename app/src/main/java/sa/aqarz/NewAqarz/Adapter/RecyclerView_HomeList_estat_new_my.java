package sa.aqarz.NewAqarz.Adapter;

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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.willy.ratingbar.ScaleRatingBar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.AddAqarz.EditAqarzActivity;
import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Dialog.BottomSheetDialogFragmen_delete_offer;
import sa.aqarz.Dialog.BottomSheetDialogFragmen_re_new_offer;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.NewAqarz.DetaislAqarzActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_HomeList_estat_new_my extends RecyclerView.Adapter<RecyclerView_HomeList_estat_new_my.MyViewHolder> {
    public static List<HomeModules_aqares> alldata = new ArrayList<HomeModules_aqares>();
    static int Postion_opend = 0;
    static int Postion_delete = -1;

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
        TextView room;
        ImageView image_icon;
        ImageView add_favorite;
        ImageView share;
        ImageView hide;
        ScaleRatingBar rate;


        ImageView re_news;
        ImageView edit;
        ImageView delete;
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

            re_news = view.findViewById(R.id.re_news);
            edit = view.findViewById(R.id.edit);
            delete = view.findViewById(R.id.delete);
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
            num_id = view.findViewById(R.id.num_id);
            bathroom = view.findViewById(R.id.bathroom);
            room = view.findViewById(R.id.room);
//            ratingbar = view.findViewById(R.id.ratingbar);
//            ratingbar = view.findViewById(R.id.ratingbar);
////            simpleRatingBar = view.findViewById(R.id.simpleRatingBar);
            share = view.findViewById(R.id.share);
            hide = view.findViewById(R.id.hide);
            is_rent_installment = view.findViewById(R.id.is_rent_installment);

        }
    }

    public RecyclerView_HomeList_estat_new_my(Context context, List<HomeModules_aqares> alldata) {
        RecyclerView_HomeList_estat_new_my.alldata = alldata;
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
//        holder.bathroom.setText(alldata.get(position).getBathroomsNumber() + "");
//        holder.room.setText(alldata.get(position).getRoomsNumber() + "");

        holder.delete.setVisibility(View.VISIBLE);
        holder.re_news.setVisibility(View.VISIBLE);
        holder.edit.setVisibility(View.VISIBLE);


        holder.share.setVisibility(View.VISIBLE);
        holder.hide.setVisibility(View.VISIBLE);
        holder.add_favorite.setVisibility(View.VISIBLE);

        holder.address.setText(alldata.get(position).getFull_address() + "");

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


        if (alldata.get(position).getEstate_type() != null) {
            Glide.with(context).load(alldata.get(position).getEstate_type().getIcon() + "").into(holder.image_icon);

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

                if (Settings.checkLogin()) {
                    init_volley();
                    WebService.loading((Activity) context, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, context);
                    mVolleyService.getDataVolley("hide", WebService.hide + "/" + alldata.get(position).getId() + "/estate");

                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }


            }
        });
        holder.re_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragmen_re_new_offer bottomSheetDialogFragmen_re_new_offer = new BottomSheetDialogFragmen_re_new_offer(alldata.get(position).getId() + "");
                bottomSheetDialogFragmen_re_new_offer.addItemClickListener(new BottomSheetDialogFragmen_re_new_offer.ItemClickListener() {
                    @Override
                    public void onItemClick(int id_estat) {

                    }
                });
                bottomSheetDialogFragmen_re_new_offer.show(((FragmentActivity) context).getSupportFragmentManager(), "");

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Postion_delete = position;
                BottomSheetDialogFragmen_delete_offer bottomSheetDialogFragmen_delete_offer = new BottomSheetDialogFragmen_delete_offer(alldata.get(position).getId() + "");
                bottomSheetDialogFragmen_delete_offer.addItemClickListener(new BottomSheetDialogFragmen_delete_offer.ItemClickListener() {
                    @Override
                    public void onItemClick(int id_estate) {

                        System.out.println("id_estate" + id_estate);
                        alldata.remove(Postion_delete);
                        Refr();

                    }
                });
                bottomSheetDialogFragmen_delete_offer.show(((FragmentActivity) context).getSupportFragmentManager(), "");

            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Intent intent = new Intent(context, EditAqarsActivity.class);
                Intent intent = new Intent(context, EditAqarzActivity.class);
                intent.putExtra("id_aqarz", alldata.get(position).getId() + "");
                context.startActivity(intent);


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


                    if (requestType.equals("delete")) {

                        alldata.remove(Postion_delete);
                        Refr();

                    } else if (requestType.equals("make_up")) {


                    }
//                        Str
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
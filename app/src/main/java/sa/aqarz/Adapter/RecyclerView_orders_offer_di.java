package sa.aqarz.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Dialog.BottomSheetDialogFragment_MyEstate;
import sa.aqarz.Modules.OfferRealStateModules;
import sa.aqarz.Modules.demandsModules;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_orders_offer_di extends RecyclerView.Adapter<RecyclerView_orders_offer_di.MyViewHolder> {
    public static List<OfferRealStateModules> alldata = new ArrayList<OfferRealStateModules>();
    static int Postion_opend = 0;

    IResult mResultCallback;

    static AlertDialog alertDialog;
    private ItemClickListener mItemClickListener;

    BottomSheetDialogFragment_MyEstate bottomSheetDialogFragment_myEstate;
    /**
     * View holder class
     */
    Context context;

    public void Refr() {


        this.notifyDataSetChanged();
    }

    public void RefreshData() {
        this.notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        //        CircleImageView story_image;
//        ProgressBar progress;
//        LinearLayout add_to_my;


        TextView price;
        TextView address;
        TextView view_type;
        TextView space;
        TextView name_estate;
        TextView statust;
        ImageView image_icon;
        ImageView image;
        LinearLayout date_la;
        ImageView add_favorite;

        public MyViewHolder(View view) {
            super(view);
            //  title_cared_product_rec = (TextView) view.findViewById(R.id.title_cared_product_rec);


            price = view.findViewById(R.id.price);
            name_estate = view.findViewById(R.id.name_estate);
            address = view.findViewById(R.id.address);
            space = view.findViewById(R.id.space);
            view_type = view.findViewById(R.id.view_type);
            statust = view.findViewById(R.id.statust);
            image_icon = view.findViewById(R.id.image_icon);
            image = view.findViewById(R.id.image);
            date_la = view.findViewById(R.id.date_la);
            add_favorite = view.findViewById(R.id.add_favorite);

//            ratingbar = view.findViewById(R.id.ratingbar);
////            simpleRatingBar = view.findViewById(R.id.simpleRatingBar);

        }
    }

    public RecyclerView_orders_offer_di(Context context, List<OfferRealStateModules> alldata) {
        this.alldata = alldata;
        this.context = context;
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


//        holder.setIsRecyclable(false);
//        holder.title.setText(alldata.get(position).getName());
//        holder.details.setText(alldata.get(position).getName());
//        if (alldata.get(position).getRate() != null) {
//            if (!alldata.get(position).getRate().equals("null")) {
//
//                holder.ratingbar.setStar(Integer.valueOf(alldata.get(position).getRate()));
//
//
//            }
//        }
//
        holder.date_la.setVisibility(View.GONE);

        holder.price.setText(alldata.get(position).getEstateTotalPrice() + "");
        holder.name_estate.setText(alldata.get(position).getEstateFinishingType() + "");
        holder.view_type.setText(alldata.get(position).getEstateInterface() + "");


        if (alldata.get(position).getEstateCity() != null) {
            holder.address.setText(alldata.get(position).getEstateCity() + "-" + alldata.get(position).getEstateNeighborhood());
        }

        holder.space.setText(alldata.get(position).getEstateTotalArea() + "");

        if (alldata.get(position).getEstate_attachment().size() != 0) {

            Picasso.get().load(alldata.get(position).getEstate_attachment().get(0).getFile()).into(holder.image);

        }
//        holder.name_estate.setText(a lldata.get(position).getEstateTypeName());
//        holder.address.setText(alldata.get(position).getCityName() + " , " + alldata.get(position).getNeighborhoodName());


//
//        System.out.println(alldata.get(position).getImage() + "");
//        Picasso.get().load(alldata.get(position).getes()).into(holder.image_icon);
////
//
//        try {
//
//
////
//        System.out.println(alldata.get(position).getImage() + "");
//        Picasso.with(context).load(alldata.get(position).getImage()).into(holder.image);
//        holder.price.setText(alldata.get(position).getPrice()+" "+context.getResources().getString(R.string.SAR));
//        holder.title.setText(alldata.get(position).getTitle()+"");
//        holder.description.setText(alldata.get(position).getDetails()+"");
//////
//
//        int random = ThreadLocalRandom.current().nextInt(1, 5);
//       holder.ratingbar.setStar(random);


        //   wallet, dafter, receipt, payment


//            double v=Double.valueOf(alldata.get(position).getRate());
//
//
//
//            holder.simpleRatingBar.setRating((float) v);
//
//
//        }catch (Exception e){
//
//        }
//
//
//        holder.image_user.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                if (alldata.get(position).getType().equals("user")) {
////                    Intent intent = new Intent(context, ProfileTravilingActivity.class);
////                    intent.putExtra("id_user", alldata.get(position).getId() + "");
////                    intent.putExtra("type", "other_frinds");
////
////                    context.startActivity(intent);
////                } else {
////                    Intent intent = new Intent(context, ProfileTourGuidActivity.class);
////                    intent.putExtra("id_user", alldata.get(position).getId() + "");
////                    intent.putExtra("type", "other_frinds");
////
////                    context.startActivity(intent);
////                }
//            }
//        });
//

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Postion_opend = position;
//                Refr();

//                RequestOrderActivity.set_fragment(position);


                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(position);
                }
//

            }
        });
        holder.statust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bottomSheetDialogFragment_myEstate = new BottomSheetDialogFragment_MyEstate(alldata.get(position).getId() + "");
//
//                bottomSheetDialogFragment_myEstate.show(((FragmentActivity) context).getSupportFragmentManager(), "");
//

            }
        });

        holder.add_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bottomSheetDialogFragment_myEstate = new BottomSheetDialogFragment_MyEstate(alldata.get(position).getId() + "");
//
//                bottomSheetDialogFragment_myEstate.show(((FragmentActivity) context).getSupportFragmentManager(), "");

                holder.add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_heart));

                init_volley();
                WebService.loading((Activity) context, true);

                VolleyService mVolleyService = new VolleyService(mResultCallback, context);
                try {

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("type_id", "" + alldata.get(position).getId());
                    jsonObject.put("type", "" + "offer");
                    mVolleyService.postDataVolley("favorite", WebService.favorite, jsonObject);


                } catch (Exception e) {

                }

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


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ordersxx_offer, parent, false);


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


                        WebService.Make_Toast_color((Activity) context, message, "success");


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
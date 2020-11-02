package aqarz.revival.sa.aqarz.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Activity.DetailsActivity;
import aqarz.revival.sa.aqarz.Activity.DetailsActivity_aqarz;
import aqarz.revival.sa.aqarz.Modules.HomeModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_HomeList extends RecyclerView.Adapter<RecyclerView_HomeList.MyViewHolder> {
    public static List<HomeModules> alldata = new ArrayList<HomeModules>();
    static int Postion_opend = 0;


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
        this.notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        //        CircleImageView story_image;
//        ProgressBar progress;
//        LinearLayout add_to_my;

        //        ImageView image_aqars;
//        TextView type;
//        TextView price;
        TextView address;
//        TextView opration;
//        TextView space;

        TextView price;
        TextView type;
        TextView dublex;
        TextView opration;
        TextView date;

        ImageView image_icon;

        public MyViewHolder(View view) {
            super(view);
            //  title_cared_product_rec = (TextView) view.findViewById(R.id.title_cared_product_rec);

//
//            image_aqars = view.findViewById(R.id.image_aqars);
//            type = view.findViewById(R.id.type);
//            price = view.findViewById(R.id.price);
//            space = view.findViewById(R.id.space);
//            address = view.findViewById(R.id.address);
//            opration = view.findViewById(R.id.opration);
            image_icon = view.findViewById(R.id.image_icon);
            price = view.findViewById(R.id.price);
            dublex = view.findViewById(R.id.dublex);
            opration = view.findViewById(R.id.opration);
            type = view.findViewById(R.id.type);
            address = view.findViewById(R.id.address);
            date = view.findViewById(R.id.date);

//            ratingbar = view.findViewById(R.id.ratingbar);
//            ratingbar = view.findViewById(R.id.ratingbar);
////            simpleRatingBar = view.findViewById(R.id.simpleRatingBar);

        }
    }

    public RecyclerView_HomeList(Context context, List<HomeModules> alldata) {
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
////
//

//
//        holder.type.setText(alldata.get(position).getEstate_type_name());
//        holder.space.setText(alldata.get(position).getArea_from() + " - " + alldata.get(position).getArea_to());
//
//        holder.price.setText(alldata.get(position).getPrice_from() + " - " + alldata.get(position).getPrice_to());
//
//
//        holder.opration.setText(alldata.get(position).getRequest_type());

        holder.price.setText(alldata.get(position).getPrice_from() + " - " + alldata.get(position).getPrice_to());
        holder.type.setText(alldata.get(position).getEstate_type_name());
        holder.opration.setText(alldata.get(position).getOperation_type_name());
        holder.date.setText(alldata.get(position).getCreated_at());

        if (alldata.get(position).getAddress() == null) {
            if (alldata.get(position).getCity_name() != null) {
                holder.address.setText(alldata.get(position).getCity_name() + " - " + alldata.get(position).getNeighborhood_name());

            }

        } else {
            holder.address.setText(alldata.get(position).getAddress());

        }

        Picasso.get().load(alldata.get(position).getEstate_type().getIcon() + "").into(holder.image_icon);

//        System.out.println(alldata.get(position).getImage() + "");
//        Picasso.with(context).load(alldata.get(position).getImage()).into(holder.service_image);
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

                Intent intent = new Intent(context, DetailsActivity.class);
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


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_bottom_details_aqarz_order, parent, false);


        // Fresco.initialize(context);


        return new MyViewHolder(v);
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int position);
    }
}
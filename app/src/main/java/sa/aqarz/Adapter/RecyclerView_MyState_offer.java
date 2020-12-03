package sa.aqarz.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.RealState.OfferDetailsActivity;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.MyOfferModule;
import sa.aqarz.R;
import sa.aqarz.Settings.Application;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_MyState_offer extends RecyclerView.Adapter<RecyclerView_MyState_offer.MyViewHolder> {
    public static List<MyOfferModule> alldata = new ArrayList<MyOfferModule>();
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


        //        LinearLayout answer_layout;
        //  public FrameLayout frame;

        LinearLayout back_ground;
        TextView opration;
        TextView price;
        TextView address;
        TextView date;
        TextView contnue;
        TextView rejecteds;
        TextView sale;
        ImageView image;

        CheckBox chechbox;

        public MyViewHolder(View view) {
            super(view);
            //  title_cared_product_rec = (TextView) view.findViewById(R.id.title_cared_product_rec);

            image = view.findViewById(R.id.image);
            opration = view.findViewById(R.id.opration);
            price = view.findViewById(R.id.price);
            address = view.findViewById(R.id.address);
            chechbox = view.findViewById(R.id.chechbox);
            date = view.findViewById(R.id.date);
            contnue = view.findViewById(R.id.contnue);
            rejecteds = view.findViewById(R.id.rejecteda);
            sale = view.findViewById(R.id.sale);


        }
    }

    public RecyclerView_MyState_offer(Context context, List<MyOfferModule> alldata) {
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
        holder.price.setText(alldata.get(position).getEstateTotalPrice() + "");
        holder.opration.setText(alldata.get(position).getEstate_type_name() + "");
        holder.address.setText(alldata.get(position).getEstateCity() + "");
        holder.sale.setText(alldata.get(position).getSale_type_name() + "");
//        holder.date.setText(alldata.get(position).getCreatedAt());
//        if (alldata.get(position).getRate() != null) {
//            if (!alldata.get(position).getRate().equals("null")) {
//
//                holder.ratingbar.setStar(Integer.valueOf(alldata.get(position).getRate()));
//
//
//            }
//        }
////
//        holder.text.setText(alldata.get(position).getName() + "");


//        if (position == Postion_opend) {
//            holder.back_ground.setBackground(context.getResources().getDrawable(R.drawable.button_login));
//
//            holder.text.setTextColor(context.getResources().getColor(R.color.white));
//
//
//            holder.image_in_type.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
//
//        } else {
//            holder.back_ground.setBackground(context.getResources().getDrawable(R.drawable.search_background));
//
//            holder.text.setTextColor(context.getResources().getColor(R.color.textColor));
//            holder.image_in_type.setColorFilter(ContextCompat.getColor(context, R.color.textColor), android.graphics.PorterDuff.Mode.MULTIPLY);
//
//        }
////
//        System.out.println(alldata.get(position).getImage() + "");
        try {
            Picasso.get().load(alldata.get(position).getEstate_attachment().get(0).getFile() + "").into(holder.image);

        } catch (Exception e) {

        }


        if (alldata.get(position).getStatus() == null) {
            holder.contnue.setVisibility(View.GONE);
            holder.rejecteds.setVisibility(View.GONE);
        } else if (alldata.get(position).getStatus().toString().equals("active")) {
            holder.contnue.setVisibility(View.VISIBLE);
            holder.rejecteds.setVisibility(View.GONE);

        } else if (alldata.get(position).getStatus().toString().equals("rejected_customer")) {
            holder.rejecteds.setVisibility(View.VISIBLE);
            holder.contnue.setVisibility(View.GONE);
            System.out.println("dkflfjlkfdlfkldfkldfkdlfk");
        } else if (alldata.get(position).getStatus().toString().equals("accepted_customer")) {
            holder.contnue.setVisibility(View.VISIBLE);
            holder.rejecteds.setVisibility(View.GONE);

        }


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


//        if (alldata.get(position).getIs_selected()) {
//            holder.chechbox.setChecked(true);
//        } else {
//            holder.chechbox.setChecked(false);
//
//        }

        holder.contnue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application.myOfferModule = alldata.get(position);

                Intent intent = new Intent(context, OfferDetailsActivity.class);
                context.startActivity(intent);


            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Application.myOfferModule = alldata.get(position);
                Intent intent = new Intent(context, OfferDetailsActivity.class);
                context.startActivity(intent);


//
//

//
//                if (alldata.get(position).getIs_selected()) {
//                    holder.chechbox.setChecked(false);
//                    alldata.get(position).setIs_selected(false);
//                } else {
//                    holder.chechbox.setChecked(true);
//                    alldata.get(position).setIs_selected(true);
//                }
//                if (mItemClickListener != null) {
//                    mItemClickListener.onItemClick(alldata);
//                }
//                Postion_opend = position;
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


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_my_estate_offer, parent, false);


        // Fresco.initialize(context);


        return new MyViewHolder(v);
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(List<HomeModules_aqares> homeModules_aqares);
    }
}
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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Activity.RealState.OfferDetailsActivity;
import sa.aqarz.Dialog.BottomSheetDialogFragment_status;
import sa.aqarz.Dialog.BottomSheetDialogFragment_status_cancle;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.MyOfferModule;
import sa.aqarz.NewAqarz.DetaislAqarzActivity;
import sa.aqarz.NewAqarz.OprationOrder.DetailsOrderActivity;
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
        TextView status;
        TextView type;
        TextView id_number;
        TextView expired;
        TextView space;
        TextView bathroom;
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
            status = view.findViewById(R.id.status);
            id_number = view.findViewById(R.id.id_number);
            type = view.findViewById(R.id.type);
            expired = view.findViewById(R.id.expired);
            space = view.findViewById(R.id.space);
            bathroom = view.findViewById(R.id.bathroom);


        }
    }

    public RecyclerView_MyState_offer(Context context, List<MyOfferModule> alldata) {
        RecyclerView_MyState_offer.alldata = alldata;
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
        holder.opration.setText(alldata.get(position).getSale_type_name() + "");
        holder.sale.setText(alldata.get(position).getSale_type_name() + "");
        holder.type.setText(alldata.get(position).getEstate_type_name() + "");
        holder.space.setText(alldata.get(position).getEstateTotalArea() + "");
//        holder.bathroom.setText(alldata.get(position).getb() + "");
        try {

            if (alldata.get(position).getFull_address() != null) {

                if (!alldata.get(position).getFull_address().equals("")) {
                    holder.address.setText(alldata.get(position).getFull_address() + " ");

                }
            }

        } catch (Exception e) {

        }
        holder.id_number.setText(" # " + alldata.get(position).getId() + "");
//        holder.date.setText(alldata.get(position).getesta());
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
            Glide.with(context).load(alldata.get(position).getEstate_attachment().get(0).getFile() + "").into(holder.image);

        } catch (Exception e) {

        }

        holder.status.setVisibility(View.VISIBLE);

        System.out.println("%^HHHH^^" + alldata.get(position).getStatus());

        if (alldata.get(position).getStatus() == null) {
            holder.contnue.setVisibility(View.VISIBLE);
            holder.rejecteds.setVisibility(View.GONE);
            holder.status.setVisibility(View.GONE);
            holder.expired.setVisibility(View.GONE);

        } else if (alldata.get(position).getStatus().equals("active")) {
            holder.contnue.setVisibility(View.VISIBLE);
            holder.rejecteds.setVisibility(View.GONE);
            holder.status.setVisibility(View.GONE);
            holder.expired.setVisibility(View.GONE);

        } else if (alldata.get(position).getStatus().equals("sending_code")) {
            holder.contnue.setVisibility(View.VISIBLE);
            holder.rejecteds.setVisibility(View.GONE);
            holder.status.setVisibility(View.GONE);
            holder.expired.setVisibility(View.GONE);

        } else if (alldata.get(position).getStatus().equals("rejected_customer ")) {
            holder.rejecteds.setVisibility(View.VISIBLE);
            holder.contnue.setVisibility(View.GONE);
            holder.status.setVisibility(View.GONE);
            holder.expired.setVisibility(View.GONE);

            System.out.println("dkflfjlkfdlfkldfkldfkdlfk");
        } else if (alldata.get(position).getStatus().equals("accepted_customer")) {
            holder.contnue.setVisibility(View.VISIBLE);
            holder.rejecteds.setVisibility(View.GONE);
            holder.status.setVisibility(View.GONE);
            holder.expired.setVisibility(View.GONE);


        } else if (alldata.get(position).getStatus().equals("expired")) {
            holder.contnue.setVisibility(View.GONE);
            holder.rejecteds.setVisibility(View.GONE);
            holder.status.setVisibility(View.GONE);
            holder.expired.setVisibility(View.VISIBLE);

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

        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(alldata.get(position).getBeneficiaryMobile()!=null) {
//                    if (alldata.get(position).getBeneficiaryMobile().equals("")) {
                        Application.myOfferModule = alldata.get(position);
//
//                        Intent intent = new Intent(context, OfferDetailsActivity.class);
                        Intent intent = new Intent(context, DetailsOrderActivity.class);
                        context.startActivity(intent);
//                    }else{
//
//                    }
//                }else{
//                    BottomSheetDialogFragment_status_cancle bottomSheetDialogFragment_status = new BottomSheetDialogFragment_status_cancle("");
//
//                    bottomSheetDialogFragment_status.show(((FragmentActivity) context).getSupportFragmentManager(), "");
//
//                }
//                BottomSheetDialogFragment_status_cancle bottomSheetDialogFragment_status = new BottomSheetDialogFragment_status_cancle("");
//
//                bottomSheetDialogFragment_status.show(((FragmentActivity) context).getSupportFragmentManager(), "");


            }
        });
        holder.contnue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Application.myOfferModule = alldata.get(position);
//
//                        Intent intent = new Intent(context, OfferDetailsActivity.class);
                Intent intent = new Intent(context, DetailsOrderActivity.class);
                context.startActivity(intent);
//                if (alldata.get(position).getBeneficiaryMobile() != null) {
//
//
//                    if (!alldata.get(position).getBeneficiaryMobile().equals("null")) {
//                        Application.myOfferModule = alldata.get(position);
//
//                        Intent intent = new Intent(context, OfferDetailsActivity.class);
//                        context.startActivity(intent);
//
//                    } else {
//                        BottomSheetDialogFragment_status_cancle bottomSheetDialogFragment_status = new BottomSheetDialogFragment_status_cancle("");
//
//                        bottomSheetDialogFragment_status.show(((FragmentActivity) context).getSupportFragmentManager(), "");
//
//                    }
//
//
//                } else {
//                    BottomSheetDialogFragment_status_cancle bottomSheetDialogFragment_status = new BottomSheetDialogFragment_status_cancle("");
//
//                    bottomSheetDialogFragment_status.show(((FragmentActivity) context).getSupportFragmentManager(), "");
//
//                }


            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

////
//                if (alldata.get(position).getStatus() == null) {
//
//                } else if (alldata.get(position).getStatus().toString().equals("active")) {
//                    Application.myOfferModule = alldata.get(position);
//                    Intent intent = new Intent(context, OfferDetailsActivity.class);
//                    context.startActivity(intent);
//                } else if (alldata.get(position).getStatus().toString().equals("rejected_customer")) {
//                } else if (alldata.get(position).getStatus().toString().equals("accepted_customer")) {
//                    Application.myOfferModule = alldata.get(position);
//                    Intent intent = new Intent(context, OfferDetailsActivity.class);
//                    context.startActivity(intent);
//
//                }

                Application.myOfferModule = alldata.get(position);

                Intent intent = new Intent(context, DetaislAqarzActivity.class);//DetaislAqarzActivity
                intent.putExtra("id_aqarz", alldata.get(position).getEstateId() + "");
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
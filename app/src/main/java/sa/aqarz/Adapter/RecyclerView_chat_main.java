package sa.aqarz.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import sa.aqarz.Activity.ChatRoomActivity;
import sa.aqarz.Activity.DetailsActivity;
import sa.aqarz.Modules.MsgModules;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_chat_main extends RecyclerView.Adapter<RecyclerView_chat_main.MyViewHolder> {
    public static List<MsgModules> alldata = new ArrayList<MsgModules>();
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


        TextView last_message;
        TextView name;
        TextView time;
        TextView count;
        TextView text_date;


        CircleImageView image_profile;

        public MyViewHolder(View view) {
            super(view);
            //  title_cared_product_rec = (TextView) view.findViewById(R.id.title_cared_product_rec);


            last_message = view.findViewById(R.id.last_message);
            name = view.findViewById(R.id.name);
            time = view.findViewById(R.id.time);
            image_profile = view.findViewById(R.id.image_profile);
            count = view.findViewById(R.id.count);
            text_date = view.findViewById(R.id.text_date);
//            ratingbar = view.findViewById(R.id.ratingbar);
////            simpleRatingBar = view.findViewById(R.id.simpleRatingBar);

        }
    }

    public RecyclerView_chat_main(Context context, List<MsgModules> alldata) {
        RecyclerView_chat_main.alldata = alldata;
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

//
//        System.out.println(alldata.get(position).getImage() + "");
//        Picasso.with(context).load(alldata.get(position).getImage()).into(holder.service_image);
//        Picasso.with(context).load(alldata.get(position).getImage()).into(holder.service_image);
//        Picasso.with(context).load(alldata.get(position).getImage()).into(holder.service_image);
//        Picasso.with(context).load(alldata.get(position).getImage()).into(holder.service_image);
//        Picasso.with(context).load(alldata.get(position).getImage()).into(holder.service_image);
////
//
//        try {
//
//
////
//        System.out.println(alldata.get(position).getImage() + "");
//        Picasso.with(context).load(alldata.get(position).getImage()).into(holder.image);
        holder.name.setText(alldata.get(position).getDisplay_name());
        holder.last_message.setText(alldata.get(position).getBody() + "");
        if (alldata.get(position).getCount_not_read().equals("0")) {
            holder.count.setVisibility(View.GONE);
        } else {
            holder.count.setVisibility(View.VISIBLE);

        }
        holder.count.setText(alldata.get(position).getCount_not_read() + "");


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        String dateInStrings = alldata.get(position).getCreatedAt() + "";
        String dateInString = dateInStrings.substring(0, 19);

        SimpleDateFormat formatterOut = new SimpleDateFormat("dd MMM yyyy");

        try {

            Date date = formatter.parse(dateInString);
            holder.text_date.setText(formatterOut.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

//        holder.description.setText(alldata.get(position).getDetails()+"");
//////
//
//        int random = ThreadLocalRandom.current().nextInt(1, 5);
//       holder.ratingbar.setStar(random);
        if (alldata.get(position).getReceiverPhoto() != null) {
            if (!alldata.get(position).getReceiverPhoto().equals("")) {
                Picasso.get().load(alldata.get(position).getReceiverPhoto()).error(R.drawable.ic_user_un).into(holder.image_profile);

            } else {
                Picasso.get().load(R.drawable.ic_user_un).error(R.drawable.ic_user_un).into(holder.image_profile);

            }

        } else {
            Picasso.get().load(R.drawable.ic_user_un).error(R.drawable.ic_user_un).into(holder.image_profile);

        }

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


                if (alldata.get(position).getSenderId() != null) {
                    Intent intent = new Intent(context, ChatRoomActivity.class);

                    if (alldata.get(position).getReceiverId().equals(Settings.GetUser().getId() + "")) {
                        intent.putExtra("user_id", alldata.get(position).getSenderId() + "");

                    } else {
                        intent.putExtra("user_id", alldata.get(position).getReceiverId() + "");

                    }

                    intent.putExtra("parent_id", alldata.get(position).getId() + "");
                    intent.putExtra("nameUser", alldata.get(position).getDisplay_name() + "");
                    intent.putExtra("imageUser", alldata.get(position).getReceiverPhoto() + "");
                    context.startActivity(intent);

                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(position);
                    }
//
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


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main_chat, parent, false);


        // Fresco.initialize(context);


        return new MyViewHolder(v);
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int position);
    }
}
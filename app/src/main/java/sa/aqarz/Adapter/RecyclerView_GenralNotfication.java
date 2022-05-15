package sa.aqarz.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Modules.ComfortModules;
import sa.aqarz.Modules.NotficationModules;
import sa.aqarz.R;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_GenralNotfication extends RecyclerView.Adapter<RecyclerView_GenralNotfication.MyViewHolder> {
    public static List<NotficationModules> alldata = new ArrayList<NotficationModules>();
    static int Postion_opend = -1;


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

        TextView title;
        TextView des;
        TextView date;
        ImageView image;

        public MyViewHolder(View view) {
            super(view);
            //  title_cared_product_rec = (TextView) view.findViewById(R.id.title_cared_product_rec);


//            back_ground = view.findViewById(R.id.back_ground);
            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
            des = view.findViewById(R.id.des);
            image = view.findViewById(R.id.image);
////            simpleRatingBar = view.findViewById(R.id.simpleRatingBar);

        }
    }

    public RecyclerView_GenralNotfication(Context context, List<NotficationModules> alldata) {
        RecyclerView_GenralNotfication.alldata = alldata;
        this.context = context;
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


//        holder.setIsRecyclable(false);
//        if (alldata.get(position).getType().equals("request")) {
//            holder.title.setText("طلب");
//        } else {
//            holder.title.setText(alldata.get(position).getType());
//
//        }
        holder.des.setText(alldata.get(position).getTitle());
//        if (alldata.get(position).getRate() != null) {
//            if (!alldata.get(position).getRate().equals("null")) {
//
//                holder.ratingbar.setStar(Integer.valueOf(alldata.get(position).getRate()));
//
//
//            }

        holder.date.setText(alldata.get(position).getCreatedAt() + "");
        if (alldata.get(position).getType().equals("request")) {
            holder.title.setText(context.getResources().getString(R.string.not1));

            holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.logo_png));

        } else if (alldata.get(position).getType().equals("chat")) {
            holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_message_chat));
            holder.title.setText(context.getResources().getString(R.string.not2));

        } else if (alldata.get(position).getType().equals("fund_request")) {
            holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.logo_png));
            holder.title.setText(context.getResources().getString(R.string.not3));

        } else if (alldata.get(position).getType().equals("fund_offer")) {
            holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.logo_png));
            holder.title.setText(context.getResources().getString(R.string.not4));

        } else if (alldata.get(position).getType().equals("offer")) {
            holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.logo_png));
            holder.title.setText(context.getResources().getString(R.string.not5));

        } else if (alldata.get(position).getType().equals("rate_offer")) {
            holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.star_w));
            holder.title.setText(context.getResources().getString(R.string.not6));

        } else if (alldata.get(position).getType().equals("rate_estate")) {
            holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.star_w));
            holder.title.setText(context.getResources().getString(R.string.not7));

        } else if (alldata.get(position).getType().equals("employee")) {
            holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_user_menu));
            holder.title.setText(context.getResources().getString(R.string.not8));

        } else if (alldata.get(position).getType().equals("estate")) {
            holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_home1122));
            holder.title.setText(context.getResources().getString(R.string.not9));

        } else {
            holder.title.setText(alldata.get(position).getType());

        }


//        }
////
//        holder.text.setText(alldata.get(position).getName() + "");

//
//        if (alldata.get(position).get_is_selected()) {
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
//
//        System.out.println(alldata.get(position).getImage() + "");
//        Glide.with(context).load(alldata.get(position).getIcon()).into(holder.image_in_type);
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

//


//                if (mItemClickListener != null) {
//                    mItemClickListener.onItemClick(position);
//                }
//
//                if (alldata.get(position).get_is_selected()) {
//                    alldata.get(position).setIs_selected(false);
//                    alldata.get(position).setIs_selected(true);
//                    holder.back_ground.setBackground(context.getResources().getDrawable(R.drawable.search_background));
//
//                    holder.text.setTextColor(context.getResources().getColor(R.color.textColor));
//                    holder.image_in_type.setColorFilter(ContextCompat.getColor(context, R.color.textColor), android.graphics.PorterDuff.Mode.MULTIPLY);
//
//                } else {
//                    System.out.println("klklkl"+alldata.get(position).get_is_selected());
//
//
//                    holder.back_ground.setBackground(context.getResources().getDrawable(R.drawable.button_login));
//
//                    holder.text.setTextColor(context.getResources().getColor(R.color.white));
//
//
//                    holder.image_in_type.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
//
//                }


//
//                if (alldata.get(position).get_is_selected()) {
//
//                } else {
//
//                }

//                Refr();

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


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notfication, parent, false);


        // Fresco.initialize(context);


        return new MyViewHolder(v);
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int position);
    }
}
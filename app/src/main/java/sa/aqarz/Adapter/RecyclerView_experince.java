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

import sa.aqarz.Modules.SettingsModules;
import sa.aqarz.R;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_experince extends RecyclerView.Adapter<RecyclerView_experince.MyViewHolder> {
    public static List<SettingsModules.service_types> alldata = new ArrayList<SettingsModules.service_types>();
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

        LinearLayout select;
        //        TextView opration;
//        TextView price;
//        TextView address;
        ImageView icon;

        TextView text;

        public MyViewHolder(View view) {
            super(view);
            //  title_cared_product_rec = (TextView) view.findViewById(R.id.title_cared_product_rec);

//            image = view.findViewById(R.id.image);
//            opration = view.findViewById(R.id.opration);
//            price = view.findViewById(R.id.price);
//            address = view.findViewById(R.id.address);
            text = view.findViewById(R.id.text);
            select = view.findViewById(R.id.select);
            icon = view.findViewById(R.id.icon);


        }
    }

    public RecyclerView_experince(Context context, List<SettingsModules.service_types> alldata) {
        RecyclerView_experince.alldata = alldata;
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
//        holder.price.setText(alldata.get(position).getTotalPrice());
//        holder.opration.setText(alldata.get(position).getEstate_type_name());
//        holder.address.setText(alldata.get(position).getInterface());
//        if (alldata.get(position).getRate() != null) {
//            if (!alldata.get(position).getRate().equals("null")) {
//
//                holder.ratingbar.setStar(Integer.valueOf(alldata.get(position).getRate()));
//
//
//            }
//        }
////
        holder.text.setText(alldata.get(position).getName() + "");
        holder.icon.setVisibility(View.GONE);
//        Picasso.get().load(alldata.get(position).getIcon()).into(holder.icon);

        if (alldata.get(position).isChecked()) {
            holder.select.setBackground(context.getResources().getDrawable(R.drawable.circle_pr));
            holder.text.setTextColor(context.getResources().getColor(R.color.black));
        } else {
            holder.select.setBackground(context.getResources().getDrawable(R.drawable.mash));
            holder.text.setTextColor(context.getResources().getColor(R.color.black));

        }


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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//
//


                if (alldata.get(position).isChecked()) {

                    holder.select.setBackground(context.getResources().getDrawable(R.drawable.mash));
                    holder.text.setTextColor(context.getResources().getColor(R.color.black));

                    alldata.get(position).setChecked(false);
                } else {
                    holder.select.setBackground(context.getResources().getDrawable(R.drawable.circle_pr));
                    holder.text.setTextColor(context.getResources().getColor(R.color.black));

                    alldata.get(position).setChecked(true);
                }


                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(alldata);
                }
////                Postion_opend = position;
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


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_meember2, parent, false);


        // Fresco.initialize(context);


        return new MyViewHolder(v);
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(List<SettingsModules.service_types> service_types);
    }
}
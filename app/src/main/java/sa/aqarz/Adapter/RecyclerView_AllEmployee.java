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

import de.hdodenhof.circleimageview.CircleImageView;
import sa.aqarz.Modules.AllEmployee;
import sa.aqarz.Modules.ComfortModules;
import sa.aqarz.Modules.User;
import sa.aqarz.R;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_AllEmployee extends RecyclerView.Adapter<RecyclerView_AllEmployee.MyViewHolder> {
    public static List<AllEmployee> alldata = new ArrayList<AllEmployee>();
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

        LinearLayout back_ground;
        TextView name;
        TextView email;
        TextView mobile;
        TextView aqarez;
        TextView real_order;
        TextView calling;
        TextView market;
        TextView Enabled;
        TextView notEnabled;
        ImageView image;

        public MyViewHolder(View view) {
            super(view);
            //  title_cared_product_rec = (TextView) view.findViewById(R.id.title_cared_product_rec);


            back_ground = view.findViewById(R.id.back_ground);
            name = view.findViewById(R.id.name);
            Enabled = view.findViewById(R.id.Enabled);
            notEnabled = view.findViewById(R.id.notEnabled);
            email = view.findViewById(R.id.email);
            mobile = view.findViewById(R.id.mobile);
            aqarez = view.findViewById(R.id.aqarez);
            real_order = view.findViewById(R.id.real_order);
            calling = view.findViewById(R.id.calling);
            market = view.findViewById(R.id.market);
            image = view.findViewById(R.id.image);
////            simpleRatingBar = view.findViewById(R.id.simpleRatingBar);

        }
    }

    public RecyclerView_AllEmployee(Context context, List<AllEmployee> alldata) {
        RecyclerView_AllEmployee.alldata = alldata;
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
        holder.name.setText(alldata.get(position).getEmp_name() + "");

        holder.mobile.setText(alldata.get(position).getEmp_mobile() + "");


        if (alldata.get(position).getUser() != null) {

            holder.aqarez.setText(alldata.get(position).getUser().getCount_offer() + "");
            holder.real_order.setText(alldata.get(position).getUser().getCount_request() + "");
            holder.market.setText(alldata.get(position).getUser().getCount_request() + "");
            holder.calling.setText(alldata.get(position).getUser().getCount_visit() + "");
            Picasso.get().load(alldata.get(position).getUser().getLogo() + "").into(holder.image);
            holder.notEnabled.setVisibility(View.GONE);
            holder.Enabled.setVisibility(View.VISIBLE);
        } else {
            holder.notEnabled.setVisibility(View.VISIBLE);
            holder.Enabled.setVisibility(View.GONE);
        }


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


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_employee, parent, false);


        // Fresco.initialize(context);


        return new MyViewHolder(v);
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int position);
    }
}
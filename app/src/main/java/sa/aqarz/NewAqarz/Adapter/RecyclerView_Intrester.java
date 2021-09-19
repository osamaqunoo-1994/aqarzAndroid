package sa.aqarz.NewAqarz.Adapter;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.MyInterestsActivity;
import sa.aqarz.Adapter.RecyclerView_select_neb;
import sa.aqarz.Modules.AllCityModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.NewAqarz.AddIntrestedMapActivity;
import sa.aqarz.NewAqarz.IntrestedActivity;
import sa.aqarz.R;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_Intrester extends RecyclerView.Adapter<RecyclerView_Intrester.MyViewHolder> {
    public static List<AllCityModules.City> alldata = new ArrayList<AllCityModules.City>();
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

        //        LinearLayout back_ground;
//        TextView text;
//        ImageView image_in_type;
//        ImageView selected;
        TextView text_;
        RecyclerView all_nib_selected;
        ImageView delete;
        ImageView edit;
        LinearLayout add_more;

        public MyViewHolder(View view) {
            super(view);
            //  title_cared_product_rec = (TextView) view.findViewById(R.id.title_cared_product_rec);


//            back_ground = view.findViewById(R.id.back_ground);
//            text = view.findViewById(R.id.text);
//            image_in_type = view.findViewById(R.id.image_in_type);
            text_ = view.findViewById(R.id.text_);
            all_nib_selected = view.findViewById(R.id.all_nib_selected);
            add_more = view.findViewById(R.id.add_more);
            edit = view.findViewById(R.id.edit);
            delete = view.findViewById(R.id.delete);
////            simpleRatingBar = view.findViewById(R.id.simpleRatingBar);

        }
    }

    public RecyclerView_Intrester(Context context, List<AllCityModules.City> alldata) {
        RecyclerView_Intrester.alldata = alldata;
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
        holder.text_.setText(alldata.get(position).getNameAr() + "");


        holder.all_nib_selected.setLayoutManager(new GridLayoutManager(context, 3));
        RecyclerView_select_neb recyclerView_select_neb = new RecyclerView_select_neb(context, alldata.get(position).getNeb());


        holder.all_nib_selected.setAdapter(recyclerView_select_neb);
//        holder.image_in_type.setVisibility(View.GONE);

//        try {
////            System.out.println("#$#$#$#"+alldata.get(position).getIcon());
//            Glide.with(context).load(alldata.get(position).getIcon() + "").into(holder.image_in_type);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        if (alldata.get(position).isIsselected()) {
//            holder.back_ground.setBackground(context.getResources().getDrawable(R.drawable.border_fillter_fill));
//
//            holder.text.setTextColor(context.getResources().getColor(R.color.white));
//
////            holder.image_in_type.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
////            holder.selected.setVisibility(View.VISIBLE);
//        } else {
//            holder.back_ground.setBackground(context.getResources().getDrawable(R.drawable.border_fillter));
//
//            holder.text.setTextColor(context.getResources().getColor(R.color.color_filter));
////            holder.image_in_type.setColorFilter(ContextCompat.getColor(context, R.color.color_filter), android.graphics.PorterDuff.Mode.SRC_IN);
////            holder.selected.setVisibility(View.GONE);
//
//        }
//
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

//

//                Postion_opend = position;


//                alldata.get(position).setIsselected(!alldata.get(position).isIsselected());
//
//
//                if (mItemClickListener != null) {
//                    mItemClickListener.onItemClick(alldata);
//                }
//                Refr();

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//

//                Postion_opend = position;


//                alldata.get(position).setIsselected(!alldata.get(position).isIsselected());
//
//
//                if (mItemClickListener != null) {
//                    mItemClickListener.onItemClick(alldata);
//                }
                alldata.remove(position);
                Refr();

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//

//                Postion_opend = position;
//                Intent intent = new Intent(context, MyInterestsActivity.class);
                Intent intent = new Intent(context, AddIntrestedMapActivity.class);
//              intent.putExtra("from", "splash");
                intent.putExtra("type", "all");
                intent.putExtra("id_user", "--");
                intent.putExtra("id_city", alldata.get(position).getCityId()+"");
                intent.putExtra("name_city", alldata.get(position).getNameAr()+"");

                context.startActivity(intent);

//                alldata.get(position).setIsselected(!alldata.get(position).isIsselected());
//
//
//                if (mItemClickListener != null) {
//                    mItemClickListener.onItemClick(alldata);
//                }
//                Refr();

            }
        });

        holder.add_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//
                Intent intent = new Intent(context, AddIntrestedMapActivity.class);
//              intent.putExtra("from", "splash");
                intent.putExtra("type", "all");
                intent.putExtra("id_user", "--");
                intent.putExtra("id_city", alldata.get(position).getCityId()+"");
                intent.putExtra("name_city", alldata.get(position).getNameAr()+"");

                context.startActivity(intent);
//                Postion_opend = position;


//                alldata.get(position).setIsselected(!alldata.get(position).isIsselected());
//
//
//                if (mItemClickListener != null) {
//                    mItemClickListener.onItemClick(alldata);
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


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_intrested, parent, false);


        // Fresco.initialize(context);


        return new MyViewHolder(v);
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(List<TypeModules> typeModules);
    }
}
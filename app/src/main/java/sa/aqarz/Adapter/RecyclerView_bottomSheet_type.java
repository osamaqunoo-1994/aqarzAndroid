package sa.aqarz.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Modules.select_typeModules;
import sa.aqarz.R;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_bottomSheet_type extends RecyclerView.Adapter<RecyclerView_bottomSheet_type.MyViewHolder> {
    public static List<select_typeModules> alldata = new ArrayList<select_typeModules>();
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

        TextView text;
        LinearLayout back_ground;

        public MyViewHolder(View view) {
            super(view);
            //  title_cared_product_rec = (TextView) view.findViewById(R.id.title_cared_product_rec);


            text = view.findViewById(R.id.text);
            back_ground = view.findViewById(R.id.back_ground);


        }
    }

    public RecyclerView_bottomSheet_type(Context context, List<select_typeModules> alldata) {
        RecyclerView_bottomSheet_type.alldata = alldata;
        this.context = context;
    }

    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    @SuppressLint("RestrictedApi")
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
        if (alldata.get(position).getSelected()) {
            holder.back_ground.setBackground(context.getResources().getDrawable(R.drawable.button_login));
            holder.text.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            holder.back_ground.setBackground(context.getResources().getDrawable(R.drawable.mash));
            holder.text.setTextColor(context.getResources().getColor(R.color.textColor));


        }
//
//
//          if (position == 0) {
//                ColorStateList colorStateList = new ColorStateList(
//                        new int[][]{
//                                new int[]{android.R.attr.state_enabled} //enabled
//                    },
//                    new int[]{context.getResources().getColor(R.color.black)}
//            );
//            holder.radio_button.setSupportButtonTintList(colorStateList);
//
//        } else if (position == 1) {
//            ColorStateList colorStateList = new ColorStateList(
//                    new int[][]{
//                            new int[]{android.R.attr.state_enabled} //enabled
//                    },
//                    new int[]{context.getResources().getColor(R.color.blue)}
//            );
//            holder.radio_button.setSupportButtonTintList(colorStateList);
//
//        } else if (position == 2) {
//            ColorStateList colorStateList = new ColorStateList(
//                    new int[][]{
//                            new int[]{android.R.attr.state_enabled} //enabled
//                    },
//                    new int[]{context.getResources().getColor(R.color.colorPrimary)}
//            );
//            holder.radio_button.setSupportButtonTintList(colorStateList);
//
//        } else if (position == 3) {
//            ColorStateList colorStateList = new ColorStateList(
//                    new int[][]{
//                            new int[]{android.R.attr.state_enabled} //enabled
//                    },
//                    new int[]{context.getResources().getColor(R.color.red_btn_bg_color)}
//            );
//            holder.radio_button.setSupportButtonTintList(colorStateList);
//
//        }

        holder.text.setText(alldata.get(position).getName() + "");


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

//        holder.radio_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//
////
//
//
////                Postion_opend = position;
////                Refr();
//            }
//        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (alldata.get(position).getSelected()) {
                    holder.back_ground.setBackground(context.getResources().getDrawable(R.drawable.circle));

                    holder.text.setTextColor(context.getResources().getColor(R.color.textColor));

                    alldata.get(position).setSelected(false);


                } else {
                    holder.back_ground.setBackground(context.getResources().getDrawable(R.drawable.button_login));
                    holder.text.setTextColor(context.getResources().getColor(R.color.white));

                    alldata.get(position).setSelected(true);


                }


                for (int i = 0; i < alldata.size(); i++) {
                    alldata.get(i).setSelected(alldata.get(i).getId() == alldata.get(position).getId());
                }


                Refr();

                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(alldata);
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


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_type_bottom_sheet, parent, false);


        // Fresco.initialize(context);


        return new MyViewHolder(v);
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(List<select_typeModules> alldata);
    }
}
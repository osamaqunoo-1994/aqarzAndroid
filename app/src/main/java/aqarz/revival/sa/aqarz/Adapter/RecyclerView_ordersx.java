package aqarz.revival.sa.aqarz.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_MyEstate;
import aqarz.revival.sa.aqarz.Modules.OrdersModules;
import aqarz.revival.sa.aqarz.R;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_ordersx extends RecyclerView.Adapter<RecyclerView_ordersx.MyViewHolder> {
    public static List<OrdersModules> alldata = new ArrayList<OrdersModules>();
    static int Postion_opend = 0;


    static AlertDialog alertDialog;
    private ItemClickListener mItemClickListener;

    static BottomSheetDialogFragment_MyEstate bottomSheetDialogFragment_myEstate;
    /**
     * View holder class
     */
    static Context context;

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
        TextView new_offer;
        TextView date;
        ImageView image_icon;

        public MyViewHolder(View view) {
            super(view);
            //  title_cared_product_rec = (TextView) view.findViewById(R.id.title_cared_product_rec);


            price = view.findViewById(R.id.price);
            name_estate = view.findViewById(R.id.name_estate);
            address = view.findViewById(R.id.address);
            space = view.findViewById(R.id.space);
            view_type = view.findViewById(R.id.view_type);
            new_offer = view.findViewById(R.id.new_offer);
            image_icon = view.findViewById(R.id.image_icon);
            date = view.findViewById(R.id.date);
//            ratingbar = view.findViewById(R.id.ratingbar);
////            simpleRatingBar = view.findViewById(R.id.simpleRatingBar);

        }
    }

    public RecyclerView_ordersx(Context context, List<OrdersModules> alldata) {
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


        holder.price.setText(alldata.get(position).getEstatePriceRange());
        holder.view_type.setText(alldata.get(position).getDirEstate());
        holder.space.setText(alldata.get(position).getStreetViewRange());
        holder.name_estate.setText(alldata.get(position).getEstateTypeName());
        holder.date.setText(alldata.get(position).getCreated_at());
        holder.address.setText(alldata.get(position).getCityName() + " , " + alldata.get(position).getNeighborhoodName());


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
        holder.new_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialogFragment_myEstate = new BottomSheetDialogFragment_MyEstate(alldata.get(position).getUuid() + "");

                bottomSheetDialogFragment_myEstate.show(((FragmentActivity) context).getSupportFragmentManager(), "");


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


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ordersx, parent, false);


        // Fresco.initialize(context);


        return new MyViewHolder(v);
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public static void send_done() {


        bottomSheetDialogFragment_myEstate.dismiss();

        BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(context);
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View parentView = li.inflate(R.layout.success_sandoq, null);
//        Button close = parentView.findViewById(R.id.close);
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                                    finish();
//            }
//        });
        bottomSheerDialog.setContentView(parentView);


        Window window = bottomSheerDialog.getWindow();
        window.findViewById(com.google.android.material.R.id.container).setFitsSystemWindows(false);
        // dark navigation bar icons
        View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, context.getResources().getDisplayMetrics());


//        ((View) decorView.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));


        bottomSheerDialog.show();
    }
}
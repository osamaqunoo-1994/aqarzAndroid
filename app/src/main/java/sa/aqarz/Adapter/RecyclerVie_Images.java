package sa.aqarz.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sa.aqarz.Activity.FullScreenImageActivity;
import sa.aqarz.Modules.SettingsModules;
import sa.aqarz.Modules.imagemodules;
import sa.aqarz.R;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerVie_Images extends RecyclerView.Adapter<RecyclerVie_Images.MyViewHolder> {
    public static List<imagemodules> alldata = new ArrayList<imagemodules>();
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
        ImageView image;
        ImageView play_video;
        ProgressBar progress;

        public MyViewHolder(View view) {
            super(view);
            //  title_cared_product_rec = (TextView) view.findViewById(R.id.title_cared_product_rec);

            image = view.findViewById(R.id.image);
            play_video = view.findViewById(R.id.play_video);
            progress = view.findViewById(R.id.progress);
        }
    }

    public RecyclerVie_Images(Context context, List<imagemodules> alldata) {
        RecyclerVie_Images.alldata = alldata;
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
        if (alldata.get(position).getType().equals("image")) {
            Glide.with(context).load(alldata.get(position).getImage_url() + "").into(holder.image);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent fullImageIntent = new Intent(context, FullScreenImageActivity.class);
                    fullImageIntent.putExtra("position", position + "");
                    context.startActivity(fullImageIntent);
                }
            });
            holder.play_video.setVisibility(View.GONE);

        } else {
            holder.progress.setVisibility(View.GONE);
            holder.play_video.setVisibility(View.VISIBLE);
            holder.play_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent playVideo = new Intent(Intent.ACTION_VIEW);
                    playVideo.setDataAndType(Uri.parse(alldata.get(position).getImage_url()), "video/mp4");
                    context.startActivity(playVideo);
                }
            });

//            long thumb = view.getLayoutPosition()*1000;
            try {
                Bitmap bitmap = retriveVideoFrameFromVideo(alldata.get(position).getImage_url() + "");
                if (bitmap != null) {
                    holder.image.setImageBitmap(bitmap);
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
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
//
//
//                if (alldata.get(position).isChecked()) {
//
//                    holder.select.setBackground(context.getResources().getDrawable(R.drawable.mash));
//                    holder.text.setTextColor(context.getResources().getColor(R.color.black));
//
//                    alldata.get(position).setChecked(false);
//                } else {
//                    holder.select.setBackground(context.getResources().getDrawable(R.drawable.circle_pr));
//                    holder.text.setTextColor(context.getResources().getColor(R.color.black));
//
//                    alldata.get(position).setChecked(true);
//                }
//
//
//                if (mItemClickListener != null) {
//                    mItemClickListener.onItemClick(alldata);
//                }
//////                Postion_opend = position;
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


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_viewpager_home, parent, false);


        // Fresco.initialize(context);


        return new MyViewHolder(v);
    }

    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(List<SettingsModules.service_types> service_types);
    }

    public static Bitmap retriveVideoFrameFromVideo(String videoPath) throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.getMessage());

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }
}
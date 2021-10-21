package sa.aqarz.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.github.marlonlom.utilities.timeago.TimeAgoMessages;
import com.orhanobut.hawk.Hawk;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import sa.aqarz.Modules.MsgModules;
import sa.aqarz.R;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_ChatRoom extends RecyclerView.Adapter<RecyclerView_ChatRoom.MyViewHolder> {
    public static List<MsgModules> alldata = new ArrayList<MsgModules>();


    AlertDialog alertDialog;

    /**
     * View holder class
     */
    Context context;

    public void load_more(List<MsgModules> alldatas) {

        alldata = alldatas;


        this.notifyDataSetChanged();

    }
    public void Ref() {



        this.notifyDataSetChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView text;


        public MyViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.text);
            date = view.findViewById(R.id.date);
//            category = view.findViewById(R.id.category);
//            image = view.findViewById(R.id.image);
//            date = view.findViewById(R.id.date);
//            viewx = view.findViewById(R.id.view);
//            progressImage = view.findViewById(R.id.progressImage);
//            share = view.findViewById(R.id.share);

        }
    }

    public RecyclerView_ChatRoom(Context context, List<MsgModules> alldata) {
        RecyclerView_ChatRoom.alldata = alldata;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        if (position == alldata.size()) {

        } else {


            holder.text.setText(alldata.get(position).getBody() + "");


            try {

//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//
//                String dateInStrings = alldata.get(position).getCreatedAt() + "";
//                String dateInString = dateInStrings.substring(0, 19);
//
//                SimpleDateFormat formatterOut = new SimpleDateFormat("dd MMM");
//
//                Date date = formatter.parse(dateInString);
//                holder.date.setText(formatterOut.format(date));

                String str_date = alldata.get(position).getCreatedAt() + "";

                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//2021-09-09 01:01:23
                Date date = formatter.parse(str_date);
                System.out.println("Today is " + date.getTime());


                Locale LocaleBylanguageTag;
                if (Hawk.get("lang").toString().equals("ar")) {
                    LocaleBylanguageTag = Locale.forLanguageTag("ar");

                } else {
                    LocaleBylanguageTag = Locale.forLanguageTag("en");

                }
                TimeAgoMessages messages = new TimeAgoMessages.Builder().withLocale(LocaleBylanguageTag).build();

                String text = TimeAgo.using(date.getTime(), messages);

                holder.date.setText(text + "");


            } catch (Exception e) {
                e.printStackTrace();
            }
//            holder.share.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    /*Create an ACTION_SEND Intent*/
//                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    /*This will be the actual content you wish you share.*/
//                    String shareBody = alldata.get(position).getDescription() + " - " + context.getResources().getString(R.string.app_name);
//                    /*The type of the content is text, obviously.*/
//                    intent.setType("text/plain");
//                    /*Applying information Subject and Body.*/
//                    intent.putExtra(Intent.EXTRA_SUBJECT, "title");
//                    intent.putExtra(Intent.EXTRA_TEXT, shareBody);
//                    /*Fire!*/
//                    context.startActivity(Intent.createChooser(intent, "Share"));
//                }
//            });
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }


    @Override
    public int getItemCount() {
        return alldata.size();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == 0) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_me, parent, false);

        } else if (viewType == 1) {


            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_other, parent, false);

        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_other, parent, false);

        }


        return new MyViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {

        try {
            if (alldata.get(position).getFormMe() != null) {
                if (alldata.get(position).getFormMe() == 1) {
                    return 0;

                } else if (position == 0) {
                    return 1;

                } else {
                    return 1;

                }
            } else {
                return 1;

            }

        } catch (Exception e) {
            return 1;

        }

    }
}
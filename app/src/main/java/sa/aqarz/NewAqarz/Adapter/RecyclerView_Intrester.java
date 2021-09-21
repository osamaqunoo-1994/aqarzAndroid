package sa.aqarz.NewAqarz.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
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

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.MyInterestsActivity;
import sa.aqarz.Adapter.RecyclerView_select_neb;
import sa.aqarz.Modules.AllCityListxx;
import sa.aqarz.Modules.AllCityModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.NewAqarz.AddIntrestedMapActivity;
import sa.aqarz.NewAqarz.IntrestedActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_Intrester extends RecyclerView.Adapter<RecyclerView_Intrester.MyViewHolder> {
    public static List<AllCityModules.City> alldata = new ArrayList<AllCityModules.City>();
    static int Postion_opend = 0;


    static AlertDialog alertDialog;
    private ItemClickListener mItemClickListener;
    static IResult mResultCallback;


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
        holder.text_.setTag(position + "");

        holder.all_nib_selected.setLayoutManager(new GridLayoutManager(context, 3));
        RecyclerView_select_neb recyclerView_select_neb = new RecyclerView_select_neb(context, alldata.get(position).getNeb());
        recyclerView_select_neb.addItemClickListener(new RecyclerView_select_neb.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

                IntrestedActivity.dataCities.get(Integer.valueOf(holder.text_.getTag().toString())).getNeb().remove(position);
                IntrestedActivity.recyclerView_intrester.Refr();


//                WebService.loading((Activity) context, true);
//                init_volley();
//                VolleyService mVolleyService = new VolleyService(mResultCallback, context);
//        url_list = WebService.home_estate_custom_list + "?" + type_filtter_;
//                mVolleyService.getAsync("my_interest", WebService.my_interest);

            }
        });

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
                intent.putExtra("id_city", alldata.get(position).getCityId() + "");
                intent.putExtra("name_city", alldata.get(position).getNameAr() + "");

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
                intent.putExtra("id_city", alldata.get(position).getCityId() + "");
                intent.putExtra("name_city", alldata.get(position).getNameAr() + "");

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

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    WebService.loading((Activity) context, false);

                    boolean status = response.getBoolean("status");
                    if (status) {
                        WebService.loading((Activity) context, false);

                        if (requestType.equals("my_interest")) {


//                            set_locationEstate(allNeigbers.getData().getData());
//                            all_estate_size.setVisibility(View.VISIBLE);


                        } else {
                            String message = response.getString("message");
                            WebService.Make_Toast_color((Activity) context, message, "success");

                        }


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color((Activity) context, message, "error");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + "That didn't work!" + error.getMessage());

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color((Activity) context, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }


                WebService.loading((Activity) context, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

                WebService.loading((Activity) context, false);

            }
        }

        ;


    }

}
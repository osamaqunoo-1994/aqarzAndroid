package sa.aqarz.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.Auth.EditProfileActivity;
import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Activity.RealState.AllOfferOrderActivity;
import sa.aqarz.Activity.RealState.MyOfferOrderActivity;
import sa.aqarz.Dialog.BottomSheetDialogFragment_MyEstate;
import sa.aqarz.Dialog.BottomSheetDialogFragment_OfferEstate;
import sa.aqarz.Dialog.BottomSheetDialogFragment_details_real_state;
import sa.aqarz.Modules.OrdersModules;
import sa.aqarz.Modules.fund_favorite;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


/**
 * Created by osama on 10/16/2017.
 */
public class RecyclerView_ordersx_favorite extends RecyclerView.Adapter<RecyclerView_ordersx_favorite.MyViewHolder> {
    public static List<fund_favorite> alldata = new ArrayList<fund_favorite>();
    static int Postion_opend = 0;

    IResult mResultCallback;

    static AlertDialog alertDialog;
    private ItemClickListener mItemClickListener;

    static BottomSheetDialogFragment_MyEstate bottomSheetDialogFragment_myEstate;
    BottomSheetDialogFragment_OfferEstate bottomSheetDialogFragment_offerEstate;
    /**
     * View holder class
     */
    static Context context;
    static BottomSheetDialog bottomSheerDialog;

    public void Refr() {


        this.notifyDataSetChanged();
    }

    public void loadmore(List<fund_favorite> alldatas) {


        alldata.addAll(alldata.size() - 1, alldatas);

        System.out.println("this.alldata" + alldatas.size());
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
        TextView Watting;
        TextView reject;
        TextView date;
        TextView accept;
        TextView Street_view;
        TextView number_id;
        TextView sending_code;
        ImageView image_icon;
        ImageView share;
        ImageView add_favorite;
        TextView more_details;

        public MyViewHolder(View view) {
            super(view);
            //  title_cared_product_rec = (TextView) view.findViewById(R.id.title_cared_product_rec);


            price = view.findViewById(R.id.price);
            name_estate = view.findViewById(R.id.name_estate);
            address = view.findViewById(R.id.address);
            space = view.findViewById(R.id.space);
            number_id = view.findViewById(R.id.number_id);
            view_type = view.findViewById(R.id.view_type);
            Watting = view.findViewById(R.id.Watting);
            new_offer = view.findViewById(R.id.new_offer);
            image_icon = view.findViewById(R.id.image_icon);
            date = view.findViewById(R.id.date);
            add_favorite = view.findViewById(R.id.add_favorite);
            reject = view.findViewById(R.id.reject);
            accept = view.findViewById(R.id.accept);
            Street_view = view.findViewById(R.id.Street_view);
            sending_code = view.findViewById(R.id.sending_code);
            more_details = view.findViewById(R.id.more_details);
            share = view.findViewById(R.id.share);
//            ratingbar = view.findViewById(R.id.ratingbar);
////            simpleRatingBar = view.findViewById(R.id.simpleRatingBar);

        }
    }

    public RecyclerView_ordersx_favorite(Context context, List<fund_favorite> alldata) {
        RecyclerView_ordersx_favorite.alldata = alldata;
        RecyclerView_ordersx_favorite.context = context;
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
        try {


            holder.number_id.setText("#" + alldata.get(position).getId() + "");
            holder.price.setText(alldata.get(position).getEstate().get(0).getEstatePriceRange() + "");
            holder.view_type.setText(alldata.get(position).getEstate().get(0).getDirEstate() + "");
            holder.space.setText(alldata.get(position).getEstate().get(0).getStreetViewRange() + "");
            holder.name_estate.setText(alldata.get(position).getEstate().get(0).getEstateTypeName() + "");
            holder.date.setText(alldata.get(position).getEstate().get(0).getCreated_at() + "");
            holder.Street_view.setText(alldata.get(position).getEstate().get(0).getStreetViewRange() + "");
            holder.address.setText(alldata.get(position).getEstate().get(0).getCityName() + " , " + alldata.get(position).getEstate().get(0).getNeighborhoodName());

            Glide.with(context).load(alldata.get(position).getEstate().get(0).getEstateTypeIcon()).into(holder.image_icon);


            holder.new_offer.setVisibility(View.GONE);
            holder.Watting.setVisibility(View.GONE);
            holder.reject.setVisibility(View.GONE);
            holder.accept.setVisibility(View.GONE);


            if (alldata.get(position).getEstate().get(0).getHas_my_offer().equals("0")) {
                holder.new_offer.setVisibility(View.VISIBLE);
                holder.Watting.setVisibility(View.GONE);
                holder.reject.setVisibility(View.GONE);
                holder.accept.setVisibility(View.GONE);

            } else {
                holder.new_offer.setVisibility(View.GONE);
                holder.Watting.setVisibility(View.VISIBLE);
                holder.reject.setVisibility(View.GONE);
                holder.accept.setVisibility(View.GONE);
                if (alldata.get(position).getEstate().get(0).getStatus() == null) {

                } else if (alldata.get(position).getEstate().get(0).getStatus().equals("active")) {
//
                } else if (alldata.get(position).getEstate().get(0).getStatus().equals("Waiting_provider_accepted")) {
//
                } else if (alldata.get(position).getEstate().get(0).getStatus().equals("rejected_customer ")) {
                    holder.new_offer.setVisibility(View.GONE);
                    holder.Watting.setVisibility(View.GONE);
                    holder.reject.setVisibility(View.VISIBLE);
                    holder.accept.setVisibility(View.GONE);
                } else if (alldata.get(position).getEstate().get(0).getStatus().equals("accepted_customer")) {
                    holder.new_offer.setVisibility(View.GONE);
                    holder.Watting.setVisibility(View.GONE);
                    holder.reject.setVisibility(View.GONE);
                    holder.sending_code.setVisibility(View.GONE);
                    holder.accept.setVisibility(View.VISIBLE);
                } else if (alldata.get(position).getEstate().get(0).getStatus().equals("sending_code")) {
                    holder.new_offer.setVisibility(View.GONE);
                    holder.Watting.setVisibility(View.GONE);
                    holder.reject.setVisibility(View.GONE);
                    holder.accept.setVisibility(View.GONE);
                    holder.sending_code.setVisibility(View.VISIBLE);
                }
            }


//
//        System.out.println(alldata.get(position).getImage() + "");
//        Glide.with(context).load(alldata.get(position).getes()).into(holder.image_icon);
////
//
//        try {
//
//
////
//        System.out.println(alldata.get(position).getImage() + "");
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

            holder.more_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    BottomSheetDialogFragment_details_real_state bottomSheetDialogFragment_details_real_state = new BottomSheetDialogFragment_details_real_state(alldata.get(position).getEstate().get(0));
                    bottomSheetDialogFragment_details_real_state.show(((FragmentActivity) context).getSupportFragmentManager(), "");

//                Postion_opend = position;
//                Refr();

//                RequestOrderActivity.set_fragment(position);


//                if (mItemClickListener != null) {
//                    mItemClickListener.onItemClick(position);
//                }
////

                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


//                Postion_opend = position;
//                Refr();

//                RequestOrderActivity.set_fragment(position);


//                if (mItemClickListener != null) {
//                    mItemClickListener.onItemClick(position);
//                }
////

                }
            });
            holder.new_offer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (Settings.CheckIsCompleate()) {


                        if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().equals("1")) {

//                        bottomSheetDialogFragment_myEstate = new BottomSheetDialogFragment_MyEstate(alldata.get(position).getUuid() + "");
//                        bottomSheetDialogFragment_myEstate.show(((FragmentActivity) context).getSupportFragmentManager(), "");


                            MainActivity.ordersModules = alldata.get(position).getEstate().get(0);
                            Intent intent = new Intent(context, AllOfferOrderActivity.class);
                            intent.putExtra("getUuid", alldata.get(position).getEstate().get(0).getUuid() + "");
                            intent.putExtra("id", alldata.get(position).getId() + "");
                            context.startActivity(intent);
//                        overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);


                        } else {
                            show_dialog();
//

                        }
                    } else {
                        Settings.Dialog_not_compleate((Activity) context);

                    }


                }
            });
            holder.Watting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (Settings.CheckIsCompleate()) {


                        if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().equals("1")) {

//                        bottomSheetDialogFragment_offerEstate = new BottomSheetDialogFragment_OfferEstate(alldata.get(position).getUuid() + "");
//                        bottomSheetDialogFragment_offerEstate.show(((FragmentActivity) context).getSupportFragmentManager(), "");
                            MainActivity.ordersModules = alldata.get(position).getEstate().get(0);

                            Intent intent = new Intent(context, MyOfferOrderActivity.class);
                            intent.putExtra("getUuid", alldata.get(position).getEstate().get(0).getUuid() + "");
                            intent.putExtra("id", alldata.get(position).getId() + "");
                            context.startActivity(intent);
//                        overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

                        } else {
                            show_dialog();
//

                        }
                    } else {
                        Settings.Dialog_not_compleate((Activity) context);

                    }


                }
            });
            holder.sending_code.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (Settings.CheckIsCompleate()) {


                        if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().equals("1")) {

//                        bottomSheetDialogFragment_offerEstate = new BottomSheetDialogFragment_OfferEstate(alldata.get(position).getUuid() + "");
//                        bottomSheetDialogFragment_offerEstate.show(((FragmentActivity) context).getSupportFragmentManager(), "");
                            MainActivity.ordersModules = alldata.get(position).getEstate().get(0);

                            Intent intent = new Intent(context, MyOfferOrderActivity.class);
                            intent.putExtra("getUuid", alldata.get(position).getEstate().get(0).getUuid() + "");
                            intent.putExtra("id", alldata.get(position).getId() + "");
                            context.startActivity(intent);
//                        overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

                        } else {
                            show_dialog();
//

                        }
                    } else {
                        Settings.Dialog_not_compleate((Activity) context);

                    }


                }
            });
            holder.accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (Settings.CheckIsCompleate()) {


                        if (Settings.GetUser().getIs_pay() != null && Settings.GetUser().getIs_pay().equals("1")) {

//                        bottomSheetDialogFragment_offerEstate = new BottomSheetDialogFragment_OfferEstate(alldata.get(position).getUuid() + "");
//                        bottomSheetDialogFragment_offerEstate.show(((FragmentActivity) context).getSupportFragmentManager(), "");
                            MainActivity.ordersModules = alldata.get(position).getEstate().get(0);

                            Intent intent = new Intent(context, MyOfferOrderActivity.class);
                            intent.putExtra("getUuid", alldata.get(position).getEstate().get(0).getUuid() + "");
                            intent.putExtra("id", alldata.get(position).getId() + "");
                            context.startActivity(intent);
//                        overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

                        } else {
                            show_dialog();
//

                        }
                    } else {
                        Settings.Dialog_not_compleate((Activity) context);

                    }


                }
            });
            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String url = alldata.get(position).getEstate().get(0).getLink();


                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
                    i.putExtra(Intent.EXTRA_TEXT, url + "");
                    context.startActivity(Intent.createChooser(i, "Share URL"));


                }
            });

            if (alldata.get(position).getEstate().get(0).getIn_fav().equals("1")) {
                holder.add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_heart));

            } else {
                holder.add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_like));

            }
            holder.add_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (alldata.get(position).getEstate().get(0).getIn_fav().equals("1")) {
                        holder.add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_like));
                        alldata.get(position).getEstate().get(0).setIn_fav("0");
                    } else {
                        holder.add_favorite.setImageDrawable(context.getDrawable(R.drawable.ic_heart));
                        alldata.get(position).getEstate().get(0).setIn_fav("1");


                    }
//                bottomSheetDialogFragment_myEstate = new BottomSheetDialogFragment_MyEstate(alldata.get(position).getId() + "");
//
//                bottomSheetDialogFragment_myEstate.show(((FragmentActivity) context).getSupportFragmentManager(), "");

                    init_volley();
                    WebService.loading((Activity) context, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, context);
                    try {

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("type_id", "" + alldata.get(position).getId());
                        jsonObject.put("type", "" + "fund");
                        mVolleyService.postDataVolley("favorite", WebService.favorite, jsonObject);


                    } catch (Exception e) {

                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

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


//        bottomSheetDialogFragment_myEstate.dismiss();

        bottomSheerDialog = new BottomSheetDialog(context);
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View parentView = li.inflate(R.layout.success_sandoq, null);
        Button ok = parentView.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                                    finish();
                bottomSheerDialog.dismiss();
            }
        });
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

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading((Activity) context, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");
//                        String message = response.getString("message");

                        if (requestType.equals("upgrade")) {

                            BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(context);
                            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                            View parentView = li.inflate(R.layout.bottom_sheets_sucess_ordder, null);

                            TextView done = parentView.findViewById(R.id.done);
                            done.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    bottomSheerDialog.cancel();
                                }
                            });
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


                        String message = response.getString("message");


//                        WebService.Make_Toast_color((Activity) context, message, "success");


                    }
                } catch (Exception e) {

                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading((Activity) context, false);

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

                WebService.Make_Toast_color((Activity) context, error, "error");


            }
        };


    }

    public void show_dialog() {

        new AlertDialog.Builder(context)
                .setMessage(context.getResources().getString(R.string.real_qustion))
                .setCancelable(false)
                .setPositiveButton(context.getResources().getString(R.string.Participation), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(context, EditProfileActivity.class);
                        context.startActivity(intent);


                    }
                })
                .setNegativeButton(context.getResources().getString(R.string.no), null)
                .show();


//        BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(context);
//        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View parentView = li.inflate(R.layout.upgrade_message2, null);
//
//
//        TextView accept = parentView.findViewById(R.id.accept);
//        TextView no = parentView.findViewById(R.id.no);
//        ImageView close = parentView.findViewById(R.id.close);
//        no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomSheerDialog.cancel();
//
////                                    finish();
//            }
//        });
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomSheerDialog.cancel();
//
////                                    finish();
//            }
//        });
//        accept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                WebService.loading((Activity) context, true);
//
//                init_volley();
//                VolleyService mVolleyService = new VolleyService(mResultCallback, context);
//                mVolleyService.getDataVolley("upgrade", WebService.upgrade);
////
//                bottomSheerDialog.cancel();
////
////                                    finish();
//            }
//        });
//        bottomSheerDialog.setContentView(parentView);
//
//
//        Window window = bottomSheerDialog.getWindow();
//        window.findViewById(com.google.android.material.R.id.container).setFitsSystemWindows(false);
//        // dark navigation bar icons
//        View decorView = window.getDecorView();
//        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
//        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, context.getResources().getDisplayMetrics());
//
//
////        ((View) decorView.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
//
//
//        bottomSheerDialog.show();
    }

}
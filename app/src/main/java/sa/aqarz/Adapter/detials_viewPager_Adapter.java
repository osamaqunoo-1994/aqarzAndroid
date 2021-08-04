package sa.aqarz.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.hedgehog.ratingbar.RatingBar;
import com.squareup.picasso.Picasso;
import com.willy.ratingbar.ScaleRatingBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.ChatRoomActivity;
import sa.aqarz.Activity.DetailsActivity_aqarz;
import sa.aqarz.Activity.DetailsNewAqarezActivity;
import sa.aqarz.Activity.ReportAqarezActivity;
import sa.aqarz.Activity.check_login;
import sa.aqarz.Activity.profile.OtherProfileActivity;
import sa.aqarz.Dialog.BottomSheetDialogFragment_Rate;
import sa.aqarz.Modules.ComfortModules;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.imagemodules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class detials_viewPager_Adapter extends PagerAdapter {


    private final List<HomeModules_aqares> IMAGES;
    private final LayoutInflater inflater;
    private final Context context;

    ViewPagerIndicator view_pager_indicator;
    ViewPager home_viewPager;
    IResult mResultCallback;
    int oi = 0;
    public static Activity activity;
    TextView operation_type_name;
    TextView estate_type_name;
    TextView price;
    TextView address;
    TextView name;
    TextView note;
    TextView type_;
    TextView area;
    TextView room;
    TextView age;
    TextView view_;
    TextView metter_price;
    TextView bathroom;
    TextView purpose;
    TextView name_owner;
    TextView last_update;
    TextView ads_number;
    TextView link;
    TextView views_nummm;
    LinearLayout call;
    RecyclerView comfort_rec;
    List<ComfortModules> comfort_list = new ArrayList<>();
    String id_or_aq = "12";
    LinearLayout profile;
    LinearLayout chat;
    //

    RecyclerView rec_list_all;
    public static final ArrayList<imagemodules> items_ViewPager = new ArrayList<imagemodules>();
//
//    RecyclerView type_RecyclerView;
//    List<TypeModules> typeModules_list = new ArrayList<>();


    ImageView favorit;
    ImageView back;

    HomeModules_aqares homeModules_aqares;
    List<HomeModules_aqares> homeModules_aqares_list = new ArrayList<>();


    LinearLayout finince;
    LinearLayout rate;
    LinearLayout tent;
    LinearLayout rate_aqarez;
    RatingBar rate_aqarez_t;
    LinearLayout report;
    RecyclerView list_coments;
    ScaleRatingBar simpleRatingBar;
    ScaleRatingBar rate_user;

    public detials_viewPager_Adapter(Context context, List<HomeModules_aqares> IMAGES) {
        this.context = context;
        this.IMAGES = IMAGES;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View vieww = inflater.inflate(R.layout.layout_details_new, view, false);
        home_viewPager = vieww.findViewById(R.id.home_viewPager);
        view_pager_indicator = vieww.findViewById(R.id.view_pager_indicator);
        operation_type_name = vieww.findViewById(R.id.operation_type_name);
        estate_type_name = vieww.findViewById(R.id.estate_type_name);
        price = vieww.findViewById(R.id.price);
        address = vieww.findViewById(R.id.address);
        name = vieww.findViewById(R.id.name);
        note = vieww.findViewById(R.id.note);
        call = vieww.findViewById(R.id.call);
        favorit = vieww.findViewById(R.id.favorit);
        profile = vieww.findViewById(R.id.profile);
        link = vieww.findViewById(R.id.link);
        chat = vieww.findViewById(R.id.chat);
        rate_user = vieww.findViewById(R.id.rate_user);
        tent = vieww.findViewById(R.id.tent);
        rate = vieww.findViewById(R.id.rate);
        finince = vieww.findViewById(R.id.finince);
        rate_aqarez = vieww.findViewById(R.id.rate_aqarez);
        rec_list_all = vieww.findViewById(R.id.rec_list_all);
        list_coments = vieww.findViewById(R.id.list_coments);
        back = vieww.findViewById(R.id.back);
        report = vieww.findViewById(R.id.report);
        simpleRatingBar = vieww.findViewById(R.id.simpleRatingBar);
        rate_aqarez_t = vieww.findViewById(R.id.rate_aqarez_t);


        type_ = vieww.findViewById(R.id.type_);
        area = vieww.findViewById(R.id.area);
        room = vieww.findViewById(R.id.room);
        age = vieww.findViewById(R.id.age);
        view_ = vieww.findViewById(R.id.view_);
        metter_price = vieww.findViewById(R.id.metter_price);
        bathroom = vieww.findViewById(R.id.bathroom);
        purpose = vieww.findViewById(R.id.purpose);
        name_owner = vieww.findViewById(R.id.name_owner);
        last_update = vieww.findViewById(R.id.last_update);
        ads_number = vieww.findViewById(R.id.ads_number);
        views_nummm = vieww.findViewById(R.id.views_nummm);
        comfort_rec = vieww.findViewById(R.id.comfort_rec);

        comfort_rec.setLayoutManager(new GridLayoutManager(context, 2));
        assert vieww != null;


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DetailsNewAqarezActivity.activity.finish();

            }
        });

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rec_list_all.setLayoutManager(layoutManager1);


        LinearLayoutManager layoutManager1s
                = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        list_coments.setLayoutManager(layoutManager1s);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, ReportAqarezActivity.class);
                intent.putExtra("id", id_or_aq + "");
                context.startActivity(intent);


            }
        });
        rate_aqarez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment_Rate bottomSheetDialogFragment_rate = new BottomSheetDialogFragment_Rate(id_or_aq + "");

                bottomSheetDialogFragment_rate.show(((FragmentActivity) context).getSupportFragmentManager(), "");

            }
        });

        rate_aqarez_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment_Rate bottomSheetDialogFragment_rate = new BottomSheetDialogFragment_Rate(id_or_aq + "");

                bottomSheetDialogFragment_rate.show(((FragmentActivity) context).getSupportFragmentManager(), "");

            }
        });
        favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Settings.checkLogin()) {
                    init_volley();
                    WebService.loading((Activity) context, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, context);
                    try {
                        if (homeModules_aqares.getIn_fav().equals("1")) {
                            favorit.setImageDrawable(context.getDrawable(R.drawable.ic_like));
                            homeModules_aqares.setIn_fav("0");
                        } else {
                            favorit.setImageDrawable(context.getDrawable(R.drawable.ic_heart));

                            homeModules_aqares.setIn_fav("1");

                        }
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("type_id", "" + id_or_aq);

                        jsonObject.put("type", "" + "offer");
                        mVolleyService.postDataVolley("favorite", WebService.favorite, jsonObject);


                    } catch (Exception e) {

                    }


                } else {
                    context.startActivity(new Intent(context, check_login.class));
                }


            }
        });
        init_volley();
        WebService.loading((Activity) context, true);

        VolleyService mVolleyService = new VolleyService(mResultCallback, context);
        mVolleyService.getDataVolley("single_estat", WebService.single_estat + IMAGES.get(position).getId() + "/estate");


        view.addView(vieww, 0);

        return vieww;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
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


                        if (requestType.equals("smilier")) {


//                            JSONObject jsonObject_data = new JSONObject(data);
//
//                            String data_inside = jsonObject_data.getString("data");
                            JSONArray jsonArray = new JSONArray(data);

                            homeModules_aqares_list.clear();
                            rec_list_all.setAdapter(null);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {

                                    JsonParser parser = new JsonParser();
                                    JsonElement mJson = parser.parse(jsonArray.getString(i));

                                    Gson gson = new Gson();

                                    HomeModules_aqares bankModules = gson.fromJson(mJson, HomeModules_aqares.class);
                                    homeModules_aqares_list.add(bankModules);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            rec_list_all.setAdapter(new RecyclerView_samilar(context, homeModules_aqares_list));


                        } else if (requestType.equals("favorite")) {

//                            WebService.Make_Toast_color(DetailsActivity_aqarz.this, message, "error");


                        } else {
                            JsonParser parser = new JsonParser();
                            JsonElement mJson = parser.parse(data);

                            Gson gson = new Gson();

                            homeModules_aqares = gson.fromJson(mJson, HomeModules_aqares.class);


                            operation_type_name.setText(homeModules_aqares.getOperationTypeName());
                            estate_type_name.setText(homeModules_aqares.getEstate_type_name());
                            price.setText(homeModules_aqares.getTotalPrice());

                            if (homeModules_aqares.getCity_name() != null) {
                                address.setText(homeModules_aqares.getCity_name() + " - " + homeModules_aqares.getNeighborhood_name());

                            } else {
                                address.setText(homeModules_aqares.getAddress());

                            }

//                            if (homeModules_aqares.getAddress() == null) {
//
//                            } else {
//
//                            }


                            name.setText(homeModules_aqares.getEstate_type_name() + "");
                            note.setText(homeModules_aqares.getNote() + "");
                            type_.setText(homeModules_aqares.getEstate_type_name() + "");
                            area.setText(homeModules_aqares.getTotalArea() + "");
                            room.setText(homeModules_aqares.getRoomsNumber() + "");
                            age.setText(homeModules_aqares.getEstateAge() + "");
                            view_.setText(homeModules_aqares.getInterface() + "");
                            metter_price.setText(homeModules_aqares.getMeterPrice() + "");
                            bathroom.setText(homeModules_aqares.getBathroomsNumber() + "");
                            purpose.setText(homeModules_aqares.getBathroomsNumber() + "");
                            name_owner.setText(homeModules_aqares.getOwnerName() + "");
                            link.setText(homeModules_aqares.getUser().getLink() + "");


                            try {
                                if (homeModules_aqares.getUser() != null) {
                                    rate_user.setRating(Float.valueOf(homeModules_aqares.getUser().getRate()));

                                }
                            } catch (Exception e) {

                            }

                            if (homeModules_aqares.getRate() != null) {


                                if (homeModules_aqares.getRate().equals("0")) {
                                    rate_aqarez.setVisibility(View.VISIBLE);

                                } else {
                                    rate_aqarez.setVisibility(View.GONE);

                                }


                            } else {
                                rate_aqarez.setVisibility(View.VISIBLE);

                            }

                            try {
                                list_coments.setAdapter(new RecyclerView_coments(context, homeModules_aqares.getRates()));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                simpleRatingBar.setRating(Float.valueOf(homeModules_aqares.getRate()));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            profile.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    Intent intent = new Intent(context, OtherProfileActivity.class);
                                    intent.putExtra("id", homeModules_aqares.getUserId() + "");
                                    context.startActivity(intent);
//                                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                                }
                            });
                            chat.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(context, ChatRoomActivity.class);
                                    intent.putExtra("user_id", homeModules_aqares.getUserId() + "");
                                    intent.putExtra("parent_id", "-1");
                                    intent.putExtra("nameUser", homeModules_aqares.getOwnerName() + "");
                                    intent.putExtra("imageUser", "");
                                    context.startActivity(intent);
                                }
                            });
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            try {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

                                Date date = format.parse(homeModules_aqares.getCreatedAt().substring(0, 19) + "");

                                String dateTime = dateFormat.format(date);
                                last_update.setText(dateTime + "");

                                System.out.println(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (homeModules_aqares.getIn_fav().equals("1")) {
                                favorit.setImageDrawable(context.getDrawable(R.drawable.ic_heart));

                            } else {
                                favorit.setImageDrawable(context.getDrawable(R.drawable.ic_like));

                            }


                            try {

//                            last_update.setText(homeModules_aqares.getCreatedAt() + "");
                                ads_number.setText(homeModules_aqares.getId() + "");
                                views_nummm.setText(homeModules_aqares.getSeen_count() + "");

                                call.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        try {
                                            String phone = "0" + homeModules_aqares.getOwnerMobile();
                                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                                            context.startActivity(intent);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
//                            System.out.println("items_ViewPageritems_ViewPager" + homeModules_aqares.getEstate_file().size());


                            items_ViewPager.clear();

                            if (homeModules_aqares.getVideo() != null) {
                                if (!homeModules_aqares.getVideo().equals("null")) {
                                    items_ViewPager.add(new imagemodules(homeModules_aqares.getVideo() + "", "video"));
                                }
                            }
                            for (int i = 0; i < homeModules_aqares.getEstate_file().size(); i++) {
                                items_ViewPager.add(new imagemodules(homeModules_aqares.getEstate_file().get(i).getFile() + "", "image"));
                            }

                            home_viewPager.setAdapter(new home_viewPager_Adapter(context, items_ViewPager));
                            view_pager_indicator.setupWithViewPager(home_viewPager);

                            try {
                                if (items_ViewPager.size() > 1) {
                                    oi = 0;

                                    Handler handler = new Handler();

                                    Runnable runnable = new Runnable() {
                                        public void run() {

                                            if (oi == items_ViewPager.size()) {
                                                oi = 0;
                                            }

                                            home_viewPager.setCurrentItem(oi);
                                            oi++;
                                            handler.postDelayed(this, 3000);
                                        }
                                    };
                                    handler.postDelayed(runnable, 3000);

                                }

                            } catch (Exception e) {

                            }


                            RecyclerView_All_Comfort_in_details recyclerView_all_comfort_in_fragment = new RecyclerView_All_Comfort_in_details(context, homeModules_aqares.getComforts());//
                            comfort_rec.setAdapter(recyclerView_all_comfort_in_fragment);


//                            init_volley();
//                            VolleyService mVolleyService = new VolleyService(mResultCallback, context);
//                            mVolleyService.getDataVolley("smilier", WebService.smilier + "/" + id_or_aq + "/estate");//&request_type=pay


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

}
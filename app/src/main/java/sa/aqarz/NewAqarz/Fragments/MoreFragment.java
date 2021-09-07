package sa.aqarz.NewAqarz.Fragments;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.orhanobut.hawk.Hawk;
import com.willy.ratingbar.ScaleRatingBar;

import de.hdodenhof.circleimageview.CircleImageView;
import sa.aqarz.Activity.AllOrder_filtterActivity;
import sa.aqarz.Activity.Auth.EditProfileActivity;
import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.Auth.NewSiginUpActivity;
import sa.aqarz.Activity.ContactUsActivity;
import sa.aqarz.Activity.Employee.DetailsEmployeeActivity;
import sa.aqarz.Activity.FavoriteActivity;
import sa.aqarz.Activity.MyOrderActivity;
import sa.aqarz.Activity.SettingsActivity;
import sa.aqarz.Activity.TermsActivity;
import sa.aqarz.Activity.profile.AllclintActivity;
import sa.aqarz.Activity.profile.MyOffersActivity;
import sa.aqarz.Activity.profile.ProfileDetailsActivity;
import sa.aqarz.Dialog.BottomSheetDialogFragmen_restPassword;
import sa.aqarz.Modules.AllEmployee;
import sa.aqarz.NewAqarz.MainAqarzActivity;
import sa.aqarz.NewAqarz.MyOrderRequstActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    LinearLayout logout;
    LinearLayout langauge;
    LinearLayout changePassword;
    LinearLayout EditProfile;
    LinearLayout contact_us;
    LinearLayout Technical_support;
    LinearLayout terms;
    LinearLayout all_aqarez_man;
    LinearLayout employee;
    LinearLayout aqarz_offer;
    LinearLayout my_order;
    LinearLayout offer;
    LinearLayout myAccount;
    LinearLayout favorit;


    TextView name;
    ScaleRatingBar rate;
    CircleImageView iamge;
    ImageView active;

    TextView order;
    TextView employee_num;
    TextView aqarz_offer_num;
    TextView offer_num;

    public MoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreFragment newInstance(String param1, String param2) {
        MoreFragment fragment = new MoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_more2, container, false);
        logout = view.findViewById(R.id.logout);
        offer_num = view.findViewById(R.id.offer_num);
        aqarz_offer_num = view.findViewById(R.id.aqarz_offer_num);
        employee_num = view.findViewById(R.id.employee_num);
        order = view.findViewById(R.id.order);
        langauge = view.findViewById(R.id.langauge);
        changePassword = view.findViewById(R.id.changePassword);
        EditProfile = view.findViewById(R.id.EditProfile);
        contact_us = view.findViewById(R.id.contact_us);
        Technical_support = view.findViewById(R.id.Technical_support);
        terms = view.findViewById(R.id.terms);
        all_aqarez_man = view.findViewById(R.id.all_aqarez_man);
        employee = view.findViewById(R.id.employee);
        aqarz_offer = view.findViewById(R.id.aqarz_offer);
        my_order = view.findViewById(R.id.my_order);
        offer = view.findViewById(R.id.offer);
        myAccount = view.findViewById(R.id.myAccount);
        favorit = view.findViewById(R.id.favorit);

        name = view.findViewById(R.id.name);
        rate = view.findViewById(R.id.rate);
        iamge = view.findViewById(R.id.iamge);
        active = view.findViewById(R.id.active);


        try {
            name.setText(Settings.GetUser().getName() + "");
            Glide.with(getActivity()).load(Settings.GetUser().getLogo() + "").error(getResources().getDrawable(R.drawable.ic_user_un)).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).into(iamge);

            if (Settings.GetUser().getIs_certified().equals("1")) {
                active.setVisibility(View.VISIBLE);
            } else {
                active.setVisibility(View.GONE);

            }

            employee_num.setText("(" + Settings.GetUser().getCount_emp() + ")");
            order.setText("(" + Settings.GetUser().getCount_request() + ")");
            offer_num.setText("(" + Settings.GetUser().getCount_estate() + ")");
            int allo = Integer.valueOf(Settings.GetUser().getCount_fund_offer() + "") + Integer.valueOf(Settings.GetUser().getCount_offer() + "");
            aqarz_offer_num.setText("(" + allo + ")");
//            order.setText("(0)");
//            aqarz_offer_num.setText("(0)");


            rate.setRating(Float.valueOf(Settings.GetUser().getRate() + ""));
        } catch (Exception e) {

        }
        onclick();
        return view;
    }

    public void onclick() {

        aqarz_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainAqarzActivity.type_order_main = "Shopping";
                MainAqarzActivity.type_type_order_main = "Myoffer";
                MainAqarzActivity.set_contanier_fragments_order();
            }
        });
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyOffersActivity.class);
//              intent.putExtra("from", "splash");
                intent.putExtra("type", "all");
                intent.putExtra("id_user", "--");

                startActivity(intent);

            }
        });
        favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FavoriteActivity.class);
//              intent.putExtra("from", "splash");
                intent.putExtra("type", "all");
                intent.putExtra("id_user", "--");

                startActivity(intent);

            }
        });
        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileDetailsActivity.class);
                startActivity(intent);

            }
        });
        my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyOrderRequstActivity.class);
                startActivity(intent);

            }
        });
        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailsEmployeeActivity.class);
                startActivity(intent);

            }
        });
        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Settings.CheckIsAccountAqarzMan()) {
                    Intent intent = new Intent(getActivity(), EditProfileActivity.class);
//              intent.putExtra("from", "splash");
                    startActivity(intent);
//                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                } else {
                    Intent intent = new Intent(getActivity(), MyProfileInformationActivity.class);
//              intent.putExtra("from", "splash");
                    startActivity(intent);
//                    overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);
                }

            }
        });
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TermsActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setMessage(getResources().getString(R.string.are_you_wantlog))
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                Hawk.put("user", "");
                                Hawk.put("api_token", "");
//                Hawk.put("user", "");
                                MainAqarzActivity.activity.finish();

//                                check_user_login();


                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), null)
                        .show();


            }
        });
        all_aqarez_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllclintActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), NewPasswordActivity.class);
////                                intent.putExtra("from", "splash");
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);


                BottomSheetDialogFragmen_restPassword bottomSheetDialogFragmen_restPassword = new BottomSheetDialogFragmen_restPassword("");
                bottomSheetDialogFragmen_restPassword.show(getChildFragmentManager(), "");

//                Intent intent = new Intent(getContext(), ForgotPasswordActivity.class);
////                                intent.putExtra("from", "splash");
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

//                finish();
            }
        });
        Technical_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url = "https://api.whatsapp.com/send?phone=" + "966532576667";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);


                try {


                    Intent sendIntent = new Intent("android.intent.action.MAIN");
                    sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.setType("text/plain");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "");
                    sendIntent.putExtra("jid", "966532576667" + "@s.whatsapp.net");
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactUsActivity.class);
//                                intent.putExtra("from", "splash");
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in_info, R.anim.fade_out_info);

            }
        });
        langauge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setMessage(getResources().getString(R.string.are_you_change_lang_post))
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


//                                if (Hawk.get("lang").toString().equals("ar")) {
//
//                                    Hawk.put("lang", "en");
//
//                                    LocaleUtils.setLocale(SettingsActivity.this, "en");
////
//                                    finishAffinity();
//
//                                    Intent intent = new Intent(SettingsActivity.this, SplashScreenActivity.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(intent);
//
//
//                                } else {
//
//                                    Hawk.put("lang", "ar");
//
//                                    LocaleUtils.setLocale(SettingsActivity.this, "ar");
////
//                                    finishAffinity();
//
//                                    Intent intent = new Intent(SettingsActivity.this, SplashScreenActivity.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(intent);
//
//                                }


                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), null)
                        .show();

            }
        });
    }
}
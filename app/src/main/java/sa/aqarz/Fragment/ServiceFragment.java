package sa.aqarz.Fragment;
/**
 * Created by osama on 10/8/2017.
 */


import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import io.blushine.android.ui.showcase.MaterialShowcaseView;
import io.blushine.android.ui.showcase.ShowcaseListener;
import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.Auth.NewSiginUpActivity;
import sa.aqarz.Activity.ContactUsActivity;
import sa.aqarz.Activity.FavoriteActivity;
import sa.aqarz.Activity.MainActivity;
import sa.aqarz.Activity.MyOrderActivity;
import sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import sa.aqarz.Activity.OprationNew.AqarzOrActivity;
import sa.aqarz.Activity.OprationNew.FinanceActivity;
import sa.aqarz.Activity.OprationNew.RateActivity;
import sa.aqarz.Activity.OprationNew.RentActivity;
import sa.aqarz.Activity.OprationNew.RentShowActivity;
import sa.aqarz.Activity.OprationNew.RequestServiceActivity;
import sa.aqarz.Activity.PrivecyActivity;
import sa.aqarz.Activity.SplashScreenActivity;
import sa.aqarz.Activity.TermsActivity;
import sa.aqarz.Activity.profile.AllclintActivity;
import sa.aqarz.Activity.profile.MyOffersActivity;
import sa.aqarz.Activity.profile.MyProfileActivity;
import sa.aqarz.Activity.profile.OtherProfileActivity;
import sa.aqarz.Dialog.BottomSheetDialogFragmen_restPassword;
import sa.aqarz.Dialog.BottomSheetDialogFragment_QR;
import sa.aqarz.R;
import sa.aqarz.Settings.LocaleUtils;
import sa.aqarz.Settings.Settings;


public class ServiceFragment extends Fragment {

    LinearLayout rent_layout;
    LinearLayout aqar_layout;
    LinearLayout rate_layout;
    LinearLayout fini_layout;
    LinearLayout AddAqareaz;
    LinearLayout Shoppingrequest_layout;
    LinearLayout Real_Estate_orders_layout;

    TextView text_a1;



    ShowcaseView showCaseView;
    ShowcaseView showCaseView1;

    MaterialShowcaseView materialShowcaseView;
    MaterialShowcaseView materialShowcaseView2;

    static Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service, container, false);

        rent_layout = v.findViewById(R.id.rent_layout);
        aqar_layout = v.findViewById(R.id.aqar_layout);
        rate_layout = v.findViewById(R.id.rate_layout);
        fini_layout = v.findViewById(R.id.fini_layout);
        AddAqareaz = v.findViewById(R.id.AddAqareaz);
        Shoppingrequest_layout = v.findViewById(R.id.Shoppingrequest_layout);
        Real_Estate_orders_layout = v.findViewById(R.id.Real_Estate_orders_layout);

        text_a1 = v.findViewById(R.id.text_a1);
        activity = getActivity();



        rent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Hawk.contains("rent_layout")) {

                    Intent intent = new Intent(getActivity(), RentActivity.class);
                    intent.putExtra("id", "");
                    startActivity(intent);
                } else {
                    Hawk.put("rent_layout", "rent_layout");
                    Intent intent = new Intent(getActivity(), RentShowActivity.class);
                    intent.putExtra("id", "");
                    startActivity(intent);
                }


            }
        });
        Shoppingrequest_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.go_to_order();

//                finish();

            }
        });
        Real_Estate_orders_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.go_to_order();
//                finish();


            }
        });
        fini_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), FinanceActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);


            }
        });
        AddAqareaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), AddAqarsActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);


            }
        });
        rate_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), RateActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);


            }
        });
        aqar_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), AqarzOrActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);


            }
        });

//        rent_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!Hawk.contains("showCaseView1")) {

                    Hawk.put("showCaseView1", "showCaseView1");


                    materialShowcaseView = new MaterialShowcaseView.Builder(getActivity())
                            .setTitleText(getResources().getString(R.string.requestAqarezhzTitle_show))
                            .setContentText(getResources().getString(R.string.requestAqarezhzdes_show))
                            .setContentTextColor(getResources().getColor(R.color.white))

                            .setDismissText(getResources().getString(R.string.Nextt)) // Optional. When used can only dismiss the showcase by clicking on the dismiss button and target isn't pressable.
                            .setTarget(aqar_layout)


//                            .setBackgroundColor(getResources().getColor(R.color.color_brimarys))
                            .setDelay(300) // Optional. But starting animations immediately in onCreate can make the choppy
                            .show();
                    materialShowcaseView.addListener(new ShowcaseListener() {
                        @Override
                        public void onShowcaseDisplayed(MaterialShowcaseView materialShowcaseView) {

                        }

                        @Override
                        public void onShowcaseDismissed(MaterialShowcaseView materialShowcaseView) {

                            materialShowcaseView2 = new MaterialShowcaseView.Builder(getActivity())
                                    .setTitleText(getResources().getString(R.string.finincezhzTitle_show))
                                    .setContentText(getResources().getString(R.string.finincezhzdes_show))
                                    .setContentTextColor(getResources().getColor(R.color.white))

                                    .setDismissText(getResources().getString(R.string.Nextt)) // Optional. When used can only dismiss the showcase by clicking on the dismiss button and target isn't pressable.
                                    .setTarget(rent_layout)


//                            .setBackgroundColor(getResources().getColor(R.color.color_brimarys))
                                    .setDelay(300) // Optional. But starting animations immediately in onCreate can make the choppy
                                    .show();
                        }

                        @Override
                        public void onShowcaseSkipped(MaterialShowcaseView materialShowcaseView) {

                        }

                        @Override
                        public void onTargetPressed(MaterialShowcaseView materialShowcaseView) {

                        }
                    });

//                    showCaseView = new ShowcaseView.Builder(RequestServiceActivity.this)
//                            .setTarget(new ViewTarget(R.id.aqar_layout, RequestServiceActivity.this))
//                            .setContentTitle(getResources().getString(R.string.requestAqarezhzTitle_show))
//                            .setContentText(getResources().getString(R.string.requestAqarezhzdes_show))
//
//                            .setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    showCaseView.hide();
//
//                                    showCaseView1 = new ShowcaseView.Builder(RequestServiceActivity.this)
//                                            .setTarget(new ViewTarget(R.id.rent_layout, RequestServiceActivity.this))
//                                            .setContentTitle(getResources().getString(R.string.finincezhzTitle_show))
//                                            .setContentText(getResources().getString(R.string.finincezhzdes_show))
//
//
//                                            .hideOnTouchOutside()
//
//                                            .setStyle(R.style.CustomShowcaseTheme2)
//                                            .build();
//                                }
//                            })
//
//
//                            .setStyle(R.style.CustomShowcaseTheme2)
//                            .build();

                }
            }
        }, 100); // After 1 seconds
        return v;
    }





    public static ServiceFragment newInstance(String text) {

        ServiceFragment f = new ServiceFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }


    @Override
    public void onResume() {



        super.onResume();
    }



}
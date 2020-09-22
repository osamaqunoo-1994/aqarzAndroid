package aqarz.revival.sa.aqarz.Fragment.TypeOrders;
/**
 * Created by osama on 10/8/2017.
 */


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import aqarz.revival.sa.aqarz.Activity.Auth.LoginActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.NewPasswordActivity;
import aqarz.revival.sa.aqarz.Activity.Auth.RegisterActivity;
import aqarz.revival.sa.aqarz.Activity.OprationAqarz.RequestOrderActivity;
import aqarz.revival.sa.aqarz.Activity.SplashScreenActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_order;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_SelectBanks;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_SelectCity;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.LocaleUtils;
import aqarz.revival.sa.aqarz.Settings.Settings;


public class type1Fragment extends Fragment {

    RecyclerView opration_RecyclerView;

    BottomSheetDialogFragment_SelectCity bottomSheetDialogFragment_selectCity;
    BottomSheetDialogFragment_SelectBanks bottomSheetDialogFragment_selectBanks;


    List<TypeModules> type_list = new ArrayList<>();

    TextView month;
    TextView year;
    TextView governmental;
    TextView Special;
    TextView Soldier;


    TextView date_bertih;
    TextView start_work_date;


    TextView city;
    TextView banks;
    TextView National_address;


    EditText price, name_owner, phone_owner, name, phone, id_number, total_sallary, Financial_obligations, buldingnumber, StreetName;
    EditText Neighborhoodname, name_city, Postal_code, additional_number, unit_number;


    Button btn_send;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.requst_order_type_1, container, false);

        init(v);
        return v;
    }

    public void init(View v) {
        opration_RecyclerView = v.findViewById(R.id.opration_RecyclerView);
        month = v.findViewById(R.id.month);
        year = v.findViewById(R.id.year);
        Soldier = v.findViewById(R.id.Soldier);
        Special = v.findViewById(R.id.Special);
        governmental = v.findViewById(R.id.governmental);


        date_bertih = v.findViewById(R.id.date_bertih);
        city = v.findViewById(R.id.city);
        start_work_date = v.findViewById(R.id.start_work_date);
        banks = v.findViewById(R.id.banks);
        National_address = v.findViewById(R.id.National_address);

        price = v.findViewById(R.id.price);
        name_owner = v.findViewById(R.id.name_owner);
        phone_owner = v.findViewById(R.id.phone_owner);
        name = v.findViewById(R.id.name);
        phone = v.findViewById(R.id.phone);
        id_number = v.findViewById(R.id.id_number);
        total_sallary = v.findViewById(R.id.total_sallary);
        Financial_obligations = v.findViewById(R.id.Financial_obligations);
        buldingnumber = v.findViewById(R.id.buldingnumber);
        StreetName = v.findViewById(R.id.StreetName);
        Neighborhoodname = v.findViewById(R.id.Neighborhoodname);
        name_city = v.findViewById(R.id.name_city);
        Postal_code = v.findViewById(R.id.Postal_code);
        additional_number = v.findViewById(R.id.additional_number);
        unit_number = v.findViewById(R.id.unit_number);
        StreetName = v.findViewById(R.id.StreetName);
        StreetName = v.findViewById(R.id.StreetName);
        btn_send = v.findViewById(R.id.btn_send);


        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        System.out.println("type_list" + type_list.size());


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        opration_RecyclerView.setLayoutManager(layoutManager1);
        opration_RecyclerView.setAdapter(new RecyclerView_All_type_in_fragment(getContext(), type_list));

//
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month.setBackground(getResources().getDrawable(R.drawable.button_login));

                month.setTextColor(getResources().getColor(R.color.white));

                year.setBackground(getResources().getDrawable(R.drawable.search_background));

                year.setTextColor(getResources().getColor(R.color.textColor));

            }
        });


        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year.setBackground(getResources().getDrawable(R.drawable.button_login));

                year.setTextColor(getResources().getColor(R.color.white));


                month.setBackground(getResources().getDrawable(R.drawable.search_background));

                month.setTextColor(getResources().getColor(R.color.textColor));
            }
        });


        governmental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                governmental.setBackground(getResources().getDrawable(R.drawable.button_login));

                governmental.setTextColor(getResources().getColor(R.color.white));


                Soldier.setBackground(null);

                Soldier.setTextColor(getResources().getColor(R.color.textColor));

                Special.setBackground(null);

                Special.setTextColor(getResources().getColor(R.color.textColor));
            }
        });
        Soldier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Soldier.setBackground(getResources().getDrawable(R.drawable.button_login));

                Soldier.setTextColor(getResources().getColor(R.color.white));


                governmental.setBackground(null);

                governmental.setTextColor(getResources().getColor(R.color.textColor));
                Special.setBackground(null);

                Special.setTextColor(getResources().getColor(R.color.textColor));
            }
        });
        Special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Special.setBackground(getResources().getDrawable(R.drawable.button_login));

                Special.setTextColor(getResources().getColor(R.color.white));


                governmental.setBackground(null);

                governmental.setTextColor(getResources().getColor(R.color.textColor));
                Soldier.setBackground(null);

                Soldier.setTextColor(getResources().getColor(R.color.textColor));
            }
        });


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date_start_work = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                start_work_date.setText(sdf.format(myCalendar.getTime()));
            }

        };
        start_work_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date_start_work, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        DatePickerDialog.OnDateSetListener datebitth = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                date_bertih.setText(sdf.format(myCalendar.getTime()));
            }

        };
        date_bertih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), datebitth, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                bottomSheetDialogFragment_selectCity = new BottomSheetDialogFragment_SelectCity("");


                bottomSheetDialogFragment_selectCity.show(getFragmentManager(), "");


            }
        });
        banks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                bottomSheetDialogFragment_selectBanks = new BottomSheetDialogFragment_SelectBanks("");


                bottomSheetDialogFragment_selectBanks.show(getFragmentManager(), "");


            }
        });


    }


    public static type1Fragment newInstance(String text) {

        type1Fragment f = new type1Fragment();
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
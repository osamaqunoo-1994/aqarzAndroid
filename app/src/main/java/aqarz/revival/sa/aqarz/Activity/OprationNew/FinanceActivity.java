package aqarz.revival.sa.aqarz.Activity.OprationNew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import aqarz.revival.sa.aqarz.Adapter.RecyclerView_Type_RequstService;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_SelectBanks;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.VolleyService;

public class FinanceActivity extends AppCompatActivity {
    TextView circle_2, circle_3;
    LinearLayout line;
    Button next_to_2;
    Button next_to_3;
    Button finish;
    String current_page = "1";

    ImageView back;

    LinearLayout layout_1, layout_2, layout_3;
    LinearLayout addres_layount;

    RecyclerView opration_RecyclerView;
    List<TypeModules> type_list = new ArrayList<>();
    String opration_select = "";


    TextView governmental;
    TextView Special;
    TextView Soldier;
    String seek_progress = "0";


    SeekBar seek_bar;
    String tenant_job_type = "governmental";


    TextView ada_1_yes;
    TextView ada_1_no;
    TextView property_dd_yes;
    TextView property_dd_no;
    String ada_ = "1";
    String property = "1";

    BottomSheetDialogFragment_SelectBanks bottomSheetDialogFragment_selectBanks;
    EditText priceAqar, available_price, Solidarity_salary;

    EditText name, phone, id_number;
    EditText name_city, total_sallary, Financial_obligations;

    TextView start_work_date;
    TextView banks;
    TextView date_bertih;
    String banks_id = "";

    ImageView image_id;

    Switch switch_address;
    Switch Solidarity_partner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        init();
    }

    public void init() {


        circle_2 = findViewById(R.id.circle_2);
        circle_3 = findViewById(R.id.circle_3);
        line = findViewById(R.id.line);
        next_to_3 = findViewById(R.id.next_to_3);
        next_to_2 = findViewById(R.id.next_to_2);
        layout_1 = findViewById(R.id.layout_1);
        layout_2 = findViewById(R.id.layout_2);
        layout_3 = findViewById(R.id.layout_3);
        back = findViewById(R.id.back);
        finish = findViewById(R.id.finish);
        seek_bar = findViewById(R.id.seek_bar);

        Soldier = findViewById(R.id.Soldier);
        Special = findViewById(R.id.Special);
        governmental = findViewById(R.id.governmental);
        ada_1_yes = findViewById(R.id.ada_1_yes);
        ada_1_no = findViewById(R.id.ada_1_no);
        property_dd_yes = findViewById(R.id.property_dd_yes);
        property_dd_no = findViewById(R.id.property_dd_no);
        switch_address = findViewById(R.id.switch_address);
        addres_layount = findViewById(R.id.addres_layount);
        Solidarity_partner = findViewById(R.id.Solidarity_partner);
        priceAqar = findViewById(R.id.priceAqar);
        available_price = findViewById(R.id.available_price);
        Solidarity_salary = findViewById(R.id.Solidarity_salary);

        opration_RecyclerView = findViewById(R.id.opration_RecyclerView);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        date_bertih = findViewById(R.id.date_bertih);
        id_number = findViewById(R.id.id_number);
        name_city = findViewById(R.id.name_city);
        total_sallary = findViewById(R.id.total_sallary);
        Financial_obligations = findViewById(R.id.Financial_obligations);
        banks = findViewById(R.id.banks);
        start_work_date = findViewById(R.id.start_work_date);
        image_id = findViewById(R.id.image_id);
        //---------------------------------------------------------
        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(FinanceActivity.this, LinearLayoutManager.HORIZONTAL, false);
        opration_RecyclerView.setLayoutManager(layoutManager1);

        RecyclerView_Type_RequstService recyclerView_type_requstService = new RecyclerView_Type_RequstService(FinanceActivity.this, type_list);
        recyclerView_type_requstService.addItemClickListener(new RecyclerView_Type_RequstService.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

                opration_select = type_list.get(position).getId().toString() + "";

            }
        });
        opration_RecyclerView.setAdapter(recyclerView_type_requstService);


        next_step();
        action_click();
    }

    public void action_click() {


        governmental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                governmental.setBackground(getResources().getDrawable(R.drawable.button_login));

                governmental.setTextColor(getResources().getColor(R.color.white));


                Soldier.setBackground(null);

                Soldier.setTextColor(getResources().getColor(R.color.textColor));

                Special.setBackground(null);

                Special.setTextColor(getResources().getColor(R.color.textColor));
                tenant_job_type = "governmental";
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
                tenant_job_type = "soldier";

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
                tenant_job_type = "special";

            }
        });


        //---------------------------------------------------------------------------------------------
        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seek_progress = progress + "";
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        ada_1_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ada_1_yes.setBackground(getResources().getDrawable(R.drawable.button_login));

                ada_1_yes.setTextColor(getResources().getColor(R.color.white));

                ada_1_no.setBackground(null);

                ada_1_no.setTextColor(getResources().getColor(R.color.textColor));

                ada_ = "1";
            }
        });
        ada_1_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ada_1_no.setBackground(getResources().getDrawable(R.drawable.button_login));

                ada_1_no.setTextColor(getResources().getColor(R.color.white));


                ada_1_yes.setBackground(null);

                ada_1_yes.setTextColor(getResources().getColor(R.color.textColor));

                ada_ = "0";

            }

        });
        property_dd_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                property_dd_yes.setBackground(getResources().getDrawable(R.drawable.button_login));

                property_dd_yes.setTextColor(getResources().getColor(R.color.white));


                property_dd_no.setBackground(null);

                property_dd_no.setTextColor(getResources().getColor(R.color.textColor));

                property = "1";
            }
        });
        property_dd_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                property_dd_no.setBackground(getResources().getDrawable(R.drawable.button_login));

                property_dd_no.setTextColor(getResources().getColor(R.color.white));


                property_dd_yes.setBackground(null);

                property_dd_yes.setTextColor(getResources().getColor(R.color.textColor));

                property = "0";
            }
        });
        switch_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    addres_layount.setVisibility(View.VISIBLE);
                } else {
                    addres_layount.setVisibility(View.GONE);
                }


            }
        });
        final Calendar myCalendar = Calendar.getInstance();

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
                new DatePickerDialog(FinanceActivity.this, datebitth, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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
                new DatePickerDialog(FinanceActivity.this, date_start_work, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        banks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialogFragment_selectBanks = new BottomSheetDialogFragment_SelectBanks("");
                bottomSheetDialogFragment_selectBanks.addItemClickListener(new BottomSheetDialogFragment_SelectBanks.ItemClickListener() {
                    @Override
                    public void onItemClick(int id_bank, String name_bank) {

                        banks_id = id_bank + "";
                        banks.setText(name_bank);
                        bottomSheetDialogFragment_selectBanks.dismiss();
                    }
                });

                bottomSheetDialogFragment_selectBanks.show(getSupportFragmentManager(), "");

            }
        });

    }

    public void next_step() {

        next_to_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (priceAqar.getText().toString().equals("")) {


                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.Price_aqar) + " " + getResources().getString(R.string.is_requred), "error");


                } else if (available_price.getText().toString().equals("")) {
                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.Available_price) + " " + getResources().getString(R.string.is_requred), "error");

                } else if (Solidarity_salary.getText().toString().equals("") && Solidarity_partner.isChecked()) {
                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.Solidarity_salary) + " " + getResources().getString(R.string.is_requred), "error");

                } else {
                    current_page = "2";

                    layout_1.setVisibility(View.GONE);
                    layout_2.setVisibility(View.VISIBLE);
                    layout_3.setVisibility(View.GONE);

                    circle_2.setBackground(getResources().getDrawable(R.drawable.circle_fill));
                    circle_3.setBackground(getResources().getDrawable(R.drawable.circle_fill_un));
                    line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                }


            }
        });
        next_to_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (name.getText().toString().equals("")) {


                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.name) + " " + getResources().getString(R.string.is_requred), "error");


                } else if (phone.getText().toString().equals("")) {
                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.phone_number) + " " + getResources().getString(R.string.is_requred), "error");

                } else if (date_bertih.getText().toString().equals("")) {
                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.Date_of_Birth) + " " + getResources().getString(R.string.is_requred), "error");

                } else if (id_number.getText().toString().equals("")) {
                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.id_number) + " " + getResources().getString(R.string.is_requred), "error");

                } else if (name_city.getText().toString().equals("")) {
                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.city) + " " + getResources().getString(R.string.is_requred), "error");

                } else if (start_work_date.getText().toString().equals("")) {
                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.startworkdate) + " " + getResources().getString(R.string.is_requred), "error");

                } else if (total_sallary.getText().toString().equals("")) {
                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.Total_salary) + " " + getResources().getString(R.string.is_requred), "error");

                } else if (banks.getText().toString().equals("")) {
                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.Salary_Transferred) + " " + getResources().getString(R.string.is_requred), "error");

                } else if (Financial_obligations.getText().toString().equals("")) {
                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.Financial_obligations) + " " + getResources().getString(R.string.is_requred), "error");

                } else {


                    current_page = "3";
                    layout_1.setVisibility(View.GONE);
                    layout_2.setVisibility(View.GONE);
                    layout_3.setVisibility(View.VISIBLE);


                    circle_2.setBackground(getResources().getDrawable(R.drawable.circle_fill));
                    circle_3.setBackground(getResources().getDrawable(R.drawable.circle_fill));
                    line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                }
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (current_page.toString().equals("3")) {

                    current_page = "2";

                    layout_1.setVisibility(View.GONE);
                    layout_2.setVisibility(View.VISIBLE);
                    layout_3.setVisibility(View.GONE);

                    circle_2.setBackground(getResources().getDrawable(R.drawable.circle_fill));
                    circle_3.setBackground(getResources().getDrawable(R.drawable.circle_fill_un));
                    line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


                } else if (current_page.toString().equals("2")) {


                    current_page = "1";

                    layout_1.setVisibility(View.VISIBLE);
                    layout_2.setVisibility(View.GONE);
                    layout_3.setVisibility(View.GONE);

                    circle_2.setBackground(getResources().getDrawable(R.drawable.circle_fill_un));
                    circle_3.setBackground(getResources().getDrawable(R.drawable.circle_fill_un));
                    line.setBackgroundColor(getResources().getColor(R.color.color_bac));
                }


            }
        });

    }


    @Override
    public void onBackPressed() {


        if (current_page.toString().equals("3")) {

            current_page = "2";

            layout_1.setVisibility(View.GONE);
            layout_2.setVisibility(View.VISIBLE);
            layout_3.setVisibility(View.GONE);

            circle_2.setBackground(getResources().getDrawable(R.drawable.circle_fill));
            circle_3.setBackground(getResources().getDrawable(R.drawable.circle_fill_un));
            line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


        } else if (current_page.toString().equals("2")) {


            current_page = "1";

            layout_1.setVisibility(View.VISIBLE);
            layout_2.setVisibility(View.GONE);
            layout_3.setVisibility(View.GONE);

            circle_2.setBackground(getResources().getDrawable(R.drawable.circle_fill_un));
            circle_3.setBackground(getResources().getDrawable(R.drawable.circle_fill_un));
            line.setBackgroundColor(getResources().getColor(R.color.color_bac));
        }


    }
}
package aqarz.revival.sa.aqarz.Activity.OprationNew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.loopj.android.http.RequestParams;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import aqarz.revival.sa.aqarz.Adapter.RecyclerView_Type_RequstService;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_SelectBanks;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_SelectCity;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;

public class FinanceActivity extends AppCompatActivity {
    IResult mResultCallback;


    Button next_to_2;

    Button finish;
    String current_page = "1";

    ImageView back;

    LinearLayout layout_1, layout_2, layout_3;
    LinearLayout addres_layount;

    RecyclerView opration_RecyclerView;
    List<TypeModules> type_list = new ArrayList<>();
    String opration_select = "2";


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
    EditText Neighborhoodname, Postal_code, additional_number, unit_number;

    EditText name, phone, id_number;
    EditText name_city, total_sallary, Financial_obligations;
    EditText buldingnumber, StreetName;

    TextView start_work_date;
    TextView banks;
    TextView city;
    TextView date_bertih;
    String banks_id = "";
    String city_id = "";
    BottomSheetDialogFragment_SelectCity bottomSheetDialogFragment_selectCity;


    Switch switch_address;
    Switch Solidarity_partner;
    ImageView natinal_image;
    ImageView image_id;
    File National_address_file = null;
    File image_id_file = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        init();
    }

    public void init() {


        next_to_2 = findViewById(R.id.next_to_2);
        layout_1 = findViewById(R.id.layout_1);
        layout_2 = findViewById(R.id.layout_2);
        layout_3 = findViewById(R.id.layout_3);
        back = findViewById(R.id.back);
        finish = findViewById(R.id.finish);
        seek_bar = findViewById(R.id.seek_bar);
        natinal_image = findViewById(R.id.natinal_image);

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
        city = findViewById(R.id.city);
        total_sallary = findViewById(R.id.total_sallary);
        Financial_obligations = findViewById(R.id.Financial_obligations);
        banks = findViewById(R.id.banks);
        start_work_date = findViewById(R.id.start_work_date);
        image_id = findViewById(R.id.image_id);
        Neighborhoodname = findViewById(R.id.Neighborhoodname);
        Postal_code = findViewById(R.id.Postal_code);
        additional_number = findViewById(R.id.additional_number);
        unit_number = findViewById(R.id.unit_number);

        buldingnumber = findViewById(R.id.buldingnumber);
        StreetName = findViewById(R.id.StreetName);


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


                String myFormat = "yyyy/MM/dd"; //In which you need put here 2020-02-15
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


                String myFormat = "yyyy/MM/dd"; //In which you need put here
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
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                bottomSheetDialogFragment_selectCity = new BottomSheetDialogFragment_SelectCity("");
                bottomSheetDialogFragment_selectCity.addItemClickListener(new BottomSheetDialogFragment_SelectCity.ItemClickListener() {
                    @Override
                    public void onItemClick(int id_city, String city_naem) {
                        city_id = id_city + "";
                        city.setText(city_naem);
                        bottomSheetDialogFragment_selectCity.dismiss();

                    }
                });

                bottomSheetDialogFragment_selectCity.show(getSupportFragmentManager(), "");

            }
        });
        natinal_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                select_image_from_local(4, 4);


            }
        });
        image_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                select_image_from_local(1, 1);


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
                    layout_3.setVisibility(View.VISIBLE);

//                    circle_2.setBackground(getResources().getDrawable(R.drawable.circle_fill));
//                    circle_3.setBackground(getResources().getDrawable(R.drawable.circle_fill_un));
//                    line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                }


            }
        });
//        next_to_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (name.getText().toString().equals("")) {
//
//
//                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.name) + " " + getResources().getString(R.string.is_requred), "error");
//
//
//                } else if (phone.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.phone_number) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (date_bertih.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.Date_of_Birth) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (id_number.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.id_number) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (name_city.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.city) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (start_work_date.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.startworkdate) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (total_sallary.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.Total_salary) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (banks.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.Salary_Transferred) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (Financial_obligations.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(FinanceActivity.this, getResources().getString(R.string.Financial_obligations) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else {
//
//
//                    current_page = "3";
//                    layout_1.setVisibility(View.GONE);
//                    layout_2.setVisibility(View.GONE);
//                    layout_3.setVisibility(View.VISIBLE);
//
//
//                    circle_2.setBackground(getResources().getDrawable(R.drawable.circle_fill));
//                    circle_3.setBackground(getResources().getDrawable(R.drawable.circle_fill));
//                    line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//
//                }
//            }
//        });
        finish.setOnClickListener(new View.OnClickListener() {
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

                } else if (city.getText().toString().equals("")) {
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


                    WebService.loading(FinanceActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, FinanceActivity.this);

                    RequestParams sendObj = new RequestParams();

                    try {


                        sendObj.put("operation_type_id", "2");//form operation list api in setting
                        sendObj.put("estate_type_id", opration_select);//form estate type list api in setting
                        sendObj.put("job_type", tenant_job_type);

                        sendObj.put("finance_interval", seek_progress);//from seek bar
                        sendObj.put("job_start_date", start_work_date.getText().toString());
                        sendObj.put("estate_price", priceAqar.getText().toString());
                        sendObj.put("engagements", Financial_obligations.getText().toString());
                        sendObj.put("city_id", city_id);
                        sendObj.put("name", name.getText().toString());


                        sendObj.put("identity_number", id_number.getText().toString());
                        if (image_id_file != null) {
                            sendObj.put("identity_file", image_id_file);//
                        }


                        sendObj.put("mobile", phone.getText().toString());
//                        sendObj.put("age", age.getText().toString());
                        sendObj.put("total_salary", total_sallary.getText().toString());
                        sendObj.put("available_amount", available_price.getText().toString());
//                        sendObj.put("national_address", "6855");

                        if (National_address_file != null) {
                            sendObj.put("national_address_file", National_address_file);//

                        }


                        sendObj.put("bank_id", banks_id);
                        sendObj.put("city_id", city_id);


                        if (switch_address.isChecked()) {
                            sendObj.put("national_address_display", "1");

                        } else {
                            sendObj.put("national_address_display", "0");

                        }
                        if (Solidarity_partner.isChecked()) {
                            sendObj.put("solidarity_partner", "1");
                            sendObj.put("solidarity_salary", Solidarity_salary.getText().toString());

                        } else {
                            sendObj.put("solidarity_partner", "0");

                        }


                        if (National_address_file != null) {
                            sendObj.put("national_address_file", National_address_file);//

                        }

                        if (!StreetName.getText().toString().equals("")) {
                            sendObj.put("street_name", StreetName.getText().toString());//

                        }
                        if (!Neighborhoodname.getText().toString().equals("")) {
                            sendObj.put("neighborhood_name", Neighborhoodname.getText().toString());//

                        }
                        if (!buldingnumber.getText().toString().equals("")) {
                            sendObj.put("building_city_name", buldingnumber.getText().toString());//

                        }
                        if (!Postal_code.getText().toString().equals("")) {
                            sendObj.put("postal_code", Postal_code.getText().toString());//

                        }
                        if (!unit_number.getText().toString().equals("")) {
                            sendObj.put("unit_name", unit_number.getText().toString());//

                        }

//                        sendObj.put("unit_name", unit_number.getText().toString());//
                        init_volley();

                        System.out.println(sendObj.toString());
                        mVolleyService.postDataasync_with_file("finance", WebService.finance, sendObj);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
//
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (current_page.toString().equals("3")) {

                    current_page = "2";

                    layout_1.setVisibility(View.GONE);
                    layout_2.setVisibility(View.VISIBLE);
                    layout_3.setVisibility(View.VISIBLE);

//                    circle_2.setBackground(getResources().getDrawable(R.drawable.circle_fill));
//                    circle_3.setBackground(getResources().getDrawable(R.drawable.circle_fill_un));
//                    line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


                } else if (current_page.toString().equals("2")) {


                    current_page = "1";

                    layout_1.setVisibility(View.VISIBLE);
                    layout_2.setVisibility(View.GONE);
                    layout_3.setVisibility(View.GONE);

//                    circle_2.setBackground(getResources().getDrawable(R.drawable.circle_fill_un));
//                    circle_3.setBackground(getResources().getDrawable(R.drawable.circle_fill_un));
//                    line.setBackgroundColor(getResources().getColor(R.color.color_bac));
                } else {
                    finish();
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
//
//            circle_2.setBackground(getResources().getDrawable(R.drawable.circle_fill));
//            circle_3.setBackground(getResources().getDrawable(R.drawable.circle_fill_un));
//            line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


        } else if (current_page.toString().equals("2")) {


            current_page = "1";

            layout_1.setVisibility(View.VISIBLE);
            layout_2.setVisibility(View.GONE);
            layout_3.setVisibility(View.GONE);

//            circle_2.setBackground(getResources().getDrawable(R.drawable.circle_fill_un));
//            circle_3.setBackground(getResources().getDrawable(R.drawable.circle_fill_un));
//            line.setBackgroundColor(getResources().getColor(R.color.color_bac));
        } else {
            finish();
        }


    }

    public void select_image_from_local(int permission, int st_code) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(FinanceActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(FinanceActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        permission);

            } else {


                ImagePicker.with(FinanceActivity.this)
                        .setFolderMode(true)
                        .setFolderTitle("Album")

                        .setDirectoryName("Image Picker")
                        .setMultipleMode(false)
                        .setShowNumberIndicator(true)
                        .setMaxSize(1)
                        .setLimitMessage("You can select one image")

                        .setRequestCode(st_code)
                        .start();
            }
        } else {

            ImagePicker.with(FinanceActivity.this)
                    .setFolderMode(true)
                    .setFolderTitle("Album")

                    .setDirectoryName("Image Picker")
                    .setMultipleMode(false)
                    .setShowNumberIndicator(true)
                    .setMaxSize(1)
                    .setLimitMessage("You can select one image")

                    .setRequestCode(st_code)
                    .start();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (ContextCompat.checkSelfPermission(FinanceActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        } else {
            ImagePicker.with(FinanceActivity.this)
                    .setFolderMode(true)
                    .setFolderTitle("Album")

                    .setDirectoryName("Image Picker")
                    .setMultipleMode(false)
                    .setShowNumberIndicator(true)
                    .setMaxSize(1)
                    .setLimitMessage("You can select one image")

                    .setRequestCode(requestCode)
                    .start();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 1)) {


            if (data != null) {
                ArrayList<Image> images = ImagePicker.getImages(data);
                String filePath = images.get(0).getPath().toString();
                Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);
                image_id.setImageBitmap(selectedImagea);
                image_id_file = new File(filePath);
//                try {
//                    RequestParams requestParams = new RequestParams();
//                    requestParams.put("photo", file_image_profile);
//
//                } catch (Exception e) {
//                }


            }
        }
        if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 2)) {


            if (data != null) {
                ArrayList<Image> images = ImagePicker.getImages(data);
                String filePath = images.get(0).getPath().toString();
                Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);
//                image_id.setImageBitmap(selectedImagea);
//                get_id_image_file = new File(filePath);
//                try {
//                    RequestParams requestParams = new RequestParams();
//                    requestParams.put("photo", file_image_profile);
//
//                } catch (Exception e) {
//                }
            }
        }
        if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 3)) {


            if (data != null) {
                ArrayList<Image> images = ImagePicker.getImages(data);
                String filePath = images.get(0).getPath().toString();
                Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);
//                image_id_owner.setImageBitmap(selectedImagea);
//                owner_get_id_image_file = new File(filePath);
//                try {
//                    RequestParams requestParams = new RequestParams();
//                    requestParams.put("photo", file_image_profile);
//
//                } catch (Exception e) {
//                }
            }
        }
        if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 4)) {


            if (data != null) {
                ArrayList<Image> images = ImagePicker.getImages(data);
                String filePath = images.get(0).getPath().toString();
                Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);
                natinal_image.setImageBitmap(selectedImagea);
                National_address_file = new File(filePath);
//                try {
//                    RequestParams requestParams = new RequestParams();
//                    requestParams.put("photo", file_image_profile);
//
//                } catch (Exception e) {
//                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);


    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(FinanceActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

//                        Hawk.put("user", data);
                        String message = response.getString("message");

                        WebService.Make_Toast_color(FinanceActivity.this, message, "success");

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(FinanceActivity.this, message, "error");
                    }


                } catch (Exception e) {

                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(FinanceActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(FinanceActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(FinanceActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(FinanceActivity.this, false);

                WebService.Make_Toast_color(FinanceActivity.this, error, "error");


            }
        };


    }


}
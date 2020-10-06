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

import aqarz.revival.sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_Type_RequstService;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_SelectBanks;
import aqarz.revival.sa.aqarz.Dialog.BottomSheetDialogFragment_SelectCity;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import aqarz.revival.sa.aqarz.Settings.WebService;
import aqarz.revival.sa.aqarz.api.IResult;
import aqarz.revival.sa.aqarz.api.VolleyService;
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

public class RentActivity extends AppCompatActivity {
    TextView circle_2, circle_3;
    LinearLayout line;
    Button next_to_2;
    Button next_to_3;
    Button finish;

    LinearLayout layout_1, layout_2, layout_3;
    IResult mResultCallback;

    BottomSheetDialogFragment_SelectBanks bottomSheetDialogFragment_selectBanks;
    BottomSheetDialogFragment_SelectCity bottomSheetDialogFragment_selectCity;
    String current_page = "1";

    ImageView back;

    RecyclerView opration_RecyclerView;
    List<TypeModules> type_list = new ArrayList<>();


    TextView month;
    TextView year;


    String opration_select = "";
    String contract_interval = "year";


    EditText price, name_owner, phone_owner, owner_id_number;
    EditText name, phone, id_number;
    EditText name_city, total_sallary, Financial_obligations;
    EditText National_address, buldingnumber, StreetName;
    EditText Neighborhoodname, Postal_code, additional_number, unit_number;
    TextView start_work_date;
    TextView banks;
    TextView date_bertih;
    TextView city;


    ImageView image_of_contract;
    ImageView image_id;
    ImageView image_id_owner;
    ImageView natinal_image;

LinearLayout addres_layount;
    TextView governmental;
    TextView Special;
    TextView Soldier;
    String tenant_job_type = "governmental";
    String city_id = "";

    String banks_id = "";
    Switch switch_;
    boolean is_switched = true;

    File National_address_file = null;
    File owner_get_id_image_file = null;
    File contract_file_file = null;
    File get_id_image_file = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        init();


    }

    public void init() {

        month = findViewById(R.id.month);
        year = findViewById(R.id.year);
        circle_2 = findViewById(R.id.circle_2);
        circle_3 = findViewById(R.id.circle_3);
        line = findViewById(R.id.line);
        next_to_3 = findViewById(R.id.next_to_3);
        next_to_2 = findViewById(R.id.next_to_2);
        layout_1 = findViewById(R.id.layout_1);
        layout_2 = findViewById(R.id.layout_2);
        layout_3 = findViewById(R.id.layout_3);
        back = findViewById(R.id.back);
        opration_RecyclerView = findViewById(R.id.opration_RecyclerView);


        price = findViewById(R.id.price);
        name_owner = findViewById(R.id.name_owner);
        phone_owner = findViewById(R.id.phone_owner);
        owner_id_number = findViewById(R.id.owner_id_number);
        image_of_contract = findViewById(R.id.image_of_contract);
        image_id_owner = findViewById(R.id.image_id_owner);


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

        Soldier = findViewById(R.id.Soldier);
        Special = findViewById(R.id.Special);
        governmental = findViewById(R.id.governmental);


        National_address = findViewById(R.id.National_address);
        buldingnumber = findViewById(R.id.buldingnumber);
        StreetName = findViewById(R.id.StreetName);
        Neighborhoodname = findViewById(R.id.Neighborhoodname);
        Postal_code = findViewById(R.id.Postal_code);
        additional_number = findViewById(R.id.additional_number);
        unit_number = findViewById(R.id.unit_number);
        city = findViewById(R.id.city);
        natinal_image = findViewById(R.id.natinal_image);
        switch_ = findViewById(R.id.switch_);
        finish = findViewById(R.id.finish);
        addres_layount = findViewById(R.id.addres_layount);


        init_volley();

        //---------------------------------------------------------
        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(RentActivity.this, LinearLayoutManager.HORIZONTAL, false);
        opration_RecyclerView.setLayoutManager(layoutManager1);

        RecyclerView_Type_RequstService recyclerView_type_requstService = new RecyclerView_Type_RequstService(RentActivity.this, type_list);
        recyclerView_type_requstService.addItemClickListener(new RecyclerView_Type_RequstService.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

                opration_select = type_list.get(position).getId().toString() + "";

            }
        });
        opration_RecyclerView.setAdapter(recyclerView_type_requstService);


        action_button();
        next_step();
    }

    public void action_button() {


        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month.setBackground(getResources().getDrawable(R.drawable.button_login));

                month.setTextColor(getResources().getColor(R.color.white));

                year.setBackground(null);

                year.setTextColor(getResources().getColor(R.color.textColor));
                contract_interval = "six_month";

            }
        });


        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year.setBackground(getResources().getDrawable(R.drawable.button_login));

                year.setTextColor(getResources().getColor(R.color.white));


                month.setBackground(null);

                month.setTextColor(getResources().getColor(R.color.textColor));
                contract_interval = "year";

            }
        });
        image_of_contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                select_image_from_local(1, 1);


            }
        });
        image_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                select_image_from_local(2, 2);


            }
        });
        image_id_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                select_image_from_local(3, 3);


            }
        });
        natinal_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                select_image_from_local(4, 4);


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
                new DatePickerDialog(RentActivity.this, datebitth, myCalendar
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
                new DatePickerDialog(RentActivity.this, date_start_work, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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

        switch_.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                is_switched = isChecked;



                if (isChecked) {
                    addres_layount.setVisibility(View.VISIBLE);
                } else {
                    addres_layount.setVisibility(View.GONE);
                }



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

    }


    public void next_step() {

        next_to_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                if (price.getText().toString().equals("")) {
//
//
//                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.Price) + " " + getResources().getString(R.string.is_requred), "error");
//
//
//                } else if (name_owner.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.name) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (phone_owner.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.phone_number) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (owner_id_number.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.id_number) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else
                    {

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

//                if (name.getText().toString().equals("")) {
//
//
//                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.name) + " " + getResources().getString(R.string.is_requred), "error");
//
//
//                } else if (phone.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.phone_number) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (date_bertih.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.Date_of_Birth) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (id_number.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.id_number) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (name_city.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.city) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (start_work_date.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.startworkdate) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (total_sallary.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.Total_salary) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (banks.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.Salary_Transferred) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else if (Financial_obligations.getText().toString().equals("")) {
//                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.Financial_obligations) + " " + getResources().getString(R.string.is_requred), "error");
//
//                } else
                    {


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

                if (National_address.getText().toString().equals("")) {


                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.National_address) + " " + getResources().getString(R.string.is_requred), "error");


                } else if (buldingnumber.getText().toString().equals("")) {
                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.buldingnumber) + " " + getResources().getString(R.string.is_requred), "error");

                } else if (StreetName.getText().toString().equals("")) {
                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.StreetName) + " " + getResources().getString(R.string.is_requred), "error");

                } else if (Neighborhoodname.getText().toString().equals("")) {
                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.Neighborhoodname) + " " + getResources().getString(R.string.is_requred), "error");

                } else if (city.getText().toString().equals("")) {
                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.city_name) + " " + getResources().getString(R.string.is_requred), "error");

                } else if (Postal_code.getText().toString().equals("")) {
                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.Postal_code) + " " + getResources().getString(R.string.is_requred), "error");

                } else if (additional_number.getText().toString().equals("")) {
                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.additional_number) + " " + getResources().getString(R.string.is_requred), "error");

                } else if (unit_number.getText().toString().equals("")) {
                    WebService.Make_Toast_color(RentActivity.this, getResources().getString(R.string.unit_number) + " " + getResources().getString(R.string.is_requred), "error");

                } else {
                    WebService.loading(RentActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, RentActivity.this);

                    RequestParams sendObj = new RequestParams();

                    try {

                        sendObj.put("operation_type_id", "1");//form operation list api in setting
                        sendObj.put("estate_type_id", opration_select);//form estate type list api in setting
                        sendObj.put("contract_interval", contract_interval);//'year','six_month'
                        if (contract_file_file != null) {
                            sendObj.put("contract_file", contract_file_file);//

                        }
                        sendObj.put("rent_price", price.getText().toString());//
                        sendObj.put("owner_name", name_owner.getText().toString());//
                        sendObj.put("owner_mobile", phone_owner.getText().toString());//
                        sendObj.put("owner_identity_number", owner_id_number.getText().toString());//
                        if (owner_get_id_image_file != null) {
                            sendObj.put("owner_identity_file", owner_get_id_image_file);//

                        }
                        sendObj.put("tenant_name", name.getText().toString());//
                        sendObj.put("tenant_mobile", phone.getText().toString());//
                        sendObj.put("tenant_identity_number", id_number.getText().toString());//


                        if (get_id_image_file != null) {
                            sendObj.put("tenant_identity_file", get_id_image_file);//

                        }
                        sendObj.put("tenant_birthday", date_bertih.getText().toString());//
                        sendObj.put("tenant_city_id", city_id);//
                        sendObj.put("tenant_job_type", tenant_job_type);//'governmental','special','soldier'
                        sendObj.put("tenant_job_start_date", start_work_date.getText().toString());//
                        sendObj.put("tenant_total_salary", total_sallary.getText().toString());//
                        sendObj.put("tenant_salary_bank_id", banks_id);//
                        sendObj.put("tenant_engagements", Financial_obligations.getText().toString());//
                        sendObj.put("national_address", "545");//National_address.getText().toString()
                        if (National_address_file != null) {
                            sendObj.put("national_address_file", National_address_file);//

                        }
                        sendObj.put("building_number", buldingnumber.getText().toString());//
                        sendObj.put("street_name", StreetName.getText().toString());//
                        sendObj.put("neighborhood_name", Neighborhoodname.getText().toString());//
                        sendObj.put("building_city_name", buldingnumber.getText().toString());//
                        sendObj.put("postal_code", Postal_code.getText().toString());//


                        System.out.println(sendObj.toString());
                        mVolleyService.postDataasync_with_file("ADDREqust", WebService.deferredInstallment, sendObj);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


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

    public void select_image_from_local(int permission, int st_code) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(RentActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(RentActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        permission);

            } else {


                ImagePicker.with(RentActivity.this)
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

            ImagePicker.with(RentActivity.this)
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

        if (ContextCompat.checkSelfPermission(RentActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        } else {
            ImagePicker.with(RentActivity.this)
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
                image_of_contract.setImageBitmap(selectedImagea);
                contract_file_file = new File(filePath);
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
                image_id.setImageBitmap(selectedImagea);
                get_id_image_file = new File(filePath);
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
                image_id_owner.setImageBitmap(selectedImagea);
                owner_get_id_image_file = new File(filePath);
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
                WebService.loading(RentActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

//                        Hawk.put("user", data);
                        String message = response.getString("message");

                        WebService.Make_Toast_color(RentActivity.this, message, "success");

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(RentActivity.this, message, "error");
                    }


                } catch (Exception e) {

                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);


                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(RentActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(RentActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

}
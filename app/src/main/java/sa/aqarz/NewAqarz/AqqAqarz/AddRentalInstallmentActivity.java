package sa.aqarz.NewAqarz.AqqAqarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sa.aqarz.Activity.OprationNew.RentActivity;
import sa.aqarz.Activity.OprationNew.RequestServiceActivity;
import sa.aqarz.Dialog.BottomSheetDialogFragment_SelectCity;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_All_type_in_Rent;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_All_type_in_home_List;
import sa.aqarz.NewAqarz.Adapter.RecyclerView_type_work_rent;
import sa.aqarz.NewAqarz.ListAqarzActivity;
import sa.aqarz.NewAqarz.MainAqarzActivity;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;

public class AddRentalInstallmentActivity extends AppCompatActivity {
    RecyclerView all_type_aqarz;
    List<TypeModules> type_list = new ArrayList<>();
    static String type_filtter = "";
    static String type_work = "";
    static String city_id = "";

    List<TypeModules> type_list_work = new ArrayList<>();

    TextView Bank;
    TextView finance_company_title;
    TextView month;
    SeekBar seek_bar;

    TextView city;
    String contract_interval = "";

    RecyclerView list_type_work;

    TextView yes_1;
    TextView no_1;

    TextView yes_2;
    TextView no_2;

    TextView yes_3;
    TextView no_3;


    EditText price;
    EditText name;
    EditText phone_owner;
    TextView date_bertih;
    EditText owner_id_number;
    EditText Total_salary;
    EditText employ_name;
    EditText personal_financing;
    EditText lease_finance;
    EditText creditcard;
    EditText Amount_of_tripping;

    String is_salary_adapter_on = "0";
    String financialbanksfailures = "0";
    String previous_financial_failures = "0";
    String Bank_or_finance_company_title = "bank";

    Button approve;
    IResult mResultCallback;
    LinearLayout request_qustion;
    BottomSheetDialogFragment_SelectCity bottomSheetDialogFragment_selectCity;


    LinearLayout a1;
    LinearLayout a2;
    LinearLayout a3;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rental_installment);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        back = findViewById(R.id.back);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (back != null) {
                back.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        inti();
    }

    public void inti() {
        all_type_aqarz = findViewById(R.id.all_type_aqarz);

        Bank = findViewById(R.id.Bank);
        finance_company_title = findViewById(R.id.finance_company_title);
        seek_bar = findViewById(R.id.seek_bar);
        month = findViewById(R.id.month);
        price = findViewById(R.id.price);
        name = findViewById(R.id.name);
        phone_owner = findViewById(R.id.phone_owner);
        date_bertih = findViewById(R.id.date_bertih);
        owner_id_number = findViewById(R.id.owner_id_number);
        list_type_work = findViewById(R.id.list_type_work);
        city = findViewById(R.id.city);
        employ_name = findViewById(R.id.employ_name);
        Total_salary = findViewById(R.id.Total_salary);
        yes_1 = findViewById(R.id.yes_1);
        yes_2 = findViewById(R.id.yes_2);
        no_1 = findViewById(R.id.no_1);
        no_2 = findViewById(R.id.no_2);
        Amount_of_tripping = findViewById(R.id.Amount_of_tripping);
        request_qustion = findViewById(R.id.request_qustion);

        yes_3 = findViewById(R.id.yes_3);
        no_3 = findViewById(R.id.no_3);
        personal_financing = findViewById(R.id.personal_financing);
        lease_finance = findViewById(R.id.lease_finance);
        creditcard = findViewById(R.id.creditcard);
        approve = findViewById(R.id.approve);
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);


        try {


            if (Settings.GetUser().getName() != null) {
                if (!Settings.GetUser().getName().equals("null")) {
                    name.setText(Settings.GetUser().getName() + "");

                }
            }
            phone_owner.setText(Settings.GetUser().getMobile() + "");

        } catch (Exception e) {

        }
        type_aqarz();
        actionbutton();
        set_type_work();
        actionbuttonYes_No();
        action_btn();

    }

    public void type_aqarz() {


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(AddRentalInstallmentActivity.this, LinearLayoutManager.HORIZONTAL, false);
        all_type_aqarz.setLayoutManager(layoutManager1);

        if (MainAqarzActivity.object_filtter.getType_list() != null) {
            if (MainAqarzActivity.object_filtter.getType_list().size() != 0) {
                type_list = MainAqarzActivity.object_filtter.getType_list();
            } else {
                type_list = Settings.getSettings().getEstate_types().getOriginal().getData();
            }


        } else {
            type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        }
        RecyclerView_All_type_in_Rent recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_Rent(AddRentalInstallmentActivity.this, type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_Rent.ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                type_filtter = "" + type_list.get(pos).getId();

//                for (int i = 0; i < typeModules.size(); i++) {
//                    if (typeModules.get(i).isIsselected()) {
//
//                        if (type_filtter.equals("")) {
//                            type_filtter = "" + typeModules.get(i).getId();
//                        } else {
//                            type_filtter = type_filtter + "," + typeModules.get(i).getId();
//
//                        }
//                    }
//                }
//

//                MainAqarzActivity.object_filtter.setType_aqarz(type_filtter);
//                MainAqarzActivity.object_filtter.setType_list(typeModules);

//                get_data();


            }
        });
        all_type_aqarz.setAdapter(recyclerView_all_type_in_fragment);

    }

    public void actionbutton() {
        Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bank_or_finance_company_title = "bank";
                Bank.setBackground(getResources().getDrawable(R.drawable.button_login1));

                Bank.setTextColor(getResources().getColor(R.color.white));

                finance_company_title.setBackground(getResources().getDrawable(R.drawable.button_loginc));

                finance_company_title.setTextColor(getResources().getColor(R.color.textColor));
                request_qustion.setVisibility(View.VISIBLE);
            }
        });
        finance_company_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bank_or_finance_company_title = "finance_company";

                finance_company_title.setBackground(getResources().getDrawable(R.drawable.button_login1));

                finance_company_title.setTextColor(getResources().getColor(R.color.white));

                Bank.setBackground(getResources().getDrawable(R.drawable.button_loginc));

                Bank.setTextColor(getResources().getColor(R.color.textColor));
                request_qustion.setVisibility(View.GONE);

            }
        });
        //---------------------------------------------------------------------------------------------
        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                contract_interval = progress + "";
                month.setText(contract_interval);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialogFragment_selectCity = new BottomSheetDialogFragment_SelectCity("");
                bottomSheetDialogFragment_selectCity.addItemClickListener(new BottomSheetDialogFragment_SelectCity.ItemClickListener() {
                    @Override
                    public void onItemClick(int id_city, String city_naem) {
                        city.setText(city_naem);
                        city_id = id_city + "";
                        bottomSheetDialogFragment_selectCity.dismiss();
                    }
                });
                bottomSheetDialogFragment_selectCity.show(getSupportFragmentManager(), "");


            }
        });
        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = format.format(calendar.getTime());

                date_bertih.setText(strDate);

            }

        };

        date_bertih.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddRentalInstallmentActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void actionbuttonYes_No() {
        yes_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_salary_adapter_on = "1";
                yes_1.setBackground(getResources().getDrawable(R.drawable.button_login1));

                yes_1.setTextColor(getResources().getColor(R.color.white));

                no_1.setBackground(null);

                no_1.setTextColor(getResources().getColor(R.color.textColor));

            }
        });
        no_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_salary_adapter_on = "0";

                no_1.setBackground(getResources().getDrawable(R.drawable.button_login1));

                no_1.setTextColor(getResources().getColor(R.color.white));

                yes_1.setBackground(null);

                yes_1.setTextColor(getResources().getColor(R.color.textColor));

            }
        });
        yes_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amount_of_tripping.setVisibility(View.VISIBLE);
                financialbanksfailures = "1";
                yes_2.setBackground(getResources().getDrawable(R.drawable.button_login1));

                yes_2.setTextColor(getResources().getColor(R.color.white));

                no_2.setBackground(null);

                no_2.setTextColor(getResources().getColor(R.color.textColor));

            }
        });
        no_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amount_of_tripping.setVisibility(View.GONE);

                financialbanksfailures = "0";

                no_2.setBackground(getResources().getDrawable(R.drawable.button_login1));

                no_2.setTextColor(getResources().getColor(R.color.white));

                yes_2.setBackground(null);

                yes_2.setTextColor(getResources().getColor(R.color.textColor));

            }
        });
        yes_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a1.setVisibility(View.VISIBLE);
                a2.setVisibility(View.VISIBLE);
                a3.setVisibility(View.VISIBLE);
                previous_financial_failures = "1";
                yes_3.setBackground(getResources().getDrawable(R.drawable.button_login1));

                yes_3.setTextColor(getResources().getColor(R.color.white));

                no_3.setBackground(null);

                no_3.setTextColor(getResources().getColor(R.color.textColor));

            }
        });
        no_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous_financial_failures = "0";
                a1.setVisibility(View.GONE);
                a2.setVisibility(View.GONE);
                a3.setVisibility(View.GONE);
                no_3.setBackground(getResources().getDrawable(R.drawable.button_login1));

                no_3.setTextColor(getResources().getColor(R.color.white));

                yes_3.setBackground(null);

                yes_3.setTextColor(getResources().getColor(R.color.textColor));

            }
        });


    }


    public void set_type_work() {
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(AddRentalInstallmentActivity.this, LinearLayoutManager.HORIZONTAL, false);
        list_type_work.setLayoutManager(layoutManager1);


        type_list_work.clear();
        type_list_work.add(new TypeModules(1, getResources().getString(R.string.governmental), "governmental"));
        type_list_work.add(new TypeModules(1, getResources().getString(R.string.Special), "special"));
        type_list_work.add(new TypeModules(1, getResources().getString(R.string.Soldier), "soldier"));
        type_list_work.add(new TypeModules(1, getResources().getString(R.string.Soldier_sf), "retired"));

        RecyclerView_type_work_rent recyclerView_type_work_rent = new RecyclerView_type_work_rent(AddRentalInstallmentActivity.this, type_list_work);
        recyclerView_type_work_rent.addItemClickListener(new RecyclerView_type_work_rent.ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                type_work = type_list_work.get(pos).getIcon() + "";

//                for (int i = 0; i < typeModules.size(); i++) {
//                    if (typeModules.get(i).isIsselected()) {
//
//                        if (type_filtter.equals("")) {
//                            type_filtter = "" + typeModules.get(i).getId();
//                        } else {
//                            type_filtter = type_filtter + "," + typeModules.get(i).getId();
//
//                        }
//                    }
//                }
//


//                get_data();


            }
        });
        list_type_work.setAdapter(recyclerView_type_work_rent);

    }

    public void action_btn() {
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (type_filtter.equals("") | price.getText().toString().equals("")
                        | price.getText().toString().equals("")
                        | name.getText().toString().equals("")
                        | phone_owner.getText().toString().equals("")
                        | owner_id_number.getText().toString().equals("")
                        | date_bertih.getText().toString().equals("")
                        | city_id.equals("")) {

                    WebService.Make_Toast_color(AddRentalInstallmentActivity.this, getResources().getString(R.string.fillallfileds1), "false");

                } else {

                    init_volley();
                    WebService.loading(AddRentalInstallmentActivity.this, true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, AddRentalInstallmentActivity.this);

                    RequestParams sendObj = new RequestParams();

                    try {

                        sendObj.put("operation_type_id", "1");//form operation list api in setting
                        sendObj.put("estate_type_id", type_filtter);//form estate type list api in setting


                        sendObj.put("contract_interval", contract_interval);//'year','six_month'


                        sendObj.put("rent_price", price.getText().toString());//
                        sendObj.put("tenant_name", name.getText().toString());//
                        sendObj.put("tenant_mobile", phone_owner.getText().toString());//
                        sendObj.put("tenant_identity_number", owner_id_number.getText().toString());//
                        sendObj.put("tenant_birthday", date_bertih.getText().toString());//
//                    sendObj.put("estat_id", id);//


                        sendObj.put("tenant_city_id", city_id);//
                        sendObj.put("tenant_job_type", type_work);//'governmental','special','soldier'

                        sendObj.put("tenant_total_salary", Total_salary.getText().toString());//
//                    sendObj.put("tenant_salary_bank_id", Total_salary.getText().toString());//

                        sendObj.put("tenant_engagements", financialbanksfailures);//


                        sendObj.put("is_salary_adapter_on", is_salary_adapter_on);//
                        sendObj.put("previous_financial_failures", previous_financial_failures);//
                        sendObj.put("personal_financing_engagements", personal_financing.getText().toString());//
                        sendObj.put("lease_finance_engagements", lease_finance.getText().toString());//
                        sendObj.put("credit_card_engagements", creditcard.getText().toString());//


                        sendObj.put("financing_body", Bank_or_finance_company_title);//
                        sendObj.put("stumble_amount", Amount_of_tripping.getText().toString());//


                        System.out.println(sendObj.toString());
                        mVolleyService.postDataasync_with_file("ADDREqust", WebService.deferredInstallment, sendObj);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(AddRentalInstallmentActivity.this, false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

//                        Hawk.put("user", data);
                        String message = response.getString("message");
                        BottomSheetDialog bottomSheerDialog = new BottomSheetDialog(AddRentalInstallmentActivity.this);
                        View parentView = getLayoutInflater().inflate(R.layout.rent_message, null);
                        Button close = parentView.findViewById(R.id.close);
                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                RequestServiceActivity.close();
                                finish();

                            }
                        });
                        bottomSheerDialog.setContentView(parentView);
                        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
                        bottomSheerDialog.show();
//                        WebService.Make_Toast_color(RentActivity.this, message, "success");

                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(AddRentalInstallmentActivity.this, message, "error");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("TAG", "Volley requester " + requestType);

                WebService.loading(AddRentalInstallmentActivity.this, false);

                try {

                    NetworkResponse response = error.networkResponse;
                    String response_data = new String(response.data);

                    JSONObject jsonObject = new JSONObject(response_data);

                    String message = jsonObject.getString("message");


                    WebService.Make_Toast_color(AddRentalInstallmentActivity.this, message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(AddRentalInstallmentActivity.this, false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {
                WebService.loading(AddRentalInstallmentActivity.this, false);

                WebService.Make_Toast_color(AddRentalInstallmentActivity.this, error, "error");


            }
        };


    }

}
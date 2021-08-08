package sa.aqarz.Fragment.TypeOrders;
/**
 * Created by osama on 10/8/2017.
 */


import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.RequestParams;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import sa.aqarz.Activity.Auth.ConfirmationActivity;
import sa.aqarz.Activity.Auth.LoginActivity;
import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.Auth.NewPasswordActivity;
import sa.aqarz.Activity.Auth.RegisterActivity;
import sa.aqarz.Activity.OprationAqarz.RequestOrderActivity;
import sa.aqarz.Activity.SplashScreenActivity;
import sa.aqarz.Adapter.RecyclerView_All_Opration_in_order;
import sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import sa.aqarz.Dialog.BottomSheetDialogFragment_SelectBanks;
import sa.aqarz.Dialog.BottomSheetDialogFragment_SelectCity;
import sa.aqarz.Modules.OprationModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.Modules.User;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;


public class type1Fragment extends Fragment {

    RecyclerView opration_RecyclerView;

    BottomSheetDialogFragment_SelectCity bottomSheetDialogFragment_selectCity;
    BottomSheetDialogFragment_SelectBanks bottomSheetDialogFragment_selectBanks;

    IResult mResultCallback;

    List<TypeModules> type_list = new ArrayList<>();

    TextView month;
    TextView year;
    TextView governmental;
    TextView Special;
    TextView Soldier;
    LinearLayout get_id_image;
    LinearLayout owner_get_id_image;

    TextView date_bertih;
    TextView start_work_date;
    TextView contract_file;

    File owner_get_id_image_file = null;
    File contract_file_file = null;
    File get_id_image_file = null;
    String tenant_job_type = "governmental";
    String contract_interval = "year";

    TextView city;
    String city_id = "";
    TextView banks;
    String banks_id = "";
    TextView National_address;

    File National_address_file = null;
    EditText price, name_owner, phone_owner, name, phone, id_number, total_sallary, Financial_obligations, buldingnumber, StreetName;
    EditText Neighborhoodname, name_city, Postal_code, additional_number, unit_number, owner_id_number;

    Button btn_send;

    String opration_select = "";

    String Id_eastate = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.requst_order_type_1, container, false);

        Id_eastate = getArguments().getString("Id_eastate");


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
        get_id_image = v.findViewById(R.id.get_id_image);
        owner_id_number = v.findViewById(R.id.owner_id_number);
        owner_get_id_image = v.findViewById(R.id.owner_get_id_image);
        contract_file = v.findViewById(R.id.contract_file);


        type_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        System.out.println("type_list" + type_list.size());


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        opration_RecyclerView.setLayoutManager(layoutManager1);

        RecyclerView_All_type_in_fragment recyclerView_all_type_in_fragment = new RecyclerView_All_type_in_fragment(getContext(), type_list);
        recyclerView_all_type_in_fragment.addItemClickListener(new RecyclerView_All_type_in_fragment.ItemClickListener() {
            @Override
            public void onItemClick(int position) {

                opration_select = type_list.get(position).getId().toString() + "";

            }
        });
        opration_RecyclerView.setAdapter(recyclerView_all_type_in_fragment);
//
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month.setBackground(getResources().getDrawable(R.drawable.button_login));

                month.setTextColor(getResources().getColor(R.color.white));

                year.setBackground(getResources().getDrawable(R.drawable.search_background));

                year.setTextColor(getResources().getColor(R.color.textColor));
                contract_interval = "six_month";

            }
        });


        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year.setBackground(getResources().getDrawable(R.drawable.button_login));

                year.setTextColor(getResources().getColor(R.color.white));

                month.setBackground(getResources().getDrawable(R.drawable.search_background));

                month.setTextColor(getResources().getColor(R.color.textColor));
                contract_interval = "year";

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
        National_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                11);

                    } else {

                        Intent intent = new Intent(getContext(), ImageSelectActivity.class);
                        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, false);//default is true
                        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                        startActivityForResult(intent, 1213);
                    }
                } else {
                    Intent intent = new Intent(getContext(), ImageSelectActivity.class);
                    intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_CAMERA, false);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                    startActivityForResult(intent, 1213);

                }


            }
        });
        contract_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                15);

                    } else {
                        Intent intent = new Intent(getContext(), ImageSelectActivity.class);
                        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);//default is true
                        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, false);//default is true
                        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                        startActivityForResult(intent, 1215);
                    }
                } else {
                    Intent intent = new Intent(getContext(), ImageSelectActivity.class);
                    intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_CAMERA, false);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                    startActivityForResult(intent, 1215);
                }

            }
        });
        owner_get_id_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                13);
                    } else {

                        Intent intent = new Intent(getContext(), ImageSelectActivity.class);
                        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, false);//default is true
                        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                        startActivityForResult(intent, 1217);

                    }
                } else {
                    Intent intent = new Intent(getContext(), ImageSelectActivity.class);
                    intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_CAMERA, false);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                    startActivityForResult(intent, 1217);

                }

            }
        });
        get_id_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                12);

                    } else {

                        Intent intent = new Intent(getContext(), ImageSelectActivity.class);
                        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, false);//default is true
                        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                        startActivityForResult(intent, 1214);
                    }
                } else {
                    Intent intent = new Intent(getContext(), ImageSelectActivity.class);
                    intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_CAMERA, false);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                    startActivityForResult(intent, 1214);

                }

            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name_owner.getText().toString().equals("") |
                        date_bertih.getText().toString().equals("") |
                        start_work_date.getText().toString().equals("") |
                        city.getText().toString().equals("") |
                        banks.getText().toString().equals("") |
                        National_address.getText().toString().equals("") |
                        price.getText().toString().equals("") |
                        name_owner.getText().toString().equals("") |
                        phone_owner.getText().toString().equals("") |
                        name.getText().toString().equals("") |
                        phone.getText().toString().equals("") |
                        id_number.getText().toString().equals("") |
                        total_sallary.getText().toString().equals("") |
                        buldingnumber.getText().toString().equals("") |
                        StreetName.getText().toString().equals("") |
                        Neighborhoodname.getText().toString().equals("") |
                        name_city.getText().toString().equals("") |
                        Postal_code.getText().toString().equals("") |
                        additional_number.getText().toString().equals("") |
                        unit_number.getText().toString().equals("")

                ) {
                    WebService.Make_Toast(getActivity(), getResources().getString(R.string.AllFiledsREquered));

                } else {

                    WebService.loading(getActivity(), true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());

                    RequestParams sendObj = new RequestParams();

                    try {

                        sendObj.put("operation_type_id", Id_eastate);//form operation list api in setting
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
                bottomSheetDialogFragment_selectCity.addItemClickListener(new BottomSheetDialogFragment_SelectCity.ItemClickListener() {
                    @Override
                    public void onItemClick(int id_city, String city_naem) {

                        city.setText(city_naem);
                        bottomSheetDialogFragment_selectCity.dismiss();

                    }
                });

                bottomSheetDialogFragment_selectCity.show(getFragmentManager(), "");

            }
        });
        banks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialogFragment_selectBanks = new BottomSheetDialogFragment_SelectBanks("");
                bottomSheetDialogFragment_selectBanks.addItemClickListener(new BottomSheetDialogFragment_SelectBanks.ItemClickListener() {
                    @Override
                    public void onItemClick(int id_bank, String name_bank) {
                        banks.setText(name_bank);
                        bottomSheetDialogFragment_selectBanks.dismiss();
                    }
                });

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

    public void init_volley() {


        mResultCallback = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response) {
                Log.d("TAG", "Volley requester " + requestType);
                Log.d("TAG", "Volley JSON post" + response.toString());
                WebService.loading(getActivity(), false);
//{"status":true,"code":200,"message":"User Profile","data"
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        String data = response.getString("data");

//                        Hawk.put("user", data);


                    } else {
                        String message = response.getString("message");

                        WebService.Make_Toast_color(getActivity(), message, "error");
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


                    WebService.Make_Toast_color(getActivity(), message, "error");

                    Log.e("error response", response_data);

                } catch (Exception e) {

                }

                WebService.loading(getActivity(), false);


            }

            @Override
            public void notify_Async_Error(String requestType, String error) {

            }
        };


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1213 && resultCode == Activity.RESULT_OK) {
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            System.out.println("fdljkfldkfldkfldkfldfk");
            National_address_file = new File(filePath);
            National_address.setBackground(getResources().getDrawable(R.drawable.edit_text_background_green));
//            image_profile.setImageBitmap(selectedImage);
//
//            //            file_path = filePath;
//            image_file_file = new File(filePath);
//
//
//            try {
//
//                RequestParams requestParams = new RequestParams();
//
//                requestParams.put("logo", image_file_file);
//
//
//                Upload_image(requestParams);
//            } catch (Exception e) {
//
//            }


        }
        if (requestCode == 1217 && resultCode == Activity.RESULT_OK) {
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            System.out.println("fdljkfldkfldkfldkfldfk");

            owner_get_id_image_file = new File(filePath);
            owner_get_id_image.setBackground(getResources().getDrawable(R.drawable.edit_text_background_green));
//            image_profile.setImageBitmap(selectedImage);
//
//            //            file_path = filePath;
//            image_file_file = new File(filePath);
//
//
//            try {
//
//                RequestParams requestParams = new RequestParams();
//
//                requestParams.put("logo", image_file_file);
//
//
//                Upload_image(requestParams);
//            } catch (Exception e) {
//
//            }
        }
        if (requestCode == 1215 && resultCode == Activity.RESULT_OK) {
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            System.out.println("fdljkfldkfldkfldkfldfk");
            contract_file_file = new File(filePath);


//            image_profile.setImageBitmap(selectedImage);
//
//            //            file_path = filePath;
//            image_file_file = new File(filePath);
//
//
//            try {
//
//                RequestParams requestParams = new RequestParams();
//
//                requestParams.put("logo", image_file_file);
//
//
//                Upload_image(requestParams);
//            } catch (Exception e) {
//
//            }

        }
        if (requestCode == 1214 && resultCode == Activity.RESULT_OK) {
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            System.out.println("fdljkfldkfldkfldkfldfk");
            get_id_image_file = new File(filePath);
            get_id_image.setBackground(getResources().getDrawable(R.drawable.edit_text_background_green));


//            image_profile.setImageBitmap(selectedImage);
//
//            //            file_path = filePath;
//            image_file_file = new File(filePath);
//
//
//            try {
//
//                RequestParams requestParams = new RequestParams();
//
//                requestParams.put("logo", image_file_file);
//
//
//                Upload_image(requestParams);
//            } catch (Exception e) {
//
//            }

        }
        if (requestCode == 100) {
//            if (resultCode == RESULT_OK) {
//                Place selectedPlace = PlacePicker.getPlace(data, this);
//
//                Latitude = selectedPlace.getLatLng().latitude + "";
//                Longitude = selectedPlace.getLatLng().longitude + "";
//
//
//                System.out.println("Latitude" + Latitude + "Longitude" + Longitude);
//
//                // Do something with the place
//            }
        }
    }

}
package sa.aqarz.Fragment.TypeOrders;
/**
 * Created by osama on 10/8/2017.
 */


import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import sa.aqarz.Activity.Auth.MyProfileInformationActivity;
import sa.aqarz.Activity.OprationAqarz.RequestOrderActivity;
import sa.aqarz.Adapter.RecyclerView_All_Opration_in_order;
import sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import sa.aqarz.Dialog.BottomSheetDialogFragment_SelectCity;
import sa.aqarz.Modules.OprationModules;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;
import sa.aqarz.api.VolleyService;


public class type2Fragment extends Fragment {

    IResult mResultCallback;

    RecyclerView opration_RecyclerView;
    String opration_select = "";
    List<TypeModules> type_list = new ArrayList<>();

    TextView National_address;
    File National_address_file = null;

    TextView governmental;
    TextView Special;
    TextView Soldier;

    String tenant_job_type = "governmental";

    TextView start_work_date;
    LinearLayout owner_get_id_image;
    File owner_get_id_image_file = null;

    EditText priceAqar;
    EditText Financial_obligations;
    TextView city;
    EditText name;
    EditText phone;
    EditText age;
    EditText total_Sallary;
    EditText available_price;
    EditText buliding_number;
    EditText neighborhood_name;
    EditText city_name;
    EditText postalcode;
    EditText additional_number;
    EditText unit_number;
    EditText Solidarity_salary;
    EditText price;//سعر الايجار


    TextView ada_1_yes;
    TextView ada_1_no;
    TextView property_dd_yes;
    TextView property_dd_no;


    BottomSheetDialogFragment_SelectCity bottomSheetDialogFragment_selectCity;


    Switch Solidarity_partner;


    String ada_ = "1";
    String property = "1";
    String city_id = "";

    String solidarity_partner_ = "1";

    Button btn_send;

    EditText StreetName;
    EditText owner_id_number;

    String Id_eastate = "";
    String seek_progress = "";


    SeekBar seek_bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.requst_order_type_2, container, false);
        Id_eastate = this.getArguments().getString("Id_eastate");

        init(v);
        return v;
    }


    public void init(View v) {
        opration_RecyclerView = v.findViewById(R.id.opration_RecyclerView);
        Soldier = v.findViewById(R.id.Soldier);
        Special = v.findViewById(R.id.Special);
        governmental = v.findViewById(R.id.governmental);
        start_work_date = v.findViewById(R.id.start_work_date);
        owner_get_id_image = v.findViewById(R.id.owner_get_id_image);
        National_address = v.findViewById(R.id.National_address);
        priceAqar = v.findViewById(R.id.priceAqar);
        Financial_obligations = v.findViewById(R.id.Financial_obligations);
        city = v.findViewById(R.id.city);
        name = v.findViewById(R.id.name);
        phone = v.findViewById(R.id.phone);
        age = v.findViewById(R.id.age);
        total_Sallary = v.findViewById(R.id.total_Sallary);
        available_price = v.findViewById(R.id.available_price);
        buliding_number = v.findViewById(R.id.buliding_number);
        neighborhood_name = v.findViewById(R.id.neighborhood_name);
        city_name = v.findViewById(R.id.city_name);
        postalcode = v.findViewById(R.id.postalcode);
        additional_number = v.findViewById(R.id.additional_number);
        unit_number = v.findViewById(R.id.unit_number);
        Solidarity_salary = v.findViewById(R.id.Solidarity_salary);
        price = v.findViewById(R.id.price);
        ada_1_yes = v.findViewById(R.id.ada_1_yes);
        ada_1_no = v.findViewById(R.id.ada_1_no);
        property_dd_yes = v.findViewById(R.id.property_dd_yes);
        property_dd_no = v.findViewById(R.id.property_dd_no);
        Solidarity_partner = v.findViewById(R.id.Solidarity_partner);
        btn_send = v.findViewById(R.id.btn_send);
        owner_id_number = v.findViewById(R.id.owner_id_number);
        StreetName = v.findViewById(R.id.StreetName);
        seek_bar = v.findViewById(R.id.seek_bar);


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

        Solidarity_partner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    solidarity_partner_ = "1";
                } else {
                    solidarity_partner_ = "0";
                }
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
//---------------------------------------------------------------------------------------------
        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date_start_work = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                String myFormat = "yyyy-MM-dd"; //In which you need put here
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

//---------------------------------------------------------------------------------------------
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

                        ImagePicker.Builder imagePicker = ImagePicker.with(getActivity())
                                .setFolderMode(true)
                                .setFolderTitle("Album")

                                .setDirectoryName("Image Picker")
                                .setMultipleMode(false)
                                .setShowNumberIndicator(true)
                                .setMaxSize(1)
                                .setLimitMessage("You can select up to 1 images")

                                .setRequestCode(1213);
                        imagePicker.start();

                        startActivityForResult(imagePicker.getIntent(), 1213);
                    }
                } else {
                    ImagePicker.Builder imagePicker = ImagePicker.with(getActivity())
                            .setFolderMode(true)
                            .setFolderTitle("Album")

                            .setDirectoryName("Image Picker")
                            .setMultipleMode(false)
                            .setShowNumberIndicator(true)
                            .setMaxSize(1)
                            .setLimitMessage("You can select up to 1 images")

                            .setRequestCode(1213);
                    imagePicker.start();

                    startActivityForResult(imagePicker.getIntent(), 1213);

                }


            }
        });
//---------------------------------------------------------------------------------------------
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

                        ImagePicker.Builder imagePicker = ImagePicker.with(getActivity())
                                .setFolderMode(true)
                                .setFolderTitle("Album")

                                .setDirectoryName("Image Picker")
                                .setMultipleMode(false)
                                .setShowNumberIndicator(true)
                                .setMaxSize(1)
                                .setLimitMessage("You can select up to 1 images")

                                .setRequestCode(1217);
                        imagePicker.start();

                        startActivityForResult(imagePicker.getIntent(), 1217);

                    }
                } else {
                    ImagePicker.Builder imagePicker = ImagePicker.with(getActivity())
                            .setFolderMode(true)
                            .setFolderTitle("Album")

                            .setDirectoryName("Image Picker")
                            .setMultipleMode(false)
                            .setShowNumberIndicator(true)
                            .setMaxSize(1)
                            .setLimitMessage("You can select up to 1 images")

                            .setRequestCode(1217);
                    imagePicker.start();

                    startActivityForResult(imagePicker.getIntent(), 1217);
                }


            }
        });
//---------------------------------------------------------------------------------------------

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

//---------------------------------------------------------------------------------------
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

                bottomSheetDialogFragment_selectCity.show(getFragmentManager(), "");


            }
        });
        init_volley();
//-------------------------------------------------------------------------------------
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (start_work_date.getText().toString().equals("") |
                        priceAqar.getText().toString().equals("") |
                        Financial_obligations.getText().toString().equals("") |
                        city_name.getText().toString().equals("") |
                        name.getText().toString().equals("") |
                        owner_id_number.getText().toString().equals("") |
                        phone.getText().toString().equals("") |
                        age.getText().toString().equals("") |
                        total_Sallary.getText().toString().equals("") |
//                        price.getText().toString().equals("") |
                        available_price.getText().toString().equals("") |
                        buliding_number.getText().toString().equals("") |
                        neighborhood_name.getText().toString().equals("") |
                        city.getText().toString().equals("") |
                        postalcode.getText().toString().equals("") |
                        additional_number.getText().toString().equals("") |
                        unit_number.getText().toString().equals("") |
                        Solidarity_salary.getText().toString().equals("")

                ) {
                    WebService.Make_Toast(getActivity(), getResources().getString(R.string.AllFiledsREquered));
                } else {

                    WebService.loading(getActivity(), true);

                    VolleyService mVolleyService = new VolleyService(mResultCallback, getContext());


                    RequestParams sendObj = new RequestParams();

                    try {

                        sendObj.put("operation_type_id", Id_eastate);//form operation list api in setting
                        sendObj.put("estate_type_id", opration_select);//form estate type list api in setting
                        sendObj.put("job_type", tenant_job_type);

                        sendObj.put("finance_interval", seek_progress);//from seek bar
                        sendObj.put("job_start_date", start_work_date.getText().toString());
                        sendObj.put("estate_price", priceAqar.getText().toString());
                        sendObj.put("engagements", Financial_obligations.getText().toString());
                        sendObj.put("city_id", city_id);
                        sendObj.put("name", name.getText().toString());
                        sendObj.put("identity_number", owner_id_number.getText().toString());
                        if (owner_get_id_image_file != null) {
                            sendObj.put("identity_file", owner_get_id_image_file);//
                        }


                        sendObj.put("mobile", phone.getText().toString());
                        sendObj.put("age", age.getText().toString());
                        sendObj.put("total_salary", total_Sallary.getText().toString());
                        sendObj.put("available_amount", available_price.getText().toString());
                        sendObj.put("national_address", "6855");

                        if (National_address_file != null) {
                            sendObj.put("national_address_file", National_address_file);//

                        }


                        sendObj.put("building_number", buliding_number.getText().toString());
                        sendObj.put("street_name", StreetName.getText().toString());
                        sendObj.put("neighborhood_name", neighborhood_name.getText().toString());
                        sendObj.put("building_city_name", city.getText().toString());
                        sendObj.put("postal_code", postalcode.getText().toString());
                        sendObj.put("additional_number", additional_number.getText().toString());
                        sendObj.put("unit_number", unit_number.getText().toString());
                        sendObj.put("solidarity_partner", solidarity_partner_);
                        sendObj.put("solidarity_salary", Solidarity_salary.getText().toString());


                        System.out.println(sendObj.toString());
                        mVolleyService.postDataasync_with_file("finance", WebService.finance, sendObj);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }


            }
        });

    }


    public static type2Fragment newInstance(String text) {

        type2Fragment f = new type2Fragment();
        Bundle b = new Bundle();
        b.putString("Id_eastate", text);

        f.setArguments(b);

        return f;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        System.out.println("fdljkfldkfldkfldkfldfk");


        if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 1217)) {


            ArrayList<Image> images = ImagePicker.getImages(data);
//            Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, images.get(0).getId()+"");


            String filePath = images.get(0).getPath().toString();
            Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);


            File file_image_profile = new File(filePath);

            System.out.println("dkfmd,fm");


            National_address_file = new File(filePath);
            National_address.setBackground(getActivity().getDrawable(R.drawable.edit_text_background_green));


        }
        if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 1213)) {


            ArrayList<Image> images = ImagePicker.getImages(data);
//            Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, images.get(0).getId()+"");


            String filePath = images.get(0).getPath().toString();
            Bitmap selectedImagea = BitmapFactory.decodeFile(filePath);


            File file_image_profile = new File(filePath);

            System.out.println("dkfmd,fm");

            owner_get_id_image_file = new File(filePath);

            owner_get_id_image.setBackground(getActivity().getDrawable(R.drawable.edit_text_background_green));


        }


        super.onActivityResult(requestCode, resultCode, data);

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

                        String message = response.getString("message");

                        WebService.Make_Toast_color(getActivity(), message, "success");
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

}
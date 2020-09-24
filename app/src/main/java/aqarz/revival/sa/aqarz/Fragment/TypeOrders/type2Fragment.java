package aqarz.revival.sa.aqarz.Fragment.TypeOrders;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import aqarz.revival.sa.aqarz.Activity.OprationAqarz.RequestOrderActivity;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_Opration_in_order;
import aqarz.revival.sa.aqarz.Adapter.RecyclerView_All_type_in_fragment;
import aqarz.revival.sa.aqarz.Modules.OprationModules;
import aqarz.revival.sa.aqarz.Modules.TypeModules;
import aqarz.revival.sa.aqarz.R;
import aqarz.revival.sa.aqarz.Settings.Settings;
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;


public class type2Fragment extends Fragment {


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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.requst_order_type_2, container, false);

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

                        Intent intent = new Intent(getContext(), ImageSelectActivity.class);
                        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                        startActivityForResult(intent, 1217);
                    }
                } else {
                    Intent intent = new Intent(getContext(), ImageSelectActivity.class);
                    intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                    startActivityForResult(intent, 1217);

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

                        Intent intent = new Intent(getContext(), ImageSelectActivity.class);
                        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                        startActivityForResult(intent, 1213);
                    }
                } else {
                    Intent intent = new Intent(getContext(), ImageSelectActivity.class);
                    intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                    startActivityForResult(intent, 1213);

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




    }


    public static type2Fragment newInstance(String text) {

        type2Fragment f = new type2Fragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1217 && resultCode == Activity.RESULT_OK) {
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            System.out.println("fdljkfldkfldkfldkfldfk");

            owner_get_id_image_file = new File(filePath);
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

        if (requestCode == 1213 && resultCode == Activity.RESULT_OK) {
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            System.out.println("fdljkfldkfldkfldkfldfk");
            National_address_file = new File(filePath);

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
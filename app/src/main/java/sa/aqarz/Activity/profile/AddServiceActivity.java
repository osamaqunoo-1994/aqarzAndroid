package sa.aqarz.Activity.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import sa.aqarz.R;

public class AddServiceActivity extends AppCompatActivity {
    ImageView back;
    Spinner add_service;
    EditText name;
    EditText mobile_number;
    EditText details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        back = findViewById(R.id.back);
        add_service = findViewById(R.id.add_service);
        name = findViewById(R.id.name);
        mobile_number = findViewById(R.id.mobile_number);
        details = findViewById(R.id.details);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //get the spinner from the xml.

//create a list of items for the spinner.
        try {
            String[] items = new String[]{};
            String[] items_id = new String[]{};

            for (int i = 0; i < OtherProfileActivity.userModules.getService_name().size(); i++) {


                items[i] = OtherProfileActivity.userModules.getService_name().get(i).getName() + "";
                items_id[i] = OtherProfileActivity.userModules.getService_name().get(i).getId() + "";


            }


//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
            add_service.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
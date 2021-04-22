package sa.aqarz.Activity.Employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import sa.aqarz.R;

public class DetailsEmployeeActivity extends AppCompatActivity {
    RecyclerView all_employee;


    ImageView back;
    ImageView search;
    EditText search_edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        all_employee = findViewById(R.id.all_employee);
        back = findViewById(R.id.back);
        search = findViewById(R.id.search);
        search_edt = findViewById(R.id.search_edt);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        all_employee.setLayoutManager(new GridLayoutManager(this, 2));

    }
}
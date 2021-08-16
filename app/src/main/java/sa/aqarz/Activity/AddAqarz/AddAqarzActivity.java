package sa.aqarz.Activity.AddAqarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.OprationAqarz.AddAqarsActivity;
import sa.aqarz.Adapter.RecyclerView_All_opration_bottom_sheet;
import sa.aqarz.Modules.TypeModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;

public class AddAqarzActivity extends AppCompatActivity {
    RecyclerView opration_RecyclerView;

    List<TypeModules> opration_list = new ArrayList<>();


    LinearLayout step_1;
    LinearLayout step_2;
    LinearLayout step_3;
    LinearLayout step_4;

    LinearLayout step_1_lay;
    LinearLayout step_2_lay;
    LinearLayout step_3_lay;
    LinearLayout step_4_lay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aqarz);


        inti();
    }

    public void inti() {
        opration_RecyclerView = findViewById(R.id.opration_RecyclerView);

        step_1 = findViewById(R.id.step_1);
        step_2 = findViewById(R.id.step_2);
        step_3 = findViewById(R.id.step_3);
        step_4 = findViewById(R.id.step_4);
        step_1_lay = findViewById(R.id.step_1_lay);
        step_2_lay = findViewById(R.id.step_2_lay);
        step_3_lay = findViewById(R.id.step_3_lay);

        ///------------------------------------------------------------------------------------------------------
        opration_list = Settings.getSettings().getEstate_types().getOriginal().getData();

        LinearLayoutManager layoutManagerw
                = new LinearLayoutManager(AddAqarzActivity.this, LinearLayoutManager.HORIZONTAL, false);
        opration_RecyclerView.setLayoutManager(layoutManagerw);

        RecyclerView_All_opration recyclerView_all_opration = new RecyclerView_All_opration(AddAqarzActivity.this, opration_list);
        recyclerView_all_opration.addItemClickListener(new RecyclerView_All_opration.ItemClickListener() {
            @Override
            public void onItemClick(int position) {


            }
        });
        opration_RecyclerView.setAdapter(recyclerView_all_opration);

        ///------------------------------------------------------------------------------------------------------


    }
}
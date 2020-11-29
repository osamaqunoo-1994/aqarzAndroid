package sa.aqarz.Activity.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import sa.aqarz.Activity.AqarzProfileActivity;
import sa.aqarz.Adapter.RecyclerView_member_new;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.SettingsModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.Settings.WebService;
import sa.aqarz.api.IResult;

public class MyMemberActivity extends AppCompatActivity {
    RecyclerView mymember;
    IResult mResultCallback;
    List<HomeModules_aqares> homeModules = new ArrayList<>();
    ImageView back;
    LinearLayout nodata_vis;
    List<SettingsModules.service_types> member_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_member);

        mymember = findViewById(R.id.mymember);
        back = findViewById(R.id.back);
        nodata_vis = findViewById(R.id.nodata_vis);


//        WebService.loading(MyMemberActivity.this, true);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(MyMemberActivity.this, LinearLayoutManager.VERTICAL, false);
        mymember.setLayoutManager(layoutManager1);


        try {
//            service_list = Settings.getSettings().getService_types();
            member_list = Settings.GetUser().getMember_name();

        } catch (Exception e) {

        }

        mymember.setAdapter(new RecyclerView_member_new(MyMemberActivity.this, member_list));


        if (member_list.size() == 0) {
            nodata_vis.setVisibility(View.VISIBLE);
        } else {
            nodata_vis.setVisibility(View.GONE);

        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
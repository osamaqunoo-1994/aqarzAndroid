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
import sa.aqarz.Adapter.RecyclerView_service_new;
import sa.aqarz.Modules.HomeModules_aqares;
import sa.aqarz.Modules.SettingsModules;
import sa.aqarz.R;
import sa.aqarz.Settings.Settings;
import sa.aqarz.api.IResult;

public class MyServiceActivity extends AppCompatActivity {
    RecyclerView mymember;
    IResult mResultCallback;
    List<HomeModules_aqares> homeModules = new ArrayList<>();
    ImageView back;
    LinearLayout nodata_vis;
//    List<SettingsModules.service_types> member_list = new ArrayList<>();
    List<SettingsModules.service_types> service_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        mymember = findViewById(R.id.mymember);
        back = findViewById(R.id.back);
        nodata_vis = findViewById(R.id.nodata_vis);


//        WebService.loading(MyMemberActivity.this, true);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(MyServiceActivity.this, LinearLayoutManager.VERTICAL, false);
        mymember.setLayoutManager(layoutManager1);


        try {
            service_list = Settings.GetUser().getService_name();
//            member_list = Settings.getSettings().getMember_types();

        } catch (Exception e) {

        }

//        mymember.setAdapter(new RecyclerView_member_new(MyServiceActivity.this, member_list));

//        mymember.setAdapter(new RecyclerView_member_new(AqarzProfileActivity.this, member_list));
        mymember.setAdapter(new RecyclerView_service_new(MyServiceActivity.this, service_list));

        if (service_list.size() == 0) {
            nodata_vis.setVisibility(View.VISIBLE);
        } else {
            nodata_vis.setVisibility(View.GONE);

        }

    }
}
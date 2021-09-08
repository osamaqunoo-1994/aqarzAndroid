package sa.aqarz.NewAqarz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import sa.aqarz.Activity.FavoriteActivity;
import sa.aqarz.R;

public class IntrestedActivity extends AppCompatActivity {
    CardView add_interst;

    ImageView back;

    RecyclerView all_arya_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intrested);
        back = findViewById(R.id.back);
        add_interst = findViewById(R.id.add_interst);
        all_arya_list = findViewById(R.id.all_arya_list);


        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(IntrestedActivity.this, LinearLayoutManager.VERTICAL, false);
        all_arya_list.setLayoutManager(layoutManager1);


        add_interst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntrestedActivity.this, SelectFavCityActivity.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });








    }
}
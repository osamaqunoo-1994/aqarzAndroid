package sa.aqarz.Activity.OprationNew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import sa.aqarz.R;

public class RentShowActivity extends AppCompatActivity {
    LinearLayout next;
    ImageView image;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_show);
        next = findViewById(R.id.next);
        image = findViewById(R.id.image);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id == 1) {

                    Intent intent = new Intent(RentShowActivity.this, RentActivity.class);
                    intent.putExtra("id", "");
                    startActivity(intent);

                } else {
                    id = 1;
                    image.setImageDrawable(getResources().getDrawable(R.drawable.rent_show_2));
                }
            }
        });
    }
}
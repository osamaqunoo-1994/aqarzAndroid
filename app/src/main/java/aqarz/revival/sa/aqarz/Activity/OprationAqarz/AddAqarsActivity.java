package aqarz.revival.sa.aqarz.Activity.OprationAqarz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import aqarz.revival.sa.aqarz.R;

public class AddAqarsActivity extends AppCompatActivity {


    ImageView Lounges_plus, Lounges_minus;
    TextView Lounges_number;

    ImageView room_plus, room_minus;
    TextView room_text;


    ImageView Bathrooms_plus, Bathrooms_minus;
    TextView Bathrooms_text;


    ImageView Boards_plus, Boards_minus;
    TextView Boards_text;


    ImageView Kitchens_plus, Kitchens_minus;
    TextView Kitchens_text;

    ImageView Dining_rooms_plus, Dining_rooms_minus;
    TextView Dining_text;


    TextView deluxe, average, normal;
    TextView north, south, east, west;
    TextView unmarried, married;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aqars);


    }


    public void init(){

        Lounges_plus=findViewById(R.id.Lounges_plus);
        Lounges_minus=findViewById(R.id.Lounges_minus);
        Lounges_number=findViewById(R.id.Lounges_number);


        room_plus=findViewById(R.id.room_plus);
        room_minus=findViewById(R.id.room_minus);
        room_text=findViewById(R.id.room_text);


        Bathrooms_plus=findViewById(R.id.Bathrooms_plus);
        Bathrooms_minus=findViewById(R.id.Bathrooms_minus);
        Bathrooms_text=findViewById(R.id.Bathrooms_text);



        Boards_plus=findViewById(R.id.Boards_plus);
        Boards_minus=findViewById(R.id.Boards_minus);
        Boards_text=findViewById(R.id.Boards_text);



        Kitchens_plus=findViewById(R.id.Kitchens_plus);
        Kitchens_minus=findViewById(R.id.Kitchens_minus);
        Kitchens_text=findViewById(R.id.Kitchens_text);


        Dining_rooms_plus=findViewById(R.id.Dining_rooms_plus);
        Dining_rooms_minus=findViewById(R.id.Dining_rooms_minus);
        Dining_text=findViewById(R.id.Dining_text);

        deluxe=findViewById(R.id.deluxe);
        average=findViewById(R.id.average);
        normal=findViewById(R.id.normal);

        north=findViewById(R.id.north);
        south=findViewById(R.id.south);
        east=findViewById(R.id.east);
        west=findViewById(R.id.west);

        unmarried=findViewById(R.id.unmarried);
        married=findViewById(R.id.married);








    }



}
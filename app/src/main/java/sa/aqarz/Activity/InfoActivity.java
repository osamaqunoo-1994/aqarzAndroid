package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import sa.aqarz.R;
import sa.aqarz.Settings.Settings;

public class InfoActivity extends AppCompatActivity {
    Spinner speener;

    ImageView play_btn;


    String text_url = "";
    VideoView videoView;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        speener = findViewById(R.id.speener);
        play_btn = findViewById(R.id.play_btn);
        videoView = findViewById(R.id.videoView);
        back = findViewById(R.id.back);


        String[] Type = new String[Settings.getSettings().getVideos().size() + 1];

        Type[0] = getResources().getString(R.string.click_here);

        for (int i = 0; i < Settings.getSettings().getVideos().size(); i++) {
            Type[i + 1] = Settings.getSettings().getVideos().get(i).getTitle() + "";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Type);//setting the country_array to spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speener.setAdapter(adapter);
//if you want to set any action you can do in this listener
        speener.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {


                if (position == 0) {
                    text_url = "";
                } else {
                    text_url = Settings.getSettings().getVideos().get(position - 1).getVideo() + "";

                    System.out.println("text_url"+text_url);

//                    text_url = "http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4";

                    videoView.setVideoPath(text_url);

                    videoView.start();
//                    Toast.makeText(InfoActivity.this, Settings.getSettings().getVideos().get(position - 1).getVideo() + "", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);


        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text_url.equals("")) {
                    Toast.makeText(InfoActivity.this, getResources().getString(R.string.novideoselected), Toast.LENGTH_LONG).show();

                } else {
                    play_btn.setVisibility(View.GONE);
                    videoView.setVideoPath(text_url);
                    videoView.start();

                }
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
package sa.aqarz.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        try {


            String[] Type = new String[Settings.getSettings().getVideos().size()];

//        Type[0] = getResources().getString(R.string.click_here);

            for (int i = 0; i < Settings.getSettings().getVideos().size(); i++) {
                Type[i] = Settings.getSettings().getVideos().get(i).getTitle() + "";
            }

            YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, Type);//setting the country_array to spinner
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            speener.setAdapter(adapter);
//if you want to set any action you can do in this listener
            speener.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int position, long id) {

                    try {
                        text_url = Settings.getSettings().getVideos().get(position).getVideo() + "";

                        Pattern compiledPattern = Pattern.compile("(?<=v=).*?(?=&|$)", Pattern.CASE_INSENSITIVE);
                        Matcher matcher = compiledPattern.matcher(text_url);
                        if (matcher.find()) {
//                        setVideoId(matcher.group());
                            System.out.println("text_url" + text_url);
//                        String[] separated = text_url.toString().split("/");
                            text_url = "https://www.youtube.com/watch?v=TQd3vpZHQw4";
//                        String video_id = separated[separated.length - 1];
                            String video_id = matcher.group() + "";

                            System.out.println("video_id" + video_id);
                            youTubePlayerView.initialize(new YouTubePlayerInitListener() {
                                @Override
                                public void onInitSuccess(final YouTubePlayer initializedYouTubePlayer) {
                                    initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                                        @Override
                                        public void onReady() {
                                            String videoId = "" + video_id;
                                            initializedYouTubePlayer.loadVideo(videoId, 0);
                                        }
                                    });
                                }
                            }, true);

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    System.out.println("text_url"+text_url);


//
////                    text_url = "http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4";
//
//                    videoView.setVideoPath(text_url);
//
//                    videoView.start();


//                    Toast.makeText(InfoActivity.this, Settings.getSettings().getVideos().get(position - 1).getVideo() + "", Toast.LENGTH_LONG).show();
                }


                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });


//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);


            play_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (text_url.equals("")) {
                        Toast.makeText(InfoActivity.this, getResources().getString(R.string.novideoselected), Toast.LENGTH_LONG).show();

                    } else {
//                    play_btn.setVisibility(View.GONE);
//                    videoView.setVideoPath(text_url);
//                    videoView.start();

                    }
                }
            });
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {
        }
    }


}
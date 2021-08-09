package sa.aqarz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.orhanobut.hawk.Hawk;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sa.aqarz.R;
import sa.aqarz.Settings.Settings;

public class Info_2_Activity extends AppCompatActivity {
    ImageView back;
    Spinner speener;
    YouTubePlayerView youTubePlayerView;

    String text_url = "";

    @Override
    protected void onStart() {

        if (Hawk.contains("lang")) {



            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } else {

            Hawk.put("lang", "ar");

            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());

        }

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_2_);
        back = findViewById(R.id.back);
        speener = findViewById(R.id.speener);
        youTubePlayerView = findViewById(R.id.youtube_player_view);

        getLifecycle().addObserver(youTubePlayerView);

        if (Hawk.contains("lang")) {



            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } else {

            Hawk.put("lang", "ar");

            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());

        }

        try {
            String[] Type = new String[Settings.getSettings().getVideos().size()];
            for (int i = 0; i < Settings.getSettings().getVideos().size(); i++) {
                Type[i] = Settings.getSettings().getVideos().get(i).getTitle() + "";
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, Type);//setting the country_array to spinner
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            speener.setAdapter(adapter);

            speener.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        text_url = Settings.getSettings().getVideos().get(position).getVideo() + "";
                        text_url = "https://www.youtube.com/watch?v=TQd3vpZHQw4";


                        if (Hawk.contains("lang")) {



                            Locale locale = new Locale(Hawk.get("lang").toString());
                            Locale.setDefault(locale);
                            Configuration config = new Configuration();
                            config.locale = locale;
                            getBaseContext().getResources().updateConfiguration(config,
                                    getBaseContext().getResources().getDisplayMetrics());
                        } else {

                            Hawk.put("lang", "ar");

                            Locale locale = new Locale(Hawk.get("lang").toString());
                            Locale.setDefault(locale);
                            Configuration config = new Configuration();
                            config.locale = locale;
                            getBaseContext().getResources().updateConfiguration(config,
                                    getBaseContext().getResources().getDisplayMetrics());

                        }

                        Pattern compiledPattern = Pattern.compile("(?<=v=).*?(?=&|$)", Pattern.CASE_INSENSITIVE);
                        Matcher matcher = compiledPattern.matcher(text_url);
                        if (matcher.find()) {


                            String video_id = matcher.group() + "";
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


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Hawk.contains("lang")) {


                    System.out.println("dfldkfdlfkldkfd");

                    Locale locale = new Locale(Hawk.get("lang").toString());
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                } else {
                    System.out.println("dfldkfdlfkldkfd");

                    Hawk.put("lang", "ar");

                    Locale locale = new Locale(Hawk.get("lang").toString());
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());

                }

                finish();
            }
        });

    }

    @Override
    protected void onResume() {

        if (Hawk.contains("lang")) {



            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } else {

            Hawk.put("lang", "ar");

            Locale locale = new Locale(Hawk.get("lang").toString());
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());

        }

        super.onResume();
    }
}
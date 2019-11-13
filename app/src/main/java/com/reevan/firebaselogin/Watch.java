package com.reevan.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

public class Watch extends AppCompatActivity {

    VideoView videoview;
    ImageButton control;
    ProgressBar progress,loading;
    TextView currentpos,duration;
    Uri videoUri;

    int current = 0,total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_watch);

        videoview = findViewById(R.id.videoView);
        control = findViewById(R.id.control);
        progress = findViewById(R.id.progressBar);
        currentpos = findViewById(R.id.currentpos);
        duration = findViewById(R.id.duration);
        loading = findViewById(R.id.loading);

        videoUri = Uri.parse(getIntent().getStringExtra("address"));

        videoview.setVideoURI(videoUri);
        videoview.requestFocus();
        videoview.start();


        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(videoview.isPlaying())
                {
                    videoview.pause();
                    control.setImageResource(android.R.drawable.ic_media_play);
                }else{
                    videoview.start();
                    control.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        });

        videoview.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
               if(i == MediaPlayer.MEDIA_INFO_BUFFERING_START)
                   loading.setVisibility(View.VISIBLE);
               else if(i == MediaPlayer.MEDIA_INFO_BUFFERING_END)
                   loading.setVisibility(View.INVISIBLE);
               else if(i == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                   loading.setVisibility(View.INVISIBLE);

                return false;
            }
        });

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                total = mediaPlayer.getDuration()/1000;
                String t = total/60 + ":" + total%60;
                duration.setText(t);
            }
        });

        progress.setMax(100);
        new VideoProgress().execute();
    }

    public class VideoProgress extends AsyncTask<Void,Integer,Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            do{
                try{
                    Thread.sleep(900);
                }catch(Exception e){

                }
                current = videoview.getCurrentPosition()/1000;
                publishProgress(current);
            }while(progress.getProgress()<=100);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(total!=0){
                currentpos.setText(values[0]/60+":"+values[0]%60);
                progress.setProgress(values[0]*100/total);
            }
        }
    }

}

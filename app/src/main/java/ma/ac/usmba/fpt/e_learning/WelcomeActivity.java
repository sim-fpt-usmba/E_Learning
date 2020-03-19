package ma.ac.usmba.fpt.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.VideoView;

public class WelcomeActivity extends AppCompatActivity {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        playVideo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playVideo();
    }

    public void playVideo() {
        videoView = (VideoView) findViewById(R.id.welcome_video);
        String videopath = "android.resource://" + getPackageName() + "/" + R.raw.welcome_video;
        videoView.setVideoPath(videopath);
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });
    }
}

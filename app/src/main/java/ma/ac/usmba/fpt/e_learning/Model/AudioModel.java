package ma.ac.usmba.fpt.e_learning.Model;

import android.app.Activity;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.widget.SeekBar;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class AudioModel {
    private String path;
    private MediaPlayer mediaPlayer;
    String audio_duration;
    private boolean started;
    private boolean paused;
    private boolean finished;


    public AudioModel(String path){
        this.path = path;
        finished = false;
        started = false;
        mediaPlayer = new MediaPlayer();
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void loadOnMemory() throws IOException {
        mediaPlayer.setDataSource(this.path);
        mediaPlayer.prepare();
    }

    public void start() {
        started = true;
        paused = false;
        finished = false;
        mediaPlayer.start();
    }

    public void resume() {
        mediaPlayer.start();
        paused = false;
    }

    public void pause() {
        paused = true;
        mediaPlayer.pause();
    }

    public void onFinish() {
        finished = true;
        paused = false;
        started = false;
    }

    public static void seeakBarController(Context context, final SeekBar seekBar, final Handler handler, final AudioModel audioModel) {
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int currentPos = audioModel.getMediaPlayer().getCurrentPosition() / 1000;
                seekBar.setProgress(currentPos);
                handler.postDelayed(this, 1000);
            }
        });
    }

    public String getAudio_duration(String audio_path) {
        Uri path = Uri.parse(audio_path);
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(path.getPath());
        String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        long dur = Long.parseLong(duration);
        int seconds = (int)((dur / 1000)%60);
        int minutes =( int )((dur / 1000)/60);
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mmr.release();

        return timeLeftFormatted;
    }
}



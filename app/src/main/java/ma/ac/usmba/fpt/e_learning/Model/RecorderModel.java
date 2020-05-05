package ma.ac.usmba.fpt.e_learning.Model;

import android.media.MediaRecorder;
import android.os.CountDownTimer;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class RecorderModel {
    private boolean started;
    private boolean stopped;
    private MediaRecorder myAudioRecorder;
    private String outputFile;
    private ArrayList<AudioModel> audios;
    long count = 0;
    private static final long START_TIME_IN_MILLIS = 600000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private String audio_duration;

    public RecorderModel(String outputFile){
        this.outputFile = outputFile;
        started = false;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public MediaRecorder getMyAudioRecorder() {
        return myAudioRecorder;
    }

    public void setMyAudioRecorder(MediaRecorder myAudioRecorder) {
        this.myAudioRecorder = myAudioRecorder;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public  ArrayList<AudioModel> getAudios() {
        return audios;
    }

    public void setAudios(ArrayList<AudioModel> audios) {
        this.audios = audios;
    }

    public void start(){
        started = true;
        stopped = false;
        try {
            myAudioRecorder = new MediaRecorder();
            myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
            myAudioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
            myAudioRecorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath()+"/test.mp3");
            myAudioRecorder.prepare();
            myAudioRecorder.start();
        }catch (Exception e){
            //Make Something
        }
    }

    public void stop() throws IllegalStateException{
        started = false;
        stopped = true;
        myAudioRecorder.stop();
        myAudioRecorder.release();
        myAudioRecorder = null;
    }

    public void loadAudios(ArrayList<AudioModel> audios){
        this.audios = audios;
        audios.add(new AudioModel(outputFile));
    }

    public void deleteAudios(String outputFile){
        new File(outputFile).delete();
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                count += 1000;
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                startTimer();
            }
        }.start();
        mTimerRunning = true;
    }


            private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        count = 0;
    }

    private String updateCountDownText() {
        int minutes = (int) (count / 1000) / 60;
        int seconds = (int) (count / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        audio_duration = timeLeftFormatted;
        return audio_duration;
    }
}



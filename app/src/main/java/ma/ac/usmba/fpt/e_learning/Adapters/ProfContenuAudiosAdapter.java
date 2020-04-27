package ma.ac.usmba.fpt.e_learning.Adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



import ma.ac.usmba.fpt.e_learning.Model.AudioModel;
import ma.ac.usmba.fpt.e_learning.R;


public class ProfContenuAudiosAdapter extends RecyclerView.Adapter<ProfContenuAudiosAdapter.ViewHolder>  {
    Context context;
    ArrayList<AudioModel> audios;

    public ProfContenuAudiosAdapter(Context context, ArrayList<AudioModel> audios) {
        this.context = context;
        this.audios = audios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_audios,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        AudioModel audioModel = audios.get(position);
        if(new File(audioModel.getPath()).exists())
            holder.audio_duration.setText(audioModel.getAudio_duration(audioModel.getPath()));
        holder.play_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    final AudioModel currentAudio = audios.get(position);
                    currentAudio.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            currentAudio.onFinish();
                            holder.play_audio.setBackground(context.getResources().getDrawable(R.drawable.ic_play));
                        }
                    });

                    if(!currentAudio.isFinished()){
                        if(!currentAudio.isStarted()){
                            holder.play_audio.setBackground(context.getResources().getDrawable(R.drawable.ic_pause));
                            holder.play_audio.setWidth(10);
                            holder.play_audio.setHeight(10);
                            try{
                                currentAudio.loadOnMemory();
                                holder.seekBar.setMax(currentAudio.getMediaPlayer().getDuration()/1000);
                                AudioModel.seeakBarController(context,holder.seekBar,holder.handler,currentAudio);
                                holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                        if(fromUser)
                                            currentAudio.getMediaPlayer().seekTo(1000);
                                    }

                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {

                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {

                                    }
                                });
                                currentAudio.start();
                                Toast.makeText(context, "Playing Audio" + currentAudio.isPaused(), Toast.LENGTH_LONG).show();
                            }catch (IOException e){
                                e.printStackTrace();
                                Log.d("onClick: ", e.toString());
                            }
                        }else{

                            if(!currentAudio.isPaused()){
                                holder.play_audio.setBackground(context.getResources().getDrawable(R.drawable.ic_play));
                                currentAudio.pause();
                                Toast.makeText(context, "Audio Paused" , Toast.LENGTH_LONG).show();
                            }else{
                                holder.play_audio.setBackground(context.getResources().getDrawable(R.drawable.ic_pause));
                                holder.play_audio.setWidth(10);
                                holder.play_audio.setHeight(10);
                                currentAudio.resume();
                                Toast.makeText(context, "Audio Resumed", Toast.LENGTH_LONG).show();
                            }

                        }

                    }else{
                        holder.play_audio.setBackground(context.getResources().getDrawable(R.drawable.ic_pause));
                        currentAudio.start();
                    }
              /*      holder.outputFile= Environment.getExternalStorageDirectory().getAbsolutePath() +
                            "/recording"+
                            position+
                            ".mp3";
                    holder.i += 1;
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(holder.outputFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
*/
                } catch (Exception e) {
                    // make something
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return audios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView audio_duration;
        Button play_audio;
        SeekBar seekBar;
        Handler handler;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            audio_duration = itemView.findViewById(R.id.audio_duration);
            play_audio = itemView.findViewById(R.id.play_button);
            seekBar = itemView.findViewById(R.id.seekBar);
            handler = new Handler();
        }
    }
}

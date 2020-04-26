package ma.ac.usmba.fpt.e_learning.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        AudioModel audioModel = audios.get(position);
        holder.audio_duration.setText(audioModel.getAudio_duration(audioModel.getPath()));
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do Something
                //TODO: La partie de backend / Telechargement des fichiers
            }
        });
    }

    @Override
    public int getItemCount() {
        return audios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView audio_duration;
        Button play;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            audio_duration = itemView.findViewById(R.id.audio_duration);
            play = itemView.findViewById(R.id.play_button);
        }
    }
}

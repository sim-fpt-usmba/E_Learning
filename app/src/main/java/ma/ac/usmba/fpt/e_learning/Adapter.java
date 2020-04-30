package ma.ac.usmba.fpt.e_learning;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ma.ac.usmba.fpt.e_learning.Model.Filiere;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context mcontext;
    LayoutInflater layoutInflater;
    List<Filiere> mlinst;


    public Adapter(Context mcontext, ArrayList<Filiere> data){
        this.mlinst = mlinst;
        this.mcontext=mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        layoutInflater= LayoutInflater.from(mcontext);
        View view = layoutInflater.inflate(R.layout.custom_view,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        ViewHolder.image.setImageResource(mlinst.get(i).getBack());
        ViewHolder.name.setText(mlinst.get(i).getNom_filere());
    }

    @Override
    public int getItemCount() {

        return mlinst.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        static ImageView image;
        static TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            image=(ImageView)itemView.findViewById(R.id.img);






        }
    }

}
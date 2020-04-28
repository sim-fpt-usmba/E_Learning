package ma.ac.usmba.fpt.e_learning.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.EtudiantModuleActivity;
import ma.ac.usmba.fpt.e_learning.Model.Module;
import ma.ac.usmba.fpt.e_learning.R;

public class EtudiantModuleAdapter extends RecyclerView.Adapter<EtudiantModuleAdapter.ModuleHolder> {

    Context context;
    ArrayList<Module> modules;
    LayoutInflater inflater;

    public EtudiantModuleAdapter(Context context, ArrayList<Module> modules) {
        this.context = context;
        this.modules = modules;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.etudiant_module_item, parent, false);
        ModuleHolder holder = new ModuleHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleHolder holder, int position) {
        Module currentModule = modules.get(position);
        holder.setData(currentModule, position);
    }

    class ModuleHolder extends RecyclerView.ViewHolder {
        TextView moduleTxt;
        public ModuleHolder(@NonNull View itemView) {
            super(itemView);
            moduleTxt = itemView.findViewById(R.id.module_txt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EtudiantModuleActivity.class);
                    context.startActivity(intent);
                }
            });
        }

        public void setData(Module currentModule, int position) {
            moduleTxt.setText(currentModule.getName());
        }
    }
}


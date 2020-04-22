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

import ma.ac.usmba.fpt.e_learning.R;
import ma.ac.usmba.fpt.e_learning.Utils.FileUtils;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.ViewHolder> {
    Context context;
    ArrayList<String> filenames;
    public FilesAdapter(Context context,ArrayList<String> filenames) {
        this.context = context;
        this.filenames = filenames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String filename = filenames.get(position);
        holder.filename.setText(FileUtils.getFileName(filename));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filenames.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filenames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView filename;
        Button delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            filename = itemView.findViewById(R.id.txt_view_question);
            delete   = itemView.findViewById(R.id.delete_button);
        }
    }
}

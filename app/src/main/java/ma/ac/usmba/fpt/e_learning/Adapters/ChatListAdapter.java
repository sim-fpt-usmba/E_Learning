package ma.ac.usmba.fpt.e_learning.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Model.Message;
import ma.ac.usmba.fpt.e_learning.R;

public class ChatListAdapter extends BaseAdapter {
    private Activity activity;
    private String nomAuteur;
    private ArrayList<Message> messages;

    public ChatListAdapter(Activity activity, String nomAuteur, ArrayList<Message> messages) {
        this.activity = activity;
        this.nomAuteur = nomAuteur;
        this.messages = messages;
    }

    public ChatListAdapter(Activity activity, String nomAuteur) {
        this.activity = activity;
        this.nomAuteur = nomAuteur;
        messages = new ArrayList<>();

    }

    static class ViewHolder {
        LinearLayout container;
        TextView nomAuteurTxt;
        TextView messageTxt;
        TextView dateTxt;
        LinearLayout.LayoutParams params;
    }

    public void addItem(Message message) {
        this.messages.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Message getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            convertView = inflater.inflate(R.layout.chat_msg_row, parent, false);
            final ViewHolder holder = new ViewHolder();
            holder.container = (LinearLayout) convertView.findViewById(R.id.container);
            holder.nomAuteurTxt = (TextView) convertView.findViewById(R.id.author);
            holder.messageTxt = (TextView) convertView.findViewById(R.id.message);
            holder.dateTxt = (TextView) convertView.findViewById(R.id.date);
            holder.params = (LinearLayout.LayoutParams) holder.nomAuteurTxt.getLayoutParams();
            convertView.setTag(holder);
        }

        final Message message = getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();

        boolean isCurrentUser = message.getAuteur().equals(nomAuteur);
        setAppearance(isCurrentUser,holder);

        if (isCurrentUser) {
            holder.nomAuteurTxt.setText(activity.getResources().getText(R.string.moi));
        } else {
            holder.nomAuteurTxt.setText(message.getAuteur());
        }
        holder.messageTxt.setText(message.getMessage());
        holder.dateTxt.setText(message.formatTime());

        return convertView;
    }

    public void setAppearance(boolean isCurrentUser, ViewHolder holder) {
        int margH = dpToPx(15, activity);
        int margT = dpToPx(7, activity);
        int padH = dpToPx(10, activity);

        if (isCurrentUser) {
            holder.params.gravity = Gravity.END;
            holder.container.setBackgroundColor(Color.YELLOW);
            holder.container.setBackgroundResource(R.drawable.msg_2);
            holder.params.setMargins(6*margH,0,margH,margT);
        } else {
            holder.params.gravity = Gravity.START;
            holder.container.setBackgroundResource(R.drawable.msg_1);
            holder.params.setMargins(margH,0,6*margH,margT);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                holder.params.width,
                holder.params.height
        );
        params.gravity = Gravity.START;
        holder.nomAuteurTxt.setLayoutParams(params);
        holder.container.setLayoutParams(holder.params);

    }

    private int dpToPx(int dp,Context context) {
        return (int)(dp * context.getResources().getDisplayMetrics().density);
    }
}

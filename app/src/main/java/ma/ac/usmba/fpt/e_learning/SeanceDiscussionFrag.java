package ma.ac.usmba.fpt.e_learning;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Adapters.ChatListAdapter;
import ma.ac.usmba.fpt.e_learning.Controller.MessageController;
import ma.ac.usmba.fpt.e_learning.Model.Message;

public class SeanceDiscussionFrag extends Fragment {

    private String nomAuteur;
    private ListView chatListView;
    private EditText inputText;
    private ImageButton envoyerBtn;
    private ChatListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seance_discussion, container, false);

        setNomAuteur();

        chatListView = (ListView) view.findViewById(R.id.chat_list_view);
        inputText = (EditText) view.findViewById(R.id.messageInput);
        envoyerBtn = (ImageButton) view.findViewById(R.id.sendButton);

        envoyerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envoyerMessage();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ArrayList<Message> messages = MessageController.getMessages();
        adapter = new ChatListAdapter(this.getActivity(), nomAuteur, messages);
        chatListView.setAdapter(adapter);
    }

    public void setNomAuteur() {
        //TODO: retrive the user name frome the sharedPreferences
        nomAuteur = "Elaissaoui Ali";
    }

    public void envoyerMessage() {
        Message message = new Message(inputText.getText().toString(),nomAuteur);
        if (!message.getMessage().isEmpty()) {
            //TODO: send message to the database

            inputText.setText("");
            adapter.addItem(message);
            Log.d("message", "envoyerMessage: " + adapter.getCount());
        }

    }
}

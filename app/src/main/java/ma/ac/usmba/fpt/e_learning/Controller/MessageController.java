package ma.ac.usmba.fpt.e_learning.Controller;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Model.Message;

public class MessageController {

    public static ArrayList<Message> getMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            messages.add(new Message( "voila le message " + i,"Auteur "+ i));
        }

        return messages;
    }
}

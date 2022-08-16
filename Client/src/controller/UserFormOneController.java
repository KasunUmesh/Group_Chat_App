package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UserFormOneController {

    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    public TextArea txtUserOneChatView;
    public TextField txtUserOneText;

    Socket socket = null;

    String messageIn = "";

    public void initialize(){
        new Thread(() -> {
            try {
                socket = new Socket("localhost",5000);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!messageIn.equals("end")){
                    messageIn = dataInputStream.readUTF();
                    txtUserOneChatView.appendText("\nServer : "+messageIn.trim()+"\n");
                }



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }



    public void btnUserOneSend(ActionEvent actionEvent) throws IOException {
        String reply = "";
        reply = txtUserOneText.getText();
        txtUserOneChatView.appendText("\t\t\t\t\t\t\t\tClientOne : " + reply.trim());
        dataOutputStream.writeUTF(reply);
        txtUserOneText.setText("");
    }
}

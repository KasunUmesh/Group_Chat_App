package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    static ServerSocket serverSocket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    static Socket socket;
    public TextField txtServerChatSend;
    public TextArea txtServerChatView;

    String messageIn = "";

    public void initialize(){
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(5000);
                System.out.println("Server Started Waiting for client! .....");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }).start();
    }




    public void btnServerTxtSend(ActionEvent actionEvent) {
    }
}

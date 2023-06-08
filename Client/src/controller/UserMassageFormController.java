package controller;

import javafx.event.ActionEvent;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.Socket;

public class UserMassageFormController extends Thread{

    public TextArea txtUserOneChatView;
    public TextField txtUserOneText;
    public Label txtUserName;

    public BufferedReader reader;
    public PrintWriter writer;
    public Socket socket;

    public void initialize(){
        txtUserOneChatView.setEditable(false);
        System.out.println("Initialized method" + LoginFormController.userName);
        txtUserName.setText(LoginFormController.userName);

        try {
            socket = new Socket("localhost", 4000);
            System.out.println("Socket is Connecting with server");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public void btnUserOneSend(ActionEvent actionEvent) {
        String msg = txtUserOneText.getText().trim();
        writer.println(LoginFormController.userName + ": "+ msg);
        txtUserOneChatView.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txtUserOneText.setText("");
        if (msg.equalsIgnoreCase("Bye") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }

    }
    @Override
    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                String[] tokens = msg.split("");
                String cmd = tokens[0];
                StringBuilder fullMessage = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMessage.append(tokens[i]);
                }

                System.out.println(fullMessage);

                if (cmd.equalsIgnoreCase(LoginFormController.userName + ": ")) {
                    continue;
                } else if (fullMessage.toString().equalsIgnoreCase("bye")) {
                    break;
                }

                txtUserOneChatView.appendText(msg + "\n\n");

            }

            reader.close();
            writer.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void imagesentOnMouseClick(MouseEvent mouseEvent) {
    }

    public void imojisentOnMouseClick(MouseEvent mouseEvent) {
    }
}

package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class UserMassageFormController extends Thread{

    public TextField txtUserOneText;
    public Label txtUserName;

    public BufferedReader reader;
    public PrintWriter writer;
    public Socket socket;
    public VBox vboxUserOneChatView;

    private FileChooser fileChooser;
    private File filePath;

    public void initialize(){
//        txtUserOneChatView.setEditable(false);
        String username = LoginFormController.userName;
        System.out.println("Initialized method" + username);
        txtUserName.setText(String.valueOf(username));

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

    @Override
    public void run() {
        try {
            while (true) {

                String msg = reader.readLine();
                String[] tokens = msg.split("");
                String cmd = tokens[0];


                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]+" ");
                }


                String[] msgToAr = msg.split(" ");
                String st = "";
                for (int i = 0; i < msgToAr.length - 1; i++) {
                    st += msgToAr[i + 1] + " ";
                }

                Text text = new Text(st);
                String firstChars = "";
                if (st.length() > 3) {
                    firstChars = st.substring(0, 3);

                }


                if (firstChars.equalsIgnoreCase("img")) {
                    //for the Images

                    st = st.substring(3, st.length() - 1);



                    File file = new File(st);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(150);
                    imageView.setFitWidth(200);


                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.TOP_LEFT);


                    if (!cmd.equalsIgnoreCase(txtUserName.getText())) {

                        vboxUserOneChatView.setAlignment(Pos.TOP_LEFT);


                        Text text1=new Text("  "+cmd+" :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);

                    } else {
                        hBox.setAlignment(Pos.TOP_LEFT);
                        hBox.getChildren().add(imageView);
                        Text text1=new Text("Me :");
                        hBox.getChildren().add(text1);

                    }

                    Platform.runLater(() -> vboxUserOneChatView.getChildren().addAll(hBox));


                } else {

                    TextFlow tempFlow = new TextFlow();

                    if (!cmd.equalsIgnoreCase(txtUserName.getText() + ":")) {
                        Text txtName = new Text(cmd + " ");
                        txtName.getStyleClass().add("txtName");
                        tempFlow.getChildren().add(txtName);
                    }

                    tempFlow.getChildren().add(text);
                    tempFlow.setMaxWidth(200); //200

                    TextFlow flow = new TextFlow(tempFlow);

                    HBox hBox = new HBox(12); //12

                    //=================================================


                    if (!cmd.equalsIgnoreCase(txtUserName.getText() + ":")) {

                        vboxUserOneChatView.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.TOP_LEFT);
                        hBox.getChildren().add(flow);

                    } else {

                        Text text2=new Text(txtUserName.getText()+": "+fullMsg);
                        TextFlow flow2 = new TextFlow(text2);
                        hBox.setAlignment(Pos.TOP_LEFT);
                        hBox.getChildren().add(flow2);
                    }

                    Platform.runLater(() -> vboxUserOneChatView.getChildren().addAll(hBox));
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnUserOneSend(ActionEvent actionEvent) {
        String msg = txtUserOneText.getText();
        writer.println(txtUserName.getText() + ": "+ msg);

        System.out.println(txtUserName.getText());

        txtUserOneText.clear();
        if (msg.equalsIgnoreCase("Bye") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }

    }

    public void imagesentOnMouseClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        writer.println(txtUserName.getText() + " " + "img" + filePath.getPath());
    }

    public void imojisentOnMouseClick(MouseEvent mouseEvent) {
    }
}

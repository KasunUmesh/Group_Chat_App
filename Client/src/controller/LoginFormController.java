package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class LoginFormController {
    public TextField txtloginUserName;
    public AnchorPane mainroot;

    public static String userName;

    public static ArrayList<String> users = new ArrayList<>();

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        userName = txtloginUserName.getText().trim();
        boolean flag = false;
        if (users.isEmpty()) {
            users.add(userName);
            flag = true;
        }

        for (String s : users) {
            if (!s.equalsIgnoreCase(userName)) {
               flag = true;
                System.out.println(userName);
                break;
            }
        }

        if (flag) {
            this.mainroot.getChildren().clear();
            this.mainroot.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/UserFormOne.fxml")));
        }

    }
}

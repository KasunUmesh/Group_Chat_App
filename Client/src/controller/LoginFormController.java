package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/UserFormOne.fxml"));
            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) this.mainroot.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
        }

    }
}

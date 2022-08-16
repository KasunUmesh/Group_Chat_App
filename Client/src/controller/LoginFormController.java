package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginFormController {

    public TextField txtUserName;
    public PasswordField txtPassword;
    public Label txtUserNameError;
    public Label txtPasswordError;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("user1") && txtPassword.getText().equals("1234")) {
            txtUserNameError.setVisible(false);
            txtPasswordError.setVisible(false);
            txtUserName.clear();
            txtPassword.clear();
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/UserFormOne.fxml"))));
            stage.setTitle("Group Chat App - User One");
            stage.show();

        } else {
            txtUserNameError.setVisible(true);
            txtPasswordError.setVisible(true);
            txtUserName.clear();
            txtPassword.clear();
        }

    }
}

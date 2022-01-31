package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import common.NotifyController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private JFXButton loginActionBtn;

    @FXML
    private JFXTextField userNameInput;

    @FXML
    private JFXPasswordField passwordInput;


    public void handleLoginAction(javafx.event.ActionEvent actionEvent) {
        String userName = userNameInput.getText();
        String password = passwordInput.getText();
        if (isValidLogin(userName, password)) {
            loadApplication();
        }
    }

    private boolean isValidLogin(String userName, String password) {
        if (userName != null && userName.trim().equals("Admin")) {
            if (password != null && password.trim().equals("1234")) {
                return true;
            } else {
                NotifyController.displayNotification("Error !", "Invalid Password, try again.");
            }
        } else {
            NotifyController.displayNotification("Error !", "Invalid Username, try again.");
        }
        return false;
    }

    private void loadApplication() {
        try {
            URL resource = this.getClass().getClassLoader().getResource("views/main_panel.fxml");
            if (resource != null) {
                AnchorPane mainPanel = FXMLLoader.load(resource);
                Stage stage = (Stage) this.loginActionBtn.getScene().getWindow();
                stage.setScene(new Scene(mainPanel));
                stage.centerOnScreen();
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

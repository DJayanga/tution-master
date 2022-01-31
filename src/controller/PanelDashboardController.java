package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PanelDashboardController  implements Initializable {

    @FXML
    void loadManageClass(ActionEvent event) throws IOException {
        loadTab("/views/panel_manage_classes.fxml");
    }

    @FXML
    void loadRegisterStudent(ActionEvent event) throws IOException {
        loadTab("/views/panel_register_student.fxml");
    }

    @FXML
    void loadStudentViewALl(ActionEvent event) throws IOException {
        loadTab("/views/panel_search_student.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void loadTab(String path) throws IOException {
        AnchorPane panel = FXMLLoader.load(this.getClass().getResource(path));
        Stage stage = new Stage();
        stage.setScene(new Scene(panel));
        stage.alwaysOnTopProperty();
        stage.centerOnScreen();
        stage.show();
    }
}

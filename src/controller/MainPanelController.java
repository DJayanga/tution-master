package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPanelController implements Initializable {

    @FXML
    private AnchorPane panelLoader;

//    @FXML
//    void loadHomeTab(ActionEvent event) throws IOException {
//        loadTab("/views/panel_dashboard.fxml");
//    }
//
//    @FXML
//    void loadManageClassTab(ActionEvent event) throws IOException {
//        loadTab("/views/panel_manage_classes.fxml");
//    }
//
//    @FXML
//    void loadRegisterStudentTab(ActionEvent event) throws IOException {
//        loadTab("/views/panel_register_student.fxml");
//    }
//
//    @FXML
//    void loadSearchStudentTab(ActionEvent event) throws IOException {
//        loadTab("/views/panel_search_student.fxml");
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadTab("/views/panel_dashboard.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTab(String path) throws IOException {
        AnchorPane dashboardPanel = FXMLLoader.load(this.getClass().getResource(path));
        panelLoader.getChildren().setAll(dashboardPanel);
    }
}

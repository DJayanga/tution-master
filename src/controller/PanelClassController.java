package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.NotifyController;
import dto.ClassDTO;
import dto.ClassDescriptionDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repo.ClassService;
import repo.QueryDAO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PanelClassController implements Initializable {

    @FXML
    public TableView<ClassDescriptionDTO> classTable;
    public JFXTextField classNameInput;
    public JFXComboBox<String> yearInput;
    public JFXButton saveNewClassBtn;
    public JFXButton clearInputBtn;
    public Label formActionError;

    private ClassService CLASS_SERVICE = new ClassService();
    private QueryDAO QUERY_SERVICE;

    private String[] yearsList = {"2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027",
            "2028",};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            try {
                QUERY_SERVICE = new QueryDAO();

                intiUIComponents();
                loadAllClasses();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void intiUIComponents() {
        yearInput.getItems().setAll(yearsList);
    }

    private void loadAllClasses() throws Exception {
        ArrayList<ClassDescriptionDTO> classDTOS = QUERY_SERVICE.searchClassWithStudentCount();
        updateTableData(classDTOS);
    }

    private void loadAllClasses(String yearName) throws Exception {
        ArrayList<ClassDescriptionDTO> classDTOS = QUERY_SERVICE.searchClassWithStudentCount(yearName);
        updateTableData(classDTOS);
    }

    private void updateTableData(ArrayList<ClassDescriptionDTO> classDTOS) {
        ObservableList<ClassDescriptionDTO> classListObs = FXCollections.observableArrayList(classDTOS);
        classTable.setItems(classListObs);
        classTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("className"));
        classTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("yearName"));
        classTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("count"));
    }

    public void selectYearInputValueUpdated(ActionEvent actionEvent) throws Exception {
        String selectedYear = yearInput.getSelectionModel().getSelectedItem();
        if (selectedYear != null) {
            ArrayList<ClassDescriptionDTO> classByYear = QUERY_SERVICE.searchClassWithStudentCount(selectedYear);
            updateTableData(classByYear);
        }
    }

    public void saveNewClass(ActionEvent actionEvent) throws Exception {
        if (validateCorrectInput()) {
            String selectedYear = yearInput.getSelectionModel().getSelectedItem();
            ClassDTO classDTO = new ClassDTO(0, classNameInput.getText(), selectedYear);
            boolean isSaved = CLASS_SERVICE.saveClass(classDTO);
            if (isSaved) {
                NotifyController.displayNotification("Success !", "Successfully added new class");
                loadAllClasses();
            } else {
                NotifyController.displayNotification("Error !", "Action cannot be completed ");
            }
        }
    }

    private boolean validateCorrectInput() {
        String classNameInput = this.classNameInput.getText();
        if (classNameInput != null && !classNameInput.isEmpty() && classNameInput.length() <= 10) {

            if (yearInput.getSelectionModel().getSelectedItem() != null) {
                formActionError.setText("");
                return true;
            } else {
                formActionError.setText("Select year to continue !");
                return false;
            }

        } else {
            formActionError.setText("Class Name Cannot be empty or less than 10 characters !");
            return false;
        }
    }

    public void clearInputFields(ActionEvent actionEvent) throws Exception {
        classNameInput.setText("");
        loadAllClasses();
    }
}

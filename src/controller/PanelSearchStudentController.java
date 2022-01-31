package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.NotifyController;
import dto.ClassFilterDTO;
import dto.StudentClassDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import repo.ClassService;
import repo.StudentService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PanelSearchStudentController implements Initializable {

    private StudentService STUDENT_SERVICE = new StudentService();
    private ClassService CLASS_SERVICE = new ClassService();

    @FXML
    private JFXTextField nameFilterInput;

    @FXML
    private JFXComboBox<ClassFilterDTO> gradeFilterInput;

    @FXML
    private JFXTextField telephoneFilterInput;

    @FXML
    private TableView<StudentClassDTO> studentTable;

    private void loadAllStudents() throws Exception {
        ArrayList<StudentClassDTO> students = STUDENT_SERVICE.getAllStudentsWithClass();
        updateTableData(students);
    }

    private void updateTableData(ArrayList<StudentClassDTO> classDTOS) {
        ObservableList<StudentClassDTO> classListObs = FXCollections.observableArrayList(classDTOS);
        studentTable.setItems(classListObs);
        studentTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("full_name"));
        studentTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("className"));
        studentTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("classYear"));
        studentTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        studentTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("telephone_no"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            try {
                loadAllStudents();
                loadAllClasses();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void loadAllClasses() throws Exception {
        ArrayList<ClassFilterDTO> classDTOS = CLASS_SERVICE.getAllClassesForSearch();
        gradeFilterInput.getItems().setAll(classDTOS);
    }

    @FXML
    void searchStudentByNameAction(KeyEvent event) throws Exception {
        String stuName = nameFilterInput.getText().trim();
        if (!stuName.isEmpty()) {
            ArrayList<StudentClassDTO> allStudentsByName = STUDENT_SERVICE.getAllStudentsByName(stuName);
            updateTableData(allStudentsByName);
        } else {
            loadAllStudents();
        }
    }

    @FXML
    void searchStudentByTelephoneAction(KeyEvent event) throws Exception {
        String tpNumber = telephoneFilterInput.getText().trim();
        if (!tpNumber.isEmpty()) {
            ArrayList<StudentClassDTO> allStudentsByName = STUDENT_SERVICE.getAllStudentsByTelephone(tpNumber);
            updateTableData(allStudentsByName);
        } else {
            loadAllStudents();
        }
    }

    @FXML
    void selectYearInputValueUpdated(ActionEvent event) throws Exception {
        ClassFilterDTO selectedItem = gradeFilterInput.getSelectionModel().getSelectedItem();
        ArrayList<StudentClassDTO> students = STUDENT_SERVICE.getAllStudentsByClass(selectedItem);
        updateTableData(students);
    }

    public void selectRemoveStudent(MouseEvent mouseEvent) throws Exception {
        StudentClassDTO selectedItem = studentTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete !");
        alert.setContentText("You are going to remove " + selectedItem.getFull_name() + ", Confirm ?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            removeStudentByStudentID(selectedItem);
        } else {
            alert.close();
        }
    }

    private void removeStudentByStudentID(StudentClassDTO selectedItem) throws Exception {
        boolean removed = STUDENT_SERVICE.removeStudent(selectedItem.getStu_id());
        if (removed) {
            NotifyController.displayNotification("Success !", "Student Removed Successfully ");
        } else {
            NotifyController.displayNotification("Error !", "Action Cannot be completed");
        }
        loadAllStudents();
    }
}

package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import common.NotifyController;
import dto.ClassDTO;
import dto.StudentDTO;
import generator.IDGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import repo.ClassService;
import repo.StudentService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PanelRegisterStudentController implements Initializable {

    public Label formActionError;
    @FXML
    private JFXTextField studentFullNameInput;
    @FXML
    private JFXComboBox<String> selectGenderInput;
    @FXML
    private JFXComboBox<String> selectYearInput;
    @FXML
    private JFXTextField telephoneNumberInput;
    @FXML
    private JFXTextField addressInput;
    @FXML
    private JFXComboBox<ClassDTO> selectGradeInput;
    private ClassService CLASS_SERVICE = new ClassService();
    private StudentService STUDENT_SERVICE = new StudentService();

    private String[] yearsList = {"2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027",
            "2028",};

    private String[] genders = {"male", "female"};

    @FXML
    void clearInputOnAction(ActionEvent event) {
        clearInputs();
    }

    @FXML
    void saveStudentOnAction(ActionEvent event) throws Exception {
        if (validSaveAction()) {
            String stu_id = IDGenerator.getNewID("student", "stu_id", "STU-");
            System.out.println(stu_id);
            StudentDTO studentDTO = new StudentDTO(
                    stu_id,
                    studentFullNameInput.getText(),
                    addressInput.getText(),
                    selectGenderInput.getSelectionModel().getSelectedItem(),
                    telephoneNumberInput.getText(),
                    selectGradeInput.getSelectionModel().getSelectedItem().getClassID()
            );

            boolean result = STUDENT_SERVICE.saveStudent(studentDTO);
            if (result) {
                clearInputs();
                NotifyController.displayNotification("Success !", "Student saved successfully ");
            } else {
                NotifyController.displayNotification("Error !", "Action cannot be completed ");
            }
            System.out.println(result);
        }
        ;
    }

    private void clearInputs() {
        studentFullNameInput.setText("");
        addressInput.setText("");
        telephoneNumberInput.setText("");
        selectYearInput.getSelectionModel().selectFirst();
    }

    private boolean validSaveAction() {
        String studentName = studentFullNameInput.getText();
        String studentAddress = addressInput.getText();
        String studentTelephone = telephoneNumberInput.getText();
        if (studentName != null && !studentName.isEmpty() && studentName.length() <= 100) {

            if (selectGenderInput.getSelectionModel().getSelectedItem() != null) {

                if (studentAddress != null && !studentAddress.isEmpty() && studentAddress.length() <= 200) {

                    if (selectYearInput.getSelectionModel().getSelectedItem() != null) {

                        if (selectYearInput.getSelectionModel().getSelectedItem() != null) {

                            if (studentTelephone != null && !studentTelephone.isEmpty() && studentTelephone.matches("^[0-9]{10}$")) {

                                formActionError.setText("");
                                return true;

                            } else {
                                formActionError.setText("Input valid telephone number ! (Pattern : 077XXXXXXX)");
                                return false;
                            }

                        } else {
                            formActionError.setText("Select grade to continue !");
                            return false;
                        }

                    } else {
                        formActionError.setText("Select year to continue !");
                        return false;
                    }

                } else {
                    formActionError.setText("Student address Cannot be empty or less than 200 characters !");
                    return false;
                }

            } else {
                formActionError.setText("Select gender to continue !");
                return false;
            }

        } else {
            formActionError.setText("Student Name Cannot be empty or less than 100 characters !");
            return false;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(this::initUIComponents).start();
    }

    private void initUIComponents() {
        selectYearInput.getItems().setAll(yearsList);
        selectGenderInput.getItems().setAll(genders);
    }

    public void changeYearOnAction(ActionEvent actionEvent) throws Exception {
        String selectedYear = selectYearInput.getSelectionModel().getSelectedItem();
        if (selectedYear != null) {
            ArrayList<ClassDTO> classByYear = CLASS_SERVICE.getAllClasses(selectedYear);
            selectGradeInput.getItems().setAll(classByYear);
        }
    }
}

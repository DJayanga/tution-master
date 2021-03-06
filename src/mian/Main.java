package mian;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
//        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.alwaysOnTopProperty();
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}

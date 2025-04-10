package com.coretech.coretech;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminView.fxml"));
        Parent root = fxmlLoader.load();

        // Get the controller and set the stage reference
        BaseController controller = fxmlLoader.getController();
        controller.setStage(stage); // This will set the close request handler for each controller

        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Admin Portal");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
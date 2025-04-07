package com.coretech.coretech;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewVehicle.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Admin Portal");

//        stage.setMinWidth(800);
//        stage.setMinHeight(600);

        stage.setWidth(900);
        stage.setHeight(700);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.example.Abarrotes.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.Abarrotes.modelo.AbarrotesTizimin;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MainView.fxml"));
        Scene scene = new Scene(loader.load(), 500, 400);
        primaryStage.setTitle("Abarrotes Tizimín");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        AbarrotesTizimin.getInstancia().guardarDatos();
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}

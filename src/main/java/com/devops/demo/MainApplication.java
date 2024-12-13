package com.devops.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Utiliser le chemin absolu à partir du répertoire "resources"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/devops/demo/main.fxml"));

        // Charger la scène à partir du fichier FXML
        Scene scene = new Scene(loader.load());

        // Lier le fichier CSS
        scene.getStylesheets().add(getClass().getResource("/com/devops/demo/css/style.css").toExternalForm());

        // Définir la scène et le titre de la fenêtre
        stage.setScene(scene);
        stage.setTitle("Cookie Game");
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

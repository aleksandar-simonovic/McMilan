package org.unibl.etf.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class McMilan extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/org/unibl/etf/gui/login.fxml"));
        primaryStage.setScene(new Scene(root, 350, 470));
        primaryStage.setResizable(false);
        primaryStage.setTitle("McMilan");
        primaryStage.getIcons().add(new Image(McMilan.class.getResourceAsStream("/img/icon.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
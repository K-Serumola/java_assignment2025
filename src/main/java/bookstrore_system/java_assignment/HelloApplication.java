package bookstrore_system.java_assignment;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setTitle("main menu");
        stage.setScene(new Scene(menu));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
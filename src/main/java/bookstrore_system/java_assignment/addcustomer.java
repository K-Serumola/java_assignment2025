package bookstrore_system.java_assignment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class addcustomer {
    String filepath = "C:\\Users\\defaultuser0\\Desktop\\java_assignment\\src\\main\\resources\\customerdatabase.txt";
    @FXML
    public TextField namefeild;
    public TextField autherfeild;
    public Label wherefound;

    @FXML
    public void submit() {
        String namedata = namefeild.getText();
        String passwordrdata = autherfeild.getText();

        String[] data = {namedata, passwordrdata,};

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath,
                true))) {
            writer.newLine();
            for (String i : data)
                writer.write(i + ",/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        wherefound.setText("customer added");
    }


    @FXML
    //opening main menu
    public void openmeny() throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Stage stage = new Stage();
        stage.setTitle("menu");
        stage.setScene(new Scene(menu));
        stage.show();

        Controller.WindowManager.addStage("MenuStage",stage);

    }

}

package bookstrore_system.java_assignment;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class pageControler implements Initializable  {

    String filepath = "C:\\Users\\defaultuser0\\Desktop\\java_assignment\\src\\main\\resources\\bookdatabase.txt";
    @FXML
    public TextField namefeild;
    public TextField autherfeild;
    public TextField genrefeild;
    public TextField isbnfeild;
    public TextField descriptionfeild;
    public Label wherefound;
    public ListView <String> databaselist;

    @FXML
    public void submit() {
        String namedata = namefeild.getText();
        String autherdata = autherfeild.getText();
        String genredata = genrefeild.getText();
        String isbndata = isbnfeild.getText();
        String descriptiondata = descriptionfeild.getText();

        String[] data = {namedata, autherdata, genredata, isbndata, descriptiondata};

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath,
                true))) {
            writer.newLine();
            for (String i : data)
                writer.write(i + ",/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        wherefound.setText("Data added");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                databaselist.getItems().add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package bookstrore_system.java_assignment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.*;

public class viewControler implements Initializable {

    @FXML
    String filepath = "src/main/resources/bookdatabase.txt";
    public ListView<String> databaselist;



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

package bookstrore_system.java_assignment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class searchControler implements Initializable {
    @FXML
    String filepath = "C:\\Users\\defaultuser0\\Desktop\\java_assignment\\src\\main\\resources\\bookdatabase.txt";
    public Button searchbutton;
    public Label keywordfound;
    public Label wherefound;
    public TextField keyword;

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

    @FXML
    public void search()throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        int lineNumber = 0;
        String line;
        while ((line = reader.readLine()) != null){
            lineNumber++;
            if(line.contains(keyword.getText())){
                keywordfound.setText("Keyword Found");
                wherefound.setText("N.O: "+lineNumber);
            }
        }
    }
}

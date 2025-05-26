package bookstrore_system.java_assignment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class customerpurchase implements Initializable {

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label errorinfo;
    @FXML private Label purchaseerror;
    @FXML public ListView<String> bookdatabase;

    public String filepath = "src/main/resources/bookdatabase.txt";
    public String Saledata = "src/main/resources/saledata.txt";
    public String customerdata = "src/main/resources/customerdatabase.txt";

    private String matchedLine; // Holds the matched line (used as ID)
    private String bookselected;

    // This is called in the login screen
    @FXML
    private void checkCredentials() {
        String user = username.getText().trim();
        String pass = password.getText().trim();

        try (BufferedReader reader = new BufferedReader(new FileReader(customerdata))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",/");
                if (parts.length == 2 && parts[0].trim().equals(user) && parts[1].trim().equals(pass)) {
                    matchedLine = line;

                    // Load purchase menu screen and pass matchedLine
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("purchase menu.fxml"));
                    Parent buy = loader.load();

                    customerpurchase controller = loader.getController();
                    controller.setMatchedLine(matchedLine);

                    Stage stage = new Stage();
                    stage.setTitle("Verify Purchase");
                    stage.setScene(new Scene(buy));
                    stage.show();
                    return;
                }
            }
            errorinfo.setText("Invalid username or password.");
            matchedLine = null;
        } catch (IOException ex) {
            errorinfo.setText("Error reading file.");
            matchedLine = null;
        }
    }

    public void setMatchedLine(String matchedLine) {
        this.matchedLine = matchedLine;
    }

    public String getMatchedLine() {
        return matchedLine;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (bookdatabase != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    bookdatabase.getItems().add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            bookdatabase.setOnMouseClicked(mouseEvent -> {
                bookselected = bookdatabase.getSelectionModel().getSelectedItem();
            });
        }
    }

    @FXML
    public void purchase() {
        long isbnNumber = 0;

        if (bookselected == null) {
            purchaseerror.setText("Please select a book");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(bookselected)) {
                    String[] parts = line.split(",/");
                    if (parts.length >= 4) {
                        String isbn = parts[3].trim();
                        isbnNumber = Long.parseLong(isbn);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Saledata, true))) {
            writer.newLine();
            writer.write(getMatchedLine() + ",/" + isbnNumber);
            purchaseerror.setText("Purchase successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

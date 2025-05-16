package bookstrore_system.java_assignment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class stonkscontroler implements Initializable {
    @FXML
    public Label totalrevenue;
    public Label revenuegenerated;
    public Label price;
    public Label unitssold;
    public TextField booksearch;
    public ListView<String> bookdatabase;

    String filepath = "C:\\Users\\defaultuser0\\Desktop\\java_assignment\\src\\main\\resources\\bookdatabase.txt";
    String saledata = "C:\\Users\\defaultuser0\\Desktop\\java_assignment\\src\\main\\resources\\saledata.txt";

    public double getPriceByLine(int lineNumber) {
        // Example logic: hardcoded pricing per line
        if (lineNumber > 5) return 40.95;
        else if (lineNumber > 3) return 20.43;
        else return 60.50;
    }

    public int getUnitsSold(String isbn) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(saledata));
        int sold = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(isbn)) sold++;
        }
        reader.close();
        return sold;
    }

    @FXML
    public void search() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        int lineNumber = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            if (line.contains(booksearch.getText())) {
                String[] bookParts = line.split(",/");
                if (bookParts.length >= 3) {
                    String isbn = bookParts[2].trim();
                    double priceVal = getPriceByLine(lineNumber);
                    int units = getUnitsSold(isbn);
                    double revenue = priceVal * units;

                    price.setText("£" + priceVal);
                    unitssold.setText(Integer.toString(units));
                    totalrevenue.setText("£" + revenue);
                    revenuegenerated.setText("Book found: " + bookParts[0]);
                }
                break;
            }
        }
        reader.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                bookdatabase.getItems().add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        bookdatabase.setOnMouseClicked(mouseEvent -> {
            String selected = bookdatabase.getSelectionModel().getSelectedItem();
            if (selected == null) return;

            try {
                BufferedReader reader = new BufferedReader(new FileReader(filepath));
                int lineNumber = 0;
                String line;
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    if (line.equals(selected)) {
                        String[] parts = line.split(",/");
                        if (parts.length >= 3) {
                            String isbn = parts[2].trim();
                            double priceVal = getPriceByLine(lineNumber);
                            int units = getUnitsSold(isbn);
                            double revenue = priceVal * units;

                            price.setText("£" + priceVal);
                            unitssold.setText(Integer.toString(units));
                            totalrevenue.setText("£" + revenue);
                            revenuegenerated.setText("Book: " + parts[0]);
                        }
                        break;
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

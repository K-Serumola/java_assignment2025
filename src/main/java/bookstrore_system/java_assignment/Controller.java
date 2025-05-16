package bookstrore_system.java_assignment;

import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import java.util.*;
import java.io.IOException;


public class Controller {

    //class of the stages for easy management
    public class WindowManager {
        // Map to hold stages by a name key
        private static final Map<String, Stage> stageMap = new HashMap<>();

        public static void addStage(String name, Stage stage) {
            stageMap.put(name, stage);
        }

        public static Stage getStage(String name) {
            return stageMap.get(name);
        }

        public static void closeStage(String name) {
            Stage stage = stageMap.get(name);
            if (stage != null && stage.isShowing()) {
                stage.close();
            }
        }
    }

    Stage stage = new Stage();

    //gui
    @FXML
    //opening view database ui
    public void openview() throws IOException {
        Parent View = FXMLLoader.load(getClass().getResource("view.fxml"));
        stage.setTitle("view database");
        stage.setScene(new Scene(View));
        stage.show();

        WindowManager.addStage("ViewStage", stage);
        //closing leading stages
        WindowManager.closeStage("StaffStage");
        WindowManager.closeStage("CustomerStage");
    }

    @FXML
    //opening search ui
    public void opensearch() throws IOException {
        Parent search = FXMLLoader.load(getClass().getResource("Search.fxml"));
        stage.setTitle("search database");
        stage.setScene(new Scene(search));
        stage.show();

        WindowManager.addStage("SearchStage", stage);
        //closing leading stages
        WindowManager.closeStage("StaffStage");
        WindowManager.closeStage("CustomerStage");
    }

    @FXML
    //opening main menu
    public void openmeny() throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setTitle("menu");
        stage.setScene(new Scene(menu));
        stage.show();

        WindowManager.addStage("MenuStage", stage);
        //closing leading stages
        stage.close();
    }

    @FXML
    //opening customer menu
    public void opencustomer() throws IOException {
        Parent customer = FXMLLoader.load(getClass().getResource("customer.fxml"));
        stage.setTitle("customer");
        stage.setScene(new Scene(customer));
        stage.show();

        WindowManager.addStage("CustomerStage", stage);
        //closing leading stages
        WindowManager.closeStage("MenuStage");
    }

    @FXML
    //opening main menu
    public void staffmenu() throws IOException {
        Parent staff = FXMLLoader.load(getClass().getResource("staff.fxml"));
        stage.setTitle("staff menu");
        stage.setScene(new Scene(staff));
        stage.show();

        WindowManager.addStage("StaffStage", stage);
        //closing leading stages
        WindowManager.closeStage("MenuStage");
    }

    @FXML
    //opening the add menu
    public void addmenu() throws IOException {
        Parent add = FXMLLoader.load(getClass().getResource("add.fxml"));
        stage.setTitle("add to database");
        stage.setScene(new Scene(add));
        stage.show();

        WindowManager.addStage("AddStage", stage);
        //closing leading stages
        WindowManager.closeStage("MenuStage");
        WindowManager.closeStage("StaffStage");
    }

    @FXML
    //opening add customer menu
    public void add_customer() throws IOException {
        Parent add_customer = FXMLLoader.load(getClass().getResource("addcustomer.fxml"));
        stage.setTitle("add customer");
        stage.setScene(new Scene(add_customer));
        stage.show();

        WindowManager.addStage("add_customer",stage);

    }

    @FXML
    //opening add customer menu
    public void view_customer() throws IOException {
        Parent view_customer = FXMLLoader.load(getClass().getResource("viewCustomerdata.fxml"));
        stage.setTitle("add customer");
        stage.setScene(new Scene(view_customer));
        stage.show();

        WindowManager.addStage("view_customer",stage);
    }
    @FXML
    public void purchase() throws IOException {
        // Load the login screen (customerlogin.fxml)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/bookstrore_system/java_assignment/customerlogin.fxml"));
        Parent login = loader.load();

        stage.setTitle("Login");
        stage.setScene(new Scene(login));
        stage.show();
    }

    @FXML
    public void revenueinfo() throws IOException {
        // Load the login screen (customerlogin.fxml)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stonks.fxml"));
        Parent revenue = loader.load();

        stage.setTitle("revenue");
        stage.setScene(new Scene(revenue));
        stage.show();
    }
}

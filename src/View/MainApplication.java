package View;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainApplication extends Application {

    static PersonList personList = new PersonList();
    public static Inventory inventory;
    public static Person loggedInUser;
    static Parent root;
    static Scene scene;
    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        personList = PersonList.loadFromFile();
        personList.addToPersonList(new Customer("aaaaaa", "aaaaaa", "aaa", "aa",
                "aaa", "jr", "mr", "aaa@aa.aa"));
        root = FXMLLoader.load(getClass().getResource("login_page.fxml"));
        scene = new Scene(root,650, 400);
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop(){
        inventory.saveToFile();
        personList.saveToFile();
    }

    public static void main(String[] args) {

        inventory = new Inventory();
        Product p1 = new Product("1", "Shirt 1", "SWag1", "Black",
                10.0, new ArrayList<>(), 0.0f, "pic1.jpg", 12);
        Product p2 = new Product("2", "Sweatshirt", "SWag2", "Red",
                20.0, new ArrayList<>(), 0.0f, "pic2.jpg", 25);
        Product p3 = new Product("3", "Pants", "A fine pair of pants", "Tan",
                50.0, new ArrayList<>(), 5.0f, "pic3.jpg", 10);
        inventory.addToInventory(p1, p1.getQuantity());
        inventory.addToInventory(p2, p2.getQuantity());
        inventory.addToInventory(p3, p3.getQuantity());
        inventory.saveToFile();

        launch(args);
    }
}

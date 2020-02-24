package View;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class MainApplication extends Application {

    public static PersonList personList = new PersonList();
    public static Inventory masterInventory, inventory;
    public static Person loggedInUser;
    public static Parent root;
    public static Scene scene;
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        File personListFile = new File("personList.txt");
        if(personListFile.exists()) {
            personList = PersonList.loadFromFile();
        }else {
            PersonList personList = new PersonList();
            personList.addToPersonList(new Customer("aaaaaa", "aaaaaa", "aaa", "aa",
                    "aaa", "jr", "mr", "aaa@aa.aa"));
            this.personList = personList;
        }
        root = FXMLLoader.load(getClass().getResource("login_page.fxml"));
        scene = new Scene(root,650, 400);
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop(){
        masterInventory.saveToFile();
        ((Customer) loggedInUser).getShoppingCart().reset();
        personList.saveToFile();
    }

    public static void main(String[] args) {
        File masterInventoryfile = new File("inventory.txt");
        if(masterInventoryfile.exists()) {
            masterInventory = Inventory.loadFromFile();
        }else{
            masterInventory = new Inventory();
            Product p1 = new Product("1", "Shirt", "A casual T-Shirt", "Black",
                    10.0, new ArrayList<>(), 0.0f, "../Images/Shirt.png", 12);
            Product p2 = new Product("2", "Sweatshirt", "A warm SweatShirt, but not too warm to make you sweat", "Red",
                    20.0, new ArrayList<>(), 0.0f, "../Images/Sweatshirt.png", 25);
            Product p3 = new Product("3", "Pants", "A fine pair of pants, plaid ain't bad", "Tan",
                    50.0, new ArrayList<>(), 5.0f, "../Images/Pants.png", 10);
            masterInventory.addToInventory(p1, p1.getQuantity());
            masterInventory.addToInventory(p2, p2.getQuantity());
            masterInventory.addToInventory(p3, p3.getQuantity());
            masterInventory.saveToFile();
        }
        inventory = masterInventory;

        launch(args);
    }
}

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
                    10.0, new ArrayList<>(), 1.5f, "../Images/Shirt.png", 12);
            Product p2 = new Product("2", "Sweatshirt", "A warm sweatswirt, but not too warm to make you sweat", "Red",
                    20.0, new ArrayList<>(), 3.0f, "../Images/Sweatshirt.png", 25);
            Product p3 = new Product("3", "Pants", "A fine pair of pants, plaid ain't bad", "Tan",
                    50.0, new ArrayList<>(), 4.0f, "../Images/Pants.png", 10);
            Product p4 = new Product("4", "Blouse", "A flowery blouse", "Yellow",
                    50.0, new ArrayList<>(), 3.0f, "../Images/Blouse.png", 15);
            Product p5 = new Product("5", "Sweatpants", "A pair of sweatpants, perfect for a lazy day", "Grey",
                    50.0, new ArrayList<>(), 2.2f, "../Images/Sweatpants.png", 10);
            Product p6 = new Product("6", "Leather Jacket", "A Jacket only fit for the baddest of the bad", "Black",
                    50.0, new ArrayList<>(), 5.0f, "../Images/Jacket.png", 5);
            masterInventory.addToInventory(p1, p1.getQuantity());
            masterInventory.addToInventory(p2, p2.getQuantity());
            masterInventory.addToInventory(p3, p3.getQuantity());
            masterInventory.addToInventory(p4, p4.getQuantity());
            masterInventory.addToInventory(p5, p5.getQuantity());
            masterInventory.addToInventory(p6, p6.getQuantity());
            masterInventory.saveToFile();
        }
        inventory = masterInventory;

        launch(args);
    }
}

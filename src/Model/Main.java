package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Control.Inventory;
import java.util.ArrayList;

public class Main extends Application {
    static Parent root;
    static Scene scene;
    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        setRoot(FXMLLoader.load(getClass().getResource("../View/InventoryDisplay.fxml")));
        setScene(new Scene(root,650, 400));
        setStage(primaryStage);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        Product p1 = new Product("1", "Shirt 1", "SWag1", "Black",
                10.0, new ArrayList<>(), 0.0f, "pic1.jpg", 1);
        Product p2 = new Product("2", "Sweatshirt", "SWag2", "Red",
                20.0, new ArrayList<>(), 0.0f, "pic2.jpg", 1);
        inventory.addToInventory(p1, p1.getQuantity());
        inventory.addToInventory(p2, p2.getQuantity());
        inventory.saveToFile();

        launch(args);
    }

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        Main.root = root;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        Main.scene = scene;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
}
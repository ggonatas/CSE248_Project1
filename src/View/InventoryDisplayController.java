package View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import Control.Inventory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import Model.Main;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class InventoryDisplayController {

    @FXML
    private FlowPane buttonPane;
    @FXML
    private searchableTextField searchTextField;
    @FXML
    private VBox rightVBox;

    @FXML
    void showProductDisplay() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductDisplay.fxml"));
            Parent newRoot = loader.load();
            Main.getScene().setRoot(newRoot);
            Main.getStage().show();

            ProductDisplayController productDisplayController = loader.getController();
            productDisplayController.showProductByProductID(1);
        }catch (IOException ex) { System.err.println(ex); }
    }

    @FXML
    void showProductDisplay(int i) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductDisplay.fxml"));
            Parent newRoot = loader.load();
            Main.getScene().setRoot(newRoot);
            Main.getStage().show();

            ProductDisplayController productDisplayController = loader.getController();
            productDisplayController.showProductByProductID(i);
        }catch (IOException ex) { System.err.println(ex); }
    }

    @FXML
    void showInventoryDisplay() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryDisplay.fxml"));
            Parent newRoot = loader.load();
            Main.getScene().setRoot(newRoot);
            Main.getStage().show();
        }catch (IOException ex) { System.err.println(ex); }
    }

    void createProductButtons(){
        Inventory inventory = Inventory.loadFromFile();
        int i = 1;
        while(!(inventory.getProduct(String.valueOf(i)) == null)){
            int productID = i;
            //String imagePath = inventory.getProduct(String.valueOf(i)).getImage);
            Button button = new Button(inventory.getProduct(String.valueOf(i)).getName());
            button.setPrefSize(133,126);
            button.setOnAction(a -> showProductDisplay(productID));
            buttonPane.getChildren().add(button);
            i++;
        }
    }

    public void initialize() {
        createProductButtons();

        ArrayList<String> productNames = new ArrayList<>();
        Inventory inventory = Inventory.loadFromFile();
        int i=1;
        while(!(inventory.getProduct(String.valueOf(i)) == null)) {
            productNames.add(inventory.getProduct(String.valueOf(i)).getName());
            i++;
        }
        searchTextField = new searchableTextField(productNames);
        rightVBox.getChildren().add(searchTextField);
    }
}

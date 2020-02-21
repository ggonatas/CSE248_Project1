package View;

import java.io.IOException;
import java.util.ArrayList;

import Control.Inventory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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

    /**
     * @param serialNum
     * loads ProductDisplay.fxml and sets it as root
     * loads product by its serialNum and updates root with the products info
     */
    @FXML
    void showProductDisplayBySerialNum(String serialNum) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductDisplay.fxml"));
            Parent newRoot = loader.load();
            Main.getScene().setRoot(newRoot);
            Main.getStage().show();

            ProductDisplayController productDisplayController = loader.getController();
            productDisplayController.showProductBySerialNum(serialNum);
        }catch (IOException ex) { System.err.println(ex); }
    }

    /**
     * loads InventoryDisplay.fxml
     */
    @FXML
    void showInventoryDisplay() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryDisplay.fxml"));
            Parent newRoot = loader.load();
            Main.getScene().setRoot(newRoot);
            Main.getStage().show();
        }catch (IOException ex) { System.err.println(ex); }
    }

    /**
     * adds a button for each product in inventory to InventoryDisplay
     * each button calls showProductDisplayBySerialNum with its products' serialNum
     */
    void createProductButtons(){
        Inventory inventory = Inventory.loadFromFile();
        int i = 1;
        while(!(inventory.getProduct(String.valueOf(i)) == null)){
            String serialNum = String.valueOf(i);
            //String imagePath = inventory.getProduct(String.valueOf(i)).getImage);
            Button button = new Button(inventory.getProduct(String.valueOf(i)).getName());
            button.setPrefSize(133,126);
            button.setOnAction(a -> showProductDisplayBySerialNum(serialNum));
            buttonPane.getChildren().add(button);
            i++;
        }
    }

    /**
     * @return an ArrayList of the names of all products in inventory
     */
    ArrayList<String> getProductNames(){
        ArrayList<String> productNames = new ArrayList<>();
        Inventory inventory = Inventory.loadFromFile();
        int i=1;
        while(!(inventory.getProduct(String.valueOf(i)) == null)) {
            productNames.add(inventory.getProduct(String.valueOf(i)).getName());
            i++;
        }
        return productNames;
    }

    /**
     * instantiates buttons and search bar to the InventoryDisplay root
     */
    public void initialize() {
        createProductButtons();

        ArrayList<String> productNames = getProductNames();
        searchTextField = new searchableTextField(productNames);
        rightVBox.getChildren().add(searchTextField);
    }
}

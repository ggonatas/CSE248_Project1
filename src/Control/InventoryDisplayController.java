package Control;

import java.io.IOException;
import java.util.ArrayList;

import Model.Inventory;
import Model.Product;
import View.MainApplication;
import View.searchableTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ProductDisplay.fxml"));
        try { MainApplication.root = loader.load(); }
        catch (IOException e) { e.printStackTrace(); }
        MainApplication.scene.setRoot(MainApplication.root);

        ProductDisplayController productDisplayController = loader.getController();
        productDisplayController.showProductBySerialNum(serialNum);
    }

    /**
     * loads InventoryDisplay.fxml
     */
    @FXML
    void showInventoryDisplay() {
        try { MainApplication.root = FXMLLoader.load(getClass().getResource("../View/InventoryDisplay.fxml")); }
        catch (IOException e) { e.printStackTrace(); }
        MainApplication.scene.setRoot(MainApplication.root);
    }

    @FXML
    void showCheckoutDisplay(){
        try { MainApplication.root = FXMLLoader.load(getClass().getResource("../View/ShoppingCartDisplay.fxml")); }
        catch (IOException e) { e.printStackTrace(); }
        MainApplication.scene.setRoot(MainApplication.root);
    }

    /**
     * adds a button for each product in inventory to InventoryDisplay
     * each button calls showProductDisplayBySerialNum with its products' serialNum
     */
    void createProductButtons(){
        for (Product product : MainApplication.inventory.getInventory().values()){
            Image image = new Image(getClass().getResource(product.getPic()).toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(80);
            Button button = new Button(product.getName(),imageView);
            button.setContentDisplay(ContentDisplay.TOP);
            button.setPrefSize(133,126);
            button.setOnAction(a -> showProductDisplayBySerialNum(product.getSerialNum()));
            buttonPane.getChildren().add(button);
        }
    }

    /**
     * @return an ArrayList of the names of all products in inventory
     */
    ArrayList<String> getProductNames(){
        ArrayList<String> productNames = new ArrayList<>();
        Inventory inventory = Inventory.loadFromFile();
        for( Product product : inventory.getInventory().values()){
            productNames.add(product.getName());
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

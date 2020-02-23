package Control;

import Model.ShoppingCart;
import Model.Main;
import Model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CheckoutDisplayController {

    @FXML
    private VBox checkoutVBox;

    @FXML
    void showInventoryDisplay() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/InventoryDisplay.fxml"));
            Parent newRoot = loader.load();
            Main.getScene().setRoot(newRoot);
            Main.getStage().show();
        }catch (IOException ex) { System.err.println(ex); }
    }

    void displayShoppingCart(){
        ShoppingCart shoppingCart = ShoppingCart.loadFromFile();
        for(Product product : shoppingCart.getInventory().values()){
            String totalCost = String.valueOf(product.getQuantity() * product.getPrice());
            Label label = new Label(product.getQuantity()+ " " +product.getName()+": " + totalCost);
            checkoutVBox.getChildren().add(label);
        }
    }

    public void initialize() {
        displayShoppingCart();
    }
}

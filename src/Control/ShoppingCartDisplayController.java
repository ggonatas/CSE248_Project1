package Control;

import Model.Customer;
import Model.ShoppingCart;
import Model.Product;
import View.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

public class ShoppingCartDisplayController {

    @FXML
    private VBox checkoutVBox;
    private int numAdded = 2;

    @FXML
    void showInventoryDisplay() {
        try { MainApplication.root = FXMLLoader.load(getClass().getResource("../View/InventoryDisplay.fxml")); }
        catch (IOException e) { e.printStackTrace(); }
        MainApplication.scene.setRoot(MainApplication.root);
    }

    @FXML
    void checkout() {
        try { MainApplication.root = FXMLLoader.load(getClass().getResource("../View/address_page.fxml")); }
        catch (IOException e) { e.printStackTrace(); }
        MainApplication.scene.setRoot(MainApplication.root);
    }

    void removeFromShoppingCart(String serialNum){
        ((Customer) MainApplication.loggedInUser).getShoppingCart().removeFromCart(serialNum);
        checkoutVBox.getChildren().remove(2,numAdded);
        displayShoppingCart();
    }
    void displayShoppingCart(){
        Button button = new Button("CheckOut");
        button.setOnAction(a -> checkout());
        ShoppingCart shoppingCart = ((Customer)MainApplication.loggedInUser).getShoppingCart();
        if(!shoppingCart.getInventory().isEmpty()) {
            for (Product product : shoppingCart.getInventory().values()) {
                String serialNum = product.getSerialNum();
                Button removeButton = new Button("X");
                removeButton.setOnAction(a -> removeFromShoppingCart(serialNum));
                Label label = new Label(product.getQuantity() + " " + product.getName() + ": $" + shoppingCart.getSubtotal());
                label.setTextAlignment(TextAlignment.CENTER);
                HBox hBox = new HBox();
                hBox.getChildren().addAll(removeButton,label);
                hBox.setAlignment(Pos.CENTER);
                checkoutVBox.getChildren().add(hBox);
                numAdded++;
            }
        }else {
                Label label = new Label("Your ShoppingCart is empty!");
                checkoutVBox.getChildren().add(label);
                numAdded++;
                button.setDisable(true);
        }

        Label totalPriceLabel = new Label("Total: $" + shoppingCart.getSubtotal());
        checkoutVBox.getChildren().add(totalPriceLabel);
        numAdded++;
        checkoutVBox.getChildren().add(button);
        numAdded++;
    }

    public void initialize() {
        displayShoppingCart();
    }
}

package Control;

import Model.Customer;
import Model.ShoppingCart;
import Model.Product;
import View.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import sun.applet.Main;

import javax.swing.*;
import java.io.IOException;

public class ShoppingCartDisplayController {

    @FXML
    private VBox checkoutVBox;

    @FXML
    void showInventoryDisplay() {
        try { MainApplication.root = FXMLLoader.load(getClass().getResource("../View/InventoryDisplay.fxml")); }
        catch (IOException e) { e.printStackTrace(); }
        MainApplication.scene.setRoot(MainApplication.root);
    }

    @FXML
    void checkout() {
        MainApplication.masterInventory = MainApplication.inventory;
        MainApplication.masterInventory.saveToFile();
    }

    void displayShoppingCart(){
        Button button = new Button("CheckOut");
        button.setOnAction(a -> checkout());
        ShoppingCart shoppingCart = ((Customer)MainApplication.loggedInUser).getShoppingCart();
        double totalShoppingCartPrice = 0.00;
        if(!shoppingCart.getInventory().isEmpty()) {
            for (Product product : shoppingCart.getInventory().values()) {
                String totalCost = String.valueOf(product.getQuantity() * product.getPrice());
                totalShoppingCartPrice += Double.parseDouble(totalCost);
                //TODO add removing from cart button and functionality
                Label label = new Label(product.getQuantity() + " " + product.getName() + ": $" + totalCost);
                checkoutVBox.getChildren().add(label);
            }
        }else {
                Label label = new Label("Your ShoppingCart is empty!");
                checkoutVBox.getChildren().add(label);
                button.setDisable(true);
        }

        Label totalPriceLabel = new Label("Total: $" + totalShoppingCartPrice);
        checkoutVBox.getChildren().add(totalPriceLabel);
        checkoutVBox.getChildren().add(button);
    }

    public void initialize() {
        displayShoppingCart();
    }
}

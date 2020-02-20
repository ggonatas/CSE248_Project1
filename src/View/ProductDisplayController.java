package View;

import java.io.IOException;
import Model.Main;
import Control.Inventory;
import Model.Product;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ProductDisplayController {

    private int quantity = 1;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label quantityLabel = new Label(Integer.toString(quantity));

    @FXML
    private Label ProductDescriptionLabel, ProductNameLabel, ProductRatingLabel, ProductPriceLabel;

    @FXML
    private ImageView ProductImage;

    @FXML
    private Button DecreaseQuantityBtn, IncreaseQuantityBtn;

    @FXML
    void DecreaseQuantity() {
        if(quantity>0){
            quantity--;
        }
        if(quantity==0){
            DecreaseQuantityBtn.setDisable(true);
        }
        quantityLabel.setText(Integer.toString(quantity));
    }

    @FXML
    void IncreaseQuantity() {
        quantity++;
        if(quantity == 1){
            DecreaseQuantityBtn.setDisable(false);
        }
        quantityLabel.setText(Integer.toString(quantity));
    }

    @FXML
    void showInventoryDisplay() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InventoryDisplay.fxml"));
        Main.getScene().setRoot(root);
        Main.getStage().show();
    }

    public void initialize() {
        Inventory inventory = Inventory.loadFromFile();
        Product p = inventory.getProduct(String.valueOf(1));
        ProductNameLabel.setText(p.getName());
        ProductDescriptionLabel.setText(p.getDescription());
        //ProductImage.setImage(); Product needs a getPic class, that at least returns image filename
        ProductRatingLabel.setText("Rating: " + p.getScore() + "/5");
        ProductPriceLabel.setText("$" + p.getPrice());
    }

}

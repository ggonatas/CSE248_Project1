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

    private int productID;
    private int quantity = 1;

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
        quantity--;
        if(quantity==0){
            DecreaseQuantityBtn.setDisable(true);
        }
        if(quantity < loadProduct(productID).getQuantity()){
            IncreaseQuantityBtn.setDisable(false);
        }
        quantityLabel.setText(Integer.toString(quantity));
    }

    @FXML
    void IncreaseQuantity() {
        quantity++;
        if(quantity == 1){
            DecreaseQuantityBtn.setDisable(false);
        }
        if(quantity == loadProduct(productID).getQuantity()){
            IncreaseQuantityBtn.setDisable(true);
        }
        quantityLabel.setText(Integer.toString(quantity));
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

    public void showProductByProductID(int productID) {
        this.productID = productID;
        Product p = loadProduct(productID);
        ProductNameLabel.setText(p.getName());
        ProductDescriptionLabel.setText(p.getDescription());
        //ProductImage.setImage(); Product needs a getPic class, that at least returns image filename
        ProductRatingLabel.setText("Rating: " + p.getScore() + "/5");
        ProductPriceLabel.setText("$" + p.getPrice());
    }

    public Product loadProduct(int productID){
        Inventory inventory = Inventory.loadFromFile();
        return inventory.getProduct(String.valueOf(productID));
    }
    public void initialize() {
    }

}

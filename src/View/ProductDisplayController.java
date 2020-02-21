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

public class ProductDisplayController {

    private String serialNum;
    private int quantity = 1;

    @FXML
    private Label quantityLabel = new Label(Integer.toString(quantity));

    @FXML
    private Label ProductDescriptionLabel, ProductNameLabel, ProductScoreLabel, ProductPriceLabel,totalLabel;

    @FXML
    private ImageView ProductImage;

    @FXML
    private Button DecreaseQuantityBtn, IncreaseQuantityBtn;

    /**
     * Decrements the shown quantity on quantityLabel
     * if quantity is one, disables DecreaseQuantityBtn
     * if quantity is less than max quantity, enables IncreaseQuantityBtn
     */
    @FXML
    void DecreaseQuantity() {
        quantity--;
        Inventory inventory = Inventory.loadFromFile();
        Product p = inventory.getProduct(serialNum);
        if(quantity==1){
            DecreaseQuantityBtn.setDisable(true);
        }
        if(quantity < p.getQuantity()){
            IncreaseQuantityBtn.setDisable(false);
        }
        quantityLabel.setText(Integer.toString(quantity));
        calculateTotal();
    }

    /**
     * Increments the shown quantity on quantityLabel
     * if quantity is max quantity, disables IncreaseQuantityBtn
     * if quantity is greater than one, enables DecreaseQuantityBtn
     */
    @FXML
    void IncreaseQuantity() {
        quantity++;
        Inventory inventory = Inventory.loadFromFile();
        Product p = inventory.getProduct(serialNum);
        if(quantity == 2){
            DecreaseQuantityBtn.setDisable(false);
        }
        if(quantity == p.getQuantity()){
            IncreaseQuantityBtn.setDisable(true);
        }
        quantityLabel.setText(Integer.toString(quantity));
        calculateTotal();
    }

    /**
     * loads InventoryDisplay.fxml and sets it as the new root
     * potentially throws IOException
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
     * sets totalLabel to the current quantity of quantityLabel times the price of the product
     */
    @FXML
    void calculateTotal(){
        String quantity = quantityLabel.getText();
        String price = ProductPriceLabel.getText().substring(1);
        totalLabel.setText("Total: $" + Double.parseDouble(quantity) * Double.parseDouble(price));
    }

    /**
     * @param serialNum
     * loads product by its serialNum
     * gets variables Name, Description, Score, and Price
     * Sets their respective label displays to reflect those variables
     */
    public void showProductBySerialNum(String serialNum) {
        this.serialNum = serialNum;
        Inventory inventory = Inventory.loadFromFile();
        Product p = inventory.getProduct(String.valueOf(serialNum));
        ProductNameLabel.setText(p.getName());
        ProductDescriptionLabel.setText(p.getDescription());
        //ProductImage.setImage(); Product needs a getPic class, that at least returns image filename
        ProductScoreLabel.setText("Rating: " + p.getScore() + "/5.0");
        ProductPriceLabel.setText("$" + p.getPrice());
        totalLabel.setText("Total: $" + p.getPrice());
    }

    /**
     * @param name
     * loads product by its name
     * gets variables Name, Description, Score, and Price
     * Sets their respective label displays to reflect those variables
     */
    public void showProductByName(String name) {
        Inventory inventory = Inventory.loadFromFile();
        Product p = inventory.getProductByName(name);
        this.serialNum = p.getSerialNum();
        ProductNameLabel.setText(p.getName());
        ProductDescriptionLabel.setText(p.getDescription());
        //ProductImage.setImage(); Product needs a getPic class, that at least returns image filename
        ProductScoreLabel.setText("Rating: " + p.getScore() + "/5.0");
        ProductPriceLabel.setText("$" + p.getPrice());
        totalLabel.setText("Total: $" + p.getPrice());
    }

    public void initialize() {
    }
}

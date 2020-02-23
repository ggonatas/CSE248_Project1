package Control;

import java.io.IOException;

import Model.*;
import View.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ProductDisplayController {

    private String serialNum;
    private int quantity = 1;
    private Product product;

    Product getProductBySerialNum(){
        Product p = MainApplication.inventory.getProduct(serialNum);
        return p;
    }
    @FXML
    private Label quantityLabel = new Label(Integer.toString(quantity));
    @FXML
    private Label ProductDescriptionLabel, ProductNameLabel, ProductScoreLabel, ProductPriceLabel,totalLabel;
    @FXML
    private ImageView ProductImage;
    @FXML
    private Button DecreaseQuantityBtn, IncreaseQuantityBtn, AddtoCartBtn;
    @FXML
    private VBox ProductDisplayRight;
    /**
     * Decrements the shown quantity on quantityLabel
     * if quantity is one, disables DecreaseQuantityBtn
     * if quantity is less than max quantity, enables IncreaseQuantityBtn
     */
    @FXML
    void DecreaseQuantity() {
        Product product = getProductBySerialNum();
        quantity--;
        if(quantity == 0) {
            AddtoCartBtn.setDisable(true);
        }
        if(quantity<=1){
            DecreaseQuantityBtn.setDisable(true);
        }
        if(quantity < product.getQuantity()){
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
        Product product = getProductBySerialNum();
        if(quantity > 0){
            DecreaseQuantityBtn.setDisable(false);
            AddtoCartBtn.setDisable(false);
        }
        if(quantity == product.getQuantity()){
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/InventoryDisplay.fxml"));
            Parent newRoot = loader.load();
            Main.getScene().setRoot(newRoot);
            Main.getStage().show();
        }catch (IOException ex) { System.err.println(ex); }
    }

    @FXML
    void showCheckoutDisplay(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/CheckoutDisplay.fxml"));
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
    void showProductBySerialNum(String serialNum) {
        this.serialNum = serialNum;
        product = getProductBySerialNum();
        ProductNameLabel.setText(product.getName());
        ProductDescriptionLabel.setText(product.getDescription());
        //ProductImage.setImage(); Product needs a getPic class, that at least returns image filename
        ProductScoreLabel.setText("Rating: " + product.getScore() + "/5.0");
        ProductPriceLabel.setText("$" + product.getPrice());
        totalLabel.setText("Total: $" + product.getPrice());
        if(product.getQuantity() == 0){
            OutOfStock(true);
        }
    }

    /**
     * @param name
     * loads product by its name
     * gets variables Name, Description, Score, and Price
     * Sets their respective label displays to reflect those variables
     */
    public void showProductByName(String name) {
        this.serialNum = product.getSerialNum();
        ProductNameLabel.setText(product.getName());
        ProductDescriptionLabel.setText(product.getDescription());
        //ProductImage.setImage(); Product needs a getPic class, that at least returns image filename
        ProductScoreLabel.setText("Rating: " + product.getScore() + "/5.0");
        ProductPriceLabel.setText("$" + product.getPrice());
        totalLabel.setText("Total: $" + product.getPrice());
        if(product.getQuantity() == 0){
            OutOfStock(true);
        }
    }

    @FXML
    void addToCart(){
        ShoppingCart shoppingCart = ((Customer)MainApplication.loggedInUser).getShoppingCart();
        shoppingCart.addToCart(product.deepCopy(), quantity);
        product.updateQuantity(product.getQuantity() - quantity);
        MainApplication.inventory.saveToFile();
        while(quantity > product.getQuantity()){
            DecreaseQuantity();
            IncreaseQuantityBtn.setDisable(true);
        }
        if(product.getQuantity() ==0){
            OutOfStock(true);
        }
    }

    @FXML
    void OutOfStock(boolean b){
        quantity = 0;
        quantityLabel.setText("0");
        DecreaseQuantityBtn.setDisable(true);
        IncreaseQuantityBtn.setDisable(true);
        AddtoCartBtn.setDisable(true);
        Label outofStockLabel = new Label("Out of Stock!");
        if(b) {
            ProductDisplayRight.getChildren().add(outofStockLabel);
        }
        else{
            ProductDisplayRight.getChildren().remove(outofStockLabel);
        }
    }

    public void initialize() {
    }
}

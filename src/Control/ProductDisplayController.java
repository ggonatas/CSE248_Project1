package Control;

import Model.Customer;
import Model.Product;
import Model.ShoppingCart;
import View.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ProductDisplayController {

    private String serialNum;
    private int quantity = 1;
    private Product product;

    @FXML private Label quantityLabel = new Label(Integer.toString(quantity));
    @FXML private Label ProductNameLabel, ProductScoreLabel, ProductPriceLabel,totalLabel;
    @FXML private ImageView ProductImage;
    @FXML private Button DecreaseQuantityBtn, IncreaseQuantityBtn, AddtoCartBtn;
    @FXML private VBox ProductDisplayRight;
    @FXML private TextArea ProductDescriptionTextArea;
    Product getProductBySerialNum(){
        Product p = MainApplication.inventory.getProduct(serialNum);
        return p;
    }

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
     * sets totalLabel to the current quantity of quantityLabel times the price of the product
     */
    @FXML
    void calculateTotal(){
        String quantity = quantityLabel.getText();
        String price = ProductPriceLabel.getText().substring(8);
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
        product = getProductBySerialNum();
        ProductNameLabel.setText(product.getName());
        ProductDescriptionTextArea.setText(product.getDescription());
        Image image = new Image(getClass().getResource(product.getPic()).toExternalForm());
        ProductImage.setImage(image);
        ProductImage.setPreserveRatio(true);
        ProductImage.setFitHeight(200);
        ProductScoreLabel.setText("Rating: " + product.getScore() + "/5.0");
        ProductPriceLabel.setText("Price: $" + product.getPrice());
        totalLabel.setText("Total: $" + product.getPrice());
        if(product.getQuantity() == 0){
            totalLabel.setText("Total: $0.00");
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
        this.serialNum = MainApplication.inventory.getProductByName(name).getSerialNum();
        product = getProductBySerialNum();
        ProductNameLabel.setText(product.getName());
        ProductDescriptionTextArea.setText(product.getDescription());
        Image image = new Image(getClass().getResource(product.getPic()).toExternalForm());
        ProductImage.setImage(image);
        ProductImage.setPreserveRatio(true);
        ProductImage.setFitHeight(200);
        ProductScoreLabel.setText("Rating: " + product.getScore() + "/5.0");
        ProductPriceLabel.setText("Price: $" + product.getPrice());
        totalLabel.setText("Total: $" + product.getPrice());
        if(product.getQuantity() == 0){
            totalLabel.setText("Total: $0.00");
            OutOfStock(true);
        }
    }

    @FXML
    void addToCart(){
        ShoppingCart shoppingCart = ((Customer)MainApplication.loggedInUser).getShoppingCart();
        shoppingCart.addToCart(product.deepCopy(), quantity);
        product.updateQuantity(product.getQuantity() - quantity);
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

package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Main;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author campd
 */
public class ProductDisplayController {

    private int quantity = 1;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label quantityLabel = new Label(Integer.toString(quantity));

    @FXML
    private Button DecreaseQuantityBtn, IncreaseQuantityBtn, HomeBtn;

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
    void showHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InventoryDisplay.fxml"));
        Main.getScene().setRoot(root);
        Main.getStage().show();
    }

    public void initialize() { }

}

package View;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import Model.Main;

public class InventoryDisplayController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button HomeBtn, Product1Btn,Product2Btn;

    @FXML
    void showProductDisplay() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProductDisplay.fxml"));
        Main.getScene().setRoot(root);
        Main.getStage().show();
    }


    @FXML
    void showInventoryDisplay() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InventoryDisplay.fxml"));
        Main.getScene().setRoot(root);
        Main.getStage().show();
    }

    public void initialize() { }
}

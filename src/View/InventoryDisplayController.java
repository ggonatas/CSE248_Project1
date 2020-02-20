package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import Model.Main;

public class InventoryDisplayController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button HomeBtn, Product1Btn;

    @FXML
    void Display() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProductDisplay.fxml"));
        Main.getScene().setRoot(root);
        Main.getStage().show();
    }

    @FXML
    void showHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InventoryDisplay.fxml"));
        Main.getScene().setRoot(root);
        Main.getStage().show();
    }

    public void initialize() { }
}

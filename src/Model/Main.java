package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Parent root;
    static Scene scene;
    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        setRoot(FXMLLoader.load(getClass().getResource("../View/InventoryDisplay.fxml")));
        setScene(new Scene(root,650, 400));
        setStage(primaryStage);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        Main.root = root;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        Main.scene = scene;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
}
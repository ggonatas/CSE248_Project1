package View;

import Model.PersonList;
import Model.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    static PersonList personList = new PersonList();
    static Person loggedInUser;
    static Parent root;
    static Scene scene;
    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        personList = PersonList.loadFromFile();
        root = FXMLLoader.load(getClass().getResource("login_page.fxml"));
        scene = new Scene(root,650, 400);
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
    }
}

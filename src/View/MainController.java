package View;

import Control.PersonList;
import Model.Customer;
import Model.PasswordUtils;
import Model.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUserID;
    @FXML
    private PasswordField pwfPassword;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnGuestLogin;
    @FXML
    private VBox rootPane;

    public void initialize(){}

    @FXML
    public void loginUser(ActionEvent event){
        String userID = txtUserID.getText();
        String password = pwfPassword.getText();
        boolean validUserID = Person.validUserID(userID);
        boolean validPassword = PasswordUtils.isValidPassword(password);
        if( validUserID && validPassword ){
            Customer customer = (Customer) MainApplication.personList.getPerson(userID);
            if(customer.verifyPassword(password)){
                MainApplication.loggedInUser = customer;
            }
        }
    }
    @FXML
    public void getRegisterUserPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register_page.fxml"));
        MainApplication.scene.setRoot(root);
        MainApplication.stage.show();

    }
}

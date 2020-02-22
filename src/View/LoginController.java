package View;

import Control.PersonList;
import Model.Customer;
import Model.PasswordUtils;
import Model.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginController {
    PersonList personList = MainApplication.personList;
    @FXML
    Parent root;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUserID;
    @FXML
    private PasswordField pwfPassword;

    @FXML
    public void loginUser(ActionEvent event){
        String userID = txtUserID.getText();
        String password = pwfPassword.getText();
        if(PersonList.isAdmin(userID, password)){
            MainApplication.loggedInUser = PersonList.admin;
        }
        else {
            boolean validUserID = Person.validUserID(userID);
            boolean validPassword = PasswordUtils.isValidPassword(password);
            if (validUserID && validPassword) {
                Customer customer = (Customer) MainApplication.personList.getPerson(userID);
                if (customer.verifyPassword(password)) {
                    MainApplication.loggedInUser = customer;
                }
            }
        }
    }
    @FXML
    public void getRegisterUserPage() throws IOException {
        MainApplication.root = FXMLLoader.load(getClass().getResource("register_page.fxml"));
        MainApplication.scene.setRoot(MainApplication.root);
    }
}

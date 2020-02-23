package Control;

import Model.Address;
import Model.Customer;
import Model.PasswordUtils;
import Model.Person;
import Model.PersonList;
import View.MainApplication;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    PersonList personList = MainApplication.personList;

    @FXML TextField txtRegisterUserID;
    @FXML Label lblVerifyUserID;
    @FXML PasswordField pwfRegisterPassword;
    @FXML Label lblVerifyPassword;
    @FXML TextField pwfRegisterConfirmPassword;
    @FXML Label lblVerifyConfirmPassword;
    @FXML TextField txtRegisterEmail;
    @FXML Label lblVerifyEmail;
    @FXML TextField txtRegisterFirstName;
    @FXML TextField txtRegisterMiddleName;
    @FXML TextField txtRegisterLastName;
    @FXML ChoiceBox<String> cbxPrefixChoice;
    @FXML ChoiceBox<String> cbxSuffixChoice;
    @FXML private Button btnRegisterFinalize;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        cbxPrefixChoice.getItems().clear();
        cbxSuffixChoice.getItems().clear();
        cbxPrefixChoice.getItems().addAll("", "Mr.", "Ms.", "Mrs.");
        cbxSuffixChoice.getItems().addAll("", "Jr.", "Sr.", "I", "II", "III");
        cbxPrefixChoice.getSelectionModel().select("");
        cbxSuffixChoice.getSelectionModel().select("");
    }
    @FXML public void verifyUserID(Event event){
        String tempUserID = txtRegisterUserID.getText();
        if(tempUserID.equals("")){
            lblVerifyUserID.setText("");
        }
        else if(Person.validUserID(tempUserID)){
            if(personList.personInList(tempUserID)){
                lblVerifyUserID.setText("UserID taken!");
            }
            else{
                lblVerifyUserID.setText("Looks good!");
            }
        }
        else{
            lblVerifyUserID.setText("Invalid user name.");
        }
    }
    @FXML public void verifyPassword(Event event){
        String tempPassword = pwfRegisterPassword.getText();
        if(PasswordUtils.isValidPassword(tempPassword)){
            lblVerifyPassword.setText("Password is valid.");
        }
        else{
            lblVerifyPassword.setText("Invalid password!");
        }
    }
    @FXML public void verifyConfirmPassword(Event event){
        String tempPassword = pwfRegisterPassword.getText();
        String tempConfirmPassword = pwfRegisterConfirmPassword.getText();
        if(tempConfirmPassword.equals(tempPassword)){
            lblVerifyConfirmPassword.setText("Passwords match!");
        }
        else{
            lblVerifyConfirmPassword.setText("Not matching.");
        }
    }
    @FXML public void verifyEmail(Event event){
        String tempEmail = txtRegisterEmail.getText();
        if(Address.isValidEmail(tempEmail)){
            lblVerifyEmail.setText("Email valid.");
        }
        else{
            lblVerifyEmail.setText("Invalid format.");
        }
    }
    @FXML public void goToAddressPage(ActionEvent event){
        String userID = txtRegisterUserID.getText();
        String password = pwfRegisterPassword.getText();
        String confirmPassword = pwfRegisterConfirmPassword.getText();
        String email = txtRegisterEmail.getText();
        String firstName = txtRegisterFirstName.getText();
        String lastName = txtRegisterLastName.getText();
        String middleName = txtRegisterMiddleName.getText();
        String suffix = cbxSuffixChoice.getValue();
        String prefix = cbxPrefixChoice.getValue();

        if((! personList.personInList(userID) ) && Person.validUserID(userID) &&
            PasswordUtils.isValidPassword(password) && password.equals(confirmPassword) &&
            Address.isValidEmail(email))
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/address_page.fxml"));
            try { MainApplication.root = loader.load();}
            catch (IOException e) { e.printStackTrace(); }
            MainApplication.scene.setRoot(MainApplication.root);
            Customer newCustomer = new Customer(userID, password, firstName, lastName, middleName, suffix, prefix, email);
            AddressPageController addressPageController = loader.getController();
            addressPageController.passCustomer(newCustomer);
            personList.addToPersonList(newCustomer);
            personList.saveToFile();
        }
    }
}

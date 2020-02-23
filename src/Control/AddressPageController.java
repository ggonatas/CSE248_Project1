package Control;

import Model.Customer;
import Model.PersonList;
import View.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AddressPageController implements Initializable {
    PersonList personList = MainApplication.personList;
    Customer customer;

    @FXML TextField txtRegisterStreet,txtRegisterCity,txtRegisterState, txtRegisterZip;
    @FXML GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
    }

    public void passCustomer(Customer customer){
        this.customer = customer;
    }

    @FXML public void finalizeRegistration(){
        String street = txtRegisterStreet.getText();
        String city = txtRegisterCity.getText();
        String state = txtRegisterState.getText();
        String zip = txtRegisterZip.getText();
        customer.setAddress(street,city, state, zip);
        personList.updatePerson(customer);
        personList.saveToFile();
        LoginController.logIn(customer);
    }

}

package Control;

import Model.*;
import View.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddressPageController implements Initializable {
    PersonList personList = MainApplication.personList;
    Customer customer;

    Label resultLabel;
    Scene resultScene;
    Stage resultStage;

    @FXML TextField txtCheckoutStreet, txtCheckoutCity, txtCheckoutState, txtCheckoutZip, txtEmail, txtName, txtCardNum, txtExpDate;
    @FXML PasswordField pwfCVV;
    @FXML GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        customer = (Customer) MainApplication.loggedInUser;

        Address userAddress = customer.getAddressObj();
        if(userAddress != null && ! userAddress.addressIsBlank()){
            txtCheckoutStreet.setText(userAddress.getStreet());
            txtCheckoutCity.setText(userAddress.getCity());
            txtCheckoutState.setText(userAddress.getState());
            txtCheckoutZip.setText(userAddress.getZipCode());
            txtEmail.setText(customer.getEmail());
            txtName.setText(customer.getFullName());
            if(customer.getCreditCard() != null) {
                txtCardNum.setText(customer.getCreditCard().getCardNum());
                txtExpDate.setText("" + customer.getCreditCard().getExpDate());
                pwfCVV.setText("" + customer.getCreditCard().getCvv());
            }
        }
        resultLabel = new Label();
        resultLabel.setPadding(new Insets(40));
        resultScene = new Scene(resultLabel);
        resultStage = new Stage();
        resultStage.setScene(resultScene);
    }

    @FXML public void finalizeCheckout(){
        String street = txtCheckoutStreet.getText();
        String city = txtCheckoutCity.getText();
        String state = txtCheckoutState.getText();
        String zip = txtCheckoutZip.getText();
        if(street.equals("") || city.equals("") || state.equals("") || zip.equals("")){
            resultLabel.setText("Invalid address entered.");
            resultStage.show();
            return;
        }
        String email, name, cardNum;
        int expDate, cvv;
        try {
            email = txtEmail.getText();
            name = txtName.getText();
            cardNum = txtCardNum.getText();
            expDate = Integer.parseInt(txtExpDate.getText());
            cvv = Integer.parseInt(pwfCVV.getText());
        }
        catch (NumberFormatException nfe){
            resultLabel.setText("Invalid credit card info!");
            resultStage.show();
            return;
        }
        if( (cardNum.length() < 12 || cardNum.length() > 16) && (expDate < 0 || expDate > 9999) && (cvv < 0 || cvv > 999)){
            resultLabel.setText("Invalid credit card info!");
            resultStage.show();
            return;
        }
        Invoice invoice;

        if(customer.getUserType().equals(UserType.CUSTOMER)) {
            customer.setAddress(street, city, state, zip);
            customer.addCreditCard(cardNum, expDate, cvv);
            personList.saveToFile();
            invoice = new Invoice(customer);
        }
        else{
            invoice = new Invoice(customer, name, street, city, state, zip, email);
        }

        resultLabel.setText("Order successfully placed! Invoice sent to " + customer.getEmail() + ".");
        resultStage.show();

        try {
            Desktop.getDesktop().edit(new File(invoice.getInvoiceNum() + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainApplication.masterInventory = MainApplication.inventory;
        MainApplication.masterInventory.saveToFile();

        customer.getShoppingCart().getInventory().clear();
        try { MainApplication.root = FXMLLoader.load(getClass().getResource("../View/InventoryDisplay.fxml")); }
        catch (IOException e) { e.printStackTrace(); }
        MainApplication.scene.setRoot(MainApplication.root);
    }

}

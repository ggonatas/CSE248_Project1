package View;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    PersonList personList = MainApplication.personList;
    @FXML
    private TextField txtUserID;
    @FXML
    private PasswordField pwfPassword;

    Label loginSuccess = new Label();
    Scene loginSuccessScene = new Scene(loginSuccess);
    Stage loginSuccessStage = new Stage();

    @FXML
    public void loginUser(ActionEvent event){
        loginSuccess.setPadding(new Insets(50));
        loginSuccessStage.setScene(loginSuccessScene);

        String userID = txtUserID.getText();
        String password = pwfPassword.getText();
        if(PersonList.isAdmin(userID, password)){
            loginSuccess.setText("Admin logged in.");
            logIn(PersonList.ADMIN);
        }
        else {
            boolean validUserID = Person.validUserID(userID);
            boolean validPassword = PasswordUtils.isValidPassword(password);
            if (validUserID && validPassword) {
                if(personList.personInList(userID)) {
                    Customer customer = (Customer) MainApplication.personList.getPerson(userID);
                    if (customer.verifyPassword(password)) {
                        loginSuccess.setText("Successfully logged in!");
                        logIn(customer);
                    }
                    else {
                        loginSuccess.setText("Incorrect password!");
                    }
                }
                else{
                    loginSuccess.setText("User not found!");
                }
            }
            else{
                loginSuccess.setText("Invalid UserID or Password.");
            }
        }
        loginSuccessStage.show();
    }

    static void logIn(Person person){
        MainApplication.loggedInUser = person;
        try{
            new Main().start(MainApplication.stage);
        }catch (IOException ex) { System.err.println(ex); } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void getRegisterUserPage() throws IOException {
        MainApplication.root = FXMLLoader.load(getClass().getResource("register_page.fxml"));
        MainApplication.scene.setRoot(MainApplication.root);
    }
    @FXML
    public void continueAsGuest() {
        MainApplication.loggedInUser = PersonList.GUEST;
    }
}

package application.controller;

import application.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author myname
 */
public class SignInFormController {
    @FXML
    public Button submitButton;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField nameField;

    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        System.out.println("Submit button pressed");
        UserService userService = UserService.getUserServiceSingleton();
        boolean isValidData = userService.userDataValidation(nameField.getText(), passwordField.getText());
        if(isValidData) {
            System.out.println("Sign In Successful");
            submitButton.getScene().getWindow().hide();
        } else {
            System.out.println("Wrong Sign In");
        }

    }
}

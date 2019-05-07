package application.controller;

import application.service.AlertHelper;
import application.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        boolean isValidUser = userService.userDataValidation(nameField.getText(), passwordField.getText());
        if(isValidUser) {
            System.out.println("Sign In Successful");
            userService.signInUser(nameField.getText());
            submitButton.getScene().getWindow().hide();
        } else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, submitButton.getScene().getWindow(),
                    "Sign In Error!", "Wrong Sign In");
        }

    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public TextField getNameField() {
        return nameField;
    }

    public void setSubmitButton(Button submitButton) {
        this.submitButton = submitButton;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }
}

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
public class RegistrationFormController {

    @FXML
    public TextField nameField;
    @FXML
    public TextField emailField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button submitButton;


    @FXML
    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        System.out.println("Submit Button Pressed");
        UserService userService = UserService.getUserServiceSingleton();
        boolean isValidData = userService.userDataValidation(nameField.getText(), emailField.getText(), passwordField.getText());
        if(!isValidData){
            AlertHelper.showAlert(Alert.AlertType.ERROR, submitButton.getScene().getWindow(),
                    "Registration Error!", "invalid Registration Data");
            return;
        }
        else {
            userService.addNewUser(nameField.getText(), emailField.getText(), passwordField.getText());
        }

        System.out.println("registration Successful");
        submitButton.getScene().getWindow().hide();
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public void setEmailField(TextField emailField) {
        this.emailField = emailField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public void setSubmitButton(Button submitButton) {
        this.submitButton = submitButton;
    }
}


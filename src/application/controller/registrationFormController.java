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
public class registrationFormController {

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
        boolean isValidData = UserService.userDataValidation(nameField.getText(), emailField.getText(), passwordField.getText());
        if(!isValidData) return;

        System.out.println("registration Successful");
        submitButton.getScene().getWindow().hide();
    }
}


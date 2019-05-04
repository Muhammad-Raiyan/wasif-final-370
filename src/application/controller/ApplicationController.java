package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class ApplicationController {

    @FXML
    public Button LSB_signOutButton;
    @FXML
    public Button LSB_registerButton;
    @FXML
    public Button LSB_signInButton;

    private static  boolean isUserSignedIn;
    public static  String REGISTRATION_FORM_PATH = "/resource/view/RegistrationForm.fxml";
    public static  String SIGNIN_FORM_PATH = "/resource/view/SignInForm.fxml";
    public static  String SIGNIN_BUTTON_TEXT;

    public ApplicationController() {
        System.out.println("Constructor Called");
        isUserSignedIn = false;
    }

    @FXML
    public void initialize(){
        System.out.println("Initialized called");
        SIGNIN_BUTTON_TEXT = LSB_signInButton.getText();
        LSB_signOutButton.setDisable(true);
    }

    public void handleSignInButtonAction(ActionEvent actionEvent) {
        System.out.println("Sign in Button Pressed");
        if(!isUserSignedIn){
            popupNewStage(SIGNIN_FORM_PATH);

        } else {
            System.out.println("Profile Button Pressed");
        }
    }

    public void handleRegisterButtonAction(ActionEvent actionEvent) {
        System.out.println("Register button pressed");
        popupNewStage(REGISTRATION_FORM_PATH);
    }

    public void handleSignOutButtonAction(ActionEvent actionEvent) {
        System.out.println("Sign Out Button Pressed");
        if(isUserSignedIn){
            isUserSignedIn = false;
            LSB_signOutButton.setDisable(true);
            LSB_signInButton.setText("Sign In");
            System.out.println("Signing out user");
        }
    }

    /*
        Additional Controller Code
     */
    private void popupNewStage(String registrationFormPath) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(registrationFormPath));
        try {
            Scene registrationScene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(registrationScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setLSB_signInButtonText(String text){
        LSB_signInButton.setText(text);
    }

    public void resetSignInButton(){
        LSB_signInButton.setText(SIGNIN_BUTTON_TEXT);
    }
}

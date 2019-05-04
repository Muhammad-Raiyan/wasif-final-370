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
    public Button LSB_signInInButton;

    private static  boolean isUserSignedIn;

    public ApplicationController() {
        System.out.println("Constructor Called");
        isUserSignedIn = false;
    }

    @FXML
    public void initialize(){
        System.out.println("Initialized called");
        LSB_signOutButton.setDisable(true);
    }

    public void handleSignInButtonAction(ActionEvent actionEvent) {
        System.out.println("Sign in Button Pressed");
        if(!isUserSignedIn){
            isUserSignedIn = true;
            LSB_signOutButton.setDisable(false);
            LSB_signInInButton.setText("User");
            System.out.println("Signed in user");
        } else {
            System.out.println("Profile Button Pressed");
        }
    }

    public void handleRegisterButtonAction(ActionEvent actionEvent) {
        System.out.println("Register button pressed");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resource/view/registrationForm.fxml"));

        try {
            Scene registrationScene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(registrationScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleSignOutButtonAction(ActionEvent actionEvent) {
        System.out.println("Sign Out Button Pressed");
        if(isUserSignedIn){
            isUserSignedIn = false;
            LSB_signOutButton.setDisable(true);
            LSB_signInInButton.setText("Sign In");
            System.out.println("Signing out user");
        }
    }
}

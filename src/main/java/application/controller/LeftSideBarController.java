package application.controller;

import application.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author myname
 */
public class LeftSideBarController extends ApplicationController{
    @FXML
    public Button LSB_signOutButton;
    @FXML
    public Button LSB_registerButton;
    @FXML
    public Button LSB_signInButton;

    protected static final String REGISTRATION_FORM_PATH = "/view/RegistrationForm.fxml";
    protected static final String SIGN_IN_FORM_PATH = "/view/SignInForm.fxml";
    protected static  String SIGN_IN_BUTTON_TEXT;

    @FXML
    public void initialize(){
        System.out.println("Initialized called");
        SIGN_IN_BUTTON_TEXT = LSB_signInButton.getText();
        LSB_signOutButton.setDisable(true);
    }

    public void handleSignInButtonAction(ActionEvent actionEvent) {
        System.out.println("Sign in Button Pressed");
        UserService userService = UserService.getUserServiceSingleton();

        if(!userService.isUserSignedIn()){
            popupNewStage(SIGN_IN_FORM_PATH);
            // Second check if user closed without signing in
            if(userService.isUserSignedIn()){
                setLSB_signInButtonText(userService.getCurrentUserName());
                LSB_signOutButton.setDisable(false);
            }
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
        UserService userService = UserService.getUserServiceSingleton();
        if(userService.isUserSignedIn()){
            userService.signOutUser();
            LSB_signOutButton.setDisable(true);
            resetSignInButton();
            System.out.println("Signing out user");
        }
    }

    /*
        Additional Controller Code
     */


    public void setLSB_signInButtonText(String text){
        LSB_signInButton.setText(text);
    }

    public void resetSignInButton(){
        LSB_signInButton.setText(SIGN_IN_BUTTON_TEXT);
    }

    public Button getLSB_signOutButton() {
        return LSB_signOutButton;
    }

    public Button getLSB_registerButton() {
        return LSB_registerButton;
    }

    public Button getLSB_signInButton() {
        return LSB_signInButton;
    }

    public static String getRegistrationFormPath() {
        return REGISTRATION_FORM_PATH;
    }

    public static String getSignInFormPath() {
        return SIGN_IN_FORM_PATH;
    }

    public static String getSignInButtonText() {
        return SIGN_IN_BUTTON_TEXT;
    }

    public void setLSB_signOutButton(Button LSB_signOutButton) {
        this.LSB_signOutButton = LSB_signOutButton;
    }

    public void setLSB_registerButton(Button LSB_registerButton) {
        this.LSB_registerButton = LSB_registerButton;
    }

    public void setLSB_signInButton(Button LSB_signInButton) {
        this.LSB_signInButton = LSB_signInButton;
    }

    public static void setSignInButtonText(String signInButtonText) {
        SIGN_IN_BUTTON_TEXT = signInButtonText;
    }
}

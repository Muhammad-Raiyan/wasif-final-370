package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.util.Random;

public class ApplicationController {

    public void generateRandom(ActionEvent actionEvent) {
        Random rand = new Random();
        int myRand = rand.nextInt(50) + 1;
        System.out.println(myRand);
    }

    public void handleSignInButtonAction(ActionEvent actionEvent) {
        System.out.println("Sign in Button Pressed");
    }

    public void handleRegisterButtonAction(ActionEvent actionEvent) {
        System.out.println("Register button pressed");
    }

    public void handleSignOutButtonAction(ActionEvent actionEvent) {
        System.out.println("Sign Out Button Pressed");
    }
}

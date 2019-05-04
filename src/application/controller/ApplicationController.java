package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.util.Random;

public class ApplicationController {

    @FXML
    public Label labelMsg;

    public void generateRandom(ActionEvent actionEvent) {
        Random rand = new Random();
        int myRand = rand.nextInt(50) + 1;
        labelMsg.setText(String.valueOf(myRand));
        System.out.println(myRand);
    }
}

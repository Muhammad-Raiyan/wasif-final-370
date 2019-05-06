package application.controller;

import application.service.UrlServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ApplicationController {

    private static String singleInputFormPath = "/view/SingleInputForm.fxml";
    public Button searchForItemButton;
    public TextField searchItem;
    @FXML
    public Label searchLabel;

    public ApplicationController() {
        System.out.println("Constructor Called");
    }

    public void handleSearchForItem(ActionEvent actionEvent) {
        popupNewStage(singleInputFormPath);
    }

    public void handleSubmitButtonForSearchItemAction(ActionEvent actionEvent) {
        System.out.println("Item searched: " + searchItem.getText());
        searchLabel.setText("Please wait.............");
        UrlServices urlServices = UrlServices.getInstance();
        List<String> items = urlServices.searchForItem(searchItem.getText());
        searchForItemButton.getScene().getWindow().hide();
        items.forEach(System.out::println);
    }

    protected void popupNewStage(String registrationFormPath) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(registrationFormPath));
        try {
            Scene registrationScene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(registrationScene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

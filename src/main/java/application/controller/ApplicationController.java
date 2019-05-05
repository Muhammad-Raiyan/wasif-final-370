package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationController {

    private static String singleInputFormPath = "/view/SingleInputForm.fxml";
    public Button searchForItemButton;
    public TextField searchItem;

    public ApplicationController() {
        System.out.println("Constructor Called");
    }

    public void handleSearchForItem(ActionEvent actionEvent) {
        popupNewStage(singleInputFormPath);
    }

    public void handleSubmitButtonForSearchItemAction(ActionEvent actionEvent) {
        System.out.println("Item searched: " + searchItem.getText());
        searchForItemButton.getScene().getWindow().hide();
    }

    public void popupNewStage(String registrationFormPath) {
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

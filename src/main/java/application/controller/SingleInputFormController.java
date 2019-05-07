package application.controller;

import application.service.AlertHelper;
import application.service.UrlServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author myname
 */
public class SingleInputFormController {
    @FXML
    public Button searchForItemButton;
    @FXML
    public TextField searchItem;
    @FXML
    public Label searchLabel;

    public void handleSubmitButtonForSearchItemAction(ActionEvent actionEvent) {
        searchLabel.setText("Please Wait");
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, searchForItemButton.getScene().getWindow(),
                "Loading Data", "It takes some time to load the data", true);
        UrlServices urlServices = UrlServices.getInstance();
        urlServices.searchForItem(searchItem.getText());
        searchForItemButton.getScene().getWindow().hide();
    }

    public Button getSearchForItemButton() {
        return searchForItemButton;
    }

    public TextField getSearchItem() {
        return searchItem;
    }

    public Label getSearchLabel() {
        return searchLabel;
    }

    public void setSearchForItemButton(Button searchForItemButton) {
        this.searchForItemButton = searchForItemButton;
    }

    public void setSearchItem(TextField searchItem) {
        this.searchItem = searchItem;
    }

    public void setSearchLabel(Label searchLabel) {
        this.searchLabel = searchLabel;
    }
}

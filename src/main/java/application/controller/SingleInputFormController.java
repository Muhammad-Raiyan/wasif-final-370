package application.controller;

import application.Model.Product;
import application.service.AlertHelper;
import application.service.UrlServices;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

/**
 * @author myname
 */
public class SingleInputFormController {
    public Button searchForItemButton;
    public TextField searchItem;
    public Label searchLabel;

    public void handleSubmitButtonForSearchItemAction(ActionEvent actionEvent) {
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, searchForItemButton.getScene().getWindow(),
                "Loading Data", "Please Wait, it takes some time to load the data", false);
        UrlServices urlServices = UrlServices.getInstance();
        urlServices.searchForItem(searchItem.getText());
        searchForItemButton.getScene().getWindow().hide();
    }
}

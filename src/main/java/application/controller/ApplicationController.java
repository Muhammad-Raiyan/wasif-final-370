package application.controller;

import application.Model.Product;
import application.service.UrlServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ApplicationController {

    private static String singleInputFormPath = "/view/SingleInputForm.fxml";

    public AnchorPane appCenterPane;
    public TableView<Product> productTable;
    @FXML
    public TableColumn<Product, String> nameColumn;
    @FXML
    public TableColumn<Product, String> priceColumn;
    @FXML
    public TableColumn<Product, String> imageColumn;
    @FXML
    public TableColumn<Product, String> urlColumn;

    private ObservableList<Product> productObservableList;

    public ApplicationController() {
        System.out.println("Constructor Called");
    }

    @FXML
    public void initialize(){
        productObservableList = FXCollections.observableArrayList(
                new Product("Empty", "Empty", "Empty", "Empty")
        );

    }
    public void handleSearchForItem(ActionEvent actionEvent) {
        popupNewStage(singleInputFormPath);
        handleSubmitButtonForSearchItemAction();
    }

    public void handleSubmitButtonForSearchItemAction() {
        UrlServices urlServices = UrlServices.getInstance();
        List<Product> items = urlServices.getCurrentProduct();
        productObservableList = FXCollections.observableArrayList(items);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        productTable.setItems(productObservableList);
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

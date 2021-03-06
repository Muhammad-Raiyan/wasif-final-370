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

    @FXML
    public AnchorPane appCenterPane;
    @FXML
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

    public static String getSingleInputFormPath() {
        return singleInputFormPath;
    }

    public AnchorPane getAppCenterPane() {
        return appCenterPane;
    }

    public TableView<Product> getProductTable() {
        return productTable;
    }

    public TableColumn<Product, String> getNameColumn() {
        return nameColumn;
    }

    public TableColumn<Product, String> getPriceColumn() {
        return priceColumn;
    }

    public TableColumn<Product, String> getImageColumn() {
        return imageColumn;
    }

    public TableColumn<Product, String> getUrlColumn() {
        return urlColumn;
    }

    public ObservableList<Product> getProductObservableList() {
        return productObservableList;
    }

    public static void setSingleInputFormPath(String singleInputFormPath) {
        ApplicationController.singleInputFormPath = singleInputFormPath;
    }

    public void setAppCenterPane(AnchorPane appCenterPane) {
        this.appCenterPane = appCenterPane;
    }

    public void setProductTable(TableView<Product> productTable) {
        this.productTable = productTable;
    }

    public void setNameColumn(TableColumn<Product, String> nameColumn) {
        this.nameColumn = nameColumn;
    }

    public void setPriceColumn(TableColumn<Product, String> priceColumn) {
        this.priceColumn = priceColumn;
    }

    public void setImageColumn(TableColumn<Product, String> imageColumn) {
        this.imageColumn = imageColumn;
    }

    public void setUrlColumn(TableColumn<Product, String> urlColumn) {
        this.urlColumn = urlColumn;
    }

    public void setProductObservableList(ObservableList<Product> productObservableList) {
        this.productObservableList = productObservableList;
    }
}

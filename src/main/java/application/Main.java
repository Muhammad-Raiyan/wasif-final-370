package application;

import application.service.AlertHelper;
import application.service.CommandParser;
import application.service.UrlServices;
import application.service.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    public static final String APPLICATION_FXML_PATH = "/view/Application.fxml";
    public static final String APPLICATION_CSS_PATH = "/css/application.css";
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(APPLICATION_FXML_PATH));
        primaryStage.setTitle("Web Scrapper - 370");
        Scene primaryScene = new Scene(root, 1000, 700);
        primaryScene.getStylesheets().add(getClass().getResource(APPLICATION_CSS_PATH).toExternalForm());
        primaryStage.setScene(primaryScene);

        Parameters params = getParameters();
        List<String> list = params.getRaw();
        CommandParser commandParser = new CommandParser();
        commandParser.parse(list);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Exiting Application");
            if(UserService.getUserServiceSingleton().saveUsersData()
                    && UrlServices.getInstance().saveSearchHistoryToFile()){
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, primaryScene.getWindow(), "User data saved", "Successfully saved users data");
            } else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, primaryScene.getWindow(), "Error Saving User Data", "Error while saving users data");
            }


        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}

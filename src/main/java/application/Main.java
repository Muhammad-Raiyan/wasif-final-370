package application;

import application.service.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Exiting Application");
            UserService.getUserServiceSingleton().saveToFile();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}

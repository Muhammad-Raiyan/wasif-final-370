package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/resource/view/Application.fxml"));
        primaryStage.setTitle("Web Scrapper - 370");
        Scene primaryScene = new Scene(root, 1000, 700);
        primaryScene.getStylesheets().add(getClass().getResource("/resource/css/application.css").toExternalForm());
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

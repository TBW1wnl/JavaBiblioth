package fr.cci.desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("Program started");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Home.fxml"));
        Parent rootBox = fxmlLoader.load();


        Scene scene = new Scene(rootBox);
        URL cssURL = getClass().getClassLoader().getResource("stylesheets/style.css");
        scene.getStylesheets().add(cssURL.toString());
        primaryStage.setScene(scene);


        primaryStage.setTitle("Program");
        primaryStage.setWidth(800);
        primaryStage.setHeight(560);
        primaryStage.show();
        primaryStage.centerOnScreen();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Program stopped");
    }
}

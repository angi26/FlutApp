package de.idvk.flutapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author annika
 */
public class FlutApp extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        TabPane tabPane = new TabPane();

        Tab tab1 = new Tab("Flutspenden");

        tabPane.getTabs().add(tab1);
        AnchorPane root = new OberflaecheFlutApp().getAnchorPane();
        tab1.setContent(root);

        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox);

        stage.setScene(scene);
        stage.setTitle("Flutapp");
        stage.show();
        
    }
}

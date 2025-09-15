package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pepero.Pepero;

/**
 * A GUI for Pepero using FXML.
 */
public class Main extends Application {

    private String filePath = "data/pepero.txt";
    private Pepero pepero = new Pepero(filePath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPepero(pepero);  // inject the Pepero instance
            stage.show();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}

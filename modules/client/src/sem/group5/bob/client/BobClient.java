package sem.group5.bob.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sem.group5.bob.client.DiscoveryListener;

/**
 * Created by Rapha on 30/03/16.
 */
public class BobClient extends Application{
    public static ConnectionManager cm;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/client.fxml"));
        Scene scene = new Scene(root, 0, 0);
        primaryStage.setTitle("Bob the SmartCar");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
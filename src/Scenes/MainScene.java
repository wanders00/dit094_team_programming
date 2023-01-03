package Scenes;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScene extends SceneController {
    public void show(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/mainMenuScene.fxml"));
        stage.setTitle("Snake Game");
        stage.setScene(new Scene(root));
        stage.setTitle("Snake Game - Main Menu");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}

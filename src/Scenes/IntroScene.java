package Scenes;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IntroScene extends SceneController{

    @FXML
    private TextField userNameField;

    public void show(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/introScene.fxml"));
        stage.setTitle("Snake Game");
        stage.setScene(new Scene(root));
        stage.show();
    }

    
}


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
    public void setUsername(){
        // String playerName = userNameField.getText();

    }
    
    public void show(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/introScene.fxml"));
        //stage.setScene(new Scene(root));
        stage.setTitle("Snake Game - Intro Page");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}


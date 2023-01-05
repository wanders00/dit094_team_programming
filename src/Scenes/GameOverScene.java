package Scenes;

import java.io.IOException;

import GameLogic.FileHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameOverScene extends SceneController {

    public void show(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/gameOverScene.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private Label scoreLabel;

    public void initialize() {
        scoreLabel.setText("Score: " + FileHandler.readCurrentScore() + System.lineSeparator() + "Layout: " + FileHandler.readGameLayout()
                + System.lineSeparator() + "Difficulty: " + FileHandler.readGameDifficulty());
    }


}

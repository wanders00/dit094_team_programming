package Scenes;

import java.io.IOException;

import GameLogic.FileHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class GameOverScene extends SceneController {

    public void show(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/gameOverScene.fxml"));
        Scene gameOverScene = new Scene(root);
        stage.setScene(gameOverScene);
        stage.show();

        gameOverScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.R) {
                new GameScene().show(stage);
            }
        });
        // To more easily restart a new game if you lose.
    }

    @FXML
    private Label scoreLabel;

    public void initialize() {
        scoreLabel.setText("Score: " + FileHandler.readCurrentScore() + System.lineSeparator() + "Layout: "
                + FileHandler.readGameLayout().toString() + System.lineSeparator() + "Difficulty: "
                + FileHandler.readGameDifficulty().toString());
    }

}

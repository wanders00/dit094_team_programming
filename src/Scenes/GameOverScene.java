package Scenes;
import java.io.IOException;

import GameLogic.Difficulty;
import GameLogic.Game.Layout;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameOverScene extends SceneController {

    public static double currentScore;
    public static Layout currentLayout;
    public static Difficulty currentDifficulty;
    public void show(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/gameOverScene.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //printScore();
    }

    @FXML
    private Label scoreLabel;

    public void initialize() {
        scoreLabel.setText("Score: " + currentScore + System.lineSeparator() + "Layout: " + currentLayout
                + System.lineSeparator() + " Difficulty: " + currentDifficulty);
    }
}

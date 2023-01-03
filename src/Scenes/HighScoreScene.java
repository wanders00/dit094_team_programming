package Scenes;

import java.io.IOException;

import GameLogic.FileHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HighScoreScene extends SceneController {

    public static final int HIGH_SCORES_AMOUNT = 10;

    public void show(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/highScoreScene.fxml"));
        stage.setTitle("Snake Game - High Scores");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Label highScoresLabel;

    public void initialize() {
        FileHandler fileHandler = new FileHandler();
        String[] highScores = fileHandler.readHighScores();
        String labelText = "";
        for (int i = 0; i < highScores.length; i++) {
            labelText = labelText + (i + 1) + ". " + highScores[i] + System.lineSeparator();
        }
        highScoresLabel.setText(labelText);
    }
}

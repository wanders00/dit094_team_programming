package Scenes;

import java.io.IOException;
import GameLogic.Difficulty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class SettingsScene extends SceneController {
    public void show(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/SettingsScene.fxml"));
        stage.setTitle("Snake Game - Settings");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEasyDifficulty() {
        Audio.play("audio/ButtonEasy.wav");
        GameScene.selectedDifficulty = Difficulty.EASY; // be local file later
    }

    public void switchToNormalDifficulty() {
        Audio.play("audio/ButtonClick.wav");
        GameScene.selectedDifficulty = Difficulty.NORMAL; // be local file later
    }

    public void switchToHardDifficulty() {
        Audio.play("audio/ButtonHard.wav");
        GameScene.selectedDifficulty = Difficulty.HARD; // be local file later
    }

    @FXML
    private Button easyDifficulty;

    @FXML
    private Button normalDifficulty;

    @FXML
    private Button hardDifficulty;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Slider fxSlider;
}

package Scenes;

import java.io.IOException;
import GameLogic.Difficulty;
import GameLogic.FileHandler;
import GameLogic.Keybind;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsScene extends SceneController {

    public static String selectedButtonColor = "#ffcccc";
    // Hexdecimal colors

    public void show(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/SettingsScene.fxml"));
        stage.setTitle("Snake Game - Settings");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchToEasyDifficulty(ActionEvent event) throws IOException {
        FileHandler.updateGameDifficulty(Difficulty.EASY);
        switchToSettingsScene(event);
    }

    public void switchToNormalDifficulty(ActionEvent event) throws IOException {
        FileHandler.updateGameDifficulty(Difficulty.NORMAL);
        switchToSettingsScene(event);
    }

    public void switchToHardDifficulty(ActionEvent event) throws IOException {
        FileHandler.updateGameDifficulty(Difficulty.HARD);
        switchToSettingsScene(event);
    }

    public void changeUpKeyBind() {
        // Planning to do the change keybind logic here
    }

    public void switchMusicOff(ActionEvent event) throws IOException {
        Audio.switchMusicVolume();
    }

    public void switchFxOff(ActionEvent event) throws IOException {
        Audio.switchSoundVolume();
    }

    public void initialize() {
        switch (FileHandler.readGameDifficulty()) {
            case EASY:
                easyDifficulty.setStyle("-fx-background-color: " + selectedButtonColor + "; ");
                break;
            case NORMAL:
                normalDifficulty.setStyle("-fx-background-color: " + selectedButtonColor + "; ");
                break;
            case HARD:
                hardDifficulty.setStyle("-fx-background-color: " + selectedButtonColor + "; ");
                break;
            default:
                break;
        }
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

    @FXML
    private CheckBox muteMusic;
}

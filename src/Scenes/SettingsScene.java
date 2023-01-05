package Scenes;

import java.io.IOException;
import GameLogic.Difficulty;
import GameLogic.FileHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class SettingsScene extends SceneController {

    double test = 0;
    double test2 = 0;

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

        test = (int) musicSlider.getValue();

        musicSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                test = musicSlider.getValue() * 0.01;
                Audio.setMusicVolume(test);
                System.out.println(test);
            }

        });

        test2 = (int) fxSlider.getValue();

        fxSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                test2 = fxSlider.getValue() * 0.01;
                Audio.setSoundVolume(test2);
                System.out.println(test2);
            }

        });
    }

    @FXML
    private Button easyDifficulty;

    @FXML
    private Button normalDifficulty;

    @FXML
    private Button hardDifficulty;

    @FXML
    private Slider musicSlider;

    @FXML
    private Slider fxSlider;

    @FXML
    private CheckBox muteMusic;
}

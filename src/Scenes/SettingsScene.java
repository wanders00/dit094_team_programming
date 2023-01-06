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

    public void switchFXOnOff(ActionEvent event) throws IOException {
        FileHandler.switchFXOnOff();
    }

    public void switchMusicOnOff(ActionEvent event) throws IOException {
        FileHandler.switchMusicOnOff();
        Audio.switchMusicOnOff();
    }

    public void initialize() {
        // Initializes the buttons to be correct state dependent on the settings.
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

        fxOnOff.setSelected(FileHandler.readFXOnOff());
        musicOnOff.setSelected(FileHandler.readMusicOnOff());

        musicSlider.setValue(FileHandler.readMusicVolume());
        musicSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                FileHandler.updateMusicVolume(musicSlider.getValue());
                Audio.updateMusicVolume();
                System.out.println(FileHandler.readMusicVolume());
            }

        });

        fxSlider.setValue(FileHandler.readFXVolume());
        fxSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                FileHandler.updateFXVolume(fxSlider.getValue());
                System.out.println(FileHandler.readFXVolume());
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
    private CheckBox fxOnOff;

    @FXML
    private CheckBox musicOnOff;
}

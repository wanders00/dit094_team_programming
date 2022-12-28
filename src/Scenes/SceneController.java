package Scenes;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class SceneController {

    public void switchToSettingsScene(ActionEvent event) throws IOException {
        SettingsScene settingsScene = new SettingsScene();
        settingsScene.show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        MainScene mainScene = new MainScene();
        mainScene.show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchGameScene(ActionEvent event) throws IOException {
        GameScene gameScene = new GameScene();
        gameScene.show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToGameOverScene(ActionEvent event) throws IOException {
        GameOverScene gameOverScene = new GameOverScene();
        gameOverScene.show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToSelectMapScene(ActionEvent event) throws IOException {
        MapSelectScene mapSelectScene = new MapSelectScene();
        mapSelectScene.show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void exitGameButton(ActionEvent event) throws IOException {
        Platform.exit();
    }

    @FXML
    private Slider volumeSlider;

    @FXML
    private Slider fxSlider;

    @FXML
    private Button easyDifficulty;

    @FXML
    private Button normalDifficulty;

    @FXML
    private Button hardDifficulty;

    @FXML
    Label scoreLabel;

}

package Scenes;

import java.io.IOException;
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
        new SettingsScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        new MainScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchGameScene(ActionEvent event) throws IOException {
        new GameScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToGameOverScene(ActionEvent event) throws IOException {
        new GameOverScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToSelectMapScene(ActionEvent event) throws IOException {
        new MapSelectScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
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

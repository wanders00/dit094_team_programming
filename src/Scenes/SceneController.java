package Scenes;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class SceneController {

    public void switchToSettingsScene(ActionEvent event) throws IOException {
        Audio.ButtonPress();
        new SettingsScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        Audio.ButtonPress();
        new MainScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchGameScene(ActionEvent event) throws IOException {
        Audio.ButtonPress();
        new GameScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToGameOverScene(ActionEvent event) throws IOException {
        Audio.ButtonPress();
        new GameOverScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToSelectMapScene(ActionEvent event) throws IOException {
        Audio.ButtonPress();
        new MapSelectScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToHighScoreScene(ActionEvent event) throws IOException {
        Audio.ButtonPress();
        new HighScoreScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void exitGameButton() {
        Audio.ButtonPress();
        Platform.exit();
    }

}

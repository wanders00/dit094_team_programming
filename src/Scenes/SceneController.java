package Scenes;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class SceneController {

    public void switchToSettingsScene(ActionEvent event) throws IOException {
        Audio.play("audio/button.mp3");
        new SettingsScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToIntroScene(ActionEvent event) throws IOException {
        new IntroScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        Audio.play("audio/button.mp3");
        new MainScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchGameScene(ActionEvent event) throws IOException {
        Audio.play("audio/button.mp3");
        new GameScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToGameOverScene(ActionEvent event) throws IOException {
        Audio.play("audio/button.mp3");
        new GameOverScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToSelectMapScene(ActionEvent event) throws IOException {
        Audio.play("audio/button.mp3");
        new MapSelectScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void switchToHigScoreScene(ActionEvent event) throws IOException {
        Audio.play("audio/button.mp3");
        new HighScoreScene().show((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void exitGameButton() {
        Audio.play("audio/button.mp3");
        Platform.exit();
    }

}

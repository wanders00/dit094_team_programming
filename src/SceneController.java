import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class SceneController extends Main {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Game game;

    public void switchToSettingsScene(ActionEvent event) throws IOException {
        updateTitle("Snake Game - Settings");
        root = FXMLLoader.load(getClass().getResource("fxml/SettingsScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        updateTitle("Snake Game");
        root = FXMLLoader.load(getClass().getResource("fxml/mainMenuScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchGameScene(ActionEvent event) throws IOException {
        showGameScene((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void exitGameButton(ActionEvent event) throws IOException {
        Platform.exit();
    }

    public void switchToGameOverScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/gameOverScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSelectMapScene(ActionEvent event) throws IOException {
        updateTitle("Snake Game - Map Selection");
        root = FXMLLoader.load(getClass().getResource("fxml/SelectMapScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEasyDifficulty() {
        Main.selectedDifficulty = Difficulty.EASY; // be local file later
    }

    public void switchToNormalDifficulty() {
        Main.selectedDifficulty = Difficulty.NORMAL; // be local file later
    }

    public void switchToHardDifficulty() {
        Main.selectedDifficulty = Difficulty.HARD; // be local file later
    }

    public void switchToNormalLayout() {
        Game.setCurrentLayout(Game.Layout.ORDINARY); // be local file later
    }

    public void switchToPlusLayout() {
        Game.setCurrentLayout(Game.Layout.PLUS); // be local file later
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

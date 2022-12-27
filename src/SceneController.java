import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SceneController extends Main {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Game game;


    public void switchToSettingsScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SettingsScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainMenuScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchGameScene(ActionEvent event) throws IOException {
        showGameScene((Stage)((Node)event.getSource()).getScene().getWindow());
        //stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //scene = new Scene(root);
        //stage.setScene(scene);
        //stage.show();
    }

    public void exitGameButton(ActionEvent event) throws IOException {
        Platform.exit();
    }

    public void switchToGameOverScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("gameOverScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSelectMapScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SelectMapScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public void switchToEasyDifficulty(){
        Main.selectedDifficulty = Difficulty.EASY;
    }

    public void switchToNormalDifficulty(){
        Main.selectedDifficulty = Difficulty.NORMAL;
    }

    public void switchToHardDifficulty(){
        Main.selectedDifficulty = Difficulty.HARD;
    }

    public void switchToNormalLayout(){Game.setCurrentLayout(Game.Layout.NORMAL);}

    public void switchToPlusLayout(){Game.setCurrentLayout(Game.Layout.PLUS);}


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

@FXML Label scoreLabel;

}

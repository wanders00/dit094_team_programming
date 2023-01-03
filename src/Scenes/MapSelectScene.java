package Scenes;

import java.io.IOException;

import GameLogic.FileHandler;
import GameLogic.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MapSelectScene extends SceneController {
    public void show(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/SelectMapScene.fxml"));
        stage.setTitle("Snake Game - Map Selection");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchToOrdinaryLayout(ActionEvent event) throws IOException {
        new FileHandler().updateGameLayout(Game.Layout.ORDINARY);
        switchToSelectMapScene(event);
    }

    public void switchToPlusLayout(ActionEvent event) throws IOException {
        new FileHandler().updateGameLayout(Game.Layout.PLUS);
        switchToSelectMapScene(event);
    }

    public void switchToOctagonLayout(ActionEvent event) throws IOException {
        new FileHandler().updateGameLayout(Game.Layout.OCTAGON);
        switchToSelectMapScene(event);
    }

    public void switchToBlankLayout(ActionEvent event) throws IOException {
        new FileHandler().updateGameLayout(Game.Layout.BLANK);
        switchToSelectMapScene(event);
    }

    public void initialize() {
        switch (new FileHandler().readGameLayout()) {
            case ORDINARY:
                ordinaryMap.setStyle("-fx-border-color: none ; ");
                break;
            case PLUS:
                plusMap.setStyle("-fx-border-color: none ; ");
                break;
            case OCTAGON:
                octagonMap.setStyle("-fx-border-color: none ; ");
                break;
            case BLANK:
                blankMap.setStyle("-fx-border-color: none ; ");
                break;
            default:
                break;
        }
    }

    @FXML
    private Button ordinaryMap;

    @FXML
    private Button plusMap;

    @FXML
    private Button octagonMap;

    @FXML
    private Button blankMap;
}

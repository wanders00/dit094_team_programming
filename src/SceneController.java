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
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class SceneController extends Main /*implements Initializable*/{
    private Stage stage;
    private Scene scene;
    private Parent root;


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


@FXML
private Slider volumeSlider;

@FXML
private Slider fxSlider;

@FXML
private Slider difficultySlider;


private int difficultyLevel;


/*@Override
public void initialize(URL arg0, ResourceBundle arg1) {
    difficultySlider.valueProperty().addListener(new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
            difficultyLevel = (int) difficultySlider.getValue();
        }
        
    });
}*/
}

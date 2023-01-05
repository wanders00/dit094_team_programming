import java.io.File;

import GameLogic.FileHandler;
import Scenes.IntroScene;
import Scenes.MainScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        if (!new File("GameData.json").isFile()) {
            FileHandler.initializeFile();
        }
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        new MainScene().show(stage);
    }

}

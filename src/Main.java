import java.io.File;

import GameLogic.FileHandler;
import Scenes.IntroScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        if (!new File("GameData.json").isFile()) {
            new FileHandler().initializeFile();
        }
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        new IntroScene().show(stage);
    }

}

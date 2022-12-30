import Scenes.IntroScene;
import Scenes.MainScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        new IntroScene().show(stage);
        //new MainScene().show(stage);
    }

}

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Add such that height and width is received from local file. (Settings)
        int width = 800;
        int height = 800;
        primaryStage.setHeight(height);
        primaryStage.setWidth(width);
        primaryStage.setTitle("Snake Game");

        Button startGameButton = new Button();
        startGameButton.setText("Start Game");
        startGameButton.setTranslateX(width / 2);
        startGameButton.setTranslateY(height * 0.4);
        startGameButton.setOnAction(event -> {

        });

        Button settingsButton = new Button();
        settingsButton.setText("Settings");
        settingsButton.setTranslateX(width / 2);
        settingsButton.setTranslateY(height * 0.5);
        settingsButton.setOnAction(event -> {

        });

        Button highScoreListButton = new Button();
        highScoreListButton.setText("High Score List");
        highScoreListButton.setTranslateX(width / 2);
        highScoreListButton.setTranslateY(height * 0.6);
        highScoreListButton.setOnAction(event -> {
            
        });

        Group root = new Group(startGameButton, settingsButton, highScoreListButton);
        Scene startScene = new Scene(root, width, height, Color.WHITE);
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

}

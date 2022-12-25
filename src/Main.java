import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class Main extends Application {


    public static int WIDTH = 800;
    public static int HEIGHT = 800;
    public static Difficulty selectedDifficulty = Difficulty.NORMAL;
    // Add such that these variables are dependant on local file.

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setHeight(HEIGHT);
        primaryStage.setWidth(WIDTH);
        primaryStage.setTitle("Snake Game");
        primaryStage.setResizable(false);
        showMainScene(primaryStage);
        primaryStage.show();
    }

    public void showMainScene(Stage stage) throws IOException { // ADD FUNCTIONALITY
        Parent root = FXMLLoader.load(getClass().getResource("mainMenuScene.fxml"));
        Scene mainScene = new Scene(root);
        stage.setScene(mainScene);
    }

    /*public void showHighScoreScene() { // ADD FUNCTIONALITY

    Text text = new Text();
    text.setText("High Scores");

        int xPOS=225;
        int yPOS=75;
        text.setX(xPOS);
        text.setY(yPOS);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));

        Group root = new Group(mainSceneButton(), quitButton(), text);
        //Scene highScoreScene = new Scene(vBox, Color.YELLOW);
        PRIMARY_STAGE.setScene(new Scene(root,800,800));
    }*/

    public void showGameScene(Stage stage) {
        int cellcount = 20;
        int cellsize = 40;
        int gridsize = cellcount * cellsize;
        Game game = new Game(cellcount, cellcount, selectedDifficulty); // Fix difficulty later
        Canvas canvas = new Canvas(gridsize, gridsize);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene gameScene = new Scene(root, gridsize, gridsize);
        stage.setScene(gameScene);

        gameScene.setOnKeyPressed(e -> {
            if (Keybind.fromKeypress(e.getCode()) != null) {
                Keybind newDirection = Keybind.fromKeypress(e.getCode());
                switch (newDirection) {
                    case DOWN:
                        if (game.getSnake().getDirection() != Direction.UP) {
                            game.getSnake().setDirection(Direction.DOWN);
                            System.out.println("DOWN");
                        }
                        break;
                    case LEFT:
                        if (game.getSnake().getDirection() != Direction.RIGHT) {
                            game.getSnake().setDirection(Direction.LEFT);
                            System.out.println("LEFT");
                        }
                        break;
                    case RIGHT:
                        if (game.getSnake().getDirection() != Direction.LEFT) {
                            game.getSnake().setDirection(Direction.RIGHT);
                            System.out.println("RIGHT");
                        }
                        break;
                    case UP:
                        if (game.getSnake().getDirection() != Direction.DOWN) {
                            game.getSnake().setDirection(Direction.UP);
                            System.out.println("UP");
                        }
                        break;
                    case PAUSE:
                            game.pauseToggle(); //calls method in class "game" which controlls the update method
                        System.out.println("Pause");
                        break;
                    default:
                        break;
                }
            }
        });

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gc.clearRect(0, 0, gridsize, gridsize);
                gc.setFill(Color.rgb(0, 0, 0));
                gc.fillRect(0, 0, gridsize, gridsize);
                for (int i = 0; i < cellcount; i++) {
                    for (int j = 0; j < cellcount; j++) {
                        GameObject currentGameObject = game.getState()[i][j];
                        gc.setFill(currentGameObject.getColor());
                        gc.fillRect(cellsize * j, cellsize * i, cellsize, cellsize);
                    }
                }
                if(!game.update()) {
                    timer.cancel();
                }
            }
        }, 0, (int) (250 * game.getDifficulty().getSpeedMultiplier()));
    }

}

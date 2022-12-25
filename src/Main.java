import java.io.IOException;
import javafx.animation.AnimationTimer;
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

    public static Difficulty selectedDifficulty = Difficulty.NORMAL;
    // Add such that these variables are dependant on local file.

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Snake Game");
        stage.setResizable(false);
        showMainScene(stage);
        stage.show();
    }

    public void showMainScene(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenuScene.fxml"));
        Scene mainScene = new Scene(root);
        stage.setScene(mainScene);
    }

    /*
     * public void showHighScoreScene() { // ADD FUNCTIONALITY
     * 
     * Text text = new Text();
     * text.setText("High Scores");
     * 
     * int xPOS=225;
     * int yPOS=75;
     * text.setX(xPOS);
     * text.setY(yPOS);
     * text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
     * 
     * Group root = new Group(mainSceneButton(), quitButton(), text);
     * //Scene highScoreScene = new Scene(vBox, Color.YELLOW);
     * PRIMARY_STAGE.setScene(new Scene(root,800,800));
     * }
     */

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
        stage.show();

        new AnimationTimer() {
            private long framedelay = (long) (250000000 * game.getDifficulty().getSpeedMultiplier());
            private long lastpress;

            public void handle(long arg0) {
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

                gameScene.setOnKeyPressed(e -> {
                    if (Keybind.fromKeypress(e.getCode()) != null) {
                        Keybind latestKeypress = Keybind.fromKeypress(e.getCode());
                        Direction currentDirection = game.getSnake().getDirection();
                        switch (latestKeypress) {
                            case DOWN:
                                if (!game.getPausedGame() && currentDirection != Direction.UP
                                        && currentDirection != Direction.DOWN) {
                                    game.getSnake().setDirection(Direction.DOWN);
                                    updateGame();
                                }
                                break;
                            case LEFT:
                                if (!game.getPausedGame() && currentDirection != Direction.RIGHT
                                        && currentDirection != Direction.LEFT) {
                                    game.getSnake().setDirection(Direction.LEFT);
                                    updateGame();
                                }
                                break;
                            case RIGHT:
                                if (!game.getPausedGame() && currentDirection != Direction.LEFT
                                        && currentDirection != Direction.RIGHT) {
                                    game.getSnake().setDirection(Direction.RIGHT);
                                    updateGame();
                                }
                                break;
                            case UP:
                                if (!game.getPausedGame() && currentDirection != Direction.DOWN
                                        && currentDirection != Direction.UP) {
                                    game.getSnake().setDirection(Direction.UP);
                                    updateGame();
                                }
                                break;
                            case PAUSE:
                                game.pauseToggle(); // calls method in class "game" which controlls the update method
                                System.out.println("Pause");
                                break;
                            default:
                                break;
                        }
                    }
                });

                if (System.nanoTime() - lastpress > framedelay) {
                    updateGame();
                }
            }

            public void updateGame() {
                lastpress = System.nanoTime();
                if (!game.update()) {
                    stop();
                }
            }
        }.start();
    }
}

package Scenes;
import java.io.IOException;
import GameLogic.Difficulty;
import GameLogic.Direction;
import GameLogic.Game;
import GameLogic.Keybind;
import GameLogic.GameObjects.GameObject;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameScene extends SceneController {

    public static Difficulty selectedDifficulty = Difficulty.NORMAL; // read from json file

    public void show(Stage stage) {
        int cellcount = 20;
        int cellsize = 40;
        int gridsize = cellcount * cellsize;
        Game game = new Game(cellcount, cellcount, selectedDifficulty); // Fix difficulty later as local file
        Canvas canvas = new Canvas(gridsize, gridsize);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene gameScene = new Scene(root, gridsize, gridsize);
        stage.setScene(gameScene);
        stage.show();

        new AnimationTimer() {
            private long framedelay = game.getDifficulty().getGameTimerSpeed();
            private long lastpress;

            public void handle(long arg0) {
                stage.setTitle("Snake Game - Map: " + game.getLayout() + " - Difficulty: " + game.getDifficulty()
                        + " - Score: " + game.getCurrentGameScore());
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
                        switch (Keybind.fromKeypress(e.getCode())) {
                            case DOWN:
                                updateMovement(Direction.DOWN);
                                break;
                            case LEFT:
                                updateMovement(Direction.LEFT);
                                break;
                            case RIGHT:
                                updateMovement(Direction.RIGHT);
                                break;
                            case UP:
                                updateMovement(Direction.UP);
                                break;
                            case PAUSE:
                                game.pauseToggle(); // calls method in class "game" which controlls the update method
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

            public void updateMovement(Direction newDirection) {
                Direction currentDirection = game.getSnake().getDirection();
                if (game.getPausedGame()) {
                    game.pauseToggle();
                }
                if (currentDirection != newDirection
                        && currentDirection != newDirection.getOppositeDirection()) {
                    game.getSnake().setNewDirection(newDirection);
                    if (System.nanoTime() - lastpress > (framedelay / 10)) {
                        updateGame();
                    }
                }
            }

            public void updateGame() {
                lastpress = System.nanoTime();
                game.getSnake().updateDirection();
                if (!game.update()) {
                    stop();
                    try {
                        new GameOverScene().show(stage);
                        /*Parent root = FXMLLoader.load(getClass().getResource("fxml/gameOverScene.fxml"));
                        Scene gameOverScene = new Scene(root);
                        stage.setScene(gameOverScene);
                        stage.show();*/
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }
}

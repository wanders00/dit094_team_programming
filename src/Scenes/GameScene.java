package Scenes;
import java.io.IOException;
import GameLogic.Direction;
import GameLogic.FileHandler;
import GameLogic.Game;
import GameLogic.Keybind;
import GameLogic.GameObjects.GameObject;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameScene extends SceneController {

    public void show(Stage stage) {
        int cellCount = 20;
        int cellSize = 40;
        int gridSize = cellCount * cellSize;
        Game game = new Game(cellCount, cellCount); // Fix difficulty later as local file
        Canvas canvas = new Canvas(gridSize, gridSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene gameScene = new Scene(root, gridSize, gridSize);
        stage.setScene(gameScene);
        stage.show();

        new AnimationTimer() {
            private long frameDelay = FileHandler.readGameDifficulty().getGameTimerSpeed();
            private long lastPress;

            public void handle(long arg0) {
                stage.setTitle("Snake Game - Map: " + game.getLayout() + " - Difficulty: " + game.getDifficulty()
                        + " - Score: " + game.getCurrentGameScore());
                gc.clearRect(0, 0, gridSize, gridSize);
                gc.setFill(Color.rgb(0, 0, 0));
                gc.fillRect(0, 0, gridSize, gridSize);
                for (int i = 0; i < cellCount; i++) {
                    for (int j = 0; j < cellCount; j++) {
                        GameObject currentGameObject = game.getState()[i][j];
                        gc.setFill(currentGameObject.getColor());
                        gc.fillRect(cellSize * j, cellSize * i, cellSize, cellSize);
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

                if (System.nanoTime() - lastPress > frameDelay) {
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
                        // prevents the user from going in the opposite direction and essentially "skipping" a turn
                    game.getSnake().setNewDirection(newDirection);
                    if (System.nanoTime() - lastPress > (frameDelay / 10)) { // used to allow for faster turn
                        updateGame();
                    }
                }
            }

            public void updateGame() {
                lastPress = System.nanoTime();
                game.getSnake().updateDirection();
                if (!game.update()) {
                    Audio.play("audio/death_sound_2.mp3");
                    Audio.play("audio/test.mp3");
                    
                    stop();
                    try {
                        FileHandler.updateHighScores(game.getCurrentGameScore());
                        new GameOverScene().show(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }
}

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
        Game game = new Game(cellCount, cellCount);
        Canvas canvas = new Canvas(gridSize, gridSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene gameScene = new Scene(root, gridSize, gridSize);
        stage.setScene(gameScene);
        stage.show();

        new AnimationTimer() {
            private long frameDelay = FileHandler.readGameDifficulty().getGameTimerSpeed();
            private long lastUpdated;

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
                        // Goes through the entire grid and fills in the color dependent on which
                        // GameObject that is located for that row/column.
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

                if (System.nanoTime() - lastUpdated > frameDelay) {
                    updateGame();
                }
                // Timer that updates the game whenever it was more than frameDelay in
                // nanoseconds since last update.
            }

            public void updateMovement(Direction newDirection) {
                Direction currentDirection = game.getSnake().getDirection();
                if (game.getPausedGame()) {
                    game.pauseToggle();
                }
                if (currentDirection != newDirection
                        && currentDirection != newDirection.getOppositeDirection()) {
                    // prevents the snake from moving invalidly, i.e same direction or opposite.
                    game.getSnake().setNewDirection(newDirection);
                    if (System.nanoTime() - lastUpdated > (frameDelay / 10)) {
                        updateGame();
                    }
                    // Allows for faster turns, which makes moving the snake way smoother.
                    // Limitation of only being able to do it every frameDelay / 10 nanoseconds.
                }
            }

            public void updateGame() {
                lastUpdated = System.nanoTime();
                game.getSnake().updateDirection();
                if (!game.update()) {
                    Audio.DeathSound();
                    stop();
                    try {
                        new GameOverScene().show(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }
}

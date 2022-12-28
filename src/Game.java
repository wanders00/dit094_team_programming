public class Game {
    private static Layout currentLayout = Layout.ORDINARY;
    // Should be local file

    public enum Layout { // there is a scene built where the user can choose between these. If you update
                         // and add more OR remove please mention it so that the correlating scene can be
                         // edited
        ORDINARY,
        PLUS
    }

    private Snake snake;
    private Layout layout;
    private int width;
    private int height;
    private GameObject[][] grid;
    private Difficulty difficulty;
    private Boolean pausedGame;
    private double currentGameScore;
    // Sound sound;
    // Resolution resolution;

    Game(int width, int height, Difficulty difficulty) {
        this.pausedGame = true;
        this.currentGameScore = 0;
        this.snake = new Snake(width / 2, height / 2);
        this.layout = currentLayout; // have this read local file instead later
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;
        this.grid = generateMap(height, width);
        generateFruit();
    }

    public GameObject[][] generateMap(int height, int width) {
        GameObject[][] gameGrid;
        switch (this.layout) {
            case ORDINARY:
                gameGrid = generateOrdinaryMap();
                break;
            case PLUS:
                gameGrid = generatePlusMap();
                break;
            default:
                gameGrid = null;
                break;
        }

        gameGrid[this.snake.getRow(0)][this.snake.getColumn(0)] = new SnakeSegment(true);
        for (int i = 1; i < this.snake.getBody().size(); i++) {
            gameGrid[this.snake.getRow(i)][this.snake.getColumn(i)] = new SnakeSegment();
        }
        return gameGrid;
    }

    private GameObject[][] generateOrdinaryMap() {
        GameObject[][] normalGameGrid = new GameObject[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (i == 0 || i == height - 1 || j == 00 || j == width - 1) {
                    normalGameGrid[i][j] = new WallGameObject();
                } else {
                    normalGameGrid[i][j] = new EmptyGameObject();
                }
            }
        }
        return normalGameGrid;
    }

    private GameObject[][] generatePlusMap() {
        GameObject[][] plusGameGrid = new GameObject[this.height][this.width];
        int widthGap = this.width / 4;
        int heightGap = this.height / 4;
        // could link this to the difficulty setting; the harder the difficulty the
        // bigger the square walls

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if ((i + heightGap < this.height - 1 && i - heightGap > 0)
                        || (j + widthGap < this.width - 1 && j - widthGap > 0)) {
                    plusGameGrid[i][j] = new EmptyGameObject();
                } else {
                    plusGameGrid[i][j] = new WallGameObject();
                }
            }
        }

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (i == 0 || i == this.height - 1 || j == 00 || j == this.width - 1) {
                    plusGameGrid[i][j] = new WallGameObject();
                }
            }
        }
        return plusGameGrid;
    }

    public boolean update() {
        if (!pausedGame) { // if the pausedGame=true the game will continue to update, when its false
                           // nothing happens and it freezez
            if (predictMovement() instanceof WallGameObject || predictMovement() instanceof SnakeSegment) {
                addScore(currentGameScore);
                return false;
            }
            int newRow = this.snake.getRow(0);
            int newColumn = this.snake.getColumn(0);
            switch (this.snake.getDirection()) {
                case DOWN:
                    newRow++;
                    break;
                case LEFT:
                    newColumn--;
                    break;
                case RIGHT:
                    newColumn++;
                    break;
                case UP:
                    newRow--;
                    break;
            }

            if (predictMovement() instanceof FruitGameObject) {
                this.snake.growSnake(this.snake.getRow(this.snake.getBody().size() - 1),
                        this.snake.getColumn(this.snake.getBody().size() - 1));
                this.currentGameScore = this.currentGameScore + 1 * difficulty.getScoreMultiplier();
                generateFruit();
            } else {
                this.grid[this.snake.getRow(this.snake.getBody().size() - 1)][this.snake
                        .getColumn(this.snake.getBody().size() - 1)] = new EmptyGameObject();
            }

            for (int i = this.snake.getBody().size() - 1; i > 0; i--) {
                this.snake.setRow(i, this.snake.getRow(i - 1));
                this.snake.setColumn(i, this.snake.getColumn(i - 1));
                this.grid[this.snake.getRow(i)][this.snake.getColumn(i)] = new SnakeSegment();
            }

            this.snake.setColumn(0, newColumn);
            this.snake.setRow(0, newRow);
            this.grid[this.snake.getRow(0)][this.snake.getColumn(0)] = new SnakeSegment(true);
        }
        return true;

    }

    public void generateFruit() {
        boolean createdFruit = true;
        while (createdFruit) {
            int row = (int) (Math.random() * height);
            int column = (int) (Math.random() * width);
            if (this.grid[row][column] instanceof EmptyGameObject) {
                this.grid[row][column] = new FruitGameObject();
                createdFruit = false;
            }
        }
    }

    public boolean getPausedGame() {
        return this.pausedGame;
    }

    public void pauseToggle() {
        // changes the value to the oposite everytime the pause-key is pressed, making
        // the game able to pause and play
        this.pausedGame = !this.pausedGame;
    }

    public GameObject predictMovement() {
        return this.grid[predictRow()][predictColumn()];
    }

    public int[] predictCoordinates() {
        switch (this.snake.getDirection()) {
            case DOWN:
                return new int[] { this.snake.getRow(0) + 1, this.snake.getColumn(0) };
            case LEFT:
                return new int[] { this.snake.getRow(0), this.snake.getColumn(0) - 1 };
            case RIGHT:
                return new int[] { this.snake.getRow(0), this.snake.getColumn(0) + 1 };
            case UP:
                return new int[] { this.snake.getRow(0) - 1, this.snake.getColumn(0) };
            default:
                return null;
        }
    }

    public void addScore(double newScore) { // creating a object is maybe not needed, but an easy way to create more
                                            // atributes to show in the highScore Scene
        Score score = new Score(newScore);
    }

    public double increaseScore(double Scoremultiplier) {
        return this.difficulty.getScoreMultiplier();
    }

    public double getCurrentGameScore() {
        return this.currentGameScore;
    }

    public static void setCurrentLayout(Layout currentLayout) {
        Game.currentLayout = currentLayout;
    }

    public int predictRow() {
        return predictCoordinates()[0];
    }

    public int predictColumn() {
        return predictCoordinates()[1];
    }

    public GameObject[][] getState() {
        return this.grid;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public Layout getLayout() {
        return this.layout;
    }
}

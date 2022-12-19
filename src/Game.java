public class Game {

    private static Layout currentLayout = Layout.NORMAL; // Should be local file
    public Boolean pausedGame = false; //prob should initialise this somewhere else, controls the update method
    public enum Layout {
        NORMAL;
    }
    private Snake snake;
    private Layout layout;
    private int width;
    private int height;
    private GameObject[][] grid;
    private Difficulty difficulty;
    // Sound sound;
    // Resolution resolution;

    Game(int width, int height, Difficulty difficulty) {
        this.snake = new Snake(width / 2, height / 2);
        this.layout = currentLayout; // do something with seed argument
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;
        this.grid = generateMap(height, width);
        generateFruit();
    }

    public GameObject[][] generateMap(int height, int width) {
        GameObject[][] gameGrid;
        switch (this.layout) {
            case NORMAL:
                gameGrid = generateNormalMap();
                break;
            default:
                gameGrid = null;
                break;
        }
        gameGrid[height / 2][width / 2] = new SnakeSegment();
        return gameGrid;
    }

    private GameObject[][] generateNormalMap() {
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

    public boolean update() {
        if(!pausedGame){ //if the pausedGame=true the game will continue to update, when its false nothing happens and it freezez
            boolean shouldGrowSnake = false;
            int newRow, newColumn, originalRow, originalColumn;
            originalRow = newRow = this.snake.getRow(0);
            originalColumn = newColumn = this.snake.getColumn(0);
            if (predictMovement() instanceof WallGameObject || predictMovement() instanceof SnakeSegment) {
                return false;
            } else if (predictMovement() instanceof FruitGameObject) {
                shouldGrowSnake = true;
                generateFruit();
            }
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
            for (int i = 0; i < this.snake.getBody().size(); i++) {
                originalColumn = this.snake.getColumn(i);
                originalRow = this.snake.getRow(i);
                this.grid[newRow][newColumn] = new SnakeSegment();
                this.snake.setColumn(i, newColumn);
                this.snake.setRow(i, newRow);
                newRow = originalRow;
                newColumn = originalColumn;
            }
            if (shouldGrowSnake) {
                this.snake.growSnake(originalRow, originalColumn);
            } else {
                this.grid[originalRow][originalColumn] = new EmptyGameObject();
            }
            return true;
        }else {
            if(pausedGame){ // this is a weird fix to get the run method to NOT return false just bc the game is paused, need to change this but it works, feels like it will give bugs for bigger games. I would probably make this method return void and use an bolean insted. I dont want this metod to return false when it's paused, thats why i used this if/else statement
                return true;
            }else {
                return false;
            }
        }

    }

    public void generateFruit() {
        boolean createdFruit = true;
        while (createdFruit) {
            int row = (int) (Math.random() * height);
            int column = (int) (Math.random() * width);
            System.out.println(row + " " + column); // delete me later
            if (this.grid[row][column] instanceof EmptyGameObject) {
                this.grid[row][column] = new FruitGameObject();
                createdFruit = false;
            }
        }
    }
    public void setPausedGame(Boolean pausedGame) { //setter for bolean 
        this.pausedGame = pausedGame;
    }
    public void pauseToggle(){ //changes the value to the oposite everytime the pause-key is pressed, making the game able to                               pause and play
        setPausedGame(!pausedGame);
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
}

public class Game {

    public enum Layout {
        NORMAL;
    }

    Layout currentLayout = Layout.NORMAL;

    Snake snake;
    int seed;
    int width;
    int height;
    GameObject[][] grid;
    Difficulty difficulty;
    // Sound sound;
    // Resolution resolution;

    Game(int seed, int width, int height, Difficulty difficulty) {
        this.snake = new Snake();
        this.grid = generateMap(height, width, currentLayout);
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;
    }

    public GameObject[][] generateMap(int height, int width, Layout layout) {
        GameObject[][] gameGrid;
        switch (layout) {
            case NORMAL:
                gameGrid = generateNormalMap(height, width);
                break;
            default:
                gameGrid = null;
                break;
        }
        return gameGrid;
    }

    private GameObject[][] generateNormalMap(int height, int width) {
        GameObject[][] normalGameGrid = new GameObject[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                normalGameGrid[i][j] = new EmptyGameObject();
            }
        }
        return normalGameGrid;
    }

    public GameObject[][] getState() {
        return this.grid;
    }
}

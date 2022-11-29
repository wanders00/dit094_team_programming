public class Game {
    Snake snake;
    int seed;
    int width;
    int height;
    GameObject[][] grid;
    Difficulty difficulty;
    //Sound sound;
    //Resolution resolution;

    Game(int seed, int width, int height, Difficulty difficulty) {
        this.snake = new Snake();
        this.grid = new GameObject[width][height]; //this.grid = generateMap(width, height, seed);
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;
    }
}

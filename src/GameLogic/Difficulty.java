package GameLogic;

public enum Difficulty {

    EASY("Easy", 5, 200_000_000),
    NORMAL("Normal", 10, 150_000_000),
    HARD("Hard", 15, 100_000_000);

    private final String DIFFICULTY_NAME;
    private final double SCORE_MULTIPLIER;
    private final long GAME_TIMER_SPEED;
    // gameTimerSpeed = in nanoseconds, 100_000_000 = 1 second.
    // Will be a ~0.1 seconds slower than the actual value. (Computing delay)

    Difficulty(String name, double scoreMultiplier, long gameTimerSpeed) {
        this.DIFFICULTY_NAME = name;
        this.SCORE_MULTIPLIER = scoreMultiplier;
        this.GAME_TIMER_SPEED = gameTimerSpeed;
    }

    public double getScoreMultiplier() {
        return this.SCORE_MULTIPLIER;
    }

    public long getGameTimerSpeed() {
        return this.GAME_TIMER_SPEED;
    }

    public String toString() {
        return this.DIFFICULTY_NAME;
    }

    public static Difficulty fromString(String input) {
        for (Difficulty difficulty : Difficulty.values()) {
            if (difficulty.toString().equals(input)) {
                return difficulty;
            }
        }
        return null;
    }

}

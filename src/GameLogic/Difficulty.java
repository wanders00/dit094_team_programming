package GameLogic;
public enum Difficulty {//Enum to create different difficulty levels of our game which are EASY,NORMAL AND HARD.

    EASY(5, 200_000_000),
    NORMAL(10, 150_000_000),
    HARD(15, 100_000_000); 
    // Cornelia and marcus thinks it would be fun to have an extreme hard level at
    // 50_000_000, will maybe implement :D

    private final double scoreMultiplier;
    private final long gameTimerSpeed;
    // gameTimerSpeed = in nanoseconds, 100_000_000 = 1 second.
    // Will be a ~0.1 seconds slower than the actual value. (Computing delay)

    Difficulty(double scoreMultiplier, long gameTimerSpeed) {
        this.scoreMultiplier = scoreMultiplier;
        this.gameTimerSpeed = gameTimerSpeed;
    }

    public double getScoreMultiplier() {
        return this.scoreMultiplier;
    }

    public long getGameTimerSpeed() {
        return this.gameTimerSpeed;
    }

}

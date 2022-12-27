public enum Difficulty {

    EASY(5, 200_000_000),
    NORMAL(10, 150_000_000),
    HARD(15, 100_000_000);
    private final double scoreMultiplier;
    private final long gameTimerSpeed;

    Difficulty(double scoreMultiplier, long speedMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
        this.gameTimerSpeed = speedMultiplier;
    }

    public double getScoreMultiplier() {
        return this.scoreMultiplier;
    }

    public long getGameTimerSpeed() {
        return this.gameTimerSpeed;
    }

}

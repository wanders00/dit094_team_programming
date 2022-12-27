public enum Difficulty {
    EASY(0.75, 200000000),
    NORMAL(1, 150000000),
    HARD(1.25, 100000000);

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

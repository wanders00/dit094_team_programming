public enum Difficulty {
    EASY(1, 1),
    NORMAL(1, 1),
    HARD(1, 1);
    // random numbers, change me later :)

    private final double scoreMultiplier;
    private final double speedMultiplier;

    Difficulty(double scoreMultiplier, double speedMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
        this.speedMultiplier = speedMultiplier;
    }

    public double getScoreMultiplier() {
        return this.scoreMultiplier;
    }

    public double getSpeedMultiplier() {
        return this.speedMultiplier;
    }

}

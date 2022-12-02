public enum Difficulty {
    Easy(1, 1),
    Medium(1, 1),
    Hard(1, 1);
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

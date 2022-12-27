public enum Difficulty {
    //We want to add 11 enums representing each of the choices the user can make in the settings, the "easy#" stands for each choice in the settings. If we feel that they are redundant we can remove or rename. theese enums are required to be consistent with the current settings page
    EASY(5, 200000000),
    NORMAL(10, 150000000),
    HARD(15, 100000000);
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

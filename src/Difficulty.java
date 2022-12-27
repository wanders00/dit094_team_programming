public enum Difficulty {
    //We want to add 11 enums representing each of the choices the user can make in the settings, the "easy#" stands for each choice in the settings. If we feel that they are redundant we can remove or rename. theese enums are required to be consistent with the current settings page
    EASY1(0.75, 200000000),
    EASY2(0.80, 190000000),
    EASY3(0.85, 180000000),
    EASY4(0.90, 170000000),
    EASY5(0.95, 160000000),//numbers over one will go slower and under one faster
    NORMAL1(1, 150000000),
    NORMAL2(1.05, 140000000),
    NORMAL3(1.10, 130000000),
    NORMAL4(1.15, 120000000),
    NORMAL5(1.20, 110000000),
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

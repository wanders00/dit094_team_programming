public class Color {
    private int red;
    private int green;
    private int blue;

    Color(int red, int green, int blue) { // 0-255
       this.red = red;
       this.green = green;
       this.blue = blue; 
    }

    // Either specific methods like this, or we can make one that returns an array of the 3 color codes.
    public int getRedColorCode() {
        return this.red;
    }

    public int getGreenColorCode() {
        return this.green;
    }

    public int getBlueColorCode() {
        return this.blue;
    }
}

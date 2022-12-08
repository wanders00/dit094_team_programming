public class Color {
    private int red;
    private int green;
    private int blue;

    Color(int red, int green, int blue) { // 0-255
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRedColorCode() {
        return this.red;
    }

    public int getGreenColorCode() {
        return this.green;
    }

    public int getBlueColorCode() {
        return this.blue;
    }

    public int[] getRGB() {
        int[] rgb = new int[] {this.red, this.blue, this.green };
        return rgb;
    }

}

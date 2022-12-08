public abstract class GameObject {
    Color color;

    GameObject(int red, int green, int blue) {
        color = new Color(red, green, blue);
    }

    public int getRed() {
        return this.color.getRedColorCode();
    }

    public int getBlue() {
        return this.color.getBlueColorCode();
    }

    public int getGreen() {
        return this.color.getGreenColorCode();
    }
}

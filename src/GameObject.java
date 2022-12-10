import javafx.scene.paint.Color;

public abstract class GameObject {
    Color color;

    GameObject(int red, int green, int blue) {
        this.color = Color.rgb(red, green, blue);
    }

    public Color getColor() {
        return this.color;
    }

}

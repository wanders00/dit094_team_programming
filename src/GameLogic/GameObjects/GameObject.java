package GameLogic.GameObjects;
import javafx.scene.paint.Color;

public abstract class GameObject {//An abstract class to provide a skeleton for game objects.
    private Color color;

    public GameObject(int red, int green, int blue) {
        this.color = Color.rgb(red, green, blue);
    }

    public Color getColor() {
        return this.color;
    }

}

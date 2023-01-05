package GameLogic.GameObjects;
public class EmptyGameObject extends GameObject {//Initialiizng an empty game object

    private final static int RED = 229;
    private final static int GREEN = 208;
    private final static int BLUE = 204;

    public EmptyGameObject() {
        super(RED, GREEN, BLUE);
    }
}

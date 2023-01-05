package GameLogic.GameObjects;
public class FruitGameObject extends GameObject {//Creation of a FruitGame object that is a sub-class of Game Object.
    
    private final static int RED = 219;
    private final static int GREEN = 50;
    private final static int BLUE = 77;

    public FruitGameObject() {
        super(RED, GREEN, BLUE);
    }

}

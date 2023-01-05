package GameLogic.GameObjects;
public class WallGameObject extends GameObject {//Initializiation of the WallGameObejct that is used to have an instance of for the grid.

    private final static int RED = 2;
    private final static int GREEN = 48;
    private final static int BLUE = 89;

    public WallGameObject() {//constructor for the wallGameobject
        super(RED, GREEN, BLUE);
    }

}

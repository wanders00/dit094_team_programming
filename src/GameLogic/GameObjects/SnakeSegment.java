package GameLogic.GameObjects;

public class SnakeSegment extends GameObject {//The method creates the snake object which is a sub-class of the game object.The snake object has a Head and a Body which can have a different colour.

    private final static int BODY_RED = 70;
    private final static int BODY_GREEN = 152;
    private final static int BODY_BLUE = 232;

    private final static int HEAD_RED = 25;
    private final static int HEAD_GREEN = 98;
    private final static int HEAD_BLUE = 196;

    public SnakeSegment() {//initializing the constructor for the snake body.
        super(BODY_RED, BODY_GREEN, BODY_BLUE);
    }

    public SnakeSegment(boolean isHead) {//initializing the constructor for the snake head.
        super(HEAD_RED, HEAD_GREEN, HEAD_BLUE);
    }
    // Overloaded method so we can have different color for the head,
    // but still be a SnakeSegment. If constructor is given a boolean argument
    // create with head color instead.

}

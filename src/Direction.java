import javafx.scene.input.KeyCode;

public enum Direction {
    UP(KeyCode.W),
    DOWN(KeyCode.S),
    LEFT(KeyCode.A),
    RIGHT(KeyCode.D);
    //

    private KeyCode keybind;

    Direction(KeyCode keybind) {
        this.keybind = keybind;
    }

    public KeyCode getKeyind() {
        return this.keybind;
    }

    public void setKeybind(KeyCode newKeybind) {
        this.keybind = newKeybind;
    }

    public static Direction fromKeypress(KeyCode input) {
        for (Direction direction : Direction.values()) {
            if (direction.getKeyind().equals(input)) {
                return direction;
            }
        }
        return null;
    }
}

import javafx.scene.input.KeyCode;

public enum Keybind {
    UP(KeyCode.W),
    DOWN(KeyCode.S),
    LEFT(KeyCode.A),
    RIGHT(KeyCode.D),
    PAUSE(KeyCode.SPACE);

    private KeyCode keyCode;

    Keybind(KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    public KeyCode getKeyCode() {
        return this.keyCode;
    }

    public void setKeyCode(KeyCode newKeybind) {
        this.keyCode = newKeybind;
    }

    public static Keybind fromKeypress(KeyCode input) {
        for (Keybind keybind : Keybind.values()) {
            if (keybind.getKeyCode().equals(input)) {
                return keybind;
            }
        }
        return null;
    }
}

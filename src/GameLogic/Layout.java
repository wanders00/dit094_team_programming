package GameLogic;

public enum Layout {

    ORDINARY("Ordinary"),
    BLANK("Blank"),
    BORDERED_PLUS("Plus"),
    UNBORDERED_PLUS("Unbordered Plus"),
    BORDERED_OCTAGON("Octagon"),
    UNBORDERED_OCTAGON("Unbordered Octagon");

    private final String LAYOUT_NAME;

    Layout(String name) {
        this.LAYOUT_NAME = name;
    }

    public String toString() {
        return this.LAYOUT_NAME;
    }

    public static Layout fromString(String input) {
        for (Layout layout : Layout.values()) {
            if (layout.toString().equals(input)) {
                return layout;
            }
        }
        return null;
    }
}
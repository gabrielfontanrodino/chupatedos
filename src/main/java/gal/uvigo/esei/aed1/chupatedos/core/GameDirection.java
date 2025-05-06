package gal.uvigo.esei.aed1.chupatedos.core;

public enum GameDirection {
    CLOCKWISE,
    COUNTERCLOCKWISE;

    /**
     * Method to change the direction in the game.
     * @return The next direction in the game.
     */
    public GameDirection reverse() {
        return this == CLOCKWISE ? COUNTERCLOCKWISE : CLOCKWISE;
    }

    /**
     * Method to convert the direction to an integer for updating the "current player" index.
     * @return 1 if the direction is CLOCKWISE, -1 if COUNTERCLOCKWISE.
     */
    public int toInt() {
        return this == CLOCKWISE ? 1 : -1;
    }
}

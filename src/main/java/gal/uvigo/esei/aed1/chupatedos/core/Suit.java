package gal.uvigo.esei.aed1.chupatedos.core;

/**
 * Suits from Spanish deck of cards
 */
public enum Suit {
    OROS, COPAS, ESPADAS, BASTOS;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.name().charAt(0)); // First letter in uppercase
        sb.append(this.name().substring(1).toLowerCase()); // Rest in lowercase

        return sb.toString();
    }
}
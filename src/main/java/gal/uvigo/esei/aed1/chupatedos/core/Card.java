package gal.uvigo.esei.aed1.chupatedos.core;

//TODO: Boolean special Cards 2 y 7

public enum Card {
    // ==== OROS ====
    AS_OROS(1, Suit.OROS),
    DOS_OROS(2, Suit.OROS),
    TRES_OROS(3, Suit.OROS),
    CUATRO_OROS(4, Suit.OROS),
    CINCO_OROS(5, Suit.OROS),
    SEIS_OROS(6, Suit.OROS),
    SIETE_OROS(7, Suit.OROS),
    SOTA_OROS(10, Suit.OROS),
    CABALLO_OROS(11, Suit.OROS),
    REY_OROS(12, Suit.OROS),
    // ==== COPAS ====
    AS_COPAS(1, Suit.COPAS),
    DOS_COPAS(2, Suit.COPAS),
    TRES_COPAS(3, Suit.COPAS),
    CUATRO_COPAS(4, Suit.COPAS),
    CINCO_COPAS(5, Suit.COPAS),
    SEIS_COPAS(6, Suit.COPAS),
    SIETE_COPAS(7, Suit.COPAS),
    SOTA_COPAS(10, Suit.COPAS),
    CABALLO_COPAS(11, Suit.COPAS),
    REY_COPAS(12, Suit.COPAS),
    // ==== ESPADAS ====
    AS_ESPADAS(1, Suit.ESPADAS),
    DOS_ESPADAS(2, Suit.ESPADAS),
    TRES_ESPADAS(3, Suit.ESPADAS),
    CUATRO_ESPADAS(4, Suit.ESPADAS),
    CINCO_ESPADAS(5, Suit.ESPADAS),
    SEIS_ESPADAS(6, Suit.ESPADAS),
    SIETE_ESPADAS(7, Suit.ESPADAS),
    SOTA_ESPADAS(10, Suit.ESPADAS),
    CABALLO_ESPADAS(11, Suit.ESPADAS),
    REY_ESPADAS(12, Suit.ESPADAS),
    // ==== BASTOS ====
    AS_BASTOS(1, Suit.BASTOS),
    DOS_BASTOS(2, Suit.BASTOS),
    TRES_BASTOS(3, Suit.BASTOS),
    CUATRO_BASTOS(4, Suit.BASTOS),
    CINCO_BASTOS(5, Suit.BASTOS),
    SEIS_BASTOS(6, Suit.BASTOS),
    SIETE_BASTOS(7, Suit.BASTOS),
    SOTA_BASTOS(10, Suit.BASTOS),
    CABALLO_BASTOS(11, Suit.BASTOS),
    REY_BASTOS(12, Suit.BASTOS);

    private final int number;
    private final Suit suit;

    Card(int number, Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    public int getNumber() {
        return this.number;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public boolean isSameSuit(Card other) {
        return this.getSuit() == other.getSuit();
    }

    public boolean isSameNumber(Card other) {
        return this.getNumber() == other.getNumber();
    }

    public boolean isCompatibleWith(Card other) {
        return this.isSameSuit(other) || this.isSameNumber(other);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (this.number) {
            case 10 -> sb.append("Sota");
            case 11 -> sb.append("Caballo");
            case 12 -> sb.append("Rey");
            default -> sb.append(Integer.toString(this.number));
        }

        sb.append(" de ");
        sb.append(this.suit);
        return sb.toString();
    }

}

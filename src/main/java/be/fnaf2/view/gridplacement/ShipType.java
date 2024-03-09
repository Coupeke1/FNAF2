package be.fnaf2.view.gridplacement;

public enum ShipType {
    SUBMARINE(3, 2),
    CRUISER(5, 2),
    DESTROYER(6, 1),
    BATTLESHIP(8, 1);

    private final int length;
    private int available; // Aantal beschikbare schepen van dit type

    ShipType(int length, int available) {
        this.length = length;
        this.available = available;
    }

    public int getLength() {
        return length;
    }

    public int getAvailable() {
        return available;
    }

    public void decrementAvailable() {
        if (this.available > 0) {
            this.available--;
        }
    }
}

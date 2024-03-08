package be.fnaf2.view.gridplacement;
public enum ShipType {
    SUBMARINE(2, 3),
    CRUISER(2   , 5),
    DESTROYER(1, 6),
    BATTLESHIP(1, 8);

    private final int numShips;
    private final int shipLength;

    ShipType(int numShips, int shipLength) {
        this.numShips = numShips;
        this.shipLength = shipLength;
    }

    public int getShipLength() {
        return shipLength;
    }

    public int getNumShips() {
        return numShips;
    }

    public static int lengthAllShips() {
        int sum = 0;
        for (ShipType type : ShipType.values()) sum += type.shipLength * type.numShips;
        return sum;
    }

    public static int sizeAllShips() {
        int sum = 0;
        for (ShipType type : ShipType.values()) sum += type.numShips;
        return sum;
    }

    public static String toShipName(ShipType type) {
        return switch (type) {
            case BATTLESHIP -> "Battleship";
            case DESTROYER -> "Destroyer";
            case CRUISER -> "Cruiser";
            case SUBMARINE -> "Submarine";
        };
    }
}

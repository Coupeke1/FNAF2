package be.fnaf2.view.gridplacement;

import javafx.stage.Stage;

import java.util.Random;

public class EnemyGrid extends Gridview {
    public EnemyGrid(Stage stage) {
        super(stage);
    }

    public void placeShips() {
        // Automatically place ships on the grid
        Random random = new Random();
        for (ShipType shipType : ShipType.values()) {
            int failedAttempts = 0;
            while (shipType.getAvailable() > 0) {
                int x = random.nextInt(NUM_COLS);
                int y = random.nextInt(NUM_ROWS);
                boolean horizontal = random.nextBoolean();

                if (canPlaceShip(shipType, x, y, horizontal)) {
                    this.placeShip(shipType, x, y, horizontal);
                    shipType.decrementAvailable(); // Decrement the available count
                    failedAttempts = 0; // Reset the failed attempts counter
                } else {
                    failedAttempts++; // Increment the failed attempts counter
                    System.out.println("Failed attempt #" + failedAttempts + " to place " + shipType + " at coordinates (" + x + ", " + y + ")");
                    if (failedAttempts > 1000) { // If there are too many failed attempts, break the loop
                        break;
                    }
                }
            }
        }
    }


}
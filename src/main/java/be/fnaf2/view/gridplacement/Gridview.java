package be.fnaf2.view.gridplacement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Gridview {

    private static final int NUM_ROWS = 10;
    private static final int NUM_COLS = 10;
    private static final int CELL_SIZE = 50;

    private ChoiceBox<ShipType> shipTypeChoiceBox;

    public void showGrid(Stage primaryStage) {
        GridPane grid = new GridPane();

        // Clear the grid (remove all existing nodes)
        grid.getChildren().clear();

        // Create cells and add them to the grid
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Cell cell = new Cell(grid, row, col);
                grid.add(cell, col, row);

                // Debug statement
                System.out.println("Cell added at (" + col + ", " + row + ")");
            }
        }

        // Create a ChoiceBox for ShipType selection
        ObservableList<ShipType> shipTypeOptions = FXCollections.observableArrayList(ShipType.values());
        shipTypeChoiceBox = new ChoiceBox<>(shipTypeOptions);
        shipTypeChoiceBox.setValue(ShipType.SUBMARINE); // Set default value

        // Add event handler for ChoiceBox selection
        shipTypeChoiceBox.setOnAction(event -> {
            ShipType selectedShipType = shipTypeChoiceBox.getValue();
            // You can do something with the selected ship type if needed
            System.out.println("Selected ShipType: " + selectedShipType);
        });

        // Create a VBox to hold the ChoiceBox and the grid
        VBox vbox = new VBox(shipTypeChoiceBox, grid);

        Scene scene = new Scene(vbox, NUM_COLS * CELL_SIZE, (NUM_ROWS + 1) * CELL_SIZE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Interactive Grid");
        primaryStage.show();
    }

    private class Cell extends Rectangle {
        private int row;
        private int col;
        private boolean clicked = false;
        private GridPane grid;

        public Cell(GridPane grid, int row, int col) {
            super(CELL_SIZE, CELL_SIZE);
            this.row = row;
            this.col = col;
            this.grid = grid;
            setFill(Color.WHITE);
            setStroke(Color.BLACK);

            // Add event handler for mouse click
            setOnMouseClicked(event -> {
                ShipType selectedShipType = shipTypeChoiceBox.getValue();
                if (selectedShipType != null) {
                    colorShip(selectedShipType);

                    // Debug statement
                    System.out.println("Cell clicked at (" + col + ", " + row + ")");
                }
                event.consume(); // Consume the event to prevent it from being handled by other nodes
            });
        }

        private void colorShip(ShipType shipType) {
            int shipLength = shipType.getShipLength();

            // Check if there is enough space to color the entire ship and no overlapping ships
            if (col + shipLength <= NUM_COLS && !shipOverlaps(col, shipLength)) {
                // Color the cells based on the ship's length
                for (int i = 0; i < shipLength; i++) {
                    int targetCol = col + i;
                    int targetRow = row;

                    if (isValidCell(targetCol, targetRow) && !isCellOccupied(targetCol, targetRow)) {
                        Rectangle coloredCell = createColoredCell();
                        grid.add(coloredCell, targetCol, targetRow);
                    }
                }

                // Add borders around the ship
                addShipBorders(shipLength);

                // Reduce the number of available ships
                shipType.decrementNumShips();

                // Disable ship selection if all ships of this type are placed
                if (shipType.getNumShips() == 0) {
                    shipTypeChoiceBox.getItems().remove(shipType);
                    shipTypeChoiceBox.setValue(null);
                }

                // Remove the old cell
                grid.getChildren().remove(this);
            }
        }

        private boolean shipOverlaps(int startCol, int shipLength) {
            // Check if the cells to be colored overlap with existing ships
            for (int i = -1; i <= shipLength; i++) {
                int targetCol = col + i;

                for (int j = -1; j <= 1; j++) {
                    int targetRow = row + j;

                    if (isValidCell(targetCol, targetRow) && isCellOccupied(targetCol, targetRow)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean isValidCell(int col, int row) {
            return col >= 0 && col < NUM_COLS && row >= 0 && row < NUM_ROWS;
        }

        private boolean isCellOccupied(int col, int row) {
            return grid.getChildren().stream()
                    .anyMatch(node -> GridPane.getColumnIndex(node) != null &&
                            GridPane.getRowIndex(node) != null &&
                            GridPane.getColumnIndex(node) == col &&
                            GridPane.getRowIndex(node) == row);
        }

        private void addShipBorders(int shipLength) {
            for (int i = -1; i <= shipLength; i++) {
                int targetCol = col + i;

                if (targetCol >= 0 && targetCol < NUM_COLS) {
                    Rectangle upperBorder = createColoredCell();
                    upperBorder.setFill(Color.RED);
                    grid.add(upperBorder, targetCol, row - 1); // Add upper border

                    Rectangle lowerBorder = createColoredCell();
                    lowerBorder.setFill(Color.RED);
                    grid.add(lowerBorder, targetCol, row + 1); // Add lower border
                }

                if (i == -1 || i == shipLength) {
                    Rectangle leftBorder = createColoredCell();
                    leftBorder.setFill(Color.RED);
                    grid.add(leftBorder, col - 1, row); // Add left border

                    Rectangle rightBorder = createColoredCell();
                    rightBorder.setFill(Color.RED);
                    grid.add(rightBorder, col + shipLength, row); // Add right border
                }
            }
        }

        private Rectangle createColoredCell() {
            Rectangle coloredCell = new Rectangle(CELL_SIZE, CELL_SIZE);
            coloredCell.setFill(Color.GREEN); // Set the color where ships are placed to green
            return coloredCell;
        }
    }
}

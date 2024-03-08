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

        // Create cells and add them to the grid
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Cell cell = new Cell(grid, row, col);
                grid.add(cell, col, row);
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

    // Define the Cell class
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
                }
            });
        }

        private void colorShip(ShipType shipType) {
            int shipLength = shipType.getShipLength();

            // Check if there is enough space to color the entire ship
            if (col + shipLength <= NUM_COLS) {
                // Color the cells based on the ship's length
                for (int i = 0; i < shipLength; i++) {
                    grid.add(createColoredCell(), col + i, row);
                }
            }
        }

        private Rectangle createColoredCell() {
            Rectangle coloredCell = new Rectangle(CELL_SIZE, CELL_SIZE);
            coloredCell.setFill(Color.BLUE); // You can set the color as needed
            return coloredCell;
        }
    }
}

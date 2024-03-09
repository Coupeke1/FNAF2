package be.fnaf2.view.gridplacement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
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
    private GridPane grid = new GridPane();
    ChoiceBox<ShipType> shipTypeChoiceBox;

    public Gridview() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Cell cell = new Cell(col, row, this);
                grid.add(cell, col, row);
            }
        }
    }

    public void showGrid(Stage primaryStage) {
        ObservableList<ShipType> shipTypeOptions = FXCollections.observableArrayList(ShipType.values());
        shipTypeChoiceBox = new ChoiceBox<>(shipTypeOptions);
        shipTypeChoiceBox.setValue(ShipType.SUBMARINE);

        VBox vbox = new VBox(shipTypeChoiceBox, grid);
        Scene scene = new Scene(vbox, NUM_COLS * CELL_SIZE, (NUM_ROWS + 1) * CELL_SIZE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Interactive Grid");
        primaryStage.show();
    }

    public boolean placeShip(ShipType shipType, int x, int y) {
        if (shipType.getAvailable() <= 0 || !canPlaceShip(shipType, x, y)) {
            System.out.println("Cannot place ship: no ships available or location is invalid.");
            return false;
        }

        for (int i = x; i < x + shipType.getLength(); i++) {
            Cell cell = getCell(i, y);
            cell.setFill(Color.GREEN);
            cell.ship = shipType;
            updateNeighboringCells(cell, Color.RED); // Update de omliggende cellen met rode rand
        }

        shipType.decrementAvailable();
        updateChoiceBox();
        return true;
    }

    private boolean canPlaceShip(ShipType shipType, int startX, int startY) {
        if (startX + shipType.getLength() > NUM_COLS) return false;

        for (int x = startX; x < startX + shipType.getLength(); x++) {
            if (!isValidPlacement(x, startY)) return false;
        }

        return true;
    }

    private boolean isValidPlacement(int x, int y) {
        if (x < 0 || x >= NUM_COLS || y < 0 || y >= NUM_ROWS) return false;
        Cell cell = getCell(x, y);
        return cell.ship == null;
    }

    public Cell getCell(int x, int y) {
        for (Node node : grid.getChildren()) {
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y && node instanceof Cell) {
                return (Cell) node;
            }
        }
        return null;
    }

    private void updateChoiceBox() {
        ObservableList<ShipType> updatedShipTypes = FXCollections.observableArrayList();
        for (ShipType shipType : ShipType.values()) {
            if (shipType.getAvailable() > 0) {
                updatedShipTypes.add(shipType);
            }
        }
        shipTypeChoiceBox.setItems(updatedShipTypes);
        shipTypeChoiceBox.setValue(updatedShipTypes.isEmpty() ? null : updatedShipTypes.get(0));
    }

    private void updateNeighboringCells(Cell cell, Color color) {
        int x = cell.x;
        int y = cell.y;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (isValidPlacement(i, j)) {
                    Cell neighboringCell = getCell(i, j);
                    neighboringCell.setStroke(color);
                }
            }
        }
    }

    public class Cell extends Rectangle {
        public int x, y;
        public ShipType ship = null;

        public Cell(int x, int y, Gridview gridview) {
            super(CELL_SIZE, CELL_SIZE, Color.LIGHTGRAY);
            setStroke(Color.BLACK);
            this.x = x;
            this.y = y;

            setOnMouseClicked(event -> {
                ShipType selectedShipType = shipTypeChoiceBox.getValue();
                if (selectedShipType != null && selectedShipType.getAvailable() > 0) {
                    boolean placed = gridview.placeShip(selectedShipType, x, y);
                    if (placed) {
                        System.out.println("Schip geplaatst: " + selectedShipType + " op (" + x + ", " + y + ")");
                    } else {
                        System.out.println("Kan schip niet plaatsen op (" + x + ", " + y + ")");
                    }
                }
            });

            // Voeg een eventlistener toe om de stijl van de cel bij te werken wanneer de muis erover beweegt
            setOnMouseEntered(event -> {
                if (ship == null) {
                    updateCellStyle(Color.RED); // Rode rand rond de cel
                }
            });

            // Voeg een eventlistener toe om de celstijl te resetten wanneer de muis eruit beweegt
            setOnMouseExited(event -> {
                if (ship == null) {
                    updateCellStyle(Color.LIGHTGRAY); // Standaard kleur van de cel
                }
            });
        }

        // Methode om de stijl van de cel bij te werken
        private void updateCellStyle(Color color) {
            setFill(color);
            setStroke(color.equals(Color.RED) ? Color.RED : Color.BLACK);
        }
    }
}

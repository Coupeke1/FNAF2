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
    private ChoiceBox<ShipType> shipTypeChoiceBox;

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
        if (shipType.getAvailable() <= 0) {
            System.out.println("Geen schepen van dit type meer beschikbaar.");
            return false;
        }

        int length = shipType.getLength();
        if (x + length > NUM_COLS) return false;

        for (int i = x; i < x + length; i++) {
            Cell cell = getCell(i, y);
            if (cell != null && cell.ship == null) {
                cell.setFill(Color.GREEN);
                cell.ship = shipType;
            } else {
                return false;
            }
        }

        shipType.decrementAvailable();
        updateChoiceBox();
        return true;
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
        if (!updatedShipTypes.isEmpty()) {
            shipTypeChoiceBox.setValue(updatedShipTypes.get(0));
        } else {
            shipTypeChoiceBox.setValue(null);
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
        }
    }
}

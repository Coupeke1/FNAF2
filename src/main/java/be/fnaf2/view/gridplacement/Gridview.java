package be.fnaf2.view.gridplacement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Gridview {

    private boolean enemy = false;
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

        scene.setOnMouseClicked(event -> {
            ShipType selectedShipType = shipTypeChoiceBox.getValue();
            int x = (int) (event.getX() / CELL_SIZE);
            int y = (int) (event.getY() / CELL_SIZE);

            if (event.getButton() == MouseButton.PRIMARY) {
                // Left-click: Place ship horizontally
                placeShip(selectedShipType, x, y, true);
            } else if (event.getButton() == MouseButton.SECONDARY) {
                // Right-click: Place ship vertically
                placeShip(selectedShipType, x, y, false);
            }
        });

        primaryStage.show();
    }

    private void placeShip(ShipType shipType, int x, int y, boolean horizontal) {
        try {
            if (canPlaceShip(shipType, x, y, horizontal)) {
                int length = shipType.getLength();

                if (horizontal) {
                    for (int i = x; i < x + length; i++) {
                        Cell cell = getCell(i, y);
                        cell.ship = shipType;
                        if (!enemy) {
                            cell.setFill(Color.WHITE);
                            cell.setStroke(Color.GREEN);
                        }
                    }
                } else {
                    for (int i = y; i < y + length; i++) {
                        Cell cell = getCell(x, i);
                        cell.ship = shipType;
                        if (!enemy) {
                            cell.setFill(Color.WHITE);
                            cell.setStroke(Color.GREEN);
                        }
                    }
                }

                shipType.decrementAvailable();
                updateChoiceBox();

                ShipType nextShipType = getNextAvailableShipType();
                if (nextShipType != null) {
                    shipTypeChoiceBox.setValue(nextShipType);
                }
            } else {
                System.out.println("Kan schip niet plaatsen. Geen beschikbare schepen meer.");
            }
        } catch (NullPointerException e) {
            System.out.println("Kan schip niet plaatsen. Geen beschikbare schepen meer.");
            e.printStackTrace();
        }
    }

    private boolean canPlaceShip(ShipType shipType, int x, int y, boolean horizontal) {
        int length = shipType.getLength();

        if (horizontal) {
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y) || getCell(i, y).ship != null)
                    return false;

                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isValidPoint((int) neighbor.getX(), (int) neighbor.getY()) || neighbor.ship != null)
                        return false;
                }
            }
        } else {
            for (int i = y; i < y + length; i++) {
                if (!isValidPoint(x, i) || getCell(x, i).ship != null)
                    return false;

                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isValidPoint((int) neighbor.getX(), (int) neighbor.getY()) || neighbor.ship != null)
                        return false;
                }
            }
        }

        return true;
    }

    private ShipType getNextAvailableShipType() {
        for (ShipType shipType : ShipType.values()) {
            if (shipType.getAvailable() > 0) {
                return shipType;
            }
        }
        return null;
    }

    private Cell[] getNeighbors(int x, int y) {
        Point2D[] points = new Point2D[]{
                new Point2D(x - 1, y),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
                new Point2D(x, y + 1)
        };

        List<Cell> neighbors = new ArrayList<>();

        for (Point2D p : points) {
            if (isValidPoint(p)) {
                neighbors.add(getCell((int) p.getX(), (int) p.getY()));
            }
        }

        return neighbors.toArray(new Cell[0]);
    }

    private Cell getCell(int x, int y) {
        for (Node node : grid.getChildren()) {
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y && node instanceof Cell) {
                return (Cell) node;
            }
        }
        return null;
    }

    private boolean isValidPoint(int x, int y) {
        return x >= 0 && x < NUM_COLS && y >= 0 && y < NUM_ROWS;
    }

    private boolean isValidPoint(Point2D point) {
        return isValidPoint((int) point.getX(), (int) point.getY());
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

    public class Cell extends Rectangle {
        public int x, y;
        public ShipType ship = null;

        public Cell(int x, int y, Gridview gridview) {
            super(CELL_SIZE, CELL_SIZE, Color.LIGHTGRAY);
            setStroke(Color.BLACK);
            this.x = x;
            this.y = y;

            setOnMouseEntered(event -> {
                if (ship == null) {
                    updateCellStyle(Color.RED);
                }
            });

            setOnMouseExited(event -> {
                if (ship == null) {
                    updateCellStyle(Color.LIGHTGRAY);
                }
            });
        }

        private void updateCellStyle(Color color) {
            setFill(color);
            setStroke(color.equals(Color.RED) ? Color.RED : Color.BLACK);
        }
    }
}

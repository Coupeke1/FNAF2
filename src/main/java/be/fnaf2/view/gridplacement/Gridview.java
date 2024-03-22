package be.fnaf2.view.gridplacement;

import be.fnaf2.model.GridModel;
import be.fnaf2.model.HoofdgameModel;
import be.fnaf2.view.hoofdgame.HoofdgamePresenter;
import be.fnaf2.view.hoofdgame.HoofdgameView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class Gridview extends GridPane {

    protected static int NUM_ROWS = 10;
    protected static int NUM_COLS = 10;
    private static final int CELL_SIZE = 50;
    private static int NUM_SHIPS = 5;
    private boolean enemy = false;
    private ChoiceBox<ShipType> shipTypeChoiceBox;
    private Button undoButton;
    private Button clearButton;
    private Button nextButton;
    private Stack<Ship> placedShips = new Stack<>();
    private HoofdgameView hoofdgameView;
    private boolean wasShot = false;
    private int ships = 0; // Add this line


    public Gridview(Stage stage, HoofdgameView hoofdgameView) {

        this.hoofdgameView = hoofdgameView;
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Cell cell = new Cell(col, row, this);
                this.add(cell, col, row);
            }
        }

        showGrid(stage);
    }


    public void showGrid(Stage stage) {
        shipTypeChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(ShipType.values()));
        shipTypeChoiceBox.setValue(ShipType.SUBMARINE);

        undoButton = new Button("Undo");
        undoButton.setMinSize(100, 20);
        undoButton.setOnAction(event -> undoLastShip());

        clearButton = new Button("Clear");
        clearButton.setMinSize(100, 20);
        clearButton.setOnAction(event -> clearGrid());

        nextButton = new Button("Continue");
        nextButton.setMinSize(100, 20);
        // Removed the automatic transition to HoofdGameview
        VBox vbox = new VBox(15, shipTypeChoiceBox, undoButton, clearButton, this);
        vbox.setMinSize(NUM_COLS * CELL_SIZE, (NUM_ROWS + 4) * CELL_SIZE); // Increase the size of the VBox
        Scene scene = new Scene(vbox, NUM_COLS * CELL_SIZE, (NUM_ROWS + 4) * CELL_SIZE); // Increase the size of the Scene

        this.setOnMouseClicked(event -> {
            ShipType selectedShipType = shipTypeChoiceBox.getValue();
            int x = (int) (event.getX() / CELL_SIZE);
            int y = (int) (event.getY() / CELL_SIZE);

            if (event.getButton() == MouseButton.PRIMARY) {
                placeShip(selectedShipType, x, y, true);
            } else if (event.getButton() == MouseButton.SECONDARY) {
                placeShip(selectedShipType, x, y, false);
            }
        });

        stage.setScene(scene);
        stage.show();
    }
    public void hideSpecialColors() {
        for (Node node : this.getChildren()) {
            if (node instanceof Cell) {
                ((Cell) node).hideSpecialColors();
            }
        }
    }
    public static void setNumRows(int numRows) {
        NUM_ROWS = numRows;
        // Update grid layout if necessary
    }

    public static void setNumCols(int numCols) {
        NUM_COLS = numCols;
        // Update grid layout if necessary
    }

    public static void setNumShips(int numShips) {
        NUM_SHIPS = numShips;
        // Update number of ships if necessary
    }
    public void enableShooting(HoofdgamePresenter presenter) {
        for (Node node : this.getChildren()) {
            if (node instanceof Cell) {
                node.setOnMouseClicked(event -> {
                    Cell clickedCell = (Cell) node;
                    presenter.handleShot(clickedCell.x, clickedCell.y);
                });
            }
        }
    }

    void placeShip(ShipType shipType, int x, int y, boolean horizontal) {
        try {
            if (canPlaceShip(shipType, x, y, horizontal)) {
                int length = shipType.getLength();

                List<Cell> shipCells = new ArrayList<>();
                if (horizontal) {
                    for (int i = x; i < x + length; i++) {
                        Cell cell = getCell(i, y);
                        assert cell != null;
                        cell.ship = shipType;
                        shipCells.add(cell);
                        if (!enemy) {
                            cell.setFill(Color.WHITE);
                            cell.setStroke(Color.RED);
                        }
                    }
                } else {
                    for (int i = y; i < y + length; i++) {
                        Cell cell = getCell(x, i);
                        assert cell != null;
                        cell.ship = shipType;
                        shipCells.add(cell);
                        if (!enemy) {
                            cell.setFill(Color.WHITE);
                            cell.setStroke(Color.RED);
                        }
                    }
                }

                placedShips.push(new Ship(shipType, shipCells));

                shipType.decrementAvailable();
                updateChoiceBox();

                ShipType nextShipType = getNextAvailableShipType();
                if (nextShipType != null) {
                    shipTypeChoiceBox.setValue(nextShipType);
                }
            } else {
                System.out.println("Kan schip niet plaatsen. ligt naast een ander schip.");
            }
        } catch (NullPointerException e) {
            System.out.println("Kan schip niet plaatsen. Geen beschikbare schepen meer.");

        }
    }
    public boolean isEnemy() {
        return enemy;
    }


    public void switchPlayer() {
        hoofdgameView.getPresenter().switchPlayer();
    }




    boolean canPlaceShip(ShipType shipType, int x, int y, boolean horizontal) {
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
    public void enableShipPlacement(HoofdgamePresenter presenter) {
        // Implement the logic to enable ship placement
    }

    public void disableShipPlacement() {
        // Implement the logic to disable ship placement
    }



    private ShipType getNextAvailableShipType() {
        for (ShipType shipType : ShipType.values()) {
            if (shipType.getAvailable() > 0) {
                return shipType;
            }
        }
        return null;
    }
    private void handleShot(int x, int y) {
        if (isValidPoint(x, y)) {
            HoofdgamePresenter presenter = hoofdgameView.getPresenter();
            presenter.handleShot(x, y);
            presenter.switchPlayer(); // Switch turn after handling the shot
        }
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



    public Cell getCell(int x, int y) {
        for (javafx.scene.Node node : this.getChildren()) {
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

    public ChoiceBox<ShipType> getShipTypeChoiceBox() {
        return shipTypeChoiceBox;
    }

    public Button getUndoButton() {
        return undoButton;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    private void undoLastShip() {
        if (!placedShips.isEmpty()) {
            Ship lastPlacedShip = placedShips.pop();
            for (Cell cell : lastPlacedShip.getCells()) {
                cell.ship = null;
                cell.setFill(Color.LIGHTGRAY);
                cell.setStroke(Color.BLACK);
            }
            lastPlacedShip.getShipType().incrementAvailable();
            updateChoiceBox();
        }
    }

    private void clearGrid() {
        while (!placedShips.isEmpty()) {
            undoLastShip();
        }
        resetShipAvailability();
        updateChoiceBox();
    }

    private void resetShipAvailability() {
        for (ShipType shipType : ShipType.values()) {
            shipType.resetAvailable();
        }
    }


    public class Ship {
        private ShipType shipType;
        private List<Cell> cells;
        private int health;

        public Ship(ShipType shipType, List<Cell> cells) {
            this.shipType = shipType;
            this.cells = cells;
            this.health = shipType.getLength();
        }

        public ShipType getShipType() {
            return shipType;
        }

        public List<Cell> getCells() {
            return cells;
        }

        public void hit() {
            health--;
        }

        public boolean isAlive() {
            return health > 0;
        }
    }

    public class Cell extends Rectangle {
        public int x, y;
        public ShipType ship = null;
        private Gridview board;
        public Ship ships;

        public Cell(int x, int y, Gridview board) {
            super(CELL_SIZE, CELL_SIZE, Color.LIGHTBLUE);
            setStroke(Color.BLACK);
            this.x = x;
            this.y = y;
            this.board = board;

            setOnMouseEntered(event -> {
                if (ship == null) {
                    updateCellStyle(Color.RED);
                }
            });

            setOnMouseExited(event -> {
                if (ship == null) {
                    updateCellStyle(Color.LIGHTBLUE);
                }
            });

            setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY && board.isEnemy()) {
                    handleShot();
                }
            });
        }

        private void handleShot() {
            if (!wasShot) {
                boolean hit = shoot();
                if (hit) {
                    board.handleShot(x, y);
                } else {
                    setFill(Color.BLUE); // Change color to indicate a miss
                    board.switchPlayer();
                }
            }
        }

        public boolean shoot() {
            wasShot = true;
            setFill(Color.BLACK);

            if (ship != null) {
                ships.hit();
                setFill(Color.RED);
                if (!ships.isAlive()) {
                    board.ships--;
                }
                return true;
            }

            return false;
        }

        private void updateCellStyle(Color color) {
            setFill(color);
            setStroke(color.equals(Color.RED) ? Color.RED : Color.BLACK);
        }

        public void hideSpecialColors() {
            setFill(Color.LIGHTBLUE);
            setStroke(Color.BLACK);
        }
    }
}
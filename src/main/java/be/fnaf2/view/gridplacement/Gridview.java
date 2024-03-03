package be.fnaf2.view.gridplacement;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Gridview {

    private static final int NUM_ROWS = 10;
    private static final int NUM_COLS = 10;
    private static final int CELL_SIZE = 50;

    public void showGrid(Stage primaryStage) {
        GridPane grid = new GridPane();

        // Create cells and add them to the grid
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Cell cell = new Cell(row, col);
                grid.add(cell, col, row);
            }
        }

        Scene scene = new Scene(grid, NUM_COLS * CELL_SIZE, NUM_ROWS * CELL_SIZE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Interactive Grid");
        primaryStage.show();
    }

    // Define the Cell class
    private class Cell extends Rectangle {
        private int row;
        private int col;
        private boolean clicked = false;

        public Cell(int row, int col) {
            super(CELL_SIZE, CELL_SIZE);
            this.row = row;
            this.col = col;
            setFill(Color.WHITE);
            setStroke(Color.BLACK);

            // Add event handler for mouse click
            setOnMouseClicked(event -> {
                if (!clicked) {
                    setFill(Color.GRAY);
                    clicked = true;
                } else {
                    setFill(Color.WHITE);
                    clicked = false;
                }
            });
        }
    }
}

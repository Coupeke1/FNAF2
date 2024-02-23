package be.fnaf2.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Gridview extends Application {

    private static final int NUM_ROWS = 10;
    private static final int NUM_COLS = 10;
    private static final int CELL_SIZE = 50;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();

        // Maak cellen en voeg ze toe aan het raster
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

    // Definieer de Cell klasse
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

            // Voeg event handler toe voor muisklik
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
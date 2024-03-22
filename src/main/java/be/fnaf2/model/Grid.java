package be.fnaf2.model;

public class Grid {
    private Cell[][] cells;

    public Grid() {
        cells = new Cell[10][10]; // Assuming a 10x10 grid
        // Initialize cells
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public int getWidth() {
        return cells.length;
    }

    public int getHeight() {
        return cells[0].length;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }
}
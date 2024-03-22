package be.fnaf2.model;

import be.fnaf2.view.hoofdgame.HoofdgamePresenter;

public class HoofdgameModel {
    private Grid grid;
    private boolean isPlayerOneTurn;
    private HoofdgamePresenter presenter; // New field


    public HoofdgameModel(HoofdgamePresenter presenter) {
        this.grid = new Grid();
        this.isPlayerOneTurn = true;
        this.presenter = presenter; // Initialize the new field
    }
    public boolean isValidPoint(int x, int y) {
        return x >= 0 && x < grid.getWidth() && y >= 0 && y < grid.getHeight();
    }

    public void updateCell(int x, int y) {
        grid.getCell(x, y).updateState();
    }

    public void switchPlayer() {
        isPlayerOneTurn = !isPlayerOneTurn;
    }
}
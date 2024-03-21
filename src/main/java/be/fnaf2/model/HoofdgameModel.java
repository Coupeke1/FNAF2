package be.fnaf2.model;

import be.fnaf2.view.gridplacement.Gridview;
import be.fnaf2.view.hoofdgame.HoofdgamePresenter;
import javafx.scene.paint.Color;

public class HoofdgameModel {
    private int currentPlayer;
    private HoofdgamePresenter presenter;

    public HoofdgameModel(HoofdgamePresenter presenter) {
        this.presenter = presenter;
        currentPlayer = 1; // Player 1 starts
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1; // Switch between player 1 and 2
    }

    public void handleShot(int x, int y) {
        presenter.handleShot(x, y);

        int currentPlayer = this.currentPlayer;

        boolean hit = checkShotHit(x, y, currentPlayer);

        updateCellColor(x, y, hit);

        presenter.switchPlayer();
    }

    private boolean checkShotHit(int x, int y, int currentPlayer) {
        return true;
    }

    private void updateCellColor(int x, int y, boolean hit) {
        Gridview.Cell cell = presenter.getCell(x, y); // Call 'getCell' through 'presenter'
        if (cell != null) {
            if (hit) {
                cell.setFill(Color.RED); // Change color to indicate a hit
            } else {
                cell.setFill(Color.WHITE); // Change color to indicate a miss
            }
        }
    }
}
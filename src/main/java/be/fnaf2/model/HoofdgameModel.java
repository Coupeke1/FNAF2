// In HoofdgameModel.java
package be.fnaf2.model;

import be.fnaf2.view.hoofdgame.HoofdgamePresenter;

public class HoofdgameModel {
    public enum GameState {
        SHIP_PLACEMENT,
        SHOOTING
    }
    private Grid grid;
    private boolean isPlayerOneTurn;
    private HoofdgamePresenter presenter;

    private GameState gameState;

    public HoofdgameModel(HoofdgamePresenter presenter) {
        this.grid = new Grid();
        this.isPlayerOneTurn = true;
        this.presenter = presenter;
        this.gameState = GameState.SHIP_PLACEMENT;
    }

    public boolean isShootingPhase() {
        return gameState == GameState.SHOOTING;
    }

    public void startShootingPhase() {
        gameState = GameState.SHOOTING;
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
    public boolean checkCell(int x, int y) {
        return grid.getCell(x, y).containsShip();
    }
}
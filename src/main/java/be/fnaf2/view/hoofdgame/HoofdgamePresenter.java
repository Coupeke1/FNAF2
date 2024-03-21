package be.fnaf2.view.hoofdgame;

public class HoofdgamePresenter {
    private boolean isPlayerTurn1 = true;


    public void switchTurn() {
        isPlayerTurn1 = !isPlayerTurn1;
    }

    public boolean isPlayerTurn1() {
        return isPlayerTurn1;
    }
}

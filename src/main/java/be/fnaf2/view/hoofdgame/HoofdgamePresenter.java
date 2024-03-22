package be.fnaf2.view.hoofdgame;

import be.fnaf2.model.HoofdgameModel;

public class HoofdgamePresenter {
    private HoofdgameModel model;
    private HoofdgameView view;

    public HoofdgamePresenter(HoofdgameModel model, HoofdgameView view) {
        this.model = model;
        this.view = view;
        initView();
    }

    private void initView() {
        // Initialize your view here, if needed
    }

    // In HoofdgamePresenter.java
    public void handleShot(int x, int y) {
        if (!model.isShootingPhase()) {
            return;
        }
        if (model.isValidPoint(x, y)) {
            model.updateCell(x, y);
        }
    }

    public void startShootingPhase() {
        // Implement the logic to start the shooting phase
    }

    public void switchPlayer() {
        model.switchPlayer();
        // Update the view to reflect the current player's turn
    }
}
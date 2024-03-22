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

    public void handleShot(int x, int y) {
        // Handle the shot at the given location
        // If the location is not valid, do nothing
        if (model.isValidPoint(x, y)) {
            model.updateCell(x, y);
        }
    }

    public void switchPlayer() {
        model.switchPlayer();
        // Update the view to reflect the current player's turn
    }
}
package be.fnaf2.view.hoofdgame;

import be.fnaf2.model.HoofdgameModel;
import be.fnaf2.view.gridplacement.Gridview;

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
    public Gridview.Cell getCell(int x, int y) {
        return view.getPresenter().getCell(x, y);
    }
    public void handleShot(int x, int y) {
        model.handleShot(x, y);
        // Update the view accordingly, e.g., showing the result of the shot
        // You may also check if the game is over and handle that scenario
    }

    public void switchPlayer() {
        model.switchPlayer();
        // Update the view to reflect the current player's turn
    }
}

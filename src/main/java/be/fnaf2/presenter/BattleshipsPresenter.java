package be.fnaf2.presenter;


import be.fnaf2.model.BattleshipsModel;
import be.fnaf2.view.BattleshipsView;

public class BattleshipsPresenter {
    private final BattleshipsModel model;
    private final BattleshipsView view;

    public BattleshipsPresenter(BattleshipsModel model, BattleshipsView view) {
        this.model = model;
        this.view = view;

        // Set up event handlers or other presenter logic

    }

    public void setView(BattleshipsView view) {
    }

    // Add presenter logic if needed
}
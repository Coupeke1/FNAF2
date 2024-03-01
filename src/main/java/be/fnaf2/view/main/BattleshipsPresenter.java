package be.fnaf2.view.main;

import be.fnaf2.model.BattleshipsModel;

public class BattleshipsPresenter {
    private final BattleshipsModel model;
    private final BattleshipsView view;

    public BattleshipsPresenter(BattleshipsModel model) {
        this.model = model;
        this.view = new BattleshipsView(this);
    }


}
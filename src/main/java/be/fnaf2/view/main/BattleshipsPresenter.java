package be.fnaf2.view.main;


import be.fnaf2.model.BattleshipsModel;
import be.fnaf2.view.main.BattleshipsView;

public class BattleshipsPresenter {
    private final BattleshipsModel model;
    private final BattleshipsView view;

    public BattleshipsPresenter(BattleshipsModel model, BattleshipsView view) {
        this.model = model;
        this.view = view;


    }

    public void setView(BattleshipsView view) {
    }

}
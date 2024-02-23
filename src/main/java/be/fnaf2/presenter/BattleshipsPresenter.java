package be.fnaf2.presenter;


import be.fnaf2.model.BattleshipsModel;
import be.fnaf2.view.BattleshipsView;

public class BattleshipsPresenter {
    private final BattleshipsModel model;
    private BattleshipsView view;

    public BattleshipsPresenter(BattleshipsModel model, BattleshipsView view) {
        this.model = model;
        this.view = view;
        // Voeg eventuele initialisatielogica hier toe
    }
    public void setView(BattleshipsView view) {
        this.view = view;
    }

    // Voeg eventuele presentatielogica hier toe
}
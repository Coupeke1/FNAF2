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
        view.getSingleplayerButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Handle singleplayer button click
            }
        });

        view.getMultiplayerButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Handle multiplayer button click
            }
        });
    }

    // Add presenter logic if needed
}
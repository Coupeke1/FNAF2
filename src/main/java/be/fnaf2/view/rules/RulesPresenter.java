package be.fnaf2.view.rules;

import be.fnaf2.Exceptions.ButtonInitializationException;
import be.fnaf2.view.main.BattleshipsPresenter;
import be.fnaf2.view.main.BattleshipsView;
import be.fnaf2.view.rules.RulesView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RulesPresenter {
        private final RulesView view;

        public RulesPresenter(RulesView view) {
            this.view = view;
            initialize();
        }
    private void initialize() {
        // Voeg een actie toe aan de terugknop
        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Roep de methode aan om terug te keren naar het hoofdscherm
                try {
                    switchToBattleshipsView();
                } catch (ButtonInitializationException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void switchToBattleshipsView() throws ButtonInitializationException {
        // Maak een nieuw hoofdscherm aan
        BattleshipsView battleshipsView = new BattleshipsView();
        BattleshipsPresenter battleshipsPresenter = new BattleshipsPresenter(battleshipsView);

        // Verander de root van het huidige scene naar het hoofdscherm
        if (view.getScene() != null) {
            view.getScene().setRoot(battleshipsView);
        } else {
            throw new ButtonInitializationException("Return button not initizalized");
        }
    }
}


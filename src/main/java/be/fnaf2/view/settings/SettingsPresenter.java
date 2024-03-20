// SettingsPresenter.java
package be.fnaf2.view.settings;

import be.fnaf2.Exceptions.ButtonInitializationException;
import be.fnaf2.view.main.BattleshipsPresenter;
import be.fnaf2.view.main.BattleshipsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SettingsPresenter {
    private final SettingsView view;

    public SettingsPresenter(SettingsView view) {
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

    // Methode om terug te keren naar het hoofdscherm
    private void switchToBattleshipsView() {
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

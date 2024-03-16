// BattleshipsPresenter.java
package be.fnaf2.view.main;

import be.fnaf2.Exceptions.ButtonInitializationException;
import be.fnaf2.model.GridModel;
import be.fnaf2.view.settings.SettingsPresenter;
import be.fnaf2.view.gridplacement.GridPresenter;
import be.fnaf2.view.gridplacement.Gridview;
import be.fnaf2.view.settings.SettingsView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BattleshipsPresenter {
    private final BattleshipsView view;

    public BattleshipsPresenter(BattleshipsView view) {
        this.view = view;
        initialize();
    }

    private void initialize() {
        setButtonAction(view.getSingleplayerButton(), "Singleplayer");
        setButtonAction(view.getMultiplayerButton(), "Multiplayer");
        setButtonAction(view.getSettingButton(), "Settings");
    }

    private void setButtonAction(Button button, String gameMode) {
        if (button != null) {
            button.setOnAction(event -> showConfirmationAlert(gameMode));
        } else {
            throw new ButtonInitializationException(gameMode + " Button Error");
        }
    }

    private void showConfirmationAlert(String gameMode) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Switching to " + gameMode + " mode");
        alert.setContentText("Are you sure you want to switch to " + gameMode + " mode?");

        alert.showAndWait().ifPresent(response -> {
            if (gameMode.equalsIgnoreCase("settings")) {
                switchToSettingsView();
            } else if (response == ButtonType.OK) {
                switchToGameView();
            }
        });
    }

    private void switchToSettingsView() {
        SettingsView settingsView = new SettingsView();
        new SettingsPresenter(settingsView);
        view.getScene().setRoot(settingsView);
    }

    private void switchToGameView() {
        Stage stage = (Stage) view.getScene().getWindow();
        Gridview gridview = new Gridview(stage);
        new GridPresenter(new GridModel(), gridview);
        HBox hbox = new HBox(gridview.getShipTypeChoiceBox(), gridview.getClearButton(), gridview.getUndoButton());
        hbox.setSpacing(10);
        // To add some space between the buttons
        VBox vbox = new VBox(hbox, gridview);
        vbox.setSpacing(5);// VBox to arrange the buttons and the Gridview vertically
        stage.setScene(new Scene(vbox));
    }
}
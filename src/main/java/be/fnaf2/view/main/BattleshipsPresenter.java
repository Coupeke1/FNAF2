// BattleshipsPresenter.java
package be.fnaf2.view.main;

import be.fnaf2.Exceptions.ButtonActionException;
import be.fnaf2.Exceptions.ButtonInitializationException;
import be.fnaf2.model.GridModel;
import be.fnaf2.view.rules.RulesPresenter;
import be.fnaf2.view.rules.RulesView;
import be.fnaf2.view.settings.SettingsPresenter;
import be.fnaf2.view.gridplacement.GridPresenter;
import be.fnaf2.view.gridplacement.Gridview;
import be.fnaf2.view.settings.SettingsView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
        setButtonAction(view.getRulesButton(), "Rules");
    }

    private void setButtonAction(Button button, String gameMode) {
        if (button != null) {
            button.setOnAction(event -> {
                try {
                    handleButtonAction(button, gameMode);
                } catch (ButtonActionException e) {
                    e.printStackTrace();
                }
            });
        } else {
            throw new ButtonInitializationException(gameMode + " Button Error");
        }
    }

    private void handleButtonAction(Button button, String gameMode) throws ButtonActionException {
        switch (gameMode.toLowerCase()) {
            case "rules":
                switchToRulesView();
                break;
            case "settings":
                switchToSettingsView();
                break;
            case "multiplayer":
                switchToMultiPlayerView();
                break;
            case "singleplayer":
                switchToSinglePlayerView();
                break;
            default:
                throw new ButtonActionException("Unsupported game mode: " + gameMode);
        }
    }

    private void switchToSettingsView() {
        SettingsView settingsView = new SettingsView();
        new SettingsPresenter(settingsView);
        view.getScene().setRoot(settingsView);
    }

    private void switchToRulesView() {
        RulesView rulesView = new RulesView();
        new RulesPresenter(rulesView);
        view.getScene().setRoot(rulesView);
    }

    private void switchToSinglePlayerView() {
        Stage stage = (Stage) view.getScene().getWindow();
        Gridview gridview = new Gridview(stage);
        new GridPresenter(new GridModel(), gridview);
        HBox hbox = new HBox(gridview.getShipTypeChoiceBox(), gridview.getClearButton(), gridview.getUndoButton(), gridview.getNextButton());
        hbox.setSpacing(10);
        VBox vbox = new VBox(hbox, gridview);
        vbox.setSpacing(5);

        // Bevestigingsmelding voor het schakelen naar de singleplayer-modus
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Switching to Singleplayer mode");
        alert.setContentText("Are you sure you want to switch to Singleplayer mode?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                stage.setScene(new Scene(vbox));
            }
        });
    }

    private void switchToMultiPlayerView() {
        Stage stage = (Stage) view.getScene().getWindow();
        Gridview gridviewP1 = new Gridview(stage);
        Gridview gridviewP2 = new Gridview(stage);
        new GridPresenter(new GridModel(), gridviewP1);
        HBox hboxP1 = new HBox(gridviewP1.getShipTypeChoiceBox(), gridviewP1.getClearButton(), gridviewP1.getUndoButton(), gridviewP1.getNextButton());
        hboxP1.setSpacing(10);
        VBox vboxP1 = new VBox(hboxP1, gridviewP1);
        vboxP1.setSpacing(5);

        new GridPresenter(new GridModel(), gridviewP2);
        HBox hboxP2 = new HBox(gridviewP2.getShipTypeChoiceBox(), gridviewP2.getClearButton(), gridviewP2.getUndoButton(), gridviewP2.getNextButton());
        hboxP2.setSpacing(10);
        VBox vboxP2 = new VBox(hboxP2, gridviewP2);
        vboxP2.setSpacing(5);
        vboxP2.setVisible(false);

        gridviewP1.getNextButton().setOnAction(event -> {
            GridPresenter presenterP1 = new GridPresenter(new GridModel(), gridviewP1);
            if (presenterP1.allShipsPlaced()) {
                vboxP1.setVisible(false);
                vboxP2.setVisible(true);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Not all ships are placed");
                alert.setContentText("Please place all ships before proceeding to the next player.");

                alert.showAndWait();
            }
        });

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Switching to Multiplayer mode");
        alert.setContentText("Are you sure you want to switch to Multiplayer mode?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                VBox vbox = new VBox(vboxP1, vboxP2);
                stage.setScene(new Scene(vbox));
                stage.show();
            }
        });
    }
}

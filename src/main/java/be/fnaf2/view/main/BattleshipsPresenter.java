// BattleshipsPresenter.java
package be.fnaf2.view.main;

import be.fnaf2.Exceptions.ButtonInitializationException;
import be.fnaf2.model.GridModel;
import be.fnaf2.view.rules.RulesPresenter;
import be.fnaf2.view.rules.RulesView;
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
        setButtonAction(view.getRulesButton(), "rules");
    }

    private void setButtonAction(Button button, String gameMode) {
        if (button != null) {
            button.setOnAction(event -> {
                if (gameMode.equalsIgnoreCase("rules")) {
                    switchToRulesView(); // Schakel over naar de RulesView als de Rules-knop wordt ingedrukt
                } else {
                    showConfirmationAlert(gameMode);
                }
            });
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
            } else if (gameMode.equalsIgnoreCase("multiplayer")) {
                switchToMultiPlayerView();
            }else if (gameMode.equalsIgnoreCase("singleplayer")) {
                switchToSinglePlayerView();
            }
        });
    }

    private void switchToSettingsView() {
        SettingsView settingsView = new SettingsView();
        new SettingsPresenter(settingsView);
        view.getScene().setRoot(settingsView);
    }
    private void switchToRulesView(){
        RulesView rulesView = new RulesView();
        new RulesPresenter(rulesView);
        view.getScene().setRoot(rulesView);
    }

    private void switchToSinglePlayerView() {
        Stage stage = (Stage) view.getScene().getWindow();
        Gridview gridview = new Gridview(stage);
        new GridPresenter(new GridModel(), gridview);
        HBox hbox = new HBox(gridview.getShipTypeChoiceBox(), gridview.getClearButton(), gridview.getUndoButton(),gridview.getNextButton());
        hbox.setSpacing(10);
        // To add some space between the buttons
        VBox vbox = new VBox(hbox, gridview);
        vbox.setSpacing(5);// VBox to arrange the buttons and the Gridview vertically
        stage.setScene(new Scene(vbox));
    }
    private void switchToMultiPlayerView() {
        Stage stage = (Stage) view.getScene().getWindow();
        Gridview gridviewP1 = new Gridview(stage);
        Gridview gridviewP2 = new Gridview(stage);
        new GridPresenter(new GridModel(), gridviewP1);
        HBox hboxP1 = new HBox(gridviewP1.getShipTypeChoiceBox(), gridviewP1.getClearButton(), gridviewP1.getUndoButton(),gridviewP1.getNextButton());
        hboxP1.setSpacing(10);
        VBox vboxP1 = new VBox(hboxP1, gridviewP1);
        vboxP1.setSpacing(5);

        new GridPresenter(new GridModel(), gridviewP2);
        HBox hboxP2 = new HBox(gridviewP2.getShipTypeChoiceBox(), gridviewP2.getClearButton(), gridviewP2.getUndoButton(),gridviewP2.getNextButton());
        hboxP2.setSpacing(10);
        VBox vboxP2 = new VBox(hboxP2, gridviewP2);
        vboxP2.setSpacing(5);
        vboxP2.setVisible(false); // Initially, the Gridview for Player2 is not visible

        // In BattleshipsPresenter.java
        gridviewP1.getNextButton().setOnAction(event -> {
            GridPresenter presenterP1 = new GridPresenter(new GridModel(), gridviewP1);
            if (presenterP1.allShipsPlaced()) {
                vboxP1.setVisible(false);
                vboxP2.setVisible(true);
                stage.show(); // Update the view
            } else {
                // Toon een bericht dat niet alle schepen zijn geplaatst
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Not all ships are placed");
                alert.setContentText("Please place all ships before proceeding to the next player.");

                alert.showAndWait();
            }
        });

        VBox vbox = new VBox(vboxP1, vboxP2);
        stage.setScene(new Scene(vbox));
        stage.show();
    }
}
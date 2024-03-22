package be.fnaf2.view.main;

import be.fnaf2.Exceptions.ButtonActionException;
import be.fnaf2.Exceptions.ButtonInitializationException;
import be.fnaf2.model.GridModel;
import be.fnaf2.view.battleship.BattleshipMain;
import be.fnaf2.view.gridplacement.GridPresenter;
import be.fnaf2.view.gridplacement.Gridview;
import be.fnaf2.view.hoofdgame.HoofdgameView;
import be.fnaf2.view.rules.RulesPresenter;
import be.fnaf2.view.rules.RulesView;
import be.fnaf2.view.settings.SettingsPresenter;
import be.fnaf2.view.settings.SettingsView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;


public class BattleshipsPresenter {
    private final BattleshipsView view;
    private Gridview gridviewP1;
    private Gridview gridviewP2;

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

        // Start het Battleship-spel in de huidige applicatie-vensters
        BattleshipMain battleshipMain = new BattleshipMain();
        Scene scene = new Scene(battleshipMain.createContent());
        stage.setScene(scene);
        stage.show();
    }

    private void switchToMultiPlayerView() {
        Stage stage = (Stage) view.getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Start Multiplayer Game Confirmation");
        alert.setContentText("Are you sure you want to start a multiplayer game?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // Gridview for player 1
            Gridview gridviewP1 = new Gridview(stage);
            GridPresenter presenterP1 = new GridPresenter(new GridModel(), gridviewP1);
            HBox hboxP1 = new HBox(gridviewP1.getShipTypeChoiceBox(), gridviewP1.getClearButton(), gridviewP1.getUndoButton(), gridviewP1.getNextButton());
            hboxP1.setSpacing(10);
            VBox vboxP1 = new VBox(hboxP1, gridviewP1);
            vboxP1.setSpacing(5);

            // Gridview for player 2
            Gridview gridviewP2 = new Gridview(stage);
            GridPresenter presenterP2 = new GridPresenter(new GridModel(), gridviewP2);
            HBox hboxP2 = new HBox(gridviewP2.getShipTypeChoiceBox(), gridviewP2.getClearButton(), gridviewP2.getUndoButton(), gridviewP2.getNextButton());
            hboxP2.setSpacing(10);
            VBox vboxP2 = new VBox(hboxP2, gridviewP2);
            vboxP2.setSpacing(5);
            vboxP2.setVisible(false);

            // HBox to contain both VBoxes
            HBox hbox = new HBox(vboxP1, vboxP2);
            hbox.setAlignment(Pos.CENTER); // Set initial alignment to center

            // Action for the "Next" button of player 1
            gridviewP1.getNextButton().setOnAction(event -> {
                if (presenterP1.allShipsPlaced() && !vboxP2.isVisible()) {
                    // Hide the VBox of player 1 and show that of player 2
                    vboxP1.setVisible(false);
                    vboxP2.setVisible(true);
                    presenterP2.resetShips(); // Reset the ships for player 2
                    hbox.setAlignment(Pos.CENTER_LEFT); // Align to left when first VBox is hidden
                } else if (presenterP2.allShipsPlaced() && vboxP2.isVisible()) {
                    // When both players have placed all their ships
                    HoofdgameView hoofdGameView = new HoofdgameView(gridviewP1, gridviewP2); // Call the showGame method
                    stage.setScene(new Scene(hoofdGameView));
                    stage.show();
                } else {
                    // Warn if not all ships are placed
                    Alert mpalert = new Alert(Alert.AlertType.WARNING);
                    mpalert.setTitle("Warning");
                    mpalert.setHeaderText("Not all ships are placed");
                    mpalert.setContentText("Please place all ships before proceeding to the next player.");

                    mpalert.showAndWait();
                }
            });

            // Action for the "Next" button of player 2
            gridviewP2.getNextButton().setOnAction(event -> {
                if (presenterP2.allShipsPlaced()) {
                    // When player 2 has placed all their ships
                    HoofdgameView hoofdGameView = new HoofdgameView(gridviewP1, gridviewP2); // Call the showGame method
                    stage.setScene(new Scene(hoofdGameView));
                    stage.show();
                } else {
                    // Warn if not all ships are placed
                    Alert mpalert = new Alert(Alert.AlertType.WARNING);
                    mpalert.setTitle("Warning");
                    mpalert.setHeaderText("Not all ships are placed");
                    mpalert.setContentText("Please place all ships before proceeding to the next player.");

                    mpalert.showAndWait();
                }
            });

            // Show player 1's grid
            stage.setScene(new Scene(hbox, 2000, 800)); // Adjust the width and height if necessary
            stage.show();
        }
    }
}
// BattleshipsPresenter.java
package be.fnaf2.view.main;

import be.fnaf2.settings.SettingsPresenter;
import be.fnaf2.settings.SettingsView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class BattleshipsPresenter {
    private final BattleshipsView view;
    private final Stage primaryStage;

    public BattleshipsPresenter(BattleshipsView view, Stage primaryStage) {
        this.view = view;
        this.primaryStage = primaryStage;
        initialize();
    }

    public static void showBattleshipsView(Stage primaryStage) {
        BattleshipsView battleshipsView = new BattleshipsView(primaryStage);

        BattleshipsPresenter battleshipsPresenter = new BattleshipsPresenter(battleshipsView, primaryStage);

        primaryStage.setTitle("Battleships");
        primaryStage.setScene(new Scene(battleshipsView, 800, 600)); // Set the scene directly here
        primaryStage.show();
    }

    private void initialize() {
        // Set event handlers
        view.getSingleplayerButton().setOnAction(event -> showConfirmationAlert("Singleplayer"));
        view.getMultiplayerButton().setOnAction(event -> showConfirmationAlert("Multiplayer"));
        view.getSettingButton().setOnAction(event -> showConfirmationAlert("Settings"));
    }

    private void showConfirmationAlert(String gameMode) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Switching to " + gameMode + " mode");
        alert.setContentText("Are you sure you want to switch to " + gameMode + " mode?");

        alert.showAndWait().ifPresent(response -> {
            if (gameMode.equalsIgnoreCase("settings")) {
                showSettingsView();
            } else {
                if (response == ButtonType.OK) {
                    showGridPlacementView();
                }
            }
        });
    }

    private void showGridPlacementView() {
        // Implement your logic to show the grid placement view
    }

    private void showSettingsView() {
        SettingsPresenter.showSettingsView(primaryStage);
    }
}
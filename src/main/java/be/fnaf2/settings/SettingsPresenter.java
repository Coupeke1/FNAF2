// SettingsPresenter.java
package be.fnaf2.settings;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SettingsPresenter {
    private final SettingsView view;
    private final Stage primaryStage;

    public SettingsPresenter(SettingsView view, Stage primaryStage) {
        this.view = view;
        this.primaryStage = primaryStage;
        initialize();
    }

    public static void showSettingsView(Stage primaryStage) {
        SettingsView settingsView = new SettingsView();

        SettingsPresenter settingsPresenter = new SettingsPresenter(settingsView, primaryStage);

        primaryStage.setTitle("Settings");
        primaryStage.setScene(new Scene(settingsView, 800, 600)); // Set the scene directly here
        primaryStage.show();
    }

    private void initialize() {
        // Set event handlers or other settings-related logic
    }
}
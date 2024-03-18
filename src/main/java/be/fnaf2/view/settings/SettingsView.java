// SettingsView.java
package be.fnaf2.view.settings;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;


public class SettingsView extends GridPane {

    private ChoiceBox<Integer> gridSizeChoiceBox;
    private ChoiceBox<String> cellColorChoiceBox;
    private ChoiceBox<String> gridLineColorChoiceBox;
    private Button backButton;
    // Add UI components for settings

    public SettingsView() {
        initializeView();
    }

    private void initializeView() {
        Label gridSizeLabel = new Label("Grid Size:");
        Label cellColorLabel = new Label("Cell Color:");
        Label gridLineColorLabel = new Label("Grid Line Color:");
    // Add UI components for settings
        gridSizeChoiceBox = new ChoiceBox<>();
        cellColorChoiceBox = new ChoiceBox<>();
        gridLineColorChoiceBox = new ChoiceBox<>();
        backButton = new Button("Go Back");

        gridSizeChoiceBox.getItems().addAll(5, 10, 15, 20);
        cellColorChoiceBox.getItems().addAll("Red", "Blue", "Green", "Yellow");
        gridLineColorChoiceBox.getItems().addAll("Black", "White", "Grey", "Brown");
        gridSizeChoiceBox.setValue(10);
        cellColorChoiceBox.setValue("Red");
        gridLineColorChoiceBox.setValue("Black");


        this.add(gridSizeLabel, 0, 0);
        this.add(gridSizeChoiceBox, 1, 0);
        this.add(cellColorLabel, 0, 1);
        this.add(cellColorChoiceBox, 1, 1);
        this.add(gridLineColorLabel, 0, 2);
        this.add(gridLineColorChoiceBox, 1, 2);
        this.add(backButton, 0, 3);
    }
    public Button getBackButton() {
        return backButton;
    }
}
// SettingsView.java
package be.fnaf2.view.settings;

import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;


public class SettingsView extends GridPane {

    private ChoiceBox<Integer> gridSizeChoiceBox;
    private ChoiceBox<String> cellColorChoiceBox;
    private ChoiceBox<String> gridLineColorChoiceBox;
    // Add UI components for settings

    public SettingsView() {
        initializeView();
    }

    private void initializeView() {
    // Add UI components for settings
        gridSizeChoiceBox = new ChoiceBox<>();
        cellColorChoiceBox = new ChoiceBox<>();
        gridLineColorChoiceBox = new ChoiceBox<>();
        gridSizeChoiceBox.getItems().addAll(5, 10, 15, 20);
        cellColorChoiceBox.getItems().addAll("Red", "Blue", "Green", "Yellow");
        gridLineColorChoiceBox.getItems().addAll("Black", "White", "Grey", "Brown");
        this.add(gridSizeChoiceBox, 0, 0);
        this.add(cellColorChoiceBox, 0, 1);
        this.add(gridLineColorChoiceBox, 0, 2);

    }
}
package be.fnaf2.view.settings;

import be.fnaf2.view.gridplacement.Gridview;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

public class SettingsView extends GridPane {

    private ChoiceBox<Integer> gridSizeChoiceBox;
    private ChoiceBox<Integer> numShipsChoiceBox;
    private Button backButton;

    public SettingsView() {
        initializeView();
    }

    private void initializeView() {
        Label gridSizeLabel = new Label("Grid Size:");
        Label numShipsLabel = new Label("Number of Ships:");

        gridSizeChoiceBox = new ChoiceBox<>();
        numShipsChoiceBox = new ChoiceBox<>();
        backButton = new Button("Go Back");

        gridSizeChoiceBox.getItems().addAll(5, 10, 15, 20);
        numShipsChoiceBox.getItems().addAll(5, 10, 15, 20);

        gridSizeChoiceBox.setOnAction(e -> updateGridSize(gridSizeChoiceBox.getValue()));
        numShipsChoiceBox.setOnAction(e -> updateNumShips(numShipsChoiceBox.getValue()));

        gridSizeChoiceBox.setValue(10); // Default grid size
        numShipsChoiceBox.setValue(5); // Default number of ships

        this.add(gridSizeLabel, 0, 0);
        this.add(gridSizeChoiceBox, 1, 0);
        this.add(numShipsLabel, 0, 1);
        this.add(numShipsChoiceBox, 1, 1);
        this.add(backButton, 0, 2);
    }

    public Button getBackButton() {
        return backButton;
    }

    private void updateGridSize(int newSize) {
        Gridview.setNumRows(newSize);
        Gridview.setNumCols(newSize);
    }

    private void updateNumShips(int newNum) {
        Gridview.setNumShips(newNum);
    }
}

package be.fnaf2.view.rules;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class RulesView extends GridPane {
    private Button backButton;

    public RulesView() {
        initializeView();
    }

    private void initializeView() {
        // Voeg instructies toe om Battleships te spelen
        Label titleLabel = new Label("Battleships Instructions:");
        Label instruction1Label = new Label("1. Plaats je schepen op het rooster door te klikken.");
        Label instruction2Label = new Label("2. Je kunt schepen horizontaal of verticaal plaatsen.");
        Label instruction3Label = new Label("3. Klik op 'Undo' om een fout te herstellen.");
        Label instruction4Label = new Label("4. Klik op 'Clear' om het rooster te resetten.");
        Label instruction5Label = new Label("5. Klik op 'Start Game' om het spel te starten.");
        backButton = new Button("Go Back");

        // Voeg de labels toe aan de GridPane
        this.add(titleLabel, 0, 0);
        this.add(instruction1Label, 0, 1);
        this.add(instruction2Label, 0, 2);
        this.add(instruction3Label, 0, 3);
        this.add(instruction4Label, 0, 4);
        this.add(instruction5Label, 0, 5);
        this.add(backButton, 0, 6);
    }

    public ButtonBase getBackButton() {
        return backButton;
    }
}


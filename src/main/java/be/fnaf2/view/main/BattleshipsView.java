package be.fnaf2.view.main;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class BattleshipsView extends VBox {


    private Label titleLabel;
    private Button singleplayerButton;
    private Button multiplayerButton;

    public BattleshipsView() {
        initializeView();
    }

    private void initializeView() {
        titleLabel = new Label("BATTLESHIPS");
        singleplayerButton = new Button("Singleplayer");
        multiplayerButton = new Button("Multiplayer");

        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-text-fill: #9f9f9f;");
        titleLabel.setFont(new Font(18.0));

        singleplayerButton.setMinWidth(Button.USE_PREF_SIZE);
        multiplayerButton.setMinWidth(Button.USE_PREF_SIZE);

        HBox buttonsBox = new HBox(singleplayerButton, multiplayerButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(10.0);

        HBox.setHgrow(buttonsBox, Priority.ALWAYS);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(10.0);
        this.getChildren().addAll(titleLabel, buttonsBox);
        Image backgroundImage = new Image(getClass().getResourceAsStream("/BattleshipsLogo.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        this.setBackground(new Background(background));



    }



    public Button getSingleplayerButton() {
        return singleplayerButton;
    }

    public Button getMultiplayerButton() {
        return multiplayerButton;
    }
}

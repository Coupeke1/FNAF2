package be.fnaf2.view.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class BattleshipsView extends GridPane {

    private Button singleplayerButton;
    private Button multiplayerButton;
    private Button settingButton;
    private Button rulesButton;

    public BattleshipsView() {
        initializeView();
    }

    private void initializeView() {
        Image settingsImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/SettingsKnop.png")));
        ImageView settingImageView = new ImageView(settingsImage);
        settingImageView.setFitHeight(20);
        settingImageView.setPreserveRatio(true);

        singleplayerButton = new Button("Singleplayer");
        multiplayerButton = new Button("Multiplayer");
        settingButton = new Button();
        rulesButton = new Button("rules");
        settingButton.setGraphic(settingImageView);
        settingButton.setPrefSize(30, 30);
        settingButton.setStyle("-fx-background-color: grey;");

        singleplayerButton.setStyle("-fx-border-color: red;");
        multiplayerButton.setStyle("-fx-border-color: blue;");
        settingButton.setStyle("-fx-border-color: green;");
        rulesButton.setStyle("-fx-border-color: green");
        singleplayerButton.setMinWidth(Button.USE_PREF_SIZE);
        multiplayerButton.setMinWidth(Button.USE_PREF_SIZE);

        this.setHgap(10.0);
        this.setVgap(10.0);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10.0));

        this.add(singleplayerButton, 0, 0);
        this.add(multiplayerButton, 1, 0);

        // Voeg lege ruimte toe om de knop rechtsonder te plaatsen
        this.add(new Region(), 0, 1);
        this.add(new Region(), 1, 1);

        this.add(settingButton, 1, 1);
        this.add(rulesButton, 0, 1);

        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/battleships.jpg")));
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        this.setBackground(new Background(background));
    }

    public Button getSingleplayerButton() {
        return singleplayerButton;
    }

    public Button getMultiplayerButton() {
        return multiplayerButton;
    }

    public Button getSettingButton() {
        return settingButton;
    }
    public Button getRulesButton() {return rulesButton;}


}
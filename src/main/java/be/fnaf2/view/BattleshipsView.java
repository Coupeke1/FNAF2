package be.fnaf2.view;

import be.fnaf2.presenter.BattleshipsPresenter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Objects;

public class BattleshipsView extends VBox {
    private final BattleshipsPresenter presenter;

    private Label titleLabel;
    private Button singleplayerButton;
    private Button multiplayerButton;
    private ImageView imageView;

    public BattleshipsView(BattleshipsPresenter presenter) {
        this.presenter = presenter;
        initializeView();
    }

    private void initializeView() {
        titleLabel = new Label("BATTLESHIPS");
        singleplayerButton = new Button("Singleplayer");
        multiplayerButton = new Button("Multiplayer");

        // Load the image
        if (getClass().getResource("/FoxySecretgif.gif") != null) {
            imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/FoxySecretgif.gif"))));
        } else {
            System.out.println("Image not found");
            imageView = new ImageView(); // or use a default image
        }

        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-text-fill: #9f9f9f;");
        titleLabel.setFont(new Font(18.0));

        singleplayerButton.setMinWidth(Button.USE_PREF_SIZE);
        multiplayerButton.setMinWidth(Button.USE_PREF_SIZE);

        imageView.setFitHeight(156.0);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setPickOnBounds(true);

        HBox buttonsBox = new HBox(singleplayerButton, multiplayerButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(10.0);

        HBox.setHgrow(buttonsBox, Priority.ALWAYS); // Allow buttonsBox to grow horizontally

        this.setAlignment(Pos.CENTER); // Center the content vertically
        this.setSpacing(10.0); // Add spacing between elements if needed
        this.getChildren().addAll(titleLabel, imageView, buttonsBox);
    }

    public BattleshipsPresenter getPresenter() {
        return presenter;
    }
}

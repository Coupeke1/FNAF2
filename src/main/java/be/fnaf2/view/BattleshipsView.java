package be.fnaf2.view;

import be.fnaf2.presenter.BattleshipsPresenter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class BattleshipsView extends HBox {
    private final BattleshipsPresenter presenter;

    // Voeg hier eventuele JavaFX-componenten toe
    private AnchorPane anchorPane;
    private Label titleLabel;
    private Button singleplayerButton;
    private Button multiplayerButton;
    private ImageView imageView;

    public BattleshipsView(BattleshipsPresenter presenter) {
        this.presenter = presenter;
        initializeView();
    }

    private void initializeView() {
        anchorPane = new AnchorPane();
        titleLabel = new Label("BATTLESHIPS");
        singleplayerButton = new Button("Singleplayer");
        multiplayerButton = new Button("Multiplayer");
        imageView = new ImageView(new Image(getClass().getResource("/FNAF2OldFoxyJumpscare.gif").toExternalForm()));

// Controleer op null voordat je toExternalForm() aanroept
        if (getClass().getResource("/path/to/FNAF2OldFoxyJumpscare.gif") != null) {
            imageView = new ImageView(new Image(getClass().getResource("/FNAF2OldFoxyJumpscare.gif").toExternalForm()));
        } else {
            // Geef een foutmelding of gebruik een standaardafbeelding als de resource niet wordt gevonden
            System.out.println("Afbeelding niet gevonden");
            imageView = new ImageView(); // of gebruik een standaardafbeelding
        }
        titleLabel.setAlignment(javafx.geometry.Pos.CENTER);
        titleLabel.setPrefSize(371.0, 26.0);
        titleLabel.setStyle("-fx-text-fill: #9f9f9f;");
        titleLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        titleLabel.setWrapText(false);
        titleLabel.setFont(new Font(18.0));

        singleplayerButton.setLayoutX(96.0);
        singleplayerButton.setLayoutY(245.0);
        singleplayerButton.setPrefSize(414.0, 26.0);

        multiplayerButton.setLayoutX(96.0);
        multiplayerButton.setLayoutY(295.0);
        multiplayerButton.setPrefSize(414.0, 26.0);

        imageView.setFitHeight(156.0);
        imageView.setFitWidth(529.0);
        imageView.setLayoutX(38.0);
        imageView.setLayoutY(14.0);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setPickOnBounds(true);

        anchorPane.getChildren().addAll(titleLabel, singleplayerButton, multiplayerButton, imageView);

        this.getChildren().add(anchorPane);
    }

    public BattleshipsPresenter getPresenter() {
        return presenter;
    }
}

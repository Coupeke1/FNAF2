package be.fnaf2.view;

import be.fnaf2.presenter.BattleshipsPresenter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import java.util.Objects;

public class BattleshipsView extends HBox {
    private final BattleshipsPresenter presenter;

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

        // Load the image
        if (getClass().getResource("/FNAF2OldFoxyJumpscare.gif") != null) {
            imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/FNAF2OldFoxyJumpscare.gif"))));
        } else {
            System.out.println("Image not found");
            imageView = new ImageView(); // or use a default image
        }

        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-text-fill: #9f9f9f;");
        titleLabel.setFont(new Font(18.0));

        singleplayerButton.setPrefSize(414.0, 26.0);
        multiplayerButton.setPrefSize(414.0, 26.0);

        imageView.setFitHeight(156.0);
        imageView.setFitWidth(529.0);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setPickOnBounds(true);

        AnchorPane.setTopAnchor(titleLabel, 20.0);
        AnchorPane.setLeftAnchor(titleLabel, (640 - titleLabel.getWidth()) / 2); // Center horizontally
        AnchorPane.setLeftAnchor(singleplayerButton, 96.0);
        AnchorPane.setTopAnchor(singleplayerButton, 245.0);
        AnchorPane.setLeftAnchor(multiplayerButton, 96.0);
        AnchorPane.setTopAnchor(multiplayerButton, 295.0);
        AnchorPane.setLeftAnchor(imageView, (640 - imageView.getFitWidth()) / 2); // Center horizontally
        AnchorPane.setTopAnchor(imageView, 14.0);

        anchorPane.getChildren().addAll(titleLabel, singleplayerButton, multiplayerButton, imageView);

        this.getChildren().add(anchorPane);
    }

    public BattleshipsPresenter getPresenter() {
        return presenter;
    }
}

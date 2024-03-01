// SplashView.java
package be.fnaf2.view.Splash;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashView {

    private final StackPane parent = new StackPane();

    public SplashView() {
        Image image = new Image("FoxySecretgif.gif");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(500);
        parent.getChildren().add(imageView);
    }

    public StackPane getParent() {
        return parent;
    }
}

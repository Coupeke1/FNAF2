// SplashView.java
package be.fnaf2.view.Splash;

import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashView extends StackPane {


    private Image image;
    private ImageView imageView;

    public SplashView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.image = new Image("FoxySecretgif.gif");
        this.imageView = new ImageView(image);
    }

    private void layoutNodes() {
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(500);
        this.getChildren().add(imageView);
    }



}

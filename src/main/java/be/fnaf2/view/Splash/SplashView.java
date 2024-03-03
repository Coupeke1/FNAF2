// SplashView.java
package be.fnaf2.view.Splash;

import javafx.util.Duration;
import be.fnaf2.view.main.BattleshipsPresenter;
import be.fnaf2.view.main.BattleshipsView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashView {

    private StackPane parent;
    private Stage splashStage;
    private Image image;
    private ImageView imageView;

    public SplashView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.parent = new StackPane();
        this.image = new Image("FoxySecretgif.gif");
        this.imageView = new ImageView(image);
    }

    private void layoutNodes() {
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(500);
        parent.getChildren().add(imageView);
    }

    public StackPane getParent() {
        return parent;
    }

    public void showSplashScreen(Stage primaryStage) {
        splashStage = new Stage();


        SplashView splashView = new SplashView();
        Scene scene = new Scene(splashView.getParent(), 800, 600);
        Duration duration = Duration.seconds(0.5);
        splashStage.setScene(scene);
        splashStage.initStyle(StageStyle.TRANSPARENT);
        splashStage.centerOnScreen();
        splashStage.show();
        SplashPresenter presenter = new SplashPresenter(splashStage,duration);
    }

}

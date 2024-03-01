// SplashView.java
package be.fnaf2.view.Splash;

import be.fnaf2.view.main.BattleshipsPresenter;
import be.fnaf2.view.main.BattleshipsView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SplashView {

    private final StackPane parent = new StackPane();
    private Stage splashStage;

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

    public void showBattleshipsView(Stage primaryStage) {
        BattleshipsPresenter battleshipsPresenter = new BattleshipsPresenter(null);
        BattleshipsView battleshipsView = new BattleshipsView(battleshipsPresenter);

        Scene scene = new Scene(battleshipsView, 800, 600);

        primaryStage.setTitle("Battleships");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showSplashScreen(Duration splashDuration, Stage primaryStage) {
        splashStage = new Stage();
        SplashView splashView = new SplashView();
        Scene scene = new Scene(splashView.getParent(), 800, 600);
        splashStage.setScene(scene);
        splashStage.initStyle(StageStyle.TRANSPARENT);
        splashStage.centerOnScreen();
        splashStage.show();

        // Close the splash screen after the specified duration
        Timeline timeline = new Timeline(
                new KeyFrame(splashDuration, event -> {
                    splashStage.close();

                    // Switch to the next view after closing the splash screen
                    showBattleshipsView(primaryStage);
                })
        );
        timeline.play();
    }
}

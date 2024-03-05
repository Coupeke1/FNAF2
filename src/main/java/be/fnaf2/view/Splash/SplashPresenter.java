package be.fnaf2.view.Splash;

// SplashPresenter.java

import be.fnaf2.view.main.BattleshipsPresenter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashPresenter {
    private final Stage splashStage;

    public SplashPresenter(Stage splashStage, Duration splashDuration) {
        this.splashStage = splashStage;
        addEventHandler(splashDuration);
    }

    private void addEventHandler(Duration splashDuration) {
        // Close the splash screen after the specified duration
        Timeline timeline = new Timeline(
                new KeyFrame(splashDuration, event -> {

                    Stage primaryStage = new Stage();
                    BattleshipsPresenter.showBattleshipsView(primaryStage);
                    splashStage.close();

                })
        );
        timeline.play();
    }
}

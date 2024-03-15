package be.fnaf2.view.Splash;

// SplashPresenter.java

import be.fnaf2.view.gridplacement.Gridview;
import be.fnaf2.view.main.BattleshipsPresenter;
import be.fnaf2.view.main.BattleshipsView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashPresenter {
    private final SplashView splashView;

    public SplashPresenter(SplashView view, Duration splashDuration) {
        this.splashView = view;
        splashDuration.toSeconds();
        addEventHandler(splashDuration);
    }

    private void addEventHandler(Duration splashDuration) {
        // Close the splash screen after the specified duration
        Timeline timeline = new Timeline(
                new KeyFrame(splashDuration, event -> {
                    BattleshipsView view = new BattleshipsView();
                    BattleshipsPresenter presenter = new BattleshipsPresenter(view);
                    splashView.getScene().setRoot(view);

                })
        );
        timeline.play();
    }
}

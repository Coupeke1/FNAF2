package be.fnaf2;

import be.fnaf2.view.Splash.SplashView;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Show the splash screen
        SplashView splashView = new SplashView();
        Duration splashDuration = Duration.seconds(0.5);
        splashView.showSplashScreen(splashDuration, primaryStage);
    }
}

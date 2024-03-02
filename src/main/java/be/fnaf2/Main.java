// Main.java
package be.fnaf2;


import be.fnaf2.view.Splash.SplashView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Show the splash screen
        SplashView splashView = new SplashView();
        splashView.showSplashScreen( primaryStage);
    }
}

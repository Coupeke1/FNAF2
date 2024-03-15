// Main.java
package be.fnaf2;


import be.fnaf2.view.Splash.SplashPresenter;
import be.fnaf2.view.Splash.SplashView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SplashView splashView = new SplashView();
        SplashPresenter presenter = new SplashPresenter(splashView, new Duration(800));
        primaryStage.setScene(new Scene(splashView));
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.show();
    }
}

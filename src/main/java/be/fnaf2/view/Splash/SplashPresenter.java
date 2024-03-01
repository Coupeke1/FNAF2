package be.fnaf2.view.Splash;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SplashPresenter extends Application {

    // The parent layout manager
    private final StackPane parent = new StackPane();

    @Override
    public void init() throws Exception {
        super.init();

        // Simulate application loading tasks
        simulateLoadingTasks();

    }

    private void simulateLoadingTasks() throws InterruptedException {

        // Simulate a loading task by sleeping for a short time
        Thread.sleep(500);
    }


    @Override
    public void start(Stage stage) throws Exception {

        // Create a scene with the StackPane as the root
        Scene scene = new Scene(parent, 640, 480);

        // Set the stage title
        stage.setTitle("Creating Splash Screens with JavaFX");

        // Set the scene for the stage
        stage.setScene(scene);

        // Center the stage on the screen
        stage.centerOnScreen();

        // Display the stage
        stage.show();
    }

    public static void main(String[] args) {

        // Specify the custom preloader class
        System.setProperty("javafx.preloader", SplashView.class.getName());
        launch(args);
    }
}
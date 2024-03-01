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

    @Override
    public void start(Stage stage) throws Exception {

    }

    private void simulateLoadingTasks() throws InterruptedException {

        // Simulate a loading task by sleeping for a short time
        Thread.sleep(500);
    }





}
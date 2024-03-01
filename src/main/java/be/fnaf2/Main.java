package be.fnaf2;

/*
import be.fnaf2.model.BattleshipsModel;
import be.fnaf2.view.main.BattleshipsPresenter;
import be.fnaf2.view.main.BattleshipsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        BattleshipsModel model = new BattleshipsModel();
        BattleshipsView view = new BattleshipsView(new BattleshipsPresenter(model, null));

        // Geef de Battleshipdemo.view door aan de Battleshipdemo.presenter
        BattleshipsPresenter presenter = view.getPresenter();
        presenter.setView(view);

        Scene scene = new Scene(view, 640, 400);
        stage.setScene(scene);
        stage.setTitle("Battleships Game");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
*/

// Main.java
import be.fnaf2.view.Splash.SplashView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Show the splash screen
        showSplashScreen();

        // Simulate some loading tasks (replace this with your actual loading tasks)
        try {
            Thread.sleep(3000); // Simulating a delay of 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Continue with your main window setup
        primaryStage.setTitle("MyApp");
        primaryStage.setScene(new Scene(new VBox(), 800, 600));
        primaryStage.show();
    }

    private void showSplashScreen() {
        Stage splashStage = new Stage();
        SplashView splashView = new SplashView();
        Scene scene = new Scene(splashView.getParent(), 640, 480);
        splashStage.setScene(scene);
        splashStage.initStyle(StageStyle.TRANSPARENT);
        splashStage.centerOnScreen();
        splashStage.showAndWait();
    }
}





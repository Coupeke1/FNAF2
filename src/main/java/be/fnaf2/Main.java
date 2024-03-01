package be.fnaf2;


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

/*
import be.fnaf2.view.Gridview;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Main extends Application {

    public static void main(String[] args) {
        // Start Gridview als een afzonderlijke applicatie
        launch(Gridview.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set up the primary stage
        primaryStage.setTitle("MyApp");
        primaryStage.setScene(new Scene(new VBox(), 800, 600));
        primaryStage.show();
    }
}
*/





package be.fnaf2;


import be.fnaf2.model.BattleshipsModel;
import be.fnaf2.presenter.BattleshipsPresenter;
import be.fnaf2.view.BattleshipsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage stage) {
        BattleshipsModel model = new BattleshipsModel();
        BattleshipsView view = new BattleshipsView(new BattleshipsPresenter(model, null));

        // Geef de Battleshipdemo.view door aan de Battleshipdemo.presenter
        BattleshipsPresenter presenter = (BattleshipsPresenter) view.getPresenter();
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

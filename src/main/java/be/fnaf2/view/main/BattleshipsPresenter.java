// BattleshipsPresenter.java
package be.fnaf2.view.main;

import be.fnaf2.model.BattleshipsModel;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BattleshipsPresenter {
    private final BattleshipsModel model;
    private final BattleshipsView view;

    public BattleshipsPresenter(BattleshipsModel model) {
        this.model = model;
        this.view = new BattleshipsView(this);
    }

    // Static method to show BattleshipsView
    public static void showBattleshipsView(Stage primaryStage) {
        BattleshipsPresenter battleshipsPresenter = new BattleshipsPresenter(null);
        BattleshipsView battleshipsView = new BattleshipsView(battleshipsPresenter);

        Scene scene = new Scene(battleshipsView, 800, 600);

        primaryStage.setTitle("Battleships");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

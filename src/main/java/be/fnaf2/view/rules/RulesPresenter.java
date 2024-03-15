package be.fnaf2.view.rules;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class RulesPresenter {
        private final RulesView view;
        private final Stage primaryStage;

        public RulesPresenter(RulesView view, Stage primaryStage) {
            this.view = view;
            this.primaryStage = primaryStage;
            initialize();
        }

    private void initialize() {
    }

    public static void showRulesView(Stage primaryStage) {
            RulesView rulesView = new RulesView();

            be.fnaf2.view.rules.RulesPresenter rulesPresenter = new be.fnaf2.view.rules.RulesPresenter(rulesView, primaryStage);

            primaryStage.setTitle("Settings");
            primaryStage.setScene(new Scene (rulesView, 800, 600)); // Set the scene directly here
            primaryStage.show();
        }
}

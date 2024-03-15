    // BattleshipsPresenter.java
    package be.fnaf2.view.main;

    import be.fnaf2.Exceptions.ButtonInitializationException;
    import be.fnaf2.model.GridModel;
    import be.fnaf2.view.settings.SettingsPresenter;
    import be.fnaf2.view.gridplacement.GridPresenter;
    import be.fnaf2.view.gridplacement.Gridview;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.ButtonType;
    import javafx.stage.Stage;

    public class BattleshipsPresenter {
        private final BattleshipsView view;

        public BattleshipsPresenter(BattleshipsView view ) {
            this.view = view;
            initialize();
        }


        private void initialize() {
            // Set event handlers
            try {
                if (view.getSingleplayerButton() != null) {
                    view.getSingleplayerButton().setOnAction(event -> showConfirmationAlert("Singleplayer"));
                } else {
                    throw new ButtonInitializationException("Singleplayer Button Error");
                }

                if (view.getMultiplayerButton() != null) {
                    view.getMultiplayerButton().setOnAction(event -> showConfirmationAlert("Multiplayer"));
                } else {
                    throw new ButtonInitializationException("Multiplayer Button Error");
                }

                if (view.getSettingButton() != null) {
                    view.getSettingButton().setOnAction(event -> showConfirmationAlert("Settings"));
                } else {
                    throw new ButtonInitializationException("Settings Button Error");
                }
               if (view.getRulesButton() != null) {
                   view.getRulesButton().setOnAction(event -> showConfirmationAlert("Rules"));
               } else {
                   throw new ButtonInitializationException("Settings Button Error");
               }

            } catch (ButtonInitializationException e) {
                e.printStackTrace();
            }
        }

        private void showConfirmationAlert(String gameMode) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Switching to " + gameMode + " mode");
            alert.setContentText("Are you sure you want to switch to " + gameMode + " mode?");

            alert.showAndWait().ifPresent(response -> {
                if (gameMode.equalsIgnoreCase("settings")) {
                   // showSettingsView();
                } else {
                    if (response == ButtonType.OK) {
                     //   showGridPlacementView();
                    }
                }
            });
        }


    }
package be.fnaf2.view.hoofdgame;

import be.fnaf2.model.HoofdgameModel;
import be.fnaf2.view.gridplacement.EnemyGrid;
import be.fnaf2.view.gridplacement.Gridview;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HoofdgameView extends HBox {
    private Gridview player2Grid;
    private Gridview playerGrid;
    private Gridview enemyGrid;

    public HoofdgameView(HoofdgameModel model, Gridview playerGrid, EnemyGrid enemyGrid) {
        this.playerGrid = playerGrid;
        this.enemyGrid = enemyGrid;

        VBox playerBox = new VBox(new Label("Your Grid"), playerGrid);
        VBox enemyBox = new VBox(new Label("Enemy Grid"), enemyGrid);

        // Set preferred width for the grids
        playerBox.setPrefWidth(400);
        enemyBox.setPrefWidth(400);

        this.getChildren().addAll(playerBox, enemyBox);
        this.setSpacing(20); // Set space between grids

        // Place the ships after the grid has been added to the view
        Platform.runLater(enemyGrid::placeShips);
    }

    public HoofdgameView(HoofdgameModel model, Gridview playerGrid, Gridview player2Grid) {
        this.playerGrid = playerGrid;
        this.player2Grid = player2Grid;

        VBox playerBox = new VBox(new Label("Player1 grid"), playerGrid);
        VBox enemyBox = new VBox(new Label("Player2  Grid"), player2Grid);

        // Set preferred width for the grids
        playerBox.setPrefWidth(400);
        enemyBox.setPrefWidth(400);

        this.getChildren().addAll(playerBox, enemyBox);
        this.setSpacing(20); // Set space between grids

        // Place the ships after the grid has been added to the view
    }
}



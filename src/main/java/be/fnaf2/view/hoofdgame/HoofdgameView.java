package be.fnaf2.view.hoofdgame;

import be.fnaf2.model.HoofdgameModel;
import be.fnaf2.view.gridplacement.EnemyGrid;
import be.fnaf2.view.gridplacement.Gridview;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HoofdgameView extends HBox {
    private Gridview player1Grid;
    private Gridview player2Grid;
    private Gridview enemyGrid;

    /*public HoofdgameView(HoofdgameModel model, Gridview playerGrid, EnemyGrid enemyGrid) {
        this.player1Grid = playerGrid;
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
    }*/

    public HoofdgameView(Gridview player1Grid, Gridview player2Grid) {
        this.player1Grid = player1Grid;
        this.player2Grid = player2Grid;

        // Add the Gridview objects directly to the HoofdgameView
        this.getChildren().addAll(this.player1Grid, this.player2Grid);
        this.setSpacing(20); // Set space between grids
    }
}



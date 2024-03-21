package be.fnaf2.view.hoofdgame;

import be.fnaf2.model.HoofdgameModel;
import be.fnaf2.view.gridplacement.Gridview;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HoofdgameView extends HBox {
    private Gridview player1Grid;
    private Gridview player2Grid;
    private Gridview enemyGrid;



    public HoofdgameView(Gridview player1Grid, Gridview player2Grid) {
        this.player1Grid = player1Grid;
        this.player2Grid = player2Grid;

        // Add the Gridview objects directly to the HoofdgameView
        this.getChildren().addAll(this.player1Grid, this.player2Grid);
        this.setSpacing(20); // Set space between grids
        System.out.println("Number of children in HoofdgameView: " + this.getChildren().size());
        for (Node child : this.getChildren()) {
            System.out.println("Child down under: " + child);
        }
    }
}
package be.fnaf2.view.hoofdgame;

import be.fnaf2.model.HoofdgameModel;
import be.fnaf2.view.gridplacement.Gridview;
import javafx.scene.layout.HBox;

public class HoofdgameView extends HBox {
    private Gridview player1Grid;
    private Gridview player2Grid;
    private HoofdgamePresenter presenter;

    public HoofdgameView(Gridview player1Grid, Gridview player2Grid) {
        this.player1Grid = player1Grid;
        this.player2Grid = player2Grid;

        this.getChildren().addAll(this.player1Grid, this.player2Grid); // Add both grids to the view
        this.setSpacing(20);

        initializePresenter();
        disableShipPlacement();
        player2Grid.hideSpecialColors();
    }

    private void initializePresenter() {
        presenter = new HoofdgamePresenter(new HoofdgameModel(presenter), this);
        player1Grid.enableShipPlacement(presenter);
    }


    void disableShipPlacement() {
        // Keep the mouse hover events
        player1Grid.setOnMouseClicked(event -> {}); // Disable mouse click events
        player1Grid.setOnMouseDragged(event -> {}); // Disable mouse drag events
    }



}
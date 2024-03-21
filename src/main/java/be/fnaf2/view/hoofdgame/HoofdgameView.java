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
        player2Grid.enableShooting(presenter);
    }

    private void initializePresenter() {
        HoofdgameModel model = new HoofdgameModel(presenter);
        presenter = new HoofdgamePresenter(model, this);
    }

    private void disableShipPlacement() {
        player1Grid.setOnMouseClicked(null); // Disable mouse click events
        player1Grid.setOnMouseDragged(null); // Disable mouse drag events
    }

    public HoofdgamePresenter getPresenter() {
        return presenter;
    }

    public Gridview getPlayer1Grid() {
        return player1Grid;
    }
}
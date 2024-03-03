package be.fnaf2.view.gridplacement;

import be.fnaf2.model.GridModel;
import javafx.stage.Stage;

public class GridPresenter {
    private final GridModel gridModel;

    private final Gridview gridview;

    public GridPresenter(GridModel gridModel, Gridview gridview) {
        this.gridModel = gridModel;
        this.gridview = gridview;
    }

    public void showGridView(Stage primaryStage) {
        gridview.showGrid(primaryStage);
    }
}

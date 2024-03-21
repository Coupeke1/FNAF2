package be.fnaf2.view.gridplacement;

import be.fnaf2.Exceptions.ButtonInitializationException;
import be.fnaf2.model.GridModel;
import be.fnaf2.view.main.BattleshipsPresenter;
import be.fnaf2.view.main.BattleshipsView;
import javafx.stage.Stage;

public class GridPresenter {
    private final GridModel gridModel;
    private final Gridview gridview;


    public GridPresenter(GridModel gridModel, Gridview gridview) {
        this.gridModel = gridModel;
        this.gridview = gridview;
        this.gridview.getShipTypeChoiceBox().getItems().addAll(ShipType.values());
        this.gridview.getShipTypeChoiceBox().setValue(ShipType.values()[0]);

    }
    public void resetShips() {
        for (ShipType shipType : ShipType.values()) {
            shipType.resetAvailable();
        }
        this.gridview.getShipTypeChoiceBox().getItems().clear();
        this.gridview.getShipTypeChoiceBox().getItems().addAll(ShipType.values());
        this.gridview.getShipTypeChoiceBox().setValue(ShipType.values()[0]);
    }

    public boolean allShipsPlaced() {
        for (ShipType shipType : ShipType.values()) {
            if (shipType.getAvailable() > 0) {
                return false;
            }
        }
        return true;
    }


}
package be.fnaf2.view.hoofdgame;

import be.fnaf2.model.HoofdgameModel;

public class HoofdgamePresenter {
    private HoofdgameModel model;
    private HoofdgameView view;

    public HoofdgamePresenter(HoofdgameModel model, HoofdgameView view) {
        this.model = model;
        this.view = view;
        initView();
    }

    private void initView() {

    }

}
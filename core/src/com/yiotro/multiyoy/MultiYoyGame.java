package com.yiotro.multiyoy;

import com.badlogic.gdx.Game;
import com.yiotro.multiyoy.view.MainMenuScreen;
import com.yiotro.multiyoy.view.GameScreenManager;

public class MultiYoyGame extends Game {

    private GameScreenManager gsm;

    @Override
    public void create() {
        gsm = new GameScreenManager(this);

        gsm.setScreen(new MainMenuScreen(gsm));
    }

    @Override
    public void dispose() {
        super.dispose();
        gsm.dispose();
    }
}

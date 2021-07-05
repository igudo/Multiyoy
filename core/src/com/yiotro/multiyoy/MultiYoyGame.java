package com.yiotro.multiyoy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.yiotro.multiyoy.view.MainMenuScreen;

public class MultiYoyGame extends Game {

    private Screen gameScreen;

    @Override
    public void create() {
        gameScreen = new MainMenuScreen();
        setScreen(gameScreen);
    }
}

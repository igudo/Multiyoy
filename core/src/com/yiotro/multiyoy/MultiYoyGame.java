package com.yiotro.multiyoy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.yiotro.multiyoy.view.MainMenuScreen;

public class MultiYoyGame extends Game {

    private Screen mainMenuScreen;

    @Override
    public void create() {
        mainMenuScreen = new MainMenuScreen();
        setScreen(mainMenuScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        mainMenuScreen.dispose();
    }
}

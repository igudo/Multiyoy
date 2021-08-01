package com.yiotro.multiyoy.view;

import com.badlogic.gdx.Screen;
import com.yiotro.multiyoy.MultiYoyGame;

public class GameScreenManager {

    public MultiYoyGame game;
    public Screen curScreen;

    public GameScreenManager(MultiYoyGame multiYoyGame) {
        game = multiYoyGame;
    }

    public void setScreen(Screen screen) {
        curScreen = screen;
        game.setScreen(screen);
    }

    public void changeScreen(Screen screen){
        curScreen.dispose();

        curScreen = screen;
        game.setScreen(screen);
    }

    public void dispose(){
        curScreen.dispose();
    }
}

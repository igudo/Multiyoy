package com.yiotro.multiyoy.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.yiotro.multiyoy.Constants;
import com.yiotro.multiyoy.view.MainMenuScreen;

public class GameScreenUI {

    public Stage stage;
    private TextureAtlas btn_atlas;
    private Skin btn_skin;
    private FitViewport viewport;
    private Table mainTable;
    private MainMenuScreen mainMenuScreen;

    public GameScreenUI() {
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT);
        stage = new Stage(viewport);

        btn_atlas = new TextureAtlas("glassy/glassy-ui.atlas");
        btn_skin = new Skin(Gdx.files.internal("glassy/glassy-ui.json"), btn_atlas);

        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        TextButton backButton = new TextButton("Menu", btn_skin);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen = new MainMenuScreen();
                ((Game) Gdx.app.getApplicationListener()).setScreen(mainMenuScreen);
            }
        });

        mainTable.add(backButton).top().padRight(10).padTop(10);
        mainTable.right();

        addToStage();
    }

    private void addToStage() {
        stage.addActor(mainTable);
    }

    public void draw() {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.act();
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}

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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.yiotro.multiyoy.Constants;
import com.yiotro.multiyoy.view.GameScreen;
import com.yiotro.multiyoy.view.OptionsScreen;

public class MainMenuScreenUI {

    public Stage stage;
    private TextureAtlas btn_atlas;
    private Skin btn_skin;
    private FitViewport viewport;
    private GameScreen gameScreen;
    private OptionsScreen optionsScreen;
    private Assets assets;
    private Table mainTable;

    public MainMenuScreenUI() {
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT);
        stage = new Stage(viewport);

        btn_atlas = new TextureAtlas("glassy/glassy-ui.atlas");
        btn_skin = new Skin(Gdx.files.internal("glassy/glassy-ui.json"), btn_atlas);

        //Create Table
        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        //Create elements
        Label label = new Label("MultiYoy", btn_skin);
        label.setAlignment(Align.center);
        label.setPosition(0, 0, Align.center);

        TextButton playButton = new TextButton("Play", btn_skin);
        TextButton optionsButton = new TextButton("Options", btn_skin);
        TextButton exitButton = new TextButton("Exit", btn_skin);

        //Add listeners to buttons
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameScreen = new GameScreen();
                ((Game) Gdx.app.getApplicationListener()).setScreen(gameScreen);
            }
        });

        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                optionsScreen = new OptionsScreen();
                ((Game) Gdx.app.getApplicationListener()).setScreen(optionsScreen);
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Add elements to table
        mainTable.add(label);
        mainTable.row();
        mainTable.add(playButton).width((float) (Constants.WIDTH * 0.6));
        mainTable.row();
        mainTable.add(optionsButton).space(20).width((float) (Constants.WIDTH * 0.6));
        mainTable.row();
        mainTable.add(exitButton).width((float) (Constants.WIDTH * 0.6));
        mainTable.center();
//        mainTable.setDebug(true);

        //Add table to stage
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
        gameScreen.dispose();
        optionsScreen.dispose();
    }
}

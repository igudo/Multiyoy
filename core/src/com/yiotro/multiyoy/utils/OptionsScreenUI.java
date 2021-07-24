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
import com.yiotro.multiyoy.view.MainMenuScreen;
import com.yiotro.multiyoy.view.OptionsScreen;

public class OptionsScreenUI {

    public Stage stage;
    private Label label;
    private TextureAtlas btn_atlas;
    private Skin btn_skin;
    private FitViewport viewport;
    private MainMenuScreen mainMenuScreen;
    private Assets assets;
    private Table mainTable;
    private OptionsScreen optionsScreen;

    public float[] rgb;

    public OptionsScreenUI() {
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT);
        stage = new Stage(viewport);

        btn_atlas = new TextureAtlas("glassy/glassy-ui.atlas");
        btn_skin = new Skin(Gdx.files.internal("glassy/glassy-ui.json"), btn_atlas);

        //Create Table
        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        //Create elements
        Label label = new Label("Options", btn_skin);
        label.setAlignment(Align.center);
        label.setPosition(0, 0, Align.center);

        TextButton ezButton = new TextButton("EZZ", btn_skin);
        final TextButton optionButton = new TextButton("Option", btn_skin);
        TextButton backButton = new TextButton("Back", btn_skin);

        //Add listeners to buttons
        ezButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rgb = new float[]{(float) Math.random(), (float) Math.random(), (float) Math.random(),1f};
//                rgb = new float[]{200/255f,150/255f,200/255f,1f};

            }
        });

        optionButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen = new MainMenuScreen();
                ((Game) Gdx.app.getApplicationListener()).setScreen(mainMenuScreen);
            }
        });

        //Add elements to table
        mainTable.add(label);
        mainTable.row();
        mainTable.add(ezButton).width((float) (Constants.WIDTH * 0.6));
        mainTable.row();
        mainTable.add(optionButton).space(20).width((float) (Constants.WIDTH * 0.6));
        mainTable.row();
        mainTable.add(backButton).width((float) (Constants.WIDTH * 0.6));
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
        mainMenuScreen.dispose();
    }
}

package com.yiotro.multiyoy.utils;

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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.yiotro.multiyoy.Constants;
import com.yiotro.multiyoy.view.GameScreen;
import com.yiotro.multiyoy.view.OptionsScreen;

import static com.yiotro.multiyoy.Constants.HEIGHT;
import static com.yiotro.multiyoy.Constants.WIDTH;

public class MainMenuScreenUI {

    public Stage stage;
    private TextureAtlas btn_atlas;
    private Skin btn_skin;
    private Skin font_skin;
    private ExtendViewport viewport;
    private Table mainTable;
    private GameScreenManager gsm;

    public MainMenuScreenUI(GameScreenManager gameScreenManager) {
        gsm = gameScreenManager;

        viewport = new ExtendViewport(WIDTH, HEIGHT);
        stage = new Stage(viewport);

        btn_atlas = new TextureAtlas("glassy/glassy-ui.atlas");
        btn_skin = new Skin(Gdx.files.internal("glassy/glassy-ui.json"), btn_atlas);
        font_skin = new Skin(Gdx.files.internal("font/font.json"));

        //Create Table
        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        //Create elements

        Label gameName = new Label("MultiYoy", font_skin.get("default", Label.LabelStyle.class));
        gameName.setColor(btn_skin.getColor("white"));
        gameName.setAlignment(Align.center);
        gameName.setPosition(0, 0, Align.center);
        gameName.setFontScale(.6f);


        TextButton playButton = new TextButton("Play", btn_skin);
        TextButton optionsButton = new TextButton("Options", btn_skin);
        TextButton exitButton = new TextButton("Exit", btn_skin);

        //Add listeners to buttons
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.changeScreen(new GameScreen(gsm));
            }
        });

        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.changeScreen(new OptionsScreen(gsm));
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.dispose();
                Gdx.app.exit();
            }
        });

        //Add elements to table
        mainTable.add(gameName).spaceBottom(30);
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
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        stage.act();
        stage.draw();
    }

    public void dispose() {
        stage.dispose();

    }
}

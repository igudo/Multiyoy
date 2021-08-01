package com.yiotro.multiyoy.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.yiotro.multiyoy.Constants;
import com.yiotro.multiyoy.model.Car;
import com.yiotro.multiyoy.view.GameScreen;
import com.yiotro.multiyoy.view.GameScreenManager;
import com.yiotro.multiyoy.view.MainMenuScreen;

public class GameScreenUI {

    public Stage stage;
    private TextureAtlas btn_atlas;
    private Skin btn_skin;
    private Skin font_skin;
    private FitViewport viewport;
    private Table mainTable;
    private GameScreenManager gsm;
    public Label nickName;
    private GameScreen gameScreen;

    public GameScreenUI(GameScreen gS) {
        gameScreen = gS;
        gsm = gameScreen.gsm;

        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT);
        stage = new Stage(viewport);

        btn_atlas = new TextureAtlas("glassy/glassy-ui.atlas");
        btn_skin = new Skin(Gdx.files.internal("glassy/glassy-ui.json"), btn_atlas);
        font_skin = new Skin(Gdx.files.internal("font/font.json"));

        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        nickName = new Label(String.valueOf(gameScreen.car.carController.carSpeed), font_skin.get("default", Label.LabelStyle.class));
        nickName.setAlignment(Align.center);
        nickName.setPosition(0, 0, Align.center);
        nickName.setFontScale(.2f);

        TextButton backButton = new TextButton("Menu", btn_skin);
        backButton.setStyle(btn_skin.get("small", TextButton.TextButtonStyle.class));
        backButton.getLabel().setStyle(font_skin.get("default", Label.LabelStyle.class));
        backButton.getLabel().setFontScale(.2f);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.changeScreen(new MainMenuScreen(gsm));

            }
        });

        mainTable.add(nickName).expandX().left().padTop(10).padLeft(10);
        mainTable.add(backButton).padTop(10).padRight(10);
        mainTable.top();
        addToStage();
    }

    private void addToStage() {
        stage.addActor(mainTable);
    }

    public void draw() {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.act();
        stage.draw();

        nickName.setText(String.valueOf(Math.round(gameScreen.car.carController.carSpeed * 1.0) / 1.0 ));
    }

    public void dispose() {
        stage.dispose();
    }
}

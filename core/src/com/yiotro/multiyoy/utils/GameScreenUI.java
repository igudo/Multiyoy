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
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.yiotro.multiyoy.Constants;
import com.yiotro.multiyoy.view.GameScreen;
import com.yiotro.multiyoy.view.GameScreenManager;
import com.yiotro.multiyoy.view.MainMenuScreen;

import static com.yiotro.multiyoy.Constants.HEIGHT;
import static com.yiotro.multiyoy.Constants.WIDTH;

public class GameScreenUI {

    public Stage stage;
    private TextureAtlas btn_atlas;
    private Skin btn_skin;
    private Skin font_skin;
    private ExtendViewport viewport;
    private Table mainTable;
    private GameScreenManager gsm;
    public Label speedLabel;
    private GameScreen gameScreen;

    public boolean btnUpIsPressed;
    public boolean btnDownIsPressed;
    public boolean btnLeftIsPressed;
    public boolean btnRightIsPressed;

    public GameScreenUI(GameScreen gS) {
        gameScreen = gS;
        gsm = gameScreen.gsm;

        viewport = new ExtendViewport(WIDTH, HEIGHT);
        stage = new Stage(viewport);

        btn_atlas = new TextureAtlas("glassy/glassy-ui.atlas");
        btn_skin = new Skin(Gdx.files.internal("glassy/glassy-ui.json"), btn_atlas);
        font_skin = new Skin(Gdx.files.internal("font/font.json"));

        mainTable = new Table();
        mainTable.setFillParent(true);

        speedLabel = new Label(String.valueOf(gameScreen.car.carController.carSpeed), font_skin.get("default", Label.LabelStyle.class));
        speedLabel.setFontScale(.2f);

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

        TextButton arrowUp = new TextButton("Up", btn_skin);
        arrowUp.setStyle(btn_skin.get("small", TextButton.TextButtonStyle.class));
        arrowUp.getLabel().setStyle(font_skin.get("default", Label.LabelStyle.class));
        arrowUp.getLabel().setFontScale(.2f);
        arrowUp.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btnUpIsPressed = true;
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnUpIsPressed = false;
            }
        });

        TextButton arrowDown = new TextButton("Down", btn_skin);
        arrowDown.setStyle(btn_skin.get("small", TextButton.TextButtonStyle.class));
        arrowDown.getLabel().setStyle(font_skin.get("default", Label.LabelStyle.class));
        arrowDown.getLabel().setFontScale(.2f);
        arrowDown.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btnDownIsPressed = true;
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnDownIsPressed = false;
            }
        });

        TextButton arrowLeft = new TextButton("Left", btn_skin);
        arrowLeft.setStyle(btn_skin.get("small", TextButton.TextButtonStyle.class));
        arrowLeft.getLabel().setStyle(font_skin.get("default", Label.LabelStyle.class));
        arrowLeft.getLabel().setFontScale(.2f);
        arrowLeft.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btnLeftIsPressed = true;
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnLeftIsPressed = false;
            }
        });

        TextButton arrowRight = new TextButton("Right", btn_skin);
        arrowRight.setStyle(btn_skin.get("small", TextButton.TextButtonStyle.class));
        arrowRight.getLabel().setStyle(font_skin.get("default", Label.LabelStyle.class));
        arrowRight.getLabel().setFontScale(.2f);
        arrowRight.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btnRightIsPressed = true;
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnRightIsPressed = false;
            }
        });

        Table infoTable = new Table();
        Table controlTable = new Table();
        Table upDownTable = new Table();
        Table leftRightTable = new Table();

        infoTable.add(speedLabel).expandX().left();
        infoTable.add(backButton);

        upDownTable.add(arrowUp).space(20);
        upDownTable.row();
        upDownTable.add(arrowDown);

        leftRightTable.add(arrowLeft).space(20);
        leftRightTable.add(arrowRight);

        controlTable.add(upDownTable).expand().left().pad(20);
        controlTable.add(leftRightTable).expand().right().pad(20);

        mainTable.add(infoTable).expandX().fillX().pad(10);
        mainTable.row();
        mainTable.add(controlTable).expand().fillX().pad(10).padBottom(50).bottom();

//        stage.setDebugAll(true);
        addToStage();

    }

    private void addToStage() {
        stage.addActor(mainTable);
    }

    public void draw() {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        stage.act();
        stage.draw();

        speedLabel.setText(String.valueOf(Math.round(gameScreen.car.carController.carSpeed * 1.0) / 1.0 ));

    }

    public void dispose() {
        stage.dispose();
    }
}

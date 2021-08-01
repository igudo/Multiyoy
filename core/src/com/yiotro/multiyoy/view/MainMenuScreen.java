package com.yiotro.multiyoy.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yiotro.multiyoy.utils.MainMenuScreenUI;

import static com.yiotro.multiyoy.Constants.CAMERA_WIDTH;

public class MainMenuScreen implements Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private MainMenuScreenUI ui;
    private GameScreenManager gsm;

    public MainMenuScreen(GameScreenManager gameScreenManager){
        gsm = gameScreenManager;
    }

    @Override
    public void show() {
        ui = new MainMenuScreenUI(gsm);
        Gdx.input.setInputProcessor(ui.stage);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255/255f,160/255f,200/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.begin();
        batch.end();

        ui.draw();
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) height / width;
        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_WIDTH * aspectRatio);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        ui.dispose();
    }
}

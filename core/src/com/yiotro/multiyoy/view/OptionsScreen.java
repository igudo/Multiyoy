package com.yiotro.multiyoy.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yiotro.multiyoy.utils.OptionsScreenUI;

import static com.yiotro.multiyoy.Constants.CAMERA_WIDTH;

public class OptionsScreen implements Screen {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private OptionsScreenUI ui;
    private GameScreenManager gsm;

    public OptionsScreen(GameScreenManager gameScreenManager){
        gsm = gameScreenManager;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        ui = new OptionsScreenUI(gsm);
        Gdx.input.setInputProcessor(ui.stage);
        ui.rgb = new float[]{150/255f,150/255f,200/255f,1f};
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(ui.rgb[0], ui.rgb[1], ui.rgb[2], ui.rgb[3]);
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

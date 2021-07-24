package com.yiotro.multiyoy.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.yiotro.multiyoy.model.Car;
import com.yiotro.multiyoy.utils.Assets;
import com.yiotro.multiyoy.utils.GameScreenUI;


import static com.yiotro.multiyoy.Constants.CAMERA_WIDTH;
import static com.yiotro.multiyoy.Constants.CAR_WIDTH_RATIO;

public class GameScreen implements Screen {

    private SpriteBatch batch;
    private TextureAtlas textureAtlas;
    private Car car;
    private OrthographicCamera camera;
    private Assets assets;
    private GameScreenUI ui;

    public static float deltaCff;

    @Override
    public void show() {
        batch = new SpriteBatch();
        assets = new Assets();
        textureAtlas = assets.getManager().get("atlas1.atlas", TextureAtlas.class);
        ui = new GameScreenUI();
        Gdx.input.setInputProcessor(ui.stage);
        car = new Car(textureAtlas.findRegion("0"), 0, 0, 3f, 3f*CAR_WIDTH_RATIO);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(210/255f,100/255f,210/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        deltaCff = delta;

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        car.draw(batch);
        batch.end();

        ui.draw();

//        camera.position.set(car.getBounds().getX(), car.getBounds().getY(), 0);
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) height / width;
        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_WIDTH * aspectRatio);

//        camera.position.set(car.getBounds().getX(), car.getBounds().getY(), 0);
//        camera.zoom = 1f;
        camera.update();
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
        car.dispose();
        ui.dispose();
    }
}

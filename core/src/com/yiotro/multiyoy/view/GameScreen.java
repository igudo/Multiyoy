package com.yiotro.multiyoy.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.yiotro.multiyoy.model.Car;
import com.yiotro.multiyoy.model.Map;
import com.yiotro.multiyoy.utils.Assets;
import com.yiotro.multiyoy.utils.GameScreenUI;


import static com.yiotro.multiyoy.Constants.CAMERA_WIDTH;
import static com.yiotro.multiyoy.Constants.CAR_WIDTH_RATIO;
import static com.yiotro.multiyoy.Constants.HEIGHT;
import static com.yiotro.multiyoy.Constants.WIDTH;

public class GameScreen implements Screen {

    private Batch batch;
    private TextureAtlas textureAtlas;
    public Car car;
    public Map map;
    private OrthographicCamera camera;
    private Assets assets;
    private GameScreenUI ui;
    public GameScreenManager gsm;

    public static float deltaCff;

    public GameScreen(GameScreenManager gameScreenManager){
        gsm = gameScreenManager;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_WIDTH * (float) HEIGHT / WIDTH);
        assets = new Assets();
        textureAtlas = assets.getManager().get("atlas1.atlas", TextureAtlas.class);
        car = new Car(textureAtlas.findRegion("0"), 0, 0, 3f, 3f*CAR_WIDTH_RATIO);
        map = new Map();
        batch = map.renderer.getBatch();
        ui = new GameScreenUI(this);
        Gdx.input.setInputProcessor(ui.stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(210/255f,100/255f,210/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        deltaCff = delta;

        batch.setProjectionMatrix(camera.combined);

        map.draw(camera);

        batch.begin();
        car.draw(batch);
        batch.end();

        // ui
        ui.draw();

        // info
        camera.position.set(car.getBounds().getX(), car.getBounds().getY(), 0);
        camera.update();
        String x = String.valueOf(Math.round(car.getBounds().getX()*1.0/1.0));
        String y = String.valueOf(Math.round(car.getBounds().getY()*1.0/1.0));
//        Gdx.app.log("#", x + " " + y + " " + camera.position.x);
        camera.zoom = (Math.abs(car.carController.carSpeed) + 20) / 20;
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) height / width;
        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_WIDTH * aspectRatio);
        camera.zoom = car.carController.carSpeed / 3;
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
        assets.dispose();
        ui.dispose();
        map.dispose();
    }
}

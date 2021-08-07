package com.yiotro.multiyoy.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.yiotro.multiyoy.control.CameraController;
import com.yiotro.multiyoy.model.Car;
import com.yiotro.multiyoy.model.DebugShapes;
import com.yiotro.multiyoy.model.Map;
import com.yiotro.multiyoy.utils.Assets;
import com.yiotro.multiyoy.utils.CollisionDetector;
import com.yiotro.multiyoy.utils.GameScreenManager;
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
    public OrthographicCamera camera;
    private Assets assets;
    public GameScreenUI ui;
    public GameScreenManager gsm;
    private CollisionDetector collisionDetector;
    private CameraController cameraController;
    private DebugShapes debugShapes;

    public static float deltaCff;

    public GameScreen(GameScreenManager gameScreenManager) {
        gsm = gameScreenManager;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_WIDTH * (float) HEIGHT / WIDTH);
        assets = new Assets();
        textureAtlas = assets.getManager().get("models/models.atlas", TextureAtlas.class);
        map = new Map(this);
        car = new Car(this, textureAtlas.findRegion("0"), map.spawnCoords[0], map.spawnCoords[1], 3f, 3f * CAR_WIDTH_RATIO);
        batch = map.renderer.getBatch();
        collisionDetector = new CollisionDetector(this);
        cameraController = new CameraController(this);
        debugShapes = new DebugShapes(this);
        ui = new GameScreenUI(this);
        Gdx.input.setInputProcessor(ui.stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0 / 255f, 0 / 255f, 10 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        deltaCff = delta;

        batch.setProjectionMatrix(camera.combined);
        debugShapes.shapeRenderer.setProjectionMatrix(camera.combined);

        map.draw();
        batch.begin();
        car.draw(batch);
        batch.end();
        debugShapes.draw();
        ui.draw();

        cameraController.update();
        collisionDetector.update();
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) height / width;
        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_WIDTH * aspectRatio);
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

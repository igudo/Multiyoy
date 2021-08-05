package com.yiotro.multiyoy.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.yiotro.multiyoy.Constants;
import com.yiotro.multiyoy.model.Car;
import com.yiotro.multiyoy.model.Map;
import com.yiotro.multiyoy.utils.Assets;
import com.yiotro.multiyoy.utils.GameScreenUI;


import java.util.Arrays;

import static com.yiotro.multiyoy.Constants.CAMERA_WIDTH;
import static com.yiotro.multiyoy.Constants.CAR_WIDTH_RATIO;
import static com.yiotro.multiyoy.Constants.HEIGHT;
import static com.yiotro.multiyoy.Constants.WIDTH;
import static com.yiotro.multiyoy.Constants.unitScale;

public class GameScreen implements Screen {

    private Batch batch;
    private TextureAtlas textureAtlas;
    public Car car;
    public Map map;
    public OrthographicCamera camera;
    private Assets assets;
    public GameScreenUI ui;
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
        map = new Map(this);
        batch = map.renderer.getBatch();
        ui = new GameScreenUI(this);
        Gdx.input.setInputProcessor(ui.stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0/255f,0/255f,10/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        deltaCff = delta;

        batch.setProjectionMatrix(camera.combined);

        map.draw(camera);

        batch.begin();
        car.draw(this, batch);
        batch.end();

        // ui
        ui.draw();

        // camera
        camera.position.set(car.getBounds().getX() + car.getBounds().getOriginX(), car.getBounds().getY() + car.getBounds().getOriginY(), 0);
//        String x = String.valueOf(Math.round(car.getBounds().getX()*1.0/1.0));
//        String y = String.valueOf(Math.round(car.getBounds().getY()*1.0/1.0));
//        Gdx.app.log("#", x + " " + y + " " + camera.position.x);
        camera.zoom = (Math.abs(car.carController.carSpeed) + 75) / 75 ;

        // collision
        MapObjects objects = map.getObjects();

        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle = map.getRectangle(rectangleObject);
            if (Intersector.overlaps(rectangle, car.getBounds().getBoundingRectangle())) {
                if (rectangleObject.getName().equals("grass")) {
                    Gdx.app.log("#", String.valueOf(rectangleObject.getName()));
                }
            }
        }
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

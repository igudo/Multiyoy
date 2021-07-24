package com.yiotro.multiyoy.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.yiotro.multiyoy.model.Car;
import com.yiotro.multiyoy.model.MyCar;
import com.yiotro.multiyoy.utils.Assets;
import com.yiotro.multiyoy.utils.GameScreenUI;
import com.yiotro.multiyoy.utils.NetWorker;


import static com.yiotro.multiyoy.Constants.CAMERA_WIDTH;
import static com.yiotro.multiyoy.Constants.CAR_WIDTH_RATIO;

public class GameScreen implements Screen {

    private SpriteBatch batch;
    private TextureAtlas textureAtlas;
    private TextureAtlas otherTextureAtlas;
    private MyCar myCar;
    private Car otherCar;
    private OrthographicCamera camera;
    private Assets assets;
    private GameScreenUI ui;

    NetWorker netWorker;

    public static float deltaCff;

    @Override
    public void show() {
        batch = new SpriteBatch();
        assets = new Assets();
        textureAtlas = assets.getManager().get("atlas1.atlas", TextureAtlas.class);
        otherTextureAtlas = assets.getManager().get("atlas1.atlas", TextureAtlas.class);
        ui = new GameScreenUI();
        Gdx.input.setInputProcessor(ui.stage);
        myCar = new MyCar(textureAtlas.findRegion("0"), 0, 0, 3f, 3f*CAR_WIDTH_RATIO);
        otherCar = new Car(otherTextureAtlas.findRegion("0"), -2f, -2f, 3f, 3f*CAR_WIDTH_RATIO);

        // init tcp conn
        netWorker = new NetWorker();
        new Thread(netWorker).start();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(210/255f,100/255f,210/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        deltaCff = delta;

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        myCar.draw(batch);
        otherCar.draw(batch);
        batch.end();

        // Sending current pos
        netWorker.myCarPos = myCar.getPos();
        otherCar.setPos(netWorker.otherCarPos[0]-2, netWorker.otherCarPos[1]-2, netWorker.otherCarPos[2]);

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
        myCar.dispose();
        otherCar.dispose();
        ui.dispose();
    }
}

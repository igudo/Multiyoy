package com.yiotro.multiyoy.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.yiotro.multiyoy.Constants;
import com.yiotro.multiyoy.MultiYoyGame;
import com.yiotro.multiyoy.model.Car;

import static com.yiotro.multiyoy.Constants.CAMERA_WIDTH;
import static com.yiotro.multiyoy.Constants.CAR_WIDTH_RATIO;

public class GameScreen implements Screen {

    private Stage stage;
    private SpriteBatch batch;
    private Car car;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;

    public static float deltaCff ;

    public GameScreen(){
        atlas = new TextureAtlas("glassy/glassy-ui.atlas");
        skin = new Skin(Gdx.files.internal("glassy/glassy-ui.json"), atlas);
        batch = new SpriteBatch();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        car = new Car("blue_car.png", 0, 0, 3f, 3f*CAR_WIDTH_RATIO);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(230/255f,100/255f,230/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        deltaCff = delta;

        stage.act();
        stage.draw();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        car.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) height / width;

        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_WIDTH * aspectRatio);
        viewport = new FitViewport(CAMERA_WIDTH, CAMERA_WIDTH * aspectRatio, camera);
        viewport.apply();
        stage = new Stage(viewport, batch);

        camera.position.set(0, 0, 0);

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
        skin.dispose();
        atlas.dispose();
        car.dispose();
    }
}

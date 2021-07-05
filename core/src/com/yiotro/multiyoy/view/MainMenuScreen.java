package com.yiotro.multiyoy.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yiotro.multiyoy.model.Bg;

public class MainMenuScreen implements Screen {

    private Texture bgImg;
    private SpriteBatch batch;
    private Bg bg;

    @Override
    public void show() {
        bgImg = new Texture(Gdx.files.internal("main_menu_background.png"));
        batch = new SpriteBatch();
        bg = new Bg(bgImg, 0, 0, 500, 100);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        bg.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        bgImg.dispose();
        batch.dispose();
    }
}

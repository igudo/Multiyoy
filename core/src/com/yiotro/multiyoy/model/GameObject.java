package com.yiotro.multiyoy.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.yiotro.multiyoy.view.GameScreen;

public abstract class GameObject {

    GameScreen gS;
    Polygon bounds;
    Sprite object;
    public TextureRegion textureRegion;

    GameObject(GameScreen gameScreen, TextureRegion textureRegion, float x, float y, float w, float h) {
        this.gS = gameScreen;
        this.textureRegion = textureRegion;
        object = new Sprite(textureRegion);
        object.setSize(w, h);
        object.setPosition(x, y);
        object.setOrigin(w / 2f, h / 2f);

        bounds = new Polygon();
        bounds.setVertices(new float[]{0f,0f, w, 0f, w,h, 0f, h});
        bounds.setPosition(x, y);
        bounds.setOrigin(w/2f, h/2f);
    }

    public void draw(Batch batch) {
        object.setPosition(bounds.getX(), bounds.getY());
        object.setRotation(bounds.getRotation());
        object.draw(batch);
    }

    public Polygon getBounds() {
        return bounds;
    }
}

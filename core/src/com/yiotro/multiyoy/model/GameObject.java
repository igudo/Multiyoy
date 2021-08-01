package com.yiotro.multiyoy.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

public abstract class GameObject {

    Polygon bounds;
    Sprite object;
    TextureRegion textureRegion;

    GameObject(TextureRegion textureRegion, float x, float y, float w, float h) {
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

package com.yiotro.multiyoy.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {

    Rectangle bounds;
    Sprite object;
    Texture texture;

    GameObject(Texture textureInternal, float x, float y, float w, float h) {
        bounds = new Rectangle(x, y, w, h);
        texture = textureInternal;
        object = new Sprite(texture);
    }

    public void draw(SpriteBatch batch) {
        object.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        object.draw(batch);
    }

    public void dispose() {
        texture.dispose();
    }
}

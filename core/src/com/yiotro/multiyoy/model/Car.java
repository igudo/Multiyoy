package com.yiotro.multiyoy.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Car extends GameObject {

    public Car(TextureRegion textureRegion, float x, float y, float w, float h) {
        super(textureRegion, x, y, w, h);
    }

    public void setPos(float x, float y, float a) {
        bounds.setPosition(x,y);
        bounds.setRotation(a);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}

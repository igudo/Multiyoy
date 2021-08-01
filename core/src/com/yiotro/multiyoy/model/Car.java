package com.yiotro.multiyoy.model;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.yiotro.multiyoy.control.CarController;

public class Car extends GameObject {

    public CarController carController;

    public Car(TextureRegion textureRegion, float x, float y, float w, float h) {
        super(textureRegion, x, y, w, h);
        carController = new CarController(bounds);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        carController.handle();
    }
}

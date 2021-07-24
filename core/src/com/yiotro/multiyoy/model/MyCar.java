package com.yiotro.multiyoy.model;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.yiotro.multiyoy.control.CarController;

public class MyCar extends Car {
    private CarController carController;
    public MyCar(TextureRegion textureRegion, float x, float y, float w, float h) {
        super(textureRegion, x, y, w, h);
        carController = new CarController(bounds);
    }

    public float[] getPos() {
        return new float[]{carController.getCarBounds().getX(), carController.getCarBounds().getY(), carController.getCarBounds().getRotation()};
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        carController.handle();
    }
}

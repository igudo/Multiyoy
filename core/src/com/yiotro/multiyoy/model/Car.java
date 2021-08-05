package com.yiotro.multiyoy.model;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.yiotro.multiyoy.control.CarController;
import com.yiotro.multiyoy.view.GameScreen;

public class Car extends GameObject {

    public CarController carController;

    public Car(TextureRegion textureRegion, float x, float y, float w, float h) {
        super(textureRegion, x, y, w, h);
        carController = new CarController(bounds);
    }

    public void draw(GameScreen gameScreen, Batch batch) {
        super.draw(batch);
        carController.handle(gameScreen);
    }
}

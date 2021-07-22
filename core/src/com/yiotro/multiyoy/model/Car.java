package com.yiotro.multiyoy.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yiotro.multiyoy.control.CarController;

public class Car extends GameObject {
    private CarController carController;
    public Car(String texturePath, float x, float y, float w, float h) {
        super(new Texture(Gdx.files.internal(texturePath)), x, y, w, h);
        carController = new CarController(bounds);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        carController.handle();
    }
}

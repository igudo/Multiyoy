package com.yiotro.multiyoy.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.yiotro.multiyoy.view.GameScreen;

public class CarController {

    private Polygon carBounds;
    private float carSpeed, speedVelocity = 35f, speedMax = 25f;
    private float rotationSpeed = 10f;

    public CarController(Polygon carBounds) {
        this.carBounds = carBounds;
    }

    public Polygon getCarBounds() {
        return carBounds;
    }


    public void handle(){
        // speed
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            carSpeed += speedVelocity * GameScreen.deltaCff;
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            carSpeed -= speedVelocity * GameScreen.deltaCff;
        else
            carSpeed = downSpeed();
        carSpeed = sliceSpeed();
        //

        // rotation
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            carBounds.rotate(rotationSpeed * carSpeed * GameScreen.deltaCff);
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            carBounds.rotate(-rotationSpeed * carSpeed * GameScreen.deltaCff);
        //

        carBounds.setPosition(carBounds.getX() + MathUtils.cosDeg(carBounds.getRotation() + 90) * carSpeed * GameScreen.deltaCff,
                                carBounds.getY() + MathUtils.sinDeg(carBounds.getRotation() + 90) * carSpeed * GameScreen.deltaCff);
    }

    private float downSpeed() {
        if (carSpeed > speedVelocity * GameScreen.deltaCff)
            return carSpeed - speedVelocity * GameScreen.deltaCff;
        else if (carSpeed < -speedVelocity * GameScreen.deltaCff)
            return carSpeed + speedVelocity * GameScreen.deltaCff;
        return 0f;
    }

    private float sliceSpeed() {
        if (carSpeed > speedMax)
            return speedMax;
        if (carSpeed < -speedMax)
            return -speedMax;
        return carSpeed;
    }
}

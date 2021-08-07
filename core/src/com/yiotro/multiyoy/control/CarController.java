package com.yiotro.multiyoy.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.yiotro.multiyoy.view.GameScreen;

public class CarController {

    public Polygon carBounds;
    public float carSpeed = 0f, acceleration = 35f, groundBraking = 70f, speedMax = 40f, groundSpeedMax = 20f;
    public float rotationSpeed = 10f;

    public boolean roadCollision = false;
    public boolean emptyCollision = false;

    public CarController(Polygon carBounds) {
        this.carBounds = carBounds;
    }


    public void handle(GameScreen gameScreen){
        // speed
        if (Gdx.input.isKeyPressed(Input.Keys.UP) | gameScreen.ui.btnUpIsPressed)
            carSpeed += acceleration * GameScreen.deltaCff;
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) | gameScreen.ui.btnDownIsPressed)
            carSpeed -= acceleration * GameScreen.deltaCff;
        else
            carSpeed = downSpeed();
        carSpeed = sliceSpeed();
        carSpeed = sliceSpeedOnGround();
        //

        // rotation
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) | gameScreen.ui.btnLeftIsPressed)
            carBounds.rotate(rotationSpeed * carSpeed * GameScreen.deltaCff);
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) | gameScreen.ui.btnRightIsPressed)
            carBounds.rotate(-rotationSpeed * carSpeed * GameScreen.deltaCff);
        //

        // CollideBorder

        //

        carBounds.setPosition(carBounds.getX() + MathUtils.cosDeg(carBounds.getRotation() + 90) * carSpeed * GameScreen.deltaCff,
                                carBounds.getY() + MathUtils.sinDeg(carBounds.getRotation() + 90) * carSpeed * GameScreen.deltaCff);


    }

    private float downSpeed() {
        if (carSpeed > acceleration * GameScreen.deltaCff)
            return carSpeed - acceleration * GameScreen.deltaCff;
        else if (carSpeed < -acceleration * GameScreen.deltaCff)
            return carSpeed + acceleration * GameScreen.deltaCff;
        return 0f;
    }

    private float sliceSpeed() {
        if (carSpeed > speedMax)
            return speedMax;
        if (carSpeed < -speedMax)
            return -speedMax;
        return carSpeed;
    }

    private float sliceSpeedOnGround(){
        if (!roadCollision) {
            if (carSpeed > groundSpeedMax)
                return carSpeed - groundBraking * GameScreen.deltaCff;
            else {
                return carSpeed;
            }
        }
        else return carSpeed;
    }
}

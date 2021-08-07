package com.yiotro.multiyoy.control;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.yiotro.multiyoy.view.GameScreen;

import java.util.Arrays;

public class CameraController {

    GameScreen gS;
    int mapLeft;
    float mapRight;
    int mapBottom;
    float mapTop;

    public CameraController(GameScreen gameScreen) {
        gS = gameScreen;
        mapLeft = 0;
        mapRight = gS.map.mapWidth;
        mapBottom = 0;
        mapTop = gS.map.mapHeight;
    }

    public void update() {
        float carX = gS.car.getBounds().getX() + gS.car.getBounds().getOriginX();
        float carY = gS.car.getBounds().getY() + gS.car.getBounds().getOriginY();

        float cameraHalfWidth = gS.camera.viewportWidth * .5f;
        float cameraHalfHeight = gS.camera.viewportHeight * .5f;
        float cameraLeft = (carX - cameraHalfWidth);
        float cameraRight = carX + cameraHalfWidth;
        float cameraBottom = carY - cameraHalfHeight;
        float cameraTop = carY + cameraHalfHeight;

        float camZoom = (Math.abs(gS.car.carController.carSpeed) + 75) / 75;

        float posX = carX;
        float posY = carY;

        if(cameraLeft <= mapLeft)
            posX = mapLeft + cameraHalfWidth;
        else if(cameraRight >= mapRight)
            posX = mapRight - cameraHalfWidth;

        if(cameraBottom <= mapBottom)
            posY = mapBottom + cameraHalfHeight;
        else if(cameraTop >= mapTop)
            posY = mapTop - cameraHalfHeight;

        gS.camera.position.set(posX, posY, 0);
//        gS.camera.zoom = camZoom;
    }
}

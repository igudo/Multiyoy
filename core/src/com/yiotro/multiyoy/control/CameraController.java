package com.yiotro.multiyoy.control;

import com.yiotro.multiyoy.view.GameScreen;

public class CameraController {

    GameScreen gS;

    public CameraController(GameScreen gameScreen) {
        this.gS = gameScreen;
    }

    public void update() {
        gS.camera.position.set(gS.car.getBounds().getX() + gS.car.getBounds().getOriginX(), gS.car.getBounds().getY() + gS.car.getBounds().getOriginY(), 0);
//        String x = String.valueOf(Math.round(car.getBounds().getX()*1.0/1.0));
//        String y = String.valueOf(Math.round(car.getBounds().getY()*1.0/1.0));
//        Gdx.app.log("#", x + " " + y + " " + camera.position.x);
        gS.camera.zoom = (Math.abs(gS.car.carController.carSpeed) + 75) / 75;
    }
}

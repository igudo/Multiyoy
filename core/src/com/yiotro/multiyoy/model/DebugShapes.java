package com.yiotro.multiyoy.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Polygon;
import com.yiotro.multiyoy.view.GameScreen;


public class DebugShapes {

    GameScreen gS;
    public ShapeRenderer shapeRenderer;
    float[] rgbShapeCar;
    float[] rgbShapeMapObject;


    public DebugShapes(GameScreen gameScreen){
        this.gS = gameScreen;
        Gdx.gl.glLineWidth(3);
        shapeRenderer = new ShapeRenderer();

    }

    public void draw(){
        drawCar();
        drawMapObjects();
    }

    private void drawCar(){
        rgbShapeCar = new float[]{0 / 255f, 0 / 255f, 0 / 255f, 1};
        if (gS.car.carController.grassCollision) {
            rgbShapeCar = new float[]{150 / 255f, 255 / 255f, 150 / 255f, 1};
        } else if (gS.car.carController.emptyCollision) {
            rgbShapeCar = new float[]{255 / 255f, 255 / 255f, 255 / 255f, 1};
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(rgbShapeCar[0], rgbShapeCar[1], rgbShapeCar[2], rgbShapeCar[3]);
        shapeRenderer.polygon(gS.car.realBound.getTransformedVertices());
        shapeRenderer.end();
    }

    private void drawMapObjects(){
        MapObjects objects = gS.map.getObjects();
        for (PolygonMapObject polygonMapObject : objects.getByType(PolygonMapObject.class)) {
            Polygon polygon = gS.map.getPolygon(polygonMapObject);

            rgbShapeMapObject = new float[]{100 / 255f, 100 / 255f, 100 / 255f, 1};
            if (gS.car.carController.grassCollision) {
                rgbShapeMapObject = new float[]{150 / 255f, 255 / 255f, 150 / 255f, 1};
            }

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(rgbShapeMapObject[0], rgbShapeMapObject[1], rgbShapeMapObject[2], rgbShapeMapObject[3]);
            shapeRenderer.polygon(polygon.getTransformedVertices());
            shapeRenderer.end();
        }
    }
}

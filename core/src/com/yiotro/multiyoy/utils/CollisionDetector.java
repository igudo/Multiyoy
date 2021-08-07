package com.yiotro.multiyoy.utils;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.FloatArray;
import com.yiotro.multiyoy.view.GameScreen;

public class CollisionDetector {

    GameScreen gS;

    public CollisionDetector(GameScreen gS) {
        this.gS = gS;
    }

    public void update(){
        MapObjects objects = gS.map.getObjects();
        for (PolygonMapObject polygonMapObject : objects.getByType(PolygonMapObject.class)) {
            Polygon polygon = gS.map.getPolygon(polygonMapObject);

            FloatArray object = new FloatArray(polygon.getTransformedVertices());
            FloatArray car = new FloatArray(gS.car.realBound.getTransformedVertices());
            if (Intersector.intersectPolygons(object, car)) {

                gS.car.carController.grassCollision = polygonMapObject.getName().equals("road");
                if (Intersector.intersectPolygonEdges(object, car)) {
                    gS.car.carController.grassCollision = !polygonMapObject.getName().equals("road");
                    gS.car.carController.emptyCollision = polygonMapObject.getName().equals("empty");
                }
            }
            else {
                gS.car.carController.grassCollision = false;
                gS.car.carController.emptyCollision = false;
            }
        }
    }
}

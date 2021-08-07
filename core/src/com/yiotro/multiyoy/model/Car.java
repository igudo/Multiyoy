package com.yiotro.multiyoy.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.yiotro.multiyoy.control.CarController;
import com.yiotro.multiyoy.view.GameScreen;



public class Car extends GameObject {

    public Polygon realBound;
    public CarController carController;

    public Car(GameScreen gameScreen, TextureRegion textureRegion, float x, float y, float w, float h) {
        super(gameScreen, textureRegion, x, y, w, h);
        realBound = getRealBound(w, h);
        realBound.setOrigin(w/2f, h/2f);
        carController = new CarController(bounds);
    }

    public void draw(Batch batch) {
        carController.handle(gS);
        realBound.setPosition(bounds.getX(), bounds.getY());
        realBound.setRotation(bounds.getRotation());
        super.draw(batch);
    }

    public Polygon getRealBound(float w, float h) {
        Polygon polygon = new Polygon();
        FloatArray vertices = new FloatArray();
        JsonReader json = new JsonReader();
        JsonValue base = json.parse(Gdx.files.internal("models/shapeOfCar.json"));
        JsonValue jsonVertices = base.get("rigidBodies").get(0).get("shapes").get(0).get("vertices");
        for (JsonValue coord : jsonVertices) {
            float x = coord.getFloat("x") * w;
            float y = coord.getFloat("y") * w;
            vertices.add(x, y);
        }
        polygon.setVertices(vertices.toArray());
        return polygon;
    }
}

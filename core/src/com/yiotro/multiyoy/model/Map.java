package com.yiotro.multiyoy.model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.FloatArray;
import com.yiotro.multiyoy.view.GameScreen;


import static com.yiotro.multiyoy.Constants.unitScale;


public class Map {
    public TiledMap map;
    public OrthogonalTiledMapRenderer renderer;
    public OrthographicCamera camera;
    public MapLayer objectLayer;

    public Map(GameScreen gameScreen){
        map = new TmxMapLoader().load("maps/map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);
    }

    public void draw(OrthographicCamera camera){
        renderer.setView(camera);
        renderer.render();
    }

    public MapObjects getObjects() {
        MapLayer objectLayer = map.getLayers().get("objects");
        return objectLayer.getObjects();
    }

    public Rectangle getRectangle(RectangleMapObject rectangleMapObject) {
        Rectangle rectangle = new Rectangle(rectangleMapObject.getRectangle());
        rectangle.setX(rectangle.getX() * unitScale);
        rectangle.setY(rectangle.getY() * unitScale);
        rectangle.setWidth(rectangle.getWidth() * unitScale);
        rectangle.setHeight(rectangle.getHeight() * unitScale);
        return rectangle;
    }

    public Polygon getPolygon(PolygonMapObject polygonMapObject) {
        Polygon polygon = new Polygon(polygonMapObject.getPolygon().getTransformedVertices());
        FloatArray vertices = new FloatArray();
        for (float value : polygon.getVertices()) {
            vertices.add(value * unitScale);
        }
        polygon.setVertices(vertices.toArray());
        return polygon;
    }


    public void dispose(){
        map.dispose();
        renderer.dispose();
    }
}

package com.yiotro.multiyoy.model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
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
    public float[] spawnCoords;
    public float mapWidth;
    public float mapHeight;
    GameScreen gS;


    public Map(GameScreen gameScreen){
        this.gS = gameScreen;
        map = new TmxMapLoader().load("maps/map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);

        MapProperties prop = map.getProperties();
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);
        mapWidth = prop.get("width", Integer.class) * tilePixelWidth * unitScale;
        mapHeight = prop.get("height", Integer.class) * tilePixelHeight * unitScale;

        spawnCoords = new float[]{(1 * tilePixelWidth + tilePixelWidth / 2f) * unitScale, (5 * tilePixelHeight + tilePixelHeight / 2f) * unitScale};
    }

    public void draw(){
        renderer.setView(gS.camera);
        renderer.render();
    }

    public MapObjects getObjects() {
        MapLayer objectLayer = map.getLayers().get("object");
        return objectLayer.getObjects();
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

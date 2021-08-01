package com.yiotro.multiyoy.model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


import static com.yiotro.multiyoy.Constants.unitScale;


public class Map {
    public TiledMap map;
    public OrthogonalTiledMapRenderer renderer;
    public OrthographicCamera camera;

    public Map(){
        map = new TmxMapLoader().load("maps/map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);

    }

    public void draw(OrthographicCamera camera){
        renderer.setView(camera);
        renderer.render();
    }

    public void dispose(){
        map.dispose();
        renderer.dispose();
    }
}

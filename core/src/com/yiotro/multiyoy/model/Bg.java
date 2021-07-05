package com.yiotro.multiyoy.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Bg extends GameObject {
    public Bg(String texturePath, float x, float y, float w, float h) {
        super(new Texture(Gdx.files.internal(texturePath)), x, y, w, h);
    }
}

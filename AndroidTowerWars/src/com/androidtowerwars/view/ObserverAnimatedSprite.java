package com.androidtowerwars.view;

import java.util.Observable;
import java.util.Observer;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.opengl.vertex.RectangleVertexBuffer;

import com.androidtowerwars.model.IObservableSprite;

public class ObserverAnimatedSprite extends AnimatedSprite implements Observer {

    public ObserverAnimatedSprite(float pX, float pY,
            TiledTextureRegion pTiledTextureRegion) {
        super(pX, pY, pTiledTextureRegion);
    }

    public ObserverAnimatedSprite(float pX, float pY,
            TiledTextureRegion pTiledTextureRegion,
            RectangleVertexBuffer pRectangleVertexBuffer) {
        super(pX, pY, pTiledTextureRegion, pRectangleVertexBuffer);
    }

    public ObserverAnimatedSprite(float pX, float pY, float pTileWidth,
            float pTileHeight, TiledTextureRegion pTiledTextureRegion) {
        super(pX, pY, pTileWidth, pTileHeight, pTiledTextureRegion);
    }

    public ObserverAnimatedSprite(float pX, float pY, float pTileWidth,
            float pTileHeight, TiledTextureRegion pTiledTextureRegion,
            RectangleVertexBuffer pRectangleVertexBuffer) {
        super(pX, pY, pTileWidth, pTileHeight, pTiledTextureRegion,
                pRectangleVertexBuffer);
    }

    public void update(Observable arg0, Object arg1) {
        IObservableSprite model = (IObservableSprite) arg0;
        setPosition(model.getX(), model.getY());
    }
}
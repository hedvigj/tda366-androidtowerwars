package com.androidtowerwars.view;

import java.util.Observable;
import java.util.Observer;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.vertex.RectangleVertexBuffer;

import com.androidtowerwars.model.IObservableSprite;
import com.androidtowerwars.model.TowerTile;

public class ObserverSprite extends Sprite implements Observer {

	public ObserverSprite(float pX, float pY, TextureRegion mSkeletonTextureRegion) {
		super(pX, pY, mSkeletonTextureRegion);
		// TODO Auto-generated constructor stub
	}

	public ObserverSprite(float pX, float pY, TextureRegion pTextureRegion,
			RectangleVertexBuffer pRectangleVertexBuffer) {
		super(pX, pY, pTextureRegion, pRectangleVertexBuffer);
		// TODO Auto-generated constructor stub
	}

	public ObserverSprite(float pX, float pY, float pWidth, float pHeight,
			TextureRegion pTextureRegion) {
		super(pX, pY, pWidth, pHeight, pTextureRegion);
		// TODO Auto-generated constructor stub
	}

	public ObserverSprite(float pX, float pY, float pWidth, float pHeight,
			TextureRegion pTextureRegion,
			RectangleVertexBuffer pRectangleVertexBuffer) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pRectangleVertexBuffer);
		// TODO Auto-generated constructor stub
	}

	public void update(Observable arg0, Object arg1) {
		IObservableSprite model = (IObservableSprite) arg0;
		setPosition(model.getX(), model.getY());
	}

}

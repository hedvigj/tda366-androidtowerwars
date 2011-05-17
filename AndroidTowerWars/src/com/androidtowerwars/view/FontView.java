package com.androidtowerwars.view;

import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;

import android.graphics.Color;
import android.graphics.Typeface;

public class FontView {

	public static Texture mFontTexture;
	public static Font mFont;
	
	public void loadResources(){
		mFontTexture = new Texture(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mFont = new Font(mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, true, Color.WHITE);

		WorldView.getInstance().getTextureManager().loadTexture(mFontTexture);
		WorldView.getInstance().getFontManager().loadFont(mFont);
	}
}

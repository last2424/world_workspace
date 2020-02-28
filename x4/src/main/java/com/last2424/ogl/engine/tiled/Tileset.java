package com.last2424.ogl.engine.tiled;

import java.util.ArrayList;

import org.joml.Vector2f;

import com.last2424.ogl.rendering.Texture;
import com.last2424.ogl.rendering.TextureRegion;

public class Tileset {
	
	public ArrayList<TextureRegion> tiles;
	public int tilewidth, tileheight;
	
	public Tileset() {
		this.tiles = new ArrayList<TextureRegion>();
	}
	
	public void setTileSize(int width, int height) {
		this.tilewidth = width;
		this.tileheight = height;
	}
	
	public void MakeTiles(Texture texture) {
		int textureWidth = texture.getWidth(),
			textureHeight = texture.getHeight(),
			tilesX = textureWidth / tilewidth,
			tilesY = textureHeight / tileheight;
		for (int y = 0; y < tilesY; y++) {
			for (int x = 0; x < tilesX; x++) {
				tiles.add(new TextureRegion(texture, new Vector2f(x*tilewidth, y*tileheight), new Vector2f(this.tilewidth, this.tileheight)));
			}
		}
	}
}

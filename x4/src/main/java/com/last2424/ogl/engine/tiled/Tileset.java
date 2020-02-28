package com.last2424.ogl.engine.tiled;

import java.util.ArrayList;

import org.joml.Vector2f;
import org.json.simple.JSONArray;

import com.last2424.ogl.rendering.Texture;
import com.last2424.ogl.rendering.TextureRegion;

public class Tileset {
	
	public TextureRegion[] tiles;
	public int tilewidth, tileheight;
	
	public Tileset(int countTiles) {
		tiles = new TextureRegion[countTiles];
	}
	
	public void setTileSize(int width, int height) {
		this.tilewidth = width;
		this.tileheight = height;
	}
	
	public void MakeTiles(Texture texture, int firstgid) {
		int textureWidth = texture.getWidth(),
			textureHeight = texture.getHeight(),
			tilesX = textureWidth / tilewidth,
			tilesY = textureHeight / tileheight,
			x = 0,
			y = 0;
		firstgid = firstgid-1;
		for (int i = 0; i < tiles.length-firstgid; i++) {
			tiles[i+firstgid] = new TextureRegion(texture, new Vector2f(x*tilewidth, y*tileheight), new Vector2f(this.tilewidth, this.tileheight));
			x++;
			if (x >= tilesX) {
				x = 0;
				y++;
			}
		}
	}
}

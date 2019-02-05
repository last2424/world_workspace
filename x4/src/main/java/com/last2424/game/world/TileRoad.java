package com.last2424.game.world;

import com.last2424.ogl.engine.Sprite;

public class TileRoad extends Tile {

	Sprite[] sprites;
	
	public TileRoad(int id, String tileName) {
		this(id, tileName, TileConfig.FLOOR, null);
	}
	
	public TileRoad(int id, String tileName, TileConfig config, Sprite sprite) {
		super(id, tileName, config, sprite);
		sprites = new Sprite[45];
		for(int i = 0; i < 45; i++) {
			sprites[i] = new Sprite("test/tiles/road/"+(i+1)+".png");
		}
		this.sprite = sprites[44];
	}
	
	public void SetOneOfSprites() {
	}

	
}

package com.last2424.game.world;

import com.last2424.ogl.engine.Sprite;

public class TileRoad extends Tile {

	Sprite[] sprites;
	
	public TileRoad(int id, String tileName, int layer) {
		this(id, tileName, TileConfig.FLOOR, null, layer);
	}
	
	public TileRoad(int id, String tileName, TileConfig config, Sprite sprite, int layer) {
		super(id, tileName, config, sprite);
		sprites = new Sprite[45];
		for(int i = 0; i < 45; i++) {
			sprites[i] = new Sprite("test/tiles/road/"+(i+1)+".png", layer);
		}
		this.sprite = sprites[44];
	}
	
	public void SetOneOfSprites() {
	}

	
}

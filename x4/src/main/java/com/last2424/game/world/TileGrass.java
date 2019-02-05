package com.last2424.game.world;

import com.last2424.ogl.engine.Sprite;

public class TileGrass extends Tile {

	Sprite[] sprites;
	
	public TileGrass(int id, String tileName) {
		this(id, tileName, TileConfig.FLOOR, null);
	}
	
	public TileGrass(int id, String tileName, TileConfig config, Sprite sprite) {
		super(id, tileName, config, sprite);
		sprites = new Sprite[22];
		for(int i = 0; i < 19; i++) {
			sprites[i] = new Sprite("test/tiles/grass/road"+(i+1)+".png");
		}
		sprites[19] = new Sprite("test/tiles/grass/slice.png");
		sprites[20] = new Sprite("test/tiles/grass/slice2.png");
		sprites[21] = new Sprite("test/tiles/grass/slice3.png");
		this.sprite = sprites[19];
	}
	
	public void SetOneOfSprites() {
	}

}

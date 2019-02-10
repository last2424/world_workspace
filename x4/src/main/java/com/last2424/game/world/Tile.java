package com.last2424.game.world;

import org.joml.Vector2f;

import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.rendering.SpriteBatch;

public class Tile {
	int id;
	String tileName;
	Sprite sprite;
	
	TileConfig config;
	
	public Tile(int id, String tileName, TileConfig config, String path) {
		this(id, tileName, config, new Sprite(path));
	}
	
	public Tile(int id, String tileName, TileConfig config, Sprite sprite) {
		this.id = id;
		this.tileName = tileName;
		this.sprite = sprite;
		this.config = config;
	}
	
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}
	
	public Sprite GetSprite() {
		return sprite;
	}
	
	public TileConfig GetTileConfig() {
		return config;
	}
	
	public void SetSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void SetPosition(float x, float y) {
		this.SetPosition(new Vector2f(x, y));
	}
	
	public void SetPosition(Vector2f position) {
		sprite.SetPosition(position.x, position.y);
	}
	
	public Vector2f GetPosition() {
		return sprite.GetPosition();
	}

	public enum TileConfig {
		FLOOR,
		SOLID,
		FLUID
	}
	
}

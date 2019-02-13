package com.last2424.game.world.environment;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import com.last2424.game.scenes.MainScene;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.rendering.SpriteBatch;

public class EnvironmentObject {
	Sprite sprite;
	String name;
	Vector2f position;
	
	public List<Rectangle> collisionZones;
	
	public float[] layerChangeCoordinates;
	
	
	public EnvironmentObject(String name, Sprite sprite, Vector2f position) {
		this.name = name;
		this.position = position;
		this.sprite = new Sprite(sprite.texture, sprite.color, this.position, sprite.texture.GetRegionSize(), 0);
		this.layerChangeCoordinates = null;
		this.collisionZones = new ArrayList<Rectangle>();
	}
	
	public void update() {
		if(layerChangeCoordinates != null) {
			if(layerChangeCoordinates[0] < MainScene.player.GetRectangle().getMinY()) {
				sprite.layer = -1;
			}
			if(layerChangeCoordinates[1] > MainScene.player.GetRectangle().getMaxY()) {
				sprite.layer = 1;
			}
		}
	}
	
	public void AddCollisionZone(int x, int y, int width, int height) {
		collisionZones.add(new Rectangle(x, y, width, height));
	}
	
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}
}

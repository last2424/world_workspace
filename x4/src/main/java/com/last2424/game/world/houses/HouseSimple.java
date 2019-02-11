package com.last2424.game.world.houses;

import java.awt.Color;

import org.joml.Vector2f;

import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.rendering.TextureRegion;

public class HouseSimple extends House {

	public HouseSimple(Vector2f position) {
		super("Simple", 2, position);
		TextureRegion region = new TextureRegion("houses/house4.png", new Vector2f(0, 0), new Vector2f(96, 96));
		this.SetSprite(0, new Sprite(region, Color.WHITE, position, new Vector2f(96, 96), 1));
		
		TextureRegion region2 = new TextureRegion("houses/house4.png", new Vector2f(0, 96), new Vector2f(96, 32));
		this.SetSprite(1, new Sprite(region2, Color.WHITE, new Vector2f(position.x, position.y+96), new Vector2f(96, 32), -1));
		
		this.AddCollisionZone((int)position.x, (int)position.y+47, 96, 49);
	}

}

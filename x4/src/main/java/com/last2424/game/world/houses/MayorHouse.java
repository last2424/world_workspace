package com.last2424.game.world.houses;

import java.awt.Color;

import org.joml.Vector2f;

import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.rendering.TextureRegion;

public class MayorHouse extends House {

	public MayorHouse(Vector2f position) {
		super("Mayor", 2, position);
		TextureRegion region = new TextureRegion("houses/house7.png", new Vector2f(0, 0), new Vector2f(272, 156));
		this.SetSprite(0, new Sprite(region, Color.WHITE, position, new Vector2f(272, 156), 1));
		
		TextureRegion region2 = new TextureRegion("houses/house7.png", new Vector2f(0, 156), new Vector2f(272, 32));
		this.SetSprite(1, new Sprite(region2, Color.WHITE, new Vector2f(position.x, position.y+156), new Vector2f(272, 32), -1));
		
		this.AddCollisionZone((int)position.x+16, (int)position.y+64, 241, 92);
	}

}

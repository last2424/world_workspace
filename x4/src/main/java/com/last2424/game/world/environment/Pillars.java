package com.last2424.game.world.environment;

import java.awt.Rectangle;

import org.joml.Vector2f;

import com.last2424.ogl.engine.Sprite;

public class Pillars extends EnvironmentObject {

	public Pillars(String name, Vector2f position) {
		super(name, new Sprite("houses/colons.png", 0), position);
		this.AddCollisionZone((int)position.x+14, (int)position.y+47, 14, 16);
		this.AddCollisionZone((int)position.x+46, (int)position.y+47, 14, 16);
		this.AddCollisionZone((int)position.x+78, (int)position.y+47, 14, 16);
		this.AddCollisionZone((int)position.x+110, (int)position.y+47, 14, 16);
		this.AddCollisionZone((int)position.x+142, (int)position.y+47, 14, 16);
		this.AddCollisionZone((int)position.x+174, (int)position.y+47, 14, 16);
		this.AddCollisionZone((int)position.x+206, (int)position.y+47, 14, 16);
		this.AddCollisionZone((int)position.x+238, (int)position.y+47, 14, 16);
		
		layerChangeCoordinates = new float[2];
		layerChangeCoordinates[0] = (int)position.y+47;
		layerChangeCoordinates[1] = (int)position.y+47;
	}

}

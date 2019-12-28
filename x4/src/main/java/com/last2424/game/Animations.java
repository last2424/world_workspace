package com.last2424.game;

import org.joml.Vector2i;

import com.last2424.ogl.engine.animation.Animation;
import com.last2424.ogl.engine.animation.ConfigJSON;

public class Animations {

	//Player animations
	public static Animation moveUp, moveDown, moveLeft, moveRight, idleUp, idleDown, idleLeft, idleRight;
	
	public static void InitAnimation() {
		idleUp = new Animation(new ConfigJSON("player.json", Textures.player, "idleUp"));
		idleDown = new Animation(new ConfigJSON("player.json", Textures.player, "idleDown"));
		idleLeft = new Animation(new ConfigJSON("player.json", Textures.player, "idleLeft"));
		idleRight = new Animation(new ConfigJSON("player.json", Textures.player, "idleRight"));
		moveUp = new Animation(new ConfigJSON("player.json", Textures.player, "moveUp"));
		moveDown = new Animation(new ConfigJSON("player.json", Textures.player, "moveDown"));
		moveLeft = new Animation(new ConfigJSON("player.json", Textures.player, "moveLeft"));
		moveRight = new Animation(new ConfigJSON("player.json", Textures.player, "moveRight"));
	}
	
}

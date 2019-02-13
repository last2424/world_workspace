package com.last2424.game.entities;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.List;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.last2424.game.scenes.MainScene;
import com.last2424.ogl.engine.GameMath;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.input.KeyboardHandler;
import com.last2424.ogl.input.MousePositionHandler;
import com.last2424.ogl.rendering.TextureRegion;

public class EntityPlayer extends Entity {

	public boolean inventoryOpened = false, infoOpened = false, merchantOpened = false;
	
	public EntityPlayer() {
		super("Player", 0, 5, 5, 5, EntityType.PLAYER, new Sprite[1], new Vector2f(100, 100), new Vector2f(64, 64));
		this.sprite[0] = new Sprite(new TextureRegion("char/Pen/side-move.png", new Vector2f(0, 0), new Vector2f(32, 32)), Color.WHITE, this.position, this.size, 0);
	}
	
	@Override
	public void update(float delta) {
		List<Rectangle> zones = MainScene.testHouse.GetCollisionZones();
		float moveX = 0, moveY = 0;
		tempPosition.set(this.GetPosition().x, this.GetPosition().y);
		
		if(KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_UP)) {
			moveY = -this.GetMoveSpeed()*delta-2;
		}
		if(KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
			moveY = this.GetMoveSpeed()*delta+2;
		}
		
		this.SetPosition(this.GetPosition().x, this.GetPosition().y+moveY);
		
		for(Rectangle zone : zones) {
			this.checkCollision(false, true, zone);
		}
		
		if(KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
			moveX = -this.GetMoveSpeed()*delta-2;
		}
		if(KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
			moveX = this.GetMoveSpeed()*delta+2;
		}
		
		this.SetPosition(this.GetPosition().x+moveX, this.GetPosition().y);
		
		for(Rectangle zone : zones) {
			this.checkCollision(true, false, zone);
		}
	}

}

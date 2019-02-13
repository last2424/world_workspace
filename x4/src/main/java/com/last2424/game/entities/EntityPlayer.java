package com.last2424.game.entities;

import java.awt.Rectangle;
import java.util.List;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.last2424.game.scenes.MainScene;
import com.last2424.ogl.engine.GameMath;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.input.KeyboardHandler;
import com.last2424.ogl.input.MousePositionHandler;

public class EntityPlayer extends Entity {

	public boolean inventoryOpened = false, infoOpened = false, merchantOpened = false;
	
	public EntityPlayer(String name, int lvl, int strength, int dexterity, int intelegence, EntityType entityType, Sprite sprite) {
		super(name, lvl, strength, dexterity, intelegence, entityType, sprite);
	}
	
	@Override
	public void update(float delta) {
		List<Rectangle> zones = MainScene.testHouse.GetCollisionZones();
		float moveX = 0, moveY = 0;
		tempPosition.set(this.GetPosition().x, this.GetPosition().y);
		
		if(KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_UP)) {
			moveY = -this.GetMoveSpeed();
		}
		if(KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
			moveY = this.GetMoveSpeed();
		}
		
		this.SetPosition(this.GetPosition().x, this.GetPosition().y+moveY);
		
		for(Rectangle zone : zones) {
			this.checkCollision(false, true, zone);
		}
		
		if(KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
			moveX = -this.GetMoveSpeed();
		}
		if(KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
			moveX = this.GetMoveSpeed();
		}
		
		this.SetPosition(this.GetPosition().x+moveX, this.GetPosition().y);
		
		for(Rectangle zone : zones) {
			this.checkCollision(true, false, zone);
		}
	}

}

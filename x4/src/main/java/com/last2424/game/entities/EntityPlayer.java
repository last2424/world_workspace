package com.last2424.game.entities;

import org.lwjgl.glfw.GLFW;

import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.input.KeyboardHandler;

public class EntityPlayer extends Entity {

	public boolean inventoryOpened = false, infoOpened = false, merchantOpened = false;
	
	public EntityPlayer(String name, int lvl, int strength, int dexterity, int intelegence, EntityType entityType, Sprite sprite) {
		super(name, lvl, strength, dexterity, intelegence, entityType, sprite);
	}
	
	@Override
	public void update(float delta) {
		float moveX = 0, moveY = 0;
		if(KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_UP)) {
			moveY = -this.GetMoveSpeed();
		}
		if(KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
			moveY = this.GetMoveSpeed();
		}
		if(KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
			moveX = -this.GetMoveSpeed();
		}
		if(KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
			moveX = this.GetMoveSpeed();
		}
		this.SetPosition(this.GetPosition().x+moveX, this.GetPosition().y+moveY);
	}

}

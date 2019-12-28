package com.last2424.game.entity;

import java.awt.Color;
import java.awt.event.KeyEvent;

import org.joml.Vector2f;
import org.joml.Vector2i;

import com.last2424.game.Animations;
import com.last2424.game.Textures;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.input.KeyboardHandler;

public class EntityPlayer extends Entity {
	
	
	public EntityPlayer() {
		this.sprite = new Sprite(Textures.player, Color.WHITE, new Vector2f(0, 0), new Vector2f(32, 32), 0);
		this.currentAnimation = Animations.idleDown;
		this.position = new Vector2f();
	}
	
	public void update(float delta) {
		super.update(delta);
		
		Vector2f speed = new Vector2f(0, 0);
		Vector2i direction = new Vector2i(0, 0);
			
		if(KeyboardHandler.isKeyDown(KeyEvent.VK_W)) {
			currentAnimation = Animations.moveUp;
			direction.y = -1;
			speed.y = -50;
		} 
		
		if(KeyboardHandler.isKeyDown(KeyEvent.VK_S)) {
			currentAnimation = Animations.moveDown;
			direction.y = 1;
			speed.y = 50;
		}
		
		if(KeyboardHandler.isKeyDown(KeyEvent.VK_A)) {
			currentAnimation = Animations.moveLeft;
			direction.x = -1;
			speed.x = -50;
		}
		
		if(KeyboardHandler.isKeyDown(KeyEvent.VK_D)) {
			currentAnimation = Animations.moveRight;
			direction.x = 1;
			speed.x = 50;
		}
		
		if (direction.x == 0 && direction.y == 0) {
			if (currentAnimation == Animations.moveRight) {
				currentAnimation = Animations.idleRight;
			}
			
			if (currentAnimation == Animations.moveLeft) {
				currentAnimation = Animations.idleLeft;
			}
			
			if (currentAnimation == Animations.moveDown) {
				currentAnimation = Animations.idleDown;
			}
			
			
			if (currentAnimation == Animations.moveUp) {
				currentAnimation = Animations.idleUp;
			}
		}
		this.position.x += speed.x * delta;
		this.position.y += speed.y * delta;
		
		currentAnimation.update(delta);
		//System.out.println(Animations.idleDown.GetFrame());
		sprite.SetFrame(currentAnimation.GetFrame());
	}
	
}

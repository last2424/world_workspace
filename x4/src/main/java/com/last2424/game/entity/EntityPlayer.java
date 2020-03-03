package com.last2424.game.entity;

import java.awt.Color;
import java.awt.event.KeyEvent;

import org.joml.Vector2f;
import org.joml.Vector2i;

import com.last2424.game.Animations;
import com.last2424.game.Textures;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.engine.tiled.Map;
import com.last2424.ogl.input.KeyboardHandler;

public class EntityPlayer extends Entity {

	Vector2i direction = new Vector2i(0, 0);
	
	public EntityPlayer() {
		this.sprite = new Sprite(Textures.player, Color.WHITE, new Vector2f(0, 0), new Vector2f(32, 32), 0);
		this.currentAnimation = Animations.idleDown;
		this.position = new Vector2f(64,64);
	}
	public void PhysicUpdate(float delta,Map map) {
		direction = new Vector2i(0, 0);
		Vector2f speed = new Vector2f(0, 0);
			
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
		Vector2f positionTemp = new Vector2f(this.position.x,this.position.y);
		positionTemp.x += speed.x * delta;
		if(map.IsSolid(positionTemp, new Vector2f(32,32))) positionTemp.x = position.x;
		positionTemp.y += speed.y * delta;
		if(map.IsSolid(positionTemp, new Vector2f(32,32))) positionTemp.y = position.y;
		
		this.position = positionTemp;
	}
	public void update(float delta) {
		super.update(delta);
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
		currentAnimation.update(delta);
		//System.out.println(Animations.idleDown.GetFrame());
		sprite.SetFrame(currentAnimation.GetFrame());
	}
	
}

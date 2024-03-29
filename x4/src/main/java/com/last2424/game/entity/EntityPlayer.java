package com.last2424.game.entity;

import java.awt.Color;
import java.awt.event.KeyEvent;

import org.joml.Math;
import org.joml.Vector2f;
import org.joml.Vector2i;

import com.last2424.game.Animations;
import com.last2424.game.Textures;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.engine.tiled.Map;
import com.last2424.ogl.engine.tiled.ObjectSolid;
import com.last2424.ogl.input.KeyboardHandler;

public class EntityPlayer extends Entity {

	Vector2i direction = new Vector2i(0, 0);
	Vector2f addition = new Vector2f(0,0);
	ObjectSolid lastObject ;
	public EntityPlayer() {
		this.sprite = new Sprite(Textures.player, Color.WHITE, new Vector2f(0, 0), new Vector2f(32, 32), 0);
		this.currentAnimation = Animations.idleDown;
		this.position = new Vector2f(64,64);
	}
	public void PhysicUpdate(float delta,Map map) {
		direction = new Vector2i(0, 0);
		Vector2f speed = new Vector2f(0, 0);
		if(KeyboardHandler.isKeyDown(KeyEvent.VK_W)) {
			lastObject = null;
			currentAnimation = Animations.moveUp;
			direction.y = -1;
			speed.y = -50;
		} 
		
		if(KeyboardHandler.isKeyDown(KeyEvent.VK_S)) {
			lastObject = null;
			currentAnimation = Animations.moveDown;
			direction.y = 1;
			speed.y = 50;
		}
		
		if(KeyboardHandler.isKeyDown(KeyEvent.VK_A)) {
			lastObject = null;
			currentAnimation = Animations.moveLeft;
			direction.x = -1;
			speed.x = -50;
		}
		
		if(KeyboardHandler.isKeyDown(KeyEvent.VK_D)) {
			lastObject = null;
			currentAnimation = Animations.moveRight;
			direction.x = 1;
			speed.x = 50;
		}
		speed.mul(delta);		
		Vector2f intSpeed = new Vector2f(speed.x,speed.y);
		if(intSpeed.x>=0) intSpeed.x = Math.floor(speed.x);
		else intSpeed.x = Math.ceil(speed.x);
		if(intSpeed.y>=0) intSpeed.y = Math.floor(speed.y);
		else intSpeed.y = Math.ceil(speed.y);

		Vector2f floatSpeed = new Vector2f(speed.x,speed.y);
		if(floatSpeed.x>=0) floatSpeed.x = speed.x - intSpeed.x;
		else floatSpeed.x = intSpeed.x+speed.x; //
		if(floatSpeed.y>=0) floatSpeed.y = speed.y - intSpeed.y;
		else floatSpeed.y = intSpeed.y+speed.y; //
		
		if(speed.x == 0)
			this.addition.x = 0;
		if(speed.y == 0)
			this.addition.y = 0;
		
		this.addition.add(floatSpeed);
		if(addition.x>=1) {
			
			intSpeed.x+=1;
			this.addition.x = this.addition.x-1;
		}
		else if(addition.x<-1) {
			
			intSpeed.x-=1;
			this.addition.x = this.addition.x+1;
		}
		if(addition.y>=1) {
			
			intSpeed.y+=1;
			this.addition.y = this.addition.y-1;
		}
		else if(addition.y<-1) {
			
			intSpeed.y-=1;
			this.addition.y = this.addition.y+1;
		}
		Vector2f positionTemp = new Vector2f(this.position.x,this.position.y);
		int stepx = (int) Math.abs(intSpeed.x);

		if(stepx>0) {
			int dirX = 0;
			if(intSpeed.x>=0) dirX = 1;
			else dirX = -1;
			for(int i=0;i<stepx;i++) {

				positionTemp.x += dirX;
				if(map.IsSolid(positionTemp, new Vector2f(32,32))) 
				{	
					lastObject = map.getLastSolidObject();
					lastObject.Colide(delta);

				//	System.out.println(lastObject.id);
					positionTemp.x -= dirX;
					break;
				}
			}
		}
		int stepY = (int) Math.abs(intSpeed.y);

		if(stepY>0) {
			int dirY = 0;
			if(intSpeed.y>=0) dirY = 1;
			else dirY = -1;
			for(int i=0;i<stepY;i++) {

				positionTemp.y += dirY;
				if(map.IsSolid(positionTemp, new Vector2f(32,32))) 
				{	
					lastObject = map.getLastSolidObject();
					lastObject.Colide(delta);
					positionTemp.y -= dirY;
					break;
				}
			}
		}
		if(KeyboardHandler.isKeyDown(KeyEvent.VK_E)) {
			if(lastObject!=null) { lastObject.Use(delta);
				System.out.println(lastObject.id);
			}
		}
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

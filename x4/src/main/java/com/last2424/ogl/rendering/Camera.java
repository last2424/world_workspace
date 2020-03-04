package com.last2424.ogl.rendering;

import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

import com.last2424.game.entity.Entity;
import com.last2424.ogl.Window;

public class Camera {
	
	public Entity target;
	public float speed = 0.5f;
	public Vector2f position;
	public Vector2f size;
	public Camera(){
		this.position = new Vector2f(0, 0);
		this.size = new Vector2f(Window.width/2, Window.height/2);
	}
	Vector2f max = new Vector2f(0,0);
	public void SetMaxCamera(Vector2f max) {
		this.max = max;
	}
	public void update(float delta) {
		
		this.size = new Vector2f(Window.width/2, Window.height/2);
		if (target != null) {
			Vector2f newPos = new Vector2f(target.getHalf().x -(this.size.x/2),target.getHalf().y-(this.size.y/2));
			if(newPos.x <=0) newPos.x = 0;
			if(newPos.y<=0) newPos.y = 0;
			if(max.x>=1 && newPos.x+this.size.x>=max.x) newPos.x = max.x-this.size.x;
			if(max.y>=1 && newPos.y+this.size.y>=max.y) newPos.y = max.y-this.size.y;
			Vector2f dir = new Vector2f(0,0);
			newPos.sub(this.position,dir);
			Vector2f direction = new Vector2f(dir.x,dir.y);
			direction = direction.normalize();
			if(dir.x<=0.01f) 
				this.position.x = newPos.x;
			else
				this.position.x+=direction.x*speed;
			if(dir.y<=0.01f) 
				this.position.y = newPos.y;
			else
				this.position.y+=direction.y*speed;
			
		}
	}
	
	public void render(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(position.x, size.x+position.x, size.y+position.y, position.y, -50, 50);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	public void renderUI() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, size.x, size.y, 0, -50, 50);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
	}
}
package com.last2424.ogl.rendering;

import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

import com.last2424.game.entity.Entity;
import com.last2424.ogl.Window;

public class Camera {
	
	public Entity target;
	
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
	public void update() {
		this.size = new Vector2f(Window.width/2, Window.height/2);
		if (target != null) {
			this.position = new Vector2f(target.position.x-(this.size.x/2),target.position.y-(this.size.y/2));
			if(this.position.x<=0) this.position.x = 0;
			if(this.position.y<=0) this.position.y = 0;
			if(max.x>=1 && this.position.x+this.size.x>=max.x) this.position.x = max.x-this.size.x;
			if(max.y>=1 && this.position.y+this.size.y>=max.y) this.position.y = max.y-this.size.y;
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
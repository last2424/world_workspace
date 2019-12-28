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
	
	public void update() {
		if (target != null) this.position = target.position;
		this.size = new Vector2f(Window.width/2, Window.height/2);
	}
	
	public void render(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(position.x, size.x+position.x, size.y+position.y, position.y, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
}
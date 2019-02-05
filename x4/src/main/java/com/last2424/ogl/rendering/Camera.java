package com.last2424.ogl.rendering;

import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

public class Camera {
	public Vector2f position;
	public Vector2f size;
	public Camera(Vector2f pos, Vector2f size){
		this.position = pos;
		this.size = size;
	}
	public void cameradraw(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(position.x, size.x+position.x, size.y+position.y, position.y, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
}
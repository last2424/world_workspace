package com.last2424.ogl.engine.animation;

import org.joml.Vector2f;

public class Frame {
	float x, y, width, height;
	
	public Frame(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public float GetX() {
		return x;
	}
	
	public float GetY() {
		return y;
	}
	
	public float GetWidth() {
		return width;
	}
	
	public float GetHeight() {
		return height;
	}
	
	public Vector2f GetPosition() {
		return new Vector2f(x, y);
	}
	
	public Vector2f GetSize() {
		return new Vector2f(width, height);
	}
}

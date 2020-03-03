package com.last2424.utils;

import org.joml.Vector2f;

public class Rectangle {
	public Vector2f pos;
	public Vector2f size;
	public Rectangle(){
		pos = new Vector2f(0,0);
		size = new Vector2f(0,0);
	}
	public Rectangle(Vector2f pos,Vector2f size) {
		this.pos = pos;
		this.size = size;
	}
	public boolean isColid(Rectangle rect) {
		/*
		 * rect1.x < rect2.x + rect2.width &&
   rect1.x + rect1.width > rect2.x &&
   rect1.y < rect2.y + rect2.height &&
   rect1.y + rect1.height > rect2.y
		 */
		return pos.x < rect.pos.x + rect.size.x &&
				pos.x + size.x > rect.pos.x &&
				pos.y  < rect.pos.y+ rect.size.y &&
				pos.y  + size.y > rect.pos.y;
		
	}
}

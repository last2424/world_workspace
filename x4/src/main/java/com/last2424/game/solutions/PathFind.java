package com.last2424.game.solutions;


import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

import com.last2424.ogl.rendering.SpriteBatch;

public class PathFind {
	Vector2f start, end;
	List<Vector2f> points = new ArrayList<Vector2f>();
	
	public PathFind() {
		
	}
	
	public void find(Vector2f start, Vector2f end) {
		this.start = start;
		this.end = end;
	}
	
	public void debugDraw(SpriteBatch batch) {
		batch.drawLine(start, end, 5);
	}
}

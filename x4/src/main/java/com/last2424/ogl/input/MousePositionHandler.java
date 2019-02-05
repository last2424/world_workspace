package com.last2424.ogl.input;

import java.awt.Rectangle;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MousePositionHandler extends GLFWCursorPosCallback {

	static double x;
	static double y;
	
	@Override
	public void invoke(long window, double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static double getX() {
		return x;
	}
	
	public static double getY() {
		return y;
	}
	
	public static boolean Intersects(Rectangle rect) {
		return new Rectangle((int)x, (int)y, 1, 1).intersects(rect);
	}

}

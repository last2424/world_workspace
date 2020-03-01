package com.last2424.ogl.input;

import org.lwjgl.glfw.GLFWKeyCallback;

import com.last2424.ogl.Window;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

public class KeyboardHandler extends GLFWKeyCallback {
	private static boolean[] pressedFirst = new boolean[65536];
	private static boolean[] releasedFirst = new boolean[65536];
	private static boolean[] keys = new boolean[65536];
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		if(key <= 0) return;
		if(action != GLFW_RELEASE){ 
			pressedFirst[key] = !keys[key] && !pressedFirst[key];
			releasedFirst[key] = false;
		}
		else {
			pressedFirst[key] = false;
			releasedFirst[key] = keys[key] && !releasedFirst[key];
		}
		keys[key] = action != GLFW_RELEASE;
		
	}
	
	public static boolean isKeyDown(int keycode) {
		return keys[keycode];
	}
	
	public static boolean isKeyPressed(int keycode)
	{
		boolean t = pressedFirst[keycode];
		pressedFirst[keycode] = false;
	    return t;
	}

	public static boolean isKeyReleased(int keycode)
	{
		boolean t = !keys[keycode] && releasedFirst[keycode];
		releasedFirst[keycode] = false;
	    return t;
	}
	
}

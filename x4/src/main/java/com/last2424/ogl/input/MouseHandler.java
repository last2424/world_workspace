package com.last2424.ogl.input;

import org.lwjgl.glfw.GLFWMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.*;

public class MouseHandler extends GLFWMouseButtonCallback {
	private static boolean[] pressedFirst = new boolean[3];
	private static boolean[] releasedFirst = new boolean[3];
	private static boolean[] buttons = new boolean[3];
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		if(action != GLFW_RELEASE){ 
			pressedFirst[button] = !buttons[button] && !pressedFirst[button];
			releasedFirst[button] = false;
		}
		else {
			pressedFirst[button] = false;
			releasedFirst[button] = buttons[button] && !releasedFirst[button];
		}
		buttons[button] = action != GLFW_RELEASE;
	}
	
	public static boolean isMouseDown(int button) {
		return buttons[button];
	}
	
	public static boolean isMousePressed(int button)
	{
		boolean t = pressedFirst[button];
		pressedFirst[button] = false;
	    return t;
	}

	public static boolean isMouseReleased(int button)
	{
		boolean t = !buttons[button] && releasedFirst[button];
		releasedFirst[button] = false;
	    return t;
	}

}

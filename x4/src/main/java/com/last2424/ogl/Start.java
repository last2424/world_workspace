package com.last2424.ogl;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import com.last2424.game.Logic;
import com.last2424.game.scenes.MainScene;
import com.last2424.ogl.input.KeyboardHandler;
import com.last2424.ogl.input.MouseHandler;
import com.last2424.ogl.input.MousePositionHandler;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Start {

	public static long window;
	private GLFWWindowSizeCallback windowSizeCallback;
	private GLFWKeyCallback keyCallback;
	private GLFWMouseButtonCallback mouseButtonCallBack;
	private GLFWCursorPosCallback cursorPosCallback;
	public static int width;
	public static int height;
	private boolean resized = true;
	public boolean running = true;

	public Timer timer;
	
	public void run(Logic logic) {
		System.out.println("LWJGL " + Version.getVersion() + "!");
		
		init(logic);
		loop(logic);
		
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		
		glfwTerminate();
		glfwSetErrorCallback(null).free();
		
	}

	private void init(Logic logic) {
		GLFWErrorCallback.createPrint(System.err).set();
		
		if(!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		window = glfwCreateWindow(640, 480, "", NULL, NULL);
		if(window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");
		
		glfwSetKeyCallback(window, keyCallback = new KeyboardHandler());
		
		glfwSetMouseButtonCallback(window, mouseButtonCallBack = new MouseHandler());
		
		glfwSetCursorPosCallback(window, cursorPosCallback = new MousePositionHandler());
		
		glfwSetWindowSizeCallback(window, windowSizeCallback = new GLFWWindowSizeCallback() {
			
			@Override
			public void invoke(long arg0, int arg1, int arg2) {
				resized = true;
			}
		});
		
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);
			
			glfwGetWindowSize(window, pWidth, pHeight);
			
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			
			glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
			width = pWidth.get(0);
			height = pHeight.get(0);
		}
		
		glfwMakeContextCurrent(window);
		
		glfwSwapInterval(0);
		
		
		
		glfwShowWindow(window);
		
	}
	
	private void loop(Logic logic) {
		GL.createCapabilities();
		SyncTimer synctimer  = new SyncTimer(SyncTimer.LWJGL_GLFW);
		this.timer = new Timer();
		
		
		logic.create();
		while(running) {
			if(resized) {
				IntBuffer widthBuffer = BufferUtils.createIntBuffer(1), heightBuffer = BufferUtils.createIntBuffer(1);
				glfwGetWindowSize(window, widthBuffer, heightBuffer);
				width = widthBuffer.get(0);
				height = heightBuffer.get(0);
				GL11.glViewport(0, 0, width, height);
				glMatrixMode(GL_PROJECTION);
				glLoadIdentity();
				glOrtho(0, width, height, 0, -1, 1);
				
				glMatrixMode(GL_MODELVIEW);
				glLoadIdentity();
				glEnable(GL_TEXTURE_2D);
				glEnable(GL11.GL_BLEND);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
				glEnable(GL11.GL_ALPHA_TEST); 
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	            GL11.glDepthFunc(GL11.GL_LESS);
				GL11.glAlphaFunc(GL11.GL_NOTEQUAL, 0);
				resized = false;
			}

			
			update(logic);
			this.timer.updateUPS();
			
			render(logic);		
			this.timer.updateFPS();
			this.timer.update();
			//System.out.println(this.timer.getUPS() + " ups, " + this.timer.getFPS() + " fps");
			
			try {
				synctimer.sync(60);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			if(glfwWindowShouldClose(window)){
		        running = false;
		    }
		}
	}
	
	private void update(Logic logic) {
		logic.update(this.timer.getDelta());
		glfwPollEvents();
	}
	
	private void render(Logic logic) {
		logic.render();
		glfwSwapBuffers(window);
	}
	
	public static void main(String[] args) {
			Logic logic = new Logic();
			Logic.scene = new MainScene();
			new Start().run(logic);
	}

}

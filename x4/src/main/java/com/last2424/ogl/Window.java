package com.last2424.ogl;

import org.joml.Matrix4f;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import com.last2424.game.Game;
import com.last2424.mmo.client.ClientChannelInitializer;
import com.last2424.mmo.client.ClientHandler;
import com.last2424.ogl.engine.IMain;
import com.last2424.ogl.input.KeyboardHandler;
import com.last2424.ogl.input.MouseHandler;
import com.last2424.ogl.input.MousePositionHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
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

public class Window {
	public static final String PATH = Window.class.getResource("").getPath().replaceFirst("ogl/", "assets/");
	
	
	public static long window;
	private GLFWWindowSizeCallback windowSizeCallback;
	private GLFWKeyCallback keyCallback;
	private GLFWMouseButtonCallback mouseButtonCallBack;
	private GLFWCursorPosCallback cursorPosCallback;
	public static int width;
	public static int height;
	private boolean resized = true;
	public boolean running = true;

	public static Timer timer;
	
	Channel channel;
	Bootstrap bootstrap;
	boolean isNetwork = false;
	
	public void connecting(IMain main) throws IOException, InterruptedException {
		isNetwork = true;
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			bootstrap = new Bootstrap()
					.group(group)
					.channel(NioSocketChannel.class)
					.handler(new ClientChannelInitializer());
			
			channel = bootstrap.connect("localhost", 8080).sync().channel();
			run(main);
		}
		finally {
			group.shutdownGracefully();
		}
	}
	
	public void run(IMain main) throws IOException, InterruptedException {
		System.out.println("LWJGL " + Version.getVersion() + "!");
		
		init(main);
		loop(main);
		
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		
		glfwTerminate();
		glfwSetErrorCallback(null).free();
		
	}

	private void init(IMain main) {
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
		glfwSwapBuffers(window);
		glfwSwapInterval(0);
		
		
		
		glfwShowWindow(window);
		
	}
	
	private void loop(IMain main) throws IOException, InterruptedException {
		GL.createCapabilities();
		SyncTimer synctimer  = new SyncTimer(SyncTimer.LWJGL_GLFW);
		this.timer = new Timer();
		
		
		main.create();
		if (isNetwork) {
			main.setChannel(channel);
		}
		while(running) {
			if(resized) {
				
				IntBuffer widthBuffer = BufferUtils.createIntBuffer(1), heightBuffer = BufferUtils.createIntBuffer(1);
				glfwGetWindowSize(window, widthBuffer, heightBuffer);
				width = widthBuffer.get(0);
				height = heightBuffer.get(0);
				GL11.glViewport(0, 0, width, height);
				glMatrixMode(GL_PROJECTION);
				glLoadIdentity();
				glOrtho(0, width, height, 0, -50, 50);
				
				glMatrixMode(GL_MODELVIEW);
				glLoadIdentity();
				//glEnable(GL_TEXTURE_2D);
				glEnable(GL11.GL_BLEND);
				glEnable(GL11.GL_DEPTH_TEST);
				glEnable(GL11.GL_ALPHA_TEST); 
				glEnable(GL11.GL_LINE_SMOOTH);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	            GL11.glDepthFunc(GL11.GL_LEQUAL);
				GL11.glAlphaFunc(GL11.GL_NOTEQUAL, 0);
				resized = false;
			}

			
			update(main);
			this.timer.updateUPS();
			
			render(main);		
			this.timer.updateFPS();
			this.timer.update();
			//System.out.println(this.timer.getUPS() + " ups, " + this.timer.getFPS() + " fps");
			
			try {
				synctimer.sync(60);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			if(glfwWindowShouldClose(window)){
				dispose(main);
		        running = false;
		    }
		}
	}
	
	private void dispose(IMain main) {
		main.dispose();
	}

	private void update(IMain main) {
		main.update(this.timer.getDelta());
		glfwPollEvents();
	}
	
	private void render(IMain main) {
		main.render();
		glfwSwapBuffers(window);
	}

}
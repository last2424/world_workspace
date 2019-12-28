package com.last2424.game;

import java.io.IOException;

import com.last2424.ogl.Window;

public class Start {

	public static void main(String[] args) throws IOException {
		Game logic = new Game();
		new Window().run(logic);
	}
	
}

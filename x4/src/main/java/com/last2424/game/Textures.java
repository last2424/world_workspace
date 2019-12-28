package com.last2424.game;

import java.io.IOException;

import com.last2424.ogl.rendering.Texture;

public class Textures {

	public static Texture player, tree;
	
	public static void InitTextures()
	{
		try {
			player = new Texture("Player.png");
			tree = new Texture("tree2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

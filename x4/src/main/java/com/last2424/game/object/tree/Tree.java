package com.last2424.game.object.tree;

import java.awt.Color;

import org.joml.Vector2f;

import com.last2424.game.Textures;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.engine.objects.GameObject;

public class Tree extends GameObject {

	public Tree() {
		this.sprite = new Sprite(Textures.tree, Color.WHITE, new Vector2f(0, 0), new Vector2f(32, 48), 0);
	}
	
}

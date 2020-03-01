package com.last2424.game.scenes;

import java.awt.Font;
import java.io.IOException;

import org.joml.Vector2f;

import com.last2424.game.entity.EntityPlayer;
import com.last2424.game.object.tree.Tree;
import com.last2424.ogl.Window;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.engine.scene.Scene;
import com.last2424.ogl.engine.tiled.Map;
import com.last2424.ogl.rendering.Camera;
import com.last2424.ogl.rendering.SpriteBatch;
import com.last2424.ogl.rendering.TrueTypeFont;

public class MainScene implements Scene {

	EntityPlayer player;
	Tree tree;
	Camera camera;
	SpriteBatch batch;
	
	Map map;
	
	TrueTypeFont font;
	Font standartFont;
	
	@Override
	public void create() throws IOException {
		player = new EntityPlayer();
		tree = new Tree();
		batch = new SpriteBatch();
		camera = new Camera();
		standartFont = new Font("Arial", Font.BOLD, 14);
		font = new TrueTypeFont(standartFont, true);
		map = Map.Load("testmap");
	}

	@Override
	public void update(float delta) {
		player.update(delta);
	}

	@Override
	public void render() {
		batch.clearWindow();
		batch.setWindowColor(0.42f, 0.69f, 0.89f, 1.0f);
		camera.render();
		//tree.render(batch);
		map.draw(batch,camera.position,camera.size);
		player.render(batch);
		batch.setColor(255, 0, 0, 1);
		font.drawString(0, 0, Integer.toString(Window.timer.getFPS()), 1.0f, 1.0f);
		
	}

}

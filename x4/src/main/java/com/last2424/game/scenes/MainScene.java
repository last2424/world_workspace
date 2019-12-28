package com.last2424.game.scenes;

import java.io.IOException;

import org.joml.Vector2f;

import com.last2424.game.entity.EntityPlayer;
import com.last2424.game.object.tree.Tree;
import com.last2424.ogl.Window;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.engine.scene.Scene;
import com.last2424.ogl.rendering.Camera;
import com.last2424.ogl.rendering.SpriteBatch;

public class MainScene implements Scene {

	EntityPlayer player;
	Tree tree;
	Camera camera;
	SpriteBatch batch;
	
	@Override
	public void create() throws IOException {
		player = new EntityPlayer();
		tree = new Tree();
		batch = new SpriteBatch();
		camera = new Camera();
	}

	@Override
	public void update(float delta) {
		camera.update();
		player.update(delta);
	}

	@Override
	public void render() {
		batch.clearWindow();
		batch.setWindowColor(0.42f, 0.69f, 0.89f, 1.0f);
		camera.render();
		tree.render(batch);
		player.render(batch);
	}

}

package com.last2424.game.scenes;

import java.awt.Font;
import java.io.IOException;

import org.joml.Vector2f;

import com.last2424.game.entity.EntityPlayer;
import com.last2424.game.object.tree.Tree;
import com.last2424.game.solutions.PathFind;
import com.last2424.ogl.Window;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.engine.audio.AudioManager;
import com.last2424.ogl.engine.audio.AudioSource;
import com.last2424.ogl.engine.scene.Scene;
import com.last2424.ogl.engine.tiled.IAction;
import com.last2424.ogl.engine.tiled.Map;
import com.last2424.ogl.engine.tiled.ObjectSolid;
import com.last2424.ogl.engine.tiled.TypeAction;
import com.last2424.ogl.rendering.Camera;
import com.last2424.ogl.rendering.SpriteBatch;
import com.last2424.ogl.rendering.TrueTypeFont;

public class MainScene implements Scene {

	EntityPlayer player;
	Tree tree;
	Camera camera;
	SpriteBatch batch;
	
	Map map;
	PathFind f;
	
	
	TrueTypeFont font;
	Font standartFont;
	
	int buffer;
	AudioSource source;
	
	boolean a = false;
	
	@Override
	public void create() throws IOException {
		player = new EntityPlayer();
		tree = new Tree();
		batch = new SpriteBatch();
		camera = new Camera();
		camera.FadeIn(1.0f);
		camera.target = player;
		standartFont = new Font("Arial", Font.BOLD, 14);
		font = new TrueTypeFont(standartFont, true);
		map = Map.Load("testmap");
		map.getObject(58).AddEvent(TypeAction.USE,new IAction() {
			
			@Override
			public void Activate(ObjectSolid solid, float delta) {
				System.out.println("USE");
			}
		});
		camera.SetMaxCamera(map.getSize());
		buffer = AudioManager.loadSound("music.wav");
		source = new AudioSource();
		source.setVolume(0.1f);
		source.play(buffer);
		f = new PathFind();
	}

	@Override
	public void update(float delta) {
		f.find(new Vector2f(129, 64), new Vector2f(224, 64),map,5);
		map.Update(delta);
		player.PhysicUpdate(delta,map);
		player.update(delta);
		camera.update(delta);
	}

	@Override
	public void render() {
		batch.clearWindow();
		batch.setWindowColor(0.42f, 0.69f, 0.89f, 1.0f);
		camera.render();
		//tree.render(batch);
		map.drawBackgrond(batch,camera.position,camera.size);
		map.draw(batch,camera.position,camera.size,player.position.y,true);
		player.render(batch);
		map.draw(batch,camera.position,camera.size,player.position.y,false);
		batch.setColor(1.0f, 1.0f, 1.0f, 1);
		f.debugDraw(batch);
		camera.postRender();
		camera.renderUI();
		font.drawString(0, 0, Integer.toString(Window.timer.getFPS()), 1.0f, 1.0f);
		
	}

}

package com.last2424.game;

import java.io.IOException;

import com.last2424.game.scenes.MainScene;
import com.last2424.ogl.engine.IMain;
import com.last2424.ogl.engine.audio.AudioManager;
import com.last2424.ogl.engine.scene.Scene;
import com.last2424.ogl.engine.tiled.Map;

import io.netty.channel.Channel;

public class Game implements IMain {
	public static Scene scene = new MainScene();
	
	public void create() throws IOException {
		Textures.InitTextures();
		Animations.InitAnimation();
		AudioManager.init();
		AudioManager.setListenerData();
		scene.create();
	}

	public void update(float delta) {
		scene.update(delta);
	}

	public void render() {
		scene.render();
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void setChannel(Channel channel) {
	}

}

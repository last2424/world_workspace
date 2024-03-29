package com.last2424.ogl.engine.scene;

import java.io.IOException;

import com.last2424.ogl.rendering.SpriteBatch;

public interface Scene {
	public void create() throws IOException;
	
	public void update(float delta);
	
	public void render();
}

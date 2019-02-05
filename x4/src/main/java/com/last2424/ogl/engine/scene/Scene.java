package com.last2424.ogl.engine.scene;

import com.last2424.ogl.rendering.SpriteBatch;

public interface Scene {
	public void create();
	
	public void update(float delta);
	
	public void render();
}
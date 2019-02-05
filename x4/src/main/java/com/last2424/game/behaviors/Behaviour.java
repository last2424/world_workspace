package com.last2424.game.behaviors;

import com.last2424.game.entities.Entity;

public interface Behaviour {
	
	public void start();
	
	public void stop();
	
	public void update(Entity entity, float delta);

	public boolean isStarted();
}
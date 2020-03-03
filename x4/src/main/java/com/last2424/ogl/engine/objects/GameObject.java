package com.last2424.ogl.engine.objects;

import org.joml.Vector2f;

import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.engine.animation.Animation;
import com.last2424.ogl.rendering.SpriteBatch;

public class GameObject {

	public Sprite sprite;
	public Animation currentAnimation;
	
	public Vector2f position;
	
	public void update(float delta) {
		this.sprite.SetPosition(position.x, position.y);
	}

	public void render(SpriteBatch batch) {
		sprite.draw(batch);
	}
	
}

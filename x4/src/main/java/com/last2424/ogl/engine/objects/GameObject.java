package com.last2424.ogl.engine.objects;

import org.joml.Vector2f;

import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.engine.animation.Animation;
import com.last2424.ogl.rendering.SpriteBatch;
import com.last2424.utils.Rectangle;

public class GameObject {

	public Sprite sprite;
	public Animation currentAnimation;
	public Vector2f position;
	public void update(float delta) {
		if(this.sprite != null)
			this.sprite.SetPosition(position.x, position.y);
	}
	public Vector2f getHalf() {
		return new Vector2f(this.position.x+(sprite.GetSize().x/2),this.position.y+(sprite.GetSize().y/2));
	}
	public void render(SpriteBatch batch) {
		if(this.sprite != null)
			sprite.draw(batch);
	}
	
}

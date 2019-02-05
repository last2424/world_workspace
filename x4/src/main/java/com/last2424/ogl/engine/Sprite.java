package com.last2424.ogl.engine;

import java.awt.Color;

import org.joml.Vector2f;

import com.last2424.ogl.engine.animation.Frame;
import com.last2424.ogl.rendering.SpriteBatch;
import com.last2424.ogl.rendering.Texture;
import com.last2424.ogl.rendering.TextureRegion;

public class Sprite {
	
	TextureRegion texture;
	Color color;
	Vector2f position;
	Vector2f size;
	
	public Sprite(String path) {
		this(Texture.loadTexture(path));
	}
	
	public Sprite(Texture texture) {
		this(texture, new Color(1.0f, 1.0f, 1.0f, 1.0f), new Vector2f(0, 0), new Vector2f(texture.getWidth(), texture.getHeight()));
	}
	
	public Sprite(String path, Color color, Vector2f position, Vector2f size) {
		this(Texture.loadTexture(path), color, position, size);
	}
	
	public Sprite(Texture texture, Color color, Vector2f position, Vector2f size) {
		this(new TextureRegion(texture, new Vector2f(0, 0), new Vector2f(texture.getWidth(), texture.getHeight())), color, position, size);
	}
	
	public Sprite(TextureRegion texture, Color color, Vector2f position, Vector2f size) {
		this.texture = texture;
		this.color = color;
		this.position = position;
		this.size = size;
	}
	
	public void SetPosition(float x, float y) {
		this.position.set(x, y);
	}
	
	public void SetColor(Color color) {
		this.color = color;
	}
	
	public void SetTexture(Texture texture) {
		this.texture = new TextureRegion(texture, new Vector2f(0, 0), new Vector2f(texture.getWidth(), texture.getHeight()));
	}
	
	public void SetRegion(Vector2f pos, Vector2f size) {
		this.texture.SetRegionPos(pos);
		this.texture.SetRegionSize(size);
	}
	
	public void SetFrame(Frame frame) {
		this.SetRegion(new Vector2f(frame.GetX(), frame.GetY()), new Vector2f(frame.GetWidth(), frame.GetHeight()));
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(texture, (int)position.x, (int)position.y, (int)size.x, (int)size.y, (float)color.getRed()/255, (float)color.getGreen()/255, (float)color.getBlue()/255, (float)color.getAlpha()/255);
	}

	public Vector2f GetPosition() {
		return this.position;
	}
	
	public Vector2f GetSize() {
		return this.size;
	}
}

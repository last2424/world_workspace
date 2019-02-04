package last2424.game.world;

import java.awt.Color;

import org.joml.Vector2f;

import last2424.ogl.engine.Sprite;
import last2424.ogl.rendering.SpriteBatch;
import last2424.ogl.rendering.TextureRegion;

public class Tree {
	Sprite[] sprites = new Sprite[2];
	Vector2f position, lowPosition;
	
	public Tree(TreeType type, Vector2f position) {
		this.position = position;
		this.lowPosition = new Vector2f(position.x, position.y + 50);
		if(type == TreeType.NORMAL) {
			sprites[0] = new Sprite(new TextureRegion("test/trees/blue.png", new Vector2f(0, 0), new Vector2f(53, 55)), 
					Color.WHITE, this.position, new Vector2f(53, 55));
			sprites[1] = new Sprite(new TextureRegion("test/trees/red.png", new Vector2f(0, 55), new Vector2f(53, 19)), 
					Color.WHITE, this.lowPosition, new Vector2f(53, 19));
		}
	}
	
	public void SetPosition(float x, float y) {
		this.SetPosition(new Vector2f(x, y));
	}
	
	public void SetPosition(Vector2f position) {
		this.position.set(position.x, position.y);
		this.lowPosition.set(position.x, position.y + 50);
	}
	
	public Vector2f GetPosition() {
		return position;
	}
	
	public void drawLayer(int layer, SpriteBatch batch) {
		sprites[layer].draw(batch);
	}
	
	public enum TreeType {
		NORMAL,
		RED
	}
}

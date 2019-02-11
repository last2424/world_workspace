package com.last2424.game.world.houses;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.rendering.SpriteBatch;

public class House {
		Sprite[] sprites;
		String name;
		
		public Vector2f position;
		
		List<Rectangle> collisionZones;
		
		public House(String name, int countLayers, Vector2f position) {
			sprites = new Sprite[countLayers];
			this.name = name;
			this.position = position;
			collisionZones = new ArrayList<Rectangle>();
		}
		
		public void SetSprite(int index, Sprite sprite) {
			sprites[index] = sprite;
		}
		
		public void AddCollisionZone(int x, int y, int width, int height) {
			collisionZones.add(new Rectangle(x, y, width, height));
		}
		
		public void AddCollisionZone(int layer) {
			Vector2f position = sprites[layer].GetPosition(), size = sprites[layer].GetSize();
			collisionZones.add(new Rectangle((int)position.x, (int)position.y, (int)size.x, (int)size.y));
		}
		
		public List<Rectangle> GetCollisionZones() {
			return collisionZones;
		}
		
		public void drawLayer(int index, SpriteBatch batch) {
			sprites[index].draw(batch);
		}
}

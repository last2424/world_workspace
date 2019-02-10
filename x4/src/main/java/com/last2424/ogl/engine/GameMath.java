package com.last2424.ogl.engine;

import java.awt.Rectangle;

import org.joml.Vector2f;

import com.last2424.game.entities.Entity;
import com.last2424.game.world.Tile;
import com.last2424.game.world.Tile.TileConfig;

public class GameMath {
	
	public static float GetDistance(Entity entityA, Entity entityB) {
		return (float)Math.sqrt((entityA.GetPosition().y - entityB.GetPosition().y) * (entityA.GetPosition().y - entityB.GetPosition().y) + (entityA.GetPosition().x - entityB.GetPosition().x) * (entityA.GetPosition().x - entityB.GetPosition().x));
	}
	
	public static float GetDistance(Entity entityA, Vector2f point) {
		return (float)Math.sqrt((entityA.GetPosition().y - point.y) * (entityA.GetPosition().y - point.y) + (entityA.GetPosition().x - point.x) * (entityA.GetPosition().x - point.x));
	}
	
	
	
	public static boolean Intersects(Rectangle rectA, Rectangle rectB) {
		return rectA.intersects(rectB);
	}
	
	public static boolean Intersects(Entity entity, Tile tile) {
		if(tile.GetTileConfig() == TileConfig.SOLID) {
			return new Rectangle((int)tile.GetPosition().x, (int)tile.GetPosition().y, (int)tile.GetSprite().size.x, (int)tile.GetSprite().size.y).intersects(new Rectangle((int)entity.GetPosition().x, (int)entity.GetPosition().y, (int)entity.GetSize().x, (int)entity.GetSize().y));
		}
		
		return false;
	}
}

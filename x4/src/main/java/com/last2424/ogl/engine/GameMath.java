package com.last2424.ogl.engine;

import java.awt.Rectangle;

import org.joml.Vector2f;

import com.last2424.game.entities.Entity;
import com.last2424.game.world.Tile;
import com.last2424.game.world.Tile.TileConfig;

public class GameMath {
	
	public static float GetDistance(Entity entityA, Entity entityB) {
		Vector2f positionA = entityA.GetPosition(), positionB = entityB.GetPosition();
		return (float)Math.sqrt((positionA.y - positionB.y) * (positionA.y - positionB.y) + (positionA.x - positionB.x) * (positionA.x - positionB.x));
	}
	
	public static float GetDistance(Entity entityA, Vector2f point) {
		Vector2f positionA = entityA.GetPosition();
		return (float)Math.sqrt((positionA.y - point.y) * (positionA.y - point.y) + (positionA.x - point.x) * (positionA.x - point.x));
	}
	
	
	
	public static boolean Intersects(Rectangle rectA, Rectangle rectB) {
		return rectA.intersects(rectB);
	}
	
	public static boolean Intersects(Entity entity, Tile tile) {
		Vector2f positionA = entity.GetPosition(), positionB = tile.GetPosition();
		Vector2f sizeA = entity.GetSize(), sizeB = tile.GetSprite().GetSize();
		if(tile.GetTileConfig() == TileConfig.SOLID) {
			return new Rectangle((int)positionB.x, (int)positionB.y, (int)sizeB.x, (int)sizeB.y).intersects(new Rectangle((int)positionA.x, (int)positionA.y, (int)sizeA.x, (int)sizeA.y));
		}
		
		return false;
	}
}

package last2424.ogl.engine;

import java.awt.Rectangle;

import org.joml.Vector2f;

import last2424.game.entities.Entity;

public class GameMath {
	
	public static float GetDistance(Entity entityA, Entity entityB) {
		return (float) Math.sqrt((entityA.GetPosition().y - entityB.GetPosition().y) * (entityA.GetPosition().y - entityB.GetPosition().y) + (entityA.GetPosition().x - entityB.GetPosition().x) * (entityA.GetPosition().x - entityB.GetPosition().x));
	}
	
	public static float GetDistance(Entity entityA, Vector2f point) {
		return (float) Math.sqrt((entityA.GetPosition().y - point.y) * (entityA.GetPosition().y - point.y) + (entityA.GetPosition().x - point.x) * (entityA.GetPosition().x - point.x));
	}
	
	
	
	public static boolean Intersects(Rectangle rectA, Rectangle rectB) {
		return rectA.intersects(rectB);
	}
}

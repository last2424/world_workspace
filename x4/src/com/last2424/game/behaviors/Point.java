package last2424.game.behaviors;

import org.joml.Vector2f;

public class Point {
	Vector2f position;
	float delay;
	
	public Point(Vector2f position) {
		this(position, 0);
	}
	
	public Point(Vector2f position, float delay) {
		this.position = position;
		this.delay = delay;
	}
	
	public Vector2f GetPosition() {
		return position;
	}
	
	public float GetDelay() {
		return delay;
	}
}

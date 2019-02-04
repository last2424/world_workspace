package last2424.game.behaviors;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import last2424.game.Logic;
import last2424.game.entities.Entity;
import last2424.ogl.Start;
import last2424.ogl.engine.GameMath;

public class ABMoveBehaviour implements Behaviour {
	List<Point> points;
	int currentPoint = 0;
	
	boolean isStarted = false;
	boolean round = false;
	
	float currentDelay = 0;
	
	public ABMoveBehaviour(boolean round) {
		points = new ArrayList<Point>();
		this.round = round;
	}
	
	public void AddPoint(Point point) {
		points.add(point);
	}
	
	public void start() {
		isStarted = true;
	}
	
	public void stop() {
		isStarted = false;
	}
	
	public void update(Entity entity, float delta) {
		if(isStarted) {
			float moveX = 0, moveY = 0;
			Rectangle rect = new Rectangle((int)entity.GetPosition().x, (int)entity.GetPosition().y, (int)entity.GetSize().x, (int)entity.GetSize().y), 
					rect2 = new Rectangle((int)points.get(currentPoint).GetPosition().x, (int)points.get(currentPoint).GetPosition().y, 1, 1) ;
			if(!GameMath.Intersects(rect, rect2)) {
				if(entity.GetPosition().x > points.get(currentPoint).GetPosition().x) {
					moveX = -entity.GetMoveSpeed();
				}
				if(entity.GetPosition().x < points.get(currentPoint).GetPosition().x) {
					moveX = entity.GetMoveSpeed();
				}
				
				if(entity.GetPosition().y > points.get(currentPoint).GetPosition().y) {
					moveY = -entity.GetMoveSpeed();
				}
				if(entity.GetPosition().y < points.get(currentPoint).GetPosition().y) {
					moveY = entity.GetMoveSpeed();
				}
				
				entity.SetPosition(entity.GetPosition().x+moveX, entity.GetPosition().y+moveY);
			}else {
				nextPoint(delta);
			}
		}
	}
	
	private void nextPoint(float delta) {
		if(currentDelay <= 0) {
			currentDelay = points.get(currentPoint).delay;
		}
		
		if(points.get(currentPoint).delay <= 0) {
			points.get(currentPoint).delay = currentDelay;
			currentDelay = 0;
			if(currentPoint < points.size()-1) {
				currentPoint++;
			}else{
				if(round) {
					currentPoint = 0;
				}else {
					stop();
				}
			}
		}else {
			points.get(currentPoint).delay -= delta;
		}
	}

	@Override
	public boolean isStarted() {
		return isStarted;
	}
}
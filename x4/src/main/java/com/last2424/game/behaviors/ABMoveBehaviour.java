package com.last2424.game.behaviors;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import com.last2424.game.Logic;
import com.last2424.game.entities.Entity;
import com.last2424.ogl.Start;
import com.last2424.ogl.engine.GameMath;

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
			Vector2f positionPoint = points.get(currentPoint).GetPosition();
			Vector2f entityPosition = entity.GetPosition(), entitySize = entity.GetSize();
			Rectangle rect = new Rectangle((int)entityPosition.x, (int)entityPosition.y, (int)entitySize.x, (int)entitySize.y), 
					rect2 = new Rectangle((int)positionPoint.x, (int)positionPoint.y, 1, 1) ;
			if(!GameMath.Intersects(rect, rect2)) {
				if(entityPosition.x > positionPoint.x) {
					moveX = -entity.GetMoveSpeed();
				}
				if(entityPosition.x < positionPoint.x) {
					moveX = entity.GetMoveSpeed();
				}
				
				if(entityPosition.y > positionPoint.y) {
					moveY = -entity.GetMoveSpeed();
				}
				if(entityPosition.y < positionPoint.y) {
					moveY = entity.GetMoveSpeed();
				}
				
				entity.SetPosition(entityPosition.x+moveX, entityPosition.y+moveY);
			}else {
				nextPoint(delta);
			}
		}
	}
	
	private void nextPoint(float delta) {
		Point point = points.get(currentPoint);
		if(currentDelay <= 0) {
			currentDelay = point.delay;
		}
		
		if(point.delay <= 0) {
			point.delay = currentDelay;
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
			point.delay -= delta;
		}
	}

	@Override
	public boolean isStarted() {
		return isStarted;
	}
}
package com.last2424.game.behaviors;

import org.joml.Vector2f;

import com.last2424.game.Dice;
import com.last2424.game.Logic;
import com.last2424.game.entities.Entity;
import com.last2424.game.entities.EntityNPC;
import com.last2424.game.entities.EntityNPC.Relationships;
import com.last2424.ogl.engine.GameMath;

public class AttackBehaviour implements Behaviour {
	boolean isStarted = false;
	public boolean isAttacked = false;
	Entity target = null;
	Vector2f pointAttack = null;
	
	@Override
	public void start() {
		isStarted = true;
	}

	@Override
	public void stop() {
		isStarted = false;
	}

	@Override
	public void update(Entity entity, float delta) {
		
		if(isStarted) {
			if(((EntityNPC) entity).GetBehaviour().isStarted()) {
				((EntityNPC) entity).GetBehaviour().stop();
			}
			
			if(GameMath.GetDistance(entity, target) < 5) {
				attack();
			}else {
				float moveX = 0, moveY = 0;
				if(entity.GetPosition().x > target.GetPosition().x) {
					moveX = -entity.GetMoveSpeed();
				}
				if(entity.GetPosition().x < target.GetPosition().x) {
					moveX = entity.GetMoveSpeed();
				}
				
				if(entity.GetPosition().y > target.GetPosition().y) {
					moveY = -entity.GetMoveSpeed();
				}
				if(entity.GetPosition().y < target.GetPosition().y) {
					moveY = entity.GetMoveSpeed();
				}
				entity.SetPosition(entity.GetPosition().x+moveX, entity.GetPosition().y+moveY);
				
				if(GameMath.GetDistance(target, pointAttack) > 400) {
					int temp = Dice.rollDice();
					if(temp > 3) {
						target = null;
						pointAttack = null;
						stop();
					}
				}
			}
		}else {
			if(!((EntityNPC) entity).GetBehaviour().isStarted()) {
				((EntityNPC) entity).GetBehaviour().start();
			}
			
			for(int i = 0; i < ((EntityNPC) entity).GetEntitiesArroundMe().size(); i++) {
				for(String enemy : ((EntityNPC) entity).enemies) {
					if((((EntityNPC) entity).GetEntitiesArroundMe().get(i)).GetName().equals(enemy)) {
						target = ((EntityNPC) entity).GetEntitiesArroundMe().get(i);
						pointAttack = new Vector2f();
						pointAttack.x = target.GetPosition().x;
						pointAttack.y = target.GetPosition().y;
						start();
						return;
					}
				}
			}
		}
	}

	private void attack() {
		isAttacked = true;
	}

	@Override
	public boolean isStarted() {
		return isStarted;
	}

}

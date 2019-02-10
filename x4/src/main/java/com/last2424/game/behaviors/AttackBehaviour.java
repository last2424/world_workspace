package com.last2424.game.behaviors;

import java.util.List;

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
		Behaviour behaviour = ((EntityNPC) entity).GetBehaviour();
		if(isStarted) {
			if(behaviour.isStarted()) {
				behaviour.stop();
			}
			
			if(GameMath.GetDistance(entity, target) < 5) {
				attack();
			}else {
				Vector2f entityPos = entity.GetPosition(), targetPos = target.GetPosition();
				
				float moveSpeed = entity.GetMoveSpeed();
				
				float moveX = 0, moveY = 0;
				
				if(entityPos.x > targetPos.x) {
					moveX = -moveSpeed;
				}
				if(entityPos.x < targetPos.x) {
					moveX = moveSpeed;
				}
				
				if(entityPos.y > targetPos.y) {
					moveY = -moveSpeed;
				}
				if(entityPos.y < targetPos.y) {
					moveY = moveSpeed;
				}
				entity.SetPosition(entityPos.x+moveX, entityPos.y+moveY);
				
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
			if(!behaviour.isStarted()) {
				behaviour.start();
			}
			List<Entity> ent = ((EntityNPC) entity).GetEntitiesArroundMe();
			for(int i = 0; i < ent.size(); i++) {
				for(String enemy : ((EntityNPC) entity).enemies) {
					if((ent.get(i)).GetName().equals(enemy)) {
						target = ent.get(i);
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

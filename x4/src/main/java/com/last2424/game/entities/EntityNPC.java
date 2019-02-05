package com.last2424.game.entities;

import java.util.ArrayList;
import java.util.List;

import com.last2424.game.Logic;
import com.last2424.game.behaviors.AttackBehaviour;
import com.last2424.game.behaviors.Behaviour;
import com.last2424.game.entities.EntityNPC.Relationships;
import com.last2424.game.mechanics.dialog.Dialog;
import com.last2424.game.scenes.MainScene;
import com.last2424.ogl.engine.GameMath;
import com.last2424.ogl.engine.Sprite;

public class EntityNPC extends Entity {

	Relationships relationship;
	public Dialog dialog;
	
	Behaviour behaviour;
	AttackBehaviour attackBehaviour;
	
	List<Entity> entitiesArroundMe;
	public String[] enemies;
	
	public boolean isMerchant = false;

	
	public EntityNPC(String name, int lvl, int strength, int dexterity, int intelegence, EntityType entityType,
			Sprite sprite, Relationships relationship) {
		super(name, lvl, strength, dexterity, intelegence, entityType, sprite);
		this.relationship = relationship;
		attackBehaviour = new AttackBehaviour();
		entitiesArroundMe = new ArrayList<Entity>();
	}
	
	public void SetBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
	}
	
	public Behaviour GetBehaviour() {
		return behaviour;
	}
	
	public void update(float delta) {
		for(Entity entity : MainScene.characters.allEntities) {
			if(entity != this) {
				if(GameMath.GetDistance(this, entity) < 200) {
					entitiesArroundMe.add(entity);
				}
			}
		}
		
		for(int i = 0; i < entitiesArroundMe.size(); i++) {
			if(GameMath.GetDistance(this, entitiesArroundMe.get(i)) > 200) {
				entitiesArroundMe.remove(entitiesArroundMe.get(i));
			}
		}
		
		behaviour.update(this, delta);
		attackBehaviour.update(this, delta);
	}
	
	public enum Relationships {
		FAMILY,
		FRIENDLY,
		NEUTRAL,
		ENEMY
	}

	public void SetDialog(Dialog dialog) {
		this.dialog = dialog;
	}
	
	public List<Entity> GetEntitiesArroundMe() {
		return entitiesArroundMe;
	}
	
	public Relationships GetRelationShip() {
		return relationship;
	}

}

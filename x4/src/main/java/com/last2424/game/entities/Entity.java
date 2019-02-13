package com.last2424.game.entities;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import com.last2424.game.entities.EntityNPC.Relationships;
import com.last2424.game.mechanics.Skill;
import com.last2424.game.mechanics.items.Storage;
import com.last2424.ogl.engine.GameMath;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.rendering.SpriteBatch;

public class Entity {
	//Base
	private String name;
	private Storage storage;
	private Skill skills;
	private EntityType entityType;
	//Stats
	private int lvl, xp = 0, max_xp = 1000;
	private int object_authority;
	private Color oa_color;
	private int strength, dexterity, intelegence; //RGB System
	private int hp, max_hp = 100, energy, max_energy = 100,  damage, defence, move_speed;
	
	Sprite sprite;
	
	public int money;
	
	Vector2f tempPosition = new Vector2f();

	public Entity(String name, int lvl, int strength, int dexterity, int intelegence, EntityType entityType, Sprite sprite) {
		this.name = name;
		this.storage = new Storage();
		this.skills = new Skill();
		this.entityType = entityType;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelegence = intelegence;
		this.lvl = lvl;
		this.sprite = sprite;
		StatRecalculation();
	}
	
	private void StatRecalculation() {
		max_xp = (int) (max_xp + (1000 * Math.round((Math.pow(entityType.getMultiplier(), lvl+1)))));
		max_hp = max_hp + (strength*lvl); 
		max_energy = max_energy + (intelegence*lvl);
		move_speed = dexterity;
		damage = (int) ((strength+dexterity)/3 + (entityType.getMultiplier() * lvl)); //Урон с руки, без модифиакторов
		defence = (int) (((strength*dexterity)/(entityType.getMultiplier() * lvl))/10);
		oa_color = new Color(strength, dexterity, intelegence, 255);
		object_authority = (strength+dexterity+intelegence);
	}
	
	public void update(float delta) {
		if(xp >= max_xp) {
			lvl += 1;
			StatRecalculation();
		}
	}
	
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}

	public enum EntityType {
		PLAYER(1.1),
		ANIMAL(0),
		UNDEAD(0),
		HUMANS(0);
		
		private double multiplier;
		
		EntityType(double multiplier){
			this.multiplier = multiplier;
		}
		
		public double getMultiplier() {
	        return this.multiplier;
	    }
		
		public void setMultiplier(double multiplier) {
			this.multiplier = multiplier;
		}
	}

	public Storage GetStorage() {
		return storage;
	}
	
	public void SetPosition(float x, float y) {
		this.sprite.SetPosition(x, y);
	}
	
	public Vector2f GetPosition() {
		return this.sprite.GetPosition();
	}
	
	public Sprite GetSprite() {
		return this.sprite;
	}
	
	public float GetMoveSpeed() {
		return move_speed;
	}

	public Vector2f GetSize() {
		return sprite.GetSize();
	}
	
	public String GetName() {
		return this.name;
	}
	
	public void checkCollision(boolean xAxis, boolean yAxis, Rectangle zone) {
			if(GameMath.Intersects(this.GetRectangle(), zone)) {
				if(xAxis) {
					if((this.GetRectangle().getMinX() <= zone.getMaxX()) 
					|| (this.GetRectangle().getMaxX() >= zone.getMinX())) {
						this.SetPosition(tempPosition.x, this.GetPosition().y);
					}
				}
				
				if(yAxis) {
					if((this.GetRectangle().getMinY() <= zone.getMaxY()) 
					|| (this.GetRectangle().getMaxY() >= zone.getMinY())) {
						this.SetPosition(this.GetPosition().x, tempPosition.y);
					}
				}
			}
	}

	public Rectangle GetRectangle() {
		return new Rectangle((int)GetPosition().x, (int)GetPosition().y, (int)GetSize().x, (int)GetSize().y);
	}
}

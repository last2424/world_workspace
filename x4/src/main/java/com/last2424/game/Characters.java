package com.last2424.game;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import com.last2424.game.behaviors.ABMoveBehaviour;
import com.last2424.game.behaviors.Behaviour;
import com.last2424.game.behaviors.Point;
import com.last2424.game.entities.Entity;
import com.last2424.game.entities.EntityNPC;
import com.last2424.game.entities.Entity.EntityType;
import com.last2424.game.entities.EntityNPC.Relationships;
import com.last2424.game.scenes.MainScene;
import com.last2424.ogl.engine.Sprite;
import com.last2424.ogl.rendering.SpriteBatch;

public class Characters {
		public List<Entity> allEntities;
		
		
		public EntityNPC testNPC;
		
		public Characters() {
			allEntities = new ArrayList<Entity>();
		}
		
		public void initializeNPC() {
			testNPC = new EntityNPC("Test", 1, 1, 1, 1, EntityType.HUMANS, new Sprite("test.png"), Relationships.NEUTRAL);
			testNPC.SetPosition(200, 200);
			testNPC.SetBehaviour(new ABMoveBehaviour(true));
			ABMoveBehaviour behaviour = (ABMoveBehaviour) testNPC.GetBehaviour();
			behaviour.AddPoint(new Point(new Vector2f(100, 100)));
			behaviour.AddPoint(new Point(new Vector2f(0, 0)));
			behaviour.AddPoint(new Point(new Vector2f(0, 50)));
			behaviour.AddPoint(new Point(new Vector2f(0, 100)));
			behaviour.AddPoint(new Point(new Vector2f(100, 200), 5f));
			behaviour.AddPoint(new Point(new Vector2f(200, 200)));
			behaviour.start();
			testNPC.enemies = new String[1];
			//testNPC.enemies[0] = "Игрок";
			this.AddEntity(testNPC);
			this.AddEntity(MainScene.player);
		}
		
		public void AddEntity(Entity entity) {
			allEntities.add(entity);
		}
		
		public void RemoveEntity(Entity entity) {
			allEntities.remove(entity);
		}
		
		public void RemoveEntity(int e) {
			allEntities.remove(e);
		}
		
		public Entity GetEntity(int e) {
			return allEntities.get(e);
		}

		public void update(float delta) {
			for(int i = 0; i < allEntities.size(); i++) {
					allEntities.get(i).update(delta);
			}
		}
		
		public void draw(SpriteBatch batch) {
			for(int i = 0; i < allEntities.size(); i++) {
				allEntities.get(i).draw(batch);
			}
		}
}
